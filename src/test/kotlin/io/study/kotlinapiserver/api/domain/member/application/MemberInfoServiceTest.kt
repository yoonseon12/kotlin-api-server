package io.study.kotlinapiserver.api.domain.member.application

import io.study.kotlinapiserver.api.domain.member.domain.MemberDomainService
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberInfoResponse
import io.study.kotlinapiserver.api.domain.member.domain.entity.authority.AuthorityType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class MemberInfoServiceTest {

    @InjectMocks
    private lateinit var memberInfoService: MemberInfoService

    @Mock
    private lateinit var memberDomainService: MemberDomainService

    @Test
    @DisplayName("id가 주어졌을 때, 회원 정보를 반환한다.")
    fun should_return_memberInfo_when_memberId_isProvided() {
        // given
        val memberId = 1L
        val memberInfo = MemberInfoResponse(
            "test@test.com",
            "test",
            listOf(AuthorityType.ROLE_USER)
        )
        given(memberDomainService.getInfo(memberId))
            .willReturn(memberInfo)

        // when
        val response = memberInfoService.getMemberInfo(memberId)

        // then
        assertThat(response.email).isEqualTo(memberInfo.email)
        assertThat(response.nickname).isEqualTo(memberInfo.nickname)
        assertThat(response.roles).isEqualTo(memberInfo.roles)
        then(memberDomainService).should(times(1))
            .getInfo(memberId)
    }
}