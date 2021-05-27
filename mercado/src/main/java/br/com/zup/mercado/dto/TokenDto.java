package br.com.zup.mercado.dto;

public class TokenDto {

	private String type;
	private String token;
	
	public TokenDto(String type, String token) {
		this.type = type;
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public String getToken() {
		return token;
	}
}
