package io.study.kotlinapiserver.api.domain.member.application

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSigninRequest
import io.study.kotlinapiserver.web.jwt.JwtProvider
import io.study.kotlinapiserver.web.jwt.TokenInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication

@ExtendWith(MockitoExtension::class)
class MemberSigninServiceTest {

    @InjectMocks
    private lateinit var memberSigninService: MemberSigninService

    @Mock
    private lateinit var jwtProvider: JwtProvider

    @Mock
    private lateinit var authenticationManager: AuthenticationManager

    @Mock
    private lateinit var authenticationManagerBuilder: AuthenticationManagerBuilder

    @Test
    @DisplayName("올바른 로그인 정보가 주어졌을때, 토큰 정보를 반환한다.")
    fun should_return_tokenInfo_when_validLoginInfo_isProvided() {
        // given
        val loginInfo = MemberSigninRequest("test@test.com", "test123!",)
        val token = TokenInfo("accessToken", "refreshToken")

        val authentication = mock(Authentication::class.java)
        given(authenticationManagerBuilder.`object`).willReturn(authenticationManager)
        given(authenticationManager.authenticate(any())).willReturn(authentication)
        given(jwtProvider.createToken(authentication)).willReturn(token)

        // when
        val response = memberSigninService.signin(loginInfo)

        // then
        assertThat(response.accessToken).isEqualTo(token.accessToken)
        assertThat(response.refreshToken).isEqualTo(token.refreshToken)
        then(jwtProvider).should(times(1))
            .createToken(authentication)
    }

}