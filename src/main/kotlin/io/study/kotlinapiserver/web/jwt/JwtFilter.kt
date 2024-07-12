package io.study.kotlinapiserver.web.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.SecurityException
import io.study.kotlinapiserver.web.base.log.logger
import io.study.kotlinapiserver.web.exception.error.AuthErrorCode
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(
    private val jwtProvider: JwtProvider
) : OncePerRequestFilter() {

    private val log = logger()

    companion object {
        const val ATTRIBUTE = "token_exception"
        const val TOKEN_PREFIX = "Bearer"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = resolveToken(request)
        validationToken(request, jwt)

        if (StringUtils.hasText(jwt) && jwtProvider.validateAccessToken(jwt)) {
            val authentication = jwtProvider.getAuthentication(jwt)
            log.info("인증정보 저장 {}", authentication);
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun validationToken(request: HttpServletRequest, token: String) {
        try {
            jwtProvider.validateAccessToken(token)
        } catch (e : Exception ) {
            when (e) {
                is SecurityException, is MalformedJwtException -> {
                    log.info("잘못된 JWT 서명입니다.")
                    request.setAttribute(ATTRIBUTE, AuthErrorCode.INVALID_JWT_SIGNATURE)
                }
                is ExpiredJwtException -> {
                    log.info("만료된 JWT 토큰입니다.")
                    request.setAttribute(ATTRIBUTE, AuthErrorCode.EXPIRED_JWT_TOKEN);
                }
                is UnsupportedJwtException -> {
                    log.info("지원되지 않는 JWT 토큰입니다.")
                    request.setAttribute(ATTRIBUTE, AuthErrorCode.UNSUPPORTED_JWT_TOKEN)
                }
                is IllegalArgumentException -> {
                    log.error("JWT 토큰이 잘못되었습니다.")
                    request.setAttribute(ATTRIBUTE, AuthErrorCode.INVALID_JWT_TOKEN)
                }
                else -> {
                    log.error("================================================")
                    log.error("JwtFilter - doFilterInternal() 오류발생")
                    log.error("token : {}", token)
                    log.error("Exception Message : {}", e.message)
                    log.error("Exception StackTrace : {")
                    e.printStackTrace()
                    log.error("}")
                    log.error("================================================")
                    request.setAttribute(ATTRIBUTE, AuthErrorCode.JWT_UNKNOWN_ERROR)
                }
            }
        }
    }

    private fun resolveToken(request: HttpServletRequest): String {
        val bearerToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        bearerToken.let {
            if (isExistBearer(bearerToken)) {
                return bearerToken!!.substring(TOKEN_PREFIX.length).trim()
            }
        }
        return ""
    }

    private fun isExistBearer(bearerToken: String?) =
        StringUtils.hasText(bearerToken) && bearerToken!!.startsWith(TOKEN_PREFIX)
}