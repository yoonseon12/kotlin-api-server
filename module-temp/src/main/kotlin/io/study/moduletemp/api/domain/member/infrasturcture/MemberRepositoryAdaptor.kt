package io.study.moduletemp.api.domain.member.infrasturcture

import io.study.moduletemp.api.domain.member.domain.repository.MemberRepository
import io.study.moduletemp.api.domain.member.domain.entity.Member
import org.springframework.stereotype.Component

@Component
class MemberRepositoryAdaptor(
    private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {

    override fun save(member: Member) =
        memberJpaRepository.save(member)

}