package io.study.moduleinfra.persistence.facade

import io.study.moduledomain.member.repository.MemberQueryRepository
import io.study.moduleinfra.persistence.facade.jpa.expansion.findByIdOrElseNull
import io.study.moduleinfra.persistence.facade.jpa.MemberQueryJpaRepository
import org.springframework.stereotype.Component

@Component
class MemberQueryRepositoryAdaptor(
    private val memberQueryJpaRepository: MemberQueryJpaRepository,
) : MemberQueryRepository {

    override fun existsByNickname(nickname: String) =
        memberQueryJpaRepository.existsByNickname(nickname)

    override fun existsByEmail(nickname: String) =
        memberQueryJpaRepository.existsByEmail(nickname)

    override fun findByEmail(email: String) =
        memberQueryJpaRepository.findByEmail(email)

    override fun findById(id: Long) =
        memberQueryJpaRepository.findByIdOrElseNull(id)

    override fun findByIdWithAuthorities(id: Long) =
        memberQueryJpaRepository.findByIdWithAuthorities(id)

}