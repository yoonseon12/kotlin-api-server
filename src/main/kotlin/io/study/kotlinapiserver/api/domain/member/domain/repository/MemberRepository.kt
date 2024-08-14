package io.study.kotlinapiserver.api.domain.member.domain.repository

import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import org.springframework.stereotype.Component

@Component
interface MemberRepository {

    fun save(member: Member): Member

}