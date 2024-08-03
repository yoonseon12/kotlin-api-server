package io.study.moduledomain.member.event

class SignupEvent(
    val emails: Array<out String>
) {
    companion object {
        fun of(vararg emails: String) = SignupEvent(emails)
    }
}
