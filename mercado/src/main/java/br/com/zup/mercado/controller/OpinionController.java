package br.com.zup.mercado.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercado.entity.Opinion;
import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.User;
import br.com.zup.mercado.form.OpinionForm;
import br.com.zup.mercado.repository.OpinionRepository;
import br.com.zup.mercado.repository.ProductRepository;
import br.com.zup.mercado.repository.UserRepository;

@RestController
@RequestMapping("/opinion")
public class OpinionController {

	@Autowired
	private OpinionRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> addOpinion(@PathVariable("id") Long id, @RequestBody @Valid OpinionForm form){
		
		Optional<Product> product = productRepository.findById(id);
		if(product.isEmpty())return ResponseEntity.badRequest().build();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userLogin = ((UserDetails)principal).getUsername();
		Optional<User> user = userRepository.findByLogin(userLogin);
		
		Opinion opinion = form.toModel(product.get(), user.get());
		
		repository.save(opinion);
		
		return ResponseEntity.ok().build();
	}
}
