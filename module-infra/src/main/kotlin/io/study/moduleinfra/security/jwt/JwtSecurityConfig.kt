package io.study.moduleinfra.security.jwt

import org.springframework.security.config.annotation.SecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtSecurityConfig(

    private val jwtProvider: JwtProvider

) : SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    override fun init(builder: HttpSecurity?) {}

    override fun configure(http: HttpSecurity?) {
        http?.addFilterBefore(
            JwtFilter(jwtProvider),
            UsernamePasswordAuthenticationFilter::class.java)
    }

}