package io.study.moduletemp.api.domain.member.ui.dto.response

import io.study.moduletemp.api.domain.member.domain.dto.response.MemberSignupResponse

data class PostMemberSignupResponse(
    val email: String,
) {
    companion object {
        fun of(response: MemberSignupResponse): PostMemberSignupResponse {
            return PostMemberSignupResponse(
                email = response.email
            )
        }
    }
}