package io.study.kotlinapiserver.api.domain.member.ui.dto.request

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberResetPasswordRequest
import io.study.kotlinapiserver.web.annotation.ValidEmail

data class PostMemberResetPassword(

    @field:ValidEmail
    val email: String,

) {
    fun toDomainDto(): MemberResetPasswordRequest {
        return MemberResetPasswordRequest(
            email = this.email,
        )
    }
}