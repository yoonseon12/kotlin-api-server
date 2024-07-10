package io.study.kotlinapiserver.api.domain.member.domain

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberSignupResponse
import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import io.study.kotlinapiserver.api.domain.member.infrasturcture.MemberRepository
import io.study.kotlinapiserver.api.domain.member.domain.validation.MemberValidator
import org.springframework.stereotype.Component

@Component
class MemberDomainServiceImpl(
    private val memberValidator: MemberValidator,
    private val memberRepository: MemberRepository
) : MemberDomainService {

    override fun register(request: MemberSignupRequest): MemberSignupResponse {
        memberValidator.signinValidate(request)
        val initBasicMember = Member.createBasicMember(request.email, request.nickname, request.password)
        val savedMember = memberRepository.save(initBasicMember)

        return MemberSignupResponse(savedMember.email)
    }

}