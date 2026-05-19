package br.com.ford.vinshare.service;

import br.com.ford.vinshare.dto.LeadDTO;
import br.com.ford.vinshare.exception.RecursoNaoEncontradoException;
import br.com.ford.vinshare.model.Lead;
import br.com.ford.vinshare.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço de Leads Preditivos (SOA)
 * Responsável por gerenciar e retornar leads gerados pelo modelo preditivo.
 * Serviço independente e reutilizável.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LeadService {

    private final LeadRepository leadRepository;

    public List<LeadDTO> listarTodos() {
        log.info("Listando todos os leads preditivos");
        return leadRepository.findAllWithVeiculoAndCliente()
                .stream()
                .map(LeadDTO::fromEntity)
                .toList();
    }

    public List<LeadDTO> listarPorStatus(Lead.StatusLead status) {
        log.info("Listando leads com status: {}", status);
        return leadRepository.findByStatusOrderByScorePropensaoDesc(status)
                .stream()
                .map(LeadDTO::fromEntity)
                .toList();
    }

    public LeadDTO buscarPorId(Long id) {
        log.info("Buscando lead por ID: {}", id);
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Lead com ID " + id + " não encontrado"));
        return LeadDTO.fromEntity(lead);
    }

    public Long contarPorStatus(Lead.StatusLead status) {
        return leadRepository.countByStatus(status);
    }

    public Double calcularScoreMedio() {
        Double avg = leadRepository.avgScorePropensao();
        return avg != null ? Math.round(avg * 100.0) / 100.0 : 0.0;
    }

    public long contarTotal() {
        return leadRepository.count();
    }
}
