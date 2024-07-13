package io.study.kotlinapiserver.api.domain.member.ui.dto.request

data class PostMemberSigninRequest(
    val email: String,
    val password: String,
)