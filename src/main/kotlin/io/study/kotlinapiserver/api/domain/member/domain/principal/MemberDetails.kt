package io.study.kotlinapiserver.api.domain.member.domain.principal

import io.study.kotlinapiserver.api.domain.member.domain.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MemberDetails(

    private val member: Member

) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return member.authorities
            .map { SimpleGrantedAuthority(it.authority.toString()) }
            .toMutableList()
    }

    override fun getPassword(): String {
        return member.password.value
    }

    override fun getUsername(): String {
        return member.email
    }

}