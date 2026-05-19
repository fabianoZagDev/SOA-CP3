package br.com.ford.vinshare.dto;

import br.com.ford.vinshare.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VeiculoDTO {

    private Long id;
    private String vin;
    private String modelo;
    private Integer ano;
    private String cor;
    private Integer kmAtual;
    private String nomeCliente;
    private String emailCliente;

    public static VeiculoDTO fromEntity(Veiculo v) {
        return VeiculoDTO.builder()
                .id(v.getId())
                .vin(v.getVin())
                .modelo(v.getModelo())
                .ano(v.getAno())
                .cor(v.getCor() != null ? v.getCor() : "não disponível")
                .kmAtual(v.getKmAtual())
                .nomeCliente(v.getCliente() != null ? v.getCliente().getNome() : null)
                .emailCliente(v.getCliente() != null ? v.getCliente().getEmail() : null)
                .build();
    }
}
