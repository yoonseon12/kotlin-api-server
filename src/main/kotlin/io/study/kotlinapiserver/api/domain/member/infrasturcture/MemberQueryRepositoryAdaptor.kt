package io.study.kotlinapiserver.api.domain.member.infrasturcture

import io.study.kotlinapiserver.api.domain.member.domain.repository.MemberQueryRepository
import org.springframework.stereotype.Component

@Component
class MemberQueryRepositoryAdaptor(
    private val memberQueryJpaRepository: MemberQueryJpaRepository,
) : MemberQueryRepository {

    override fun existsByNickname(nickname: String) =
        memberQueryJpaRepository.existsByNickname(nickname)


    override fun existsByEmail(nickname: String) =
        memberQueryJpaRepository.existsByEmail(nickname)

}