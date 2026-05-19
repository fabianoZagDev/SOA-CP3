package br.com.ford.vinshare.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoRequestDTO {

    @NotBlank(message = "Observação do contato é obrigatória")
    private String observacao;
}
