package br.com.ford.vinshare.controller;

import br.com.ford.vinshare.dto.ApiResponse;
import br.com.ford.vinshare.dto.HistoricoManutencaoDTO;
import br.com.ford.vinshare.dto.VeiculoDTO;
import br.com.ford.vinshare.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/veiculos")
@RequiredArgsConstructor
@Tag(name = "Veículos", description = "Serviço de Análise de Dados — gerencia veículos e histórico de manutenção")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping
    @Operation(summary = "Listar veículos", description = "Retorna todos os veículos cadastrados na concessionária")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<ApiResponse<List<VeiculoDTO>>> listarVeiculos() {
        List<VeiculoDTO> veiculos = veiculoService.listarTodos();
        return ResponseEntity.ok(ApiResponse.ok(veiculos));
    }

    @GetMapping("/{vin}")
    @Operation(summary = "Buscar veículo por VIN", description = "Retorna os detalhes de um veículo específico pelo número VIN")
    public ResponseEntity<ApiResponse<VeiculoDTO>> buscarPorVin(
            @Parameter(description = "Número VIN do veículo (17 caracteres)", example = "9BWZZZ377VT004251")
            @PathVariable String vin) {
        VeiculoDTO veiculo = veiculoService.buscarPorVin(vin);
        return ResponseEntity.ok(ApiResponse.ok(veiculo));
    }

    @GetMapping("/{vin}/historico")
    @Operation(summary = "Histórico de manutenção", description = "Retorna o histórico completo de manutenções de um veículo pelo VIN, ordenado do mais recente")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Histórico retornado com sucesso"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<ApiResponse<List<HistoricoManutencaoDTO>>> buscarHistorico(
            @Parameter(description = "Número VIN do veículo", example = "9BWZZZ377VT004251")
            @PathVariable String vin) {
        List<HistoricoManutencaoDTO> historico = veiculoService.buscarHistoricoPorVin(vin);
        return ResponseEntity.ok(ApiResponse.ok(historico));
    }
}
