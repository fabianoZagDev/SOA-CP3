package br.com.ford.vinshare.controller;

import br.com.ford.vinshare.dto.ApiResponse;
import br.com.ford.vinshare.dto.DashboardMetricasDTO;
import br.com.ford.vinshare.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Métricas consolidadas de Service Share / VIN Share para a concessionária")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/metricas")
    @Operation(summary = "Métricas consolidadas",
               description = "Retorna o painel consolidado com totais de veículos, clientes, leads por status, "
                           + "taxa de retenção e score médio de propensão")
    public ResponseEntity<ApiResponse<DashboardMetricasDTO>> obterMetricas() {
        DashboardMetricasDTO metricas = dashboardService.obterMetricas();
        return ResponseEntity.ok(ApiResponse.ok(metricas));
    }
}
