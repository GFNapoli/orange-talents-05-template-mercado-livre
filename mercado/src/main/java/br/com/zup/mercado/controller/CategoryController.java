package br.com.zup.mercado.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercado.entity.Category;
import br.com.zup.mercado.form.CategoryForm;
import br.com.zup.mercado.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryForm form){
		
		if(form.getCategory() != null) {
			Optional<Category> motherCategory = categoryRepository.findById(form.getCategory());
			if(motherCategory.isPresent()) {
				Category category = form.converter(motherCategory.get());
				categoryRepository.save(category);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada!");
		}
		
		Category category = form.converter(null);
		categoryRepository.save(category);
		return ResponseEntity.ok().build();
	}
	
}
