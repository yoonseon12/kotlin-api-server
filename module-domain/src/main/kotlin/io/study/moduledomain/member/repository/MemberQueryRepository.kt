package io.study.moduledomain.member.repository

import io.study.moduledomain.member.entity.Member
import org.springframework.stereotype.Component

@Component
interface MemberQueryRepository {

    fun existsByNickname(nickname: String): Boolean

    fun existsByEmail(nickname: String): Boolean

    fun findByEmail(email: String): Member?

    fun findById(id: Long): Member?

    fun findByIdWithAuthorities(id: Long): Member?

}