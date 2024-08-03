package io.study.moduledomain.member.repository

import io.study.moduledomain.member.entity.Member
import org.springframework.stereotype.Component

@Component
interface MemberRepository {

    fun save(member: Member): Member

}