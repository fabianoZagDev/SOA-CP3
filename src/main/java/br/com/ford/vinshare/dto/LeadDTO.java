package br.com.ford.vinshare.dto;

import br.com.ford.vinshare.model.Lead;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeadDTO {

    private Long id;
    private Double scorePropensao;
    private String status;
    private String recomendacaoAcao;
    private String motivoRisco;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimoContato;

    // Dados do veículo vinculado
    private String vin;
    private String modeloVeiculo;
    private String nomeCliente;
    private String emailCliente;
    private String telefoneCliente;

    public static LeadDTO fromEntity(Lead l) {
        return LeadDTO.builder()
                .id(l.getId())
                .scorePropensao(l.getScorePropensao())
                .status(l.getStatus().name())
                .recomendacaoAcao(l.getRecomendacaoAcao())
                .motivoRisco(l.getMotivoRisco() != null ? l.getMotivoRisco() : "não disponível")
                .dataCriacao(l.getDataCriacao())
                .dataUltimoContato(l.getDataUltimoContato())
                .vin(l.getVeiculo() != null ? l.getVeiculo().getVin() : null)
                .modeloVeiculo(l.getVeiculo() != null ? l.getVeiculo().getModelo() : null)
                .nomeCliente(l.getVeiculo() != null && l.getVeiculo().getCliente() != null
                        ? l.getVeiculo().getCliente().getNome() : null)
                .emailCliente(l.getVeiculo() != null && l.getVeiculo().getCliente() != null
                        ? l.getVeiculo().getCliente().getEmail() : null)
                .telefoneCliente(l.getVeiculo() != null && l.getVeiculo().getCliente() != null
                        ? l.getVeiculo().getCliente().getTelefone() : null)
                .build();
    }
}
