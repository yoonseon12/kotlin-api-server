package io.study.moduledomain.member.dto.response

import io.study.moduletemp.web.jwt.TokenInfo

data class MemberSigninResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        fun of(tokenInfo: TokenInfo): MemberSigninResponse {
            return MemberSigninResponse(
                accessToken = tokenInfo.accessToken,
                refreshToken = tokenInfo.refreshToken,
            )
        }
    }
}