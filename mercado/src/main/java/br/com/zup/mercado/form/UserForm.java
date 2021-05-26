package br.com.zup.mercado.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.mercado.annotation.UniqueValue;
import br.com.zup.mercado.entity.User;
import br.com.zup.mercado.security.CleanPassword;

public class UserForm {

	@NotBlank
	@Email
	@UniqueValue(domainClass = User.class, fieldName = "login")
	private String login;
	
	@NotBlank
	@Size(min = 6)
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
	
	public User converter() {
		return new User(login, new CleanPassword(password));
	}
}
