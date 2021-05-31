package br.com.zup.mercado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.mercado.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
