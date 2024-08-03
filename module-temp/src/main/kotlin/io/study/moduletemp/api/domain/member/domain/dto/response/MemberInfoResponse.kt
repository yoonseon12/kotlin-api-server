package io.study.moduletemp.api.domain.member.domain.dto.response

import io.study.moduletemp.api.domain.member.domain.entity.authority.AuthorityType

data class MemberInfoResponse(
    val email: String,
    val nickname: String,
    val roles: List<AuthorityType>
)