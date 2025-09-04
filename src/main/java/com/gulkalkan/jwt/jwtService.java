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
import java.util.Objects;
import java.util.function.Function;

@Component
public class jwtService {
    public static final String SECRET_KEY = "SsFydGdzfrEJod5SeBKvzB4+r7ompeJJLoHPw+jTBL4=";

    public String generateToken(UserDetails userDetails) {


        Map<String, Object> claimsMap = new HashMap<>();

        claimsMap.put("role","ADMIN");




        // You can add custom claims here if needed
        // claims.put("role", userDetails.getAuthorities().stream().findFirst().get().getAuthority());
      return   Jwts.builder()
                .setSubject(userDetails.getUsername())
              .addClaims(claimsMap)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2)) // 10 hours expiration
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();


    }




    public   Object getClaimsByKey(String token,String key){
        Claims claims=getClaims(token);

        return claims.get(key);

    }

    public   Claims getClaims(String token){
        Claims claims= Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
        return  claims;
    }


    public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {

      Claims claims = getClaims(token);


    return  claimsFunction.apply(claims);

    }


    public String getUserNameById(String token) {
        return exportToken(token, Claims::getSubject);
    }


    public Boolean isTokenExpired(String token) {
        Date expireDate= exportToken(token, Claims::getExpiration);
        return new Date().before(expireDate);
    }
    public  Key getKey() {
       byte[] keybytes= Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keybytes);
    }
}
