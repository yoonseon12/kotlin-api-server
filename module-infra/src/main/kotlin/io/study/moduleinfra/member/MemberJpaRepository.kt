package io.study.moduleinfra.member

import io.study.moduledomain.member.entity.Member
import org.springframework.data.repository.Repository

interface MemberJpaRepository : Repository<Member, Long> {

    fun save(member: Member): Member

}