package io.study.moduleapi.member.dto.response

import io.study.moduledomain.member.dto.response.MemberSignupResponse

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