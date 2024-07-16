package io.study.kotlinapiserver.api.domain.member.ui

import io.study.kotlinapiserver.api.domain.member.application.MemberInfoService
import io.study.kotlinapiserver.api.domain.member.application.MemberSigninService
import io.study.kotlinapiserver.api.domain.member.application.MemberSignupService
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSigninRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberInfoResponse
import io.study.kotlinapiserver.api.domain.member.ui.dto.request.PostMemberSigninRequest
import io.study.kotlinapiserver.api.domain.member.ui.dto.request.PostMemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.ui.dto.response.PostMemberSigninResponse
import io.study.kotlinapiserver.api.domain.member.ui.dto.response.PostMemberSignupResponse
import io.study.kotlinapiserver.web.base.BaseController
import io.study.kotlinapiserver.web.base.response.SuccessResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(

    private val memberSignupService: MemberSignupService,
    private val memberSigninService: MemberSigninService,
    private val memberInfoService: MemberInfoService,

    ) : BaseController() {

    @PostMapping("/members", headers = [X_API_VERSION])
    fun signup(
        @RequestBody @Validated request: PostMemberSignupRequest,
    ): ResponseEntity<SuccessResponse<PostMemberSignupResponse>> {
        val command = MemberSignupRequest.of(request)
        val info = memberSignupService.signup(command)
        val response = PostMemberSignupResponse.of(info)

        return ResponseEntity.ok(SuccessResponse.of(response))
    }

    @PostMapping("/members/signin", headers = [X_API_VERSION])
    fun signin(
        @RequestBody @Validated request: PostMemberSigninRequest,
    ): ResponseEntity<SuccessResponse<PostMemberSigninResponse>> {
        val command = MemberSigninRequest.of(request)
        val info = memberSigninService.signin(command)
        val response = PostMemberSigninResponse.of(info)

        return ResponseEntity.ok(SuccessResponse.of(response))
    }

    @GetMapping("/members/{id}", headers = [X_API_VERSION])
    fun getMemberInfo(
        @PathVariable id: Long
    ): ResponseEntity<SuccessResponse<MemberInfoResponse>> {
        val response = memberInfoService.getMemberInfo(id)

        return ResponseEntity.ok(SuccessResponse.of(response))
    }

}