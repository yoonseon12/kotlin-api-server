package io.study.moduledomain.temp.config.mdc


import org.slf4j.MDC
import org.springframework.core.task.TaskDecorator

class MdcTaskDecorator : TaskDecorator {

    override fun decorate(runnable: Runnable): Runnable {
        val threadContext = MDC.getCopyOfContextMap()
        return Runnable {
            MDC.setContextMap(threadContext)
            runnable.run()
        }
    }

}