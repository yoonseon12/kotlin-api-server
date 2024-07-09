package io.study.kotlinapiserver.web.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import kotlin.reflect.KClass

@Constraint(validatedBy = [])
@Target(allowedTargets = [AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER])
@Retention(AnnotationRetention.RUNTIME)
@Pattern(
    regexp = "^(?=.*[!@#$%^&*()\\-_=+])[a-zA-Z0-9!@#$%^&*()\\-_=+]{8,16}$",
    message = "비밀번호 형식이 잘못되었습니다.(특수문자, 영문자를 포함한 8자리이상 16자리 이하)",
)
@NotBlank
annotation class ValidPassword(

    val message: String = "Invalid email format",

    val groups: Array<KClass<*>> = [],

    val payload: Array<KClass<out Payload>> = []

)
