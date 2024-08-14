package io.study.kotlinapiserver.web.aop

import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import io.study.kotlinapiserver.api.domain.member.domain.repository.MemberQueryRepository
import io.study.kotlinapiserver.web.exception.ApiException
import io.study.kotlinapiserver.web.exception.error.AuthErrorCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

@ExtendWith(MockitoExtension::class)
class OnlyOwnerAllowedAspectTest {

    @Mock
    lateinit var memberQueryRepository: MemberQueryRepository

    @InjectMocks
    lateinit var onlyOwnerAllowedAspect: OnlyOwnerAllowedAspect

    @Test
    @DisplayName("요청 받은 memberId의 email과 시큐리티 컨텍스트 안의 email이 다르면 예외를 반환한다.")
    fun shouldReturnForbiddenExceptionWhenRequestingDifferentMemberId() {
        // given
        val myLoginEmail = "test@example.com"
        val othersMemberId = 100L
        val othersMemberEmail = "others@example.com"
        val othersMember = Member.createBasicMember(othersMemberEmail, "test", "")

        SecurityContextHolder.getContext().authentication = mock(Authentication::class.java)
        given(SecurityContextHolder.getContext().authentication.name)
            .willReturn(myLoginEmail)
        given(memberQueryRepository.findById(othersMemberId))
            .willReturn(othersMember)

        // when & then
        val exception = assertThrows<ApiException> {
            onlyOwnerAllowedAspect.beforeFunctionWithOnlyOwnerAllowed(othersMemberId)
        }
        assertThat(exception.errorCode)
            .isEqualTo(AuthErrorCode.FORBIDDEN)
    }

}