package io.study.moduleapi.member.dto.request

import io.study.moduleapi.common.annotation.ValidEmail
import io.study.moduleapi.common.annotation.ValidPassword
import io.study.moduledomain.member.dto.request.MemberSignupRequest
import jakarta.validation.constraints.NotBlank

data class PostMemberSignupRequest(

    @field:NotBlank(message = "닉네임은 공백일 수 없습니다.")
    val nickname: String,

    @field:ValidEmail
    val email: String,

    @field:ValidPassword
    val password: String,

) {

    fun toDomainDto(): MemberSignupRequest {
        return MemberSignupRequest(
            nickname = this.nickname,
            email = this.email,
            password = this.password,
        )
    }

}