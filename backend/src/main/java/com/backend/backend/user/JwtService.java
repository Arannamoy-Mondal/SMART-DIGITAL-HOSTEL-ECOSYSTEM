package com.backend.backend.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
   
   private final SecretKey secretKey;
   public JwtService() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
    public String generateToken(String userName) {
      Map<String,Object>claims=new HashMap<>();
      return Jwts.builder()
      .setClaims(claims)
      .setSubject(userName)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
      .signWith(secretKey,SignatureAlgorithm.HS512).compact();
    }

}
