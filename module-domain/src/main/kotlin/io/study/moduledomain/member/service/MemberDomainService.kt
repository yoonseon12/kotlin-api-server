package io.study.moduledomain.member.service

import io.study.moduledomain.member.dto.request.MemberResetPasswordRequest
import io.study.moduledomain.member.dto.request.MemberSignupRequest
import io.study.moduledomain.member.dto.response.MemberInfoResponse
import io.study.moduledomain.member.dto.response.MemberSignupResponse
import org.springframework.stereotype.Component

@Component
interface MemberDomainService {

    fun register(request: MemberSignupRequest): MemberSignupResponse

    fun getInfo(id: Long): MemberInfoResponse

    fun resetPassword(request: MemberResetPasswordRequest)

}