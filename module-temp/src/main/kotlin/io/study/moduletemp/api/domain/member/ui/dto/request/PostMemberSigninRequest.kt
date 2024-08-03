package io.study.moduletemp.api.domain.member.ui.dto.request

import io.study.moduletemp.api.domain.member.domain.dto.request.MemberSigninRequest
import io.study.moduletemp.web.annotation.ValidEmail

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