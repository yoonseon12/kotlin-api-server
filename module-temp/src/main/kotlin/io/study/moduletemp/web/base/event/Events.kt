package io.study.moduletemp.web.base.event

import org.springframework.context.ApplicationEventPublisher

object Events {
    private var eventPublisher: ApplicationEventPublisher? = null

    fun setPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        eventPublisher = applicationEventPublisher
    }

    fun raise(event: Any) {
        eventPublisher?.publishEvent(event)
    }
}