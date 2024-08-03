package io.study.moduledomain.member.principal

import io.study.modulecommon.exception.ApiException
import io.study.modulecommon.exception.error.AuthErrorCode
import io.study.moduledomain.member.repository.MemberQueryRepository
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