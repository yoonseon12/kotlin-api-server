package io.study.moduleapplication.interfaces

import io.study.modulecommon.dto.TokenDto

interface SecurityService {
    fun authenticate(email: String, password: String): TokenDto
}