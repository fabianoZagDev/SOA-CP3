package br.com.ford.vinshare.service;

import br.com.ford.vinshare.dto.DashboardMetricasDTO;
import br.com.ford.vinshare.model.Lead;
import br.com.ford.vinshare.repository.ClienteRepository;
import br.com.ford.vinshare.repository.HistoricoManutencaoRepository;
import br.com.ford.vinshare.repository.LeadRepository;
import br.com.ford.vinshare.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Serviço de Dashboard / Métricas (SOA)
 * Consolida dados de múltiplos serviços para o painel da concessionária.
 * Consome VeiculoService, LeadService e dados de manutenção de forma reutilizável.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;
    private final LeadRepository leadRepository;
    private final HistoricoManutencaoRepository historicoRepository;

    public DashboardMetricasDTO obterMetricas() {
        log.info("Calculando métricas consolidadas do dashboard");

        long totalVeiculos     = veiculoRepository.count();
        long totalClientes     = clienteRepository.count();
        long totalLeads        = leadRepository.count();
        long leadsNovos        = leadRepository.countByStatus(Lead.StatusLead.NOVO);
        long leadsEmContato    = leadRepository.countByStatus(Lead.StatusLead.EM_CONTATO);
        long leadsConvertidos  = leadRepository.countByStatus(Lead.StatusLead.CONVERTIDO);
        long leadsPerdidos     = leadRepository.countByStatus(Lead.StatusLead.PERDIDO);
        Double scoreMedio      = leadRepository.avgScorePropensao();

        LocalDate noventa = LocalDate.now().minusDays(90);
        long manutencoesRecentes = historicoRepository.countManutencoesDesde(noventa);

        double taxaRetencao = totalVeiculos > 0
                ? Math.round((double) leadsConvertidos / totalVeiculos * 100 * 100.0) / 100.0
                : 0.0;

        return DashboardMetricasDTO.builder()
                .totalVeiculos(totalVeiculos)
                .totalClientes(totalClientes)
                .totalLeads(totalLeads)
                .leadsNovos(leadsNovos)
                .leadsEmContato(leadsEmContato)
                .leadsConvertidos(leadsConvertidos)
                .leadsPerdidos(leadsPerdidos)
                .taxaRetencao(taxaRetencao)
                .scoremedioLeads(scoreMedio != null ? Math.round(scoreMedio * 100.0) / 100.0 : 0.0)
                .totalManutencoesUltimos90Dias(manutencoesRecentes)
                .build();
    }
}
