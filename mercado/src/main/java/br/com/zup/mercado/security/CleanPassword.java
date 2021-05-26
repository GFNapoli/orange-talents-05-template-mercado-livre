package br.com.zup.mercado.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CleanPassword {

	@NotBlank
	@Size(min= 6)
	private String password;

	public CleanPassword(@NotBlank @Size(min = 6) String password) {
		this.password = password;
	}

	public String hash() {
		return new BCryptPasswordEncoder().encode(password);
	}
}
