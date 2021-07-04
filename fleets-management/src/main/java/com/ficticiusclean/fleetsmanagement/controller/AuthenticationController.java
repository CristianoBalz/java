package com.ficticiusclean.fleetsmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ficticiusclean.fleetsmanagement.config.security.TokenService;
import com.ficticiusclean.fleetsmanagement.controller.dto.TokenDto;
import com.ficticiusclean.fleetsmanagement.controller.form.LoginForm;

@RestController
@Profile(value = {"prod"})
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken login = form.converter();
		
		try {
			Authentication authenticate = authManager.authenticate(login);
			String token = tokenService.createToken(authenticate);
			return ResponseEntity.ok(new TokenDto(token,"Bearer"));			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}
