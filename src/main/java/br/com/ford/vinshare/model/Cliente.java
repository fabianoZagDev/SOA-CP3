package br.com.ford.vinshare.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TB_CLIENTE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
    @SequenceGenerator(name = "seq_cliente", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @NotBlank
    @Column(name = "NM_CLIENTE", nullable = false, length = 150)
    private String nome;

    @Email
    @Column(name = "DS_EMAIL", unique = true, length = 150)
    private String email;

    @Column(name = "NR_TELEFONE", length = 20)
    private String telefone;

    @Column(name = "DS_CPF", unique = true, length = 14)
    private String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Veiculo> veiculos;
}
