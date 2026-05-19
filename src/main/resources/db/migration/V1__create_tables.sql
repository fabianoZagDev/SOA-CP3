-- =========================================================
-- V1 — Criação das tabelas e sequences (Oracle)
-- =========================================================

-- Sequence para TB_CLIENTE
CREATE SEQUENCE SEQ_CLIENTE
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Sequence para TB_VEICULO
CREATE SEQUENCE SEQ_VEICULO
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Sequence para TB_HISTORICO_MANUTENCAO
CREATE SEQUENCE SEQ_HISTORICO_MANUTENCAO
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- Sequence para TB_LEAD
CREATE SEQUENCE SEQ_LEAD
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;

-- ---------------------------------------------------------
-- Tabela de clientes
-- ---------------------------------------------------------
CREATE TABLE TB_CLIENTE (
    ID_CLIENTE   NUMBER        NOT NULL,
    NM_CLIENTE   VARCHAR2(150) NOT NULL,
    DS_EMAIL     VARCHAR2(150),
    NR_TELEFONE  VARCHAR2(20),
    DS_CPF       VARCHAR2(14),
    CONSTRAINT PK_CLIENTE PRIMARY KEY (ID_CLIENTE),
    CONSTRAINT UQ_CLIENTE_EMAIL UNIQUE (DS_EMAIL),
    CONSTRAINT UQ_CLIENTE_CPF   UNIQUE (DS_CPF)
);

-- ---------------------------------------------------------
-- Tabela de veículos
-- ---------------------------------------------------------
CREATE TABLE TB_VEICULO (
    ID_VEICULO   NUMBER        NOT NULL,
    NR_VIN       VARCHAR2(17)  NOT NULL,
    DS_MODELO    VARCHAR2(100) NOT NULL,
    NR_ANO       NUMBER(4),
    DS_COR       VARCHAR2(50),
    NR_KM_ATUAL  NUMBER(10),
    ID_CLIENTE   NUMBER        NOT NULL,
    CONSTRAINT PK_VEICULO       PRIMARY KEY (ID_VEICULO),
    CONSTRAINT UQ_VEICULO_VIN   UNIQUE (NR_VIN),
    CONSTRAINT FK_VEICULO_CLIENTE FOREIGN KEY (ID_CLIENTE)
        REFERENCES TB_CLIENTE (ID_CLIENTE)
);

-- ---------------------------------------------------------
-- Tabela de histórico de manutenção
-- ---------------------------------------------------------
CREATE TABLE TB_HISTORICO_MANUTENCAO (
    ID_HISTORICO      NUMBER        NOT NULL,
    DT_MANUTENCAO     DATE          NOT NULL,
    DS_TIPO_SERVICO   VARCHAR2(100) NOT NULL,
    VL_SERVICO        NUMBER(10, 2),
    DS_CONCESSIONARIA VARCHAR2(150),
    DS_OBSERVACAO     VARCHAR2(500),
    ID_VEICULO        NUMBER        NOT NULL,
    CONSTRAINT PK_HISTORICO PRIMARY KEY (ID_HISTORICO),
    CONSTRAINT FK_HISTORICO_VEICULO FOREIGN KEY (ID_VEICULO)
        REFERENCES TB_VEICULO (ID_VEICULO)
);

-- ---------------------------------------------------------
-- Tabela de leads preditivos
-- ---------------------------------------------------------
CREATE TABLE TB_LEAD (
    ID_LEAD                NUMBER         NOT NULL,
    NR_SCORE_PROPENSAO     NUMBER(5, 2)   NOT NULL,
    DS_STATUS              VARCHAR2(30)   NOT NULL,
    DS_RECOMENDACAO_ACAO   VARCHAR2(500),
    DS_MOTIVO_RISCO        VARCHAR2(300),
    DT_CRIACAO             TIMESTAMP      NOT NULL,
    DT_ULTIMO_CONTATO      TIMESTAMP,
    DS_OBSERVACAO_CONTATO  VARCHAR2(500),
    ID_VEICULO             NUMBER         NOT NULL,
    CONSTRAINT PK_LEAD           PRIMARY KEY (ID_LEAD),
    CONSTRAINT UQ_LEAD_VEICULO   UNIQUE (ID_VEICULO),
    CONSTRAINT FK_LEAD_VEICULO   FOREIGN KEY (ID_VEICULO)
        REFERENCES TB_VEICULO (ID_VEICULO),
    CONSTRAINT CHK_LEAD_STATUS   CHECK (DS_STATUS IN ('NOVO','EM_CONTATO','CONVERTIDO','PERDIDO')),
    CONSTRAINT CHK_LEAD_SCORE    CHECK (NR_SCORE_PROPENSAO BETWEEN 0 AND 100)
);
