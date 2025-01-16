package com.E_Commerce.backend.service.utils;

import com.E_Commerce.backend.lib.auth.JwtPayload;
import com.E_Commerce.backend.lib.auth.UserPrincipal;
import com.E_Commerce.backend.lib.exception.UserNotFoundException;
import com.E_Commerce.backend.model.Users;
import com.E_Commerce.backend.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${security.jwt.secret-key}")
    private String secretkey;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void validateSecretKey() {
        if (secretkey == null || secretkey.isEmpty()) {
            throw new IllegalStateException("JWT Secret Key must be configured.");
        }
    }

    public String generateToken(Long id, String username, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("role", role);
        claims.put("email", email);
        return Jwts.builder().claims().add(claims).subject(id.toString()).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000)).and().signWith(getKey()).compact();
    }

    public SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String extractId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private UserPrincipal loadUserById(Long id) throws UserNotFoundException {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return new UserPrincipal(user);
    }

    public JwtPayload extractData(String token) {
        Long id = Long.valueOf(extractClaim(token, Claims::getSubject));  // Get 'id' as Long
        String username = extractClaim(token, claims -> claims.get("username", String.class));
        String role = extractClaim(token, claims -> claims.get("role", String.class));
        String email = extractClaim(token, claims -> claims.get("email", String.class));
        return new JwtPayload(id, username, email, role);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userId = extractId(token);
        UserPrincipal user = loadUserById(Long.valueOf(userId));
        return user.getId().toString().equals(userId) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
