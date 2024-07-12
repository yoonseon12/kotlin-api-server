package io.study.kotlinapiserver.api.domain.member.infrasturcture

import io.study.kotlinapiserver.api.domain.member.domain.repository.MemberRepository
import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import org.springframework.stereotype.Component

@Component
class MemberRepositoryAdaptor(
    private val memberJpaRepository: MemberJpaRepository
) : MemberRepository {

    override fun save(member: Member) =
        memberJpaRepository.save(member)

}