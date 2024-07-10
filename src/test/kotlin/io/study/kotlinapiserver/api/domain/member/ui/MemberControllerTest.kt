package io.study.kotlinapiserver.api.domain.member.ui

import com.fasterxml.jackson.databind.ObjectMapper
import io.study.kotlinapiserver.api.domain.member.application.MemberSignupService
import io.study.kotlinapiserver.api.domain.member.domain.dto.request.MemberSignupRequest
import io.study.kotlinapiserver.api.domain.member.domain.dto.response.MemberSignupResponse
import io.study.kotlinapiserver.api.domain.member.ui.dto.request.PostMemberSignupRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.MessageSource
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(controllers = [MemberController::class])
class MemberControllerTest {

    companion object {
        private const val APPLICATION_JSON: String = "application/json"
        private const val X_API_VERSION: String = "x-api-version"
        private const val VERSION_V1: String = "v1"
        private const val SUCCESS_MESSAGE: String = "success"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var memberSignupService: MemberSignupService

    @Autowired
    private lateinit var messageSource: MessageSource

    private fun serializeToString(request: PostMemberSignupRequest): String = ObjectMapper().writeValueAsString(request)

    @Test
    @DisplayName("잘못된 이메일 형식으로 회원가입했을 때, 올바른 에러 메시지를 반환한다.")
    fun shouldReturnCorrectErrorMessageWhenSignUpWithInvalidEmail() {
        // given
        val invalidEmail = "test@example..com"
        val request = PostMemberSignupRequest("test_user", invalidEmail, "Password123!")

        // when
        val resultActions = mockMvc.perform(
            post("/api/members")
                .contentType(APPLICATION_JSON)
                .content(serializeToString(request))
                .header(X_API_VERSION, VERSION_V1)
        )
        // then
        val errorMessage = MessageSourceAccessor(messageSource)
            .getMessage("valid-messages.invalid-email")

        resultActions
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.message").value(errorMessage))
    }

    @Test
    @DisplayName("잘못된 비밀번호 형식으로 회원가입했을 때, 올바른 에러 메시지를 반환한다.")
    fun shouldReturnCorrectErrorMessageWhenSignUpWithInvalidPassword() {
        // given
        val invalidPassword = "12345678"
        val request = PostMemberSignupRequest("test_user", "test@example.com", invalidPassword)

        // when
        val resultActions = mockMvc.perform(
            post("/api/members")
                .contentType(APPLICATION_JSON)
                .content(serializeToString(request))
                .header(X_API_VERSION, VERSION_V1)
        )

        // then
        val errorMessage = MessageSourceAccessor(messageSource)
            .getMessage("valid-messages.invalid-password")

        resultActions
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
            .andExpect(jsonPath("$.message").value(errorMessage))
    }

    @Test
    @DisplayName("올바른 형식의 데이터로 회원가입했을 때, 성공 응답 메시지를 반환한다.")
    fun shouldReturnCorrectSuccessMessageWhenSignUpWithValidPassword() {
        // given
        val validRequest = PostMemberSignupRequest("test_user", "test@example.com", "Password123!")
        val mockResponse = MemberSignupResponse(validRequest.email)
        given(memberSignupService.signup(MemberSignupRequest.of(validRequest)))
            .willReturn(mockResponse)

        // when
        val resultActions = mockMvc.perform(
            post("/api/members")
                .contentType(APPLICATION_JSON)
                .content(serializeToString(validRequest))
                .header(X_API_VERSION, VERSION_V1)
        )
        // then
        resultActions
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.data.email").value(validRequest.email))
            .andExpect(jsonPath("$.message").value(SUCCESS_MESSAGE));
    }
}