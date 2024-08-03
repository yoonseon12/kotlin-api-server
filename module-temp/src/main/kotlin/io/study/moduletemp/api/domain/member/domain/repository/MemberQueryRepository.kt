package io.study.moduletemp.api.domain.member.domain.repository

import io.study.moduletemp.api.domain.member.domain.entity.Member

interface MemberQueryRepository {

    fun existsByNickname(nickname: String): Boolean

    fun existsByEmail(nickname: String): Boolean

    fun findByEmail(email: String): Member?

    fun findById(id: Long): Member?

    fun findByIdWithAuthorities(id: Long): Member?

}