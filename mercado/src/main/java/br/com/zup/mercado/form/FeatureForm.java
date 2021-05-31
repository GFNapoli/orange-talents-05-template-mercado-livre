package br.com.zup.mercado.form;

import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.ProductFeature;

public class FeatureForm {

	private String name;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductFeature toModel(Product product) {
		return new ProductFeature(name, description, product);
	}
	
	
}
