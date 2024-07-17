package io.study.kotlinapiserver.api.domain.member.ui.dto.request

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberResetPasswordRequest

data class PostMemberResetPassword(
    val email: String,
) {
    fun toDomainDto(): MemberResetPasswordRequest {
        return MemberResetPasswordRequest(
            email = this.email,
        )
    }
}