package io.study.moduleinfra.member

import io.study.moduledomain.member.entity.Member
import io.study.moduledomain.member.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class MemberRepositoryAdaptor(
    private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {

    override fun save(member: Member) =
        memberJpaRepository.save(member)

}