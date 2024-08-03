package io.study.moduledomain.member.validation

import io.study.modulecommon.exception.ApiException
import io.study.modulecommon.exception.error.MemberErrorCode
import io.study.moduledomain.member.dto.request.MemberSignupRequest
import io.study.moduledomain.member.repository.MemberQueryRepository
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