package io.study.kotlinapiserver.infra.mail

interface MailService {

    fun sendMail(toEmailArray: Array<out String>, title: String, content: String)

}