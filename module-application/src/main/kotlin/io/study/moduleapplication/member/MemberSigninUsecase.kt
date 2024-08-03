package io.study.moduleapplication.member

import io.study.moduleapplication.interfaces.SecurityService
import io.study.moduledomain.member.dto.request.MemberSigninRequest
import io.study.moduledomain.member.dto.response.MemberSigninResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberSigninUsecase(

    private val securityService: SecurityService,

    ) {

    fun signin(request: MemberSigninRequest): MemberSigninResponse {
        val token = securityService.authenticate(request.email, request.password)

        return MemberSigninResponse.of(token)
    }

}