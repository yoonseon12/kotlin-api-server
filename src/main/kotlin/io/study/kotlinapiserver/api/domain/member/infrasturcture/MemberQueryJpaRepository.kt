package io.study.kotlinapiserver.api.domain.member.infrasturcture

import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository

interface MemberQueryJpaRepository : Repository<Member, Long> {

    fun existsByNickname(nickname: String): Boolean

    fun existsByEmail(nickname: String): Boolean

    fun findByEmail(email: String): Member?

    fun findById(id: Long): Member?

    @Query("select m from Member m left join fetch m.authorities where m.id = :id")
    fun findByIdWithAuthorities(id: Long) : Member?

}