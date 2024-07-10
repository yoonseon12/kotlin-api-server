package io.study.kotlinapiserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class KotlinApiServerApplication

fun main(args: Array<String>) {
    runApplication<KotlinApiServerApplication>(*args)
}
