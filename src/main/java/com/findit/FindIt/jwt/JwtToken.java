package com.findit.FindIt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtToken {

    @Value("${testing.app.secret}")
    private String secret;
    @Value("${testing.app.expirationM}")
    private Duration lifetime;

    public String generateToken(UserDetails userDetails){
        Map<String , Object> claims = new HashMap<>();
        Set<String> rolesList = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        claims.put("roles",rolesList);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime()+lifetime.toMillis());

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(issuedDate)
                .expiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,getKey())
                .compact();
    }

    private Claims getClaimsFromToken(String token){
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();

    }
    public String getUsername(String token){
        return getClaimsFromToken(token).getSubject();
    }
    public Set<String> getRoles(String token){
        return getClaimsFromToken(token).get("roles",Set.class);
    }
    private SecretKey getKey(){
        byte[] keyBytes= Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
