package dsl.jackson.igor.SGCC.model.repository;

import dsl.jackson.igor.SGCC.model.entity.Intercambio;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IntercambioRepository extends MongoRepository<Intercambio, String> {
    // Consulta para buscar intercâmbios entre duas datas
    List<Intercambio> findByDataHoraBetween(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);
}