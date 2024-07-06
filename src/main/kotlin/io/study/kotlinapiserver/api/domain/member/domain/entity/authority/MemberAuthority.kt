package io.study.kotlinapiserver.api.domain.member.domain.entity.authority

import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import jakarta.persistence.*

@Entity
@Table(name = "members_authority")
class MemberAuthority(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    val id: Long,

    @Enumerated(EnumType.STRING)
    @Column(name="authority")
    val authority: AuthorityType,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,

    ) {

}