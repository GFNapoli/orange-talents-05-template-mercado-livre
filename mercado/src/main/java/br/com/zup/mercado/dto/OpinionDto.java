package br.com.zup.mercado.dto;

import br.com.zup.mercado.entity.Opinion;

public class OpinionDto {

	private Integer score;
	private String title;
	private String description;
	private String user;
	
	public OpinionDto(Opinion opinion) {
		this.score = opinion.getScore();
		this.title = opinion.getTitle();
		this.description = opinion.getDescription();
		this.user = opinion.getUser().getUsername();
	}
	
	public OpinionDto() {
	}
	
	public Integer getScore() {
		return score;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getUser() {
		return user;
	}
	
	
}
