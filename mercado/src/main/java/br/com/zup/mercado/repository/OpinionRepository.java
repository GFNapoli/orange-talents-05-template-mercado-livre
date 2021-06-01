package br.com.zup.mercado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.mercado.entity.Opinion;

public interface OpinionRepository extends CrudRepository<Opinion, Long>{

	Iterable<Opinion> findByProductId(Long id);

}
