package io.study.kotlinapiserver.web.annotation


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnlyOwnerAllowed
