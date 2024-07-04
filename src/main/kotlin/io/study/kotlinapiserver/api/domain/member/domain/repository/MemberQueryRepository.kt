package io.study.kotlinapiserver.api.domain.member.domain.repository

import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import org.springframework.data.repository.Repository

interface MemberQueryRepository : Repository<Member, Long> {

    fun existsByNickname(nickname: String): Boolean

    fun existsByEmail(nickname: String): Boolean

}