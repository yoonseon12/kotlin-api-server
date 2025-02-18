package io.study.kotlinapiserver.api.domain.member.domain.principal

import io.study.kotlinapiserver.api.domain.member.domain.repository.MemberQueryRepository
import io.study.kotlinapiserver.web.exception.ApiException
import io.study.kotlinapiserver.web.exception.error.AuthErrorCode
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(readOnly = true)
class MemberDetailsService(

    private val memberQueryRepository: MemberQueryRepository

) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val member = memberQueryRepository.findByEmail(username!!)
            ?: throw ApiException(AuthErrorCode.INVALID_ACCOUNT)

        return MemberDetails(member)
    }

}