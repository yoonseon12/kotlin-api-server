package io.study.kotlinapiserver.web.exception

import io.study.kotlinapiserver.web.base.log.logger
import io.study.kotlinapiserver.web.base.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = logger()

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): ResponseEntity<ErrorResponse> {
        log.error(e.message)

        return ResponseEntity.status(e.errorCode.getHttpStatus())
            .body(ErrorResponse.of(e.errorCode))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(
        e: MethodArgumentNotValidException
    ): ResponseEntity<ErrorResponse> {
        log.warn(e.message, e)

        return ResponseEntity.status(e.statusCode)
            .body(ErrorResponse.of(e.statusCode.value(), e.bindingResult.fieldError?.defaultMessage))
    }
}