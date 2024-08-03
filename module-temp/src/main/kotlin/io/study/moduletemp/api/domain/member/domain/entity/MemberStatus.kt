package io.study.moduletemp.api.domain.member.domain.entity

enum class MemberStatus(
    val status: String,
) {
    ACTIVE("활성화"),
    INACTIVE("비활성화"),
}