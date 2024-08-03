package io.study.moduledomain.member.dto.request

data class MemberResetPasswordRequest(
    val email: String,
    val tempPassword: String? = null,
)