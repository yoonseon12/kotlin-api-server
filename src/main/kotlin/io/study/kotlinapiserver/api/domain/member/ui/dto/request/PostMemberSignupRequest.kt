package io.study.kotlinapiserver.api.domain.member.ui.dto.request

import io.study.kotlinapiserver.web.annotation.ValidEmail
import io.study.kotlinapiserver.web.annotation.ValidPassword
import jakarta.validation.constraints.NotBlank

data class PostMemberSignupRequest(
    @field:NotBlank(message = "닉네임은 공백일 수 없습니다.")
    val nickname: String,

    @field:ValidEmail
    val email: String,

    @field:ValidPassword
    val password: String,
)