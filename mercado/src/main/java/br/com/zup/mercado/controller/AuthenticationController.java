package br.com.zup.mercado.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercado.dto.TokenDto;
import br.com.zup.mercado.form.LoginForm;
import br.com.zup.mercado.security.config.TokenGenerator;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenGenerator tokenGenerator;
	
	@PostMapping
	public ResponseEntity<?> authentication(@RequestBody @Valid LoginForm form){
		
		UsernamePasswordAuthenticationToken loginData = form.converter();
		
		try {
			Authentication authetication = authManager.authenticate(loginData);
			String token = tokenGenerator.gerateToken(authetication);
			return ResponseEntity.ok(new TokenDto("Bearer", token));
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
