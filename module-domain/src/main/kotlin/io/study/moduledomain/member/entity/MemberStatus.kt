package io.study.moduledomain.member.entity

enum class MemberStatus(
    val status: String,
) {
    ACTIVE("활성화"),
    INACTIVE("비활성화"),
}