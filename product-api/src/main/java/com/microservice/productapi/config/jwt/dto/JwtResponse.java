package com.microservice.productapi.config.jwt.dto;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private Integer id;
    private String name;
    private String email;

    public static JwtResponse getUser(Claims claims){
        try {
            return new JwtResponse().builder()
                    .id((Integer) claims.get("id"))
                    .name((String) claims.get("name"))
                    .email((String) claims.get("email"))
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
