package dsl.jackson.igor.SGCC.model.service.Implementation;

import dsl.jackson.igor.SGCC.model.dto.CentroRecursoDTO;
import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;
import dsl.jackson.igor.SGCC.model.entity.CentroRecursos;
import dsl.jackson.igor.SGCC.model.entity.Intercambio;
import dsl.jackson.igor.SGCC.model.entity.Recursos;
import dsl.jackson.igor.SGCC.model.repository.CentroComunitarioRepository;
import dsl.jackson.igor.SGCC.model.repository.CentroRecursosRepository;
import dsl.jackson.igor.SGCC.model.repository.IntercambioRepository;
import dsl.jackson.igor.SGCC.model.repository.RecursosRepository;
import dsl.jackson.igor.SGCC.model.service.IntercambioService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IntercambioServiceImpl implements IntercambioService {

    IntercambioRepository intercambioRepository;
    CentroComunitarioRepository centroComunitarioRepository;
    RecursosRepository recursosRepository;
    CentroRecursosRepository centroRecursosRepository;

    public IntercambioServiceImpl(IntercambioRepository intercambioRepository,
                                  CentroComunitarioRepository centroComunitarioRepository,
                                  RecursosRepository recursosRepository,
                                  CentroRecursosRepository centroRecursosRepository){
        this.intercambioRepository = intercambioRepository;
        this.centroComunitarioRepository = centroComunitarioRepository;
        this.recursosRepository = recursosRepository;
        this.centroRecursosRepository = centroRecursosRepository;
    }

    @Override
   public boolean realizarIntercambio(String idCentroOrigem, String idCentroDestino, List<CentroRecursoDTO> recursosCentroOrigem, List<CentroRecursoDTO> recursosCentroDestino) {
        CentroComunitario centroOrigem = centroComunitarioRepository.findById(idCentroOrigem).orElse(null);
        CentroComunitario centroDestino = centroComunitarioRepository.findById(idCentroDestino).orElse(null);

        if (centroOrigem == null || centroDestino == null || recursosCentroOrigem.isEmpty() || recursosCentroDestino.isEmpty()) {
            return false;
        }


        int pontosOrigem = 0;
        int pontosDestino = 0;
        double ocupacaoOrigem = (double) centroOrigem.getPessoasOcupando() / centroOrigem.getCapacidadeMaxima();

        pontosOrigem = calculaPontos(pontosOrigem,recursosCentroOrigem);
        pontosDestino = calculaPontos(pontosDestino,recursosCentroDestino);
        verificaQuantSuficientes(recursosCentroOrigem,centroOrigem);
        verificaQuantSuficientes(recursosCentroDestino,centroDestino);

        if (!verificaPontoOcupacao(pontosOrigem,pontosDestino,ocupacaoOrigem)){
            throw new RuntimeException("quantidade de pontos dos centros diverge.");
        }


        List<CentroRecursos>centroRecursosOrigemList = new ArrayList<>();
        List<CentroRecursos>centroRecursosDestinoList = new ArrayList<>();

        atualizarRecursos(recursosCentroOrigem,recursosCentroDestino, centroOrigem,centroDestino, centroRecursosOrigemList,centroRecursosDestinoList);

        // Adiciona o registro ao intercâmbio
        Intercambio intercambio = new Intercambio(
                idCentroOrigem,
                idCentroDestino,
                centroRecursosOrigemList,
                centroRecursosDestinoList,
                pontosOrigem,//origem ou destinos são iguais
                LocalDateTime.now()
        );
        intercambioRepository.save(intercambio);

        return true;
    }

    private void atualizarRecursos(List<CentroRecursoDTO> recursosCentroAList,
                                   List<CentroRecursoDTO> recursosCentroBList,
                                   CentroComunitario centroA,
                                   CentroComunitario centroB,
                                   List<CentroRecursos> centroRecursosOrigemList,
                                   List<CentroRecursos> centroRecursosDestinoList) {
        for (CentroRecursoDTO centroRecursoDTO : recursosCentroAList){

            Recursos recurso = recursosRepository.findByCodRecurso(centroRecursoDTO.getCodRecurso());

            CentroRecursos centroRecursoA = centroRecursosRepository.findByIdRecursoCentro(centroA.getId(),recurso.getId());
            centroRecursoA.setQuantidadeRecurso(centroRecursoA.getQuantidadeRecurso() - centroRecursoDTO.getQuantidadeRecurso());

            CentroRecursos centroRecursoB = centroRecursosRepository.findByIdRecursoCentro(centroB.getId(),recurso.getId());
            centroRecursoB.setQuantidadeRecurso(centroRecursoB.getQuantidadeRecurso() + centroRecursoDTO.getQuantidadeRecurso());

            centroRecursosRepository.save(centroRecursoA);
            centroRecursosRepository.save(centroRecursoB);

            CentroRecursos centroRecursoInter = new CentroRecursos();
            centroRecursoInter.setId(centroRecursoA.getId());
            centroRecursoInter.setIdCentro(centroRecursoA.getIdCentro());
            centroRecursoInter.setIdRecurso(centroRecursoA.getIdRecurso());
            centroRecursoInter.setQuantidadeRecurso(centroRecursoDTO.getQuantidadeRecurso());

            centroRecursosOrigemList.add(centroRecursoInter);
        }
        for (CentroRecursoDTO centroRecursoDTO : recursosCentroBList){
            Recursos recurso = recursosRepository.findByCodRecurso(centroRecursoDTO.getCodRecurso());

            CentroRecursos centroRecursoB = centroRecursosRepository.findByIdRecursoCentro(centroB.getId(),recurso.getId());
            centroRecursoB.setQuantidadeRecurso(centroRecursoB.getQuantidadeRecurso() - centroRecursoDTO.getQuantidadeRecurso());

            CentroRecursos centroRecursoA = centroRecursosRepository.findByIdRecursoCentro(centroA.getId(),recurso.getId());
            centroRecursoA.setQuantidadeRecurso(centroRecursoA.getQuantidadeRecurso() + centroRecursoDTO.getQuantidadeRecurso());

            centroRecursosRepository.save(centroRecursoB);
            centroRecursosRepository.save(centroRecursoA);

            CentroRecursos centroRecursoInter = new CentroRecursos();
            centroRecursoInter.setId(centroRecursoB.getId());
            centroRecursoInter.setIdCentro(centroRecursoB.getIdCentro());
            centroRecursoInter.setIdRecurso(centroRecursoB.getIdRecurso());
            centroRecursoInter.setQuantidadeRecurso(centroRecursoDTO.getQuantidadeRecurso());

            centroRecursosDestinoList.add(centroRecursoInter);
        }
    }

    private void verificaQuantSuficientes(List<CentroRecursoDTO>recursosCentro, CentroComunitario centro){
        for (CentroRecursoDTO centroRecursoDTO : recursosCentro){
            Recursos recurso = recursosRepository.findByCodRecurso(centroRecursoDTO.getCodRecurso());
                // Verifica se a quantidade é suficiente para a troca
                CentroRecursos recursos = centroRecursosRepository.findByIdRecursoCentro(centro.getId(),recurso.getId());
                if (recursos.getQuantidadeRecurso()<centroRecursoDTO.getQuantidadeRecurso()){
                    throw new RuntimeException("quantidade de recurso insuficiente para o intercambio");
                }
        }
    }

    private int calculaPontos(int pontos, List<CentroRecursoDTO>recursosCentro){
        for (CentroRecursoDTO centroRecursoDTO : recursosCentro){
            Recursos recurso = recursosRepository.findByCodRecurso(centroRecursoDTO.getCodRecurso());
            pontos = pontos + recurso.getPontos();
        }
        return pontos;
    }

    private boolean verificaPontoOcupacao(int pontosOrigem, int pontosDestino, double ocupacaoOrigem){
        if (pontosOrigem != pontosDestino){
            // Verifica se a ocupação é maior que 90% para o centro origem
            return ocupacaoOrigem > 0.90;
        }
        return true;
    }

    @Override
    public List<Intercambio> listarIntercambios() {
        return intercambioRepository.findAll();
    }
}