package br.dev.rvz.forum.configurations.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.SignatureVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JWTVerifyToken(
    @Value("\${jwt.secret}")
    val secretKey: String,
) {
    fun isValid(token: String?): Boolean {
        val decodedJWT = JWT.decode(token)

        return try {
            Algorithm.HMAC512(secretKey).verify(decodedJWT)
            true
        } catch (e: SignatureVerificationException) {
            false
        }
    }

    fun getAuthentication(token: String?): Authentication {
        val decodedJWT = JWT.decode(token)
        val username = decodedJWT.subject

        return UsernamePasswordAuthenticationToken(username, null, null)
    }
}