package io.study.kotlinapiserver.api.domain.member.domain.entity.authority

import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import io.study.kotlinapiserver.web.base.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "members_authority")
class MemberAuthority(

    @Enumerated(EnumType.STRING)
    @Column(name="authority")
    val authority: AuthorityType,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,

    ) : BaseEntity() {

}