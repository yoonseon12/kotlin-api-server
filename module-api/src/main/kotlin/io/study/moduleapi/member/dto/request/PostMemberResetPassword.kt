package io.study.moduleapi.member.dto.request

import io.study.moduleapi.common.annotation.ValidEmail
import io.study.moduledomain.member.dto.request.MemberResetPasswordRequest

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