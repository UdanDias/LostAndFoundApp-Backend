package lk.ijse.cmjd109.LostAndFoundApp.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Configuration
public class JWTUtils {
    @Value("${signKey}")
    private String signkey;
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(signkey));
    }
    public String generateToken(String userName, Collection<?extends GrantedAuthority>authorities){
        String roles=authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(userName)
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    public Boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(key()).build().parse(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public String getUserNameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(key()).build()
                .parseSignedClaims(token)
                .getBody().getSubject();

    }

}
