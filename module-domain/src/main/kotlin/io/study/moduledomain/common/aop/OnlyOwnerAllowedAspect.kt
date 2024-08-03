package io.study.moduledomain.common.aop

import io.study.modulecommon.interfaces.SecurityService
import io.study.modulecommon.exception.ApiException
import io.study.modulecommon.exception.error.AuthErrorCode
import io.study.modulecommon.exception.error.MemberErrorCode
import io.study.moduledomain.member.repository.MemberQueryRepository
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class OnlyOwnerAllowedAspect(
    private val memberQueryRepository: MemberQueryRepository,
    private val securityService: SecurityService,

    ) {
    @Before("@annotation(io.study.modulecommon.annotation.OnlyOwnerAllowed) && args(memberId)")
    fun beforeFunctionWithOnlyOwnerAllowed(
        memberId: Long
    ) {

        println("AOP beforeFunctionWithOnlyOwnerAllowed called with memberId: $memberId")

        val loginEmail = securityService.getCurrentUserEmail()

        val findMember = memberQueryRepository.findById(memberId)
            ?: throw ApiException(MemberErrorCode.NOT_FOUND_MEMBER)

        validateSelfEmail(loginEmail, findMember.email)
    }

    private fun validateSelfEmail(
        loginEmail: String,
        findMemberEmail: String,
    ) {
        if (loginEmail != findMemberEmail) {
            throw ApiException(AuthErrorCode.FORBIDDEN)
        }
    }

}