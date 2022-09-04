package br.dev.rvz.forum.configurations.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTGenerateToken(
    @Value("\${jwt.secret}")
    val secretKey: String,

    @Value("\${jwt.time.expire}")
    val expiration: Long
) {

    fun getToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String {
        val roles: MutableList<String> = mutableListOf()
        authorities.forEach {
            roles.add(it.authority)
        }

        return JWT.create()
            .withIssuer(username)
            .withSubject(username)
            .withArrayClaim("roles", roles.toTypedArray())
            .withExpiresAt(Date(System.currentTimeMillis() + expiration))
            .sign(Algorithm.HMAC512(secretKey))
    }
}

