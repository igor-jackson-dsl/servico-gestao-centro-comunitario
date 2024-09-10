package dsl.jackson.igor.SGCC.model.service.Implementation;

import dsl.jackson.igor.SGCC.model.dto.CentroComunitarioDTO;
import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;
import dsl.jackson.igor.SGCC.model.entity.Recursos;
import dsl.jackson.igor.SGCC.model.repository.CentroComunitarioRepository;
import dsl.jackson.igor.SGCC.model.service.CentroComunitarioService;
import dsl.jackson.igor.SGCC.model.service.NotificacaoService;
import org.springframework.stereotype.Service;

@Service
public class CentroComunitarioServiceImpl implements CentroComunitarioService {


    private final CentroComunitarioRepository centroComunitarioRepository;
    private final NotificacaoService notificacaoService;

    public CentroComunitarioServiceImpl(CentroComunitarioRepository centroComunitarioRepository, NotificacaoService notificacaoService) {
        this.centroComunitarioRepository = centroComunitarioRepository;
        this.notificacaoService = notificacaoService;
    }

    public CentroComunitario criarCentroComunitario(CentroComunitarioDTO centroDTO) {
        Recursos recursos = new Recursos(
                centroDTO.getRecursos().getMedicos(),
                centroDTO.getRecursos().getVoluntarios(),
                centroDTO.getRecursos().getKitsSuprimentos(),
                centroDTO.getRecursos().getVeiculos(),
                centroDTO.getRecursos().getCestasBasicas()
        );

        CentroComunitario centroComunitario = new CentroComunitario(
                null, // id gerado automaticamente pelo MongoDB
                centroDTO.getNome(),
                centroDTO.getEndereco(),
                centroDTO.getLocalizacao(),
                centroDTO.getCapacidadeMaxima(),
                centroDTO.getPessoasOcupando(),
                recursos
        );

        return centroComunitarioRepository.save(centroComunitario);
    }

    public boolean atualizarOcupacao(String id, int novaOcupacao) {
        CentroComunitario centro = centroComunitarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Centro não encontrado"));

        if (novaOcupacao > centro.getCapacidadeMaxima()) {
            throw new IllegalArgumentException("Ocupação excede a capacidade máxima.");
        }

        centro.setPessoasOcupando(novaOcupacao);
        centroComunitarioRepository.save(centro);

        if (novaOcupacao >= centro.getCapacidadeMaxima()) {
            notificacaoService.enviarNotificacao("Centro " + centro.getNome() + " atingiu a capacidade máxima.");
            return true;
        }

        return false;
    }
}