package io.study.moduledomain.member.dto.request

data class MemberSigninRequest(
    val email: String,
    val password: String,
)