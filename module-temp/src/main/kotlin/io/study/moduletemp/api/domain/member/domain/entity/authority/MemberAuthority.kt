package io.study.moduletemp.api.domain.member.domain.entity.authority

import io.study.moduletemp.api.domain.member.domain.entity.Member
import io.study.moduletemp.web.base.entity.BaseEntity
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