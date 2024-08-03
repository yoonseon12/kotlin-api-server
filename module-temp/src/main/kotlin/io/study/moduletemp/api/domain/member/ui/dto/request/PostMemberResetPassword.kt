package io.study.moduletemp.api.domain.member.ui.dto.request

import io.study.moduletemp.api.domain.member.domain.dto.request.MemberResetPasswordRequest
import io.study.moduletemp.web.annotation.ValidEmail

data class PostMemberResetPassword(

    @field:ValidEmail
    val email: String,

) {
    fun toDomainDto(): io.study.moduletemp.api.domain.member.domain.dto.request.MemberResetPasswordRequest {
        return io.study.moduletemp.api.domain.member.domain.dto.request.MemberResetPasswordRequest(
            email = this.email,
        )
    }
}