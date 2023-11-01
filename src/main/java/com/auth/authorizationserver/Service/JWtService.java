package com.auth.authorizationserver.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JWtService {

    private final String  SECRETE_KEY="5dA8B3F1eC7a6D9f2E0b8A5c7D4eF1a7B6d9C8E5F3a2B0c8A1D7e4F2";

//? extract all the claims

    private Claims  extractAllClaims( String  token){

    return      Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //    ? sign in key

    private Key getSignKey() {
        byte[] KeyBytes= Decoders.BASE64.decode(SECRETE_KEY);
         return Keys.hmacShaKeyFor(KeyBytes);
    }


//    ? extract a single claim


    public <T>T extractSingleClaim(
            String  token,
            Function<Claims,T> claimsResolver
    ){
        final  Claims claims= extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }


//? extract email from  the token

  public   String extractEmail(String token){
        return  extractSingleClaim(token,Claims::getSubject);
    }


//    ? create a new token for the user if the user is a valid user

    public  String  generateToken(
            Map<String , Objects> extraClaims,
            UserDetails userDetails
    ){
        return  Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()+ 1000*60*2))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }

//    ? Generate the token without the extra claims
//    ? over load the method above

    public  String generateToken (UserDetails userDetails){

        return generateToken(new HashMap<>(),userDetails);
    }
//    ? validate the  token to make sure it is a valid token

    public  boolean isTokenValid(String  token,UserDetails userDetails){
        final  String username= extractEmail(token);

        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));

    }

    private boolean isTokenExpired(String token) {

        return extractExpiration(token);
    }

    private boolean extractExpiration(String token) {
        return extractSingleClaim(token,Claims::getExpiration).before(new Date());
    }


}
