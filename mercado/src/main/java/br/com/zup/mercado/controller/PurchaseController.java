package br.com.zup.mercado.controller;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercado.repository.PurchaseRepository;
import br.com.zup.mercado.repository.UserRepository;
import br.com.zup.mercado.controller.validate.FormOfPaymant;
import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.Purchase;
import br.com.zup.mercado.entity.User;
import br.com.zup.mercado.fake.EmailFake;
import br.com.zup.mercado.fake.PaymentRedirect;
import br.com.zup.mercado.form.PurchaseForm;
import br.com.zup.mercado.repository.ProductRepository;

@RestController
@RequestMapping("/compra")
public class PurchaseController {

	@Autowired
	private PurchaseRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> purchase(@RequestBody @Valid PurchaseForm form){
		
		Optional<Product> product = productRepository.findById(form.getIdAcquisition());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userLogin = ((UserDetails)principal).getUsername();
		Optional<User> client = userRepository.findByLogin(userLogin);
		FormOfPaymant paymant = FormOfPaymant.NONE;
		
		if(form.getPaymant().equalsIgnoreCase("PAYPAL")) paymant = FormOfPaymant.PAYPAL;
		if(form.getPaymant().equalsIgnoreCase("PAGSEGURO")) paymant = FormOfPaymant.PAGSEGURO;
		if(paymant == FormOfPaymant.NONE)return ResponseEntity.badRequest().build();
		
		if(!product.get().attQuantity(form.getQuantity()))return ResponseEntity.badRequest().build();
		
		Purchase purchase = form.toModel(product.get(), client.get(),paymant);
		
		productRepository.save(product.get());
		repository.save(purchase);
		
		EmailFake.send(userLogin, product.get().getName(), "Avalie seu produto");
		
		return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).body(new PaymentRedirect(paymant));
		
	}
}
