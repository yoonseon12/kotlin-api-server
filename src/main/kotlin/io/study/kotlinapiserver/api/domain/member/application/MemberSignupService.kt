package io.study.kotlinapiserver.api.domain.member.application

import io.study.kotlinapiserver.api.domain.member.domain.MemberDomainService
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberSignupResponse
import org.springframework.stereotype.Service

@Service
class MemberSignupService(
    private val memberDomainService: MemberDomainService,
) {

    fun signup(command: MemberSignupRequest): MemberSignupResponse {
        return memberDomainService.register(command)
    }

}