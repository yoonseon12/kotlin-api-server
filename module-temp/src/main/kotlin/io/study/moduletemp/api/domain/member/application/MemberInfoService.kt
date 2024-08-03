package io.study.moduletemp.api.domain.member.application

import io.study.moduletemp.api.domain.member.domain.MemberDomainService
import io.study.moduletemp.api.domain.member.domain.dto.response.MemberInfoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberInfoService(

    private val memberDomainService: MemberDomainService,

    ) {

    @Transactional(readOnly = true)
    fun getMemberInfo(id: Long): MemberInfoResponse {
        return memberDomainService.getInfo(id)
    }

}