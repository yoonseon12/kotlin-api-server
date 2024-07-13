package io.study.kotlinapiserver.api.domain.member.application

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSigninRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberSigninResponse
import io.study.kotlinapiserver.web.jwt.JwtProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberSigninService(
    private val jwtProvider: JwtProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
) {

    fun signin(request: MemberSigninRequest): MemberSigninResponse {
        val authenticationToken = UsernamePasswordAuthenticationToken(request.email, request.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        val token = jwtProvider.createToken(authentication)

        return MemberSigninResponse.of(token)
    }

}