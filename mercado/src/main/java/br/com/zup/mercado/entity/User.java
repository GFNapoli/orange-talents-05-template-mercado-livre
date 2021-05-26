package br.com.zup.mercado.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.mercado.security.CleanPassword;

@Entity
@Table(name = "client")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	@Size(min = 6)
	private String password;
	private LocalDateTime instant = LocalDateTime.now();
	
	public User(@NotBlank @Email String login, CleanPassword cleanPassword) {
		this.login = login;
		this.password = cleanPassword.hash();
	}
	
}
