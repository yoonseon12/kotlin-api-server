package io.study.kotlinapiserver.web.exception.error

import org.springframework.http.HttpStatus

interface ErrorCode {

    fun getHttpStatus(): HttpStatus

    fun getDescription(): String

}