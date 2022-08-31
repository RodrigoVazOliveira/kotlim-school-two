package br.dev.rvz.forum.exceptions

import br.dev.rvz.forum.models.dto.ErrorResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundException(e: NotFoundException, httpRequest: HttpServletRequest): ErrorResponseDTO {
        return ErrorResponseDTO(
            message = e.message,
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            path = httpRequest.servletPath
        )
    }
}