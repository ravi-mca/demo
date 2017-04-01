package com.favendo.user.service.jwt;

import com.favendo.commons.utils.DateFactory;
import com.favendo.user.service.domain.User;
import io.jsonwebtoken.Claims;import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    @Value("${jwt.expiration.time}")
    private Long expirationTime;

    @Autowired
    private DateFactory dateFactory;

    public String generateToken(User user) {
        TokenUtils tokenUtils = new TokenUtils();
        JwtBuilder builder = Jwts.builder().setId(tokenUtils.getGeneratedUUID())
                .setIssuer(user.getUsername())
                .setIssuedAt(dateFactory.getDateByMilliseconds())
                .setExpiration(dateFactory.getDateByMilliseconds(expirationTime))
                .setClaims(tokenUtils.getClaims(user, dateFactory.getDateByMilliseconds(expirationTime)))
                .signWith(SignatureAlgorithm.HS256, tokenUtils.getApiSecretKey());
        return builder.compact();
    }    }


























