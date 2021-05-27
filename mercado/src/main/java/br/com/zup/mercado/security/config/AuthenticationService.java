package br.com.zup.mercado.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.mercado.entity.User;
import br.com.zup.mercado.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = repository.findByLogin(username);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UsernameNotFoundException("Usuario "+user.get().getUsername() +" não cadastrado");
	}

}
