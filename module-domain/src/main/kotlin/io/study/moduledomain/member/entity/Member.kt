package io.study.moduledomain.member.entity

import io.study.moduledomain.common.base.BaseEntity
import io.study.moduledomain.member.entity.authority.MemberAuthority
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(

    @Column(name="email", nullable = false)
    var email: String,

    @Embedded
    var password: MemberPassword,

    @Column(name = "nickname", nullable = false)
    val nickname: String,

    @Enumerated(EnumType.STRING)
    @Column(name="member_status", nullable = false)
    val status: MemberStatus,

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
    var authorities: MutableSet<MemberAuthority> = mutableSetOf()

) : BaseEntity() {

    fun addAuthority(memberAuthority: MemberAuthority) {
        authorities.add(memberAuthority)
        memberAuthority.addMember(this)
    }

    fun changePassword(newPassword: String) {
        password = MemberPassword(newPassword)
    }

    companion object {
        fun createBasicMember(email:String, nickname:String, password: String): Member {
            val newMember = Member(
                email = email,
                password = MemberPassword(password),
                nickname = nickname,
                status = MemberStatus.ACTIVE,
            )
            newMember.addAuthority(MemberAuthority.setRoleUser(newMember))
            return newMember
        }
    }
}