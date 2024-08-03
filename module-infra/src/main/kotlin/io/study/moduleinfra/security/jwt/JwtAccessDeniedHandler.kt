package io.study.moduleinfra.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import io.study.moduleapi.common.base.response.ErrorResponse
import io.study.modulecommon.exception.error.AuthErrorCode
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {

    private val objectMapper = ObjectMapper()

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        responseBuilder(response!!, AuthErrorCode.FORBIDDEN)
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