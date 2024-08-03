package io.study.moduleapplication.member

import io.study.moduledomain.member.dto.response.MemberInfoResponse
import io.study.moduledomain.member.service.MemberDomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberInfoUsecase(

    private val memberDomainService: MemberDomainService,

) {

    fun getMemberInfo(id: Long): MemberInfoResponse {
        return memberDomainService.getInfo(id)
    }

}