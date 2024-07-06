package io.study.kotlinapiserver.web.exception.error

import org.springframework.http.HttpStatus

enum class MemberErrorCode(
    private val httpStatus: HttpStatus,
    private val message: String,
): ErrorCode {

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다."),
    CONFLICT_DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임이 존재합니다."),
    CONFLICT_DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 이메일이 존재합니다.")
    ;

    override fun getHttpStatus(): HttpStatus {
        return httpStatus
    }

    override fun getDescription(): String {
        return message
    }

}