package io.study.kotlinapiserver.api.domain.member.ui.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class PostMemberSignupRequest(
    @field:NotBlank(message = "닉네임은 공백일 수 없습니다.")
    val nickname: String,

    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @field:NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    val password: String,
)