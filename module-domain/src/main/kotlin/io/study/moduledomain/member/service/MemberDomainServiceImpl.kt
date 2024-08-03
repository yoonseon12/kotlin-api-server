package io.study.moduledomain.member.service

import io.study.modulecommon.exception.ApiException
import io.study.modulecommon.exception.error.MemberErrorCode
import io.study.modulecommon.interfaces.SecurityService
import io.study.moduledomain.member.dto.request.MemberResetPasswordRequest
import io.study.moduledomain.member.dto.request.MemberSignupRequest
import io.study.moduledomain.member.dto.response.MemberInfoResponse
import io.study.moduledomain.member.dto.response.MemberSignupResponse
import io.study.moduledomain.member.entity.Member
import io.study.moduledomain.member.repository.MemberQueryRepository
import io.study.moduledomain.member.repository.MemberRepository
import io.study.moduledomain.member.service.validation.MemberValidator
import org.springframework.stereotype.Component

@Component
class MemberDomainServiceImpl(

    private val memberQueryRepository: MemberQueryRepository,
    private val memberRepository: MemberRepository,
    private val memberValidator: MemberValidator,
    private val securityService: SecurityService,

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

    override fun resetPassword(request: MemberResetPasswordRequest) {
        val findMember = memberQueryRepository.findByEmail(request.email)
            ?: throw ApiException(MemberErrorCode.NOT_FOUND_MEMBER)

        findMember.changePassword(encodePassword(request.tempPassword!!))
    }

    private fun encodePassword(password: String): String {
        return securityService.encodePassword(password)
    }

}