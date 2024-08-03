package io.study.moduleapi.member.dto.response

import io.study.moduledomain.member.dto.response.MemberSigninResponse

data class PostMemberSigninResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        fun of(info: MemberSigninResponse): PostMemberSigninResponse {
            return PostMemberSigninResponse(
                accessToken = info.accessToken,
                refreshToken = info.refreshToken,)
        }
    }
}