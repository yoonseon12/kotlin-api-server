val jjwtVersion: String by project.extra

dependencies {
    implementation(project(":module-application"))
    implementation(project(":module-domain"))
    implementation(project(":module-common"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")
}

tasks.bootJar {
    enabled = false
}

tasks.jar {
    enabled = true
}

