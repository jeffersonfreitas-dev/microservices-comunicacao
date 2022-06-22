package com.microservice.productapi.config.jwt.service;

import com.microservice.productapi.config.exception.AuthenticationException;
import com.microservice.productapi.config.jwt.dto.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@Data
public class JwtService {

    private static final String BEARER = "Bearer ";

    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public void validatorAuthorized(String token){
        var accessToken = extractToken(token);
        try {
            var claims = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            var user = AuthUser.getUser(claims);
            if(isEmpty(user) || isEmpty(user.getId())){
                throw new AuthenticationException("Usuário não é válido");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            throw new AuthenticationException("Erro ao tentar processar o access token");
        }
    }

    private String extractToken(String token){
        String result = "";
        if(isEmpty(token)) throw new AuthenticationException("Token não pode ser nulo");
        if(token.contains(BEARER)) {
            result = token.replace(BEARER, Strings.EMPTY);
        }
        return result;
    }
}
