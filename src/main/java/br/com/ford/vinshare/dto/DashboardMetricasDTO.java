package br.com.ford.vinshare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardMetricasDTO {

    private Long totalVeiculos;
    private Long totalClientes;
    private Long totalLeads;
    private Long leadsNovos;
    private Long leadsEmContato;
    private Long leadsConvertidos;
    private Long leadsPerdidos;
    private Double taxaRetencao;         // % veículos que retornaram à rede
    private Double scoremedioLeads;      // score médio de propensão
    private Long totalManutencoesUltimos90Dias;
}
