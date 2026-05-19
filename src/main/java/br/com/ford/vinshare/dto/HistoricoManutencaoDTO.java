package br.com.ford.vinshare.dto;

import br.com.ford.vinshare.model.HistoricoManutencao;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoricoManutencaoDTO {

    private Long id;
    private LocalDate dataManutencao;
    private String tipoServico;
    private BigDecimal valorServico;
    private String concessionaria;
    private String observacao;

    public static HistoricoManutencaoDTO fromEntity(HistoricoManutencao h) {
        return HistoricoManutencaoDTO.builder()
                .id(h.getId())
                .dataManutencao(h.getDataManutencao())
                .tipoServico(h.getTipoServico())
                .valorServico(h.getValorServico())
                .concessionaria(h.getConcessionaria() != null ? h.getConcessionaria() : "não disponível")
                .observacao(h.getObservacao())
                .build();
    }
}
