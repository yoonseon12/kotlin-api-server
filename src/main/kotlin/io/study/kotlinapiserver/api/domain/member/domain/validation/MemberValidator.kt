package io.study.kotlinapiserver.api.domain.member.domain.validation

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.repository.MemberQueryRepository
import org.springframework.stereotype.Component

@Component
class MemberValidator(
    private val memberQueryRepository: MemberQueryRepository
) {

    fun signinValidate(request: MemberSignupRequest) {
        validateDuplicateNickname(request.nickname)
        validationDuplicateEmail(request.email)
    }

    private fun validateDuplicateNickname(nickname: String) {
        if (memberQueryRepository.existsByNickname(nickname)) {
            throw IllegalArgumentException("등록된 닉네임 입니다.")
        }
    }

    private fun validationDuplicateEmail(email: String) {
        if (memberQueryRepository.existsByEmail(email)) {
            throw IllegalArgumentException("등록된 이메일 입니다.")
        }
    }

}