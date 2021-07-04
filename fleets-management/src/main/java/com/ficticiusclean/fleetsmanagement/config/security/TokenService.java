package com.ficticiusclean.fleetsmanagement.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ficticiusclean.fleetsmanagement.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	private String expiration;

	@Value("${jwt.secret}")
	private String secret;

	public String createToken(Authentication authenticate) {
		Users logged = (Users) authenticate.getPrincipal();
		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("Rest API Fleets Management")
				.setSubject(logged.getId().toString())
				.setIssuedAt(today)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUser(String token) {
		Claims body = Jwts.parser()
		.setSigningKey(this.secret)
		.parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}

}
