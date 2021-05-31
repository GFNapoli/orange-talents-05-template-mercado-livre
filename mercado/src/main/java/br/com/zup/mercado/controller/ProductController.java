package br.com.zup.mercado.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercado.controller.validate.ForbiddenEqualsFeatures;
import br.com.zup.mercado.entity.Category;
import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.User;
import br.com.zup.mercado.form.ProductForm;
import br.com.zup.mercado.repository.CategoryRepository;
import br.com.zup.mercado.repository.ProductRepository;
import br.com.zup.mercado.repository.UserRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Autowired
	private UserRepository userRepository;
	
	@InitBinder
	public void init(WebDataBinder dataBinder) {
		dataBinder.addValidators(new ForbiddenEqualsFeatures());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> registerProduct(@RequestBody @Valid ProductForm form){
		
		Optional<Category> category = categoryRepository.findById(form.getCategory());
		if(category.isEmpty()) return ResponseEntity.notFound().build();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userLogin = ((UserDetails)principal).getUsername();
		Optional<User> user = userRepository.findByLogin(userLogin);
		
		if(user.isEmpty())return ResponseEntity.notFound().build();
		
		Product product = form.converter(category.get(), user.get());
		repository.save(product);
		return ResponseEntity.ok().build();
	}
}
