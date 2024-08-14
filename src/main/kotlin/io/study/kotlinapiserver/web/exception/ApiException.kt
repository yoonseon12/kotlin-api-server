package io.study.kotlinapiserver.web.exception

import io.study.kotlinapiserver.web.exception.error.ErrorCode

class ApiException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.getDescription())