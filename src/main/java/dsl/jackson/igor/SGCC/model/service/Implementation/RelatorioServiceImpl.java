package dsl.jackson.igor.SGCC.model.service.Implementation;

import dsl.jackson.igor.SGCC.model.dto.RecursoSomaDTO;
import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;
import dsl.jackson.igor.SGCC.model.entity.CentroRecursos;
import dsl.jackson.igor.SGCC.model.entity.Intercambio;
import dsl.jackson.igor.SGCC.model.entity.Recursos;
import dsl.jackson.igor.SGCC.model.repository.CentroComunitarioRepository;
import dsl.jackson.igor.SGCC.model.repository.CentroRecursosRepository;
import dsl.jackson.igor.SGCC.model.repository.IntercambioRepository;
import dsl.jackson.igor.SGCC.model.repository.RecursosRepository;
import dsl.jackson.igor.SGCC.model.service.RelatorioService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

@Service
public class RelatorioServiceImpl implements RelatorioService {

    CentroComunitarioRepository centroComunitarioRepository;
    CentroRecursosRepository centroRecursosRepository;
    RecursosRepository recursosRepository;
    IntercambioRepository intercambioRepository;

    public RelatorioServiceImpl(CentroComunitarioRepository centroComunitarioRepository,
                                CentroRecursosRepository centroRecursosRepository,
                                RecursosRepository recursosRepository,
                                IntercambioRepository intercambioRepository){
        this.centroComunitarioRepository = centroComunitarioRepository;
        this.centroRecursosRepository = centroRecursosRepository;
        this.recursosRepository = recursosRepository;
        this.intercambioRepository = intercambioRepository;
    }


    @Override
    public byte[] gerarRelatorioOcupacao() {
        try (PDDocument document = new PDDocument()) {
            // Cria uma nova página
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

        // Configurar largura e altura da página A4
        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();

        // Iniciar fluxo de conteúdo
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Título do relatório - Centralizado
        String titulo = "Relatório de Ocupação";
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
        float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(titulo) / 1000 * 24;
        float titleXPosition = (pageWidth - titleWidth) / 2;
        contentStream.newLineAtOffset(titleXPosition, 750);
        contentStream.showText(titulo);
        contentStream.endText();

        // Definir posições iniciais para a tabela
        float tableTopY = 700;
        float tableMargin = 20;
        float tableWidth = pageWidth - 2 * tableMargin;
        float yPosition = tableTopY;

        // Largura de cada coluna
        float[] columnWidths = {tableWidth / 4, tableWidth / 4, tableWidth / 4, tableWidth / 4};

        // Tabela - Cabeçalhos
        String[] cabecalhos = {"Centro Comunitário", "Capacidade Máxima", "Ocupação Atual", "Ocupação(%)"};
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

        // Desenhar cabeçalhos centralizados
        for (int i = 0; i < cabecalhos.length; i++) {
            contentStream.beginText();
            float textWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(cabecalhos[i]) / 1000 * 12;
            float textX = tableMargin + (i * columnWidths[i]) + (columnWidths[i] - textWidth) / 2;
            contentStream.newLineAtOffset(textX, yPosition);
            contentStream.showText(cabecalhos[i]);
            contentStream.endText();
        }

        // Desenhar linha abaixo do cabeçalho
        yPosition -= 20; // Ajustar a posição da linha
        contentStream.moveTo(tableMargin, yPosition);
        contentStream.lineTo(pageWidth - tableMargin, yPosition);
        contentStream.stroke();

        // Dados da tabela - Exemplo

        List<CentroComunitario> centros = centroComunitarioRepository.findAll();
        List<CentroComunitario> centrosOcupacao = new ArrayList<>();
        for (CentroComunitario centro : centros){
            if ((double) (centro.getPessoasOcupando() * 100) / centro.getCapacidadeMaxima() > 90){
                centrosOcupacao.add(centro);
            }
        }
        String[][] dados = criarArrayDeDados(centrosOcupacao);

        contentStream.setFont(PDType1Font.HELVETICA, 12);
        yPosition -= 15; // Ajustar para a próxima linha

        // Desenhar dados
        for (String[] linha : dados) {
            int count = 0;
            for (int i = 0; i < linha.length; i++) {
                contentStream.beginText();
                float textWidth = PDType1Font.HELVETICA.getStringWidth(linha[i]) / 1000 * 12;
                float textX = tableMargin + (i * columnWidths[i]) + (columnWidths[i] - textWidth) / 2;
                contentStream.newLineAtOffset(textX, yPosition);
                contentStream.showText(linha[i]);
                contentStream.endText();
            }

            // Desenhar linha após cada registro
            yPosition -= 20;
            contentStream.moveTo(tableMargin, yPosition);
            contentStream.lineTo(pageWidth - tableMargin, yPosition);
            contentStream.stroke();
            yPosition -= 15; // Ajustar para a próxima linha
        }


        // Rodapé com a data e numeração de página - Centralizado
        contentStream.setFont(PDType1Font.HELVETICA, 10);
        Date dataAtual = new Date();
        // Define o formato de data brasileiro
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        // Formata a data atual
        String dataFormatada = formatoBrasileiro.format(dataAtual);

        float footerWidth = PDType1Font.HELVETICA.getStringWidth(dataFormatada) / 1000 * 10;
        float footerX = (pageWidth - footerWidth) / 2;
        contentStream.beginText();
        contentStream.newLineAtOffset(footerX, 50);
        contentStream.showText(dataFormatada);
        contentStream.endText();

        String pagina = "Página 1";
        float paginaWidth = PDType1Font.HELVETICA.getStringWidth(pagina) / 1000 * 10;
        float paginaX = (pageWidth - paginaWidth) / 2;
        contentStream.beginText();
        contentStream.newLineAtOffset(paginaX, 35);
        contentStream.showText(pagina);
        contentStream.endText();

        // Fechar fluxo de conteúdo
        contentStream.close();

            // Cria um OutputStream para armazenar o PDF
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            document.close();

            // Converte o OutputStream para um array de bytes

            return out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public byte[] gerarRelatorioRecursos() {
        try (PDDocument document = new PDDocument()) {
            // Cria uma nova página
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

        // Configurar largura e altura da página A4
        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();

        // Iniciar fluxo de conteúdo
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Título do relatório - Centralizado
        String titulo = "Relatório de Recursos";
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
        float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(titulo) / 1000 * 24;
        float titleXPosition = (pageWidth - titleWidth) / 2;
        contentStream.newLineAtOffset(titleXPosition, 750);
        contentStream.showText(titulo);
        contentStream.endText();

        // Definir posições iniciais para a tabela
        float tableTopY = 700;
        float tableMargin = 20;
        float tableWidth = pageWidth - 2 * tableMargin;
        float yPosition = tableTopY;

        // Largura de cada coluna
        float[] columnWidths = {tableWidth / 2, tableWidth / 2};

        // Tabela - Cabeçalhos
        String[] cabecalhos = {"Recurso", "Média de recursos"};
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

        // Desenhar cabeçalhos centralizados
        for (int i = 0; i < cabecalhos.length; i++) {
            contentStream.beginText();
            float textWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(cabecalhos[i]) / 1000 * 12;
            float textX = tableMargin + (i * columnWidths[i]) + (columnWidths[i] - textWidth) / 2;
            contentStream.newLineAtOffset(textX, yPosition);
            contentStream.showText(cabecalhos[i]);
            contentStream.endText();
        }

        // Desenhar linha abaixo do cabeçalho
        yPosition -= 20; // Ajustar a posição da linha
        contentStream.moveTo(tableMargin, yPosition);
        contentStream.lineTo(pageWidth - tableMargin, yPosition);
        contentStream.stroke();

        // Dados da tabela - Exemplo

        List<CentroComunitario> centros = centroComunitarioRepository.findAll();
        List<RecursoSomaDTO> recursoSomaDTOList = centroRecursosRepository.somarQuantidadePorIdRecurso();

        String[][] dados = criarArrayDeRecurso(recursoSomaDTOList,centros.size());

        contentStream.setFont(PDType1Font.HELVETICA, 12);
        yPosition -= 15; // Ajustar para a próxima linha

        // Desenhar dados
        for (String[] linha : dados) {
            int count = 0;
            for (int i = 0; i < linha.length; i++) {
                contentStream.beginText();
                float textWidth = PDType1Font.HELVETICA.getStringWidth(linha[i]) / 1000 * 12;
                float textX = tableMargin + (i * columnWidths[i]) + (columnWidths[i] - textWidth) / 2;
                contentStream.newLineAtOffset(textX, yPosition);
                contentStream.showText(linha[i]);
                contentStream.endText();
            }

            // Desenhar linha após cada registro
            yPosition -= 20;
            contentStream.moveTo(tableMargin, yPosition);
            contentStream.lineTo(pageWidth - tableMargin, yPosition);
            contentStream.stroke();
            yPosition -= 15; // Ajustar para a próxima linha
        }


        // Rodapé com a data e numeração de página - Centralizado
        contentStream.setFont(PDType1Font.HELVETICA, 10);
        Date dataAtual = new Date();
        // Define o formato de data brasileiro
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        // Formata a data atual
        String dataFormatada = formatoBrasileiro.format(dataAtual);

        float footerWidth = PDType1Font.HELVETICA.getStringWidth(dataFormatada) / 1000 * 10;
        float footerX = (pageWidth - footerWidth) / 2;
        contentStream.beginText();
        contentStream.newLineAtOffset(footerX, 50);
        contentStream.showText(dataFormatada);
        contentStream.endText();

        String pagina = "Página 1";
        float paginaWidth = PDType1Font.HELVETICA.getStringWidth(pagina) / 1000 * 10;
        float paginaX = (pageWidth - paginaWidth) / 2;
        contentStream.beginText();
        contentStream.newLineAtOffset(paginaX, 35);
        contentStream.showText(pagina);
        contentStream.endText();

        // Fechar fluxo de conteúdo
        contentStream.close();

            // Cria um OutputStream para armazenar o PDF
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            document.close();

            // Converte o OutputStream para um array de bytes

            return out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[][] criarArrayDeDados(List<CentroComunitario> centros) {
        String[][] dados = new String[centros.size()][4]; // 3 colunas: Nome, Capacidade Máxima, Ocupação Atual, taxa de ocupação

        for (int i = 0; i < centros.size(); i++) {
            CentroComunitario centro = centros.get(i);
            dados[i][0] = centro.getNome();
            dados[i][1] = centro.getCapacidadeMaxima() + " Pessoas";
            dados[i][2] = centro.getPessoasOcupando() + " Pessoas";
            dados[i][3] = String.format("%.2f", (double) (centro.getPessoasOcupando() * 100) / centro.getCapacidadeMaxima()) +"%";
        }

        return dados;
    }
    public String[][] criarArrayDeRecurso(List<RecursoSomaDTO> recursos, int quantCentros) {
        String[][] dados = new String[recursos.size()][2]; // 2 colunas: Nome, quantidade

        for (int i = 0; i < recursos.size(); i++) {
            RecursoSomaDTO recursoSoma = recursos.get(i);
            Optional<Recursos> rec = recursosRepository.findById(recursoSoma.getIdRecurso());
            if (rec.isPresent()) {
                Recursos recurso = rec.get();
                dados[i][0] = recurso.getNome();
                dados[i][1] = String.format("%.2f", (double) (recursoSoma.getTotalQuantidade()) / quantCentros)+" Por centro comunitario";
            }
        }

        return dados;
    }



    public byte[] gerarRelatorioIntercambio(String dataHoraInicio) {
     try (PDDocument document = new PDDocument()) {
         PDPage page = new PDPage(PDRectangle.A4);
         document.addPage(page);

         PDPageContentStream contentStream = new PDPageContentStream(document, page);

         // Configurações básicas
         float margin = 20;
         float yStart = page.getMediaBox().getHeight() - margin;
         float yPosition = yStart;
         float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
         float rowHeight = 20;
         float contentHeight = 0;  // Altura total usada na página

         // Cabeçalhos da tabela
         String[] cabecalhosOrigem = {"RECURSOS ORIGEM", "QUANTIDADE"};
         String[] cabecalhosDestino = {"RECURSOS DESTINO", "QUANTIDADE"};
         float[] columnWidths = {tableWidth / 3, tableWidth / 3, tableWidth / 3, tableWidth / 3};

         // Definir título
         String titulo = "Relatório de Intercâmbio de Recursos";
         contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
         float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(titulo) / 1000 * 18;
         float titleXPosition = (page.getMediaBox().getWidth() - titleWidth) / 2;
         contentStream.beginText();
         contentStream.newLineAtOffset(titleXPosition, yPosition);
         contentStream.showText(titulo);
         contentStream.endText();
         yPosition -= 40;
         contentHeight += 40;

         // Desenhar linha abaixo do título
         contentStream.setStrokingColor(Color.RED);
         contentStream.moveTo(margin, yPosition);
         contentStream.lineTo(page.getMediaBox().getWidth() - margin, yPosition);
         contentStream.stroke();
         yPosition -= rowHeight;
         contentHeight += rowHeight;

         // Dados da tabela
                 // Filtra os intercâmbios por data, se fornecida
         List<Intercambio> intercambios;
         LocalDateTime dataHoraFim = LocalDateTime.now();
         if (dataHoraInicio != null) {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
             LocalDateTime dataHoraInicioParsed = LocalDateTime.parse(dataHoraInicio, formatter);
             intercambios = intercambioRepository.findByDataHoraBetween(dataHoraInicioParsed, dataHoraFim);
         } else {
             intercambios = intercambioRepository.findAll();
         }
         contentStream.setFont(PDType1Font.HELVETICA, 12);

         for (Intercambio intercambio : intercambios) {
             if (contentHeight + rowHeight * 10 > page.getMediaBox().getHeight()) {  // Verifica se a página tem espaço para mais intercâmbios
                 contentStream.close();
                 page = new PDPage(PDRectangle.A4);
                 document.addPage(page);
                 contentStream = new PDPageContentStream(document, page);
                 yPosition = yStart;
                 contentHeight = 0;
             }

             // Nome dos centros

             Optional<CentroComunitario> centroOrigem = centroComunitarioRepository.findById(intercambio.getIdCentroOrigem());
             Optional<CentroComunitario> centroDestino = centroComunitarioRepository.findById(intercambio.getIdCentroDestino());
             CentroComunitario centroComumOrigem = new CentroComunitario();
             CentroComunitario centroComumDestino = new CentroComunitario();
             if (centroOrigem.isPresent()){
                  centroComumOrigem = centroOrigem.get();
             }
             if (centroDestino.isPresent()){
                 centroComumDestino = centroDestino.get();
             }
             String nomeCentroOrigem = "Centro Origem: "+centroComumOrigem.getNome();
             String nomeCentroDestino = "Centro Destino: "+centroComumDestino.getNome();



             contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
             contentStream.beginText();
             contentStream.newLineAtOffset(margin, yPosition);
             contentStream.showText(nomeCentroOrigem);
             contentStream.endText();

             contentStream.beginText();
             contentStream.newLineAtOffset(page.getMediaBox().getWidth() / 2 + margin, yPosition);
             contentStream.showText(nomeCentroDestino);
             contentStream.endText();
             yPosition -= rowHeight;
             contentHeight += rowHeight;

             // Desenhar linha abaixo dos nomes dos centros
             contentStream.setStrokingColor(Color.RED);
             contentStream.moveTo(margin, yPosition);
             contentStream.lineTo(page.getMediaBox().getWidth() - margin, yPosition);
             contentStream.stroke();
             yPosition -= rowHeight;
             contentHeight += rowHeight;

             // Desenhar cabeçalhos da tabela
             for (int i = 0; i < cabecalhosOrigem.length; i++) {
                 contentStream.beginText();
                 contentStream.newLineAtOffset(margin + (i * columnWidths[i]), yPosition);
                 contentStream.showText(cabecalhosOrigem[i]);
                 contentStream.endText();
             }

             for (int i = 0; i < cabecalhosDestino.length; i++) {
                 contentStream.beginText();
                 contentStream.newLineAtOffset(page.getMediaBox().getWidth() / 2 + (i * columnWidths[i]), yPosition);
                 contentStream.showText(cabecalhosDestino[i]);
                 contentStream.endText();
             }
             yPosition -= rowHeight;
             contentHeight += rowHeight;

             // Desenhar linha abaixo dos cabeçalhos da tabela
             contentStream.setStrokingColor(Color.BLACK);
             contentStream.moveTo(margin, yPosition);
             contentStream.lineTo(page.getMediaBox().getWidth() - margin, yPosition);
             contentStream.stroke();
             yPosition -= rowHeight;
             contentHeight += rowHeight;

             // Determine o maior tamanho entre as listas de origem e destino
            int maxSize = Math.max(intercambio.getRecursosOrigem().size(), intercambio.getRecursosDestino().size());

            // Itera sobre os recursos
            for (int i = 0; i < maxSize; i++) {
                // Pega o recurso de origem se existir
                CentroRecursos CenRecursoOrigem = i < intercambio.getRecursosOrigem().size() ? intercambio.getRecursosOrigem().get(i) : null;

                // Pega o recurso de destino se existir
                CentroRecursos CenRecursoDestino = i < intercambio.getRecursosDestino().size() ? intercambio.getRecursosDestino().get(i) : null;

                String nomeRecursoOrigem = "";
                String nomeRecursoDestino = "";

                // Verifica e busca o nome do recurso de origem
                if (CenRecursoOrigem != null) {
                    Optional<Recursos> recursosO = recursosRepository.findById(CenRecursoOrigem.getIdRecurso());
                    if (recursosO.isPresent()) {
                        nomeRecursoOrigem = recursosO.get().getNome();
                    }
                }

                // Verifica e busca o nome do recurso de destino
                if (CenRecursoDestino != null) {
                    Optional<Recursos> recursosD = recursosRepository.findById(CenRecursoDestino.getIdRecurso());
                    if (recursosD.isPresent()) {
                        nomeRecursoDestino = recursosD.get().getNome();
                    }
                }

                // Escreve os dados do recurso de origem
                if (CenRecursoOrigem != null) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText(nomeRecursoOrigem);
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin + columnWidths[1], yPosition);
                    contentStream.showText(String.valueOf(CenRecursoOrigem.getQuantidadeRecurso()));
                    contentStream.endText();
                }

                // Escreve os dados do recurso de destino (se existir)
                if (CenRecursoDestino != null) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(page.getMediaBox().getWidth() / 2, yPosition);
                    contentStream.showText(nomeRecursoDestino);
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.newLineAtOffset(page.getMediaBox().getWidth() / 2 + columnWidths[1], yPosition);
                    contentStream.showText(String.valueOf(CenRecursoDestino.getQuantidadeRecurso()));
                    contentStream.endText();
                }

                // Atualiza a posição vertical para a próxima linha
                yPosition -= rowHeight;
                contentHeight += rowHeight;
            }


             // Desenhar linha após os dados
             contentStream.setStrokingColor(Color.RED);
             contentStream.moveTo(margin, yPosition);
             contentStream.lineTo(page.getMediaBox().getWidth() - margin, yPosition);
             contentStream.stroke();
             yPosition -= rowHeight;
             contentHeight += rowHeight;

             // Adiciona a data do intercâmbio
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = intercambio.getDataHora().format(formatter);
             contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
             contentStream.beginText();
             contentStream.newLineAtOffset(margin, yPosition);
             contentStream.showText("Data do Intercâmbio: " + dataFormatada);
             contentStream.endText();

             yPosition -= rowHeight;
             contentHeight += rowHeight;

             // Desenhar linha final
             contentStream.setStrokingColor(Color.BLACK);
             contentStream.moveTo(margin, yPosition);
             contentStream.lineTo(page.getMediaBox().getWidth() - margin, yPosition);
             contentStream.stroke();
             yPosition -= 100;
             contentHeight += rowHeight;
         }

         contentStream.close();
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         document.save(out);
         document.close();

         return out.toByteArray();

     } catch (IOException e) {
         e.printStackTrace();
     }
     return null;
 }




}