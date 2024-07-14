package io.study.kotlinapiserver.api.domain.member.domain.event

class SignupEvent(
    val emails: Array<out String>
) {
    companion object {
        fun of(vararg emails: String) = SignupEvent(emails)
    }
}
