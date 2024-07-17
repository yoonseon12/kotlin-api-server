package io.study.kotlinapiserver.api.domain.member.application

import io.study.kotlinapiserver.api.domain.member.domain.MemberDomainService
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberResetPasswordRequest
import io.study.kotlinapiserver.api.domain.member.domain.event.ResetPasswordEvent
import io.study.kotlinapiserver.web.base.event.Events
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.SecureRandom
import java.util.*

@Service
@Transactional(readOnly = true)
class MemberResetService(

    private val memberDomainService: MemberDomainService,

) {
    companion object {
        private const val VALID_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        private const val SPECIAL_CHARACTERS = "!@#$%^&*()\\-_=+"
        private const val MAX_SPECIAL_CHARACTER = 3;
        private const val MIN_LENGTH = 8;
        private const val MAX_LENGTH = 16;
    }

    @Transactional
    fun resetPassword(request: MemberResetPasswordRequest) {
        val tempPassword = createTempPassword()

        memberDomainService.resetPassword(MemberResetPasswordRequest(request.email, tempPassword))

        /** 비밀번호 변경 메일 전송 **/
        publishResetPasswordSuccessEmailEvent(tempPassword, request.email)
    }

    private fun createTempPassword(): String {
        val random = SecureRandom()
        val passwordLength = MIN_LENGTH + random.nextInt(MAX_LENGTH - MIN_LENGTH + 1)
        val password = StringBuilder(passwordLength)

        val specialCharacterLength = addSpecialCharacters(password)
        addValidCharacters(specialCharacterLength, passwordLength, password)

        return shufflePassword(password)
    }

    private fun publishResetPasswordSuccessEmailEvent(
        password: String,
        registeredEmail: String,
    ) {
        Events.raise(ResetPasswordEvent.of(password, registeredEmail))
    }

    private fun shufflePassword(password: StringBuilder): String {
        val map = password.toList()
        Collections.shuffle(map)
        return map.joinToString("")
    }

    private fun addSpecialCharacters(password: StringBuilder): Int {
        val random = SecureRandom()
        val specialCharacterLength = random.nextInt(MAX_SPECIAL_CHARACTER) + 1

        for (i in 0 until specialCharacterLength) {
            password.append(SPECIAL_CHARACTERS[random.nextInt(SPECIAL_CHARACTERS.length)])
        }

        return specialCharacterLength
    }

    private fun addValidCharacters(
        specialCharacterLength: Int,
        passwordLength: Int,
        password: StringBuilder
    ) {
        val random = SecureRandom()
        for (i in specialCharacterLength until passwordLength) {
            val index = random.nextInt(VALID_CHARACTERS.length)
            password.append(VALID_CHARACTERS[index])
        }
    }
}