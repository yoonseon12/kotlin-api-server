package io.study.moduletemp.web.exception

import io.study.moduletemp.web.exception.error.ErrorCode

class ApiException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.getDescription())