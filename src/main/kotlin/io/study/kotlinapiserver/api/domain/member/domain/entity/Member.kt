package io.study.kotlinapiserver.api.domain.member.domain.entity

import io.study.kotlinapiserver.api.domain.member.domain.entity.authority.MemberAuthority
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var email: String,

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
    companion object {
        fun createBasicMember(email:String, nickname:String, password: String): Member {
            return Member(
                email = email,
                password = MemberPassword(password),
                nickname = nickname,
                status = MemberStatus.ACTIVE,
            )
        }
    }
}