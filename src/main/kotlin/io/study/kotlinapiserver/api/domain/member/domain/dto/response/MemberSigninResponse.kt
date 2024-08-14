package io.study.kotlinapiserver.api.domain.member.domain.dto.response

import io.study.kotlinapiserver.web.jwt.TokenInfo

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