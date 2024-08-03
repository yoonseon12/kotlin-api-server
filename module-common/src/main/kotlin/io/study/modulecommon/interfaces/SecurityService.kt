package io.study.modulecommon.interfaces

import io.study.modulecommon.dto.TokenDto

interface SecurityService {
    fun authenticate(email: String, password: String): TokenDto
    fun getCurrentUserEmail(): String
    fun encodePassword(password: String): String
}