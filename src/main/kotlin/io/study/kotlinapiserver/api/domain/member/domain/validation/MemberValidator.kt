package io.study.kotlinapiserver.api.domain.member.domain.validation

import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.infrasturcture.MemberQueryRepository
import io.study.kotlinapiserver.web.exception.ApiException
import io.study.kotlinapiserver.web.exception.error.MemberErrorCode
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
            throw ApiException(MemberErrorCode.CONFLICT_DUPLICATE_NICKNAME)
        }
    }

    private fun validationDuplicateEmail(email: String) {
        if (memberQueryRepository.existsByEmail(email)) {
            throw ApiException(MemberErrorCode.CONFLICT_DUPLICATE_EMAIL)
        }
    }

}