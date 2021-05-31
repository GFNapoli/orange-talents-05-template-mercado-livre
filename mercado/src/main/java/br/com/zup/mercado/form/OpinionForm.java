package br.com.zup.mercado.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.zup.mercado.entity.Opinion;
import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.User;

public class OpinionForm {

	@Min(value = 1)
	@Max(value = 5)
	private Integer score;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;

	public OpinionForm() {
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Opinion toModel(Product product, User user) {
		
		return new Opinion(score, title, description, product, user);
	}
	
}
