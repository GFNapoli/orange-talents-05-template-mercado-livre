package br.com.zup.mercado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.mercado.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
