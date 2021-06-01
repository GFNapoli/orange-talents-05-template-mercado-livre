package br.com.zup.mercado.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.mercado.entity.Opinion;
import br.com.zup.mercado.entity.Product;
import br.com.zup.mercado.entity.Question;

public class DetailProductDto {

	private String name;
	private BigDecimal value;
	private Integer quantity;
	private Integer averageGrades;
	private Integer numberGrades;
	private List<FeaturesDto> features = new ArrayList<FeaturesDto>();
	private String description;
	private String category;
	private LocalDateTime registrationTime;
	private String ownerUser;
	private List<String> images = new ArrayList<String>();
	private List<QuestionDto> questions = new ArrayList<QuestionDto>();
	private List<OpinionDto> opinions = new ArrayList<OpinionDto>();
	
	
	
	public DetailProductDto(Product product, Iterable<Question> questions, Iterable<Opinion> opinions) {
		this.name = product.getName();
		this.value = product.getValue();
		this.quantity = product.getQuantity();
		this.features.addAll(product.getFeatures().stream().map(feature -> feature.toModel()).collect(Collectors.toList()));
		this.description = product.getDescription();
		this.category = product.getCategory().getName();
		this.registrationTime = product.getRegistrationTime();
		this.ownerUser = product.getUser().getUsername();
		product.getImages().forEach(image -> this.images.add(image.getUrl()));
		questions.forEach(question -> this.questions.add(new QuestionDto(question)));
		opinions.forEach(opinion -> this.opinions.add(new OpinionDto(opinion)));
		
		this.grades();
	}

	public DetailProductDto() {
	}
	
	public String getName() {
		return name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public List<FeaturesDto> getFeatures() {
		return features;
	}
	public String getDescription() {
		return description;
	}
	public String getCategory() {
		return category;
	}
	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}
	public String getOwnerUser() {
		return ownerUser;
	}
	public List<String> getImages() {
		return images;
	}
	public List<QuestionDto> getQuestions() {
		return questions;
	}
	public List<OpinionDto> getOpinions() {
		return opinions;
	}
	public Integer getAverageGrades() {
		return averageGrades;
	}
	public Integer getNumberGrades() {
		return numberGrades;
	}

	public void grades() {
		
		this.numberGrades = this.opinions.size();
		int total = this.numberGrades;
		int grades = 0;
		
		for (OpinionDto opinionDto : opinions) {
			grades+= opinionDto.getScore();
		}
		
		this.averageGrades = grades / total;
	}
}
