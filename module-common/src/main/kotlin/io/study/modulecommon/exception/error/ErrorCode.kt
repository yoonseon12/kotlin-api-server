package io.study.modulecommon.exception.error

import org.springframework.http.HttpStatus


interface ErrorCode {

    fun getHttpStatus(): HttpStatus

    fun getDescription(): String

    companion object {
        val serverErrorMessage: String = "서버에서 에러가 발생했습니다."
    }

}