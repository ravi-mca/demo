package com.favendo.user.service.jwt;

import com.favendo.user.service.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static com.favendo.user.service.constant.UserConstant.*;

public class TokenUtils {

    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);

    private static final byte[] secretBytes = secret.getEncoded();

    public String getGeneratedUUID() {
        return UUID.randomUUID().toString();
    }

    public String getApiSecretKey() {
        return Base64.getEncoder().encodeToString(secretBytes);
    }

    public Claims getClaims(User user, Date expirationTime) {
        Claims claims = Jwts.claims();
        claims.put(USER_ID, user.getUser_id());
        claims.put(USERNAME, user.getUsername());
        claims.put(EXPIRATION_TIME, expirationTime);
        return claims;
    }

    public String getUsernameByToken(String token) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(getApiSecretKey())
                .parseClaimsJws(token).getBody();
        return claims.get(USERNAME).toString();
    }

    public Boolean validateToken(String token, User user) throws Exception {
        Claims claims = Jwts.parser()
                .setSigningKey(getApiSecretKey())
                .parseClaimsJws(token).getBody();
        if (BooleanUtils.isTrue(isTokenExpired(NumberUtils.toLong(claims.get(EXPIRATION_TIME).toString())))) {
            return false;
        }
        if (BooleanUtils.isFalse(isValidUser(claims, user))) {
            return false;
        }
        return true;
    }

    private Boolean isTokenExpired(Long expirationTime) {
        return System.currentTimeMillis() > expirationTime ? true : false;
    }

    private Boolean isValidUser(Claims claims, User user) {
        Boolean isValid = false;
        if (StringUtils.equals(claims.get(USER_ID).toString(), String.valueOf(user.getUser_id()))) {
            isValid = true;
        }
        if (StringUtils.equals(claims.get(USERNAME).toString(), user.getUsername())) {
            isValid = true;
        }
        return isValid;
    }
}
