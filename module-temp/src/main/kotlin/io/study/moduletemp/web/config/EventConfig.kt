package io.study.moduletemp.web.config

import io.study.moduletemp.web.base.event.Events
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventConfig(
    private val publisher: ApplicationEventPublisher
) {

    @Bean
    fun eventInitializer(): InitializingBean {
        return InitializingBean {
            Events.setPublisher(publisher)
        }
    }

}