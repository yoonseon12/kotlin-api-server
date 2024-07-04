package io.study.kotlinapiserver.api.domain.member.domain.repository

import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import org.springframework.data.repository.Repository

interface MemberRepository : Repository<Member, Long> {
    fun save(member: Member): Member
}