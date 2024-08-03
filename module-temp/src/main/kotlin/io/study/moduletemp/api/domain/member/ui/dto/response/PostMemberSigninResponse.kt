package io.study.moduletemp.api.domain.member.ui.dto.response

import io.study.moduletemp.api.domain.member.domain.dto.response.MemberSigninResponse

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