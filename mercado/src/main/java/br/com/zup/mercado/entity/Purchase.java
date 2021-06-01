package br.com.zup.mercado.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import br.com.zup.mercado.controller.validate.FormOfPaymant;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	@ManyToOne
	private Product product;
	
	@NotNull
	@Positive
	private BigDecimal value;
	
	@NotNull
	@Positive
	private Integer quantity;
	
	@NotNull
	@ManyToOne
	private User user;
	private FormOfPaymant paymant;
	
	public Purchase() {
	}

	public Purchase(Product product, Integer quantity, User user, FormOfPaymant paymant) {
		this.product = product;
		this.value = product.getValue();
		this.quantity = quantity;
		this.user = user;
		this.paymant = paymant;
	}

	public Long getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public BigDecimal getValue() {
		return value;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public User getUser() {
		return user;
	}

	public FormOfPaymant getPaymant() {
		return paymant;
	}
	
}
