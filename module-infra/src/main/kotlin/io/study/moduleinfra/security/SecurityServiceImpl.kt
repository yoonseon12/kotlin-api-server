package io.study.moduleinfra.security

import io.study.moduleapplication.interfaces.SecurityService
import io.study.modulecommon.dto.TokenDto
import io.study.moduleinfra.security.jwt.JwtProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Component

@Component
class SecurityServiceImpl(

    private val jwtProvider: JwtProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder

) : SecurityService {

    override fun authenticate(email: String, password: String): TokenDto {
        val authenticationToken = UsernamePasswordAuthenticationToken(email, password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtProvider.createToken(authentication)
    }

}