package br.com.zup.mercado.dto;

import java.time.LocalDateTime;

import br.com.zup.mercado.entity.Question;

public class QuestionDto {

	private String title;
	private LocalDateTime creationAt;
	private String user;
	
	public QuestionDto(Question question) {
		this.title = question.getTitle();
		this.creationAt = question.getCreationAt();
		this.user = question.getUser().getUsername();
	}
	
	public QuestionDto() {
	}
	
	public String getTitle() {
		return title;
	}
	public LocalDateTime getCreationAt() {
		return creationAt;
	}
	public String getUser() {
		return user;
	}
}
