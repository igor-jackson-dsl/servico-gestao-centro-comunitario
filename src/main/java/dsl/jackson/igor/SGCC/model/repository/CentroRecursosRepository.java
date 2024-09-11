package dsl.jackson.igor.SGCC.model.repository;

import dsl.jackson.igor.SGCC.model.entity.CentroRecursos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CentroRecursosRepository extends MongoRepository<CentroRecursos, String> {

    @Query("{ 'idCentro': ?0, 'idRecurso': ?1 }")
    CentroRecursos findByIdRecursoCentro(String idCentro, String idRecurso);
}