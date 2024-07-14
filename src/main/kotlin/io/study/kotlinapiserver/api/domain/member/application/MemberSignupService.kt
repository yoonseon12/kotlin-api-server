package io.study.kotlinapiserver.api.domain.member.application

import io.study.kotlinapiserver.api.domain.member.domain.MemberDomainService
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberSignupResponse
import io.study.kotlinapiserver.api.domain.member.domain.event.SignupEvent
import io.study.kotlinapiserver.web.base.event.Events
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberSignupService(
    private val memberDomainService: MemberDomainService,
) {

    @Transactional
    fun signup(command: MemberSignupRequest): MemberSignupResponse {
        /** 회원가입 **/
        val savedInfo = memberDomainService.register(command)

        /** 이메일 전송 **/
        val registeredEmail = savedInfo.email
        Events.raise(SignupEvent.of(registeredEmail))

        return savedInfo
    }

}