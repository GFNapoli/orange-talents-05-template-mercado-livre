package br.com.zup.mercado.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.mercado.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

	Optional<User> findByLogin(String username);

}
