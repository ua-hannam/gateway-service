package uahannam.gatewayservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private String secretKey = "C.E.X.";


    public boolean verityToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build().parseClaimsJws(token);

            return claims
                    .getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build().parseClaimsJws(token)
                .getBody().getSubject();
    }
}
