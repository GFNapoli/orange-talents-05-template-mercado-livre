package br.com.zup.mercado.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zup.mercado.security.CleanPassword;

@Entity
@Table(name = "client")
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public LocalDateTime getInstant() {
		return instant;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
