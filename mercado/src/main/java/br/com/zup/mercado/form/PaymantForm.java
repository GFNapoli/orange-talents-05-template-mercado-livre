package br.com.zup.mercado.form;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.zup.mercado.annotation.ItExists;
import br.com.zup.mercado.annotation.UniqueValue;
import br.com.zup.mercado.controller.validate.StatusPaymant;
import br.com.zup.mercado.entity.Paymant;
import br.com.zup.mercado.entity.Purchase;

public class PaymantForm {

	@NotNull
	@ItExists(domainClass = Purchase.class, fieldName = "id")
	private Long idPurchase;
	
	@NotNull
	@UniqueValue(domainClass = Paymant.class, fieldName = "idPaymant")
	private Long idPaymant;
	
	@NotBlank
	private String statusPaymant;
	
	public PaymantForm() {
	}

	public PaymantForm(Long idPurchase, Long idPaymant, @NotBlank String statusPaymant) {
		this.idPurchase = idPurchase;
		this.idPaymant = idPaymant;
		this.statusPaymant = statusPaymant;
	}

	public Long getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(Long idPurchase) {
		this.idPurchase = idPurchase;
	}

	public Long getIdPaymant() {
		return idPaymant;
	}

	public void setIdPaymant(Long idPaymant) {
		this.idPaymant = idPaymant;
	}

	public String getStatusPaymant() {
		return statusPaymant;
	}

	public void setStatusPaymant(String statusPaymant) {
		this.statusPaymant = statusPaymant;
	}
	
	public Paymant toModel(Purchase purchase ) {
		if(statusPaymant.equalsIgnoreCase("SUCESSO") || statusPaymant.equals("1")) {
			return new Paymant(idPaymant, StatusPaymant.SUCESSO, purchase);
		}else {
			return new Paymant(idPaymant, StatusPaymant.FALHA, purchase);
		}
	}
}
