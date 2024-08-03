package io.study.moduledomain.member.entity.authority

import io.study.moduledomain.common.base.BaseEntity
import io.study.moduledomain.member.entity.Member
import jakarta.persistence.*

@Entity
@Table(name = "members_authority")
class MemberAuthority(

    @Enumerated(EnumType.STRING)
    @Column(name="authority")
    val authority: AuthorityType,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    ) : BaseEntity() {

    fun addMember(newMember: Member) {
        this.member = newMember
    }

    companion object {
        fun setRoleUser(member: Member): MemberAuthority {
            return MemberAuthority(AuthorityType.ROLE_USER, member)
        }
    }
}