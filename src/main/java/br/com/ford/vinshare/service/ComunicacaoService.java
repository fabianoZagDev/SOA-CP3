package br.com.ford.vinshare.service;

import br.com.ford.vinshare.dto.ContatoRequestDTO;
import br.com.ford.vinshare.dto.LeadDTO;
import br.com.ford.vinshare.exception.RecursoNaoEncontradoException;
import br.com.ford.vinshare.model.Lead;
import br.com.ford.vinshare.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Serviço de Comunicação (SOA)
 * Responsável por registrar interações e contatos com clientes via leads.
 * Serviço independente e reutilizável.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ComunicacaoService {

    private final LeadRepository leadRepository;

    @Transactional
    public LeadDTO registrarContato(Long leadId, ContatoRequestDTO request) {
        log.info("Registrando contato para lead ID: {}", leadId);

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Lead com ID " + leadId + " não encontrado"));

        lead.setDataUltimoContato(LocalDateTime.now());
        lead.setObservacaoContato(request.getObservacao());

        // Atualiza status para EM_CONTATO se ainda estiver NOVO
        if (lead.getStatus() == Lead.StatusLead.NOVO) {
            lead.setStatus(Lead.StatusLead.EM_CONTATO);
        }

        Lead leadAtualizado = leadRepository.save(lead);
        log.info("Contato registrado com sucesso para lead ID: {}", leadId);
        return LeadDTO.fromEntity(leadAtualizado);
    }
}
