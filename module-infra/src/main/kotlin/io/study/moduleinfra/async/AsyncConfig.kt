package io.study.moduleinfra.async

import io.study.moduleinfra.async.mdc.MdcTaskDecorator
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
@EnableAsync
class AsyncConfig : AsyncConfigurer {
    companion object {
        const val CORE_POOL_SIZE = 10
        const val MAX_POOL_SIZE = 30
        const val QUEUE_CAPACITY = 100
        const val AWAIT_TERMINATION_SECONDS = 30
        const val THREAD_NAME_PREFIX = "executor-"
        const val WAIT_TASK_COMPLETE = true
    }

    @Bean
    fun taskExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = CORE_POOL_SIZE // 동시에 실행 할 기본 스레드의 수를 설정
        executor.maxPoolSize = MAX_POOL_SIZE // 스레드풀의 사용할 수 있는 최대 스레드 수를 설정
        executor.queueCapacity = QUEUE_CAPACITY // 스레드풀 executor의 작업 큐의 크기를 설정
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX) // 스레드의 이름 설정
        executor.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS) // 최대 대기 타임 아웃 설정

        // true 설정시 어플리케이션 종료 요청시 queue에 남아 있는 모든 작업들이 완료될 때까지 기다린 후 종료
        executor.setWaitForTasksToCompleteOnShutdown(WAIT_TASK_COMPLETE);

        executor.setTaskDecorator(MdcTaskDecorator())
        executor.initialize()

        return executor
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler {
        return AsyncExceptionHandler()
    }
}