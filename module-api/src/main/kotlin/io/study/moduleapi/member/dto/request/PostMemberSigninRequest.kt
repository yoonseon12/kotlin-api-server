package io.study.moduleapi.member.dto.request

import io.study.moduleapi.common.annotation.ValidEmail
import io.study.moduledomain.member.dto.request.MemberSigninRequest

data class PostMemberSigninRequest(

    @field:ValidEmail
    val email: String,

    val password: String,
) {
    fun toDomainDto(): MemberSigninRequest {
        return MemberSigninRequest(
            email = this.email,
            password = this.password
        )
    }
}