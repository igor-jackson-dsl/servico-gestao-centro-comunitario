package dsl.jackson.igor.SGCC.model.service.Implementation;

import dsl.jackson.igor.SGCC.model.dto.CentroComunitarioDTO;
import dsl.jackson.igor.SGCC.model.dto.CentroRecursoDTO;
import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;
import dsl.jackson.igor.SGCC.model.entity.CentroRecursos;
import dsl.jackson.igor.SGCC.model.entity.Recursos;
import dsl.jackson.igor.SGCC.model.repository.CentroComunitarioRepository;
import dsl.jackson.igor.SGCC.model.repository.CentroRecursosRepository;
import dsl.jackson.igor.SGCC.model.repository.RecursosRepository;
import dsl.jackson.igor.SGCC.model.service.CentroComunitarioService;
import dsl.jackson.igor.SGCC.model.service.NotificacaoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CentroComunitarioServiceImpl implements CentroComunitarioService {


    private final CentroComunitarioRepository centroComunitarioRepository;
    private final NotificacaoService notificacaoService;
    private final RecursosRepository recursosRepository;
    private final CentroRecursosRepository centroRecursosRepository;

    public CentroComunitarioServiceImpl(CentroComunitarioRepository centroComunitarioRepository, NotificacaoService notificacaoService, RecursosRepository recursosRepository, CentroRecursosRepository centroRecursosRepository) {
        this.centroComunitarioRepository = centroComunitarioRepository;
        this.notificacaoService = notificacaoService;
        this.recursosRepository = recursosRepository;
        this.centroRecursosRepository = centroRecursosRepository;
    }

    @Override
    public CentroComunitario criarCentroComunitario(CentroComunitarioDTO centroDTO) {

        CentroComunitario centroComunitario = new CentroComunitario(
                null, // id gerado automaticamente pelo MongoDB
                centroDTO.getNome(),
                centroDTO.getEndereco(),
                centroDTO.getLocalizacao(),
                centroDTO.getCapacidadeMaxima(),
                centroDTO.getPessoasOcupando()
        );
        centroComunitarioRepository.save(centroComunitario);


        List<CentroRecursos> centroRecursosList = new ArrayList<>();
        for (CentroRecursoDTO centroRecursoDTO : centroDTO.getRecursos()){

            CentroRecursos centroRecursos = new CentroRecursos();
            Recursos recursos = recursosRepository.findByCodRecurso(centroRecursoDTO.getCodRecurso());

            if (Objects.nonNull(recursos)){
                centroRecursos.setIdCentro(centroComunitario.getId());
                centroRecursos.setIdRecurso(recursos.getId());
                centroRecursos.setQuantidadeRecurso(centroRecursoDTO.getQuantidadeRecurso());

                centroRecursosList.add(centroRecursos);
            }
        }

        centroRecursosRepository.saveAll(centroRecursosList);


        return centroComunitario;
    }

    @Override
    public boolean atualizarOcupacao(String id, int novaOcupacao) {
        CentroComunitario centro = centroComunitarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Centro não encontrado"));

        if (novaOcupacao > centro.getCapacidadeMaxima()) {
            throw new IllegalArgumentException("Ocupação excede a capacidade máxima: " + centro.getCapacidadeMaxima());
        }

        centro.setPessoasOcupando(novaOcupacao);
        centroComunitarioRepository.save(centro);

        if (novaOcupacao >= centro.getCapacidadeMaxima()) {
            notificacaoService.enviarNotificacao("Centro " + centro.getNome() + " atingiu a capacidade máxima.");
            return true;
        }

        return false;
    }

    @Override
    public List<CentroComunitario> listarCentros(){
        return centroComunitarioRepository.findAll();
    }
}