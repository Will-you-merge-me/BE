package com.springboot.userserver.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final Key secretkey;
    private final long validityInMilliseconds;

    public JwtTokenProvider(@Value("${security.jwt.secret-key}") final String secretkey, @Value("${security.jwt.expire-length}") final long validityInMilliseconds) {
        this.secretkey = Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
        this.validityInMilliseconds = validityInMilliseconds;
    }

    // token 발급
    public String createToken(String nickname, String role) {
        logger.info("JwtTokenProvider: 토큰을 발급했습니다.");
        final Date now = new Date();
        final Date validity = new Date(now.getTime() + validityInMilliseconds);
        Claims claims = Jwts.claims().setSubject(nickname);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretkey, SignatureAlgorithm.HS256)
                .compact();
    }
}
