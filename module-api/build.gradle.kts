dependencies {
    implementation(project(":module-common"))
    implementation(project(":module-application"))
    implementation(project(":module-domain"))
    implementation(project(":module-infra"))
    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.bootJar {
    enabled = true
}

tasks.jar {
    enabled = false
}
