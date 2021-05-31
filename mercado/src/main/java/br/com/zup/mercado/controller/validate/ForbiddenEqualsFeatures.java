package br.com.zup.mercado.controller.validate;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.mercado.form.ProductForm;

public class ForbiddenEqualsFeatures implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors())return ;
		
		ProductForm form = (ProductForm) target;
		Set<String> equalNames = form.equalFeatures();
		if(equalNames.size() > 0) {
			errors.rejectValue("features", null, "você tentou cadastrar caracteristicas iguais!");
		}

	}

}
