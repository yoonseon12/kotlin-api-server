package io.study.moduleapi.common.base.response

import io.study.modulecommon.exception.error.ErrorCode

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

        fun of(status: Int, message: String?): ErrorResponse {
            return ErrorResponse(status,
                message ?: "유효성 검증에 통과하지 못했습니다.",
            )
        }
    }
}