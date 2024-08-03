package io.study.moduletemp.api.domain.member.ui.dto.request

import io.study.moduletemp.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.moduletemp.web.annotation.ValidEmail
import io.study.moduletemp.web.annotation.ValidPassword
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