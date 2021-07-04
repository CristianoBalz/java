package com.ficticiusclean.fleetsmanagement.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ficticiusclean.fleetsmanagement.model.Users;
import com.ficticiusclean.fleetsmanagement.repository.UserRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;	
	private UserRepository repository;
	
	public AuthenticationTokenFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = retrieveToken(request);
		boolean isValid = tokenService.isValidToken(token);
		if(isValid) {
			clientAuthenticate(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void clientAuthenticate(String token) {
		Long idUser = tokenService.getIdUser(token); 
		Optional<Users> userOpt = repository.findById(idUser);
		if(userOpt.isPresent()) {
			Users user = userOpt.get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String retrieveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;			
		}
		
		return token.substring(7, token.length());
	}

}
