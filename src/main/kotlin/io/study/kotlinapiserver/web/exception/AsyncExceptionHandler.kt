package io.study.kotlinapiserver.web.exception

import io.study.kotlinapiserver.web.base.log.logger
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import java.lang.reflect.Method

class AsyncExceptionHandler : AsyncUncaughtExceptionHandler {

    private val log = logger()

    override fun handleUncaughtException(ex: Throwable, method: Method, vararg params: Any?) {
        log.error("AsyncExceptionHandler handleUncaughtException !!", ex)
    }

}