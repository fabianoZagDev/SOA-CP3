package br.com.ford.vinshare.service;

import br.com.ford.vinshare.dto.HistoricoManutencaoDTO;
import br.com.ford.vinshare.dto.VeiculoDTO;
import br.com.ford.vinshare.exception.RecursoNaoEncontradoException;
import br.com.ford.vinshare.model.Veiculo;
import br.com.ford.vinshare.repository.HistoricoManutencaoRepository;
import br.com.ford.vinshare.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço de Análise de Dados (SOA)
 * Responsável por gerenciar veículos e histórico de manutenção.
 * Serviço independente e reutilizável.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final HistoricoManutencaoRepository historicoRepository;

    public List<VeiculoDTO> listarTodos() {
        log.info("Listando todos os veículos");
        return veiculoRepository.findAll()
                .stream()
                .map(VeiculoDTO::fromEntity)
                .toList();
    }

    public VeiculoDTO buscarPorVin(String vin) {
        log.info("Buscando veículo pelo VIN: {}", vin);
        Veiculo veiculo = veiculoRepository.findByVinWithCliente(vin)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Veículo com VIN '" + vin + "' não encontrado"));
        return VeiculoDTO.fromEntity(veiculo);
    }

    public List<HistoricoManutencaoDTO> buscarHistoricoPorVin(String vin) {
        log.info("Buscando histórico de manutenção para VIN: {}", vin);

        // Verifica se o veículo existe
        veiculoRepository.findByVin(vin)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Veículo com VIN '" + vin + "' não encontrado"));

        return historicoRepository.findByVeiculoVinOrderByDataManutencaoDesc(vin)
                .stream()
                .map(HistoricoManutencaoDTO::fromEntity)
                .toList();
    }

    public long contarTotalVeiculos() {
        return veiculoRepository.count();
    }
}
