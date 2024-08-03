package io.study.moduledomain.member.dto.request

data class MemberSignupRequest(
    val nickname: String,
    val email: String,
    val password: String,
)