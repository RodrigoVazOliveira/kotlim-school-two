package br.dev.rvz.forum.configurations.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTGenerateToken(
    @Value("\${jwt.secret}")
    val secretKey: String,

    @Value("\${jwt.time.expire}")
    val expiration: Long
) {

    fun getToken(username: String): String {
        return JWT.create()
            .withIssuer(username)
            .withSubject(username)
            .withExpiresAt(Date(System.currentTimeMillis() + expiration))
            .sign(Algorithm.HMAC512(secretKey))
    }
}