package dsl.jackson.igor.SGCC.model.repository;

import dsl.jackson.igor.SGCC.model.dto.RecursoSomaDTO;
import org.springframework.data.mongodb.repository.Aggregation;
import dsl.jackson.igor.SGCC.model.entity.CentroRecursos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroRecursosRepository extends MongoRepository<CentroRecursos, String>{

    @Query("{ 'idCentro': ?0, 'idRecurso': ?1 }")
    CentroRecursos findByIdRecursoCentro(String idCentro, String idRecurso);

    @Aggregation(pipeline = {
            "{ '$group': { '_id': '$idRecurso', 'totalQuantidade': { '$sum': '$quantidadeRecurso' } } }",
            "{ '$project': { 'idRecurso': '$_id', 'totalQuantidade': 1, '_id': 0 } }"
        })
    List<RecursoSomaDTO> somarQuantidadePorIdRecurso();


}