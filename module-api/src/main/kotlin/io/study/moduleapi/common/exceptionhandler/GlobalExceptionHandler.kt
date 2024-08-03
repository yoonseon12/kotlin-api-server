package io.study.moduleapi.common.exceptionhandler

import io.study.moduleapi.common.base.response.ErrorResponse
import io.study.modulecommon.exception.ApiException
import io.study.modulecommon.log.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = logger()

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(e.errorCode.getHttpStatus())
            .body(ErrorResponse.of(e.errorCode))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(
        e: MethodArgumentNotValidException
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(e.statusCode)
            .body(ErrorResponse.of(e.statusCode.value(), e.bindingResult.fieldError?.defaultMessage))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handlerHttpMessageNotReadableException(
        e: HttpMessageNotReadableException
    ): ResponseEntity<ErrorResponse> {
       return ResponseEntity.status(HttpStatus.BAD_REQUEST)
           .body(ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "요청정보가 올바르지 않습니다."))
    }

    @ExceptionHandler(Exception::class)
    fun handlerException(
        e: Exception
    ): ResponseEntity<ErrorResponse> {
        // todo :
        //  MemberDetailsService에서 던지는 ApiException가 해당 메서드로 던져진다.
        //  디버깅을 끝까지 해봤으나 원인을 찾지 못해서 임시로 분기처리함.
        if (e.cause is ApiException) {
            val apiException = e.cause as ApiException
            return handleApiException(apiException)
        }

        log.error(e.message, e)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .body(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 에러 입니다."))
    }
}