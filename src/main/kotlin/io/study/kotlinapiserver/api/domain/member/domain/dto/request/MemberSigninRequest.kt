package io.study.kotlinapiserver.api.domain.member.domain.dto.request

import io.study.kotlinapiserver.api.domain.member.ui.dto.request.PostMemberSigninRequest

data class MemberSigninRequest(
    val email: String,
    val password: String,
) {
    companion object {
        fun of(request: PostMemberSigninRequest): MemberSigninRequest {
            return MemberSigninRequest(
                email = request.email,
                password = request.password,
            )
        }
    }
}