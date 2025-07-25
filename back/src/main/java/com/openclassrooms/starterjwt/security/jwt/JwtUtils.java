package com.openclassrooms.starterjwt.security.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${oc.app.jwtSecret}")
  private String jwtSecret;

  @Value("${oc.app.jwtExpirationMs}")
  private int jwtExpirationMs;


  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
	
	SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    return Jwts.builder()
		.subject(userPrincipal.getUsername())
        .issuedAt(new Date())
        .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(key)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
	SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
	SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    try {
      Jwts.parser().verifyWith(key).build().parseSignedClaims(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
