package br.com.zup.mercado.form;

import javax.validation.constraints.NotBlank;

import br.com.zup.mercado.annotation.UniqueValue;
import br.com.zup.mercado.entity.Category;

public class CategoryForm {

	@NotBlank
	@UniqueValue(domainClass = Category.class, fieldName = "name")
	private String name;
	
	private Long category;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCategory() {
		return category;
	}
	public void setCategory(Long category) {
		this.category = category;
	}
	
	public Category converter(Category category) {
		return new Category(name, category);
	}
	
}
