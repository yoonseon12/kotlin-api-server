package io.study.kotlinapiserver.web.base.response

import io.study.kotlinapiserver.web.exception.error.ErrorCode

class ErrorResponse(
    val status: Int,
    val message: String,
) {
    companion object {
        fun of(errorCode: ErrorCode): ErrorResponse {
            return ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getDescription(),
            )
        }
    }
}