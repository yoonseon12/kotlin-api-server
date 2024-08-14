package io.study.kotlinapiserver.web.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.study.kotlinapiserver.web.config.properties.JwtProperties
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@Component
class JwtProvider(
    private val jwtProperties: JwtProperties
) {

    private val key: Key

    init {
        val keyBytes = Decoders.BASE64.decode(jwtProperties.secret)
        this.key = Keys.hmacShaKeyFor(keyBytes)
    }

    companion object {
        private const val AUTHORITIES_KEY = "auth"
    }

    fun createToken(authentication: Authentication): TokenInfo {
        val authirities = authentication.authorities
            .map { it.authority }
            .joinToString(", ")

        val accessToken = createAccessToken(authentication.name, authirities)
        val refreshToken = createRefreshToken(authentication.name)

        return TokenInfo(accessToken, refreshToken)
    }

    private fun createAccessToken(sub: String, authorities: String): String {
        return Jwts.builder()
            .setSubject(sub)
            .claim(AUTHORITIES_KEY, authorities)
            .setExpiration(getExpirationTime(jwtProperties.accessTokenExpireTime))
            .signWith(key, SignatureAlgorithm.HS512)
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .compact()
    }

    private fun createRefreshToken(sub: String): String {
        return Jwts.builder()
            .setSubject(sub)
            .setExpiration(getExpirationTime(jwtProperties.refreshTokenExpireTime))
            .signWith(key, SignatureAlgorithm.HS512)
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .compact()
    }

    private fun getExpirationTime(tokenExpireTime: Long) : Date {
        val localDateTime = LocalDateTime.now()
            .plusSeconds(tokenExpireTime)
        val toInstant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()

        return Date.from(toInstant)
    }

    fun validateAccessToken(token: String): Boolean {
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)

        return true
    }

    fun getAuthentication(token: String): Authentication {
        val claims = parseClaims(token)
        val authorities = claims[AUTHORITIES_KEY].toString().split(",")
            .map { SimpleGrantedAuthority(it)}
            .toList()

        val principal = User(claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    private fun parseClaims(accessToken: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }
}