package io.study.kotlinapiserver.infra.mail.infrastucture

import io.study.kotlinapiserver.infra.mail.MailService
import jakarta.mail.internet.InternetAddress
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

@Component
class JavaMailService(

    private val mailSender: JavaMailSender,

) : MailService {

    companion object {
        const val UTF_8 = "UTF-8"
        const val FROM_EMAIL = "noreply.yoonseon3@gmail.com"
        const val FROM_NAME = "이윤선"
    }

    override fun sendMail(toEmailArray: Array<out String>, title: String, content: String) {
        val message = mailSender.createMimeMessage()

        val messageHelper = MimeMessageHelper(message, true, UTF_8)
        messageHelper.setTo(toEmailArray.map { InternetAddress(it) }.toTypedArray())
        messageHelper.setFrom(InternetAddress(FROM_EMAIL, FROM_NAME, UTF_8));
        messageHelper.setSubject(title)
        messageHelper.setText(content, true)

        mailSender.send(message)
    }

}