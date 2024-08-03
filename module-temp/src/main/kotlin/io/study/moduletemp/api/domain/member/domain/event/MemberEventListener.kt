package io.study.moduletemp.api.domain.member.domain.event

import io.study.moduletemp.infra.mail.MailService
import io.study.moduletemp.web.base.log.logger
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class MemberEventListener(
    private val mailService: MailService
) {
    private val log = logger()

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    fun signupEventListener(signupEvent: SignupEvent) {
        log.info("MemberEventListener.signupEventListener !! ")
        
        mailService.sendMail(signupEvent.emails, "회원가입 완료 안내", "회원가입이 완료되었습니다.")
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    fun resetPasswordEventListener(resetPasswordEvent: ResetPasswordEvent) {
        log.info("MemberEventListener.resetPasswordEventListener !! ")

        mailService.sendMail(resetPasswordEvent.emails, "임시비밀번호 발급 안내", "임시발급 비밀번호 : ${resetPasswordEvent.tempPassword}")
    }
}