package br.dev.rvz.forum.exceptions

import br.dev.rvz.forum.models.dto.ErrorResponseDTO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exception(e: Exception, httpRequest: HttpServletRequest): ErrorResponseDTO {
        return ErrorResponseDTO(
            message = e.message,
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            path = httpRequest.servletPath
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun notFoundException(e: MethodArgumentNotValidException, httpRequest: HttpServletRequest): ErrorResponseDTO {
        val errorMessage = HashMap<String, String?>()
        e.bindingResult.fieldErrors.forEach { fieldError ->
            errorMessage.put(
                fieldError.field,
                fieldError.defaultMessage
            )
        }
        val objectMapper = jacksonObjectMapper()
        val errorMessageToJson = objectMapper.writeValueAsString(errorMessage)


        return ErrorResponseDTO(
            message = errorMessageToJson,
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            path = httpRequest.servletPath
        )
    }
}