package io.study.kotlinapiserver.web.base.event

import org.springframework.context.ApplicationEventPublisher

object Events {
    private lateinit var eventPublisher: ApplicationEventPublisher

    fun setPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        eventPublisher = applicationEventPublisher
    }

    fun raise(event: Any) {
        eventPublisher.publishEvent(event)
    }
}