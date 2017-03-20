package com.favendo.user.service.jwt;

import com.favendo.commons.utils.DateFactory;
import com.favendo.user.service.domain.StorecastUser;
import com.favendo.user.service.utils.StorecastUserContextHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
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

    @Autowired
    private TokenUtils tokenUtils;

    public String generateToken(StorecastUser storecastUser) {
        JwtBuilder builder = Jwts.builder().setId(tokenUtils.getGeneratedUUID())
                .setIssuer(storecastUser.getUsername())
                .setIssuedAt(dateFactory.getDateByMilliseconds())
                .setExpiration(dateFactory.getDateByMilliseconds(expirationTime))
                .setClaims(tokenUtils.getClaims(storecastUser,dateFactory.getDateByMilliseconds(expirationTime)))
                .signWith(SignatureAlgorithm.HS256, tokenUtils.getApiSecretKey());
        return builder.compact();
    }

    public Boolean validateToken(String token) throws Exception {
        StorecastUser storecastUser = StorecastUserContextHolder.getLoggedInUser();
        Claims claims = Jwts.parser()
                .setSigningKey(tokenUtils.getApiSecretKey())
                .parseClaimsJws(token).getBody();
        return tokenUtils.validateToken(claims, storecastUser);
    }
}


























