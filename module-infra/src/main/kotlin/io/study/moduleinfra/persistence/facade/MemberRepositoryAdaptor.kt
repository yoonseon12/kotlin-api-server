package io.study.moduleinfra.persistence.facade

import io.study.moduledomain.member.entity.Member
import io.study.moduledomain.member.repository.MemberRepository
import io.study.moduleinfra.persistence.facade.jpa.MemberJpaRepository
import org.springframework.stereotype.Component

@Component
class MemberRepositoryAdaptor(
    private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {

    override fun save(member: Member) =
        memberJpaRepository.save(member)

}