package br.com.ford.vinshare.controller;

import br.com.ford.vinshare.dto.ApiResponse;
import br.com.ford.vinshare.dto.ContatoRequestDTO;
import br.com.ford.vinshare.dto.LeadDTO;
import br.com.ford.vinshare.model.Lead;
import br.com.ford.vinshare.service.ComunicacaoService;
import br.com.ford.vinshare.service.LeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leads")
@RequiredArgsConstructor
@Tag(name = "Leads Preditivos", description = "Serviço de Leads Preditivos — veículos com alta probabilidade de retorno ou risco de evasão")
public class LeadController {

    private final LeadService leadService;
    private final ComunicacaoService comunicacaoService;

    @GetMapping
    @Operation(summary = "Listar leads preditivos",
               description = "Retorna todos os leads ordenados por score de propensão (maior primeiro). "
                           + "Filtro opcional por status: NOVO, EM_CONTATO, CONVERTIDO, PERDIDO")
    public ResponseEntity<ApiResponse<List<LeadDTO>>> listarLeads(
            @Parameter(description = "Filtrar por status do lead")
            @RequestParam(required = false) Lead.StatusLead status) {

        List<LeadDTO> leads = (status != null)
                ? leadService.listarPorStatus(status)
                : leadService.listarTodos();

        return ResponseEntity.ok(ApiResponse.ok(leads));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar lead por ID", description = "Retorna os detalhes de um lead específico")
    public ResponseEntity<ApiResponse<LeadDTO>> buscarPorId(
            @Parameter(description = "ID do lead") @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(leadService.buscarPorId(id)));
    }

    @PostMapping("/{id}/contato")
    @Operation(summary = "Registrar contato com cliente",
               description = "Registra uma interação com o cliente associado ao lead. "
                           + "Atualiza data do último contato e muda status de NOVO para EM_CONTATO automaticamente.")
    public ResponseEntity<ApiResponse<LeadDTO>> registrarContato(
            @Parameter(description = "ID do lead") @PathVariable Long id,
            @Valid @RequestBody ContatoRequestDTO request) {
        LeadDTO leadAtualizado = comunicacaoService.registrarContato(id, request);
        return ResponseEntity.ok(
                ApiResponse.ok("Contato registrado com sucesso", leadAtualizado));
    }
}
