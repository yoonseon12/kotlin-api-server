dependencies {
    implementation(project(":module-common"))
    implementation("org.springframework.boot:spring-boot-starter-mail")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}
