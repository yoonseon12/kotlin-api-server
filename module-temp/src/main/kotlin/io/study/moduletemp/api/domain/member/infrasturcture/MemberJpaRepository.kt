package io.study.moduletemp.api.domain.member.infrasturcture

import io.study.moduletemp.api.domain.member.domain.entity.Member
import org.springframework.data.repository.Repository

interface MemberJpaRepository : Repository<Member, Long> {

    fun save(member: Member): Member

}