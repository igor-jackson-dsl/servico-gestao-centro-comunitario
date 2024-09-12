package dsl.jackson.igor.SGCC.model.repository;

import dsl.jackson.igor.SGCC.model.dto.RecursoSomaDTO;
import dsl.jackson.igor.SGCC.model.entity.Recursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecursosRepository extends MongoRepository<Recursos, String> {
    Recursos findByCodRecurso (int codRecurso);

}