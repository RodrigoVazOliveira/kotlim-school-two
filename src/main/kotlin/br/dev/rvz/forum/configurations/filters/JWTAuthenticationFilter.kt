package br.dev.rvz.forum.configurations.filters

import br.dev.rvz.forum.configurations.jwt.JWTVerifyToken
import br.dev.rvz.forum.models.dto.ErrorResponseDTO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
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
        if (token != null) {
            val jwt = getTokenDetails(token)
            validateToken(jwt)
            filterChain.doFilter(request, response)
        } else {
            val errorResponseDTO = ErrorResponseDTO(
                message = "Token nao informado!",
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.name,
                path = request.servletPath
            )

            response.contentType = MediaType.APPLICATION_JSON.toString()
            response.status = HttpStatus.BAD_REQUEST.value()
            response.writer.write(jacksonObjectMapper().writeValueAsString(errorResponseDTO))
            filterChain.doFilter(request, response)
        }
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