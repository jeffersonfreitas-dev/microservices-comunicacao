package com.microservice.productapi.config.jwt.dto;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {

    private Integer id;
    private String name;
    private String email;

    public static AuthUser getUser(Claims claims){
        try {
            var authUser =  new AuthUser().builder()
                    .id((Integer) claims.get("id"))
                    .name((String) claims.get("name"))
                    .email((String) claims.get("email"))
                    .build();
            return authUser;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
