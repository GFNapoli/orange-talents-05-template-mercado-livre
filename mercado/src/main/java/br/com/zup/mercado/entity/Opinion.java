package br.com.zup.mercado.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
public class Opinion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	private Integer score;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;
	
	@NotNull
	@ManyToOne
	private Product product;
	
	@NotNull
	@ManyToOne
	private User user;
	
	public Opinion(Integer score, @NotBlank String title, @NotBlank String description, Product product, User user) {
		this.score = score;
		this.title = title;
		this.description = description;
		this.product = product;
		this.user = user;
	}

	public Opinion() {
	}

	public Long getId() {
		return id;
	}

	public Integer getScore() {
		return score;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Product getProduct() {
		return product;
	}

	public User getUser() {
		return user;
	}
	
}
