package br.dev.rvz.forum.configurations.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class JWTVerifyToken(
    @Value("\${jwt.secret}")
    val secretKey: String,
) {
    fun isValid(token: String?): Boolean {
        return try {
            val decodedJWT = JWT.decode(token)
            Algorithm.HMAC512(secretKey).verify(decodedJWT)
            true
        } catch (e: SignatureVerificationException) {
            false
        } catch (e: JWTDecodeException) {
            false
        }
    }

    fun getAuthentication(token: String?): Authentication {
        val decodedJWT = JWT.decode(token)
        val username = decodedJWT.subject
        val authorities: MutableList<SimpleGrantedAuthority> = getAuthories(decodedJWT)
        
        return UsernamePasswordAuthenticationToken(username, null, authorities)
    }

    private fun getAuthories(decodedJWT: DecodedJWT): MutableList<SimpleGrantedAuthority> {
        val roles = decodedJWT.getClaim("roles")
        val authorities: MutableList<SimpleGrantedAuthority> = mutableListOf()
        roles.asArray(String::class.java).forEach { role ->
            authorities.add(SimpleGrantedAuthority(role))
        }

        return authorities
    }
}