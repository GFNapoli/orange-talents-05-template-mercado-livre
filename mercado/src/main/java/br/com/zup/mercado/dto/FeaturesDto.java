package br.com.zup.mercado.dto;

import br.com.zup.mercado.entity.ProductFeature;

public class FeaturesDto {

	private String name;
	private String description;
	
	public FeaturesDto(ProductFeature feature) {
		this.name = feature.getName();
		this.description = feature.getDescription();
	}
	
	public FeaturesDto() {
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
}
