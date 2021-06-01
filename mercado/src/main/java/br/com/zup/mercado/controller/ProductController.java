package br.com.zup.mercado.controller;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercado.controller.validate.ForbiddenEqualsFeatures;
import br.com.zup.mercado.dto.DetailProductDto;
import br.com.zup.mercado.entity.Category;
import br.com.zup.mercado.entity.Opinion;
import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.Question;
import br.com.zup.mercado.entity.User;
import br.com.zup.mercado.fake.FakeUploader;
import br.com.zup.mercado.form.ImagesForm;
import br.com.zup.mercado.form.ProductForm;
import br.com.zup.mercado.repository.CategoryRepository;
import br.com.zup.mercado.repository.OpinionRepository;
import br.com.zup.mercado.repository.ProductRepository;
import br.com.zup.mercado.repository.QuestionRepository;
import br.com.zup.mercado.repository.UserRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private EntityManager manager;
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FakeUploader uploader;
	
	@Autowired
	private OpinionRepository opinionRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@InitBinder(value = "productForm")
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
	
	@PostMapping(value = "/{id}/imagens")
	@Transactional
	public ResponseEntity<?> addImage(@PathVariable("id") Long id, @Valid ImagesForm form){
		
		Set<String> urls = uploader.send(form.getImages());
		
		Optional<Product> product = repository.findById(id);
		
		if(product.isEmpty())return ResponseEntity.notFound().build();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userLogin = ((UserDetails)principal).getUsername();
		if(!userLogin.equalsIgnoreCase(product.get().getUser().getLogin())) return ResponseEntity.badRequest().build();
		
		product.get().addImages(urls, form);
		manager.merge(product.get());
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> productDetail(@PathVariable Long id){
		
		Optional<Product> product = repository.findById(id);
		
		if(product.isEmpty()) return ResponseEntity.badRequest().build();
		
		Iterable<Opinion> opinions = opinionRepository.findByProductId(id);
		Iterable<Question> questions = questionRepository.findByProductId(id);
		
		return ResponseEntity.ok(new DetailProductDto(product.get(), questions, opinions));
	}
}
