package br.com.zup.mercado.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercado.entity.Client;
import br.com.zup.mercado.form.ClientForm;
import br.com.zup.mercado.repository.ClientRepository;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> registerClient(@RequestBody @Valid ClientForm form){
		
		Client client = form.converter();
		repository.save(client);
		
		return ResponseEntity.ok().build();
	}
}
