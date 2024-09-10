package dsl.jackson.igor.SGCC.model.repository;

import dsl.jackson.igor.SGCC.model.entity.Intercambio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IntercambioRepository extends MongoRepository<Intercambio, String> {

}