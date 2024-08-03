package io.study.moduledomain.temp.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties(prefix = "jwt")
data class JwtProperties @ConstructorBinding constructor(
    val secret: String,
    val accessTokenExpireTime: Long,
    val refreshTokenExpireTime: Long,
)