package com.touchsoft.timetracker.api.security.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_ROLES = "roles";
    private static final String SECRET = "mySecret";
    private static final Long EXPIRATION = 60L;//minutes

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        LocalDateTime createdDate = LocalDateTime.now();
        claims.put(CLAIM_KEY_CREATED, Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.toList()));
        Date expirationDate = Date.from(createdDate.plusMinutes(EXPIRATION)
                .atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUsername(String token) {
        String username;
        try {
            final Claims claims = getClaims(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public List<GrantedAuthority> getAuthorities(String token) {
        List<GrantedAuthority> authorities;
        List<String> roles;
        try {
            final Claims claims = getClaims(token);
            roles = claims.get(CLAIM_KEY_ROLES, List.class);
            authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            authorities = null;
        }
        return authorities;
    }

    private Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration == null || expiration.before(new Date());
    }

    private Date getExpirationDate(String token) {
        Date expiration;
        try {
            final Claims claims = getClaims(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

}
