package com.favendo.user.service.jwt;

import com.favendo.commons.utils.DateFactory;
import com.favendo.user.service.domain.StorecastUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static com.favendo.user.service.constsant.StorecastUserConstant.*;

@Component
public class TokenUtils {

    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);

    private static final byte[] secretBytes = secret.getEncoded();

    @Autowired
    private DateFactory dateFactory;

    public String getGeneratedUUID() {
        return UUID.randomUUID().toString();
    }

    public String getApiSecretKey() {
        return Base64.getEncoder().encodeToString(secretBytes);
    }

    public Claims getClaims(StorecastUser storecastUser, Date expirationTime) {
        Claims claims = Jwts.claims();
        claims.put(USER_ID, storecastUser.getUser_id());
        claims.put(USERNAME, storecastUser.getUsername());
        claims.put(EXPIRATION_TIME, expirationTime);
        return claims;
    }

    public Boolean validateToken(Claims claims, StorecastUser storecastUser) {
        if (isTokenExpired(NumberUtils.toLong(claims.get(EXPIRATION_TIME).toString()))) {
            return false;
        }
        if (!isValidUser(claims, storecastUser)) {
            return false;
        }
        return true;
    }

    private Boolean isTokenExpired(Long expirationTime) {
        return dateFactory.getCurrentTimeMilliseconds() > expirationTime ? true : false;
    }

    private Boolean isValidUser(Claims claims, StorecastUser storecastUser) {
        Boolean isValid = false;
        if (StringUtils.equals(claims.get(USER_ID).toString(), String.valueOf(storecastUser.getUser_id()))) {
            isValid = true;
        }
        if (StringUtils.equals(claims.get(USERNAME).toString(), storecastUser.getUsername())) {
            isValid = true;
        }
        return isValid;
    }
}
