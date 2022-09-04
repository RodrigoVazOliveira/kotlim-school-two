package br.dev.rvz.forum.configurations.filters

import br.dev.rvz.forum.configurations.jwt.JWTGenerateToken
import br.dev.rvz.forum.models.authentication.Credencials
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTVerifyAuthenticationFilter(
    private val authManager: AuthenticationManager?,
    private val jwtGenerateToken: JWTGenerateToken
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val credencials = ObjectMapper().readValue(request?.inputStream, Credencials::class.java)
        val token = UsernamePasswordAuthenticationToken(credencials.username, credencials.password)

        return authManager!!.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val username = (authResult?.principal as UserDetails).username
        val token = jwtGenerateToken.getToken(username)

        response?.addHeader("Authorization", "Bearer $token")
    }
}
