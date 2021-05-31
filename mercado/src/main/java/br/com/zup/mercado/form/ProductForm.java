package br.com.zup.mercado.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.zup.mercado.annotation.ItExists;
import br.com.zup.mercado.entity.Category;
import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.User;

public class ProductForm {

	@NotBlank
	private String name;
	
	@NotNull
	@Positive
	private BigDecimal value;
	
	@NotNull
	@Positive
	private Integer quantity;
	
	@Size(min = 3)
	@Valid
	private List<FeatureForm> features = new ArrayList<FeatureForm>();
	
	@Length(max = 1000)
	private String description;
	
	@NotNull
	@ItExists(domainClass = Category.class, fieldName = "id")
	private Long category;
	
	public ProductForm(@NotBlank String name, @Positive BigDecimal value, @Positive Integer quantity,
			@Size(min = 3) @Valid List<FeatureForm> feature, @Length(max = 1000) String description, Long category) {
		this.name = name;
		this.value = value;
		this.quantity = quantity;
		this.features.addAll(feature);
		this.description = description;
		this.category = category;
	}

	public ProductForm() {
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<FeatureForm> getFeature() {
		return features;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public List<FeatureForm> getFeatures() {
		return features;
	}

	public void setFeatures(List<FeatureForm> features) {
		this.features = features;
	}

	public Product converter(Category category, User user) {
		return new Product(name, value, quantity, features, description, category, user);
	}

	public Set<String> equalFeatures() {
		HashSet<String> equalfeature = new HashSet<String>();
		HashSet<String> sameNames = new HashSet<String>();
		for(FeatureForm feature: features ) {
			System.out.println(feature.getName());
			if(!equalfeature.add(feature.getName())) {
				sameNames.add(feature.getName());
			}
		}
		
		return sameNames;
	}
	
	
}
