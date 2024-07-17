package io.study.kotlinapiserver.api.domain.member.domain.dto.request

data class MemberResetPasswordRequest(
    val email: String,
    val tempPassword: String? = null,
)