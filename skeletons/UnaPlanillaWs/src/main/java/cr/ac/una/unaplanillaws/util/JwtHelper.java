package cr.ac.una.unaplanillaws.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author arayaroma
 */
public class JwtHelper {

    private static JwtHelper jwtHelper = null;
    private static final long EXPIRATION_LIMIT = 1;
    private static final long EXPIRATION_RENEWAL_LIMIT = 5;
    private static final String AUTHENTICATION_SCHEME = "Bearer ";
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private JwtHelper() {
    }

    public static JwtHelper getInstance() {
        if (jwtHelper == null) {
            jwtHelper = new JwtHelper();
        }
        return jwtHelper;
    }

    public String generatePrivateKey(String username) {
        return AUTHENTICATION_SCHEME + Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate(false))
                .claim("rnt", AUTHENTICATION_SCHEME + Jwts
                        .builder()
                        .setSubject(username)
                        .setIssuedAt(new Date())
                        .setExpiration(getExpirationDate(true))
                        .claim("rnw", true)
                        .signWith(key)
                        .compact())
                .signWith(key)
                .compact();
    }

    public Claims claimKey(String privateKey) throws ExpiredJwtException, MalformedJwtException {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(privateKey)
                .getBody();
    }

    private Date getExpirationDate(boolean renewal) {
        long currentTimeInMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        if (renewal) {
            expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_RENEWAL_LIMIT);
        }
        return new Date(currentTimeInMillis + expMilliSeconds);
    }
}
