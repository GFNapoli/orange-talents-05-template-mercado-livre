package br.com.zup.mercado.form;

import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import br.com.zup.mercado.annotation.ItExists;
import br.com.zup.mercado.controller.validate.FormOfPaymant;
import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.Purchase;
import br.com.zup.mercado.entity.User;

public class PurchaseForm {

	@NotNull
	@ItExists(domainClass = Product.class, fieldName = "id")
	private Long idAcquisition;
	
	@NotNull
	@Positive
	private Integer quantity;
	
	@NotNull
	private String paymant;

	public PurchaseForm() {
	}

	public PurchaseForm(Long idAcquisition, @Positive Integer quantity, String paymant) {
		this.idAcquisition = idAcquisition;
		this.quantity = quantity;
		this.paymant = paymant;
	}

	public Long getIdAcquisition() {
		return idAcquisition;
	}

	public void setIdAcquisition(Long idAcquisition) {
		this.idAcquisition = idAcquisition;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPaymant() {
		return paymant;
	}

	public void setPaymant(String paymant) {
		this.paymant = paymant;
	}

	public Purchase toModel(Product product, User user, FormOfPaymant paymant2) {
		return new Purchase(product, quantity, user, paymant2);
	}
	
	
}
