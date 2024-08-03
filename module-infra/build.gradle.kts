dependencies {
    implementation(project(":module-common"))
    implementation(project(":module-application"))
    implementation(project(":module-domain"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

