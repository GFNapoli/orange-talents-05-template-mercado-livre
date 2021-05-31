package br.com.zup.mercado.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.URL;

import com.sun.istack.NotNull;

@Entity
public class ProductImages {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	@URL
	private String url;
	
	@NotNull
	@ManyToOne
	private Product product;
	
	public ProductImages(@URL @Valid String url, Product product) {
		this.url = url;
		this.product = product;
	}
	
	public ProductImages() {
	}

	public Long getId() {
		return id;
	}
	public String getUrl() {
		return url;
	}
	public Product getProduct() {
		return product;
	}
	
	
	
}
