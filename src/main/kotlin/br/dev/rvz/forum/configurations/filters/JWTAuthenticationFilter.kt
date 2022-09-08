package br.dev.rvz.forum.configurations.filters

import br.dev.rvz.forum.configurations.jwt.JWTVerifyToken
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.server.ResponseStatusException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    private val jwtVerify: JWTVerifyToken
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Token nao informado!")
        val jwt = getTokenDetails(token)
        validateToken(jwt)

        filterChain.doFilter(request, response)
    }

    private fun validateToken(jwt: String?) {
        if (jwtVerify.isValid(jwt)) {
            val authentication = jwtVerify.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }
    }

    private fun getTokenDetails(token: String): String {
        return token.let { jwt ->
            jwt.startsWith("Bearer  ")
            jwt.substring(7, token.length)
        }
    }
}