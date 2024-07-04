package io.study.kotlinapiserver.api.domain.member.ui

import io.study.kotlinapiserver.api.domain.member.application.MemberSignupService
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.ui.dto.request.PostMemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.ui.dto.response.PostMemberSignupResponse
import io.study.kotlinapiserver.web.base.BaseController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberSignupService: MemberSignupService,
) : BaseController() {

    @PostMapping("/members")
    fun signup(
        @RequestBody request: PostMemberSignupRequest,
    ): ResponseEntity<PostMemberSignupResponse> {
        val command = MemberSignupRequest.of(request)
        val info = memberSignupService.signup(command)
        val response = PostMemberSignupResponse.of(info)

        return ResponseEntity.ok(response)
    }

}