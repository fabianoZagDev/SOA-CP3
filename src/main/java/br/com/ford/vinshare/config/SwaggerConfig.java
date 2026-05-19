package br.com.ford.vinshare.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI fordVinShareOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ford VIN Share API")
                        .description("""
                                API REST para a solução Ford VIN Share — aumenta a retenção de clientes
                                na rede oficial de pós-venda por meio de análise preditiva de leads.

                                **Arquitetura SOA — 3 serviços independentes:**
                                - **Serviço de Análise de Dados** (`/veiculos`) — gerencia veículos e histórico
                                - **Serviço de Leads Preditivos** (`/leads`) — score de propensão e risco
                                - **Serviço de Comunicação** (`/leads/{id}/contato`) — registra interações
                                - **Dashboard** (`/dashboard/metricas`) — consolida métricas de VIN Share
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Grupo FIAP — Challenge Ford")
                                .email("grupo@fiap.com.br")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Desenvolvimento local")
                ));
    }
}
