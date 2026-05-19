package br.com.ford.vinshare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_HISTORICO_MANUTENCAO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoManutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico")
    @SequenceGenerator(name = "seq_historico", sequenceName = "SEQ_HISTORICO_MANUTENCAO", allocationSize = 1)
    @Column(name = "ID_HISTORICO")
    private Long id;

    @Column(name = "DT_MANUTENCAO", nullable = false)
    private LocalDate dataManutencao;

    @Column(name = "DS_TIPO_SERVICO", nullable = false, length = 100)
    private String tipoServico;

    @Column(name = "VL_SERVICO", precision = 10, scale = 2)
    private BigDecimal valorServico;

    @Column(name = "DS_CONCESSIONARIA", length = 150)
    private String concessionaria;

    @Column(name = "DS_OBSERVACAO", length = 500)
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VEICULO", nullable = false)
    private Veiculo veiculo;
}
