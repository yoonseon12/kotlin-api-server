package io.study.moduleinfra.persistence.facade.jpa

import io.study.moduledomain.member.entity.Member
import org.springframework.data.repository.Repository

interface MemberJpaRepository : Repository<Member, Long> {

    fun save(member: Member): Member

}