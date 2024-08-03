package io.study.moduletemp.infra.mail

interface MailService {

    fun sendMail(toEmailArray: Array<out String>, title: String, content: String)

}