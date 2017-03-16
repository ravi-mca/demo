package com.favendo.user.service.jwt;

import com.favendo.user.service.domain.StorecastUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.UUID;

import static com.favendo.user.service.constsant.StorecastUserConstant.USERNAME;
import static com.favendo.user.service.constsant.StorecastUserConstant.USER_ID;

@Component
public class TokenUtils {

    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);

    private static final byte[] secretBytes = secret.getEncoded();

    public String getGeneratedUUID() {
        return UUID.randomUUID().toString();
    }

    public String getApiSecretKey() {
        return Base64.getEncoder().encodeToString(secretBytes);
    }

    public Claims getClaims(StorecastUser storecastUser) {
        Claims claims = Jwts.claims();
        claims.put(USER_ID, storecastUser.getUser_id());
        claims.put(USERNAME, storecastUser.getUsername());
        return claims;
    }

    public Boolean validateUser(StorecastUser storecastUser, Claims claims) {
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
