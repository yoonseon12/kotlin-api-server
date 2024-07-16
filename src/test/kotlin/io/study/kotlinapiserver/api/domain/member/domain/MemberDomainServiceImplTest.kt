package io.study.kotlinapiserver.api.domain.member.domain

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import io.study.kotlinapiserver.api.domain.member.domain.entity.authority.AuthorityType
import io.study.kotlinapiserver.api.domain.member.domain.repository.MemberQueryRepository
import io.study.kotlinapiserver.api.domain.member.domain.repository.MemberRepository
import io.study.kotlinapiserver.api.domain.member.domain.validation.MemberValidator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.password.PasswordEncoder

@ExtendWith(MockitoExtension::class)
class MemberDomainServiceImplTest {

    @InjectMocks
    private lateinit var memberDomainServiceImpl: MemberDomainServiceImpl
    @Mock
    private lateinit var memberRepository: MemberRepository
    @Mock
    private lateinit var memberQueryRepository: MemberQueryRepository
    @Mock
    private lateinit var memberValidator: MemberValidator
    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @Test
    @DisplayName("회원 정보가 주어졌을 때, 회원등록 후 등록된 회원정보를 반환한다.")
    fun should_return_savedMemberInfo_when_memberInfo_isProvided() {
        // given
        val request = MemberSignupRequest("test", "test@test.com","Password!!!")
        val encodePassword = "ABCDE"
        val member = Member.createBasicMember(request.email, request.nickname, request.password)

        given(passwordEncoder.encode(request.password)).willReturn(encodePassword)
        given(memberRepository.save(org.mockito.kotlin.any())).willReturn(member)

        // when
        val response = memberDomainServiceImpl.register(request)

        // then
        assertThat(response.email).isEqualTo(request.email)
        then(memberValidator).should(times(1))
            .signinValidate(request)
        then(memberRepository).should(times(1))
            .save(org.mockito.kotlin.any())
        then(passwordEncoder).should(times(1))
            .encode(member.password.value)
    }

    @Test
    @DisplayName("회원 id가 주어졌을 때, 회원 정보를 반환한다.")
    fun should_return_memberInfo_when_memberId_isNotProvided() {
        // given
        val memberId = 1L
        val member = Member.createBasicMember(
            email = "test@test.com",
            nickname = "test",
            password = "password"
        )

        given(memberQueryRepository.findByIdWithAuthorities(memberId))
            .willReturn(member)

        // when
        val response = memberDomainServiceImpl.getInfo(memberId)

        // then
        assertThat(response.email).isEqualTo(response.email)
        assertThat(response.nickname).isEqualTo(response.nickname)
        assertThat(response.roles).hasSize(1)
        assertThat(response.roles[0]).isEqualTo(AuthorityType.ROLE_USER)
        then(memberQueryRepository).should(times(1))
            .findByIdWithAuthorities(memberId)
    }
}