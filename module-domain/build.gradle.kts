dependencies {
    implementation(project(":module-common"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    // H2
    runtimeOnly("com.h2database:h2")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}