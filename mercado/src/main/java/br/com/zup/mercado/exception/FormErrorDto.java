package br.com.zup.mercado.exception;

public class FormErrorDto {

	private String field;
	private String error;
	
	public FormErrorDto(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
}
