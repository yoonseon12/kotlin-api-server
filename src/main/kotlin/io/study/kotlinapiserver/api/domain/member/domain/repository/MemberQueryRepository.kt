package io.study.kotlinapiserver.api.domain.member.domain.repository

interface MemberQueryRepository {

    fun existsByNickname(nickname: String): Boolean

    fun existsByEmail(nickname: String): Boolean

}