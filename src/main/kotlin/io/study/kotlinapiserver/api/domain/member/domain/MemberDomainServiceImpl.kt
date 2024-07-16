package io.study.kotlinapiserver.api.domain.member.domain

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberInfoResponse
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberSignupResponse
import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import io.study.kotlinapiserver.api.domain.member.domain.repository.MemberQueryRepository
import io.study.kotlinapiserver.api.domain.member.domain.repository.MemberRepository
import io.study.kotlinapiserver.api.domain.member.domain.validation.MemberValidator
import io.study.kotlinapiserver.web.exception.ApiException
import io.study.kotlinapiserver.web.exception.error.MemberErrorCode
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class MemberDomainServiceImpl(

    private val memberRepository: MemberRepository,
    private val memberQueryRepository: MemberQueryRepository,
    private val memberValidator: MemberValidator,
    private val passwordEncoder: PasswordEncoder,

) : MemberDomainService {

    override fun register(request: MemberSignupRequest): MemberSignupResponse {
        memberValidator.signinValidate(request)
        val initBasicMember = Member.createBasicMember(request.email, request.nickname, encodePassword(request.password))
        val savedMember = memberRepository.save(initBasicMember)

        return MemberSignupResponse(savedMember.email)
    }

    override fun getInfo(id: Long): MemberInfoResponse {
        val findMember = memberQueryRepository.findByIdWithAuthorities(id)
            ?: throw ApiException(MemberErrorCode.NOT_FOUND_MEMBER)

        return MemberInfoResponse(
            email = findMember.email,
            nickname = findMember.nickname,
            roles = findMember.authorities.map { it.authority }
        )
    }

    private fun encodePassword(password: String): String {
        return passwordEncoder.encode(password)
    }

}