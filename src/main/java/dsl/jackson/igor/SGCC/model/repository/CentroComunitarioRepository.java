package dsl.jackson.igor.SGCC.model.repository;

import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CentroComunitarioRepository extends MongoRepository<CentroComunitario, String> {

}