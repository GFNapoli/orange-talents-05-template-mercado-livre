package br.com.zup.mercado.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercado.entity.User;
import br.com.zup.mercado.form.UserForm;
import br.com.zup.mercado.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserForm form){
		
		User user = form.converter();
		repository.save(user);
		
		return ResponseEntity.ok().build();
	}
}
