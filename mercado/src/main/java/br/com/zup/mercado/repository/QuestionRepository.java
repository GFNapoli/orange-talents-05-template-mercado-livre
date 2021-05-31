package br.com.zup.mercado.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.mercado.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{

}
