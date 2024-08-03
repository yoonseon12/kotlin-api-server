package io.study.modulecommon.exception

import io.study.modulecommon.exception.error.ErrorCode

class ApiException(
    val errorCode: ErrorCode,
) : RuntimeException(errorCode.getDescription())