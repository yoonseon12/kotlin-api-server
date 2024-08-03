package io.study.moduletemp.web.exception.error

import org.springframework.http.HttpStatus

enum class ServerErrorCode(
    private val httpStatus: HttpStatus,
    private val description: String,
) : ErrorCode {

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 에러가 발생했습니다.")
    ;

    override fun getHttpStatus(): HttpStatus {
        return httpStatus
    }

    override fun getDescription(): String {
        return description
    }
}