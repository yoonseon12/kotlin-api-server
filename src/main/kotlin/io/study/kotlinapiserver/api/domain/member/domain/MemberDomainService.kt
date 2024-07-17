package io.study.kotlinapiserver.api.domain.member.domain

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberResetPasswordRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberInfoResponse
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberSignupResponse

interface MemberDomainService {

    fun register(request: MemberSignupRequest): MemberSignupResponse

    fun getInfo(id: Long): MemberInfoResponse

    fun resetPassword(request: MemberResetPasswordRequest)

}