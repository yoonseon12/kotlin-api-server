package io.study.moduletemp.api.domain.member.ui

import io.study.moduletemp.api.domain.member.application.MemberInfoService
import io.study.moduletemp.api.domain.member.application.MemberResetService
import io.study.moduletemp.api.domain.member.application.MemberSigninService
import io.study.moduletemp.api.domain.member.application.MemberSignupService
import io.study.moduletemp.api.domain.member.domain.dto.response.MemberInfoResponse
import io.study.moduletemp.api.domain.member.ui.dto.request.PostMemberResetPassword
import io.study.moduletemp.api.domain.member.ui.dto.request.PostMemberSigninRequest
import io.study.moduletemp.api.domain.member.ui.dto.request.PostMemberSignupRequest
import io.study.moduletemp.api.domain.member.ui.dto.response.PostMemberSigninResponse
import io.study.moduletemp.api.domain.member.ui.dto.response.PostMemberSignupResponse
import io.study.moduletemp.web.annotation.OnlyOwnerAllowed
import io.study.moduletemp.web.base.BaseController
import io.study.moduletemp.web.base.response.BaseResponse
import io.study.moduletemp.web.base.response.SuccessResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(

    private val memberSignupService: MemberSignupService,
    private val memberSigninService: MemberSigninService,
    private val memberInfoService: MemberInfoService,
    private val memberResetService: MemberResetService,

    ) : BaseController() {

    @PostMapping("/members", headers = [X_API_VERSION])
    fun signup(
        @RequestBody @Validated request: PostMemberSignupRequest,
    ): ResponseEntity<SuccessResponse<PostMemberSignupResponse>> {
        val command = request.toDomainDto()
        val info = memberSignupService.signup(command)
        val response = PostMemberSignupResponse.of(info)

        return ResponseEntity.ok(SuccessResponse.of(response))
    }

    @PostMapping("/members/signin", headers = [X_API_VERSION])
    fun signin(
        @RequestBody @Validated request: PostMemberSigninRequest,
    ): ResponseEntity<SuccessResponse<PostMemberSigninResponse>> {
        val command = request.toDomainDto()
        val info = memberSigninService.signin(command)
        val response = PostMemberSigninResponse.of(info)

        return ResponseEntity.ok(SuccessResponse.of(response))
    }

    @GetMapping("/members/{memberId}", headers = [X_API_VERSION])
    @OnlyOwnerAllowed
    fun getMemberInfo(
        @PathVariable memberId: Long
    ): ResponseEntity<SuccessResponse<MemberInfoResponse>> {
        val response = memberInfoService.getMemberInfo(memberId)

        return ResponseEntity.ok(SuccessResponse.of(response))
    }

    @PostMapping("/members/reset-password", headers = [X_API_VERSION])
    fun resetPassword(
        @RequestBody @Validated request: PostMemberResetPassword,
    ) : ResponseEntity<BaseResponse>{
        val command = request.toDomainDto()
        memberResetService.resetPassword(command)

        return ResponseEntity.ok(BaseResponse())
    }


}