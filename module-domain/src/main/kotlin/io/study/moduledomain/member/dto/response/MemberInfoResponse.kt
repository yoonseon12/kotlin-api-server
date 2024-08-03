package io.study.moduledomain.member.dto.response

import io.study.moduledomain.member.entity.authority.AuthorityType

data class MemberInfoResponse(
    val email: String,
    val nickname: String,
    val roles: List<AuthorityType>
)