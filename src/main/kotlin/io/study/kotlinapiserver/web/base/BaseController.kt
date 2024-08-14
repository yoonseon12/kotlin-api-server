package io.study.kotlinapiserver.web.base

import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api")
open class BaseController() {
    companion object {
        const val X_API_VERSION: String = "x-api-version=v1"
    }
}