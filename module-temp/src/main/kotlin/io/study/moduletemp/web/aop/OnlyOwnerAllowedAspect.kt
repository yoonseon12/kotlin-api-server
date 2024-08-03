package io.study.moduletemp.web.aop

import io.study.moduletemp.api.domain.member.domain.repository.MemberQueryRepository
import io.study.moduletemp.web.exception.ApiException
import io.study.moduletemp.web.exception.error.AuthErrorCode
import io.study.moduletemp.web.exception.error.MemberErrorCode
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Aspect
@Component
class OnlyOwnerAllowedAspect(

    private val memberQueryRepository: MemberQueryRepository,

) {

    @Before("@annotation(io.study.kotlinapiserver.web.annotation.OnlyOwnerAllowed) && args(memberId)")
    fun beforeFunctionWithOnlyOwnerAllowed(
        memberId: Long
    ) {

        println("AOP beforeFunctionWithOnlyOwnerAllowed called with memberId: $memberId")

        val loginEmail = SecurityContextHolder.getContext().authentication.name

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