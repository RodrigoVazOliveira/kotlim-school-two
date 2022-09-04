package br.dev.rvz.forum.configurations.filters

import br.dev.rvz.forum.configurations.jwt.JWTVerifyToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
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
        val jwt = getTokenDetails(token)
        if (jwtVerify.isValid(jwt)) {
            val authentication = jwtVerify.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenDetails(token: String?): String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer  ")
            jwt.substring(7, token.length)
        }
    }
}