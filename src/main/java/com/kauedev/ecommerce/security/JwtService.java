package com.kauedev.ecommerce.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(UserPrincipal userPrincipal) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expiration);

		return Jwts.builder()
				.subject(userPrincipal.getUsername())
				.claim("role", userPrincipal.getUser().getRole().name())
				.issuedAt(now)
				.expiration(expiryDate)
				.signWith(getSigningKey())
				.compact();
	}

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	public boolean isTokenValid(String token) {
		try {
			extractClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Claims extractClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
