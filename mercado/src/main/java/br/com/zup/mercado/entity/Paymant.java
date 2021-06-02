package br.com.zup.mercado.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

import br.com.zup.mercado.controller.validate.StatusPaymant;

@Entity
public class Paymant {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	private Long idPaymant;
	private StatusPaymant statusPaymant;
	
	@NotNull
	@ManyToOne
	private Purchase purchase;
	
	public Paymant(Long idPaymant, StatusPaymant statusPaymant, Purchase purchase) {
		this.idPaymant = idPaymant;
		this.statusPaymant = statusPaymant;
		this.purchase = purchase;
	}
	
	public Paymant() {
	}
	
	public Long getId() {
		return id;
	}
	public Long getIdPaymant() {
		return idPaymant;
	}
	public StatusPaymant getStatusPaymant() {
		return statusPaymant;
	}
	public Purchase getPurchase() {
		return purchase;
	}
}
