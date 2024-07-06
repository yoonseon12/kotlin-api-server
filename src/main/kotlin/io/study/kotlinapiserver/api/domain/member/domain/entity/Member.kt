package io.study.kotlinapiserver.api.domain.member.domain.entity

import io.study.kotlinapiserver.api.domain.member.domain.entity.authority.MemberAuthority
import io.study.kotlinapiserver.web.base.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(

    @Column(name="email", nullable = false)
    var email: String,

    @Embedded
    val password: MemberPassword,

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