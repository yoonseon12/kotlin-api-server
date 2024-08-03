package io.study.moduleapplication.member

import io.study.moduledomain.member.dto.request.MemberSigninRequest
import io.study.moduledomain.member.dto.response.MemberSigninResponse
import io.study.moduletemp.web.jwt.JwtProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberSigninUsecase(

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