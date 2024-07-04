package io.study.kotlinapiserver.api.domain.member.domain.entity

import io.study.kotlinapiserver.api.domain.member.domain.entity.authority.MemberAuthority
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Embedded
    val password: MemberPassword,

    @Column(name = "nickname", nullable = false)
    val nickname: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: MemberStatus,

    @OneToMany(mappedBy = "member")
    val authorities: MutableSet<MemberAuthority> = mutableSetOf()
) {

}