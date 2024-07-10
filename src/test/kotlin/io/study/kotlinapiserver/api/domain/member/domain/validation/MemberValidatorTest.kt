package io.study.kotlinapiserver.api.domain.member.domain.validation

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.infrasturcture.MemberQueryRepository
import io.study.kotlinapiserver.web.exception.ApiException
import io.study.kotlinapiserver.web.exception.error.MemberErrorCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MemberValidatorTest {

    @InjectMocks
    lateinit var memberValidator: MemberValidator

    @Mock
    lateinit var memberQueryRepository: MemberQueryRepository

    @Test
    @DisplayName("중복 이메일 검증 테스트")
    fun emailDuplicateValidationTest() {
        // given
        var request = MemberSignupRequest("","이메일","")
        given(memberQueryRepository.existsByEmail(request.email))
            .willReturn(true)

        // when & then
        val exception = assertThrows<ApiException> {
            memberValidator.signinValidate(request)
        }
        assertThat(exception.errorCode)
            .isEqualTo(MemberErrorCode.CONFLICT_DUPLICATE_EMAIL)
    }

    @Test
    @DisplayName("중복 닉네임 검증 테스트")
    fun nicknameDuplicateValidationTest() {
        // given
        var request = MemberSignupRequest("닉네임","","")
        given(memberQueryRepository.existsByNickname(request.nickname))
            .willReturn(true)

        // when & then
        val exception = assertThrows<ApiException> {
            memberValidator.signinValidate(request)
        }
        assertThat(exception.errorCode)
            .isEqualTo(MemberErrorCode.CONFLICT_DUPLICATE_NICKNAME)
    }
}