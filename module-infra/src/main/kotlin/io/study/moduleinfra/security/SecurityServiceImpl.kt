package io.study.moduleinfra.security

import io.study.modulecommon.interfaces.SecurityService
import io.study.modulecommon.dto.TokenDto
import io.study.moduleinfra.security.jwt.JwtProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurityServiceImpl(

    private val jwtProvider: JwtProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val passwordEncoder: PasswordEncoder,

    ) : SecurityService {

    override fun authenticate(email: String, password: String): TokenDto {
        val authenticationToken = UsernamePasswordAuthenticationToken(email, password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtProvider.createToken(authentication)
    }

    override fun getCurrentUserEmail(): String {
        return SecurityContextHolder.getContext().authentication.name
    }

    override fun encodePassword(password: String): String {
        return passwordEncoder.encode(password)
    }

}