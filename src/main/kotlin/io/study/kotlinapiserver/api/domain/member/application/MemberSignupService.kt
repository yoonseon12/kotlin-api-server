package io.study.kotlinapiserver.api.domain.member.application

import io.study.kotlinapiserver.api.domain.member.domain.MemberDomainService
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberSignupResponse
import io.study.kotlinapiserver.infra.mail.MailService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberSignupService(
    private val memberDomainService: MemberDomainService,
    private val mailService: MailService,
) {

    @Transactional
    fun signup(command: MemberSignupRequest): MemberSignupResponse {
        /** 회원가입 **/
        val savedInfo = memberDomainService.register(command)

        /** 이메일 전송 **/
        val registeredEmail = savedInfo.email
        val toEmail = toEmailArray(registeredEmail);
        mailService.sendMail(toEmail, "회원가입 완료 안내", "회원가입이 완료되었습니다.")

        return savedInfo
    }

    private fun toEmailArray(vararg emails: String): Array<out String> {
        return emails
    }

}