package io.study.moduleapi.common.annotation

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
    message = "{valid-messages.invalid-password}",
)
@NotBlank
annotation class ValidPassword(

    val message: String = "Invalid email format",

    val groups: Array<KClass<*>> = [],

    val payload: Array<KClass<out Payload>> = [],

)
