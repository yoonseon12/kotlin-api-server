package io.study.moduleinfra.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import io.study.moduleapi.common.base.response.ErrorResponse
import io.study.modulecommon.exception.error.AuthErrorCode
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    companion object {
        private const val ATTRIBUTE = "token_exception"
    }

    private val objectMapper = ObjectMapper()

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {

        val attribute = request!!.getAttribute(ATTRIBUTE) as? AuthErrorCode

        when (attribute) {
            AuthErrorCode.INVALID_JWT_SIGNATURE,
            AuthErrorCode.EXPIRED_JWT_TOKEN,
            AuthErrorCode.UNSUPPORTED_JWT_TOKEN,
            AuthErrorCode.INVALID_JWT_TOKEN,
            AuthErrorCode.JWT_UNKNOWN_ERROR -> response?.let { responseBuilder(it, attribute!!) }
            else -> response?.let { responseBuilder(it, AuthErrorCode.FORBIDDEN) }
        }
    }

    private fun responseBuilder(response: HttpServletResponse, authErrorCode: AuthErrorCode) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = Charsets.UTF_8.name()
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.writer.print(
            objectMapper.writeValueAsString(
                ErrorResponse.of(authErrorCode)
            )
        )
    }
}