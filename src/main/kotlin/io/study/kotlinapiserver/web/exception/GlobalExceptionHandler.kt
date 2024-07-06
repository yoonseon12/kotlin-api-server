package io.study.kotlinapiserver.web.exception

import io.study.kotlinapiserver.web.base.log.logger
import io.study.kotlinapiserver.web.base.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = logger()

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): ResponseEntity<ErrorResponse> {
        logger.error(e.message)

        return ResponseEntity.status(e.errorCode.getHttpStatus())
            .body(ErrorResponse.of(e.errorCode))
    }
}