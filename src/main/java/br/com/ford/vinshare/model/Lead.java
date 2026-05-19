package br.com.ford.vinshare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_LEAD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lead")
    @SequenceGenerator(name = "seq_lead", sequenceName = "SEQ_LEAD", allocationSize = 1)
    @Column(name = "ID_LEAD")
    private Long id;

    @Column(name = "NR_SCORE_PROPENSAO", nullable = false)
    private Double scorePropensao;

    @Enumerated(EnumType.STRING)
    @Column(name = "DS_STATUS", nullable = false, length = 30)
    private StatusLead status;

    @Column(name = "DS_RECOMENDACAO_ACAO", length = 500)
    private String recomendacaoAcao;

    @Column(name = "DS_MOTIVO_RISCO", length = 300)
    private String motivoRisco;

    @Column(name = "DT_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DT_ULTIMO_CONTATO")
    private LocalDateTime dataUltimoContato;

    @Column(name = "DS_OBSERVACAO_CONTATO", length = 500)
    private String observacaoContato;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VEICULO", nullable = false, unique = true)
    private Veiculo veiculo;

    public enum StatusLead {
        NOVO, EM_CONTATO, CONVERTIDO, PERDIDO
    }
}
