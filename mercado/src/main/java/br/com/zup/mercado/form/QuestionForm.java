package br.com.zup.mercado.form;

import javax.validation.constraints.NotBlank;

import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.Question;
import br.com.zup.mercado.entity.User;

public class QuestionForm {
	
	@NotBlank
	private String title;

	public QuestionForm() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Question toModel(Product product, User user) {
		return new Question(title, product, user);
	}
}
