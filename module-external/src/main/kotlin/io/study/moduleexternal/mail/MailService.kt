package io.study.moduleexternal.mail

interface MailService {

    fun sendMail(toEmailArray: Array<out String>, title: String, content: String)

}