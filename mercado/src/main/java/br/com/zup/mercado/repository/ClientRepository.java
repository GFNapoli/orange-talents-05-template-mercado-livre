package br.com.zup.mercado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.mercado.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{

}
