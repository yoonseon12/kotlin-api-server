package io.study.moduletemp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ModuleTempApplication

fun main(args: Array<String>) {
    runApplication<ModuleTempApplication>(*args)
}
