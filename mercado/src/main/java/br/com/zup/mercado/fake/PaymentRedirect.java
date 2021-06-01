package br.com.zup.mercado.fake;



import org.hibernate.id.UUIDGenerator;

import br.com.zup.mercado.controller.validate.FormOfPaymant;

public class PaymentRedirect {

	private FormOfPaymant paymant;
	private String url;

	public PaymentRedirect(FormOfPaymant paymant) {
		this.paymant = paymant;
		if(paymant == FormOfPaymant.PAYPAL) {
			url = "paypal.com?buyerId={"+UUIDGenerator.buildSessionFactoryUniqueIdentifierGenerator()+"}&redirectUrl={urlRetornoAppPosPagamento}";
		}
		url = "pagseguro.com?returnId={"+UUIDGenerator.buildSessionFactoryUniqueIdentifierGenerator()+"}&redirectUrl={urlRetornoAppPosPagamento}";
	}

	public FormOfPaymant getPaymant() {
		return paymant;
	}

	public String getUrl() {
		return url;
	}
	
	
}
