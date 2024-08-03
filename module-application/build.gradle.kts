dependencies {
    implementation(project(":module-common"))
    implementation(project(":module-domain"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

