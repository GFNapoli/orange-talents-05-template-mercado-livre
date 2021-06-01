package br.com.zup.mercado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.mercado.entity.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long>{

}
