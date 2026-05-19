package br.com.ford.vinshare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TB_VEICULO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veiculo")
    @SequenceGenerator(name = "seq_veiculo", sequenceName = "SEQ_VEICULO", allocationSize = 1)
    @Column(name = "ID_VEICULO")
    private Long id;

    @NotBlank
    @Column(name = "NR_VIN", unique = true, nullable = false, length = 17)
    private String vin;

    @Column(name = "DS_MODELO", nullable = false, length = 100)
    private String modelo;

    @Column(name = "NR_ANO")
    private Integer ano;

    @Column(name = "DS_COR", length = 50)
    private String cor;

    @Column(name = "NR_KM_ATUAL")
    private Integer kmAtual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistoricoManutencao> historicos;

    @OneToOne(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private Lead lead;
}
