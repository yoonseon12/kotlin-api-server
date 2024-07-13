package io.study.kotlinapiserver.api.domain.member.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class MemberPassword(

    @Column(name="password", nullable = false)
    val value: String,

    ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}