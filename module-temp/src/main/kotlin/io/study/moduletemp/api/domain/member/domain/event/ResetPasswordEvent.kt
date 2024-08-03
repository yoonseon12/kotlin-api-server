package io.study.moduletemp.api.domain.member.domain.event

class ResetPasswordEvent(
    val emails: Array<out String>,
    val tempPassword: String,
) {
    companion object {
        fun of(tempPassword:String, vararg emails: String) =
            ResetPasswordEvent(
                tempPassword = tempPassword,
                emails = emails,
            )
    }
}
