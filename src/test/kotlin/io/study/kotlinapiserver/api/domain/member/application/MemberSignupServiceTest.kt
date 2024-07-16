package io.study.kotlinapiserver.api.domain.member.application

import io.study.kotlinapiserver.api.domain.member.domain.MemberDomainService
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberSignupResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.context.ApplicationEventPublisher

@ExtendWith(MockitoExtension::class)
class MemberSignupServiceTest {

    @InjectMocks
    private lateinit var memberSignupService: MemberSignupService

    @Mock
    private lateinit var memberDomainService: MemberDomainService

    @Mock
    private lateinit var eventPublisher: ApplicationEventPublisher

    @Test
    @DisplayName("올바른 회원가입 정보가 주어졌을때, 회원가입 정보를 반환한다.")
    fun should_return_signupInfo_when_validSignupInfo_isProvided() {
        // given
        val request = MemberSignupRequest("test", "test@test.com","Password!!!")
        val signupInfo = MemberSignupResponse(request.email)

        given(memberDomainService.register(request))
            .willReturn(signupInfo)

        // when
        val response = memberSignupService.signup(request)

        // then
        Assertions.assertThat(request.email).isEqualTo(response.email)
        then(memberDomainService).should(times(1))
            .register(request)
    }
}