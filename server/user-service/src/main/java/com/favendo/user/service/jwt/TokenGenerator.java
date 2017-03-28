package com.favendo.user.service.jwt;

import com.favendo.commons.utils.DateFactory;
import com.favendo.user.service.domain.User;
import com.favendo.user.service.service.UserService;
import com.favendo.user.service.utils.StorecastUserContextHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.favendo.user.service.constant.UserConstant.USER_ID;

@Component
public class TokenGenerator {

    @Value("${jwt.expiration.time}")
    private Long expirationTime;

    @Autowired
    private DateFactory dateFactory;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    public String generateToken(User user) {
        JwtBuilder builder = Jwts.builder().setId(tokenUtils.getGeneratedUUID())
                .setIssuer(user.getUsername())
                .setIssuedAt(dateFactory.getDateByMilliseconds())
                .setExpiration(dateFactory.getDateByMilliseconds(expirationTime))
                .setClaims(tokenUtils.getClaims(user, dateFactory.getDateByMilliseconds(expirationTime)))
                .signWith(SignatureAlgorithm.HS256, tokenUtils.getApiSecretKey());
        return builder.compact();
    }

    public Boolean validateToken(String token) throws Exception {
        // Commented temporary for SecurityContextHolder issue
        // User user = StorecastUserContextHolder.getLoggedInUser();
        Claims claims = Jwts.parser()
                .setSigningKey(tokenUtils.getApiSecretKey())
                .parseClaimsJws(token).getBody();
        User user = userService.getByUserId(Long.parseLong(claims.get(USER_ID).toString()));
        return tokenUtils.validateToken(claims, user);
    }
}


























