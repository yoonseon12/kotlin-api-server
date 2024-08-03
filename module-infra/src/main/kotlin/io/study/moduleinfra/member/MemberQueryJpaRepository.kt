package io.study.moduleinfra.member

import io.study.moduledomain.member.entity.Member
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MemberQueryJpaRepository : CrudRepository<Member, Long> {

    fun existsByNickname(nickname: String): Boolean

    fun existsByEmail(nickname: String): Boolean

    fun findByEmail(email: String): Member?

    @Query("select m from Member m left join fetch m.authorities where m.id = :id")
    fun findByIdWithAuthorities(id: Long) : Member?

}