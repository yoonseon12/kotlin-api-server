package io.study.moduletemp.web.jwt

class TokenInfo(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(accessToken: String, refreshToken: String) = TokenInfo(accessToken, refreshToken)
    }
}