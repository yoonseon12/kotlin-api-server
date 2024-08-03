dependencies {
    implementation(project(":module-common"))
    implementation(project(":module-domain"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

