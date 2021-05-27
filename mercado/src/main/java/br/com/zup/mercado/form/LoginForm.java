package br.com.zup.mercado.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	@NotBlank
	private String login;
	
	@NotBlank
	@Size(min= 6)
	private String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login, password);
	}
	
	
}
