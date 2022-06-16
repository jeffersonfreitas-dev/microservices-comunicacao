package com.microservice.productapi.config.jwt.service;

import com.microservice.productapi.config.exception.AuthenticationException;
import com.microservice.productapi.config.jwt.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class JwtService {

    private static final String BEARER = "bearer ";

    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public void validatorAuthorized(String token){
        try {
            var accessToken = extractToken(token);
            var claims = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            var user = JwtResponse.getUser(claims);
            if(isEmpty(user) || isEmpty(user.getId())){
                throw new AuthenticationException("Usuário não é válido");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            throw new AuthenticationException("Erro ao tentar processar o access token");
        }
    }

    private String extractToken(String token){
        if(isEmpty(token)) throw new AuthenticationException("Token não pode ser nulo");
        if(token.toLowerCase().contains(BEARER)) return token.replace(BEARER, Strings.EMPTY);
    }
}
