package io.study.kotlinapiserver.api.domain.member.application

import io.study.kotlinapiserver.api.domain.member.domain.MemberDomainService
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberResetPasswordRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MemberResetServiceTest{

    @InjectMocks
    private lateinit var memberResetService: MemberResetService

    @Mock
    private lateinit var memberDomainService: MemberDomainService

    @Test
    @DisplayName("이메일이 주어졌을 때, 비밀번호 변경 함수가 1회 실행한다.")
    fun should_execute_function_when_email_isProvided() {
        // given
        val request = MemberResetPasswordRequest("test@test.com")
        willDoNothing().given(memberDomainService).resetPassword(org.mockito.kotlin.any())

        // when
        memberResetService.resetPassword(request)

        // then
        then(memberDomainService).should(times(1))
            .resetPassword(org.mockito.kotlin.any())
    }
}