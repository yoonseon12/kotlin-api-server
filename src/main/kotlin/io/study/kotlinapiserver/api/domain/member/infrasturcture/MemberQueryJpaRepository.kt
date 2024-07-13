package io.study.kotlinapiserver.api.domain.member.infrasturcture

import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import org.springframework.data.repository.Repository

interface MemberQueryJpaRepository : Repository<Member, Long> {

    fun existsByNickname(nickname: String): Boolean

    fun existsByEmail(nickname: String): Boolean

    fun findByEmail(email: String): Member?

}