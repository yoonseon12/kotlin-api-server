package io.study.kotlinapiserver.api.domain.member.domain.dto.request

import io.study.kotlinapiserver.api.domain.member.ui.dto.request.PostMemberSignupRequest

data class MemberSignupRequest(
    val nickname: String,
    val email: String,
    val password: String,
) {
    companion object {
        fun of(request: PostMemberSignupRequest): MemberSignupRequest {
            return MemberSignupRequest(
                nickname = request.nickname,
                email = request.email,
                password = request.password,
            )
        }
    }
}