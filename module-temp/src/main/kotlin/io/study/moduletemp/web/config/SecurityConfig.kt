package io.study.moduletemp.web.config

import io.study.moduletemp.web.jwt.JwtAccessDeniedHandler
import io.study.moduletemp.web.jwt.JwtAuthenticationEntryPoint
import io.study.moduletemp.web.jwt.JwtProvider
import io.study.moduletemp.web.jwt.JwtSecurityConfig
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler,
    private val jwtProvider: JwtProvider,
) {
    companion object {
        private val PERMIT_ALL_POSTS = arrayOf(
            "/api/members",
            "/api/members/signin",
            "/api/members/reset-password"
        )
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.csrf(CsrfConfigurer<HttpSecurity>::disable)
            .formLogin(FormLoginConfigurer<HttpSecurity>::disable)
            .httpBasic(HttpBasicConfigurer<HttpSecurity>::disable)


        http.sessionManagement { sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        http.authorizeHttpRequests { authorize ->
            authorize
                .requestMatchers(HttpMethod.POST, *PERMIT_ALL_POSTS).permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
        }

        http.headers { header ->
            header.frameOptions { frameOptions -> frameOptions.sameOrigin() }
        }

        http.exceptionHandling {exception ->
            exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
        }

        http.apply(JwtSecurityConfig(jwtProvider))

        return http.build()
    }
}