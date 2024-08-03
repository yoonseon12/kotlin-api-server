package io.study.moduleapi.common.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.NotBlank
import kotlin.reflect.KClass

@Constraint(validatedBy = [])
@Target(allowedTargets = [AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER])
@Retention(AnnotationRetention.RUNTIME)
@jakarta.validation.constraints.Pattern(
    regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})+$",
    message = "{valid-messages.invalid-email}",
)
@NotBlank
annotation class ValidEmail(

    val message: String = "Invalid email format",

    val groups: Array<KClass<*>> = [],

    val payload: Array<KClass<out Payload>> = [],

)
