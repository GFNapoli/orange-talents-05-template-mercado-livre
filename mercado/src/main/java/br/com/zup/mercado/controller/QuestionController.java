package br.com.zup.mercado.controller;

import java.util.Optional;

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

import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.Question;
import br.com.zup.mercado.entity.User;
import br.com.zup.mercado.fake.EmailFake;
import br.com.zup.mercado.form.QuestionForm;
import br.com.zup.mercado.repository.ProductRepository;
import br.com.zup.mercado.repository.QuestionRepository;
import br.com.zup.mercado.repository.UserRepository;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<?> newQuestion(@PathVariable("id") Long id, @RequestBody @Valid QuestionForm form){
		
		Optional<Product> product = productRepository.findById(id);
		if(product.isEmpty())return ResponseEntity.badRequest().build();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userLogin = ((UserDetails)principal).getUsername();
		Optional<User> user = userRepository.findByLogin(userLogin);
		
		Question question = form.toModel(product.get(), user.get());
		
		repository.save(question);
		
		EmailFake.send(product.get().getUser().getLogin(), product.get().getName(), question.getTitle());
		
		return ResponseEntity.ok().build();
	}
}
