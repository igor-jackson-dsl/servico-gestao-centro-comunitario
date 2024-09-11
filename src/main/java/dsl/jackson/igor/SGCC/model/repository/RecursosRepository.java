package dsl.jackson.igor.SGCC.model.repository;

import dsl.jackson.igor.SGCC.model.entity.Recursos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecursosRepository extends MongoRepository<Recursos, String> {


    Recursos findByCodRecurso (int codRecurso);
}