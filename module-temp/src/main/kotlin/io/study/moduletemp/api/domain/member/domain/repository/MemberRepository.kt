package io.study.moduletemp.api.domain.member.domain.repository

import io.study.moduletemp.api.domain.member.domain.entity.Member
import org.springframework.stereotype.Component

@Component
interface MemberRepository {

    fun save(member: Member): Member

}