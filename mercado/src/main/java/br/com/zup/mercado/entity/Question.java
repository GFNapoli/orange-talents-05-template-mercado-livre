package br.com.zup.mercado.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	private String title;
	private LocalDateTime creationAt = LocalDateTime.now();
	
	@NotNull
	@ManyToOne
	private Product product;
	
	@NotNull
	@ManyToOne
	private User user;
	
	public Question(@NotBlank String title, Product product, User user) {
		this.title = title;
		this.product = product;
		this.user = user;
	}

	public Question() {
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public LocalDateTime getCreationAt() {
		return creationAt;
	}

	public Product getProduct() {
		return product;
	}

	public User getUser() {
		return user;
	}
	
}
