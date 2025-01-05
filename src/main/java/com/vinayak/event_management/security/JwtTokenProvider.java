// package com.vinayak.event_management.security;

// import com.vinayak.event_management.entity.User;
// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import java.util.Date;

// @Component
// public class JwtTokenProvider {

//     // Secret key for signing the JWT token
//     @Value("${jwt.secret}")
//     private String jwtSecret;

//     // JWT expiration time (e.g., 1 hour)
//     @Value("${jwt.expiration}")
//     private long jwtExpiration;

//     // Generate a JWT token based on the user details
//     public String generateToken(User user) {
//         Claims claims = Jwts.claims().setSubject(user.getEmail());  // Set the user email as the subject

//         // Set the issued and expiration times
//         Date issuedAt = new Date();
//         Date expirationTime = new Date(issuedAt.getTime() + jwtExpiration);

//         // Create the JWT token
//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setIssuedAt(issuedAt)
//                 .setExpiration(expirationTime)
//                 .signWith(SignatureAlgorithm.HS512, jwtSecret)  // Sign the token with the secret key
//                 .compact();
//     }

//     // Validate the JWT token
//     public boolean validateToken(String token, UserDetails userDetails) {
//         String username = extractUsername(token);
//         return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//     }

//     // Extract the username (email) from the token
//     public String extractUsername(String token) {
//         return extractClaims(token).getSubject();
//     }

//     // Extract claims from the token
//     private Claims extractClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(jwtSecret)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     // Check if the token is expired
//     private boolean isTokenExpired(String token) {
//         return extractClaims(token).getExpiration().before(new Date());
//     }

//     // Extract expiration date from the token
//     public Date extractExpiration(String token) {
//         return extractClaims(token).getExpiration();
//     }
// }
