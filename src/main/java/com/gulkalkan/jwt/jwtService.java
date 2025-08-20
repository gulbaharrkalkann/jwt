package com.gulkalkan.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtService {
    public static final String SECRET_KEY = "SsFydGdzfrEJod5SeBKvzB4+r7ompeJJLoHPw+jTBL4=";

    public String generateToken(UserDetails userDetails) {
Map<String,String> claimsMap = new HashMap<>();
 claimsMap.put("role","Admin");
        // You can add custom claims here if needed
        // claims.put("role", userDetails.getAuthorities().stream().findFirst().get().getAuthority());
      return   Jwts.builder()
                .setSubject(userDetails.getUsername())
              .setClaims(claimsMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();


    }

    public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {
       Claims claims= Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
    return  claimsFunction.apply(claims);

    }


    public String getUserNameById(String token) {
        return exportToken(token, Claims::getSubject);
    }


    public Boolean isTokenExpired(String token) {
        Date expireDate= exportToken(token, Claims::getExpiration);
        return new Date().before(expireDate);
    }
    public Key getKey() {
       byte[] keybytes= Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keybytes);
    }
}
