package io.study.moduleapi.member

import io.study.moduleapi.common.base.BaseController
import io.study.moduleapi.common.base.response.BaseResponse
import io.study.moduleapi.common.base.response.SuccessResponse
import io.study.moduleapi.member.dto.request.PostMemberResetPassword
import io.study.moduleapi.member.dto.request.PostMemberSigninRequest
import io.study.moduleapi.member.dto.request.PostMemberSignupRequest
import io.study.moduleapi.member.dto.response.PostMemberSigninResponse
import io.study.moduleapi.member.dto.response.PostMemberSignupResponse
import io.study.moduleapplication.member.MemberInfoUsecase
import io.study.moduleapplication.member.MemberResetUsecase
import io.study.moduleapplication.member.MemberSigninUsecase
import io.study.moduleapplication.member.MemberSignupUsecase
import io.study.modulecommon.annotation.OnlyOwnerAllowed
import io.study.moduledomain.member.dto.response.MemberInfoResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(

    private val memberSignupUsecase: MemberSignupUsecase,
    private val memberSigninUsecase: MemberSigninUsecase,
    private val memberInfoUsecase: MemberInfoUsecase,
    private val memberResetUsecase: MemberResetUsecase,

) : BaseController() {

    @PostMapping("/members", headers = [X_API_VERSION])
    fun signup(
        @RequestBody @Validated request: PostMemberSignupRequest,
    ): ResponseEntity<SuccessResponse<PostMemberSignupResponse>> {
        val command = request.toDomainDto()
        val info = memberSignupUsecase.signup(command)
        val response = PostMemberSignupResponse.of(info)

        return ResponseEntity.ok(SuccessResponse.of(response))
    }

    @PostMapping("/members/signin", headers = [X_API_VERSION])
    fun signin(
        @RequestBody @Validated request: PostMemberSigninRequest,
    ): ResponseEntity<SuccessResponse<PostMemberSigninResponse>> {
        val command = request.toDomainDto()
        val info = memberSigninUsecase.signin(command)
        val response = PostMemberSigninResponse.of(info)

        return ResponseEntity.ok(SuccessResponse.of(response))
    }

    @GetMapping("/members/{memberId}", headers = [X_API_VERSION])
    @OnlyOwnerAllowed
    fun getMemberInfo(
        @PathVariable memberId: Long
    ): ResponseEntity<SuccessResponse<MemberInfoResponse>> {
        val response = memberInfoUsecase.getMemberInfo(memberId)

        return ResponseEntity.ok(SuccessResponse.of(response))
    }

    @PostMapping("/members/reset-password", headers = [X_API_VERSION])
    fun resetPassword(
        @RequestBody @Validated request: PostMemberResetPassword,
    ) : ResponseEntity<BaseResponse>{
        val command = request.toDomainDto()
        memberResetUsecase.resetPassword(command)

        return ResponseEntity.ok(BaseResponse())
    }

}