package io.study.kotlinapiserver.api.domain.member.ui.dto.request

data class PostMemberSignupRequest(
    val nickname: String,
    val email: String,
    val password: String,
)