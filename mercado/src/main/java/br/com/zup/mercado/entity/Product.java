package br.com.zup.mercado.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import com.sun.istack.NotNull;

import br.com.zup.mercado.form.FeatureForm;
import br.com.zup.mercado.form.ImagesForm;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotNull
	@Positive
	private BigDecimal value;
	
	@NotNull
	@Positive
	private Integer quantity;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
	private Set<ProductFeature> features = new HashSet<ProductFeature>();
	
	@Length(max = 1000)
	private String description;
	
	@NotNull
	@ManyToOne
	private Category category;
	private LocalDateTime registrationTime = LocalDateTime.now();
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
	private Set<ProductImages> images = new HashSet<ProductImages>();

	
	
	public Product(@NotBlank String name, @NotEmpty @Positive BigDecimal value, @NotEmpty @Positive Integer quantity,
			Collection<FeatureForm> features, @Length(max = 1000) String description, Category category, User user) {
		this.name = name;
		this.value = value;
		this.quantity = quantity;
		this.description = description;
		this.category = category;
		this.user = user;
		this.features.addAll(features.stream().map(feature -> feature.toModel(this)).collect(Collectors.toSet()));
		
		Assert.isTrue(this.features.size() >= 3, "Precisa de pelo menos 3 caracteristicas");
	}
	
	public Product() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Set<ProductFeature> getFeatures() {
		return features;
	}

	public String getDescription() {
		return description;
	}

	public Category getCategory() {
		return category;
	}

	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}

	public User getUser() {
		return user;
	}

	public Set<ProductImages> getImages() {
		return images;
	}

	public void addImages(Set<String> urls, ImagesForm form) {
		
		this.images.addAll(urls.stream().map(image -> form.toModel(image, this)).collect(Collectors.toSet()));
		
	}
	
	public Boolean attQuantity(Integer quantity) {
		if(this.quantity < quantity) return false;
		
		this.quantity -= quantity;
		return true;
	}
	
}
