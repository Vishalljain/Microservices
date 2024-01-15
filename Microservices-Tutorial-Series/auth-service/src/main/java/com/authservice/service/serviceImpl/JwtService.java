package com.authservice.service.serviceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtService {

	
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";//while generating token i am using this secret to encrypt similarly i need the same secret while decrypting it.


    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }


    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
         String createToken = createToken(claims, userName);
         return createToken;
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)//claims is nothing your header payload and signature whatever your given from the input
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))//when the token is issued 
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))//when the token is expired
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();//which type of algorithm you are using to encrypt your token
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    
    //
//    import io.jsonwebtoken.Claims;
//    import io.jsonwebtoken.Jwts;
//
//    String jwt = "your_jwt_here";
//    Claims claims = Jwts.parser().setSigningKey("your_secret_key").parseClaimsJws(jwt).getBody();

}
