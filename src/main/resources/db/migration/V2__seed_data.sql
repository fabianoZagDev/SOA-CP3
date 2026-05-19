-- =========================================================
-- V2 — Dados iniciais para demonstração
-- =========================================================

-- ---------------------------------------------------------
-- Clientes
-- ---------------------------------------------------------
INSERT INTO TB_CLIENTE (ID_CLIENTE, NM_CLIENTE, DS_EMAIL, NR_TELEFONE, DS_CPF)
VALUES (SEQ_CLIENTE.NEXTVAL, 'Carlos Andrade', 'carlos.andrade@email.com', '(11) 98765-4321', '123.456.789-00');

INSERT INTO TB_CLIENTE (ID_CLIENTE, NM_CLIENTE, DS_EMAIL, NR_TELEFONE, DS_CPF)
VALUES (SEQ_CLIENTE.NEXTVAL, 'Ana Paula Souza', 'ana.souza@email.com', '(11) 91234-5678', '987.654.321-00');

INSERT INTO TB_CLIENTE (ID_CLIENTE, NM_CLIENTE, DS_EMAIL, NR_TELEFONE, DS_CPF)
VALUES (SEQ_CLIENTE.NEXTVAL, 'Roberto Lima', 'roberto.lima@email.com', '(21) 99876-5432', '456.123.789-00');

-- ---------------------------------------------------------
-- Veículos
-- ---------------------------------------------------------
INSERT INTO TB_VEICULO (ID_VEICULO, NR_VIN, DS_MODELO, NR_ANO, DS_COR, NR_KM_ATUAL, ID_CLIENTE)
VALUES (SEQ_VEICULO.NEXTVAL, '9BWZZZ377VT004251', 'Ford Ka', 2021, 'Prata', 35000, 1);

INSERT INTO TB_VEICULO (ID_VEICULO, NR_VIN, DS_MODELO, NR_ANO, DS_COR, NR_KM_ATUAL, ID_CLIENTE)
VALUES (SEQ_VEICULO.NEXTVAL, '9BWZZZ377VT009872', 'Ford Ranger', 2020, 'Branco', 78000, 1);

INSERT INTO TB_VEICULO (ID_VEICULO, NR_VIN, DS_MODELO, NR_ANO, DS_COR, NR_KM_ATUAL, ID_CLIENTE)
VALUES (SEQ_VEICULO.NEXTVAL, '9BWZZZ377VT012345', 'Ford EcoSport', 2022, 'Vermelho', 22000, 2);

INSERT INTO TB_VEICULO (ID_VEICULO, NR_VIN, DS_MODELO, NR_ANO, DS_COR, NR_KM_ATUAL, ID_CLIENTE)
VALUES (SEQ_VEICULO.NEXTVAL, '9BWZZZ377VT098765', 'Ford Territory', 2023, 'Cinza', 15000, 3);

-- ---------------------------------------------------------
-- Histórico de manutenção
-- ---------------------------------------------------------
INSERT INTO TB_HISTORICO_MANUTENCAO (ID_HISTORICO, DT_MANUTENCAO, DS_TIPO_SERVICO, VL_SERVICO, DS_CONCESSIONARIA, DS_OBSERVACAO, ID_VEICULO)
VALUES (SEQ_HISTORICO_MANUTENCAO.NEXTVAL, TO_DATE('2024-03-15','YYYY-MM-DD'), 'Revisão 30.000 km', 650.00, 'Ford Itaim Bibi', 'Troca de óleo e filtros', 1);

INSERT INTO TB_HISTORICO_MANUTENCAO (ID_HISTORICO, DT_MANUTENCAO, DS_TIPO_SERVICO, VL_SERVICO, DS_CONCESSIONARIA, DS_OBSERVACAO, ID_VEICULO)
VALUES (SEQ_HISTORICO_MANUTENCAO.NEXTVAL, TO_DATE('2023-09-10','YYYY-MM-DD'), 'Troca de pastilhas de freio', 420.00, 'Ford Itaim Bibi', null, 1);

INSERT INTO TB_HISTORICO_MANUTENCAO (ID_HISTORICO, DT_MANUTENCAO, DS_TIPO_SERVICO, VL_SERVICO, DS_CONCESSIONARIA, DS_OBSERVACAO, ID_VEICULO)
VALUES (SEQ_HISTORICO_MANUTENCAO.NEXTVAL, TO_DATE('2024-01-20','YYYY-MM-DD'), 'Revisão 75.000 km', 1200.00, 'Ford Pinheiros', 'Troca de correia dentada', 2);

INSERT INTO TB_HISTORICO_MANUTENCAO (ID_HISTORICO, DT_MANUTENCAO, DS_TIPO_SERVICO, VL_SERVICO, DS_CONCESSIONARIA, DS_OBSERVACAO, ID_VEICULO)
VALUES (SEQ_HISTORICO_MANUTENCAO.NEXTVAL, TO_DATE('2024-04-05','YYYY-MM-DD'), 'Alinhamento e balanceamento', 180.00, 'Ford Mooca', null, 3);

-- ---------------------------------------------------------
-- Leads preditivos
-- ---------------------------------------------------------
INSERT INTO TB_LEAD (ID_LEAD, NR_SCORE_PROPENSAO, DS_STATUS, DS_RECOMENDACAO_ACAO, DS_MOTIVO_RISCO, DT_CRIACAO, ID_VEICULO)
VALUES (SEQ_LEAD.NEXTVAL, 87.5, 'NOVO',
    'Contatar cliente para agendar revisão dos 35.000 km — veículo próximo do vencimento',
    'Última revisão há mais de 6 meses, KM acumulado alto',
    SYSTIMESTAMP, 1);

INSERT INTO TB_LEAD (ID_LEAD, NR_SCORE_PROPENSAO, DS_STATUS, DS_RECOMENDACAO_ACAO, DS_MOTIVO_RISCO, DT_CRIACAO, ID_VEICULO)
VALUES (SEQ_LEAD.NEXTVAL, 72.0, 'NOVO',
    'Oferecer revisão dos 80.000 km com desconto — risco de perda para oficina externa',
    'KM muito alto, cliente não realizou última revisão na rede oficial',
    SYSTIMESTAMP, 2);

INSERT INTO TB_LEAD (ID_LEAD, NR_SCORE_PROPENSAO, DS_STATUS, DS_RECOMENDACAO_ACAO, DS_MOTIVO_RISCO, DT_CRIACAO, ID_VEICULO)
VALUES (SEQ_LEAD.NEXTVAL, 45.0, 'EM_CONTATO',
    'Acompanhar abertura de OS — cliente demonstrou interesse em revisão',
    null,
    SYSTIMESTAMP, 3);

INSERT INTO TB_LEAD (ID_LEAD, NR_SCORE_PROPENSAO, DS_STATUS, DS_RECOMENDACAO_ACAO, DS_MOTIVO_RISCO, DT_CRIACAO, ID_VEICULO)
VALUES (SEQ_LEAD.NEXTVAL, 91.0, 'NOVO',
    'Ação prioritária: veículo novo com alto valor agregado, primeira revisão se aproximando',
    'Veículo com menos de 1 ano, garantia ativa — importante fidelizar no pós-venda oficial',
    SYSTIMESTAMP, 4);

COMMIT;
