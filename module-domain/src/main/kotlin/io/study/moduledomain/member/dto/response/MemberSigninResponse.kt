package io.study.moduledomain.member.dto.response

import io.study.modulecommon.dto.TokenDto

data class MemberSigninResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        fun of(tokenInfo: TokenDto): MemberSigninResponse {
            return MemberSigninResponse(
                accessToken = tokenInfo.accessToken,
                refreshToken = tokenInfo.refreshToken,
            )
        }
    }
}