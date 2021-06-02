package br.com.zup.mercado.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercado.controller.validate.StatusPaymant;
import br.com.zup.mercado.entity.Paymant;
import br.com.zup.mercado.entity.Purchase;
import br.com.zup.mercado.fake.EmailFake;
import br.com.zup.mercado.form.PaymantForm;
import br.com.zup.mercado.repository.PurchaseRepository;

@RestController
@RequestMapping("/pagamento")
public class PaymantController {

	@Autowired
	private EntityManager manager;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> paymant(@RequestBody @Valid PaymantForm form){
		
		Optional<Purchase> purchase = purchaseRepository.findById(form.getIdPurchase());
		
		for (Paymant payment : purchase.get().getPaymentMade()) {
			if(payment.getStatusPaymant().equals(StatusPaymant.SUCESSO)) {
				return ResponseEntity.badRequest().build();
			}
		}
		Paymant payment = form.toModel(purchase.get());
		purchase.get().addPayment(payment);
		
		if(payment.getStatusPaymant().equals(StatusPaymant.FALHA)) {
			EmailFake.send(purchase.get().getUser().getUsername(), "falha no pagamento", "ouve fala no seu pagamento");
		}else {
			EmailFake.send(purchase.get().getUser().getUsername(), "pagamento realizado com sucesso", "sucesso");
		}
		
		
		
		manager.persist(purchase.get());
		return ResponseEntity.ok().build();
		
	}
}
