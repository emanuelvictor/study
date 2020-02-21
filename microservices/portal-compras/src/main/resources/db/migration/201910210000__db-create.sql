--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7 (Debian 10.7-1.pgdg90+1)
-- Dumped by pg_dump version 11.3

-- Started on 2019-10-23 14:14:37 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 68233)
-- Name: cadastro; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA cadastro;


--
-- TOC entry 4 (class 2615 OID 68238)
-- Name: cadastro_audit; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA cadastro_audit;


--
-- TOC entry 8 (class 2615 OID 68235)
-- Name: configuracao; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA configuracao;


--
-- TOC entry 9 (class 2615 OID 68240)
-- Name: configuracao_audit; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA configuracao_audit;


--
-- TOC entry 7 (class 2615 OID 68237)
-- Name: endereco; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA endereco;


--
-- TOC entry 12 (class 2615 OID 68242)
-- Name: endereco_audit; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA endereco_audit;


--
-- TOC entry 15 (class 2615 OID 68236)
-- Name: fornecedor; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA fornecedor;


--
-- TOC entry 13 (class 2615 OID 68241)
-- Name: fornecedor_audit; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA fornecedor_audit;


--
-- TOC entry 5 (class 2615 OID 68234)
-- Name: publicacao; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA publicacao;


--
-- TOC entry 17 (class 2615 OID 68239)
-- Name: publicacao_audit; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA publicacao_audit;


SET default_with_oids = false;

--
-- TOC entry 207 (class 1259 OID 68245)
-- Name: banco; Type: TABLE; Schema: cadastro; Owner: -
--

CREATE TABLE cadastro.banco (
                                id bigint NOT NULL,
                                created timestamp without time zone NOT NULL,
                                updated timestamp without time zone,
                                codigo character varying(3) NOT NULL,
                                nome character varying(250) NOT NULL
);


--
-- TOC entry 206 (class 1259 OID 68243)
-- Name: banco_id_seq; Type: SEQUENCE; Schema: cadastro; Owner: -
--

CREATE SEQUENCE cadastro.banco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3504 (class 0 OID 0)
-- Dependencies: 206
-- Name: banco_id_seq; Type: SEQUENCE OWNED BY; Schema: cadastro; Owner: -
--

ALTER SEQUENCE cadastro.banco_id_seq OWNED BY cadastro.banco.id;


--
-- TOC entry 209 (class 1259 OID 68253)
-- Name: categoria; Type: TABLE; Schema: cadastro; Owner: -
--

CREATE TABLE cadastro.categoria (
                                    id bigint NOT NULL,
                                    created timestamp without time zone NOT NULL,
                                    updated timestamp without time zone,
                                    ativo boolean NOT NULL,
                                    nome character varying(50) NOT NULL
);


--
-- TOC entry 208 (class 1259 OID 68251)
-- Name: categoria_id_seq; Type: SEQUENCE; Schema: cadastro; Owner: -
--

CREATE SEQUENCE cadastro.categoria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3505 (class 0 OID 0)
-- Dependencies: 208
-- Name: categoria_id_seq; Type: SEQUENCE OWNED BY; Schema: cadastro; Owner: -
--

ALTER SEQUENCE cadastro.categoria_id_seq OWNED BY cadastro.categoria.id;


--
-- TOC entry 211 (class 1259 OID 68261)
-- Name: tipo_cadastro; Type: TABLE; Schema: cadastro; Owner: -
--

CREATE TABLE cadastro.tipo_cadastro (
                                        id bigint NOT NULL,
                                        created timestamp without time zone NOT NULL,
                                        updated timestamp without time zone,
                                        ativo boolean NOT NULL,
                                        nome character varying(50) NOT NULL
);


--
-- TOC entry 210 (class 1259 OID 68259)
-- Name: tipo_cadastro_id_seq; Type: SEQUENCE; Schema: cadastro; Owner: -
--

CREATE SEQUENCE cadastro.tipo_cadastro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3506 (class 0 OID 0)
-- Dependencies: 210
-- Name: tipo_cadastro_id_seq; Type: SEQUENCE OWNED BY; Schema: cadastro; Owner: -
--

ALTER SEQUENCE cadastro.tipo_cadastro_id_seq OWNED BY cadastro.tipo_cadastro.id;


--
-- TOC entry 213 (class 1259 OID 68269)
-- Name: tipo_cadastro_tipo_documento; Type: TABLE; Schema: cadastro; Owner: -
--

CREATE TABLE cadastro.tipo_cadastro_tipo_documento (
                                                       id bigint NOT NULL,
                                                       created timestamp without time zone NOT NULL,
                                                       updated timestamp without time zone,
                                                       interno boolean NOT NULL,
                                                       obrigatorio boolean NOT NULL,
                                                       tipo_cadastro_id bigint NOT NULL,
                                                       tipo_documento_id bigint NOT NULL
);


--
-- TOC entry 212 (class 1259 OID 68267)
-- Name: tipo_cadastro_tipo_documento_id_seq; Type: SEQUENCE; Schema: cadastro; Owner: -
--

CREATE SEQUENCE cadastro.tipo_cadastro_tipo_documento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3507 (class 0 OID 0)
-- Dependencies: 212
-- Name: tipo_cadastro_tipo_documento_id_seq; Type: SEQUENCE OWNED BY; Schema: cadastro; Owner: -
--

ALTER SEQUENCE cadastro.tipo_cadastro_tipo_documento_id_seq OWNED BY cadastro.tipo_cadastro_tipo_documento.id;


--
-- TOC entry 215 (class 1259 OID 68277)
-- Name: tipo_documento; Type: TABLE; Schema: cadastro; Owner: -
--

CREATE TABLE cadastro.tipo_documento (
                                         id bigint NOT NULL,
                                         created timestamp without time zone NOT NULL,
                                         updated timestamp without time zone,
                                         ativo boolean NOT NULL,
                                         modelo character varying(500),
                                         nome character varying(250) NOT NULL
);


--
-- TOC entry 214 (class 1259 OID 68275)
-- Name: tipo_documento_id_seq; Type: SEQUENCE; Schema: cadastro; Owner: -
--

CREATE SEQUENCE cadastro.tipo_documento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3508 (class 0 OID 0)
-- Dependencies: 214
-- Name: tipo_documento_id_seq; Type: SEQUENCE OWNED BY; Schema: cadastro; Owner: -
--

ALTER SEQUENCE cadastro.tipo_documento_id_seq OWNED BY cadastro.tipo_documento.id;


--
-- TOC entry 216 (class 1259 OID 68290)
-- Name: banco_audit; Type: TABLE; Schema: cadastro_audit; Owner: -
--

CREATE TABLE cadastro_audit.banco_audit (
                                            id bigint NOT NULL,
                                            revision bigint NOT NULL,
                                            revision_type smallint,
                                            codigo character varying(3),
                                            nome character varying(250)
);


--
-- TOC entry 217 (class 1259 OID 68295)
-- Name: categoria_audit; Type: TABLE; Schema: cadastro_audit; Owner: -
--

CREATE TABLE cadastro_audit.categoria_audit (
                                                id bigint NOT NULL,
                                                revision bigint NOT NULL,
                                                revision_type smallint,
                                                ativo boolean,
                                                nome character varying(50)
);


--
-- TOC entry 218 (class 1259 OID 68300)
-- Name: tipo_cadastro_audit; Type: TABLE; Schema: cadastro_audit; Owner: -
--

CREATE TABLE cadastro_audit.tipo_cadastro_audit (
                                                    id bigint NOT NULL,
                                                    revision bigint NOT NULL,
                                                    revision_type smallint,
                                                    ativo boolean,
                                                    nome character varying(50)
);


--
-- TOC entry 219 (class 1259 OID 68305)
-- Name: tipo_cadastro_tipo_documento_audit; Type: TABLE; Schema: cadastro_audit; Owner: -
--

CREATE TABLE cadastro_audit.tipo_cadastro_tipo_documento_audit (
                                                                   id bigint NOT NULL,
                                                                   revision bigint NOT NULL,
                                                                   revision_type smallint,
                                                                   interno boolean,
                                                                   obrigatorio boolean,
                                                                   tipo_cadastro_id bigint,
                                                                   tipo_documento_id bigint
);


--
-- TOC entry 220 (class 1259 OID 68310)
-- Name: tipo_documento_audit; Type: TABLE; Schema: cadastro_audit; Owner: -
--

CREATE TABLE cadastro_audit.tipo_documento_audit (
                                                     id bigint NOT NULL,
                                                     revision bigint NOT NULL,
                                                     revision_type smallint,
                                                     ativo boolean,
                                                     modelo character varying(500),
                                                     nome character varying(250)
);


--
-- TOC entry 222 (class 1259 OID 68320)
-- Name: categoria_aviso_edital; Type: TABLE; Schema: configuracao; Owner: -
--

CREATE TABLE configuracao.categoria_aviso_edital (
                                                     id bigint NOT NULL,
                                                     created timestamp without time zone NOT NULL,
                                                     updated timestamp without time zone,
                                                     aviso_edital_id bigint NOT NULL,
                                                     categoria_id bigint NOT NULL
);


--
-- TOC entry 221 (class 1259 OID 68318)
-- Name: categoria_aviso_edital_id_seq; Type: SEQUENCE; Schema: configuracao; Owner: -
--

CREATE SEQUENCE configuracao.categoria_aviso_edital_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3509 (class 0 OID 0)
-- Dependencies: 221
-- Name: categoria_aviso_edital_id_seq; Type: SEQUENCE OWNED BY; Schema: configuracao; Owner: -
--

ALTER SEQUENCE configuracao.categoria_aviso_edital_id_seq OWNED BY configuracao.categoria_aviso_edital.id;


--
-- TOC entry 224 (class 1259 OID 68328)
-- Name: grupo_acesso; Type: TABLE; Schema: configuracao; Owner: -
--

CREATE TABLE configuracao.grupo_acesso (
                                           id bigint NOT NULL,
                                           created timestamp without time zone NOT NULL,
                                           updated timestamp without time zone,
                                           ativo boolean NOT NULL,
                                           nome character varying(50) NOT NULL
);


--
-- TOC entry 223 (class 1259 OID 68326)
-- Name: grupo_acesso_id_seq; Type: SEQUENCE; Schema: configuracao; Owner: -
--

CREATE SEQUENCE configuracao.grupo_acesso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3510 (class 0 OID 0)
-- Dependencies: 223
-- Name: grupo_acesso_id_seq; Type: SEQUENCE OWNED BY; Schema: configuracao; Owner: -
--

ALTER SEQUENCE configuracao.grupo_acesso_id_seq OWNED BY configuracao.grupo_acesso.id;


--
-- TOC entry 226 (class 1259 OID 68336)
-- Name: grupo_acesso_permissao; Type: TABLE; Schema: configuracao; Owner: -
--

CREATE TABLE configuracao.grupo_acesso_permissao (
                                                     id bigint NOT NULL,
                                                     created timestamp without time zone NOT NULL,
                                                     updated timestamp without time zone,
                                                     grupo_acesso_permissao_id bigint NOT NULL,
                                                     permissao_id bigint NOT NULL
);


--
-- TOC entry 225 (class 1259 OID 68334)
-- Name: grupo_acesso_permissao_id_seq; Type: SEQUENCE; Schema: configuracao; Owner: -
--

CREATE SEQUENCE configuracao.grupo_acesso_permissao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3511 (class 0 OID 0)
-- Dependencies: 225
-- Name: grupo_acesso_permissao_id_seq; Type: SEQUENCE OWNED BY; Schema: configuracao; Owner: -
--

ALTER SEQUENCE configuracao.grupo_acesso_permissao_id_seq OWNED BY configuracao.grupo_acesso_permissao.id;


--
-- TOC entry 228 (class 1259 OID 68344)
-- Name: permissao; Type: TABLE; Schema: configuracao; Owner: -
--

CREATE TABLE configuracao.permissao (
                                        id bigint NOT NULL,
                                        created timestamp without time zone NOT NULL,
                                        updated timestamp without time zone,
                                        identificador character varying(255) NOT NULL,
                                        nome character varying(255) NOT NULL,
                                        permissao_superior_id bigint
);


--
-- TOC entry 227 (class 1259 OID 68342)
-- Name: permissao_id_seq; Type: SEQUENCE; Schema: configuracao; Owner: -
--

CREATE SEQUENCE configuracao.permissao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3512 (class 0 OID 0)
-- Dependencies: 227
-- Name: permissao_id_seq; Type: SEQUENCE OWNED BY; Schema: configuracao; Owner: -
--

ALTER SEQUENCE configuracao.permissao_id_seq OWNED BY configuracao.permissao.id;


--
-- TOC entry 230 (class 1259 OID 68355)
-- Name: pessoa; Type: TABLE; Schema: configuracao; Owner: -
--

CREATE TABLE configuracao.pessoa (
                                     id bigint NOT NULL,
                                     created timestamp without time zone NOT NULL,
                                     updated timestamp without time zone,
                                     documento character varying(14),
                                     nome character varying(150) NOT NULL
);


--
-- TOC entry 229 (class 1259 OID 68353)
-- Name: pessoa_id_seq; Type: SEQUENCE; Schema: configuracao; Owner: -
--

CREATE SEQUENCE configuracao.pessoa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3513 (class 0 OID 0)
-- Dependencies: 229
-- Name: pessoa_id_seq; Type: SEQUENCE OWNED BY; Schema: configuracao; Owner: -
--

ALTER SEQUENCE configuracao.pessoa_id_seq OWNED BY configuracao.pessoa.id;


--
-- TOC entry 231 (class 1259 OID 68361)
-- Name: usuario; Type: TABLE; Schema: configuracao; Owner: -
--

CREATE TABLE configuracao.usuario (
                                      administrador boolean NOT NULL,
                                      ativo boolean NOT NULL,
                                      codigo character varying(255),
                                      email character varying(150) NOT NULL,
                                      interno boolean NOT NULL,
                                      senha character varying(100) NOT NULL,
                                      tentativas_login smallint,
                                      ultima_tentativa_login timestamp without time zone,
                                      ultimo_acesso timestamp without time zone,
                                      id bigint NOT NULL,
                                      grupo_acesso_id bigint
);


--
-- TOC entry 232 (class 1259 OID 68381)
-- Name: grupo_acesso_audit; Type: TABLE; Schema: configuracao_audit; Owner: -
--

CREATE TABLE configuracao_audit.grupo_acesso_audit (
                                                       id bigint NOT NULL,
                                                       revision bigint NOT NULL,
                                                       revision_type smallint,
                                                       ativo boolean,
                                                       nome character varying(50)
);


--
-- TOC entry 233 (class 1259 OID 68386)
-- Name: grupo_acesso_permissao_audit; Type: TABLE; Schema: configuracao_audit; Owner: -
--

CREATE TABLE configuracao_audit.grupo_acesso_permissao_audit (
                                                                 id bigint NOT NULL,
                                                                 revision bigint NOT NULL,
                                                                 revision_type smallint,
                                                                 grupo_acesso_permissao_id bigint,
                                                                 permissao_id bigint
);


--
-- TOC entry 234 (class 1259 OID 68391)
-- Name: permissao_audit; Type: TABLE; Schema: configuracao_audit; Owner: -
--

CREATE TABLE configuracao_audit.permissao_audit (
                                                    id bigint NOT NULL,
                                                    revision bigint NOT NULL,
                                                    revision_type smallint,
                                                    identificador character varying(255),
                                                    nome character varying(255),
                                                    permissao_superior_id bigint
);


--
-- TOC entry 235 (class 1259 OID 68399)
-- Name: pessoa_audit; Type: TABLE; Schema: configuracao_audit; Owner: -
--

CREATE TABLE configuracao_audit.pessoa_audit (
                                                 id bigint NOT NULL,
                                                 revision bigint NOT NULL,
                                                 revision_type smallint,
                                                 documento character varying(14),
                                                 nome character varying(150)
);


--
-- TOC entry 236 (class 1259 OID 68404)
-- Name: usuario_audit; Type: TABLE; Schema: configuracao_audit; Owner: -
--

CREATE TABLE configuracao_audit.usuario_audit (
                                                  id bigint NOT NULL,
                                                  revision bigint NOT NULL,
                                                  administrador boolean,
                                                  ativo boolean,
                                                  codigo character varying(255),
                                                  email character varying(150),
                                                  interno boolean,
                                                  senha character varying(100),
                                                  tentativas_login smallint,
                                                  ultima_tentativa_login timestamp without time zone,
                                                  ultimo_acesso timestamp without time zone,
                                                  grupo_acesso_id bigint
);


--
-- TOC entry 238 (class 1259 OID 68414)
-- Name: cidade; Type: TABLE; Schema: endereco; Owner: -
--

CREATE TABLE endereco.cidade (
                                 id bigint NOT NULL,
                                 created timestamp without time zone NOT NULL,
                                 updated timestamp without time zone,
                                 nome character varying(200) NOT NULL,
                                 estado_id bigint NOT NULL
);


--
-- TOC entry 237 (class 1259 OID 68412)
-- Name: cidade_id_seq; Type: SEQUENCE; Schema: endereco; Owner: -
--

CREATE SEQUENCE endereco.cidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3514 (class 0 OID 0)
-- Dependencies: 237
-- Name: cidade_id_seq; Type: SEQUENCE OWNED BY; Schema: endereco; Owner: -
--

ALTER SEQUENCE endereco.cidade_id_seq OWNED BY endereco.cidade.id;


--
-- TOC entry 240 (class 1259 OID 68422)
-- Name: endereco; Type: TABLE; Schema: endereco; Owner: -
--

CREATE TABLE endereco.endereco (
                                   id bigint NOT NULL,
                                   created timestamp without time zone NOT NULL,
                                   updated timestamp without time zone,
                                   bairro character varying(20),
                                   caixa_postal character varying(200),
                                   cep character varying(9),
                                   complemento character varying(50),
                                   logradouro character varying(40),
                                   numero character varying(20),
                                   cidade_id bigint
);


--
-- TOC entry 239 (class 1259 OID 68420)
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: endereco; Owner: -
--

CREATE SEQUENCE endereco.endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3515 (class 0 OID 0)
-- Dependencies: 239
-- Name: endereco_id_seq; Type: SEQUENCE OWNED BY; Schema: endereco; Owner: -
--

ALTER SEQUENCE endereco.endereco_id_seq OWNED BY endereco.endereco.id;


--
-- TOC entry 242 (class 1259 OID 68430)
-- Name: estado; Type: TABLE; Schema: endereco; Owner: -
--

CREATE TABLE endereco.estado (
                                 id bigint NOT NULL,
                                 created timestamp without time zone NOT NULL,
                                 updated timestamp without time zone,
                                 nome character varying(200) NOT NULL,
                                 uf character varying(2) NOT NULL,
                                 pais_id bigint NOT NULL
);


--
-- TOC entry 241 (class 1259 OID 68428)
-- Name: estado_id_seq; Type: SEQUENCE; Schema: endereco; Owner: -
--

CREATE SEQUENCE endereco.estado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3516 (class 0 OID 0)
-- Dependencies: 241
-- Name: estado_id_seq; Type: SEQUENCE OWNED BY; Schema: endereco; Owner: -
--

ALTER SEQUENCE endereco.estado_id_seq OWNED BY endereco.estado.id;


--
-- TOC entry 244 (class 1259 OID 68438)
-- Name: pais; Type: TABLE; Schema: endereco; Owner: -
--

CREATE TABLE endereco.pais (
                               id bigint NOT NULL,
                               created timestamp without time zone NOT NULL,
                               updated timestamp without time zone,
                               nome character varying(200) NOT NULL
);


--
-- TOC entry 243 (class 1259 OID 68436)
-- Name: pais_id_seq; Type: SEQUENCE; Schema: endereco; Owner: -
--

CREATE SEQUENCE endereco.pais_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3517 (class 0 OID 0)
-- Dependencies: 243
-- Name: pais_id_seq; Type: SEQUENCE OWNED BY; Schema: endereco; Owner: -
--

ALTER SEQUENCE endereco.pais_id_seq OWNED BY endereco.pais.id;


--
-- TOC entry 245 (class 1259 OID 68448)
-- Name: cidade_audit; Type: TABLE; Schema: endereco_audit; Owner: -
--

CREATE TABLE endereco_audit.cidade_audit (
                                             id bigint NOT NULL,
                                             revision bigint NOT NULL,
                                             revision_type smallint,
                                             nome character varying(200),
                                             estado_id bigint
);


--
-- TOC entry 246 (class 1259 OID 68453)
-- Name: endereco_audit; Type: TABLE; Schema: endereco_audit; Owner: -
--

CREATE TABLE endereco_audit.endereco_audit (
                                               id bigint NOT NULL,
                                               revision bigint NOT NULL,
                                               revision_type smallint,
                                               bairro character varying(20),
                                               caixa_postal character varying(200),
                                               cep character varying(255),
                                               complemento character varying(50),
                                               logradouro character varying(40),
                                               numero character varying(20),
                                               cidade_id bigint
);


--
-- TOC entry 247 (class 1259 OID 68461)
-- Name: estado_audit; Type: TABLE; Schema: endereco_audit; Owner: -
--

CREATE TABLE endereco_audit.estado_audit (
                                             id bigint NOT NULL,
                                             revision bigint NOT NULL,
                                             revision_type smallint,
                                             nome character varying(200),
                                             uf character varying(2),
                                             pais_id bigint
);


--
-- TOC entry 248 (class 1259 OID 68466)
-- Name: pais_audit; Type: TABLE; Schema: endereco_audit; Owner: -
--

CREATE TABLE endereco_audit.pais_audit (
                                           id bigint NOT NULL,
                                           revision bigint NOT NULL,
                                           revision_type smallint,
                                           nome character varying(200)
);


--
-- TOC entry 252 (class 1259 OID 68489)
-- Name: atividade_economica; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.atividade_economica (
                                                code character varying(255) NOT NULL,
                                                text character varying(255) NOT NULL
);


--
-- TOC entry 249 (class 1259 OID 68471)
-- Name: atividade_economica_audited; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.atividade_economica_audited (
                                                        code character varying(255) NOT NULL,
                                                        revision bigint NOT NULL,
                                                        revision_type smallint,
                                                        text character varying(255)
);


--
-- TOC entry 250 (class 1259 OID 68479)
-- Name: atividade_economica_primaria; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.atividade_economica_primaria (
                                                         fornecedor_id bigint NOT NULL,
                                                         atividade_economica_id character varying(255) NOT NULL
);


--
-- TOC entry 251 (class 1259 OID 68484)
-- Name: atividade_economica_secundaria; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.atividade_economica_secundaria (
                                                           fornecedor_id bigint NOT NULL,
                                                           atividade_economica_id character varying(255) NOT NULL
);


--
-- TOC entry 254 (class 1259 OID 68499)
-- Name: categoria_fornecedor; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.categoria_fornecedor (
                                                 id bigint NOT NULL,
                                                 created timestamp without time zone NOT NULL,
                                                 updated timestamp without time zone,
                                                 categoria_id bigint NOT NULL,
                                                 fornecedor_id bigint NOT NULL
);


--
-- TOC entry 253 (class 1259 OID 68497)
-- Name: categoria_fornecedor_id_seq; Type: SEQUENCE; Schema: fornecedor; Owner: -
--

CREATE SEQUENCE fornecedor.categoria_fornecedor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3518 (class 0 OID 0)
-- Dependencies: 253
-- Name: categoria_fornecedor_id_seq; Type: SEQUENCE OWNED BY; Schema: fornecedor; Owner: -
--

ALTER SEQUENCE fornecedor.categoria_fornecedor_id_seq OWNED BY fornecedor.categoria_fornecedor.id;


--
-- TOC entry 256 (class 1259 OID 68507)
-- Name: conta_bancaria; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.conta_bancaria (
                                           id bigint NOT NULL,
                                           created timestamp without time zone NOT NULL,
                                           updated timestamp without time zone,
                                           agencia character varying(4),
                                           digito character varying(2),
                                           numero character varying(20),
                                           tipo_conta_bancaria integer,
                                           banco_id bigint
);


--
-- TOC entry 255 (class 1259 OID 68505)
-- Name: conta_bancaria_id_seq; Type: SEQUENCE; Schema: fornecedor; Owner: -
--

CREATE SEQUENCE fornecedor.conta_bancaria_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3519 (class 0 OID 0)
-- Dependencies: 255
-- Name: conta_bancaria_id_seq; Type: SEQUENCE OWNED BY; Schema: fornecedor; Owner: -
--

ALTER SEQUENCE fornecedor.conta_bancaria_id_seq OWNED BY fornecedor.conta_bancaria.id;


--
-- TOC entry 258 (class 1259 OID 68515)
-- Name: dados_recebimento; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.dados_recebimento (
                                              id bigint NOT NULL,
                                              created timestamp without time zone NOT NULL,
                                              updated timestamp without time zone,
                                              forma_pagamento integer NOT NULL,
                                              conta_bancaria_id bigint
);


--
-- TOC entry 257 (class 1259 OID 68513)
-- Name: dados_recebimento_id_seq; Type: SEQUENCE; Schema: fornecedor; Owner: -
--

CREATE SEQUENCE fornecedor.dados_recebimento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3520 (class 0 OID 0)
-- Dependencies: 257
-- Name: dados_recebimento_id_seq; Type: SEQUENCE OWNED BY; Schema: fornecedor; Owner: -
--

ALTER SEQUENCE fornecedor.dados_recebimento_id_seq OWNED BY fornecedor.dados_recebimento.id;


--
-- TOC entry 259 (class 1259 OID 68521)
-- Name: documento; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.documento (
                                      aprovado boolean,
                                      nome character varying(150) NOT NULL,
                                      observacao character varying(200),
                                      id bigint NOT NULL,
                                      fornecedor_id bigint NOT NULL,
                                      tipo_cadastro_tipo_documento_id bigint NOT NULL
);


--
-- TOC entry 261 (class 1259 OID 68528)
-- Name: fornecedor; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.fornecedor (
                                       id bigint NOT NULL,
                                       created timestamp without time zone NOT NULL,
                                       updated timestamp without time zone,
                                       codigo_protheus bigint,
                                       cooperativa boolean NOT NULL,
                                       data_aprovacao timestamp without time zone,
                                       descricao_produtos_servicos character varying(250),
                                       email_vencimento_enviado boolean,
                                       feedback character varying(250),
                                       inscricao_estadual character varying(18),
                                       inscricao_municipal character varying(18),
                                       possui_inscricao_estadual boolean NOT NULL,
                                       razao_social character varying(150),
                                       simples_nacional boolean NOT NULL,
                                       site character varying(300),
                                       sou_empresa boolean NOT NULL,
                                       status integer NOT NULL,
                                       tipo_pessoa_juridica integer NOT NULL,
                                       dados_recebimento_id bigint,
                                       endereco_id bigint,
                                       tipo_cadastro_id bigint,
                                       usuario_id bigint NOT NULL
);


--
-- TOC entry 262 (class 1259 OID 68537)
-- Name: fornecedor_atividades_economicas; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.fornecedor_atividades_economicas (
                                                             fornecedor_id bigint NOT NULL,
                                                             atividades_economicas integer
);


--
-- TOC entry 263 (class 1259 OID 68540)
-- Name: fornecedor_emails; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.fornecedor_emails (
                                              fornecedor_id bigint NOT NULL,
                                              emails character varying(255)
);


--
-- TOC entry 260 (class 1259 OID 68526)
-- Name: fornecedor_id_seq; Type: SEQUENCE; Schema: fornecedor; Owner: -
--

CREATE SEQUENCE fornecedor.fornecedor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3521 (class 0 OID 0)
-- Dependencies: 260
-- Name: fornecedor_id_seq; Type: SEQUENCE OWNED BY; Schema: fornecedor; Owner: -
--

ALTER SEQUENCE fornecedor.fornecedor_id_seq OWNED BY fornecedor.fornecedor.id;


--
-- TOC entry 264 (class 1259 OID 68543)
-- Name: fornecedor_telefones; Type: TABLE; Schema: fornecedor; Owner: -
--

CREATE TABLE fornecedor.fornecedor_telefones (
                                                 fornecedor_id bigint NOT NULL,
                                                 telefones character varying(255)
);


--
-- TOC entry 265 (class 1259 OID 68558)
-- Name: atividade_economica_primaria_audit; Type: TABLE; Schema: fornecedor_audit; Owner: -
--

CREATE TABLE fornecedor_audit.atividade_economica_primaria_audit (
                                                                     revision bigint NOT NULL,
                                                                     fornecedor_id bigint NOT NULL,
                                                                     atividade_economica_id character varying(255) NOT NULL,
                                                                     revision_type smallint
);


--
-- TOC entry 266 (class 1259 OID 68563)
-- Name: atividade_economica_secundaria_audit; Type: TABLE; Schema: fornecedor_audit; Owner: -
--

CREATE TABLE fornecedor_audit.atividade_economica_secundaria_audit (
                                                                       revision bigint NOT NULL,
                                                                       fornecedor_id bigint NOT NULL,
                                                                       atividade_economica_id character varying(255) NOT NULL,
                                                                       revision_type smallint
);


--
-- TOC entry 267 (class 1259 OID 68568)
-- Name: conta_bancaria_audit; Type: TABLE; Schema: fornecedor_audit; Owner: -
--

CREATE TABLE fornecedor_audit.conta_bancaria_audit (
                                                       id bigint NOT NULL,
                                                       revision bigint NOT NULL,
                                                       revision_type smallint,
                                                       agencia character varying(4),
                                                       digito character varying(2),
                                                       numero character varying(20),
                                                       tipo_conta_bancaria integer,
                                                       banco_id bigint
);


--
-- TOC entry 268 (class 1259 OID 68573)
-- Name: dados_recebimento_audit; Type: TABLE; Schema: fornecedor_audit; Owner: -
--

CREATE TABLE fornecedor_audit.dados_recebimento_audit (
                                                          id bigint NOT NULL,
                                                          revision bigint NOT NULL,
                                                          revision_type smallint,
                                                          forma_pagamento integer,
                                                          conta_bancaria_id bigint
);


--
-- TOC entry 269 (class 1259 OID 68578)
-- Name: documento_audit; Type: TABLE; Schema: fornecedor_audit; Owner: -
--

CREATE TABLE fornecedor_audit.documento_audit (
                                                  id bigint NOT NULL,
                                                  revision bigint NOT NULL,
                                                  aprovado boolean,
                                                  nome character varying(150),
                                                  observacao character varying(200),
                                                  fornecedor_id bigint,
                                                  tipo_cadastro_tipo_documento_id bigint
);


--
-- TOC entry 270 (class 1259 OID 68583)
-- Name: fornecedor_atividades_economicas_audited; Type: TABLE; Schema: fornecedor_audit; Owner: -
--

CREATE TABLE fornecedor_audit.fornecedor_atividades_economicas_audited (
                                                                           revision bigint NOT NULL,
                                                                           fornecedor_id bigint NOT NULL,
                                                                           atividades_economicas integer NOT NULL,
                                                                           revision_type smallint
);

--
-- TOC entry 271 (class 1259 OID 68588)
-- Name: fornecedor_audit; Type: TABLE; Schema: fornecedor_audit; Owner: -
--

CREATE TABLE fornecedor_audit.fornecedor_audit (
                                                   id bigint NOT NULL,
                                                   revision bigint NOT NULL,
                                                   revision_type smallint,
                                                   codigo_protheus bigint,
                                                   cooperativa boolean,
                                                   data_aprovacao timestamp without time zone,
                                                   descricao_produtos_servicos character varying(250),
                                                   email_vencimento_enviado boolean,
                                                   feedback character varying(250),
                                                   inscricao_estadual character varying(18),
                                                   inscricao_municipal character varying(18),
                                                   possui_inscricao_estadual boolean,
                                                   razao_social character varying(150),
                                                   simples_nacional boolean,
                                                   site character varying(300),
                                                   sou_empresa boolean,
                                                   status integer,
                                                   tipo_pessoa_juridica integer,
                                                   dados_recebimento_id bigint,
                                                   endereco_id bigint,
                                                   tipo_cadastro_id bigint,
                                                   usuario_id bigint,
                                                   categoria_id bigint,
                                                   fornecedor_id bigint
);


--
-- TOC entry 272 (class 1259 OID 68596)
-- Name: fornecedor_emails_audited; Type: TABLE; Schema: fornecedor_audit; Owner: -
--

CREATE TABLE fornecedor_audit.fornecedor_emails_audited (
                                                            revision bigint NOT NULL,
                                                            fornecedor_id bigint NOT NULL,
                                                            emails character varying(255) NOT NULL,
                                                            revision_type smallint
);


--
-- TOC entry 273 (class 1259 OID 68601)
-- Name: fornecedor_telefones_audited; Type: TABLE; Schema: fornecedor_audit; Owner: -
--

CREATE TABLE fornecedor_audit.fornecedor_telefones_audited (
                                                               revision bigint NOT NULL,
                                                               fornecedor_id bigint NOT NULL,
                                                               telefones character varying(255) NOT NULL,
                                                               revision_type smallint
);


--
-- TOC entry 275 (class 1259 OID 68608)
-- Name: revision; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.revision (
                                 id bigint NOT NULL,
                                 "timestamp" bigint NOT NULL,
                                 user_id bigint
);


--
-- TOC entry 274 (class 1259 OID 68606)
-- Name: revision_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.revision_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3522 (class 0 OID 0)
-- Dependencies: 274
-- Name: revision_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.revision_id_seq OWNED BY public.revision.id;


--
-- TOC entry 276 (class 1259 OID 68614)
-- Name: anexo; Type: TABLE; Schema: publicacao; Owner: -
--

CREATE TABLE publicacao.anexo (
                                  nome character varying(150) NOT NULL,
                                  id bigint NOT NULL,
                                  publicacao_id bigint NOT NULL
);


--
-- TOC entry 278 (class 1259 OID 68621)
-- Name: arquivo; Type: TABLE; Schema: publicacao; Owner: -
--

CREATE TABLE publicacao.arquivo (
                                    id bigint NOT NULL,
                                    created timestamp without time zone NOT NULL,
                                    updated timestamp without time zone,
                                    caminho character varying(255) NOT NULL,
                                    conteudo oid,
                                    externo boolean NOT NULL,
                                    link character varying(500),
                                    type character varying(255) NOT NULL
);


--
-- TOC entry 277 (class 1259 OID 68619)
-- Name: arquivo_id_seq; Type: SEQUENCE; Schema: publicacao; Owner: -
--

CREATE SEQUENCE publicacao.arquivo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3523 (class 0 OID 0)
-- Dependencies: 277
-- Name: arquivo_id_seq; Type: SEQUENCE OWNED BY; Schema: publicacao; Owner: -
--

ALTER SEQUENCE publicacao.arquivo_id_seq OWNED BY publicacao.arquivo.id;


--
-- TOC entry 279 (class 1259 OID 68630)
-- Name: aviso_contratacao; Type: TABLE; Schema: publicacao; Owner: -
--

CREATE TABLE publicacao.aviso_contratacao (
                                              modalidade integer NOT NULL,
                                              numero_modalidade character varying(20) NOT NULL,
                                              numero_processo character varying(20) NOT NULL,
                                              razao_social character varying(150),
                                              id bigint NOT NULL
);


--
-- TOC entry 280 (class 1259 OID 68635)
-- Name: aviso_edital; Type: TABLE; Schema: publicacao; Owner: -
--

CREATE TABLE publicacao.aviso_edital (
                                         numero_edital character varying(20) NOT NULL,
                                         numero_processo character varying(20) NOT NULL,
                                         prazo_propostas date NOT NULL,
                                         status integer NOT NULL,
                                         id bigint NOT NULL
);


--
-- TOC entry 281 (class 1259 OID 68640)
-- Name: extrato_contrato; Type: TABLE; Schema: publicacao; Owner: -
--

CREATE TABLE publicacao.extrato_contrato (
                                             instrumento_juridico integer NOT NULL,
                                             numero_processo character varying(20) NOT NULL,
                                             razao_social character varying(150),
                                             id bigint NOT NULL
);


--
-- TOC entry 283 (class 1259 OID 68647)
-- Name: publicacao; Type: TABLE; Schema: publicacao; Owner: -
--

CREATE TABLE publicacao.publicacao (
                                       id bigint NOT NULL,
                                       created timestamp without time zone NOT NULL,
                                       updated timestamp without time zone,
                                       data_publicacao date NOT NULL,
                                       objeto character varying(500) NOT NULL
);


--
-- TOC entry 282 (class 1259 OID 68645)
-- Name: publicacao_id_seq; Type: SEQUENCE; Schema: publicacao; Owner: -
--

CREATE SEQUENCE publicacao.publicacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3524 (class 0 OID 0)
-- Dependencies: 282
-- Name: publicacao_id_seq; Type: SEQUENCE OWNED BY; Schema: publicacao; Owner: -
--

ALTER SEQUENCE publicacao.publicacao_id_seq OWNED BY publicacao.publicacao.id;


--
-- TOC entry 284 (class 1259 OID 68666)
-- Name: anexo_audit; Type: TABLE; Schema: publicacao_audit; Owner: -
--

CREATE TABLE publicacao_audit.anexo_audit (
                                              id bigint NOT NULL,
                                              revision bigint NOT NULL,
                                              nome character varying(150),
                                              publicacao_id bigint
);


--
-- TOC entry 285 (class 1259 OID 68671)
-- Name: arquivo_audit; Type: TABLE; Schema: publicacao_audit; Owner: -
--

CREATE TABLE publicacao_audit.arquivo_audit (
                                                id bigint NOT NULL,
                                                revision bigint NOT NULL,
                                                revision_type smallint,
                                                caminho character varying(255),
                                                conteudo oid,
                                                externo boolean,
                                                link character varying(500),
                                                type character varying(255)
);


--
-- TOC entry 286 (class 1259 OID 68679)
-- Name: aviso_contratacao_audit; Type: TABLE; Schema: publicacao_audit; Owner: -
--

CREATE TABLE publicacao_audit.aviso_contratacao_audit (
                                                          id bigint NOT NULL,
                                                          revision bigint NOT NULL,
                                                          modalidade integer,
                                                          numero_modalidade character varying(20),
                                                          numero_processo character varying(20),
                                                          razao_social character varying(150)
);


--
-- TOC entry 287 (class 1259 OID 68684)
-- Name: aviso_edital_audit; Type: TABLE; Schema: publicacao_audit; Owner: -
--

CREATE TABLE publicacao_audit.aviso_edital_audit (
                                                     id bigint NOT NULL,
                                                     revision bigint NOT NULL,
                                                     numero_edital character varying(20),
                                                     numero_processo character varying(20),
                                                     prazo_propostas date,
                                                     status integer
);


--
-- TOC entry 288 (class 1259 OID 68689)
-- Name: categoria_aviso_edital_audit; Type: TABLE; Schema: publicacao_audit; Owner: -
--

CREATE TABLE publicacao_audit.categoria_aviso_edital_audit (
                                                               id bigint NOT NULL,
                                                               revision bigint NOT NULL,
                                                               revision_type smallint,
                                                               aviso_edital_id bigint,
                                                               categoria_id bigint
);


--
-- TOC entry 289 (class 1259 OID 68694)
-- Name: extrato_contrato_audit; Type: TABLE; Schema: publicacao_audit; Owner: -
--

CREATE TABLE publicacao_audit.extrato_contrato_audit (
                                                         id bigint NOT NULL,
                                                         revision bigint NOT NULL,
                                                         instrumento_juridico integer,
                                                         numero_processo character varying(20),
                                                         razao_social character varying(150)
);


--
-- TOC entry 290 (class 1259 OID 68699)
-- Name: publicacao_audit; Type: TABLE; Schema: publicacao_audit; Owner: -
--

CREATE TABLE publicacao_audit.publicacao_audit (
                                                   id bigint NOT NULL,
                                                   revision bigint NOT NULL,
                                                   revision_type smallint,
                                                   data_publicacao date,
                                                   objeto character varying(500)
);


--
-- TOC entry 3042 (class 2604 OID 68248)
-- Name: banco id; Type: DEFAULT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.banco ALTER COLUMN id SET DEFAULT nextval('cadastro.banco_id_seq'::regclass);


--
-- TOC entry 3043 (class 2604 OID 68256)
-- Name: categoria id; Type: DEFAULT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.categoria ALTER COLUMN id SET DEFAULT nextval('cadastro.categoria_id_seq'::regclass);


--
-- TOC entry 3044 (class 2604 OID 68264)
-- Name: tipo_cadastro id; Type: DEFAULT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_cadastro ALTER COLUMN id SET DEFAULT nextval('cadastro.tipo_cadastro_id_seq'::regclass);


--
-- TOC entry 3045 (class 2604 OID 68272)
-- Name: tipo_cadastro_tipo_documento id; Type: DEFAULT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_cadastro_tipo_documento ALTER COLUMN id SET DEFAULT nextval('cadastro.tipo_cadastro_tipo_documento_id_seq'::regclass);


--
-- TOC entry 3046 (class 2604 OID 68280)
-- Name: tipo_documento id; Type: DEFAULT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_documento ALTER COLUMN id SET DEFAULT nextval('cadastro.tipo_documento_id_seq'::regclass);


--
-- TOC entry 3047 (class 2604 OID 68323)
-- Name: categoria_aviso_edital id; Type: DEFAULT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.categoria_aviso_edital ALTER COLUMN id SET DEFAULT nextval('configuracao.categoria_aviso_edital_id_seq'::regclass);


--
-- TOC entry 3048 (class 2604 OID 68331)
-- Name: grupo_acesso id; Type: DEFAULT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso ALTER COLUMN id SET DEFAULT nextval('configuracao.grupo_acesso_id_seq'::regclass);


--
-- TOC entry 3049 (class 2604 OID 68339)
-- Name: grupo_acesso_permissao id; Type: DEFAULT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso_permissao ALTER COLUMN id SET DEFAULT nextval('configuracao.grupo_acesso_permissao_id_seq'::regclass);


--
-- TOC entry 3050 (class 2604 OID 68347)
-- Name: permissao id; Type: DEFAULT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.permissao ALTER COLUMN id SET DEFAULT nextval('configuracao.permissao_id_seq'::regclass);


--
-- TOC entry 3051 (class 2604 OID 68358)
-- Name: pessoa id; Type: DEFAULT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.pessoa ALTER COLUMN id SET DEFAULT nextval('configuracao.pessoa_id_seq'::regclass);


--
-- TOC entry 3052 (class 2604 OID 68417)
-- Name: cidade id; Type: DEFAULT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.cidade ALTER COLUMN id SET DEFAULT nextval('endereco.cidade_id_seq'::regclass);


--
-- TOC entry 3053 (class 2604 OID 68425)
-- Name: endereco id; Type: DEFAULT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.endereco ALTER COLUMN id SET DEFAULT nextval('endereco.endereco_id_seq'::regclass);


--
-- TOC entry 3054 (class 2604 OID 68433)
-- Name: estado id; Type: DEFAULT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.estado ALTER COLUMN id SET DEFAULT nextval('endereco.estado_id_seq'::regclass);


--
-- TOC entry 3055 (class 2604 OID 68441)
-- Name: pais id; Type: DEFAULT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.pais ALTER COLUMN id SET DEFAULT nextval('endereco.pais_id_seq'::regclass);


--
-- TOC entry 3056 (class 2604 OID 68502)
-- Name: categoria_fornecedor id; Type: DEFAULT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.categoria_fornecedor ALTER COLUMN id SET DEFAULT nextval('fornecedor.categoria_fornecedor_id_seq'::regclass);


--
-- TOC entry 3057 (class 2604 OID 68510)
-- Name: conta_bancaria id; Type: DEFAULT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.conta_bancaria ALTER COLUMN id SET DEFAULT nextval('fornecedor.conta_bancaria_id_seq'::regclass);


--
-- TOC entry 3058 (class 2604 OID 68518)
-- Name: dados_recebimento id; Type: DEFAULT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.dados_recebimento ALTER COLUMN id SET DEFAULT nextval('fornecedor.dados_recebimento_id_seq'::regclass);


--
-- TOC entry 3059 (class 2604 OID 68531)
-- Name: fornecedor id; Type: DEFAULT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor ALTER COLUMN id SET DEFAULT nextval('fornecedor.fornecedor_id_seq'::regclass);


--
-- TOC entry 3060 (class 2604 OID 68611)
-- Name: revision id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.revision ALTER COLUMN id SET DEFAULT nextval('public.revision_id_seq'::regclass);


--
-- TOC entry 3061 (class 2604 OID 68624)
-- Name: arquivo id; Type: DEFAULT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.arquivo ALTER COLUMN id SET DEFAULT nextval('publicacao.arquivo_id_seq'::regclass);


--
-- TOC entry 3062 (class 2604 OID 68650)
-- Name: publicacao id; Type: DEFAULT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.publicacao ALTER COLUMN id SET DEFAULT nextval('publicacao.publicacao_id_seq'::regclass);


--
-- TOC entry 3415 (class 0 OID 68245)
-- Dependencies: 207
-- Data for Name: banco; Type: TABLE DATA; Schema: cadastro; Owner: -
--

INSERT INTO cadastro.banco VALUES (1, '2019-10-23 14:13:12.450229', NULL, '001', 'BANCO DO BRASIL S/A');
INSERT INTO cadastro.banco VALUES (2, '2019-10-23 14:13:12.450229', NULL, '002', 'BANCO CENTRAL DO BRASIL');
INSERT INTO cadastro.banco VALUES (3, '2019-10-23 14:13:12.450229', NULL, '003', 'BANCO DA AMAZONIA S.A');
INSERT INTO cadastro.banco VALUES (4, '2019-10-23 14:13:12.450229', NULL, '004', 'BANCO DO NORDESTE DO BRASIL S.A');
INSERT INTO cadastro.banco VALUES (7, '2019-10-23 14:13:12.450229', NULL, '007', 'BANCO NAC DESENV. ECO. SOCIAL S.A');
INSERT INTO cadastro.banco VALUES (8, '2019-10-23 14:13:12.450229', NULL, '008', 'BANCO MERIDIONAL DO BRASIL');
INSERT INTO cadastro.banco VALUES (20, '2019-10-23 14:13:12.450229', NULL, '020', 'BANCO DO ESTADO DE ALAGOAS S.A');
INSERT INTO cadastro.banco VALUES (21, '2019-10-23 14:13:12.450229', NULL, '021', 'BANCO DO ESTADO DO ESPIRITO SANTO S.A');
INSERT INTO cadastro.banco VALUES (22, '2019-10-23 14:13:12.450229', NULL, '022', 'BANCO DE CREDITO REAL DE MINAS GERAIS SA');
INSERT INTO cadastro.banco VALUES (24, '2019-10-23 14:13:12.450229', NULL, '024', 'BANCO DO ESTADO DE PERNAMBUCO');
INSERT INTO cadastro.banco VALUES (25, '2019-10-23 14:13:12.450229', NULL, '025', 'BANCO ALFA S/A');
INSERT INTO cadastro.banco VALUES (26, '2019-10-23 14:13:12.450229', NULL, '026', 'BANCO DO ESTADO DO ACRE S.A');
INSERT INTO cadastro.banco VALUES (27, '2019-10-23 14:13:12.450229', NULL, '027', 'BANCO DO ESTADO DE SANTA CATARINA S.A');
INSERT INTO cadastro.banco VALUES (28, '2019-10-23 14:13:12.450229', NULL, '028', 'BANCO DO ESTADO DA BAHIA S.A');
INSERT INTO cadastro.banco VALUES (29, '2019-10-23 14:13:12.450229', NULL, '029', 'BANCO DO ESTADO DO RIO DE JANEIRO S.A');
INSERT INTO cadastro.banco VALUES (30, '2019-10-23 14:13:12.450229', NULL, '030', 'BANCO DO ESTADO DA PARAIBA S.A');
INSERT INTO cadastro.banco VALUES (31, '2019-10-23 14:13:12.450229', NULL, '031', 'BANCO DO ESTADO DE GOIAS S.A');
INSERT INTO cadastro.banco VALUES (32, '2019-10-23 14:13:12.450229', NULL, '032', 'BANCO DO ESTADO DO MATO GROSSO S.A.');
INSERT INTO cadastro.banco VALUES (33, '2019-10-23 14:13:12.450229', NULL, '033', 'BANCO DO ESTADO DE SAO PAULO S.A');
INSERT INTO cadastro.banco VALUES (34, '2019-10-23 14:13:12.450229', NULL, '034', 'BANCO DO ESADO DO AMAZONAS S.A');
INSERT INTO cadastro.banco VALUES (35, '2019-10-23 14:13:12.450229', NULL, '035', 'BANCO DO ESTADO DO CEARA S.A');
INSERT INTO cadastro.banco VALUES (36, '2019-10-23 14:13:12.450229', NULL, '036', 'BANCO DO ESTADO DO MARANHAO S.A');
INSERT INTO cadastro.banco VALUES (37, '2019-10-23 14:13:12.450229', NULL, '037', 'BANCO DO ESTADO DO PARA S.A');
INSERT INTO cadastro.banco VALUES (38, '2019-10-23 14:13:12.450229', NULL, '038', 'BANCO DO ESTADO DO PARANA S.A');
INSERT INTO cadastro.banco VALUES (39, '2019-10-23 14:13:12.450229', NULL, '039', 'BANCO DO ESTADO DO PIAUI S.A');
INSERT INTO cadastro.banco VALUES (41, '2019-10-23 14:13:12.450229', NULL, '041', 'BANCO DO ESTADO DO RIO GRANDE DO SUL S.A');
INSERT INTO cadastro.banco VALUES (47, '2019-10-23 14:13:12.450229', NULL, '047', 'BANCO DO ESTADO DE SERGIPE S.A');
INSERT INTO cadastro.banco VALUES (48, '2019-10-23 14:13:12.450229', NULL, '048', 'BANCO DO ESTADO DE MINAS GERAIS S.A');
INSERT INTO cadastro.banco VALUES (59, '2019-10-23 14:13:12.450229', NULL, '059', 'BANCO DO ESTADO DE RONDONIA S.A');
INSERT INTO cadastro.banco VALUES (70, '2019-10-23 14:13:12.450229', NULL, '070', 'BANCO DE BRASILIA S.A');
INSERT INTO cadastro.banco VALUES (104, '2019-10-23 14:13:12.450229', NULL, '104', 'CAIXA ECONOMICA FEDERAL');
INSERT INTO cadastro.banco VALUES (106, '2019-10-23 14:13:12.450229', NULL, '106', 'BANCO ITABANCO S.A.');
INSERT INTO cadastro.banco VALUES (107, '2019-10-23 14:13:12.450229', NULL, '107', 'BANCO BBM S.A');
INSERT INTO cadastro.banco VALUES (109, '2019-10-23 14:13:12.450229', NULL, '109', 'BANCO CREDIBANCO S.A');
INSERT INTO cadastro.banco VALUES (116, '2019-10-23 14:13:12.450229', NULL, '116', 'BANCO B.N.L DO BRASIL S.A');
INSERT INTO cadastro.banco VALUES (148, '2019-10-23 14:13:12.450229', NULL, '148', 'MULTI BANCO S.A');
INSERT INTO cadastro.banco VALUES (151, '2019-10-23 14:13:12.450229', NULL, '151', 'CAIXA ECONOMICA DO ESTADO DE SAO PAULO');
INSERT INTO cadastro.banco VALUES (153, '2019-10-23 14:13:12.450229', NULL, '153', 'CAIXA ECONOMICA DO ESTADO DO R.G.SUL');
INSERT INTO cadastro.banco VALUES (165, '2019-10-23 14:13:12.450229', NULL, '165', 'BANCO NORCHEM S.A');
INSERT INTO cadastro.banco VALUES (166, '2019-10-23 14:13:12.450229', NULL, '166', 'BANCO INTER-ATLANTICO S.A');
INSERT INTO cadastro.banco VALUES (168, '2019-10-23 14:13:12.450229', NULL, '168', 'BANCO C.C.F. BRASIL S.A');
INSERT INTO cadastro.banco VALUES (175, '2019-10-23 14:13:12.450229', NULL, '175', 'CONTINENTAL BANCO S.A');
INSERT INTO cadastro.banco VALUES (184, '2019-10-23 14:13:12.450229', NULL, '184', 'BBA - CREDITANSTALT S.A');
INSERT INTO cadastro.banco VALUES (199, '2019-10-23 14:13:12.450229', NULL, '199', 'BANCO FINANCIAL PORTUGUES');
INSERT INTO cadastro.banco VALUES (200, '2019-10-23 14:13:12.450229', NULL, '200', 'BANCO FRICRISA AXELRUD S.A');
INSERT INTO cadastro.banco VALUES (201, '2019-10-23 14:13:12.450229', NULL, '201', 'BANCO AUGUSTA INDUSTRIA E COMERCIAL S.A');
INSERT INTO cadastro.banco VALUES (204, '2019-10-23 14:13:12.450229', NULL, '204', 'BANCO S.R.L S.A');
INSERT INTO cadastro.banco VALUES (205, '2019-10-23 14:13:12.450229', NULL, '205', 'BANCO SUL AMERICA S.A');
INSERT INTO cadastro.banco VALUES (206, '2019-10-23 14:13:12.450229', NULL, '206', 'BANCO MARTINELLI S.A');
INSERT INTO cadastro.banco VALUES (208, '2019-10-23 14:13:12.450229', NULL, '208', 'BANCO PACTUAL S.A');
INSERT INTO cadastro.banco VALUES (210, '2019-10-23 14:13:12.450229', NULL, '210', 'DEUTSCH SUDAMERIKANICHE BANK AG');
INSERT INTO cadastro.banco VALUES (211, '2019-10-23 14:13:12.450229', NULL, '211', 'BANCO SISTEMA S.A');
INSERT INTO cadastro.banco VALUES (212, '2019-10-23 14:13:12.450229', NULL, '212', 'BANCO MATONE S.A');
INSERT INTO cadastro.banco VALUES (213, '2019-10-23 14:13:12.450229', NULL, '213', 'BANCO ARBI S.A');
INSERT INTO cadastro.banco VALUES (214, '2019-10-23 14:13:12.450229', NULL, '214', 'BANCO DIBENS S.A');
INSERT INTO cadastro.banco VALUES (215, '2019-10-23 14:13:12.450229', NULL, '215', 'BANCO AMERICA DO SUL S.A');
INSERT INTO cadastro.banco VALUES (216, '2019-10-23 14:13:12.450229', NULL, '216', 'BANCO REGIONAL MALCON S.A');
INSERT INTO cadastro.banco VALUES (217, '2019-10-23 14:13:12.450229', NULL, '217', 'BANCO AGROINVEST S.A');
INSERT INTO cadastro.banco VALUES (218, '2019-10-23 14:13:12.450229', NULL, '218', 'BBS - BANCO BONSUCESSO S.A.');
INSERT INTO cadastro.banco VALUES (219, '2019-10-23 14:13:12.450229', NULL, '219', 'BANCO DE CREDITO DE SAO PAULO S.A');
INSERT INTO cadastro.banco VALUES (220, '2019-10-23 14:13:12.450229', NULL, '220', 'BANCO CREFISUL');
INSERT INTO cadastro.banco VALUES (221, '2019-10-23 14:13:12.450229', NULL, '221', 'BANCO GRAPHUS S.A');
INSERT INTO cadastro.banco VALUES (222, '2019-10-23 14:13:12.450229', NULL, '222', 'BANCO AGF BRASIL S. A.');
INSERT INTO cadastro.banco VALUES (223, '2019-10-23 14:13:12.450229', NULL, '223', 'BANCO INTERUNION S.A');
INSERT INTO cadastro.banco VALUES (224, '2019-10-23 14:13:12.450229', NULL, '224', 'BANCO FIBRA S.A');
INSERT INTO cadastro.banco VALUES (225, '2019-10-23 14:13:12.450229', NULL, '225', 'BANCO BRASCAN S.A');
INSERT INTO cadastro.banco VALUES (228, '2019-10-23 14:13:12.450229', NULL, '228', 'BANCO ICATU S.A');
INSERT INTO cadastro.banco VALUES (229, '2019-10-23 14:13:12.450229', NULL, '229', 'BANCO CRUZEIRO S.A');
INSERT INTO cadastro.banco VALUES (230, '2019-10-23 14:13:12.450229', NULL, '230', 'BANCO BANDEIRANTES S.A');
INSERT INTO cadastro.banco VALUES (231, '2019-10-23 14:13:12.450229', NULL, '231', 'BANCO BOAVISTA S.A');
INSERT INTO cadastro.banco VALUES (232, '2019-10-23 14:13:12.450229', NULL, '232', 'BANCO INTERPART S.A');
INSERT INTO cadastro.banco VALUES (233, '2019-10-23 14:13:12.450229', NULL, '233', 'BANCO MAPPIN S.A');
INSERT INTO cadastro.banco VALUES (234, '2019-10-23 14:13:12.450229', NULL, '234', 'BANCO LAVRA S.A.');
INSERT INTO cadastro.banco VALUES (235, '2019-10-23 14:13:12.450229', NULL, '235', 'BANCO LIBERAL S.A');
INSERT INTO cadastro.banco VALUES (236, '2019-10-23 14:13:12.450229', NULL, '236', 'BANCO CAMBIAL S.A');
INSERT INTO cadastro.banco VALUES (237, '2019-10-23 14:13:12.450229', NULL, '237', 'BANCO BRADESCO S.A');
INSERT INTO cadastro.banco VALUES (239, '2019-10-23 14:13:12.450229', NULL, '239', 'BANCO BANCRED S.A');
INSERT INTO cadastro.banco VALUES (240, '2019-10-23 14:13:12.450229', NULL, '240', 'BANCO DE CREDITO REAL DE MINAS GERAIS S.');
INSERT INTO cadastro.banco VALUES (241, '2019-10-23 14:13:12.450229', NULL, '241', 'BANCO CLASSICO S.A');
INSERT INTO cadastro.banco VALUES (242, '2019-10-23 14:13:12.450229', NULL, '242', 'BANCO EUROINVEST S.A');
INSERT INTO cadastro.banco VALUES (243, '2019-10-23 14:13:12.450229', NULL, '243', 'BANCO STOCK S.A');
INSERT INTO cadastro.banco VALUES (244, '2019-10-23 14:13:12.450229', NULL, '244', 'BANCO CIDADE S.A');
INSERT INTO cadastro.banco VALUES (245, '2019-10-23 14:13:12.450229', NULL, '245', 'BANCO EMPRESARIAL S.A');
INSERT INTO cadastro.banco VALUES (246, '2019-10-23 14:13:12.450229', NULL, '246', 'BANCO ABC ROMA S.A');
INSERT INTO cadastro.banco VALUES (247, '2019-10-23 14:13:12.450229', NULL, '247', 'BANCO OMEGA S.A');
INSERT INTO cadastro.banco VALUES (249, '2019-10-23 14:13:12.450229', NULL, '249', 'BANCO INVESTCRED S.A');
INSERT INTO cadastro.banco VALUES (250, '2019-10-23 14:13:12.450229', NULL, '250', 'BANCO SCHAHIN CURY S.A');
INSERT INTO cadastro.banco VALUES (251, '2019-10-23 14:13:12.450229', NULL, '251', 'BANCO SAO JORGE S.A.');
INSERT INTO cadastro.banco VALUES (252, '2019-10-23 14:13:12.450229', NULL, '252', 'BANCO FININVEST S.A');
INSERT INTO cadastro.banco VALUES (254, '2019-10-23 14:13:12.450229', NULL, '254', 'BANCO PARANA BANCO S.A');
INSERT INTO cadastro.banco VALUES (255, '2019-10-23 14:13:12.450229', NULL, '255', 'MILBANCO S.A.');
INSERT INTO cadastro.banco VALUES (256, '2019-10-23 14:13:12.450229', NULL, '256', 'BANCO GULVINVEST S.A');
INSERT INTO cadastro.banco VALUES (258, '2019-10-23 14:13:12.450229', NULL, '258', 'BANCO INDUSCRED S.A');
INSERT INTO cadastro.banco VALUES (261, '2019-10-23 14:13:12.450229', NULL, '261', 'BANCO VARIG S.A');
INSERT INTO cadastro.banco VALUES (262, '2019-10-23 14:13:12.450229', NULL, '262', 'BANCO BOREAL S.A');
INSERT INTO cadastro.banco VALUES (263, '2019-10-23 14:13:12.450229', NULL, '263', 'BANCO CACIQUE');
INSERT INTO cadastro.banco VALUES (264, '2019-10-23 14:13:12.450229', NULL, '264', 'BANCO PERFORMANCE S.A');
INSERT INTO cadastro.banco VALUES (265, '2019-10-23 14:13:12.450229', NULL, '265', 'BANCO FATOR S.A');
INSERT INTO cadastro.banco VALUES (266, '2019-10-23 14:13:12.450229', NULL, '266', 'BANCO CEDULA S.A');
INSERT INTO cadastro.banco VALUES (267, '2019-10-23 14:13:12.450229', NULL, '267', 'BANCO BBM-COM.C.IMOB.CFI S.A.');
INSERT INTO cadastro.banco VALUES (275, '2019-10-23 14:13:12.450229', NULL, '275', 'BANCO REAL S.A');
INSERT INTO cadastro.banco VALUES (277, '2019-10-23 14:13:12.450229', NULL, '277', 'BANCO PLANIBANC S.A');
INSERT INTO cadastro.banco VALUES (282, '2019-10-23 14:13:12.450229', NULL, '282', 'BANCO BRASILEIRO COMERCIAL');
INSERT INTO cadastro.banco VALUES (291, '2019-10-23 14:13:12.450229', NULL, '291', 'BANCO DE CREDITO NACIONAL S.A');
INSERT INTO cadastro.banco VALUES (294, '2019-10-23 14:13:12.450229', NULL, '294', 'BCR - BANCO DE CREDITO REAL S.A');
INSERT INTO cadastro.banco VALUES (295, '2019-10-23 14:13:12.450229', NULL, '295', 'BANCO CREDIPLAN S.A');
INSERT INTO cadastro.banco VALUES (300, '2019-10-23 14:13:12.450229', NULL, '300', 'BANCO DE LA NACION ARGENTINA S.A');
INSERT INTO cadastro.banco VALUES (302, '2019-10-23 14:13:12.450229', NULL, '302', 'BANCO DO PROGRESSO S.A');
INSERT INTO cadastro.banco VALUES (303, '2019-10-23 14:13:12.450229', NULL, '303', 'BANCO HNF S.A.');
INSERT INTO cadastro.banco VALUES (304, '2019-10-23 14:13:12.450229', NULL, '304', 'BANCO PONTUAL S.A');
INSERT INTO cadastro.banco VALUES (308, '2019-10-23 14:13:12.450229', NULL, '308', 'BANCO COMERCIAL BANCESA S.A.');
INSERT INTO cadastro.banco VALUES (318, '2019-10-23 14:13:12.450229', NULL, '318', 'BANCO B.M.G. S.A');
INSERT INTO cadastro.banco VALUES (320, '2019-10-23 14:13:12.450229', NULL, '320', 'BANCO INDUSTRIAL E COMERCIAL');
INSERT INTO cadastro.banco VALUES (341, '2019-10-23 14:13:12.450229', NULL, '341', 'BANCO ITAU S.A');
INSERT INTO cadastro.banco VALUES (346, '2019-10-23 14:13:12.450229', NULL, '346', 'BANCO FRANCES E BRASILEIRO S.A');
INSERT INTO cadastro.banco VALUES (347, '2019-10-23 14:13:12.450229', NULL, '347', 'BANCO SUDAMERIS BRASIL S.A');
INSERT INTO cadastro.banco VALUES (351, '2019-10-23 14:13:12.450229', NULL, '351', 'BANCO BOZANO SIMONSEN S.A');
INSERT INTO cadastro.banco VALUES (353, '2019-10-23 14:13:12.450229', NULL, '353', 'BANCO GERAL DO COMERCIO S.A');
INSERT INTO cadastro.banco VALUES (356, '2019-10-23 14:13:12.450229', NULL, '356', 'ABN AMRO S.A');
INSERT INTO cadastro.banco VALUES (366, '2019-10-23 14:13:12.450229', NULL, '366', 'BANCO SOGERAL S.A');
INSERT INTO cadastro.banco VALUES (369, '2019-10-23 14:13:12.450229', NULL, '369', 'PONTUAL');
INSERT INTO cadastro.banco VALUES (370, '2019-10-23 14:13:12.450229', NULL, '370', 'BEAL - BANCO EUROPEU PARA AMERICA LATINA');
INSERT INTO cadastro.banco VALUES (372, '2019-10-23 14:13:12.450229', NULL, '372', 'BANCO ITAMARATI S.A');
INSERT INTO cadastro.banco VALUES (375, '2019-10-23 14:13:12.450229', NULL, '375', 'BANCO FENICIA S.A');
INSERT INTO cadastro.banco VALUES (376, '2019-10-23 14:13:12.450229', NULL, '376', 'CHASE MANHATTAN BANK S.A');
INSERT INTO cadastro.banco VALUES (388, '2019-10-23 14:13:12.450229', NULL, '388', 'BANCO MERCANTIL DE DESCONTOS S/A');
INSERT INTO cadastro.banco VALUES (389, '2019-10-23 14:13:12.450229', NULL, '389', 'MERCANTIL S.A.');
INSERT INTO cadastro.banco VALUES (392, '2019-10-23 14:13:12.450229', NULL, '392', 'BANCO MERCANTIL DE SAO PAULO S.A');
INSERT INTO cadastro.banco VALUES (394, '2019-10-23 14:13:12.450229', NULL, '394', 'BANCO B.M.C. S.A');
INSERT INTO cadastro.banco VALUES (399, '2019-10-23 14:13:12.450229', NULL, '399', 'BANCO BAMERINDUS DO BRASIL S.A');
INSERT INTO cadastro.banco VALUES (409, '2019-10-23 14:13:12.450229', NULL, '409', 'UNIBANCO - UNIAO DOS BANCOS BRASILEIROS');
INSERT INTO cadastro.banco VALUES (412, '2019-10-23 14:13:12.450229', NULL, '412', 'BANCO NACIONAL DA BAHIA S.A');
INSERT INTO cadastro.banco VALUES (415, '2019-10-23 14:13:12.450229', NULL, '415', 'BANCO NACIONAL S.A');
INSERT INTO cadastro.banco VALUES (420, '2019-10-23 14:13:12.450229', NULL, '420', 'BANCO NACIONAL DO NORTE S.A');
INSERT INTO cadastro.banco VALUES (422, '2019-10-23 14:13:12.450229', NULL, '422', 'BANCO SAFRA S.A');
INSERT INTO cadastro.banco VALUES (424, '2019-10-23 14:13:12.450229', NULL, '424', 'BANCO NOROESTE S.A');
INSERT INTO cadastro.banco VALUES (434, '2019-10-23 14:13:12.450229', NULL, '434', 'BANCO FORTALEZA S.A');
INSERT INTO cadastro.banco VALUES (453, '2019-10-23 14:13:12.450229', NULL, '453', 'BANCO RURAL S.A');
INSERT INTO cadastro.banco VALUES (456, '2019-10-23 14:13:12.450229', NULL, '456', 'BANCO TOKIO S.A');
INSERT INTO cadastro.banco VALUES (464, '2019-10-23 14:13:12.450229', NULL, '464', 'BANCO SUMITOMO BRASILEIRO S.A');
INSERT INTO cadastro.banco VALUES (466, '2019-10-23 14:13:12.450229', NULL, '466', 'BANCO MITSUBISHI BRASILEIRO S.A');
INSERT INTO cadastro.banco VALUES (472, '2019-10-23 14:13:12.450229', NULL, '472', 'LLOYDS BANK PLC');
INSERT INTO cadastro.banco VALUES (473, '2019-10-23 14:13:12.450229', NULL, '473', 'BANCO FINANCIAL PORTUGUES S.A');
INSERT INTO cadastro.banco VALUES (477, '2019-10-23 14:13:12.450229', NULL, '477', 'CITIBANK N.A');
INSERT INTO cadastro.banco VALUES (479, '2019-10-23 14:13:12.450229', NULL, '479', 'BANCO DE BOSTON S.A');
INSERT INTO cadastro.banco VALUES (480, '2019-10-23 14:13:12.450229', NULL, '480', 'BANCO PORTUGUES DO ATLANTICO-BRASIL S.A');
INSERT INTO cadastro.banco VALUES (483, '2019-10-23 14:13:12.450229', NULL, '483', 'BANCO AGRIMISA S.A.');
INSERT INTO cadastro.banco VALUES (487, '2019-10-23 14:13:12.450229', NULL, '487', 'DEUTSCHE BANK S.A - BANCO ALEMAO');
INSERT INTO cadastro.banco VALUES (488, '2019-10-23 14:13:12.450229', NULL, '488', 'BANCO J. P. MORGAN S.A');
INSERT INTO cadastro.banco VALUES (489, '2019-10-23 14:13:12.450229', NULL, '489', 'BANESTO BANCO URUGAUAY S.A');
INSERT INTO cadastro.banco VALUES (492, '2019-10-23 14:13:12.450229', NULL, '492', 'INTERNATIONALE NEDERLANDEN BANK N.V.');
INSERT INTO cadastro.banco VALUES (493, '2019-10-23 14:13:12.450229', NULL, '493', 'BANCO UNION S.A.C.A');
INSERT INTO cadastro.banco VALUES (494, '2019-10-23 14:13:12.450229', NULL, '494', 'BANCO LA REP. ORIENTAL DEL URUGUAY');
INSERT INTO cadastro.banco VALUES (495, '2019-10-23 14:13:12.450229', NULL, '495', 'BANCO LA PROVINCIA DE BUENOS AIRES');
INSERT INTO cadastro.banco VALUES (496, '2019-10-23 14:13:12.450229', NULL, '496', 'BANCO EXTERIOR DE ESPANA S.A');
INSERT INTO cadastro.banco VALUES (498, '2019-10-23 14:13:12.450229', NULL, '498', 'CENTRO HISPANO BANCO');
INSERT INTO cadastro.banco VALUES (499, '2019-10-23 14:13:12.450229', NULL, '499', 'BANCO IOCHPE S.A');
INSERT INTO cadastro.banco VALUES (501, '2019-10-23 14:13:12.450229', NULL, '501', 'BANCO BRASILEIRO IRAQUIANO S.A.');
INSERT INTO cadastro.banco VALUES (502, '2019-10-23 14:13:12.450229', NULL, '502', 'BANCO SANTANDER S.A');
INSERT INTO cadastro.banco VALUES (504, '2019-10-23 14:13:12.450229', NULL, '504', 'BANCO MULTIPLIC S.A');
INSERT INTO cadastro.banco VALUES (505, '2019-10-23 14:13:12.450229', NULL, '505', 'BANCO GARANTIA S.A');
INSERT INTO cadastro.banco VALUES (600, '2019-10-23 14:13:12.450229', NULL, '600', 'BANCO LUSO BRASILEIRO S.A');
INSERT INTO cadastro.banco VALUES (601, '2019-10-23 14:13:12.450229', NULL, '601', 'BFC BANCO S.A.');
INSERT INTO cadastro.banco VALUES (602, '2019-10-23 14:13:12.450229', NULL, '602', 'BANCO PATENTE S.A');
INSERT INTO cadastro.banco VALUES (604, '2019-10-23 14:13:12.450229', NULL, '604', 'BANCO INDUSTRIAL DO BRASIL S.A');
INSERT INTO cadastro.banco VALUES (607, '2019-10-23 14:13:12.450229', NULL, '607', 'BANCO SANTOS NEVES S.A');
INSERT INTO cadastro.banco VALUES (608, '2019-10-23 14:13:12.450229', NULL, '608', 'BANCO OPEN S.A');
INSERT INTO cadastro.banco VALUES (610, '2019-10-23 14:13:12.450229', NULL, '610', 'BANCO V.R. S.A');
INSERT INTO cadastro.banco VALUES (611, '2019-10-23 14:13:12.450229', NULL, '611', 'BANCO PAULISTA S.A');
INSERT INTO cadastro.banco VALUES (612, '2019-10-23 14:13:12.450229', NULL, '612', 'BANCO GUANABARA S.A');
INSERT INTO cadastro.banco VALUES (613, '2019-10-23 14:13:12.450229', NULL, '613', 'BANCO PECUNIA S.A');
INSERT INTO cadastro.banco VALUES (616, '2019-10-23 14:13:12.450229', NULL, '616', 'BANCO INTERPACIFICO S.A');
INSERT INTO cadastro.banco VALUES (617, '2019-10-23 14:13:12.450229', NULL, '617', 'BANCO INVESTOR S.A.');
INSERT INTO cadastro.banco VALUES (618, '2019-10-23 14:13:12.450229', NULL, '618', 'BANCO TENDENCIA S.A');
INSERT INTO cadastro.banco VALUES (621, '2019-10-23 14:13:12.450229', NULL, '621', 'BANCO APLICAP S.A.');
INSERT INTO cadastro.banco VALUES (622, '2019-10-23 14:13:12.450229', NULL, '622', 'BANCO DRACMA S.A');
INSERT INTO cadastro.banco VALUES (623, '2019-10-23 14:13:12.450229', NULL, '623', 'BANCO PANAMERICANO S.A');
INSERT INTO cadastro.banco VALUES (624, '2019-10-23 14:13:12.450229', NULL, '624', 'BANCO GENERAL MOTORS S.A');
INSERT INTO cadastro.banco VALUES (625, '2019-10-23 14:13:12.450229', NULL, '625', 'BANCO ARAUCARIA S.A');
INSERT INTO cadastro.banco VALUES (626, '2019-10-23 14:13:12.450229', NULL, '626', 'BANCO FICSA S.A');
INSERT INTO cadastro.banco VALUES (627, '2019-10-23 14:13:12.450229', NULL, '627', 'BANCO DESTAK S.A');
INSERT INTO cadastro.banco VALUES (628, '2019-10-23 14:13:12.450229', NULL, '628', 'BANCO CRITERIUM S.A');
INSERT INTO cadastro.banco VALUES (629, '2019-10-23 14:13:12.450229', NULL, '629', 'BANCORP BANCO COML. E. DE INVESTMENTO');
INSERT INTO cadastro.banco VALUES (630, '2019-10-23 14:13:12.450229', NULL, '630', 'BANCO INTERCAP S.A');
INSERT INTO cadastro.banco VALUES (633, '2019-10-23 14:13:12.450229', NULL, '633', 'BANCO REDIMENTO S.A');
INSERT INTO cadastro.banco VALUES (634, '2019-10-23 14:13:12.450229', NULL, '634', 'BANCO TRIANGULO S.A');
INSERT INTO cadastro.banco VALUES (635, '2019-10-23 14:13:12.450229', NULL, '635', 'BANCO DO ESTADO DO AMAPA S.A');
INSERT INTO cadastro.banco VALUES (637, '2019-10-23 14:13:12.450229', NULL, '637', 'BANCO SOFISA S.A');
INSERT INTO cadastro.banco VALUES (638, '2019-10-23 14:13:12.450229', NULL, '638', 'BANCO PROSPER S.A');
INSERT INTO cadastro.banco VALUES (639, '2019-10-23 14:13:12.450229', NULL, '639', 'BIG S.A. - BANCO IRMAOS GUIMARAES');
INSERT INTO cadastro.banco VALUES (640, '2019-10-23 14:13:12.450229', NULL, '640', 'BANCO DE CREDITO METROPOLITANO S.A');
INSERT INTO cadastro.banco VALUES (641, '2019-10-23 14:13:12.450229', NULL, '641', 'BANCO EXCEL ECONOMICO S/A');
INSERT INTO cadastro.banco VALUES (643, '2019-10-23 14:13:12.450229', NULL, '643', 'BANCO SEGMENTO S.A');
INSERT INTO cadastro.banco VALUES (645, '2019-10-23 14:13:12.450229', NULL, '645', 'BANCO DO ESTADO DE RORAIMA S.A');
INSERT INTO cadastro.banco VALUES (647, '2019-10-23 14:13:12.450229', NULL, '647', 'BANCO MARKA S.A');
INSERT INTO cadastro.banco VALUES (648, '2019-10-23 14:13:12.450229', NULL, '648', 'BANCO ATLANTIS S.A');
INSERT INTO cadastro.banco VALUES (649, '2019-10-23 14:13:12.450229', NULL, '649', 'BANCO DIMENSAO S.A');
INSERT INTO cadastro.banco VALUES (650, '2019-10-23 14:13:12.450229', NULL, '650', 'BANCO PEBB S.A');
INSERT INTO cadastro.banco VALUES (652, '2019-10-23 14:13:12.450229', NULL, '652', 'BANCO FRANCES E BRASILEIRO SA');
INSERT INTO cadastro.banco VALUES (653, '2019-10-23 14:13:12.450229', NULL, '653', 'BANCO INDUSVAL S.A');
INSERT INTO cadastro.banco VALUES (654, '2019-10-23 14:13:12.450229', NULL, '654', 'BANCO A. J. RENNER S.A');
INSERT INTO cadastro.banco VALUES (655, '2019-10-23 14:13:12.450229', NULL, '655', 'BANCO VOTORANTIM S.A.');
INSERT INTO cadastro.banco VALUES (656, '2019-10-23 14:13:12.450229', NULL, '656', 'BANCO MATRIX S.A');
INSERT INTO cadastro.banco VALUES (657, '2019-10-23 14:13:12.450229', NULL, '657', 'BANCO TECNICORP S.A');
INSERT INTO cadastro.banco VALUES (658, '2019-10-23 14:13:12.450229', NULL, '658', 'BANCO PORTO REAL S.A');
INSERT INTO cadastro.banco VALUES (702, '2019-10-23 14:13:12.450229', NULL, '702', 'BANCO SANTOS S.A');
INSERT INTO cadastro.banco VALUES (705, '2019-10-23 14:13:12.450229', NULL, '705', 'BANCO INVESTCORP S.A.');
INSERT INTO cadastro.banco VALUES (707, '2019-10-23 14:13:12.450229', NULL, '707', 'BANCO DAYCOVAL S.A');
INSERT INTO cadastro.banco VALUES (711, '2019-10-23 14:13:12.450229', NULL, '711', ' BANCO VETOR S.A.');
INSERT INTO cadastro.banco VALUES (713, '2019-10-23 14:13:12.450229', NULL, '713', 'BANCO CINDAM S.A');
INSERT INTO cadastro.banco VALUES (715, '2019-10-23 14:13:12.450229', NULL, '715', 'BANCO VEGA S.A');
INSERT INTO cadastro.banco VALUES (718, '2019-10-23 14:13:12.450229', NULL, '718', 'BANCO OPERADOR S.A');
INSERT INTO cadastro.banco VALUES (719, '2019-10-23 14:13:12.450229', NULL, '719', 'BANCO PRIMUS S.A');
INSERT INTO cadastro.banco VALUES (720, '2019-10-23 14:13:12.450229', NULL, '720', 'BANCO MAXINVEST S.A');
INSERT INTO cadastro.banco VALUES (721, '2019-10-23 14:13:12.450229', NULL, '721', 'BANCO CREDIBEL S.A');
INSERT INTO cadastro.banco VALUES (722, '2019-10-23 14:13:12.450229', NULL, '722', 'BANCO INTERIOR DE SAO PAULO S.A');
INSERT INTO cadastro.banco VALUES (724, '2019-10-23 14:13:12.450229', NULL, '724', 'BANCO PORTO SEGURO S.A');
INSERT INTO cadastro.banco VALUES (725, '2019-10-23 14:13:12.450229', NULL, '725', 'BANCO FINABANCO S.A');
INSERT INTO cadastro.banco VALUES (726, '2019-10-23 14:13:12.450229', NULL, '726', 'BANCO UNIVERSAL S.A');
INSERT INTO cadastro.banco VALUES (728, '2019-10-23 14:13:12.450229', NULL, '728', 'BANCO FITAL S.A');
INSERT INTO cadastro.banco VALUES (729, '2019-10-23 14:13:12.450229', NULL, '729', 'BANCO FONTE S.A');
INSERT INTO cadastro.banco VALUES (730, '2019-10-23 14:13:12.450229', NULL, '730', 'BANCO COMERCIAL PARAGUAYO S.A');
INSERT INTO cadastro.banco VALUES (731, '2019-10-23 14:13:12.450229', NULL, '731', 'BANCO GNPP S.A.');
INSERT INTO cadastro.banco VALUES (732, '2019-10-23 14:13:12.450229', NULL, '732', 'BANCO PREMIER S.A.');
INSERT INTO cadastro.banco VALUES (733, '2019-10-23 14:13:12.450229', NULL, '733', 'BANCO NACOES S.A.');
INSERT INTO cadastro.banco VALUES (734, '2019-10-23 14:13:12.450229', NULL, '734', 'BANCO GERDAU S.A');
INSERT INTO cadastro.banco VALUES (735, '2019-10-23 14:13:12.450229', NULL, '735', 'BACO POTENCIAL');
INSERT INTO cadastro.banco VALUES (736, '2019-10-23 14:13:12.450229', NULL, '736', 'BANCO UNITED S.A');
INSERT INTO cadastro.banco VALUES (737, '2019-10-23 14:13:12.450229', NULL, '737', 'THECA');
INSERT INTO cadastro.banco VALUES (738, '2019-10-23 14:13:12.450229', NULL, '738', 'MARADA');
INSERT INTO cadastro.banco VALUES (739, '2019-10-23 14:13:12.450229', NULL, '739', 'BGN');
INSERT INTO cadastro.banco VALUES (740, '2019-10-23 14:13:12.450229', NULL, '740', 'BCN BARCLAYS');
INSERT INTO cadastro.banco VALUES (741, '2019-10-23 14:13:12.450229', NULL, '741', 'BRP');
INSERT INTO cadastro.banco VALUES (742, '2019-10-23 14:13:12.450229', NULL, '742', 'EQUATORIAL');
INSERT INTO cadastro.banco VALUES (743, '2019-10-23 14:13:12.450229', NULL, '743', 'BANCO EMBLEMA S.A');
INSERT INTO cadastro.banco VALUES (744, '2019-10-23 14:13:12.450229', NULL, '744', 'THE FIRST NATIONAL BANK OF BOSTON');
INSERT INTO cadastro.banco VALUES (745, '2019-10-23 14:13:12.450229', NULL, '745', 'CITIBAN N.A.');
INSERT INTO cadastro.banco VALUES (746, '2019-10-23 14:13:12.450229', NULL, '746', 'MODAL S\A');
INSERT INTO cadastro.banco VALUES (747, '2019-10-23 14:13:12.450229', NULL, '747', 'RAIBOBANK DO BRASIL');
INSERT INTO cadastro.banco VALUES (748, '2019-10-23 14:13:12.450229', NULL, '748', 'SICREDI');
INSERT INTO cadastro.banco VALUES (749, '2019-10-23 14:13:12.450229', NULL, '749', 'BRMSANTIL SA');
INSERT INTO cadastro.banco VALUES (750, '2019-10-23 14:13:12.450229', NULL, '750', 'BANCO REPUBLIC NATIONAL OF NEW YORK (BRA');
INSERT INTO cadastro.banco VALUES (751, '2019-10-23 14:13:12.450229', NULL, '751', 'DRESDNER BANK LATEINAMERIKA-BRASIL S/A');
INSERT INTO cadastro.banco VALUES (752, '2019-10-23 14:13:12.450229', NULL, '752', 'BANCO BANQUE NATIONALE DE PARIS BRASIL S');
INSERT INTO cadastro.banco VALUES (753, '2019-10-23 14:13:12.450229', NULL, '753', 'BANCO COMERCIAL URUGUAI S.A.');
INSERT INTO cadastro.banco VALUES (755, '2019-10-23 14:13:12.450229', NULL, '755', 'BANCO MERRILL LYNCH S.A');
INSERT INTO cadastro.banco VALUES (756, '2019-10-23 14:13:12.450229', NULL, '756', 'BANCO COOPERATIVO DO BRASIL S.A.');
INSERT INTO cadastro.banco VALUES (757, '2019-10-23 14:13:12.450229', NULL, '757', 'BANCO KEB DO BRASIL S.A.');


--
-- TOC entry 3417 (class 0 OID 68253)
-- Dependencies: 209
-- Data for Name: categoria; Type: TABLE DATA; Schema: cadastro; Owner: -
--



--
-- TOC entry 3419 (class 0 OID 68261)
-- Dependencies: 211
-- Data for Name: tipo_cadastro; Type: TABLE DATA; Schema: cadastro; Owner: -
--



--
-- TOC entry 3421 (class 0 OID 68269)
-- Dependencies: 213
-- Data for Name: tipo_cadastro_tipo_documento; Type: TABLE DATA; Schema: cadastro; Owner: -
--



--
-- TOC entry 3423 (class 0 OID 68277)
-- Dependencies: 215
-- Data for Name: tipo_documento; Type: TABLE DATA; Schema: cadastro; Owner: -
--



--
-- TOC entry 3424 (class 0 OID 68290)
-- Dependencies: 216
-- Data for Name: banco_audit; Type: TABLE DATA; Schema: cadastro_audit; Owner: -
--



--
-- TOC entry 3425 (class 0 OID 68295)
-- Dependencies: 217
-- Data for Name: categoria_audit; Type: TABLE DATA; Schema: cadastro_audit; Owner: -
--



--
-- TOC entry 3426 (class 0 OID 68300)
-- Dependencies: 218
-- Data for Name: tipo_cadastro_audit; Type: TABLE DATA; Schema: cadastro_audit; Owner: -
--



--
-- TOC entry 3427 (class 0 OID 68305)
-- Dependencies: 219
-- Data for Name: tipo_cadastro_tipo_documento_audit; Type: TABLE DATA; Schema: cadastro_audit; Owner: -
--



--
-- TOC entry 3428 (class 0 OID 68310)
-- Dependencies: 220
-- Data for Name: tipo_documento_audit; Type: TABLE DATA; Schema: cadastro_audit; Owner: -
--



--
-- TOC entry 3430 (class 0 OID 68320)
-- Dependencies: 222
-- Data for Name: categoria_aviso_edital; Type: TABLE DATA; Schema: configuracao; Owner: -
--



--
-- TOC entry 3432 (class 0 OID 68328)
-- Dependencies: 224
-- Data for Name: grupo_acesso; Type: TABLE DATA; Schema: configuracao; Owner: -
--



--
-- TOC entry 3434 (class 0 OID 68336)
-- Dependencies: 226
-- Data for Name: grupo_acesso_permissao; Type: TABLE DATA; Schema: configuracao; Owner: -
--



--
-- TOC entry 3436 (class 0 OID 68344)
-- Dependencies: 228
-- Data for Name: permissao; Type: TABLE DATA; Schema: configuracao; Owner: -
--

INSERT INTO configuracao.permissao VALUES (1000, '2019-02-11 19:49:53.65032', NULL, 'tipos-documentos', 'Tipo de Documento', NULL);
INSERT INTO configuracao.permissao VALUES (10000, '2019-02-11 19:49:53.65032', NULL, 'tipos-documentos/post', 'Adicionar', 1000);
INSERT INTO configuracao.permissao VALUES (10001, '2019-02-11 19:49:53.65032', NULL, 'tipos-documentos/put', 'Alterar', 1000);
INSERT INTO configuracao.permissao VALUES (10002, '2019-02-11 19:49:53.65032', NULL, 'tipos-documentos/get', 'Consultar', 1000);
INSERT INTO configuracao.permissao VALUES (2000, '2019-02-11 19:49:53.65032', NULL, 'categorias', 'Categoria', NULL);
INSERT INTO configuracao.permissao VALUES (20000, '2019-02-11 19:49:53.65032', NULL, 'categorias/post', 'Adicionar', 2000);
INSERT INTO configuracao.permissao VALUES (20001, '2019-02-11 19:49:53.65032', NULL, 'categorias/put', 'Alterar', 2000);
INSERT INTO configuracao.permissao VALUES (4000, '2019-10-23 13:59:43.915071', NULL, 'usuarios', 'Usuario', NULL);
INSERT INTO configuracao.permissao VALUES (40000, '2019-10-23 13:59:43.939391', NULL, 'usuarios/post', 'Adicionar', 4000);
INSERT INTO configuracao.permissao VALUES (40001, '2019-10-23 13:59:43.969203', NULL, 'usuarios/put', 'Alterar', 4000);
INSERT INTO configuracao.permissao VALUES (40002, '2019-10-23 13:59:43.987528', NULL, 'usuarios/get', 'Consultar', 4000);
INSERT INTO configuracao.permissao VALUES (400001, '2019-10-23 13:59:44.029735', NULL, 'usuarios/put/change-password', 'Alterar senha', 40001);
INSERT INTO configuracao.permissao VALUES (400002, '2019-10-23 13:59:44.058356', NULL, 'usuarios/put/activate', 'Ativar/Desativar', 40001);
INSERT INTO configuracao.permissao VALUES (5000, '2019-10-23 13:59:44.08853', NULL, 'grupos-acesso', 'Grupo de Acesso', NULL);
INSERT INTO configuracao.permissao VALUES (50000, '2019-10-23 13:59:44.116429', NULL, 'grupos-acesso/post', 'Adicionar', 5000);
INSERT INTO configuracao.permissao VALUES (50001, '2019-10-23 13:59:44.135241', NULL, 'grupos-acesso/put', 'Alterar', 5000);
INSERT INTO configuracao.permissao VALUES (50002, '2019-10-23 13:59:44.154362', NULL, 'grupos-acesso/get', 'Consultar', 5000);
INSERT INTO configuracao.permissao VALUES (500001, '2019-10-23 13:59:44.183914', NULL, 'grupos-acesso/put/activate', 'Ativar', 50001);
INSERT INTO configuracao.permissao VALUES (50003, '2019-10-23 13:59:44.207101', NULL, 'grupos-acesso/delete', 'Remover', 5000);
INSERT INTO configuracao.permissao VALUES (6000, '2019-10-23 13:59:44.235251', NULL, 'avisos-contratacoes', 'Aviso de Contratação', NULL);
INSERT INTO configuracao.permissao VALUES (60000, '2019-10-23 13:59:44.251373', NULL, 'avisos-contratacoes/post', 'Adicionar', 6000);
INSERT INTO configuracao.permissao VALUES (60001, '2019-10-23 13:59:44.285332', NULL, 'avisos-contratacoes/put', 'Alterar', 6000);
INSERT INTO configuracao.permissao VALUES (7000, '2019-10-23 13:59:44.316663', NULL, 'extratos-contratos', 'Extrato de Contrato', NULL);
INSERT INTO configuracao.permissao VALUES (70000, '2019-10-23 13:59:44.339183', NULL, 'extratos-contratos/post', 'Adicionar', 7000);
INSERT INTO configuracao.permissao VALUES (70001, '2019-10-23 13:59:44.368473', NULL, 'extratos-contratos/put', 'Alterar', 7000);
INSERT INTO configuracao.permissao VALUES (8000, '2019-10-23 13:59:44.397907', NULL, 'avisos-editais', 'Aviso de Edital', NULL);
INSERT INTO configuracao.permissao VALUES (80000, '2019-10-23 13:59:44.434725', NULL, 'avisos-editais/post', 'Adicionar', 8000);
INSERT INTO configuracao.permissao VALUES (80001, '2019-10-23 13:59:44.458495', NULL, 'avisos-editais/put', 'Alterar', 8000);
INSERT INTO configuracao.permissao VALUES (9000, '2019-10-23 13:59:44.481112', NULL, 'tipos-cadastros', 'Tipos de Cadastro', NULL);
INSERT INTO configuracao.permissao VALUES (90001, '2019-10-23 13:59:44.505214', NULL, 'tipos-cadastros/post', 'Adicionar', 9000);
INSERT INTO configuracao.permissao VALUES (90002, '2019-10-23 13:59:44.529581', NULL, 'tipos-cadastros/put', 'Alterar', 9000);
INSERT INTO configuracao.permissao VALUES (900001, '2019-10-23 13:59:44.553728', NULL, 'tipos-cadastros/put/activate', 'Ativar', 90002);
INSERT INTO configuracao.permissao VALUES (11000, '2019-10-23 13:59:44.581116', NULL, 'fornecedores', 'Fornecedores', NULL);
INSERT INTO configuracao.permissao VALUES (110000, '2019-10-23 13:59:44.604195', NULL, 'fornecedores/get', 'Consultar', 11000);
INSERT INTO configuracao.permissao VALUES (110001, '2019-10-23 13:59:44.626442', NULL, 'fornecedores/post', 'Adicionar', 11000);
INSERT INTO configuracao.permissao VALUES (110002, '2019-10-23 13:59:44.651205', NULL, 'fornecedores/put', 'Alterar', 11000);
INSERT INTO configuracao.permissao VALUES (1100001, '2019-10-23 13:59:44.662395', NULL, 'fornecedores/put/approve', 'Aprovar', 110002);
INSERT INTO configuracao.permissao VALUES (100001, '2019-02-11 19:49:53.65032', NULL, 'tipos-documentos/put/activate', 'Ativar', 10001);
INSERT INTO configuracao.permissao VALUES (200001, '2019-02-11 19:49:53.65032', NULL, 'categorias/put/activate', 'Ativar', 20001);


--
-- TOC entry 3438 (class 0 OID 68355)
-- Dependencies: 230
-- Data for Name: pessoa; Type: TABLE DATA; Schema: configuracao; Owner: -
--

INSERT INTO configuracao.pessoa VALUES (1, '2019-10-23 10:59:04.838522', '2019-10-23 10:59:04.838522', NULL, 'Administrador');


--
-- TOC entry 3439 (class 0 OID 68361)
-- Dependencies: 231
-- Data for Name: usuario; Type: TABLE DATA; Schema: configuracao; Owner: -
--

INSERT INTO configuracao.usuario VALUES (true, true, NULL, 'admin@pti.org.br', false, '$2a$10$TOqJp/ppzyTK9WnCYt.WaeoeylYZKujnfhDQM0hRNbTk2AR8pqFQy', NULL, NULL, NULL, 1, NULL);


--
-- TOC entry 3440 (class 0 OID 68381)
-- Dependencies: 232
-- Data for Name: grupo_acesso_audit; Type: TABLE DATA; Schema: configuracao_audit; Owner: -
--



--
-- TOC entry 3441 (class 0 OID 68386)
-- Dependencies: 233
-- Data for Name: grupo_acesso_permissao_audit; Type: TABLE DATA; Schema: configuracao_audit; Owner: -
--



--
-- TOC entry 3442 (class 0 OID 68391)
-- Dependencies: 234
-- Data for Name: permissao_audit; Type: TABLE DATA; Schema: configuracao_audit; Owner: -
--



--
-- TOC entry 3443 (class 0 OID 68399)
-- Dependencies: 235
-- Data for Name: pessoa_audit; Type: TABLE DATA; Schema: configuracao_audit; Owner: -
--

INSERT INTO configuracao_audit.pessoa_audit VALUES (1, 1, 0, NULL, 'Administrador');


--
-- TOC entry 3444 (class 0 OID 68404)
-- Dependencies: 236
-- Data for Name: usuario_audit; Type: TABLE DATA; Schema: configuracao_audit; Owner: -
--

INSERT INTO configuracao_audit.usuario_audit VALUES (1, 1, true, true, NULL, 'admin@pti.org.br', false, '$2a$10$TOqJp/ppzyTK9WnCYt.WaeoeylYZKujnfhDQM0hRNbTk2AR8pqFQy', NULL, NULL, NULL, NULL);


--
-- TOC entry 3446 (class 0 OID 68414)
-- Dependencies: 238
-- Data for Name: cidade; Type: TABLE DATA; Schema: endereco; Owner: -
--

INSERT INTO endereco.cidade VALUES (3, '2019-10-23 14:05:31.168533', NULL, 'Brasiléia', 1);
INSERT INTO endereco.cidade VALUES (5, '2019-10-23 14:05:31.168533', NULL, 'Capixaba', 1);
INSERT INTO endereco.cidade VALUES (7, '2019-10-23 14:05:31.168533', NULL, 'Epitaciolândia', 1);
INSERT INTO endereco.cidade VALUES (8, '2019-10-23 14:05:31.168533', NULL, 'Feijó', 1);
INSERT INTO endereco.cidade VALUES (10, '2019-10-23 14:05:31.168533', NULL, 'Mâncio Lima', 1);
INSERT INTO endereco.cidade VALUES (12, '2019-10-23 14:05:31.168533', NULL, 'Marechal Thaumaturgo', 1);
INSERT INTO endereco.cidade VALUES (14, '2019-10-23 14:05:31.168533', NULL, 'Porto Acre', 1);
INSERT INTO endereco.cidade VALUES (15, '2019-10-23 14:05:31.168533', NULL, 'Porto Walter', 1);
INSERT INTO endereco.cidade VALUES (17, '2019-10-23 14:05:31.168533', NULL, 'Rodrigues Alves', 1);
INSERT INTO endereco.cidade VALUES (19, '2019-10-23 14:05:31.168533', NULL, 'Sena Madureira', 1);
INSERT INTO endereco.cidade VALUES (21, '2019-10-23 14:05:31.168533', NULL, 'Tarauacá', 1);
INSERT INTO endereco.cidade VALUES (22, '2019-10-23 14:05:31.168533', NULL, 'Xapuri', 1);
INSERT INTO endereco.cidade VALUES (24, '2019-10-23 14:05:31.168533', NULL, 'Anadia', 2);
INSERT INTO endereco.cidade VALUES (26, '2019-10-23 14:05:31.168533', NULL, 'Atalaia', 2);
INSERT INTO endereco.cidade VALUES (28, '2019-10-23 14:05:31.168533', NULL, 'Barra De São Miguel', 2);
INSERT INTO endereco.cidade VALUES (29, '2019-10-23 14:05:31.168533', NULL, 'Batalha', 2);
INSERT INTO endereco.cidade VALUES (31, '2019-10-23 14:05:31.168533', NULL, 'Belo Monte', 2);
INSERT INTO endereco.cidade VALUES (33, '2019-10-23 14:05:31.168533', NULL, 'Branquinha', 2);
INSERT INTO endereco.cidade VALUES (34, '2019-10-23 14:05:31.168533', NULL, 'Cacimbinhas', 2);
INSERT INTO endereco.cidade VALUES (36, '2019-10-23 14:05:31.168533', NULL, 'Campestre', 2);
INSERT INTO endereco.cidade VALUES (38, '2019-10-23 14:05:31.168533', NULL, 'Campo Grande', 2);
INSERT INTO endereco.cidade VALUES (40, '2019-10-23 14:05:31.168533', NULL, 'Capela', 2);
INSERT INTO endereco.cidade VALUES (41, '2019-10-23 14:05:31.168533', NULL, 'Carneiros', 2);
INSERT INTO endereco.cidade VALUES (43, '2019-10-23 14:05:31.168533', NULL, 'Coité Do Nóia', 2);
INSERT INTO endereco.cidade VALUES (45, '2019-10-23 14:05:31.168533', NULL, 'Coqueiro Seco', 2);
INSERT INTO endereco.cidade VALUES (47, '2019-10-23 14:05:31.168533', NULL, 'Craíbas', 2);
INSERT INTO endereco.cidade VALUES (49, '2019-10-23 14:05:31.168533', NULL, 'Dois Riachos', 2);
INSERT INTO endereco.cidade VALUES (50, '2019-10-23 14:05:31.168533', NULL, 'Estrela De Alagoas', 2);
INSERT INTO endereco.cidade VALUES (52, '2019-10-23 14:05:31.168533', NULL, 'Feliz Deserto', 2);
INSERT INTO endereco.cidade VALUES (54, '2019-10-23 14:05:31.168533', NULL, 'Girau Do Ponciano', 2);
INSERT INTO endereco.cidade VALUES (55, '2019-10-23 14:05:31.168533', NULL, 'Ibateguara', 2);
INSERT INTO endereco.cidade VALUES (57, '2019-10-23 14:05:31.168533', NULL, 'Igreja Nova', 2);
INSERT INTO endereco.cidade VALUES (59, '2019-10-23 14:05:31.168533', NULL, 'Jacaré Dos Homens', 2);
INSERT INTO endereco.cidade VALUES (61, '2019-10-23 14:05:31.168533', NULL, 'Japaratinga', 2);
INSERT INTO endereco.cidade VALUES (63, '2019-10-23 14:05:31.168533', NULL, 'Jequiá Da Praia', 2);
INSERT INTO endereco.cidade VALUES (64, '2019-10-23 14:05:31.168533', NULL, 'Joaquim Gomes', 2);
INSERT INTO endereco.cidade VALUES (66, '2019-10-23 14:05:31.168533', NULL, 'Junqueiro', 2);
INSERT INTO endereco.cidade VALUES (68, '2019-10-23 14:05:31.168533', NULL, 'Limoeiro De Anadia', 2);
INSERT INTO endereco.cidade VALUES (70, '2019-10-23 14:05:31.168533', NULL, 'Major Isidoro', 2);
INSERT INTO endereco.cidade VALUES (71, '2019-10-23 14:05:31.168533', NULL, 'Mar Vermelho', 2);
INSERT INTO endereco.cidade VALUES (73, '2019-10-23 14:05:31.168533', NULL, 'Maravilha', 2);
INSERT INTO endereco.cidade VALUES (75, '2019-10-23 14:05:31.168533', NULL, 'Maribondo', 2);
INSERT INTO endereco.cidade VALUES (77, '2019-10-23 14:05:31.168533', NULL, 'Matriz De Camaragibe', 2);
INSERT INTO endereco.cidade VALUES (78, '2019-10-23 14:05:31.168533', NULL, 'Messias', 2);
INSERT INTO endereco.cidade VALUES (80, '2019-10-23 14:05:31.168533', NULL, 'Monteirópolis', 2);
INSERT INTO endereco.cidade VALUES (82, '2019-10-23 14:05:31.168533', NULL, 'Novo Lino', 2);
INSERT INTO endereco.cidade VALUES (84, '2019-10-23 14:05:31.168533', NULL, 'Olho D`Água Do Casado', 2);
INSERT INTO endereco.cidade VALUES (85, '2019-10-23 14:05:31.168533', NULL, 'Olho D`Água Grande', 2);
INSERT INTO endereco.cidade VALUES (87, '2019-10-23 14:05:31.168533', NULL, 'Ouro Branco', 2);
INSERT INTO endereco.cidade VALUES (89, '2019-10-23 14:05:31.168533', NULL, 'Palmeira Dos Índios', 2);
INSERT INTO endereco.cidade VALUES (91, '2019-10-23 14:05:31.168533', NULL, 'Pariconha', 2);
INSERT INTO endereco.cidade VALUES (92, '2019-10-23 14:05:31.168533', NULL, 'Paripueira', 2);
INSERT INTO endereco.cidade VALUES (94, '2019-10-23 14:05:31.168533', NULL, 'Paulo Jacinto', 2);
INSERT INTO endereco.cidade VALUES (96, '2019-10-23 14:05:31.168533', NULL, 'Piaçabuçu', 2);
INSERT INTO endereco.cidade VALUES (97, '2019-10-23 14:05:31.168533', NULL, 'Pilar', 2);
INSERT INTO endereco.cidade VALUES (99, '2019-10-23 14:05:31.168533', NULL, 'Piranhas', 2);
INSERT INTO endereco.cidade VALUES (101, '2019-10-23 14:05:31.168533', NULL, 'Porto Calvo', 2);
INSERT INTO endereco.cidade VALUES (103, '2019-10-23 14:05:31.168533', NULL, 'Porto Real Do Colégio', 2);
INSERT INTO endereco.cidade VALUES (104, '2019-10-23 14:05:31.168533', NULL, 'Quebrangulo', 2);
INSERT INTO endereco.cidade VALUES (106, '2019-10-23 14:05:31.168533', NULL, 'Roteiro', 2);
INSERT INTO endereco.cidade VALUES (108, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Ipanema', 2);
INSERT INTO endereco.cidade VALUES (109, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Mundaú', 2);
INSERT INTO endereco.cidade VALUES (111, '2019-10-23 14:05:31.168533', NULL, 'São José Da Laje', 2);
INSERT INTO endereco.cidade VALUES (113, '2019-10-23 14:05:31.168533', NULL, 'São Luís Do Quitunde', 2);
INSERT INTO endereco.cidade VALUES (114, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Dos Campos', 2);
INSERT INTO endereco.cidade VALUES (116, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião', 2);
INSERT INTO endereco.cidade VALUES (117, '2019-10-23 14:05:31.168533', NULL, 'Satuba', 2);
INSERT INTO endereco.cidade VALUES (119, '2019-10-23 14:05:31.168533', NULL, 'Tanque D`Arca', 2);
INSERT INTO endereco.cidade VALUES (121, '2019-10-23 14:05:31.168533', NULL, 'Teotônio Vilela', 2);
INSERT INTO endereco.cidade VALUES (122, '2019-10-23 14:05:31.168533', NULL, 'Traipu', 2);
INSERT INTO endereco.cidade VALUES (124, '2019-10-23 14:05:31.168533', NULL, 'Viçosa', 2);
INSERT INTO endereco.cidade VALUES (126, '2019-10-23 14:05:31.168533', NULL, 'Amaturá', 3);
INSERT INTO endereco.cidade VALUES (2713, '2019-10-23 14:05:31.168533', NULL, 'Princesa Isabel', 15);
INSERT INTO endereco.cidade VALUES (129, '2019-10-23 14:05:31.168533', NULL, 'Apuí', 3);
INSERT INTO endereco.cidade VALUES (131, '2019-10-23 14:05:31.168533', NULL, 'Autazes', 3);
INSERT INTO endereco.cidade VALUES (132, '2019-10-23 14:05:31.168533', NULL, 'Barcelos', 3);
INSERT INTO endereco.cidade VALUES (134, '2019-10-23 14:05:31.168533', NULL, 'Benjamin Constant', 3);
INSERT INTO endereco.cidade VALUES (136, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista Do Ramos', 3);
INSERT INTO endereco.cidade VALUES (138, '2019-10-23 14:05:31.168533', NULL, 'Borba', 3);
INSERT INTO endereco.cidade VALUES (140, '2019-10-23 14:05:31.168533', NULL, 'Canutama', 3);
INSERT INTO endereco.cidade VALUES (141, '2019-10-23 14:05:31.168533', NULL, 'Carauari', 3);
INSERT INTO endereco.cidade VALUES (143, '2019-10-23 14:05:31.168533', NULL, 'Careiro Da Várzea', 3);
INSERT INTO endereco.cidade VALUES (145, '2019-10-23 14:05:31.168533', NULL, 'Codajás', 3);
INSERT INTO endereco.cidade VALUES (147, '2019-10-23 14:05:31.168533', NULL, 'Envira', 3);
INSERT INTO endereco.cidade VALUES (149, '2019-10-23 14:05:31.168533', NULL, 'Guajará', 3);
INSERT INTO endereco.cidade VALUES (150, '2019-10-23 14:05:31.168533', NULL, 'Humaitá', 3);
INSERT INTO endereco.cidade VALUES (152, '2019-10-23 14:05:31.168533', NULL, 'Iranduba', 3);
INSERT INTO endereco.cidade VALUES (154, '2019-10-23 14:05:31.168533', NULL, 'Itamarati', 3);
INSERT INTO endereco.cidade VALUES (156, '2019-10-23 14:05:31.168533', NULL, 'Japurá', 3);
INSERT INTO endereco.cidade VALUES (158, '2019-10-23 14:05:31.168533', NULL, 'Jutaí', 3);
INSERT INTO endereco.cidade VALUES (159, '2019-10-23 14:05:31.168533', NULL, 'Lábrea', 3);
INSERT INTO endereco.cidade VALUES (161, '2019-10-23 14:05:31.168533', NULL, 'Manaquiri', 3);
INSERT INTO endereco.cidade VALUES (163, '2019-10-23 14:05:31.168533', NULL, 'Manicoré', 3);
INSERT INTO endereco.cidade VALUES (165, '2019-10-23 14:05:31.168533', NULL, 'Maués', 3);
INSERT INTO endereco.cidade VALUES (166, '2019-10-23 14:05:31.168533', NULL, 'Nhamundá', 3);
INSERT INTO endereco.cidade VALUES (168, '2019-10-23 14:05:31.168533', NULL, 'Novo Airão', 3);
INSERT INTO endereco.cidade VALUES (170, '2019-10-23 14:05:31.168533', NULL, 'Parintins', 3);
INSERT INTO endereco.cidade VALUES (171, '2019-10-23 14:05:31.168533', NULL, 'Pauini', 3);
INSERT INTO endereco.cidade VALUES (173, '2019-10-23 14:05:31.168533', NULL, 'Rio Preto Da Eva', 3);
INSERT INTO endereco.cidade VALUES (175, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Içá', 3);
INSERT INTO endereco.cidade VALUES (177, '2019-10-23 14:05:31.168533', NULL, 'São Paulo De Olivença', 3);
INSERT INTO endereco.cidade VALUES (178, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Uatumã', 3);
INSERT INTO endereco.cidade VALUES (179, '2019-10-23 14:05:31.168533', NULL, 'Silves', 3);
INSERT INTO endereco.cidade VALUES (181, '2019-10-23 14:05:31.168533', NULL, 'Tapauá', 3);
INSERT INTO endereco.cidade VALUES (183, '2019-10-23 14:05:31.168533', NULL, 'Tonantins', 3);
INSERT INTO endereco.cidade VALUES (185, '2019-10-23 14:05:31.168533', NULL, 'Urucará', 3);
INSERT INTO endereco.cidade VALUES (187, '2019-10-23 14:05:31.168533', NULL, 'Amapá', 4);
INSERT INTO endereco.cidade VALUES (188, '2019-10-23 14:05:31.168533', NULL, 'Calçoene', 4);
INSERT INTO endereco.cidade VALUES (190, '2019-10-23 14:05:31.168533', NULL, 'Ferreira Gomes', 4);
INSERT INTO endereco.cidade VALUES (192, '2019-10-23 14:05:31.168533', NULL, 'Laranjal Do Jari', 4);
INSERT INTO endereco.cidade VALUES (194, '2019-10-23 14:05:31.168533', NULL, 'Mazagão', 4);
INSERT INTO endereco.cidade VALUES (196, '2019-10-23 14:05:31.168533', NULL, 'Pedra Branca Do Amaparí', 4);
INSERT INTO endereco.cidade VALUES (198, '2019-10-23 14:05:31.168533', NULL, 'Pracuúba', 4);
INSERT INTO endereco.cidade VALUES (199, '2019-10-23 14:05:31.168533', NULL, 'Santana', 4);
INSERT INTO endereco.cidade VALUES (201, '2019-10-23 14:05:31.168533', NULL, 'Tartarugalzinho', 4);
INSERT INTO endereco.cidade VALUES (203, '2019-10-23 14:05:31.168533', NULL, 'Abaíra', 5);
INSERT INTO endereco.cidade VALUES (204, '2019-10-23 14:05:31.168533', NULL, 'Abaré', 5);
INSERT INTO endereco.cidade VALUES (206, '2019-10-23 14:05:31.168533', NULL, 'Adustina', 5);
INSERT INTO endereco.cidade VALUES (208, '2019-10-23 14:05:31.168533', NULL, 'Aiquara', 5);
INSERT INTO endereco.cidade VALUES (210, '2019-10-23 14:05:31.168533', NULL, 'Alcobaça', 5);
INSERT INTO endereco.cidade VALUES (211, '2019-10-23 14:05:31.168533', NULL, 'Almadina', 5);
INSERT INTO endereco.cidade VALUES (213, '2019-10-23 14:05:31.168533', NULL, 'Amélia Rodrigues', 5);
INSERT INTO endereco.cidade VALUES (215, '2019-10-23 14:05:31.168533', NULL, 'Anagé', 5);
INSERT INTO endereco.cidade VALUES (217, '2019-10-23 14:05:31.168533', NULL, 'Andorinha', 5);
INSERT INTO endereco.cidade VALUES (218, '2019-10-23 14:05:31.168533', NULL, 'Angical', 5);
INSERT INTO endereco.cidade VALUES (220, '2019-10-23 14:05:31.168533', NULL, 'Antas', 5);
INSERT INTO endereco.cidade VALUES (222, '2019-10-23 14:05:31.168533', NULL, 'Antônio Gonçalves', 5);
INSERT INTO endereco.cidade VALUES (224, '2019-10-23 14:05:31.168533', NULL, 'Apuarema', 5);
INSERT INTO endereco.cidade VALUES (225, '2019-10-23 14:05:31.168533', NULL, 'Araças', 5);
INSERT INTO endereco.cidade VALUES (227, '2019-10-23 14:05:31.168533', NULL, 'Araci', 5);
INSERT INTO endereco.cidade VALUES (229, '2019-10-23 14:05:31.168533', NULL, 'Arataca', 5);
INSERT INTO endereco.cidade VALUES (231, '2019-10-23 14:05:31.168533', NULL, 'Aurelino Leal', 5);
INSERT INTO endereco.cidade VALUES (233, '2019-10-23 14:05:31.168533', NULL, 'Baixa Grande', 5);
INSERT INTO endereco.cidade VALUES (234, '2019-10-23 14:05:31.168533', NULL, 'Banzaê', 5);
INSERT INTO endereco.cidade VALUES (236, '2019-10-23 14:05:31.168533', NULL, 'Barra Da Estiva', 5);
INSERT INTO endereco.cidade VALUES (238, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Mendes', 5);
INSERT INTO endereco.cidade VALUES (239, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Rocha', 5);
INSERT INTO endereco.cidade VALUES (241, '2019-10-23 14:05:31.168533', NULL, 'Barro Alto', 5);
INSERT INTO endereco.cidade VALUES (243, '2019-10-23 14:05:31.168533', NULL, 'Barro Preto', 5);
INSERT INTO endereco.cidade VALUES (245, '2019-10-23 14:05:31.168533', NULL, 'Belo Campo', 5);
INSERT INTO endereco.cidade VALUES (246, '2019-10-23 14:05:31.168533', NULL, 'Biritinga', 5);
INSERT INTO endereco.cidade VALUES (248, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista Do Tupim', 5);
INSERT INTO endereco.cidade VALUES (250, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Da Serra', 5);
INSERT INTO endereco.cidade VALUES (252, '2019-10-23 14:05:31.168533', NULL, 'Bonito', 5);
INSERT INTO endereco.cidade VALUES (253, '2019-10-23 14:05:31.168533', NULL, 'Boquira', 5);
INSERT INTO endereco.cidade VALUES (255, '2019-10-23 14:05:31.168533', NULL, 'Brejões', 5);
INSERT INTO endereco.cidade VALUES (2716, '2019-10-23 14:05:31.168533', NULL, 'Quixabá', 15);
INSERT INTO endereco.cidade VALUES (258, '2019-10-23 14:05:31.168533', NULL, 'Brumado', 5);
INSERT INTO endereco.cidade VALUES (260, '2019-10-23 14:05:31.168533', NULL, 'Buritirama', 5);
INSERT INTO endereco.cidade VALUES (262, '2019-10-23 14:05:31.168533', NULL, 'Cabaceiras Do Paraguaçu', 5);
INSERT INTO endereco.cidade VALUES (264, '2019-10-23 14:05:31.168533', NULL, 'Caculé', 5);
INSERT INTO endereco.cidade VALUES (265, '2019-10-23 14:05:31.168533', NULL, 'Caém', 5);
INSERT INTO endereco.cidade VALUES (267, '2019-10-23 14:05:31.168533', NULL, 'Caetité', 5);
INSERT INTO endereco.cidade VALUES (269, '2019-10-23 14:05:31.168533', NULL, 'Cairu', 5);
INSERT INTO endereco.cidade VALUES (271, '2019-10-23 14:05:31.168533', NULL, 'Camacan', 5);
INSERT INTO endereco.cidade VALUES (272, '2019-10-23 14:05:31.168533', NULL, 'Camaçari', 5);
INSERT INTO endereco.cidade VALUES (275, '2019-10-23 14:05:31.168533', NULL, 'Campo Formoso', 5);
INSERT INTO endereco.cidade VALUES (276, '2019-10-23 14:05:31.168533', NULL, 'Canápolis', 5);
INSERT INTO endereco.cidade VALUES (278, '2019-10-23 14:05:31.168533', NULL, 'Canavieiras', 5);
INSERT INTO endereco.cidade VALUES (279, '2019-10-23 14:05:31.168533', NULL, 'Candeal', 5);
INSERT INTO endereco.cidade VALUES (281, '2019-10-23 14:05:31.168533', NULL, 'Candiba', 5);
INSERT INTO endereco.cidade VALUES (283, '2019-10-23 14:05:31.168533', NULL, 'Cansanção', 5);
INSERT INTO endereco.cidade VALUES (285, '2019-10-23 14:05:31.168533', NULL, 'Capela Do Alto Alegre', 5);
INSERT INTO endereco.cidade VALUES (287, '2019-10-23 14:05:31.168533', NULL, 'Caraíbas', 5);
INSERT INTO endereco.cidade VALUES (288, '2019-10-23 14:05:31.168533', NULL, 'Caravelas', 5);
INSERT INTO endereco.cidade VALUES (290, '2019-10-23 14:05:31.168533', NULL, 'Carinhanha', 5);
INSERT INTO endereco.cidade VALUES (292, '2019-10-23 14:05:31.168533', NULL, 'Castro Alves', 5);
INSERT INTO endereco.cidade VALUES (293, '2019-10-23 14:05:31.168533', NULL, 'Catolândia', 5);
INSERT INTO endereco.cidade VALUES (295, '2019-10-23 14:05:31.168533', NULL, 'Caturama', 5);
INSERT INTO endereco.cidade VALUES (297, '2019-10-23 14:05:31.168533', NULL, 'Chorrochó', 5);
INSERT INTO endereco.cidade VALUES (299, '2019-10-23 14:05:31.168533', NULL, 'Cipó', 5);
INSERT INTO endereco.cidade VALUES (300, '2019-10-23 14:05:31.168533', NULL, 'Coaraci', 5);
INSERT INTO endereco.cidade VALUES (302, '2019-10-23 14:05:31.168533', NULL, 'Conceição Da Feira', 5);
INSERT INTO endereco.cidade VALUES (304, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Coité', 5);
INSERT INTO endereco.cidade VALUES (306, '2019-10-23 14:05:31.168533', NULL, 'Conde', 5);
INSERT INTO endereco.cidade VALUES (307, '2019-10-23 14:05:31.168533', NULL, 'Condeúba', 5);
INSERT INTO endereco.cidade VALUES (309, '2019-10-23 14:05:31.168533', NULL, 'Coração De Maria', 5);
INSERT INTO endereco.cidade VALUES (310, '2019-10-23 14:05:31.168533', NULL, 'Cordeiros', 5);
INSERT INTO endereco.cidade VALUES (312, '2019-10-23 14:05:31.168533', NULL, 'Coronel João Sá', 5);
INSERT INTO endereco.cidade VALUES (314, '2019-10-23 14:05:31.168533', NULL, 'Cotegipe', 5);
INSERT INTO endereco.cidade VALUES (316, '2019-10-23 14:05:31.168533', NULL, 'Crisópolis', 5);
INSERT INTO endereco.cidade VALUES (317, '2019-10-23 14:05:31.168533', NULL, 'Cristópolis', 5);
INSERT INTO endereco.cidade VALUES (319, '2019-10-23 14:05:31.168533', NULL, 'Curaçá', 5);
INSERT INTO endereco.cidade VALUES (321, '2019-10-23 14:05:31.168533', NULL, 'Dias D`Ávila', 5);
INSERT INTO endereco.cidade VALUES (322, '2019-10-23 14:05:31.168533', NULL, 'Dom Basílio', 5);
INSERT INTO endereco.cidade VALUES (324, '2019-10-23 14:05:31.168533', NULL, 'Elísio Medrado', 5);
INSERT INTO endereco.cidade VALUES (326, '2019-10-23 14:05:31.168533', NULL, 'Entre Rios', 5);
INSERT INTO endereco.cidade VALUES (328, '2019-10-23 14:05:31.168533', NULL, 'Esplanada', 5);
INSERT INTO endereco.cidade VALUES (329, '2019-10-23 14:05:31.168533', NULL, 'Euclides Da Cunha', 5);
INSERT INTO endereco.cidade VALUES (331, '2019-10-23 14:05:31.168533', NULL, 'Fátima', 5);
INSERT INTO endereco.cidade VALUES (333, '2019-10-23 14:05:31.168533', NULL, 'Feira De Santana', 5);
INSERT INTO endereco.cidade VALUES (334, '2019-10-23 14:05:31.168533', NULL, 'Filadélfia', 5);
INSERT INTO endereco.cidade VALUES (336, '2019-10-23 14:05:31.168533', NULL, 'Floresta Azul', 5);
INSERT INTO endereco.cidade VALUES (338, '2019-10-23 14:05:31.168533', NULL, 'Gandu', 5);
INSERT INTO endereco.cidade VALUES (339, '2019-10-23 14:05:31.168533', NULL, 'Gavião', 5);
INSERT INTO endereco.cidade VALUES (341, '2019-10-23 14:05:31.168533', NULL, 'Glória', 5);
INSERT INTO endereco.cidade VALUES (343, '2019-10-23 14:05:31.168533', NULL, 'Governador Mangabeira', 5);
INSERT INTO endereco.cidade VALUES (344, '2019-10-23 14:05:31.168533', NULL, 'Guajeru', 5);
INSERT INTO endereco.cidade VALUES (346, '2019-10-23 14:05:31.168533', NULL, 'Guaratinga', 5);
INSERT INTO endereco.cidade VALUES (348, '2019-10-23 14:05:31.168533', NULL, 'Iaçu', 5);
INSERT INTO endereco.cidade VALUES (350, '2019-10-23 14:05:31.168533', NULL, 'Ibicaraí', 5);
INSERT INTO endereco.cidade VALUES (352, '2019-10-23 14:05:31.168533', NULL, 'Ibicuí', 5);
INSERT INTO endereco.cidade VALUES (353, '2019-10-23 14:05:31.168533', NULL, 'Ibipeba', 5);
INSERT INTO endereco.cidade VALUES (355, '2019-10-23 14:05:31.168533', NULL, 'Ibiquera', 5);
INSERT INTO endereco.cidade VALUES (357, '2019-10-23 14:05:31.168533', NULL, 'Ibirapuã', 5);
INSERT INTO endereco.cidade VALUES (359, '2019-10-23 14:05:31.168533', NULL, 'Ibitiara', 5);
INSERT INTO endereco.cidade VALUES (360, '2019-10-23 14:05:31.168533', NULL, 'Ibititá', 5);
INSERT INTO endereco.cidade VALUES (362, '2019-10-23 14:05:31.168533', NULL, 'Ichu', 5);
INSERT INTO endereco.cidade VALUES (364, '2019-10-23 14:05:31.168533', NULL, 'Igrapiúna', 5);
INSERT INTO endereco.cidade VALUES (366, '2019-10-23 14:05:31.168533', NULL, 'Ilhéus', 5);
INSERT INTO endereco.cidade VALUES (368, '2019-10-23 14:05:31.168533', NULL, 'Ipecaetá', 5);
INSERT INTO endereco.cidade VALUES (369, '2019-10-23 14:05:31.168533', NULL, 'Ipiaú', 5);
INSERT INTO endereco.cidade VALUES (371, '2019-10-23 14:05:31.168533', NULL, 'Ipupiara', 5);
INSERT INTO endereco.cidade VALUES (373, '2019-10-23 14:05:31.168533', NULL, 'Iramaia', 5);
INSERT INTO endereco.cidade VALUES (375, '2019-10-23 14:05:31.168533', NULL, 'Irará', 5);
INSERT INTO endereco.cidade VALUES (376, '2019-10-23 14:05:31.168533', NULL, 'Irecê', 5);
INSERT INTO endereco.cidade VALUES (378, '2019-10-23 14:05:31.168533', NULL, 'Itaberaba', 5);
INSERT INTO endereco.cidade VALUES (380, '2019-10-23 14:05:31.168533', NULL, 'Itacaré', 5);
INSERT INTO endereco.cidade VALUES (382, '2019-10-23 14:05:31.168533', NULL, 'Itagi', 5);
INSERT INTO endereco.cidade VALUES (384, '2019-10-23 14:05:31.168533', NULL, 'Itagimirim', 5);
INSERT INTO endereco.cidade VALUES (387, '2019-10-23 14:05:31.168533', NULL, 'Itajuípe', 5);
INSERT INTO endereco.cidade VALUES (389, '2019-10-23 14:05:31.168533', NULL, 'Itamari', 5);
INSERT INTO endereco.cidade VALUES (391, '2019-10-23 14:05:31.168533', NULL, 'Itanagra', 5);
INSERT INTO endereco.cidade VALUES (392, '2019-10-23 14:05:31.168533', NULL, 'Itanhém', 5);
INSERT INTO endereco.cidade VALUES (394, '2019-10-23 14:05:31.168533', NULL, 'Itapé', 5);
INSERT INTO endereco.cidade VALUES (396, '2019-10-23 14:05:31.168533', NULL, 'Itapetinga', 5);
INSERT INTO endereco.cidade VALUES (398, '2019-10-23 14:05:31.168533', NULL, 'Itapitanga', 5);
INSERT INTO endereco.cidade VALUES (400, '2019-10-23 14:05:31.168533', NULL, 'Itarantim', 5);
INSERT INTO endereco.cidade VALUES (401, '2019-10-23 14:05:31.168533', NULL, 'Itatim', 5);
INSERT INTO endereco.cidade VALUES (403, '2019-10-23 14:05:31.168533', NULL, 'Itiúba', 5);
INSERT INTO endereco.cidade VALUES (405, '2019-10-23 14:05:31.168533', NULL, 'Ituaçu', 5);
INSERT INTO endereco.cidade VALUES (407, '2019-10-23 14:05:31.168533', NULL, 'Iuiú', 5);
INSERT INTO endereco.cidade VALUES (409, '2019-10-23 14:05:31.168533', NULL, 'Jacaraci', 5);
INSERT INTO endereco.cidade VALUES (410, '2019-10-23 14:05:31.168533', NULL, 'Jacobina', 5);
INSERT INTO endereco.cidade VALUES (412, '2019-10-23 14:05:31.168533', NULL, 'Jaguarari', 5);
INSERT INTO endereco.cidade VALUES (414, '2019-10-23 14:05:31.168533', NULL, 'Jandaíra', 5);
INSERT INTO endereco.cidade VALUES (416, '2019-10-23 14:05:31.168533', NULL, 'Jeremoabo', 5);
INSERT INTO endereco.cidade VALUES (417, '2019-10-23 14:05:31.168533', NULL, 'Jiquiriçá', 5);
INSERT INTO endereco.cidade VALUES (420, '2019-10-23 14:05:31.168533', NULL, 'Juazeiro', 5);
INSERT INTO endereco.cidade VALUES (421, '2019-10-23 14:05:31.168533', NULL, 'Jucuruçu', 5);
INSERT INTO endereco.cidade VALUES (423, '2019-10-23 14:05:31.168533', NULL, 'Jussari', 5);
INSERT INTO endereco.cidade VALUES (424, '2019-10-23 14:05:31.168533', NULL, 'Jussiape', 5);
INSERT INTO endereco.cidade VALUES (426, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Real', 5);
INSERT INTO endereco.cidade VALUES (428, '2019-10-23 14:05:31.168533', NULL, 'Lajedão', 5);
INSERT INTO endereco.cidade VALUES (430, '2019-10-23 14:05:31.168533', NULL, 'Lajedo Do Tabocal', 5);
INSERT INTO endereco.cidade VALUES (431, '2019-10-23 14:05:31.168533', NULL, 'Lamarão', 5);
INSERT INTO endereco.cidade VALUES (433, '2019-10-23 14:05:31.168533', NULL, 'Lauro De Freitas', 5);
INSERT INTO endereco.cidade VALUES (435, '2019-10-23 14:05:31.168533', NULL, 'Licínio De Almeida', 5);
INSERT INTO endereco.cidade VALUES (437, '2019-10-23 14:05:31.168533', NULL, 'Luís Eduardo Magalhães', 5);
INSERT INTO endereco.cidade VALUES (438, '2019-10-23 14:05:31.168533', NULL, 'Macajuba', 5);
INSERT INTO endereco.cidade VALUES (440, '2019-10-23 14:05:31.168533', NULL, 'Macaúbas', 5);
INSERT INTO endereco.cidade VALUES (442, '2019-10-23 14:05:31.168533', NULL, 'Madre De Deus', 5);
INSERT INTO endereco.cidade VALUES (444, '2019-10-23 14:05:31.168533', NULL, 'Maiquinique', 5);
INSERT INTO endereco.cidade VALUES (446, '2019-10-23 14:05:31.168533', NULL, 'Malhada', 5);
INSERT INTO endereco.cidade VALUES (448, '2019-10-23 14:05:31.168533', NULL, 'Manoel Vitorino', 5);
INSERT INTO endereco.cidade VALUES (449, '2019-10-23 14:05:31.168533', NULL, 'Mansidão', 5);
INSERT INTO endereco.cidade VALUES (451, '2019-10-23 14:05:31.168533', NULL, 'Maragogipe', 5);
INSERT INTO endereco.cidade VALUES (453, '2019-10-23 14:05:31.168533', NULL, 'Marcionílio Souza', 5);
INSERT INTO endereco.cidade VALUES (455, '2019-10-23 14:05:31.168533', NULL, 'Mata De São João', 5);
INSERT INTO endereco.cidade VALUES (456, '2019-10-23 14:05:31.168533', NULL, 'Matina', 5);
INSERT INTO endereco.cidade VALUES (458, '2019-10-23 14:05:31.168533', NULL, 'Miguel Calmon', 5);
INSERT INTO endereco.cidade VALUES (460, '2019-10-23 14:05:31.168533', NULL, 'Mirangaba', 5);
INSERT INTO endereco.cidade VALUES (461, '2019-10-23 14:05:31.168533', NULL, 'Mirante', 5);
INSERT INTO endereco.cidade VALUES (463, '2019-10-23 14:05:31.168533', NULL, 'Morpará', 5);
INSERT INTO endereco.cidade VALUES (465, '2019-10-23 14:05:31.168533', NULL, 'Mortugaba', 5);
INSERT INTO endereco.cidade VALUES (467, '2019-10-23 14:05:31.168533', NULL, 'Mucuri', 5);
INSERT INTO endereco.cidade VALUES (469, '2019-10-23 14:05:31.168533', NULL, 'Mundo Novo', 5);
INSERT INTO endereco.cidade VALUES (470, '2019-10-23 14:05:31.168533', NULL, 'Muniz Ferreira', 5);
INSERT INTO endereco.cidade VALUES (472, '2019-10-23 14:05:31.168533', NULL, 'Muritiba', 5);
INSERT INTO endereco.cidade VALUES (473, '2019-10-23 14:05:31.168533', NULL, 'Mutuípe', 5);
INSERT INTO endereco.cidade VALUES (475, '2019-10-23 14:05:31.168533', NULL, 'Nilo Peçanha', 5);
INSERT INTO endereco.cidade VALUES (477, '2019-10-23 14:05:31.168533', NULL, 'Nova Canaã', 5);
INSERT INTO endereco.cidade VALUES (479, '2019-10-23 14:05:31.168533', NULL, 'Nova Ibiá', 5);
INSERT INTO endereco.cidade VALUES (481, '2019-10-23 14:05:31.168533', NULL, 'Nova Redenção', 5);
INSERT INTO endereco.cidade VALUES (482, '2019-10-23 14:05:31.168533', NULL, 'Nova Soure', 5);
INSERT INTO endereco.cidade VALUES (484, '2019-10-23 14:05:31.168533', NULL, 'Novo Horizonte', 5);
INSERT INTO endereco.cidade VALUES (486, '2019-10-23 14:05:31.168533', NULL, 'Olindina', 5);
INSERT INTO endereco.cidade VALUES (488, '2019-10-23 14:05:31.168533', NULL, 'Ouriçangas', 5);
INSERT INTO endereco.cidade VALUES (489, '2019-10-23 14:05:31.168533', NULL, 'Ourolândia', 5);
INSERT INTO endereco.cidade VALUES (491, '2019-10-23 14:05:31.168533', NULL, 'Palmeiras', 5);
INSERT INTO endereco.cidade VALUES (492, '2019-10-23 14:05:31.168533', NULL, 'Paramirim', 5);
INSERT INTO endereco.cidade VALUES (494, '2019-10-23 14:05:31.168533', NULL, 'Paripiranga', 5);
INSERT INTO endereco.cidade VALUES (496, '2019-10-23 14:05:31.168533', NULL, 'Paulo Afonso', 5);
INSERT INTO endereco.cidade VALUES (498, '2019-10-23 14:05:31.168533', NULL, 'Pedrão', 5);
INSERT INTO endereco.cidade VALUES (500, '2019-10-23 14:05:31.168533', NULL, 'Piatã', 5);
INSERT INTO endereco.cidade VALUES (501, '2019-10-23 14:05:31.168533', NULL, 'Pilão Arcado', 5);
INSERT INTO endereco.cidade VALUES (503, '2019-10-23 14:05:31.168533', NULL, 'Pindobaçu', 5);
INSERT INTO endereco.cidade VALUES (505, '2019-10-23 14:05:31.168533', NULL, 'Piraí Do Norte', 5);
INSERT INTO endereco.cidade VALUES (506, '2019-10-23 14:05:31.168533', NULL, 'Piripá', 5);
INSERT INTO endereco.cidade VALUES (508, '2019-10-23 14:05:31.168533', NULL, 'Planaltino', 5);
INSERT INTO endereco.cidade VALUES (510, '2019-10-23 14:05:31.168533', NULL, 'Poções', 5);
INSERT INTO endereco.cidade VALUES (512, '2019-10-23 14:05:31.168533', NULL, 'Ponto Novo', 5);
INSERT INTO endereco.cidade VALUES (514, '2019-10-23 14:05:31.168533', NULL, 'Potiraguá', 5);
INSERT INTO endereco.cidade VALUES (515, '2019-10-23 14:05:31.168533', NULL, 'Prado', 5);
INSERT INTO endereco.cidade VALUES (518, '2019-10-23 14:05:31.168533', NULL, 'Presidente Tancredo Neves', 5);
INSERT INTO endereco.cidade VALUES (519, '2019-10-23 14:05:31.168533', NULL, 'Queimadas', 5);
INSERT INTO endereco.cidade VALUES (521, '2019-10-23 14:05:31.168533', NULL, 'Quixabeira', 5);
INSERT INTO endereco.cidade VALUES (523, '2019-10-23 14:05:31.168533', NULL, 'Remanso', 5);
INSERT INTO endereco.cidade VALUES (525, '2019-10-23 14:05:31.168533', NULL, 'Riachão Das Neves', 5);
INSERT INTO endereco.cidade VALUES (527, '2019-10-23 14:05:31.168533', NULL, 'Riacho De Santana', 5);
INSERT INTO endereco.cidade VALUES (528, '2019-10-23 14:05:31.168533', NULL, 'Ribeira Do Amparo', 5);
INSERT INTO endereco.cidade VALUES (530, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Do Largo', 5);
INSERT INTO endereco.cidade VALUES (531, '2019-10-23 14:05:31.168533', NULL, 'Rio De Contas', 5);
INSERT INTO endereco.cidade VALUES (533, '2019-10-23 14:05:31.168533', NULL, 'Rio Do Pires', 5);
INSERT INTO endereco.cidade VALUES (535, '2019-10-23 14:05:31.168533', NULL, 'Rodelas', 5);
INSERT INTO endereco.cidade VALUES (536, '2019-10-23 14:05:31.168533', NULL, 'Ruy Barbosa', 5);
INSERT INTO endereco.cidade VALUES (538, '2019-10-23 14:05:31.168533', NULL, 'Salvador', 5);
INSERT INTO endereco.cidade VALUES (540, '2019-10-23 14:05:31.168533', NULL, 'Santa Brígida', 5);
INSERT INTO endereco.cidade VALUES (542, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Da Vitória', 5);
INSERT INTO endereco.cidade VALUES (543, '2019-10-23 14:05:31.168533', NULL, 'Santa Inês', 5);
INSERT INTO endereco.cidade VALUES (545, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Da Vitória', 5);
INSERT INTO endereco.cidade VALUES (547, '2019-10-23 14:05:31.168533', NULL, 'Santa Teresinha', 5);
INSERT INTO endereco.cidade VALUES (548, '2019-10-23 14:05:31.168533', NULL, 'Santaluz', 5);
INSERT INTO endereco.cidade VALUES (550, '2019-10-23 14:05:31.168533', NULL, 'Santanópolis', 5);
INSERT INTO endereco.cidade VALUES (552, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio De Jesus', 5);
INSERT INTO endereco.cidade VALUES (554, '2019-10-23 14:05:31.168533', NULL, 'São Desidério', 5);
INSERT INTO endereco.cidade VALUES (556, '2019-10-23 14:05:31.168533', NULL, 'São Felipe', 5);
INSERT INTO endereco.cidade VALUES (557, '2019-10-23 14:05:31.168533', NULL, 'São Félix', 5);
INSERT INTO endereco.cidade VALUES (559, '2019-10-23 14:05:31.168533', NULL, 'São Francisco Do Conde', 5);
INSERT INTO endereco.cidade VALUES (561, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Dos Campos', 5);
INSERT INTO endereco.cidade VALUES (563, '2019-10-23 14:05:31.168533', NULL, 'São José Do Jacuípe', 5);
INSERT INTO endereco.cidade VALUES (564, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Das Matas', 5);
INSERT INTO endereco.cidade VALUES (566, '2019-10-23 14:05:31.168533', NULL, 'Sapeaçu', 5);
INSERT INTO endereco.cidade VALUES (567, '2019-10-23 14:05:31.168533', NULL, 'Sátiro Dias', 5);
INSERT INTO endereco.cidade VALUES (569, '2019-10-23 14:05:31.168533', NULL, 'Saúde', 5);
INSERT INTO endereco.cidade VALUES (571, '2019-10-23 14:05:31.168533', NULL, 'Sebastião Laranjeiras', 5);
INSERT INTO endereco.cidade VALUES (573, '2019-10-23 14:05:31.168533', NULL, 'Sento Sé', 5);
INSERT INTO endereco.cidade VALUES (574, '2019-10-23 14:05:31.168533', NULL, 'Serra Do Ramalho', 5);
INSERT INTO endereco.cidade VALUES (576, '2019-10-23 14:05:31.168533', NULL, 'Serra Preta', 5);
INSERT INTO endereco.cidade VALUES (577, '2019-10-23 14:05:31.168533', NULL, 'Serrinha', 5);
INSERT INTO endereco.cidade VALUES (579, '2019-10-23 14:05:31.168533', NULL, 'Simões Filho', 5);
INSERT INTO endereco.cidade VALUES (581, '2019-10-23 14:05:31.168533', NULL, 'Sítio Do Quinto', 5);
INSERT INTO endereco.cidade VALUES (583, '2019-10-23 14:05:31.168533', NULL, 'Souto Soares', 5);
INSERT INTO endereco.cidade VALUES (585, '2019-10-23 14:05:31.168533', NULL, 'Tanhaçu', 5);
INSERT INTO endereco.cidade VALUES (586, '2019-10-23 14:05:31.168533', NULL, 'Tanque Novo', 5);
INSERT INTO endereco.cidade VALUES (588, '2019-10-23 14:05:31.168533', NULL, 'Taperoá', 5);
INSERT INTO endereco.cidade VALUES (590, '2019-10-23 14:05:31.168533', NULL, 'Teixeira De Freitas', 5);
INSERT INTO endereco.cidade VALUES (592, '2019-10-23 14:05:31.168533', NULL, 'Teofilândia', 5);
INSERT INTO endereco.cidade VALUES (593, '2019-10-23 14:05:31.168533', NULL, 'Teolândia', 5);
INSERT INTO endereco.cidade VALUES (595, '2019-10-23 14:05:31.168533', NULL, 'Tremedal', 5);
INSERT INTO endereco.cidade VALUES (597, '2019-10-23 14:05:31.168533', NULL, 'Uauá', 5);
INSERT INTO endereco.cidade VALUES (598, '2019-10-23 14:05:31.168533', NULL, 'Ubaíra', 5);
INSERT INTO endereco.cidade VALUES (600, '2019-10-23 14:05:31.168533', NULL, 'Ubatã', 5);
INSERT INTO endereco.cidade VALUES (602, '2019-10-23 14:05:31.168533', NULL, 'Umburanas', 5);
INSERT INTO endereco.cidade VALUES (604, '2019-10-23 14:05:31.168533', NULL, 'Urandi', 5);
INSERT INTO endereco.cidade VALUES (606, '2019-10-23 14:05:31.168533', NULL, 'Utinga', 5);
INSERT INTO endereco.cidade VALUES (608, '2019-10-23 14:05:31.168533', NULL, 'Valente', 5);
INSERT INTO endereco.cidade VALUES (609, '2019-10-23 14:05:31.168533', NULL, 'Várzea Da Roça', 5);
INSERT INTO endereco.cidade VALUES (611, '2019-10-23 14:05:31.168533', NULL, 'Várzea Nova', 5);
INSERT INTO endereco.cidade VALUES (613, '2019-10-23 14:05:31.168533', NULL, 'Vera Cruz', 5);
INSERT INTO endereco.cidade VALUES (615, '2019-10-23 14:05:31.168533', NULL, 'Vitória Da Conquista', 5);
INSERT INTO endereco.cidade VALUES (616, '2019-10-23 14:05:31.168533', NULL, 'Wagner', 5);
INSERT INTO endereco.cidade VALUES (619, '2019-10-23 14:05:31.168533', NULL, 'Xique Xique', 5);
INSERT INTO endereco.cidade VALUES (620, '2019-10-23 14:05:31.168533', NULL, 'Abaiara', 6);
INSERT INTO endereco.cidade VALUES (621, '2019-10-23 14:05:31.168533', NULL, 'Acarape', 6);
INSERT INTO endereco.cidade VALUES (623, '2019-10-23 14:05:31.168533', NULL, 'Acopiara', 6);
INSERT INTO endereco.cidade VALUES (625, '2019-10-23 14:05:31.168533', NULL, 'Alcântaras', 6);
INSERT INTO endereco.cidade VALUES (627, '2019-10-23 14:05:31.168533', NULL, 'Alto Santo', 6);
INSERT INTO endereco.cidade VALUES (629, '2019-10-23 14:05:31.168533', NULL, 'Antonina Do Norte', 6);
INSERT INTO endereco.cidade VALUES (630, '2019-10-23 14:05:31.168533', NULL, 'Apuiarés', 6);
INSERT INTO endereco.cidade VALUES (632, '2019-10-23 14:05:31.168533', NULL, 'Aracati', 6);
INSERT INTO endereco.cidade VALUES (634, '2019-10-23 14:05:31.168533', NULL, 'Ararendá', 6);
INSERT INTO endereco.cidade VALUES (636, '2019-10-23 14:05:31.168533', NULL, 'Aratuba', 6);
INSERT INTO endereco.cidade VALUES (637, '2019-10-23 14:05:31.168533', NULL, 'Arneiroz', 6);
INSERT INTO endereco.cidade VALUES (639, '2019-10-23 14:05:31.168533', NULL, 'Aurora', 6);
INSERT INTO endereco.cidade VALUES (641, '2019-10-23 14:05:31.168533', NULL, 'Banabuiú', 6);
INSERT INTO endereco.cidade VALUES (2718, '2019-10-23 14:05:31.168533', NULL, 'Riachão', 15);
INSERT INTO endereco.cidade VALUES (643, '2019-10-23 14:05:31.168533', NULL, 'Barreira', 6);
INSERT INTO endereco.cidade VALUES (645, '2019-10-23 14:05:31.168533', NULL, 'Barroquinha', 6);
INSERT INTO endereco.cidade VALUES (647, '2019-10-23 14:05:31.168533', NULL, 'Beberibe', 6);
INSERT INTO endereco.cidade VALUES (649, '2019-10-23 14:05:31.168533', NULL, 'Boa Viagem', 6);
INSERT INTO endereco.cidade VALUES (650, '2019-10-23 14:05:31.168533', NULL, 'Brejo Santo', 6);
INSERT INTO endereco.cidade VALUES (652, '2019-10-23 14:05:31.168533', NULL, 'Campos Sales', 6);
INSERT INTO endereco.cidade VALUES (654, '2019-10-23 14:05:31.168533', NULL, 'Capistrano', 6);
INSERT INTO endereco.cidade VALUES (656, '2019-10-23 14:05:31.168533', NULL, 'Cariré', 6);
INSERT INTO endereco.cidade VALUES (658, '2019-10-23 14:05:31.168533', NULL, 'Cariús', 6);
INSERT INTO endereco.cidade VALUES (659, '2019-10-23 14:05:31.168533', NULL, 'Carnaubal', 6);
INSERT INTO endereco.cidade VALUES (661, '2019-10-23 14:05:31.168533', NULL, 'Catarina', 6);
INSERT INTO endereco.cidade VALUES (663, '2019-10-23 14:05:31.168533', NULL, 'Caucaia', 6);
INSERT INTO endereco.cidade VALUES (665, '2019-10-23 14:05:31.168533', NULL, 'Chaval', 6);
INSERT INTO endereco.cidade VALUES (666, '2019-10-23 14:05:31.168533', NULL, 'Choró', 6);
INSERT INTO endereco.cidade VALUES (668, '2019-10-23 14:05:31.168533', NULL, 'Coreaú', 6);
INSERT INTO endereco.cidade VALUES (670, '2019-10-23 14:05:31.168533', NULL, 'Crato', 6);
INSERT INTO endereco.cidade VALUES (672, '2019-10-23 14:05:31.168533', NULL, 'Cruz', 6);
INSERT INTO endereco.cidade VALUES (674, '2019-10-23 14:05:31.168533', NULL, 'Ererê', 6);
INSERT INTO endereco.cidade VALUES (675, '2019-10-23 14:05:31.168533', NULL, 'Eusébio', 6);
INSERT INTO endereco.cidade VALUES (677, '2019-10-23 14:05:31.168533', NULL, 'Forquilha', 6);
INSERT INTO endereco.cidade VALUES (679, '2019-10-23 14:05:31.168533', NULL, 'Fortim', 6);
INSERT INTO endereco.cidade VALUES (680, '2019-10-23 14:05:31.168533', NULL, 'Frecheirinha', 6);
INSERT INTO endereco.cidade VALUES (682, '2019-10-23 14:05:31.168533', NULL, 'Graça', 6);
INSERT INTO endereco.cidade VALUES (684, '2019-10-23 14:05:31.168533', NULL, 'Granjeiro', 6);
INSERT INTO endereco.cidade VALUES (686, '2019-10-23 14:05:31.168533', NULL, 'Guaiúba', 6);
INSERT INTO endereco.cidade VALUES (687, '2019-10-23 14:05:31.168533', NULL, 'Guaraciaba Do Norte', 6);
INSERT INTO endereco.cidade VALUES (689, '2019-10-23 14:05:31.168533', NULL, 'Hidrolândia', 6);
INSERT INTO endereco.cidade VALUES (691, '2019-10-23 14:05:31.168533', NULL, 'Ibaretama', 6);
INSERT INTO endereco.cidade VALUES (693, '2019-10-23 14:05:31.168533', NULL, 'Ibicuitinga', 6);
INSERT INTO endereco.cidade VALUES (694, '2019-10-23 14:05:31.168533', NULL, 'Icapuí', 6);
INSERT INTO endereco.cidade VALUES (696, '2019-10-23 14:05:31.168533', NULL, 'Iguatu', 6);
INSERT INTO endereco.cidade VALUES (698, '2019-10-23 14:05:31.168533', NULL, 'Ipaporanga', 6);
INSERT INTO endereco.cidade VALUES (700, '2019-10-23 14:05:31.168533', NULL, 'Ipu', 6);
INSERT INTO endereco.cidade VALUES (701, '2019-10-23 14:05:31.168533', NULL, 'Ipueiras', 6);
INSERT INTO endereco.cidade VALUES (703, '2019-10-23 14:05:31.168533', NULL, 'Irauçuba', 6);
INSERT INTO endereco.cidade VALUES (705, '2019-10-23 14:05:31.168533', NULL, 'Itaitinga', 6);
INSERT INTO endereco.cidade VALUES (707, '2019-10-23 14:05:31.168533', NULL, 'Itapipoca', 6);
INSERT INTO endereco.cidade VALUES (708, '2019-10-23 14:05:31.168533', NULL, 'Itapiúna', 6);
INSERT INTO endereco.cidade VALUES (710, '2019-10-23 14:05:31.168533', NULL, 'Itatira', 6);
INSERT INTO endereco.cidade VALUES (712, '2019-10-23 14:05:31.168533', NULL, 'Jaguaribara', 6);
INSERT INTO endereco.cidade VALUES (714, '2019-10-23 14:05:31.168533', NULL, 'Jaguaruana', 6);
INSERT INTO endereco.cidade VALUES (715, '2019-10-23 14:05:31.168533', NULL, 'Jardim', 6);
INSERT INTO endereco.cidade VALUES (718, '2019-10-23 14:05:31.168533', NULL, 'Juazeiro Do Norte', 6);
INSERT INTO endereco.cidade VALUES (719, '2019-10-23 14:05:31.168533', NULL, 'Jucás', 6);
INSERT INTO endereco.cidade VALUES (721, '2019-10-23 14:05:31.168533', NULL, 'Limoeiro Do Norte', 6);
INSERT INTO endereco.cidade VALUES (722, '2019-10-23 14:05:31.168533', NULL, 'Madalena', 6);
INSERT INTO endereco.cidade VALUES (724, '2019-10-23 14:05:31.168533', NULL, 'Maranguape', 6);
INSERT INTO endereco.cidade VALUES (726, '2019-10-23 14:05:31.168533', NULL, 'Martinópole', 6);
INSERT INTO endereco.cidade VALUES (728, '2019-10-23 14:05:31.168533', NULL, 'Mauriti', 6);
INSERT INTO endereco.cidade VALUES (729, '2019-10-23 14:05:31.168533', NULL, 'Meruoca', 6);
INSERT INTO endereco.cidade VALUES (731, '2019-10-23 14:05:31.168533', NULL, 'Milhã', 6);
INSERT INTO endereco.cidade VALUES (733, '2019-10-23 14:05:31.168533', NULL, 'Missão Velha', 6);
INSERT INTO endereco.cidade VALUES (735, '2019-10-23 14:05:31.168533', NULL, 'Monsenhor Tabosa', 6);
INSERT INTO endereco.cidade VALUES (736, '2019-10-23 14:05:31.168533', NULL, 'Morada Nova', 6);
INSERT INTO endereco.cidade VALUES (738, '2019-10-23 14:05:31.168533', NULL, 'Morrinhos', 6);
INSERT INTO endereco.cidade VALUES (740, '2019-10-23 14:05:31.168533', NULL, 'Mulungu', 6);
INSERT INTO endereco.cidade VALUES (742, '2019-10-23 14:05:31.168533', NULL, 'Nova Russas', 6);
INSERT INTO endereco.cidade VALUES (744, '2019-10-23 14:05:31.168533', NULL, 'Ocara', 6);
INSERT INTO endereco.cidade VALUES (745, '2019-10-23 14:05:31.168533', NULL, 'Orós', 6);
INSERT INTO endereco.cidade VALUES (747, '2019-10-23 14:05:31.168533', NULL, 'Pacatuba', 6);
INSERT INTO endereco.cidade VALUES (749, '2019-10-23 14:05:31.168533', NULL, 'Pacujá', 6);
INSERT INTO endereco.cidade VALUES (750, '2019-10-23 14:05:31.168533', NULL, 'Palhano', 6);
INSERT INTO endereco.cidade VALUES (752, '2019-10-23 14:05:31.168533', NULL, 'Paracuru', 6);
INSERT INTO endereco.cidade VALUES (754, '2019-10-23 14:05:31.168533', NULL, 'Parambu', 6);
INSERT INTO endereco.cidade VALUES (756, '2019-10-23 14:05:31.168533', NULL, 'Pedra Branca', 6);
INSERT INTO endereco.cidade VALUES (758, '2019-10-23 14:05:31.168533', NULL, 'Pentecoste', 6);
INSERT INTO endereco.cidade VALUES (760, '2019-10-23 14:05:31.168533', NULL, 'Pindoretama', 6);
INSERT INTO endereco.cidade VALUES (762, '2019-10-23 14:05:31.168533', NULL, 'Pires Ferreira', 6);
INSERT INTO endereco.cidade VALUES (763, '2019-10-23 14:05:31.168533', NULL, 'Poranga', 6);
INSERT INTO endereco.cidade VALUES (765, '2019-10-23 14:05:31.168533', NULL, 'Potengi', 6);
INSERT INTO endereco.cidade VALUES (767, '2019-10-23 14:05:31.168533', NULL, 'Quiterianópolis', 6);
INSERT INTO endereco.cidade VALUES (769, '2019-10-23 14:05:31.168533', NULL, 'Quixelô', 6);
INSERT INTO endereco.cidade VALUES (771, '2019-10-23 14:05:31.168533', NULL, 'Quixeré', 6);
INSERT INTO endereco.cidade VALUES (772, '2019-10-23 14:05:31.168533', NULL, 'Redenção', 6);
INSERT INTO endereco.cidade VALUES (2721, '2019-10-23 14:05:31.168533', NULL, 'Riacho De Santo Antônio', 15);
INSERT INTO endereco.cidade VALUES (776, '2019-10-23 14:05:31.168533', NULL, 'Salitre', 6);
INSERT INTO endereco.cidade VALUES (778, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Acaraú', 6);
INSERT INTO endereco.cidade VALUES (780, '2019-10-23 14:05:31.168533', NULL, 'São Benedito', 6);
INSERT INTO endereco.cidade VALUES (781, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Do Amarante', 6);
INSERT INTO endereco.cidade VALUES (783, '2019-10-23 14:05:31.168533', NULL, 'São Luís Do Curu', 6);
INSERT INTO endereco.cidade VALUES (784, '2019-10-23 14:05:31.168533', NULL, 'Senador Pompeu', 6);
INSERT INTO endereco.cidade VALUES (786, '2019-10-23 14:05:31.168533', NULL, 'Sobral', 6);
INSERT INTO endereco.cidade VALUES (788, '2019-10-23 14:05:31.168533', NULL, 'Tabuleiro Do Norte', 6);
INSERT INTO endereco.cidade VALUES (790, '2019-10-23 14:05:31.168533', NULL, 'Tarrafas', 6);
INSERT INTO endereco.cidade VALUES (791, '2019-10-23 14:05:31.168533', NULL, 'Tauá', 6);
INSERT INTO endereco.cidade VALUES (793, '2019-10-23 14:05:31.168533', NULL, 'Tianguá', 6);
INSERT INTO endereco.cidade VALUES (795, '2019-10-23 14:05:31.168533', NULL, 'Tururu', 6);
INSERT INTO endereco.cidade VALUES (797, '2019-10-23 14:05:31.168533', NULL, 'Umari', 6);
INSERT INTO endereco.cidade VALUES (798, '2019-10-23 14:05:31.168533', NULL, 'Umirim', 6);
INSERT INTO endereco.cidade VALUES (800, '2019-10-23 14:05:31.168533', NULL, 'Uruoca', 6);
INSERT INTO endereco.cidade VALUES (802, '2019-10-23 14:05:31.168533', NULL, 'Várzea Alegre', 6);
INSERT INTO endereco.cidade VALUES (804, '2019-10-23 14:05:31.168533', NULL, 'Brasília', 7);
INSERT INTO endereco.cidade VALUES (806, '2019-10-23 14:05:31.168533', NULL, 'Água Doce Do Norte', 8);
INSERT INTO endereco.cidade VALUES (807, '2019-10-23 14:05:31.168533', NULL, 'Águia Branca', 8);
INSERT INTO endereco.cidade VALUES (809, '2019-10-23 14:05:31.168533', NULL, 'Alfredo Chaves', 8);
INSERT INTO endereco.cidade VALUES (811, '2019-10-23 14:05:31.168533', NULL, 'Anchieta', 8);
INSERT INTO endereco.cidade VALUES (812, '2019-10-23 14:05:31.168533', NULL, 'Apiacá', 8);
INSERT INTO endereco.cidade VALUES (815, '2019-10-23 14:05:31.168533', NULL, 'Baixo Guandu', 8);
INSERT INTO endereco.cidade VALUES (816, '2019-10-23 14:05:31.168533', NULL, 'Barra De São Francisco', 8);
INSERT INTO endereco.cidade VALUES (818, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Do Norte', 8);
INSERT INTO endereco.cidade VALUES (819, '2019-10-23 14:05:31.168533', NULL, 'Brejetuba', 8);
INSERT INTO endereco.cidade VALUES (821, '2019-10-23 14:05:31.168533', NULL, 'Cariacica', 8);
INSERT INTO endereco.cidade VALUES (823, '2019-10-23 14:05:31.168533', NULL, 'Colatina', 8);
INSERT INTO endereco.cidade VALUES (825, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Castelo', 8);
INSERT INTO endereco.cidade VALUES (826, '2019-10-23 14:05:31.168533', NULL, 'Divino De São Lourenço', 8);
INSERT INTO endereco.cidade VALUES (828, '2019-10-23 14:05:31.168533', NULL, 'Dores Do Rio Preto', 8);
INSERT INTO endereco.cidade VALUES (829, '2019-10-23 14:05:31.168533', NULL, 'Ecoporanga', 8);
INSERT INTO endereco.cidade VALUES (832, '2019-10-23 14:05:31.168533', NULL, 'Guaçuí', 8);
INSERT INTO endereco.cidade VALUES (833, '2019-10-23 14:05:31.168533', NULL, 'Guarapari', 8);
INSERT INTO endereco.cidade VALUES (835, '2019-10-23 14:05:31.168533', NULL, 'Ibiraçu', 8);
INSERT INTO endereco.cidade VALUES (836, '2019-10-23 14:05:31.168533', NULL, 'Ibitirama', 8);
INSERT INTO endereco.cidade VALUES (838, '2019-10-23 14:05:31.168533', NULL, 'Irupi', 8);
INSERT INTO endereco.cidade VALUES (840, '2019-10-23 14:05:31.168533', NULL, 'Itapemirim', 8);
INSERT INTO endereco.cidade VALUES (842, '2019-10-23 14:05:31.168533', NULL, 'Iúna', 8);
INSERT INTO endereco.cidade VALUES (843, '2019-10-23 14:05:31.168533', NULL, 'Jaguaré', 8);
INSERT INTO endereco.cidade VALUES (845, '2019-10-23 14:05:31.168533', NULL, 'João Neiva', 8);
INSERT INTO endereco.cidade VALUES (847, '2019-10-23 14:05:31.168533', NULL, 'Linhares', 8);
INSERT INTO endereco.cidade VALUES (849, '2019-10-23 14:05:31.168533', NULL, 'Marataízes', 8);
INSERT INTO endereco.cidade VALUES (850, '2019-10-23 14:05:31.168533', NULL, 'Marechal Floriano', 8);
INSERT INTO endereco.cidade VALUES (852, '2019-10-23 14:05:31.168533', NULL, 'Mimoso Do Sul', 8);
INSERT INTO endereco.cidade VALUES (854, '2019-10-23 14:05:31.168533', NULL, 'Mucurici', 8);
INSERT INTO endereco.cidade VALUES (856, '2019-10-23 14:05:31.168533', NULL, 'Muqui', 8);
INSERT INTO endereco.cidade VALUES (857, '2019-10-23 14:05:31.168533', NULL, 'Nova Venécia', 8);
INSERT INTO endereco.cidade VALUES (859, '2019-10-23 14:05:31.168533', NULL, 'Pedro Canário', 8);
INSERT INTO endereco.cidade VALUES (861, '2019-10-23 14:05:31.168533', NULL, 'Piúma', 8);
INSERT INTO endereco.cidade VALUES (862, '2019-10-23 14:05:31.168533', NULL, 'Ponto Belo', 8);
INSERT INTO endereco.cidade VALUES (864, '2019-10-23 14:05:31.168533', NULL, 'Rio Bananal', 8);
INSERT INTO endereco.cidade VALUES (866, '2019-10-23 14:05:31.168533', NULL, 'Santa Leopoldina', 8);
INSERT INTO endereco.cidade VALUES (868, '2019-10-23 14:05:31.168533', NULL, 'Santa Teresa', 8);
INSERT INTO endereco.cidade VALUES (869, '2019-10-23 14:05:31.168533', NULL, 'São Domingos Do Norte', 8);
INSERT INTO endereco.cidade VALUES (871, '2019-10-23 14:05:31.168533', NULL, 'São José Do Calçado', 8);
INSERT INTO endereco.cidade VALUES (872, '2019-10-23 14:05:31.168533', NULL, 'São Mateus', 8);
INSERT INTO endereco.cidade VALUES (874, '2019-10-23 14:05:31.168533', NULL, 'Serra', 8);
INSERT INTO endereco.cidade VALUES (875, '2019-10-23 14:05:31.168533', NULL, 'Sooretama', 8);
INSERT INTO endereco.cidade VALUES (877, '2019-10-23 14:05:31.168533', NULL, 'Venda Nova Do Imigrante', 8);
INSERT INTO endereco.cidade VALUES (879, '2019-10-23 14:05:31.168533', NULL, 'Vila Pavão', 8);
INSERT INTO endereco.cidade VALUES (881, '2019-10-23 14:05:31.168533', NULL, 'Vila Velha', 8);
INSERT INTO endereco.cidade VALUES (882, '2019-10-23 14:05:31.168533', NULL, 'Vitória', 8);
INSERT INTO endereco.cidade VALUES (884, '2019-10-23 14:05:31.168533', NULL, 'Abadiânia', 9);
INSERT INTO endereco.cidade VALUES (886, '2019-10-23 14:05:31.168533', NULL, 'Adelândia', 9);
INSERT INTO endereco.cidade VALUES (888, '2019-10-23 14:05:31.168533', NULL, 'Água Limpa', 9);
INSERT INTO endereco.cidade VALUES (889, '2019-10-23 14:05:31.168533', NULL, 'Águas Lindas De Goiás', 9);
INSERT INTO endereco.cidade VALUES (891, '2019-10-23 14:05:31.168533', NULL, 'Aloândia', 9);
INSERT INTO endereco.cidade VALUES (893, '2019-10-23 14:05:31.168533', NULL, 'Alto Paraíso De Goiás', 9);
INSERT INTO endereco.cidade VALUES (894, '2019-10-23 14:05:31.168533', NULL, 'Alvorada Do Norte', 9);
INSERT INTO endereco.cidade VALUES (896, '2019-10-23 14:05:31.168533', NULL, 'Americano Do Brasil', 9);
INSERT INTO endereco.cidade VALUES (898, '2019-10-23 14:05:31.168533', NULL, 'Anápolis', 9);
INSERT INTO endereco.cidade VALUES (2724, '2019-10-23 14:05:31.168533', NULL, 'Salgadinho', 15);
INSERT INTO endereco.cidade VALUES (902, '2019-10-23 14:05:31.168533', NULL, 'Aparecida Do Rio Doce', 9);
INSERT INTO endereco.cidade VALUES (903, '2019-10-23 14:05:31.168533', NULL, 'Aporé', 9);
INSERT INTO endereco.cidade VALUES (905, '2019-10-23 14:05:31.168533', NULL, 'Aragarças', 9);
INSERT INTO endereco.cidade VALUES (907, '2019-10-23 14:05:31.168533', NULL, 'Araguapaz', 9);
INSERT INTO endereco.cidade VALUES (909, '2019-10-23 14:05:31.168533', NULL, 'Aruanã', 9);
INSERT INTO endereco.cidade VALUES (910, '2019-10-23 14:05:31.168533', NULL, 'Aurilândia', 9);
INSERT INTO endereco.cidade VALUES (912, '2019-10-23 14:05:31.168533', NULL, 'Baliza', 9);
INSERT INTO endereco.cidade VALUES (914, '2019-10-23 14:05:31.168533', NULL, 'Bela Vista De Goiás', 9);
INSERT INTO endereco.cidade VALUES (916, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus De Goiás', 9);
INSERT INTO endereco.cidade VALUES (917, '2019-10-23 14:05:31.168533', NULL, 'Bonfinópolis', 9);
INSERT INTO endereco.cidade VALUES (919, '2019-10-23 14:05:31.168533', NULL, 'Brazabrantes', 9);
INSERT INTO endereco.cidade VALUES (921, '2019-10-23 14:05:31.168533', NULL, 'Buriti Alegre', 9);
INSERT INTO endereco.cidade VALUES (923, '2019-10-23 14:05:31.168533', NULL, 'Buritinópolis', 9);
INSERT INTO endereco.cidade VALUES (924, '2019-10-23 14:05:31.168533', NULL, 'Cabeceiras', 9);
INSERT INTO endereco.cidade VALUES (926, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira De Goiás', 9);
INSERT INTO endereco.cidade VALUES (928, '2019-10-23 14:05:31.168533', NULL, 'Caçu', 9);
INSERT INTO endereco.cidade VALUES (929, '2019-10-23 14:05:31.168533', NULL, 'Caiapônia', 9);
INSERT INTO endereco.cidade VALUES (931, '2019-10-23 14:05:31.168533', NULL, 'Caldazinha', 9);
INSERT INTO endereco.cidade VALUES (933, '2019-10-23 14:05:31.168533', NULL, 'Campinaçu', 9);
INSERT INTO endereco.cidade VALUES (934, '2019-10-23 14:05:31.168533', NULL, 'Campinorte', 9);
INSERT INTO endereco.cidade VALUES (936, '2019-10-23 14:05:31.168533', NULL, 'Campo Limpo De Goiás', 9);
INSERT INTO endereco.cidade VALUES (938, '2019-10-23 14:05:31.168533', NULL, 'Campos Verdes', 9);
INSERT INTO endereco.cidade VALUES (940, '2019-10-23 14:05:31.168533', NULL, 'Castelândia', 9);
INSERT INTO endereco.cidade VALUES (941, '2019-10-23 14:05:31.168533', NULL, 'Catalão', 9);
INSERT INTO endereco.cidade VALUES (943, '2019-10-23 14:05:31.168533', NULL, 'Cavalcante', 9);
INSERT INTO endereco.cidade VALUES (944, '2019-10-23 14:05:31.168533', NULL, 'Ceres', 9);
INSERT INTO endereco.cidade VALUES (946, '2019-10-23 14:05:31.168533', NULL, 'Chapadão Do Céu', 9);
INSERT INTO endereco.cidade VALUES (948, '2019-10-23 14:05:31.168533', NULL, 'Cocalzinho De Goiás', 9);
INSERT INTO endereco.cidade VALUES (950, '2019-10-23 14:05:31.168533', NULL, 'Córrego Do Ouro', 9);
INSERT INTO endereco.cidade VALUES (951, '2019-10-23 14:05:31.168533', NULL, 'Corumbá De Goiás', 9);
INSERT INTO endereco.cidade VALUES (953, '2019-10-23 14:05:31.168533', NULL, 'Cristalina', 9);
INSERT INTO endereco.cidade VALUES (955, '2019-10-23 14:05:31.168533', NULL, 'Crixás', 9);
INSERT INTO endereco.cidade VALUES (957, '2019-10-23 14:05:31.168533', NULL, 'Cumari', 9);
INSERT INTO endereco.cidade VALUES (958, '2019-10-23 14:05:31.168533', NULL, 'Damianópolis', 9);
INSERT INTO endereco.cidade VALUES (960, '2019-10-23 14:05:31.168533', NULL, 'Davinópolis', 9);
INSERT INTO endereco.cidade VALUES (962, '2019-10-23 14:05:31.168533', NULL, 'Divinópolis De Goiás', 9);
INSERT INTO endereco.cidade VALUES (964, '2019-10-23 14:05:31.168533', NULL, 'Edealina', 9);
INSERT INTO endereco.cidade VALUES (965, '2019-10-23 14:05:31.168533', NULL, 'Edéia', 9);
INSERT INTO endereco.cidade VALUES (967, '2019-10-23 14:05:31.168533', NULL, 'Faina', 9);
INSERT INTO endereco.cidade VALUES (969, '2019-10-23 14:05:31.168533', NULL, 'Firminópolis', 9);
INSERT INTO endereco.cidade VALUES (971, '2019-10-23 14:05:31.168533', NULL, 'Formosa', 9);
INSERT INTO endereco.cidade VALUES (972, '2019-10-23 14:05:31.168533', NULL, 'Formoso', 9);
INSERT INTO endereco.cidade VALUES (974, '2019-10-23 14:05:31.168533', NULL, 'Goianápolis', 9);
INSERT INTO endereco.cidade VALUES (975, '2019-10-23 14:05:31.168533', NULL, 'Goiandira', 9);
INSERT INTO endereco.cidade VALUES (977, '2019-10-23 14:05:31.168533', NULL, 'Goiânia', 9);
INSERT INTO endereco.cidade VALUES (979, '2019-10-23 14:05:31.168533', NULL, 'Goiás', 9);
INSERT INTO endereco.cidade VALUES (981, '2019-10-23 14:05:31.168533', NULL, 'Gouvelândia', 9);
INSERT INTO endereco.cidade VALUES (982, '2019-10-23 14:05:31.168533', NULL, 'Guapó', 9);
INSERT INTO endereco.cidade VALUES (984, '2019-10-23 14:05:31.168533', NULL, 'Guarani De Goiás', 9);
INSERT INTO endereco.cidade VALUES (986, '2019-10-23 14:05:31.168533', NULL, 'Heitoraí', 9);
INSERT INTO endereco.cidade VALUES (988, '2019-10-23 14:05:31.168533', NULL, 'Hidrolina', 9);
INSERT INTO endereco.cidade VALUES (989, '2019-10-23 14:05:31.168533', NULL, 'Iaciara', 9);
INSERT INTO endereco.cidade VALUES (991, '2019-10-23 14:05:31.168533', NULL, 'Indiara', 9);
INSERT INTO endereco.cidade VALUES (993, '2019-10-23 14:05:31.168533', NULL, 'Ipameri', 9);
INSERT INTO endereco.cidade VALUES (995, '2019-10-23 14:05:31.168533', NULL, 'Iporá', 9);
INSERT INTO endereco.cidade VALUES (996, '2019-10-23 14:05:31.168533', NULL, 'Israelândia', 9);
INSERT INTO endereco.cidade VALUES (998, '2019-10-23 14:05:31.168533', NULL, 'Itaguari', 9);
INSERT INTO endereco.cidade VALUES (1000, '2019-10-23 14:05:31.168533', NULL, 'Itajá', 9);
INSERT INTO endereco.cidade VALUES (1002, '2019-10-23 14:05:31.168533', NULL, 'Itapirapuã', 9);
INSERT INTO endereco.cidade VALUES (1003, '2019-10-23 14:05:31.168533', NULL, 'Itapuranga', 9);
INSERT INTO endereco.cidade VALUES (1005, '2019-10-23 14:05:31.168533', NULL, 'Itauçu', 9);
INSERT INTO endereco.cidade VALUES (1007, '2019-10-23 14:05:31.168533', NULL, 'Ivolândia', 9);
INSERT INTO endereco.cidade VALUES (1009, '2019-10-23 14:05:31.168533', NULL, 'Jaraguá', 9);
INSERT INTO endereco.cidade VALUES (1011, '2019-10-23 14:05:31.168533', NULL, 'Jaupaci', 9);
INSERT INTO endereco.cidade VALUES (1012, '2019-10-23 14:05:31.168533', NULL, 'Jesúpolis', 9);
INSERT INTO endereco.cidade VALUES (1014, '2019-10-23 14:05:31.168533', NULL, 'Jussara', 9);
INSERT INTO endereco.cidade VALUES (1016, '2019-10-23 14:05:31.168533', NULL, 'Leopoldo De Bulhões', 9);
INSERT INTO endereco.cidade VALUES (1018, '2019-10-23 14:05:31.168533', NULL, 'Mairipotaba', 9);
INSERT INTO endereco.cidade VALUES (1020, '2019-10-23 14:05:31.168533', NULL, 'Mara Rosa', 9);
INSERT INTO endereco.cidade VALUES (1021, '2019-10-23 14:05:31.168533', NULL, 'Marzagão', 9);
INSERT INTO endereco.cidade VALUES (1023, '2019-10-23 14:05:31.168533', NULL, 'Maurilândia', 9);
INSERT INTO endereco.cidade VALUES (1025, '2019-10-23 14:05:31.168533', NULL, 'Minaçu', 9);
INSERT INTO endereco.cidade VALUES (1027, '2019-10-23 14:05:31.168533', NULL, 'Moiporá', 9);
INSERT INTO endereco.cidade VALUES (2863, '2019-10-23 14:05:31.168533', NULL, 'Ipojuca', 16);
INSERT INTO endereco.cidade VALUES (1029, '2019-10-23 14:05:31.168533', NULL, 'Montes Claros De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1031, '2019-10-23 14:05:31.168533', NULL, 'Montividiu Do Norte', 9);
INSERT INTO endereco.cidade VALUES (1033, '2019-10-23 14:05:31.168533', NULL, 'Morro Agudo De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1034, '2019-10-23 14:05:31.168533', NULL, 'Mossâmedes', 9);
INSERT INTO endereco.cidade VALUES (1036, '2019-10-23 14:05:31.168533', NULL, 'Mundo Novo', 9);
INSERT INTO endereco.cidade VALUES (1038, '2019-10-23 14:05:31.168533', NULL, 'Nazário', 9);
INSERT INTO endereco.cidade VALUES (1039, '2019-10-23 14:05:31.168533', NULL, 'Nerópolis', 9);
INSERT INTO endereco.cidade VALUES (1041, '2019-10-23 14:05:31.168533', NULL, 'Nova América', 9);
INSERT INTO endereco.cidade VALUES (1043, '2019-10-23 14:05:31.168533', NULL, 'Nova Crixás', 9);
INSERT INTO endereco.cidade VALUES (1045, '2019-10-23 14:05:31.168533', NULL, 'Nova Iguaçu De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1046, '2019-10-23 14:05:31.168533', NULL, 'Nova Roma', 9);
INSERT INTO endereco.cidade VALUES (1048, '2019-10-23 14:05:31.168533', NULL, 'Novo Brasil', 9);
INSERT INTO endereco.cidade VALUES (1050, '2019-10-23 14:05:31.168533', NULL, 'Novo Planalto', 9);
INSERT INTO endereco.cidade VALUES (1052, '2019-10-23 14:05:31.168533', NULL, 'Ouro Verde De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1054, '2019-10-23 14:05:31.168533', NULL, 'Padre Bernardo', 9);
INSERT INTO endereco.cidade VALUES (1056, '2019-10-23 14:05:31.168533', NULL, 'Palmeiras De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1057, '2019-10-23 14:05:31.168533', NULL, 'Palmelo', 9);
INSERT INTO endereco.cidade VALUES (1059, '2019-10-23 14:05:31.168533', NULL, 'Panamá', 9);
INSERT INTO endereco.cidade VALUES (1061, '2019-10-23 14:05:31.168533', NULL, 'Paraúna', 9);
INSERT INTO endereco.cidade VALUES (1062, '2019-10-23 14:05:31.168533', NULL, 'Perolândia', 9);
INSERT INTO endereco.cidade VALUES (1064, '2019-10-23 14:05:31.168533', NULL, 'Pilar De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1066, '2019-10-23 14:05:31.168533', NULL, 'Piranhas', 9);
INSERT INTO endereco.cidade VALUES (1068, '2019-10-23 14:05:31.168533', NULL, 'Pires Do Rio', 9);
INSERT INTO endereco.cidade VALUES (1069, '2019-10-23 14:05:31.168533', NULL, 'Planaltina', 9);
INSERT INTO endereco.cidade VALUES (1071, '2019-10-23 14:05:31.168533', NULL, 'Porangatu', 9);
INSERT INTO endereco.cidade VALUES (1073, '2019-10-23 14:05:31.168533', NULL, 'Portelândia', 9);
INSERT INTO endereco.cidade VALUES (1074, '2019-10-23 14:05:31.168533', NULL, 'Posse', 9);
INSERT INTO endereco.cidade VALUES (1076, '2019-10-23 14:05:31.168533', NULL, 'Quirinópolis', 9);
INSERT INTO endereco.cidade VALUES (1078, '2019-10-23 14:05:31.168533', NULL, 'Rianápolis', 9);
INSERT INTO endereco.cidade VALUES (1080, '2019-10-23 14:05:31.168533', NULL, 'Rio Verde', 9);
INSERT INTO endereco.cidade VALUES (1081, '2019-10-23 14:05:31.168533', NULL, 'Rubiataba', 9);
INSERT INTO endereco.cidade VALUES (1083, '2019-10-23 14:05:31.168533', NULL, 'Santa Bárbara De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1085, '2019-10-23 14:05:31.168533', NULL, 'Santa Fé De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1087, '2019-10-23 14:05:31.168533', NULL, 'Santa Isabel', 9);
INSERT INTO endereco.cidade VALUES (1088, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita Do Araguaia', 9);
INSERT INTO endereco.cidade VALUES (1090, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1092, '2019-10-23 14:05:31.168533', NULL, 'Santa Terezinha De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1093, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Da Barra', 9);
INSERT INTO endereco.cidade VALUES (1095, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Descoberto', 9);
INSERT INTO endereco.cidade VALUES (1096, '2019-10-23 14:05:31.168533', NULL, 'São Domingos', 9);
INSERT INTO endereco.cidade VALUES (1098, '2019-10-23 14:05:31.168533', NULL, 'São João D`Aliança', 9);
INSERT INTO endereco.cidade VALUES (1100, '2019-10-23 14:05:31.168533', NULL, 'São Luís De Montes Belos', 9);
INSERT INTO endereco.cidade VALUES (1101, '2019-10-23 14:05:31.168533', NULL, 'São Luíz Do Norte', 9);
INSERT INTO endereco.cidade VALUES (1103, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Passa Quatro', 9);
INSERT INTO endereco.cidade VALUES (1104, '2019-10-23 14:05:31.168533', NULL, 'São Patrício', 9);
INSERT INTO endereco.cidade VALUES (1106, '2019-10-23 14:05:31.168533', NULL, 'Senador Canedo', 9);
INSERT INTO endereco.cidade VALUES (1108, '2019-10-23 14:05:31.168533', NULL, 'Silvânia', 9);
INSERT INTO endereco.cidade VALUES (1109, '2019-10-23 14:05:31.168533', NULL, 'Simolândia', 9);
INSERT INTO endereco.cidade VALUES (1111, '2019-10-23 14:05:31.168533', NULL, 'Taquaral De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1113, '2019-10-23 14:05:31.168533', NULL, 'Terezópolis De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1115, '2019-10-23 14:05:31.168533', NULL, 'Trindade', 9);
INSERT INTO endereco.cidade VALUES (1116, '2019-10-23 14:05:31.168533', NULL, 'Trombas', 9);
INSERT INTO endereco.cidade VALUES (1118, '2019-10-23 14:05:31.168533', NULL, 'Turvelândia', 9);
INSERT INTO endereco.cidade VALUES (1120, '2019-10-23 14:05:31.168533', NULL, 'Uruaçu', 9);
INSERT INTO endereco.cidade VALUES (1122, '2019-10-23 14:05:31.168533', NULL, 'Urutaí', 9);
INSERT INTO endereco.cidade VALUES (1124, '2019-10-23 14:05:31.168533', NULL, 'Varjão', 9);
INSERT INTO endereco.cidade VALUES (1125, '2019-10-23 14:05:31.168533', NULL, 'Vianópolis', 9);
INSERT INTO endereco.cidade VALUES (1127, '2019-10-23 14:05:31.168533', NULL, 'Vila Boa', 9);
INSERT INTO endereco.cidade VALUES (1128, '2019-10-23 14:05:31.168533', NULL, 'Vila Propício', 9);
INSERT INTO endereco.cidade VALUES (1130, '2019-10-23 14:05:31.168533', NULL, 'Afonso Cunha', 10);
INSERT INTO endereco.cidade VALUES (1132, '2019-10-23 14:05:31.168533', NULL, 'Alcântara', 10);
INSERT INTO endereco.cidade VALUES (1133, '2019-10-23 14:05:31.168533', NULL, 'Aldeias Altas', 10);
INSERT INTO endereco.cidade VALUES (1135, '2019-10-23 14:05:31.168533', NULL, 'Alto Alegre Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1137, '2019-10-23 14:05:31.168533', NULL, 'Alto Parnaíba', 10);
INSERT INTO endereco.cidade VALUES (1138, '2019-10-23 14:05:31.168533', NULL, 'Amapá Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1140, '2019-10-23 14:05:31.168533', NULL, 'Anajatuba', 10);
INSERT INTO endereco.cidade VALUES (1141, '2019-10-23 14:05:31.168533', NULL, 'Anapurus', 10);
INSERT INTO endereco.cidade VALUES (1143, '2019-10-23 14:05:31.168533', NULL, 'Araguanã', 10);
INSERT INTO endereco.cidade VALUES (1145, '2019-10-23 14:05:31.168533', NULL, 'Arame', 10);
INSERT INTO endereco.cidade VALUES (1147, '2019-10-23 14:05:31.168533', NULL, 'Axixá', 10);
INSERT INTO endereco.cidade VALUES (1148, '2019-10-23 14:05:31.168533', NULL, 'Bacabal', 10);
INSERT INTO endereco.cidade VALUES (1152, '2019-10-23 14:05:31.168533', NULL, 'Balsas', 10);
INSERT INTO endereco.cidade VALUES (1154, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Corda', 10);
INSERT INTO endereco.cidade VALUES (1156, '2019-10-23 14:05:31.168533', NULL, 'Bela Vista Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1157, '2019-10-23 14:05:31.168533', NULL, 'Belágua', 10);
INSERT INTO endereco.cidade VALUES (1159, '2019-10-23 14:05:31.168533', NULL, 'Bequimão', 10);
INSERT INTO endereco.cidade VALUES (1161, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista Do Gurupi', 10);
INSERT INTO endereco.cidade VALUES (1162, '2019-10-23 14:05:31.168533', NULL, 'Bom Jardim', 10);
INSERT INTO endereco.cidade VALUES (1164, '2019-10-23 14:05:31.168533', NULL, 'Bom Lugar', 10);
INSERT INTO endereco.cidade VALUES (1166, '2019-10-23 14:05:31.168533', NULL, 'Brejo De Areia', 10);
INSERT INTO endereco.cidade VALUES (1167, '2019-10-23 14:05:31.168533', NULL, 'Buriti', 10);
INSERT INTO endereco.cidade VALUES (1169, '2019-10-23 14:05:31.168533', NULL, 'Buriticupu', 10);
INSERT INTO endereco.cidade VALUES (1171, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Grande', 10);
INSERT INTO endereco.cidade VALUES (1173, '2019-10-23 14:05:31.168533', NULL, 'Cajari', 10);
INSERT INTO endereco.cidade VALUES (1175, '2019-10-23 14:05:31.168533', NULL, 'Cândido Mendes', 10);
INSERT INTO endereco.cidade VALUES (1176, '2019-10-23 14:05:31.168533', NULL, 'Cantanhede', 10);
INSERT INTO endereco.cidade VALUES (1178, '2019-10-23 14:05:31.168533', NULL, 'Carolina', 10);
INSERT INTO endereco.cidade VALUES (1179, '2019-10-23 14:05:31.168533', NULL, 'Carutapera', 10);
INSERT INTO endereco.cidade VALUES (1181, '2019-10-23 14:05:31.168533', NULL, 'Cedral', 10);
INSERT INTO endereco.cidade VALUES (1183, '2019-10-23 14:05:31.168533', NULL, 'Centro Do Guilherme', 10);
INSERT INTO endereco.cidade VALUES (1185, '2019-10-23 14:05:31.168533', NULL, 'Chapadinha', 10);
INSERT INTO endereco.cidade VALUES (1186, '2019-10-23 14:05:31.168533', NULL, 'Cidelândia', 10);
INSERT INTO endereco.cidade VALUES (1188, '2019-10-23 14:05:31.168533', NULL, 'Coelho Neto', 10);
INSERT INTO endereco.cidade VALUES (1190, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Lago Açu', 10);
INSERT INTO endereco.cidade VALUES (1191, '2019-10-23 14:05:31.168533', NULL, 'Coroatá', 10);
INSERT INTO endereco.cidade VALUES (1193, '2019-10-23 14:05:31.168533', NULL, 'Davinópolis', 10);
INSERT INTO endereco.cidade VALUES (1195, '2019-10-23 14:05:31.168533', NULL, 'Duque Bacelar', 10);
INSERT INTO endereco.cidade VALUES (1197, '2019-10-23 14:05:31.168533', NULL, 'Estreito', 10);
INSERT INTO endereco.cidade VALUES (1199, '2019-10-23 14:05:31.168533', NULL, 'Fernando Falcão', 10);
INSERT INTO endereco.cidade VALUES (1200, '2019-10-23 14:05:31.168533', NULL, 'Formosa Da Serra Negra', 10);
INSERT INTO endereco.cidade VALUES (1202, '2019-10-23 14:05:31.168533', NULL, 'Fortuna', 10);
INSERT INTO endereco.cidade VALUES (1203, '2019-10-23 14:05:31.168533', NULL, 'Godofredo Viana', 10);
INSERT INTO endereco.cidade VALUES (1205, '2019-10-23 14:05:31.168533', NULL, 'Governador Archer', 10);
INSERT INTO endereco.cidade VALUES (1207, '2019-10-23 14:05:31.168533', NULL, 'Governador Eugênio Barros', 10);
INSERT INTO endereco.cidade VALUES (1209, '2019-10-23 14:05:31.168533', NULL, 'Governador Newton Bello', 10);
INSERT INTO endereco.cidade VALUES (1210, '2019-10-23 14:05:31.168533', NULL, 'Governador Nunes Freire', 10);
INSERT INTO endereco.cidade VALUES (1212, '2019-10-23 14:05:31.168533', NULL, 'Grajaú', 10);
INSERT INTO endereco.cidade VALUES (1213, '2019-10-23 14:05:31.168533', NULL, 'Guimarães', 10);
INSERT INTO endereco.cidade VALUES (1215, '2019-10-23 14:05:31.168533', NULL, 'Icatu', 10);
INSERT INTO endereco.cidade VALUES (1217, '2019-10-23 14:05:31.168533', NULL, 'Igarapé Grande', 10);
INSERT INTO endereco.cidade VALUES (1218, '2019-10-23 14:05:31.168533', NULL, 'Imperatriz', 10);
INSERT INTO endereco.cidade VALUES (1220, '2019-10-23 14:05:31.168533', NULL, 'Itapecuru Mirim', 10);
INSERT INTO endereco.cidade VALUES (1222, '2019-10-23 14:05:31.168533', NULL, 'Jatobá', 10);
INSERT INTO endereco.cidade VALUES (1224, '2019-10-23 14:05:31.168533', NULL, 'João Lisboa', 10);
INSERT INTO endereco.cidade VALUES (1225, '2019-10-23 14:05:31.168533', NULL, 'Joselândia', 10);
INSERT INTO endereco.cidade VALUES (1227, '2019-10-23 14:05:31.168533', NULL, 'Lago Da Pedra', 10);
INSERT INTO endereco.cidade VALUES (1229, '2019-10-23 14:05:31.168533', NULL, 'Lago Dos Rodrigues', 10);
INSERT INTO endereco.cidade VALUES (1230, '2019-10-23 14:05:31.168533', NULL, 'Lago Verde', 10);
INSERT INTO endereco.cidade VALUES (1232, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Grande Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1234, '2019-10-23 14:05:31.168533', NULL, 'Lima Campos', 10);
INSERT INTO endereco.cidade VALUES (1235, '2019-10-23 14:05:31.168533', NULL, 'Loreto', 10);
INSERT INTO endereco.cidade VALUES (1237, '2019-10-23 14:05:31.168533', NULL, 'Magalhães De Almeida', 10);
INSERT INTO endereco.cidade VALUES (1239, '2019-10-23 14:05:31.168533', NULL, 'Marajá Do Sena', 10);
INSERT INTO endereco.cidade VALUES (1241, '2019-10-23 14:05:31.168533', NULL, 'Mata Roma', 10);
INSERT INTO endereco.cidade VALUES (1242, '2019-10-23 14:05:31.168533', NULL, 'Matinha', 10);
INSERT INTO endereco.cidade VALUES (1244, '2019-10-23 14:05:31.168533', NULL, 'Matões Do Norte', 10);
INSERT INTO endereco.cidade VALUES (1246, '2019-10-23 14:05:31.168533', NULL, 'Mirador', 10);
INSERT INTO endereco.cidade VALUES (1247, '2019-10-23 14:05:31.168533', NULL, 'Miranda Do Norte', 10);
INSERT INTO endereco.cidade VALUES (1249, '2019-10-23 14:05:31.168533', NULL, 'Monção', 10);
INSERT INTO endereco.cidade VALUES (1251, '2019-10-23 14:05:31.168533', NULL, 'Morros', 10);
INSERT INTO endereco.cidade VALUES (1252, '2019-10-23 14:05:31.168533', NULL, 'Nina Rodrigues', 10);
INSERT INTO endereco.cidade VALUES (1254, '2019-10-23 14:05:31.168533', NULL, 'Nova Iorque', 10);
INSERT INTO endereco.cidade VALUES (1256, '2019-10-23 14:05:31.168533', NULL, 'Olho D`Água Das Cunhãs', 10);
INSERT INTO endereco.cidade VALUES (1258, '2019-10-23 14:05:31.168533', NULL, 'Paço Do Lumiar', 10);
INSERT INTO endereco.cidade VALUES (1259, '2019-10-23 14:05:31.168533', NULL, 'Palmeirândia', 10);
INSERT INTO endereco.cidade VALUES (1261, '2019-10-23 14:05:31.168533', NULL, 'Parnarama', 10);
INSERT INTO endereco.cidade VALUES (1262, '2019-10-23 14:05:31.168533', NULL, 'Passagem Franca', 10);
INSERT INTO endereco.cidade VALUES (1264, '2019-10-23 14:05:31.168533', NULL, 'Paulino Neves', 10);
INSERT INTO endereco.cidade VALUES (1266, '2019-10-23 14:05:31.168533', NULL, 'Pedreiras', 10);
INSERT INTO endereco.cidade VALUES (1268, '2019-10-23 14:05:31.168533', NULL, 'Penalva', 10);
INSERT INTO endereco.cidade VALUES (1269, '2019-10-23 14:05:31.168533', NULL, 'Peri Mirim', 10);
INSERT INTO endereco.cidade VALUES (1271, '2019-10-23 14:05:31.168533', NULL, 'Pindaré Mirim', 10);
INSERT INTO endereco.cidade VALUES (1273, '2019-10-23 14:05:31.168533', NULL, 'Pio Xii', 10);
INSERT INTO endereco.cidade VALUES (1276, '2019-10-23 14:05:31.168533', NULL, 'Porto Franco', 10);
INSERT INTO endereco.cidade VALUES (1277, '2019-10-23 14:05:31.168533', NULL, 'Porto Rico Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1279, '2019-10-23 14:05:31.168533', NULL, 'Presidente Juscelino', 10);
INSERT INTO endereco.cidade VALUES (1281, '2019-10-23 14:05:31.168533', NULL, 'Presidente Sarney', 10);
INSERT INTO endereco.cidade VALUES (1282, '2019-10-23 14:05:31.168533', NULL, 'Presidente Vargas', 10);
INSERT INTO endereco.cidade VALUES (1284, '2019-10-23 14:05:31.168533', NULL, 'Raposa', 10);
INSERT INTO endereco.cidade VALUES (1286, '2019-10-23 14:05:31.168533', NULL, 'Ribamar Fiquene', 10);
INSERT INTO endereco.cidade VALUES (1287, '2019-10-23 14:05:31.168533', NULL, 'Rosário', 10);
INSERT INTO endereco.cidade VALUES (1290, '2019-10-23 14:05:31.168533', NULL, 'Santa Helena', 10);
INSERT INTO endereco.cidade VALUES (1291, '2019-10-23 14:05:31.168533', NULL, 'Santa Inês', 10);
INSERT INTO endereco.cidade VALUES (1293, '2019-10-23 14:05:31.168533', NULL, 'Santa Luzia Do Paruá', 10);
INSERT INTO endereco.cidade VALUES (1295, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita', 10);
INSERT INTO endereco.cidade VALUES (1296, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1298, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Dos Lopes', 10);
INSERT INTO endereco.cidade VALUES (1300, '2019-10-23 14:05:31.168533', NULL, 'São Bento', 10);
INSERT INTO endereco.cidade VALUES (1301, '2019-10-23 14:05:31.168533', NULL, 'São Bernardo', 10);
INSERT INTO endereco.cidade VALUES (1303, '2019-10-23 14:05:31.168533', NULL, 'São Domingos Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1304, '2019-10-23 14:05:31.168533', NULL, 'São Félix De Balsas', 10);
INSERT INTO endereco.cidade VALUES (1306, '2019-10-23 14:05:31.168533', NULL, 'São Francisco Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1308, '2019-10-23 14:05:31.168533', NULL, 'São João Do Carú', 10);
INSERT INTO endereco.cidade VALUES (1310, '2019-10-23 14:05:31.168533', NULL, 'São João Do Soter', 10);
INSERT INTO endereco.cidade VALUES (1311, '2019-10-23 14:05:31.168533', NULL, 'São João Dos Patos', 10);
INSERT INTO endereco.cidade VALUES (1313, '2019-10-23 14:05:31.168533', NULL, 'São José Dos Basílios', 10);
INSERT INTO endereco.cidade VALUES (1314, '2019-10-23 14:05:31.168533', NULL, 'São Luís', 10);
INSERT INTO endereco.cidade VALUES (1316, '2019-10-23 14:05:31.168533', NULL, 'São Mateus Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1317, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Da Água Branca', 10);
INSERT INTO endereco.cidade VALUES (1319, '2019-10-23 14:05:31.168533', NULL, 'São Raimundo Das Mangabeiras', 10);
INSERT INTO endereco.cidade VALUES (1321, '2019-10-23 14:05:31.168533', NULL, 'São Roberto', 10);
INSERT INTO endereco.cidade VALUES (1322, '2019-10-23 14:05:31.168533', NULL, 'São Vicente Ferrer', 10);
INSERT INTO endereco.cidade VALUES (1324, '2019-10-23 14:05:31.168533', NULL, 'Senador Alexandre Costa', 10);
INSERT INTO endereco.cidade VALUES (1326, '2019-10-23 14:05:31.168533', NULL, 'Serrano Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1327, '2019-10-23 14:05:31.168533', NULL, 'Sítio Novo', 10);
INSERT INTO endereco.cidade VALUES (1329, '2019-10-23 14:05:31.168533', NULL, 'Sucupira Do Riachão', 10);
INSERT INTO endereco.cidade VALUES (1331, '2019-10-23 14:05:31.168533', NULL, 'Timbiras', 10);
INSERT INTO endereco.cidade VALUES (1332, '2019-10-23 14:05:31.168533', NULL, 'Timon', 10);
INSERT INTO endereco.cidade VALUES (1334, '2019-10-23 14:05:31.168533', NULL, 'Tufilândia', 10);
INSERT INTO endereco.cidade VALUES (1336, '2019-10-23 14:05:31.168533', NULL, 'Turiaçu', 10);
INSERT INTO endereco.cidade VALUES (1337, '2019-10-23 14:05:31.168533', NULL, 'Turilândia', 10);
INSERT INTO endereco.cidade VALUES (1339, '2019-10-23 14:05:31.168533', NULL, 'Urbano Santos', 10);
INSERT INTO endereco.cidade VALUES (1341, '2019-10-23 14:05:31.168533', NULL, 'Viana', 10);
INSERT INTO endereco.cidade VALUES (1343, '2019-10-23 14:05:31.168533', NULL, 'Vitória Do Mearim', 10);
INSERT INTO endereco.cidade VALUES (1344, '2019-10-23 14:05:31.168533', NULL, 'Vitorino Freire', 10);
INSERT INTO endereco.cidade VALUES (1346, '2019-10-23 14:05:31.168533', NULL, 'Abadia Dos Dourados', 11);
INSERT INTO endereco.cidade VALUES (1348, '2019-10-23 14:05:31.168533', NULL, 'Abre Campo', 11);
INSERT INTO endereco.cidade VALUES (1350, '2019-10-23 14:05:31.168533', NULL, 'Açucena', 11);
INSERT INTO endereco.cidade VALUES (1351, '2019-10-23 14:05:31.168533', NULL, 'Água Boa', 11);
INSERT INTO endereco.cidade VALUES (1353, '2019-10-23 14:05:31.168533', NULL, 'Aguanil', 11);
INSERT INTO endereco.cidade VALUES (1355, '2019-10-23 14:05:31.168533', NULL, 'Águas Vermelhas', 11);
INSERT INTO endereco.cidade VALUES (1357, '2019-10-23 14:05:31.168533', NULL, 'Aiuruoca', 11);
INSERT INTO endereco.cidade VALUES (1358, '2019-10-23 14:05:31.168533', NULL, 'Alagoa', 11);
INSERT INTO endereco.cidade VALUES (1360, '2019-10-23 14:05:31.168533', NULL, 'Além Paraíba', 11);
INSERT INTO endereco.cidade VALUES (1362, '2019-10-23 14:05:31.168533', NULL, 'Alfredo Vasconcelos', 11);
INSERT INTO endereco.cidade VALUES (1364, '2019-10-23 14:05:31.168533', NULL, 'Alpercata', 11);
INSERT INTO endereco.cidade VALUES (1366, '2019-10-23 14:05:31.168533', NULL, 'Alterosa', 11);
INSERT INTO endereco.cidade VALUES (1367, '2019-10-23 14:05:31.168533', NULL, 'Alto Caparaó', 11);
INSERT INTO endereco.cidade VALUES (1369, '2019-10-23 14:05:31.168533', NULL, 'Alto Rio Doce', 11);
INSERT INTO endereco.cidade VALUES (1371, '2019-10-23 14:05:31.168533', NULL, 'Alvinópolis', 11);
INSERT INTO endereco.cidade VALUES (1373, '2019-10-23 14:05:31.168533', NULL, 'Amparo Do Serra', 11);
INSERT INTO endereco.cidade VALUES (1374, '2019-10-23 14:05:31.168533', NULL, 'Andradas', 11);
INSERT INTO endereco.cidade VALUES (1376, '2019-10-23 14:05:31.168533', NULL, 'Angelândia', 11);
INSERT INTO endereco.cidade VALUES (1378, '2019-10-23 14:05:31.168533', NULL, 'Antônio Dias', 11);
INSERT INTO endereco.cidade VALUES (1380, '2019-10-23 14:05:31.168533', NULL, 'Araçaí', 11);
INSERT INTO endereco.cidade VALUES (1381, '2019-10-23 14:05:31.168533', NULL, 'Aracitaba', 11);
INSERT INTO endereco.cidade VALUES (1383, '2019-10-23 14:05:31.168533', NULL, 'Araguari', 11);
INSERT INTO endereco.cidade VALUES (1384, '2019-10-23 14:05:31.168533', NULL, 'Arantina', 11);
INSERT INTO endereco.cidade VALUES (1386, '2019-10-23 14:05:31.168533', NULL, 'Araporã', 11);
INSERT INTO endereco.cidade VALUES (1388, '2019-10-23 14:05:31.168533', NULL, 'Araújos', 11);
INSERT INTO endereco.cidade VALUES (1390, '2019-10-23 14:05:31.168533', NULL, 'Arceburgo', 11);
INSERT INTO endereco.cidade VALUES (1392, '2019-10-23 14:05:31.168533', NULL, 'Areado', 11);
INSERT INTO endereco.cidade VALUES (1393, '2019-10-23 14:05:31.168533', NULL, 'Argirita', 11);
INSERT INTO endereco.cidade VALUES (1395, '2019-10-23 14:05:31.168533', NULL, 'Arinos', 11);
INSERT INTO endereco.cidade VALUES (2726, '2019-10-23 14:05:31.168533', NULL, 'Santa Cecília', 15);
INSERT INTO endereco.cidade VALUES (1398, '2019-10-23 14:05:31.168533', NULL, 'Augusto De Lima', 11);
INSERT INTO endereco.cidade VALUES (1399, '2019-10-23 14:05:31.168533', NULL, 'Baependi', 11);
INSERT INTO endereco.cidade VALUES (1401, '2019-10-23 14:05:31.168533', NULL, 'Bambuí', 11);
INSERT INTO endereco.cidade VALUES (1403, '2019-10-23 14:05:31.168533', NULL, 'Bandeira Do Sul', 11);
INSERT INTO endereco.cidade VALUES (1405, '2019-10-23 14:05:31.168533', NULL, 'Barão De Monte Alto', 11);
INSERT INTO endereco.cidade VALUES (1406, '2019-10-23 14:05:31.168533', NULL, 'Barbacena', 11);
INSERT INTO endereco.cidade VALUES (1408, '2019-10-23 14:05:31.168533', NULL, 'Barroso', 11);
INSERT INTO endereco.cidade VALUES (1410, '2019-10-23 14:05:31.168533', NULL, 'Belmiro Braga', 11);
INSERT INTO endereco.cidade VALUES (1412, '2019-10-23 14:05:31.168533', NULL, 'Belo Oriente', 11);
INSERT INTO endereco.cidade VALUES (1413, '2019-10-23 14:05:31.168533', NULL, 'Belo Vale', 11);
INSERT INTO endereco.cidade VALUES (1415, '2019-10-23 14:05:31.168533', NULL, 'Berizal', 11);
INSERT INTO endereco.cidade VALUES (1417, '2019-10-23 14:05:31.168533', NULL, 'Betim', 11);
INSERT INTO endereco.cidade VALUES (1419, '2019-10-23 14:05:31.168533', NULL, 'Bicas', 11);
INSERT INTO endereco.cidade VALUES (1420, '2019-10-23 14:05:31.168533', NULL, 'Biquinhas', 11);
INSERT INTO endereco.cidade VALUES (1422, '2019-10-23 14:05:31.168533', NULL, 'Bocaina De Minas', 11);
INSERT INTO endereco.cidade VALUES (1424, '2019-10-23 14:05:31.168533', NULL, 'Bom Despacho', 11);
INSERT INTO endereco.cidade VALUES (1426, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Da Penha', 11);
INSERT INTO endereco.cidade VALUES (1427, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Do Amparo', 11);
INSERT INTO endereco.cidade VALUES (1429, '2019-10-23 14:05:31.168533', NULL, 'Bom Repouso', 11);
INSERT INTO endereco.cidade VALUES (1431, '2019-10-23 14:05:31.168533', NULL, 'Bonfim', 11);
INSERT INTO endereco.cidade VALUES (1433, '2019-10-23 14:05:31.168533', NULL, 'Bonito De Minas', 11);
INSERT INTO endereco.cidade VALUES (1434, '2019-10-23 14:05:31.168533', NULL, 'Borda Da Mata', 11);
INSERT INTO endereco.cidade VALUES (1436, '2019-10-23 14:05:31.168533', NULL, 'Botumirim', 11);
INSERT INTO endereco.cidade VALUES (1438, '2019-10-23 14:05:31.168533', NULL, 'Brasilândia De Minas', 11);
INSERT INTO endereco.cidade VALUES (1440, '2019-10-23 14:05:31.168533', NULL, 'Brasópolis', 11);
INSERT INTO endereco.cidade VALUES (1441, '2019-10-23 14:05:31.168533', NULL, 'Braúnas', 11);
INSERT INTO endereco.cidade VALUES (1443, '2019-10-23 14:05:31.168533', NULL, 'Bueno Brandão', 11);
INSERT INTO endereco.cidade VALUES (1445, '2019-10-23 14:05:31.168533', NULL, 'Bugre', 11);
INSERT INTO endereco.cidade VALUES (1446, '2019-10-23 14:05:31.168533', NULL, 'Buritis', 11);
INSERT INTO endereco.cidade VALUES (1448, '2019-10-23 14:05:31.168533', NULL, 'Cabeceira Grande', 11);
INSERT INTO endereco.cidade VALUES (1450, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Da Prata', 11);
INSERT INTO endereco.cidade VALUES (1452, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira De Pajeú', 11);
INSERT INTO endereco.cidade VALUES (1454, '2019-10-23 14:05:31.168533', NULL, 'Caetanópolis', 11);
INSERT INTO endereco.cidade VALUES (1455, '2019-10-23 14:05:31.168533', NULL, 'Caeté', 11);
INSERT INTO endereco.cidade VALUES (1457, '2019-10-23 14:05:31.168533', NULL, 'Cajuri', 11);
INSERT INTO endereco.cidade VALUES (1459, '2019-10-23 14:05:31.168533', NULL, 'Camacho', 11);
INSERT INTO endereco.cidade VALUES (1460, '2019-10-23 14:05:31.168533', NULL, 'Camanducaia', 11);
INSERT INTO endereco.cidade VALUES (1462, '2019-10-23 14:05:31.168533', NULL, 'Cambuquira', 11);
INSERT INTO endereco.cidade VALUES (1464, '2019-10-23 14:05:31.168533', NULL, 'Campanha', 11);
INSERT INTO endereco.cidade VALUES (1466, '2019-10-23 14:05:31.168533', NULL, 'Campina Verde', 11);
INSERT INTO endereco.cidade VALUES (1468, '2019-10-23 14:05:31.168533', NULL, 'Campo Belo', 11);
INSERT INTO endereco.cidade VALUES (1470, '2019-10-23 14:05:31.168533', NULL, 'Campo Florido', 11);
INSERT INTO endereco.cidade VALUES (1471, '2019-10-23 14:05:31.168533', NULL, 'Campos Altos', 11);
INSERT INTO endereco.cidade VALUES (1473, '2019-10-23 14:05:31.168533', NULL, 'Cana Verde', 11);
INSERT INTO endereco.cidade VALUES (1475, '2019-10-23 14:05:31.168533', NULL, 'CanÁpolis', 11);
INSERT INTO endereco.cidade VALUES (1476, '2019-10-23 14:05:31.168533', NULL, 'Candeias', 11);
INSERT INTO endereco.cidade VALUES (1478, '2019-10-23 14:05:31.168533', NULL, 'Caparaó', 11);
INSERT INTO endereco.cidade VALUES (1480, '2019-10-23 14:05:31.168533', NULL, 'Capelinha', 11);
INSERT INTO endereco.cidade VALUES (1482, '2019-10-23 14:05:31.168533', NULL, 'Capim Branco', 11);
INSERT INTO endereco.cidade VALUES (1484, '2019-10-23 14:05:31.168533', NULL, 'Capitão Andrade', 11);
INSERT INTO endereco.cidade VALUES (1486, '2019-10-23 14:05:31.168533', NULL, 'Capitólio', 11);
INSERT INTO endereco.cidade VALUES (1487, '2019-10-23 14:05:31.168533', NULL, 'Caputira', 11);
INSERT INTO endereco.cidade VALUES (1489, '2019-10-23 14:05:31.168533', NULL, 'Caranaíba', 11);
INSERT INTO endereco.cidade VALUES (1491, '2019-10-23 14:05:31.168533', NULL, 'Carangola', 11);
INSERT INTO endereco.cidade VALUES (1493, '2019-10-23 14:05:31.168533', NULL, 'Carbonita', 11);
INSERT INTO endereco.cidade VALUES (1494, '2019-10-23 14:05:31.168533', NULL, 'Careaçu', 11);
INSERT INTO endereco.cidade VALUES (1496, '2019-10-23 14:05:31.168533', NULL, 'Carmésia', 11);
INSERT INTO endereco.cidade VALUES (1498, '2019-10-23 14:05:31.168533', NULL, 'Carmo Da Mata', 11);
INSERT INTO endereco.cidade VALUES (1500, '2019-10-23 14:05:31.168533', NULL, 'Carmo Do Cajuru', 11);
INSERT INTO endereco.cidade VALUES (1501, '2019-10-23 14:05:31.168533', NULL, 'Carmo Do Paranaíba', 11);
INSERT INTO endereco.cidade VALUES (1503, '2019-10-23 14:05:31.168533', NULL, 'Carmópolis De Minas', 11);
INSERT INTO endereco.cidade VALUES (1504, '2019-10-23 14:05:31.168533', NULL, 'Carneirinho', 11);
INSERT INTO endereco.cidade VALUES (1506, '2019-10-23 14:05:31.168533', NULL, 'Carvalhópolis', 11);
INSERT INTO endereco.cidade VALUES (1508, '2019-10-23 14:05:31.168533', NULL, 'Casa Grande', 11);
INSERT INTO endereco.cidade VALUES (1510, '2019-10-23 14:05:31.168533', NULL, 'CÁssia', 11);
INSERT INTO endereco.cidade VALUES (1511, '2019-10-23 14:05:31.168533', NULL, 'Cataguases', 11);
INSERT INTO endereco.cidade VALUES (1514, '2019-10-23 14:05:31.168533', NULL, 'Catuji', 11);
INSERT INTO endereco.cidade VALUES (1515, '2019-10-23 14:05:31.168533', NULL, 'Catuti', 11);
INSERT INTO endereco.cidade VALUES (1517, '2019-10-23 14:05:31.168533', NULL, 'Cedro Do Abaeté', 11);
INSERT INTO endereco.cidade VALUES (1518, '2019-10-23 14:05:31.168533', NULL, 'Central De Minas', 11);
INSERT INTO endereco.cidade VALUES (1520, '2019-10-23 14:05:31.168533', NULL, 'ChÁcara', 11);
INSERT INTO endereco.cidade VALUES (1522, '2019-10-23 14:05:31.168533', NULL, 'Chapada Do Norte', 11);
INSERT INTO endereco.cidade VALUES (2865, '2019-10-23 14:05:31.168533', NULL, 'Itacuruba', 16);
INSERT INTO endereco.cidade VALUES (1525, '2019-10-23 14:05:31.168533', NULL, 'Cipotânea', 11);
INSERT INTO endereco.cidade VALUES (1527, '2019-10-23 14:05:31.168533', NULL, 'Claro Dos Poções', 11);
INSERT INTO endereco.cidade VALUES (1529, '2019-10-23 14:05:31.168533', NULL, 'Coimbra', 11);
INSERT INTO endereco.cidade VALUES (1531, '2019-10-23 14:05:31.168533', NULL, 'Comendador Gomes', 11);
INSERT INTO endereco.cidade VALUES (1533, '2019-10-23 14:05:31.168533', NULL, 'Conceição Da Aparecida', 11);
INSERT INTO endereco.cidade VALUES (1535, '2019-10-23 14:05:31.168533', NULL, 'Conceição Das Alagoas', 11);
INSERT INTO endereco.cidade VALUES (1536, '2019-10-23 14:05:31.168533', NULL, 'Conceição Das Pedras', 11);
INSERT INTO endereco.cidade VALUES (1538, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Mato Dentro', 11);
INSERT INTO endereco.cidade VALUES (1539, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do ParÁ', 11);
INSERT INTO endereco.cidade VALUES (1541, '2019-10-23 14:05:31.168533', NULL, 'Conceição Dos Ouros', 11);
INSERT INTO endereco.cidade VALUES (1543, '2019-10-23 14:05:31.168533', NULL, 'Confins', 11);
INSERT INTO endereco.cidade VALUES (1544, '2019-10-23 14:05:31.168533', NULL, 'Congonhal', 11);
INSERT INTO endereco.cidade VALUES (1546, '2019-10-23 14:05:31.168533', NULL, 'Congonhas Do Norte', 11);
INSERT INTO endereco.cidade VALUES (1548, '2019-10-23 14:05:31.168533', NULL, 'Conselheiro Lafaiete', 11);
INSERT INTO endereco.cidade VALUES (1550, '2019-10-23 14:05:31.168533', NULL, 'Consolação', 11);
INSERT INTO endereco.cidade VALUES (1551, '2019-10-23 14:05:31.168533', NULL, 'Contagem', 11);
INSERT INTO endereco.cidade VALUES (1553, '2019-10-23 14:05:31.168533', NULL, 'Coração De Jesus', 11);
INSERT INTO endereco.cidade VALUES (1555, '2019-10-23 14:05:31.168533', NULL, 'Cordislândia', 11);
INSERT INTO endereco.cidade VALUES (1556, '2019-10-23 14:05:31.168533', NULL, 'Corinto', 11);
INSERT INTO endereco.cidade VALUES (1558, '2019-10-23 14:05:31.168533', NULL, 'Coromandel', 11);
INSERT INTO endereco.cidade VALUES (1560, '2019-10-23 14:05:31.168533', NULL, 'Coronel Murta', 11);
INSERT INTO endereco.cidade VALUES (1562, '2019-10-23 14:05:31.168533', NULL, 'Coronel Xavier Chaves', 11);
INSERT INTO endereco.cidade VALUES (1563, '2019-10-23 14:05:31.168533', NULL, 'Córrego Danta', 11);
INSERT INTO endereco.cidade VALUES (1565, '2019-10-23 14:05:31.168533', NULL, 'Córrego Fundo', 11);
INSERT INTO endereco.cidade VALUES (1567, '2019-10-23 14:05:31.168533', NULL, 'Couto De Magalhães De Minas', 11);
INSERT INTO endereco.cidade VALUES (1568, '2019-10-23 14:05:31.168533', NULL, 'Crisólita', 11);
INSERT INTO endereco.cidade VALUES (1570, '2019-10-23 14:05:31.168533', NULL, 'CristÁlia', 11);
INSERT INTO endereco.cidade VALUES (1572, '2019-10-23 14:05:31.168533', NULL, 'Cristina', 11);
INSERT INTO endereco.cidade VALUES (1574, '2019-10-23 14:05:31.168533', NULL, 'Cruzeiro Da Fortaleza', 11);
INSERT INTO endereco.cidade VALUES (1575, '2019-10-23 14:05:31.168533', NULL, 'Cruzília', 11);
INSERT INTO endereco.cidade VALUES (1577, '2019-10-23 14:05:31.168533', NULL, 'Curral De Dentro', 11);
INSERT INTO endereco.cidade VALUES (1579, '2019-10-23 14:05:31.168533', NULL, 'Datas', 11);
INSERT INTO endereco.cidade VALUES (1581, '2019-10-23 14:05:31.168533', NULL, 'Delfinópolis', 11);
INSERT INTO endereco.cidade VALUES (1582, '2019-10-23 14:05:31.168533', NULL, 'Delta', 11);
INSERT INTO endereco.cidade VALUES (1584, '2019-10-23 14:05:31.168533', NULL, 'Desterro De Entre Rios', 11);
INSERT INTO endereco.cidade VALUES (1586, '2019-10-23 14:05:31.168533', NULL, 'Diamantina', 11);
INSERT INTO endereco.cidade VALUES (1588, '2019-10-23 14:05:31.168533', NULL, 'Dionísio', 11);
INSERT INTO endereco.cidade VALUES (1589, '2019-10-23 14:05:31.168533', NULL, 'Divinésia', 11);
INSERT INTO endereco.cidade VALUES (1591, '2019-10-23 14:05:31.168533', NULL, 'Divino Das Laranjeiras', 11);
INSERT INTO endereco.cidade VALUES (1593, '2019-10-23 14:05:31.168533', NULL, 'Divinópolis', 11);
INSERT INTO endereco.cidade VALUES (1594, '2019-10-23 14:05:31.168533', NULL, 'Divisa Alegre', 11);
INSERT INTO endereco.cidade VALUES (1596, '2019-10-23 14:05:31.168533', NULL, 'Divisópolis', 11);
INSERT INTO endereco.cidade VALUES (1597, '2019-10-23 14:05:31.168533', NULL, 'Dom Bosco', 11);
INSERT INTO endereco.cidade VALUES (1599, '2019-10-23 14:05:31.168533', NULL, 'Dom Joaquim', 11);
INSERT INTO endereco.cidade VALUES (1601, '2019-10-23 14:05:31.168533', NULL, 'Dom Viçoso', 11);
INSERT INTO endereco.cidade VALUES (1603, '2019-10-23 14:05:31.168533', NULL, 'Dores De Campos', 11);
INSERT INTO endereco.cidade VALUES (1604, '2019-10-23 14:05:31.168533', NULL, 'Dores De Guanhães', 11);
INSERT INTO endereco.cidade VALUES (1606, '2019-10-23 14:05:31.168533', NULL, 'Dores Do Turvo', 11);
INSERT INTO endereco.cidade VALUES (1607, '2019-10-23 14:05:31.168533', NULL, 'Doresópolis', 11);
INSERT INTO endereco.cidade VALUES (1609, '2019-10-23 14:05:31.168533', NULL, 'Durandé', 11);
INSERT INTO endereco.cidade VALUES (1611, '2019-10-23 14:05:31.168533', NULL, 'Engenheiro Caldas', 11);
INSERT INTO endereco.cidade VALUES (1613, '2019-10-23 14:05:31.168533', NULL, 'Entre Folhas', 11);
INSERT INTO endereco.cidade VALUES (1614, '2019-10-23 14:05:31.168533', NULL, 'Entre Rios De Minas', 11);
INSERT INTO endereco.cidade VALUES (1616, '2019-10-23 14:05:31.168533', NULL, 'Esmeraldas', 11);
INSERT INTO endereco.cidade VALUES (1618, '2019-10-23 14:05:31.168533', NULL, 'Espinosa', 11);
INSERT INTO endereco.cidade VALUES (1620, '2019-10-23 14:05:31.168533', NULL, 'Estiva', 11);
INSERT INTO endereco.cidade VALUES (1621, '2019-10-23 14:05:31.168533', NULL, 'Estrela Dalva', 11);
INSERT INTO endereco.cidade VALUES (1623, '2019-10-23 14:05:31.168533', NULL, 'Estrela Do Sul', 11);
INSERT INTO endereco.cidade VALUES (1625, '2019-10-23 14:05:31.168533', NULL, 'Ewbank Da Câmara', 11);
INSERT INTO endereco.cidade VALUES (1626, '2019-10-23 14:05:31.168533', NULL, 'Extrema', 11);
INSERT INTO endereco.cidade VALUES (1628, '2019-10-23 14:05:31.168533', NULL, 'Faria Lemos', 11);
INSERT INTO endereco.cidade VALUES (1630, '2019-10-23 14:05:31.168533', NULL, 'Felisburgo', 11);
INSERT INTO endereco.cidade VALUES (1631, '2019-10-23 14:05:31.168533', NULL, 'Felixlândia', 11);
INSERT INTO endereco.cidade VALUES (1633, '2019-10-23 14:05:31.168533', NULL, 'Ferros', 11);
INSERT INTO endereco.cidade VALUES (1635, '2019-10-23 14:05:31.168533', NULL, 'Florestal', 11);
INSERT INTO endereco.cidade VALUES (1637, '2019-10-23 14:05:31.168533', NULL, 'Formoso', 11);
INSERT INTO endereco.cidade VALUES (1638, '2019-10-23 14:05:31.168533', NULL, 'Fortaleza De Minas', 11);
INSERT INTO endereco.cidade VALUES (1640, '2019-10-23 14:05:31.168533', NULL, 'Francisco Badaró', 11);
INSERT INTO endereco.cidade VALUES (1642, '2019-10-23 14:05:31.168533', NULL, 'Francisco SÁ', 11);
INSERT INTO endereco.cidade VALUES (1644, '2019-10-23 14:05:31.168533', NULL, 'Frei Gaspar', 11);
INSERT INTO endereco.cidade VALUES (1645, '2019-10-23 14:05:31.168533', NULL, 'Frei Inocêncio', 11);
INSERT INTO endereco.cidade VALUES (2867, '2019-10-23 14:05:31.168533', NULL, 'Itambé', 16);
INSERT INTO endereco.cidade VALUES (1649, '2019-10-23 14:05:31.168533', NULL, 'Fruta De Leite', 11);
INSERT INTO endereco.cidade VALUES (1650, '2019-10-23 14:05:31.168533', NULL, 'Frutal', 11);
INSERT INTO endereco.cidade VALUES (1652, '2019-10-23 14:05:31.168533', NULL, 'Galiléia', 11);
INSERT INTO endereco.cidade VALUES (1654, '2019-10-23 14:05:31.168533', NULL, 'Glaucilândia', 11);
INSERT INTO endereco.cidade VALUES (1655, '2019-10-23 14:05:31.168533', NULL, 'Goiabeira', 11);
INSERT INTO endereco.cidade VALUES (1657, '2019-10-23 14:05:31.168533', NULL, 'Gonçalves', 11);
INSERT INTO endereco.cidade VALUES (1659, '2019-10-23 14:05:31.168533', NULL, 'Gouveia', 11);
INSERT INTO endereco.cidade VALUES (1661, '2019-10-23 14:05:31.168533', NULL, 'Grão Mogol', 11);
INSERT INTO endereco.cidade VALUES (1662, '2019-10-23 14:05:31.168533', NULL, 'Grupiara', 11);
INSERT INTO endereco.cidade VALUES (1664, '2019-10-23 14:05:31.168533', NULL, 'Guapé', 11);
INSERT INTO endereco.cidade VALUES (1666, '2019-10-23 14:05:31.168533', NULL, 'Guaraciama', 11);
INSERT INTO endereco.cidade VALUES (1668, '2019-10-23 14:05:31.168533', NULL, 'Guarani', 11);
INSERT INTO endereco.cidade VALUES (1669, '2019-10-23 14:05:31.168533', NULL, 'GuararÁ', 11);
INSERT INTO endereco.cidade VALUES (1671, '2019-10-23 14:05:31.168533', NULL, 'Guaxupé', 11);
INSERT INTO endereco.cidade VALUES (1673, '2019-10-23 14:05:31.168533', NULL, 'Guimarânia', 11);
INSERT INTO endereco.cidade VALUES (1675, '2019-10-23 14:05:31.168533', NULL, 'Gurinhatã', 11);
INSERT INTO endereco.cidade VALUES (1677, '2019-10-23 14:05:31.168533', NULL, 'Iapu', 11);
INSERT INTO endereco.cidade VALUES (1678, '2019-10-23 14:05:31.168533', NULL, 'Ibertioga', 11);
INSERT INTO endereco.cidade VALUES (1680, '2019-10-23 14:05:31.168533', NULL, 'Ibiaí', 11);
INSERT INTO endereco.cidade VALUES (1682, '2019-10-23 14:05:31.168533', NULL, 'Ibiraci', 11);
INSERT INTO endereco.cidade VALUES (1684, '2019-10-23 14:05:31.168533', NULL, 'Ibitiúra De Minas', 11);
INSERT INTO endereco.cidade VALUES (1686, '2019-10-23 14:05:31.168533', NULL, 'Icaraí De Minas', 11);
INSERT INTO endereco.cidade VALUES (1687, '2019-10-23 14:05:31.168533', NULL, 'Igarapé', 11);
INSERT INTO endereco.cidade VALUES (1689, '2019-10-23 14:05:31.168533', NULL, 'Iguatama', 11);
INSERT INTO endereco.cidade VALUES (1691, '2019-10-23 14:05:31.168533', NULL, 'Ilicínea', 11);
INSERT INTO endereco.cidade VALUES (1693, '2019-10-23 14:05:31.168533', NULL, 'Inconfidentes', 11);
INSERT INTO endereco.cidade VALUES (1694, '2019-10-23 14:05:31.168533', NULL, 'Indaiabira', 11);
INSERT INTO endereco.cidade VALUES (1696, '2019-10-23 14:05:31.168533', NULL, 'Ingaí', 11);
INSERT INTO endereco.cidade VALUES (1698, '2019-10-23 14:05:31.168533', NULL, 'Inhaúma', 11);
INSERT INTO endereco.cidade VALUES (1700, '2019-10-23 14:05:31.168533', NULL, 'Ipaba', 11);
INSERT INTO endereco.cidade VALUES (1701, '2019-10-23 14:05:31.168533', NULL, 'Ipanema', 11);
INSERT INTO endereco.cidade VALUES (1703, '2019-10-23 14:05:31.168533', NULL, 'Ipiaçu', 11);
INSERT INTO endereco.cidade VALUES (1705, '2019-10-23 14:05:31.168533', NULL, 'Iraí De Minas', 11);
INSERT INTO endereco.cidade VALUES (1707, '2019-10-23 14:05:31.168533', NULL, 'Itabirinha', 11);
INSERT INTO endereco.cidade VALUES (1708, '2019-10-23 14:05:31.168533', NULL, 'Itabirito', 11);
INSERT INTO endereco.cidade VALUES (1710, '2019-10-23 14:05:31.168533', NULL, 'Itacarambi', 11);
INSERT INTO endereco.cidade VALUES (1712, '2019-10-23 14:05:31.168533', NULL, 'Itaipé', 11);
INSERT INTO endereco.cidade VALUES (1714, '2019-10-23 14:05:31.168533', NULL, 'Itamarandiba', 11);
INSERT INTO endereco.cidade VALUES (1716, '2019-10-23 14:05:31.168533', NULL, 'Itambacuri', 11);
INSERT INTO endereco.cidade VALUES (1718, '2019-10-23 14:05:31.168533', NULL, 'Itamogi', 11);
INSERT INTO endereco.cidade VALUES (1719, '2019-10-23 14:05:31.168533', NULL, 'Itamonte', 11);
INSERT INTO endereco.cidade VALUES (1721, '2019-10-23 14:05:31.168533', NULL, 'Itanhomi', 11);
INSERT INTO endereco.cidade VALUES (1722, '2019-10-23 14:05:31.168533', NULL, 'Itaobim', 11);
INSERT INTO endereco.cidade VALUES (1724, '2019-10-23 14:05:31.168533', NULL, 'Itapecerica', 11);
INSERT INTO endereco.cidade VALUES (1726, '2019-10-23 14:05:31.168533', NULL, 'Itatiaiuçu', 11);
INSERT INTO endereco.cidade VALUES (1728, '2019-10-23 14:05:31.168533', NULL, 'Itaúna', 11);
INSERT INTO endereco.cidade VALUES (1729, '2019-10-23 14:05:31.168533', NULL, 'Itaverava', 11);
INSERT INTO endereco.cidade VALUES (1731, '2019-10-23 14:05:31.168533', NULL, 'Itueta', 11);
INSERT INTO endereco.cidade VALUES (1733, '2019-10-23 14:05:31.168533', NULL, 'Itumirim', 11);
INSERT INTO endereco.cidade VALUES (1735, '2019-10-23 14:05:31.168533', NULL, 'Itutinga', 11);
INSERT INTO endereco.cidade VALUES (1737, '2019-10-23 14:05:31.168533', NULL, 'Jacinto', 11);
INSERT INTO endereco.cidade VALUES (1738, '2019-10-23 14:05:31.168533', NULL, 'Jacuí', 11);
INSERT INTO endereco.cidade VALUES (1740, '2019-10-23 14:05:31.168533', NULL, 'Jaguaraçu', 11);
INSERT INTO endereco.cidade VALUES (1742, '2019-10-23 14:05:31.168533', NULL, 'Jampruca', 11);
INSERT INTO endereco.cidade VALUES (1743, '2019-10-23 14:05:31.168533', NULL, 'Janaúba', 11);
INSERT INTO endereco.cidade VALUES (1745, '2019-10-23 14:05:31.168533', NULL, 'Japaraíba', 11);
INSERT INTO endereco.cidade VALUES (1747, '2019-10-23 14:05:31.168533', NULL, 'Jeceaba', 11);
INSERT INTO endereco.cidade VALUES (1749, '2019-10-23 14:05:31.168533', NULL, 'Jequeri', 11);
INSERT INTO endereco.cidade VALUES (1750, '2019-10-23 14:05:31.168533', NULL, 'Jequitaí', 11);
INSERT INTO endereco.cidade VALUES (1753, '2019-10-23 14:05:31.168533', NULL, 'Jesuânia', 11);
INSERT INTO endereco.cidade VALUES (1754, '2019-10-23 14:05:31.168533', NULL, 'Joaíma', 11);
INSERT INTO endereco.cidade VALUES (1756, '2019-10-23 14:05:31.168533', NULL, 'João Monlevade', 11);
INSERT INTO endereco.cidade VALUES (1758, '2019-10-23 14:05:31.168533', NULL, 'Joaquim Felício', 11);
INSERT INTO endereco.cidade VALUES (1759, '2019-10-23 14:05:31.168533', NULL, 'Jordânia', 11);
INSERT INTO endereco.cidade VALUES (1761, '2019-10-23 14:05:31.168533', NULL, 'José Raydan', 11);
INSERT INTO endereco.cidade VALUES (1763, '2019-10-23 14:05:31.168533', NULL, 'Juatuba', 11);
INSERT INTO endereco.cidade VALUES (1764, '2019-10-23 14:05:31.168533', NULL, 'Juiz De Fora', 11);
INSERT INTO endereco.cidade VALUES (1766, '2019-10-23 14:05:31.168533', NULL, 'Juruaia', 11);
INSERT INTO endereco.cidade VALUES (1768, '2019-10-23 14:05:31.168533', NULL, 'Ladainha', 11);
INSERT INTO endereco.cidade VALUES (1770, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Da Prata', 11);
INSERT INTO endereco.cidade VALUES (1771, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Dos Patos', 11);
INSERT INTO endereco.cidade VALUES (1773, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Formosa', 11);
INSERT INTO endereco.cidade VALUES (1775, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Santa', 11);
INSERT INTO endereco.cidade VALUES (1776, '2019-10-23 14:05:31.168533', NULL, 'Lajinha', 11);
INSERT INTO endereco.cidade VALUES (2870, '2019-10-23 14:05:31.168533', NULL, 'Itaquitinga', 16);
INSERT INTO endereco.cidade VALUES (1779, '2019-10-23 14:05:31.168533', NULL, 'Laranjal', 11);
INSERT INTO endereco.cidade VALUES (1781, '2019-10-23 14:05:31.168533', NULL, 'Lavras', 11);
INSERT INTO endereco.cidade VALUES (1783, '2019-10-23 14:05:31.168533', NULL, 'Leme Do Prado', 11);
INSERT INTO endereco.cidade VALUES (1785, '2019-10-23 14:05:31.168533', NULL, 'Liberdade', 11);
INSERT INTO endereco.cidade VALUES (1786, '2019-10-23 14:05:31.168533', NULL, 'Lima Duarte', 11);
INSERT INTO endereco.cidade VALUES (1788, '2019-10-23 14:05:31.168533', NULL, 'Lontra', 11);
INSERT INTO endereco.cidade VALUES (1790, '2019-10-23 14:05:31.168533', NULL, 'Luislândia', 11);
INSERT INTO endereco.cidade VALUES (1792, '2019-10-23 14:05:31.168533', NULL, 'Luz', 11);
INSERT INTO endereco.cidade VALUES (1793, '2019-10-23 14:05:31.168533', NULL, 'Machacalis', 11);
INSERT INTO endereco.cidade VALUES (1795, '2019-10-23 14:05:31.168533', NULL, 'Madre De Deus De Minas', 11);
INSERT INTO endereco.cidade VALUES (1797, '2019-10-23 14:05:31.168533', NULL, 'Mamonas', 11);
INSERT INTO endereco.cidade VALUES (1799, '2019-10-23 14:05:31.168533', NULL, 'Manhuaçu', 11);
INSERT INTO endereco.cidade VALUES (1800, '2019-10-23 14:05:31.168533', NULL, 'Manhumirim', 11);
INSERT INTO endereco.cidade VALUES (1802, '2019-10-23 14:05:31.168533', NULL, 'Mar De Espanha', 11);
INSERT INTO endereco.cidade VALUES (1804, '2019-10-23 14:05:31.168533', NULL, 'Maria Da Fé', 11);
INSERT INTO endereco.cidade VALUES (1806, '2019-10-23 14:05:31.168533', NULL, 'Marilac', 11);
INSERT INTO endereco.cidade VALUES (1808, '2019-10-23 14:05:31.168533', NULL, 'MaripÁ De Minas', 11);
INSERT INTO endereco.cidade VALUES (1809, '2019-10-23 14:05:31.168533', NULL, 'Marliéria', 11);
INSERT INTO endereco.cidade VALUES (1811, '2019-10-23 14:05:31.168533', NULL, 'Martinho Campos', 11);
INSERT INTO endereco.cidade VALUES (1813, '2019-10-23 14:05:31.168533', NULL, 'Mata Verde', 11);
INSERT INTO endereco.cidade VALUES (1815, '2019-10-23 14:05:31.168533', NULL, 'Mateus Leme', 11);
INSERT INTO endereco.cidade VALUES (1816, '2019-10-23 14:05:31.168533', NULL, 'Mathias Lobato', 11);
INSERT INTO endereco.cidade VALUES (1818, '2019-10-23 14:05:31.168533', NULL, 'Matias Cardoso', 11);
INSERT INTO endereco.cidade VALUES (1820, '2019-10-23 14:05:31.168533', NULL, 'Mato Verde', 11);
INSERT INTO endereco.cidade VALUES (1821, '2019-10-23 14:05:31.168533', NULL, 'Matozinhos', 11);
INSERT INTO endereco.cidade VALUES (1823, '2019-10-23 14:05:31.168533', NULL, 'Medeiros', 11);
INSERT INTO endereco.cidade VALUES (1825, '2019-10-23 14:05:31.168533', NULL, 'Mendes Pimentel', 11);
INSERT INTO endereco.cidade VALUES (1827, '2019-10-23 14:05:31.168533', NULL, 'Mesquita', 11);
INSERT INTO endereco.cidade VALUES (1828, '2019-10-23 14:05:31.168533', NULL, 'Minas Novas', 11);
INSERT INTO endereco.cidade VALUES (1830, '2019-10-23 14:05:31.168533', NULL, 'Mirabela', 11);
INSERT INTO endereco.cidade VALUES (1832, '2019-10-23 14:05:31.168533', NULL, 'Miraí', 11);
INSERT INTO endereco.cidade VALUES (1834, '2019-10-23 14:05:31.168533', NULL, 'Moeda', 11);
INSERT INTO endereco.cidade VALUES (1836, '2019-10-23 14:05:31.168533', NULL, 'Monjolos', 11);
INSERT INTO endereco.cidade VALUES (1838, '2019-10-23 14:05:31.168533', NULL, 'Montalvânia', 11);
INSERT INTO endereco.cidade VALUES (1839, '2019-10-23 14:05:31.168533', NULL, 'Monte Alegre De Minas', 11);
INSERT INTO endereco.cidade VALUES (1841, '2019-10-23 14:05:31.168533', NULL, 'Monte Belo', 11);
INSERT INTO endereco.cidade VALUES (1843, '2019-10-23 14:05:31.168533', NULL, 'Monte Formoso', 11);
INSERT INTO endereco.cidade VALUES (1845, '2019-10-23 14:05:31.168533', NULL, 'Monte Sião', 11);
INSERT INTO endereco.cidade VALUES (1846, '2019-10-23 14:05:31.168533', NULL, 'Montes Claros', 11);
INSERT INTO endereco.cidade VALUES (1848, '2019-10-23 14:05:31.168533', NULL, 'Morada Nova De Minas', 11);
INSERT INTO endereco.cidade VALUES (1850, '2019-10-23 14:05:31.168533', NULL, 'Morro Do Pilar', 11);
INSERT INTO endereco.cidade VALUES (1851, '2019-10-23 14:05:31.168533', NULL, 'Munhoz', 11);
INSERT INTO endereco.cidade VALUES (1853, '2019-10-23 14:05:31.168533', NULL, 'Mutum', 11);
INSERT INTO endereco.cidade VALUES (1855, '2019-10-23 14:05:31.168533', NULL, 'Nacip Raydan', 11);
INSERT INTO endereco.cidade VALUES (1856, '2019-10-23 14:05:31.168533', NULL, 'Nanuque', 11);
INSERT INTO endereco.cidade VALUES (1858, '2019-10-23 14:05:31.168533', NULL, 'Natalândia', 11);
INSERT INTO endereco.cidade VALUES (1860, '2019-10-23 14:05:31.168533', NULL, 'Nazareno', 11);
INSERT INTO endereco.cidade VALUES (1862, '2019-10-23 14:05:31.168533', NULL, 'Ninheira', 11);
INSERT INTO endereco.cidade VALUES (1864, '2019-10-23 14:05:31.168533', NULL, 'Nova Era', 11);
INSERT INTO endereco.cidade VALUES (1865, '2019-10-23 14:05:31.168533', NULL, 'Nova Lima', 11);
INSERT INTO endereco.cidade VALUES (1867, '2019-10-23 14:05:31.168533', NULL, 'Nova Ponte', 11);
INSERT INTO endereco.cidade VALUES (1869, '2019-10-23 14:05:31.168533', NULL, 'Nova Resende', 11);
INSERT INTO endereco.cidade VALUES (1871, '2019-10-23 14:05:31.168533', NULL, 'Nova União', 11);
INSERT INTO endereco.cidade VALUES (1872, '2019-10-23 14:05:31.168533', NULL, 'Novo Cruzeiro', 11);
INSERT INTO endereco.cidade VALUES (1874, '2019-10-23 14:05:31.168533', NULL, 'Novorizonte', 11);
INSERT INTO endereco.cidade VALUES (1876, '2019-10-23 14:05:31.168533', NULL, 'Olhos D`Água', 11);
INSERT INTO endereco.cidade VALUES (1878, '2019-10-23 14:05:31.168533', NULL, 'Oliveira', 11);
INSERT INTO endereco.cidade VALUES (1879, '2019-10-23 14:05:31.168533', NULL, 'Oliveira Fortes', 11);
INSERT INTO endereco.cidade VALUES (1881, '2019-10-23 14:05:31.168533', NULL, 'Oratórios', 11);
INSERT INTO endereco.cidade VALUES (1883, '2019-10-23 14:05:31.168533', NULL, 'Ouro Branco', 11);
INSERT INTO endereco.cidade VALUES (1884, '2019-10-23 14:05:31.168533', NULL, 'Ouro Fino', 11);
INSERT INTO endereco.cidade VALUES (1886, '2019-10-23 14:05:31.168533', NULL, 'Ouro Verde De Minas', 11);
INSERT INTO endereco.cidade VALUES (1888, '2019-10-23 14:05:31.168533', NULL, 'Padre Paraíso', 11);
INSERT INTO endereco.cidade VALUES (1890, '2019-10-23 14:05:31.168533', NULL, 'Paineiras', 11);
INSERT INTO endereco.cidade VALUES (1891, '2019-10-23 14:05:31.168533', NULL, 'Pains', 11);
INSERT INTO endereco.cidade VALUES (1893, '2019-10-23 14:05:31.168533', NULL, 'Palma', 11);
INSERT INTO endereco.cidade VALUES (1895, '2019-10-23 14:05:31.168533', NULL, 'Papagaios', 11);
INSERT INTO endereco.cidade VALUES (1897, '2019-10-23 14:05:31.168533', NULL, 'Paracatu', 11);
INSERT INTO endereco.cidade VALUES (1898, '2019-10-23 14:05:31.168533', NULL, 'Paraguaçu', 11);
INSERT INTO endereco.cidade VALUES (1900, '2019-10-23 14:05:31.168533', NULL, 'Paraopeba', 11);
INSERT INTO endereco.cidade VALUES (1902, '2019-10-23 14:05:31.168533', NULL, 'Passa Tempo', 11);
INSERT INTO endereco.cidade VALUES (1904, '2019-10-23 14:05:31.168533', NULL, 'Passabém', 11);
INSERT INTO endereco.cidade VALUES (1905, '2019-10-23 14:05:31.168533', NULL, 'Passos', 11);
INSERT INTO endereco.cidade VALUES (2728, '2019-10-23 14:05:31.168533', NULL, 'Santa Helena', 15);
INSERT INTO endereco.cidade VALUES (1908, '2019-10-23 14:05:31.168533', NULL, 'Patrocínio', 11);
INSERT INTO endereco.cidade VALUES (1910, '2019-10-23 14:05:31.168533', NULL, 'Paula Cândido', 11);
INSERT INTO endereco.cidade VALUES (1912, '2019-10-23 14:05:31.168533', NULL, 'Pavão', 11);
INSERT INTO endereco.cidade VALUES (1913, '2019-10-23 14:05:31.168533', NULL, 'Peçanha', 11);
INSERT INTO endereco.cidade VALUES (1915, '2019-10-23 14:05:31.168533', NULL, 'Pedra Bonita', 11);
INSERT INTO endereco.cidade VALUES (1917, '2019-10-23 14:05:31.168533', NULL, 'Pedra Do IndaiÁ', 11);
INSERT INTO endereco.cidade VALUES (1919, '2019-10-23 14:05:31.168533', NULL, 'Pedralva', 11);
INSERT INTO endereco.cidade VALUES (1920, '2019-10-23 14:05:31.168533', NULL, 'Pedras De Maria Da Cruz', 11);
INSERT INTO endereco.cidade VALUES (1922, '2019-10-23 14:05:31.168533', NULL, 'Pedro Leopoldo', 11);
INSERT INTO endereco.cidade VALUES (1924, '2019-10-23 14:05:31.168533', NULL, 'Pequeri', 11);
INSERT INTO endereco.cidade VALUES (1925, '2019-10-23 14:05:31.168533', NULL, 'Pequi', 11);
INSERT INTO endereco.cidade VALUES (1927, '2019-10-23 14:05:31.168533', NULL, 'Perdizes', 11);
INSERT INTO endereco.cidade VALUES (1929, '2019-10-23 14:05:31.168533', NULL, 'Periquito', 11);
INSERT INTO endereco.cidade VALUES (1931, '2019-10-23 14:05:31.168533', NULL, 'Piau', 11);
INSERT INTO endereco.cidade VALUES (1933, '2019-10-23 14:05:31.168533', NULL, 'Piedade De Ponte Nova', 11);
INSERT INTO endereco.cidade VALUES (1934, '2019-10-23 14:05:31.168533', NULL, 'Piedade Do Rio Grande', 11);
INSERT INTO endereco.cidade VALUES (1936, '2019-10-23 14:05:31.168533', NULL, 'Pimenta', 11);
INSERT INTO endereco.cidade VALUES (1938, '2019-10-23 14:05:31.168533', NULL, 'Pintópolis', 11);
INSERT INTO endereco.cidade VALUES (1939, '2019-10-23 14:05:31.168533', NULL, 'Piracema', 11);
INSERT INTO endereco.cidade VALUES (1941, '2019-10-23 14:05:31.168533', NULL, 'Piranga', 11);
INSERT INTO endereco.cidade VALUES (1943, '2019-10-23 14:05:31.168533', NULL, 'Piranguinho', 11);
INSERT INTO endereco.cidade VALUES (1944, '2019-10-23 14:05:31.168533', NULL, 'Pirapetinga', 11);
INSERT INTO endereco.cidade VALUES (1946, '2019-10-23 14:05:31.168533', NULL, 'Piraúba', 11);
INSERT INTO endereco.cidade VALUES (1948, '2019-10-23 14:05:31.168533', NULL, 'Piumhi', 11);
INSERT INTO endereco.cidade VALUES (1950, '2019-10-23 14:05:31.168533', NULL, 'Poço Fundo', 11);
INSERT INTO endereco.cidade VALUES (1952, '2019-10-23 14:05:31.168533', NULL, 'Pocrane', 11);
INSERT INTO endereco.cidade VALUES (1953, '2019-10-23 14:05:31.168533', NULL, 'Pompéu', 11);
INSERT INTO endereco.cidade VALUES (1955, '2019-10-23 14:05:31.168533', NULL, 'Ponto Chique', 11);
INSERT INTO endereco.cidade VALUES (1957, '2019-10-23 14:05:31.168533', NULL, 'Porteirinha', 11);
INSERT INTO endereco.cidade VALUES (1958, '2019-10-23 14:05:31.168533', NULL, 'Porto Firme', 11);
INSERT INTO endereco.cidade VALUES (1960, '2019-10-23 14:05:31.168533', NULL, 'Pouso Alegre', 11);
INSERT INTO endereco.cidade VALUES (1962, '2019-10-23 14:05:31.168533', NULL, 'Prados', 11);
INSERT INTO endereco.cidade VALUES (1964, '2019-10-23 14:05:31.168533', NULL, 'PratÁpolis', 11);
INSERT INTO endereco.cidade VALUES (1966, '2019-10-23 14:05:31.168533', NULL, 'Presidente Bernardes', 11);
INSERT INTO endereco.cidade VALUES (1968, '2019-10-23 14:05:31.168533', NULL, 'Presidente Kubitschek', 11);
INSERT INTO endereco.cidade VALUES (1969, '2019-10-23 14:05:31.168533', NULL, 'Presidente OlegÁrio', 11);
INSERT INTO endereco.cidade VALUES (1971, '2019-10-23 14:05:31.168533', NULL, 'Quartel Geral', 11);
INSERT INTO endereco.cidade VALUES (1972, '2019-10-23 14:05:31.168533', NULL, 'Queluzito', 11);
INSERT INTO endereco.cidade VALUES (1974, '2019-10-23 14:05:31.168533', NULL, 'Raul Soares', 11);
INSERT INTO endereco.cidade VALUES (1976, '2019-10-23 14:05:31.168533', NULL, 'Reduto', 11);
INSERT INTO endereco.cidade VALUES (1978, '2019-10-23 14:05:31.168533', NULL, 'Resplendor', 11);
INSERT INTO endereco.cidade VALUES (1979, '2019-10-23 14:05:31.168533', NULL, 'Ressaquinha', 11);
INSERT INTO endereco.cidade VALUES (1981, '2019-10-23 14:05:31.168533', NULL, 'Riacho Dos Machados', 11);
INSERT INTO endereco.cidade VALUES (1983, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Vermelho', 11);
INSERT INTO endereco.cidade VALUES (1985, '2019-10-23 14:05:31.168533', NULL, 'Rio Casca', 11);
INSERT INTO endereco.cidade VALUES (1986, '2019-10-23 14:05:31.168533', NULL, 'Rio Do Prado', 11);
INSERT INTO endereco.cidade VALUES (1988, '2019-10-23 14:05:31.168533', NULL, 'Rio Espera', 11);
INSERT INTO endereco.cidade VALUES (1990, '2019-10-23 14:05:31.168533', NULL, 'Rio Novo', 11);
INSERT INTO endereco.cidade VALUES (1992, '2019-10-23 14:05:31.168533', NULL, 'Rio Pardo De Minas', 11);
INSERT INTO endereco.cidade VALUES (1993, '2019-10-23 14:05:31.168533', NULL, 'Rio Piracicaba', 11);
INSERT INTO endereco.cidade VALUES (1995, '2019-10-23 14:05:31.168533', NULL, 'Rio Preto', 11);
INSERT INTO endereco.cidade VALUES (1997, '2019-10-23 14:05:31.168533', NULL, 'RitÁpolis', 11);
INSERT INTO endereco.cidade VALUES (1999, '2019-10-23 14:05:31.168533', NULL, 'Rodeiro', 11);
INSERT INTO endereco.cidade VALUES (2000, '2019-10-23 14:05:31.168533', NULL, 'Romaria', 11);
INSERT INTO endereco.cidade VALUES (2002, '2019-10-23 14:05:31.168533', NULL, 'Rubelita', 11);
INSERT INTO endereco.cidade VALUES (2004, '2019-10-23 14:05:31.168533', NULL, 'SabarÁ', 11);
INSERT INTO endereco.cidade VALUES (2006, '2019-10-23 14:05:31.168533', NULL, 'Sacramento', 11);
INSERT INTO endereco.cidade VALUES (2007, '2019-10-23 14:05:31.168533', NULL, 'Salinas', 11);
INSERT INTO endereco.cidade VALUES (2009, '2019-10-23 14:05:31.168533', NULL, 'Santa BÁrbara', 11);
INSERT INTO endereco.cidade VALUES (2011, '2019-10-23 14:05:31.168533', NULL, 'Santa BÁrbara Do Monte Verde', 11);
INSERT INTO endereco.cidade VALUES (2012, '2019-10-23 14:05:31.168533', NULL, 'Santa BÁrbara Do Tugúrio', 11);
INSERT INTO endereco.cidade VALUES (2014, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz De Salinas', 11);
INSERT INTO endereco.cidade VALUES (2016, '2019-10-23 14:05:31.168533', NULL, 'Santa Efigênia De Minas', 11);
INSERT INTO endereco.cidade VALUES (2017, '2019-10-23 14:05:31.168533', NULL, 'Santa Fé De Minas', 11);
INSERT INTO endereco.cidade VALUES (2019, '2019-10-23 14:05:31.168533', NULL, 'Santa Juliana', 11);
INSERT INTO endereco.cidade VALUES (2020, '2019-10-23 14:05:31.168533', NULL, 'Santa Luzia', 11);
INSERT INTO endereco.cidade VALUES (2022, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria De Itabira', 11);
INSERT INTO endereco.cidade VALUES (2024, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Do Suaçuí', 11);
INSERT INTO endereco.cidade VALUES (2026, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita De Ibitipoca', 11);
INSERT INTO endereco.cidade VALUES (2027, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita De Jacutinga', 11);
INSERT INTO endereco.cidade VALUES (2029, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita Do Itueto', 11);
INSERT INTO endereco.cidade VALUES (2033, '2019-10-23 14:05:31.168533', NULL, 'Santana Da Vargem', 11);
INSERT INTO endereco.cidade VALUES (2035, '2019-10-23 14:05:31.168533', NULL, 'Santana De Pirapama', 11);
INSERT INTO endereco.cidade VALUES (2036, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Deserto', 11);
INSERT INTO endereco.cidade VALUES (2038, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Jacaré', 11);
INSERT INTO endereco.cidade VALUES (2040, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Paraíso', 11);
INSERT INTO endereco.cidade VALUES (2041, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Riacho', 11);
INSERT INTO endereco.cidade VALUES (2043, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Amparo', 11);
INSERT INTO endereco.cidade VALUES (2045, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Grama', 11);
INSERT INTO endereco.cidade VALUES (2046, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Itambé', 11);
INSERT INTO endereco.cidade VALUES (2048, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Monte', 11);
INSERT INTO endereco.cidade VALUES (2050, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Rio Abaixo', 11);
INSERT INTO endereco.cidade VALUES (2051, '2019-10-23 14:05:31.168533', NULL, 'Santo Hipólito', 11);
INSERT INTO endereco.cidade VALUES (2053, '2019-10-23 14:05:31.168533', NULL, 'São Bento Abade', 11);
INSERT INTO endereco.cidade VALUES (2055, '2019-10-23 14:05:31.168533', NULL, 'São Domingos Das Dores', 11);
INSERT INTO endereco.cidade VALUES (2056, '2019-10-23 14:05:31.168533', NULL, 'São Domingos Do Prata', 11);
INSERT INTO endereco.cidade VALUES (2058, '2019-10-23 14:05:31.168533', NULL, 'São Francisco', 11);
INSERT INTO endereco.cidade VALUES (2060, '2019-10-23 14:05:31.168533', NULL, 'São Francisco De Sales', 11);
INSERT INTO endereco.cidade VALUES (2062, '2019-10-23 14:05:31.168533', NULL, 'São Geraldo', 11);
INSERT INTO endereco.cidade VALUES (2063, '2019-10-23 14:05:31.168533', NULL, 'São Geraldo Da Piedade', 11);
INSERT INTO endereco.cidade VALUES (2065, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Do Abaeté', 11);
INSERT INTO endereco.cidade VALUES (2066, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Do ParÁ', 11);
INSERT INTO endereco.cidade VALUES (2068, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Do Rio Preto', 11);
INSERT INTO endereco.cidade VALUES (2070, '2019-10-23 14:05:31.168533', NULL, 'São Gotardo', 11);
INSERT INTO endereco.cidade VALUES (2071, '2019-10-23 14:05:31.168533', NULL, 'São João Batista Do Glória', 11);
INSERT INTO endereco.cidade VALUES (2073, '2019-10-23 14:05:31.168533', NULL, 'São João Da Mata', 11);
INSERT INTO endereco.cidade VALUES (2075, '2019-10-23 14:05:31.168533', NULL, 'São João Das Missões', 11);
INSERT INTO endereco.cidade VALUES (2076, '2019-10-23 14:05:31.168533', NULL, 'São João Del Rei', 11);
INSERT INTO endereco.cidade VALUES (2078, '2019-10-23 14:05:31.168533', NULL, 'São João Do Manteninha', 11);
INSERT INTO endereco.cidade VALUES (2080, '2019-10-23 14:05:31.168533', NULL, 'São João Do Pacuí', 11);
INSERT INTO endereco.cidade VALUES (2081, '2019-10-23 14:05:31.168533', NULL, 'São João Do Paraíso', 11);
INSERT INTO endereco.cidade VALUES (2083, '2019-10-23 14:05:31.168533', NULL, 'São João Nepomuceno', 11);
INSERT INTO endereco.cidade VALUES (2085, '2019-10-23 14:05:31.168533', NULL, 'São José Da Barra', 11);
INSERT INTO endereco.cidade VALUES (2086, '2019-10-23 14:05:31.168533', NULL, 'São José Da Lapa', 11);
INSERT INTO endereco.cidade VALUES (2088, '2019-10-23 14:05:31.168533', NULL, 'São José Da Varginha', 11);
INSERT INTO endereco.cidade VALUES (2090, '2019-10-23 14:05:31.168533', NULL, 'São José Do Divino', 11);
INSERT INTO endereco.cidade VALUES (2091, '2019-10-23 14:05:31.168533', NULL, 'São José Do Goiabal', 11);
INSERT INTO endereco.cidade VALUES (2093, '2019-10-23 14:05:31.168533', NULL, 'São José Do Mantimento', 11);
INSERT INTO endereco.cidade VALUES (2095, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Anta', 11);
INSERT INTO endereco.cidade VALUES (2096, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Da União', 11);
INSERT INTO endereco.cidade VALUES (2098, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Dos Ferros', 11);
INSERT INTO endereco.cidade VALUES (2099, '2019-10-23 14:05:31.168533', NULL, 'São Romão', 11);
INSERT INTO endereco.cidade VALUES (2101, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Da Bela Vista', 11);
INSERT INTO endereco.cidade VALUES (2103, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Anta', 11);
INSERT INTO endereco.cidade VALUES (2104, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Maranhão', 11);
INSERT INTO endereco.cidade VALUES (2106, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Paraíso', 11);
INSERT INTO endereco.cidade VALUES (2108, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Rio Verde', 11);
INSERT INTO endereco.cidade VALUES (2109, '2019-10-23 14:05:31.168533', NULL, 'São Thomé Das Letras', 11);
INSERT INTO endereco.cidade VALUES (2110, '2019-10-23 14:05:31.168533', NULL, 'São Tiago', 11);
INSERT INTO endereco.cidade VALUES (2112, '2019-10-23 14:05:31.168533', NULL, 'São Vicente De Minas', 11);
INSERT INTO endereco.cidade VALUES (2114, '2019-10-23 14:05:31.168533', NULL, 'SardoÁ', 11);
INSERT INTO endereco.cidade VALUES (2115, '2019-10-23 14:05:31.168533', NULL, 'Sarzedo', 11);
INSERT INTO endereco.cidade VALUES (2117, '2019-10-23 14:05:31.168533', NULL, 'Senador Amaral', 11);
INSERT INTO endereco.cidade VALUES (2119, '2019-10-23 14:05:31.168533', NULL, 'Senador Firmino', 11);
INSERT INTO endereco.cidade VALUES (2121, '2019-10-23 14:05:31.168533', NULL, 'Senador Modestino Gonçalves', 11);
INSERT INTO endereco.cidade VALUES (2122, '2019-10-23 14:05:31.168533', NULL, 'Senhora De Oliveira', 11);
INSERT INTO endereco.cidade VALUES (2124, '2019-10-23 14:05:31.168533', NULL, 'Senhora Dos Remédios', 11);
INSERT INTO endereco.cidade VALUES (2126, '2019-10-23 14:05:31.168533', NULL, 'Seritinga', 11);
INSERT INTO endereco.cidade VALUES (2127, '2019-10-23 14:05:31.168533', NULL, 'Serra Azul De Minas', 11);
INSERT INTO endereco.cidade VALUES (2129, '2019-10-23 14:05:31.168533', NULL, 'Serra Do Salitre', 11);
INSERT INTO endereco.cidade VALUES (2131, '2019-10-23 14:05:31.168533', NULL, 'Serrania', 11);
INSERT INTO endereco.cidade VALUES (2133, '2019-10-23 14:05:31.168533', NULL, 'Serranos', 11);
INSERT INTO endereco.cidade VALUES (2134, '2019-10-23 14:05:31.168533', NULL, 'Serro', 11);
INSERT INTO endereco.cidade VALUES (2136, '2019-10-23 14:05:31.168533', NULL, 'Setubinha', 11);
INSERT INTO endereco.cidade VALUES (2137, '2019-10-23 14:05:31.168533', NULL, 'Silveirânia', 11);
INSERT INTO endereco.cidade VALUES (2139, '2019-10-23 14:05:31.168533', NULL, 'Simão Pereira', 11);
INSERT INTO endereco.cidade VALUES (2141, '2019-10-23 14:05:31.168533', NULL, 'SobrÁlia', 11);
INSERT INTO endereco.cidade VALUES (2143, '2019-10-23 14:05:31.168533', NULL, 'Tabuleiro', 11);
INSERT INTO endereco.cidade VALUES (2144, '2019-10-23 14:05:31.168533', NULL, 'Taiobeiras', 11);
INSERT INTO endereco.cidade VALUES (2731, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita', 15);
INSERT INTO endereco.cidade VALUES (2147, '2019-10-23 14:05:31.168533', NULL, 'Tapiraí', 11);
INSERT INTO endereco.cidade VALUES (2149, '2019-10-23 14:05:31.168533', NULL, 'Tarumirim', 11);
INSERT INTO endereco.cidade VALUES (2150, '2019-10-23 14:05:31.168533', NULL, 'Teixeiras', 11);
INSERT INTO endereco.cidade VALUES (2152, '2019-10-23 14:05:31.168533', NULL, 'Timóteo', 11);
INSERT INTO endereco.cidade VALUES (2154, '2019-10-23 14:05:31.168533', NULL, 'Tiros', 11);
INSERT INTO endereco.cidade VALUES (2155, '2019-10-23 14:05:31.168533', NULL, 'Tocantins', 11);
INSERT INTO endereco.cidade VALUES (2157, '2019-10-23 14:05:31.168533', NULL, 'Toledo', 11);
INSERT INTO endereco.cidade VALUES (2159, '2019-10-23 14:05:31.168533', NULL, 'Três Corações', 11);
INSERT INTO endereco.cidade VALUES (2161, '2019-10-23 14:05:31.168533', NULL, 'Três Pontas', 11);
INSERT INTO endereco.cidade VALUES (2162, '2019-10-23 14:05:31.168533', NULL, 'Tumiritinga', 11);
INSERT INTO endereco.cidade VALUES (2164, '2019-10-23 14:05:31.168533', NULL, 'Turmalina', 11);
INSERT INTO endereco.cidade VALUES (2166, '2019-10-23 14:05:31.168533', NULL, 'UbÁ', 11);
INSERT INTO endereco.cidade VALUES (2167, '2019-10-23 14:05:31.168533', NULL, 'Ubaí', 11);
INSERT INTO endereco.cidade VALUES (2169, '2019-10-23 14:05:31.168533', NULL, 'Uberaba', 11);
INSERT INTO endereco.cidade VALUES (2171, '2019-10-23 14:05:31.168533', NULL, 'Umburatiba', 11);
INSERT INTO endereco.cidade VALUES (2173, '2019-10-23 14:05:31.168533', NULL, 'União De Minas', 11);
INSERT INTO endereco.cidade VALUES (2174, '2019-10-23 14:05:31.168533', NULL, 'Uruana De Minas', 11);
INSERT INTO endereco.cidade VALUES (2176, '2019-10-23 14:05:31.168533', NULL, 'Urucuia', 11);
INSERT INTO endereco.cidade VALUES (2178, '2019-10-23 14:05:31.168533', NULL, 'Vargem Bonita', 11);
INSERT INTO endereco.cidade VALUES (2180, '2019-10-23 14:05:31.168533', NULL, 'Varginha', 11);
INSERT INTO endereco.cidade VALUES (2181, '2019-10-23 14:05:31.168533', NULL, 'Varjão De Minas', 11);
INSERT INTO endereco.cidade VALUES (2183, '2019-10-23 14:05:31.168533', NULL, 'Varzelândia', 11);
INSERT INTO endereco.cidade VALUES (2184, '2019-10-23 14:05:31.168533', NULL, 'Vazante', 11);
INSERT INTO endereco.cidade VALUES (2186, '2019-10-23 14:05:31.168533', NULL, 'Veredinha', 11);
INSERT INTO endereco.cidade VALUES (2188, '2019-10-23 14:05:31.168533', NULL, 'Vermelho Novo', 11);
INSERT INTO endereco.cidade VALUES (2190, '2019-10-23 14:05:31.168533', NULL, 'Viçosa', 11);
INSERT INTO endereco.cidade VALUES (2191, '2019-10-23 14:05:31.168533', NULL, 'Vieiras', 11);
INSERT INTO endereco.cidade VALUES (2193, '2019-10-23 14:05:31.168533', NULL, 'Virgínia', 11);
INSERT INTO endereco.cidade VALUES (2195, '2019-10-23 14:05:31.168533', NULL, 'Virgolândia', 11);
INSERT INTO endereco.cidade VALUES (2197, '2019-10-23 14:05:31.168533', NULL, 'Volta Grande', 11);
INSERT INTO endereco.cidade VALUES (2198, '2019-10-23 14:05:31.168533', NULL, 'Wenceslau Braz', 11);
INSERT INTO endereco.cidade VALUES (2200, '2019-10-23 14:05:31.168533', NULL, 'Alcinópolis', 12);
INSERT INTO endereco.cidade VALUES (2202, '2019-10-23 14:05:31.168533', NULL, 'Anastácio', 12);
INSERT INTO endereco.cidade VALUES (2203, '2019-10-23 14:05:31.168533', NULL, 'Anaurilândia', 12);
INSERT INTO endereco.cidade VALUES (2205, '2019-10-23 14:05:31.168533', NULL, 'Antônio João', 12);
INSERT INTO endereco.cidade VALUES (2207, '2019-10-23 14:05:31.168533', NULL, 'Aquidauana', 12);
INSERT INTO endereco.cidade VALUES (2209, '2019-10-23 14:05:31.168533', NULL, 'Bandeirantes', 12);
INSERT INTO endereco.cidade VALUES (2210, '2019-10-23 14:05:31.168533', NULL, 'Bataguassu', 12);
INSERT INTO endereco.cidade VALUES (2212, '2019-10-23 14:05:31.168533', NULL, 'Bela Vista', 12);
INSERT INTO endereco.cidade VALUES (2214, '2019-10-23 14:05:31.168533', NULL, 'Bonito', 12);
INSERT INTO endereco.cidade VALUES (2216, '2019-10-23 14:05:31.168533', NULL, 'Caarapó', 12);
INSERT INTO endereco.cidade VALUES (2217, '2019-10-23 14:05:31.168533', NULL, 'Camapuã', 12);
INSERT INTO endereco.cidade VALUES (2219, '2019-10-23 14:05:31.168533', NULL, 'Caracol', 12);
INSERT INTO endereco.cidade VALUES (2221, '2019-10-23 14:05:31.168533', NULL, 'Chapadão Do Sul', 12);
INSERT INTO endereco.cidade VALUES (2222, '2019-10-23 14:05:31.168533', NULL, 'Corguinho', 12);
INSERT INTO endereco.cidade VALUES (2224, '2019-10-23 14:05:31.168533', NULL, 'Corumbá', 12);
INSERT INTO endereco.cidade VALUES (2226, '2019-10-23 14:05:31.168533', NULL, 'Coxim', 12);
INSERT INTO endereco.cidade VALUES (2227, '2019-10-23 14:05:31.168533', NULL, 'Deodápolis', 12);
INSERT INTO endereco.cidade VALUES (2229, '2019-10-23 14:05:31.168533', NULL, 'Douradina', 12);
INSERT INTO endereco.cidade VALUES (2231, '2019-10-23 14:05:31.168533', NULL, 'Eldorado', 12);
INSERT INTO endereco.cidade VALUES (2233, '2019-10-23 14:05:31.168533', NULL, 'Figueirão', 12);
INSERT INTO endereco.cidade VALUES (2234, '2019-10-23 14:05:31.168533', NULL, 'Glória De Dourados', 12);
INSERT INTO endereco.cidade VALUES (2236, '2019-10-23 14:05:31.168533', NULL, 'Iguatemi', 12);
INSERT INTO endereco.cidade VALUES (2237, '2019-10-23 14:05:31.168533', NULL, 'Inocência', 12);
INSERT INTO endereco.cidade VALUES (2239, '2019-10-23 14:05:31.168533', NULL, 'Itaquiraí', 12);
INSERT INTO endereco.cidade VALUES (2241, '2019-10-23 14:05:31.168533', NULL, 'Japorã', 12);
INSERT INTO endereco.cidade VALUES (2243, '2019-10-23 14:05:31.168533', NULL, 'Jardim', 12);
INSERT INTO endereco.cidade VALUES (2245, '2019-10-23 14:05:31.168533', NULL, 'Juti', 12);
INSERT INTO endereco.cidade VALUES (2246, '2019-10-23 14:05:31.168533', NULL, 'Ladário', 12);
INSERT INTO endereco.cidade VALUES (2248, '2019-10-23 14:05:31.168533', NULL, 'Maracaju', 12);
INSERT INTO endereco.cidade VALUES (2250, '2019-10-23 14:05:31.168533', NULL, 'Mundo Novo', 12);
INSERT INTO endereco.cidade VALUES (2252, '2019-10-23 14:05:31.168533', NULL, 'Nioaque', 12);
INSERT INTO endereco.cidade VALUES (2254, '2019-10-23 14:05:31.168533', NULL, 'Nova Andradina', 12);
INSERT INTO endereco.cidade VALUES (2255, '2019-10-23 14:05:31.168533', NULL, 'Novo Horizonte Do Sul', 12);
INSERT INTO endereco.cidade VALUES (2257, '2019-10-23 14:05:31.168533', NULL, 'Paranhos', 12);
INSERT INTO endereco.cidade VALUES (2259, '2019-10-23 14:05:31.168533', NULL, 'Ponta Porã', 12);
INSERT INTO endereco.cidade VALUES (2261, '2019-10-23 14:05:31.168533', NULL, 'Ribas Do Rio Pardo', 12);
INSERT INTO endereco.cidade VALUES (2262, '2019-10-23 14:05:31.168533', NULL, 'Rio Brilhante', 12);
INSERT INTO endereco.cidade VALUES (2264, '2019-10-23 14:05:31.168533', NULL, 'Rio Verde De Mato Grosso', 12);
INSERT INTO endereco.cidade VALUES (2266, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita Do Pardo', 12);
INSERT INTO endereco.cidade VALUES (2268, '2019-10-23 14:05:31.168533', NULL, 'Selvíria', 12);
INSERT INTO endereco.cidade VALUES (2269, '2019-10-23 14:05:31.168533', NULL, 'Sete Quedas', 12);
INSERT INTO endereco.cidade VALUES (2271, '2019-10-23 14:05:31.168533', NULL, 'Sonora', 12);
INSERT INTO endereco.cidade VALUES (2733, '2019-10-23 14:05:31.168533', NULL, 'Santana De Mangueira', 15);
INSERT INTO endereco.cidade VALUES (2274, '2019-10-23 14:05:31.168533', NULL, 'Terenos', 12);
INSERT INTO endereco.cidade VALUES (2276, '2019-10-23 14:05:31.168533', NULL, 'Vicentina', 12);
INSERT INTO endereco.cidade VALUES (2278, '2019-10-23 14:05:31.168533', NULL, 'Água Boa', 13);
INSERT INTO endereco.cidade VALUES (2280, '2019-10-23 14:05:31.168533', NULL, 'Alto Araguaia', 13);
INSERT INTO endereco.cidade VALUES (2281, '2019-10-23 14:05:31.168533', NULL, 'Alto Boa Vista', 13);
INSERT INTO endereco.cidade VALUES (2283, '2019-10-23 14:05:31.168533', NULL, 'Alto Paraguai', 13);
INSERT INTO endereco.cidade VALUES (2285, '2019-10-23 14:05:31.168533', NULL, 'Apiacás', 13);
INSERT INTO endereco.cidade VALUES (2286, '2019-10-23 14:05:31.168533', NULL, 'Araguaiana', 13);
INSERT INTO endereco.cidade VALUES (2288, '2019-10-23 14:05:31.168533', NULL, 'Araputanga', 13);
INSERT INTO endereco.cidade VALUES (2290, '2019-10-23 14:05:31.168533', NULL, 'Aripuanã', 13);
INSERT INTO endereco.cidade VALUES (2292, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Bugres', 13);
INSERT INTO endereco.cidade VALUES (2294, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Do Araguaia', 13);
INSERT INTO endereco.cidade VALUES (2295, '2019-10-23 14:05:31.168533', NULL, 'Brasnorte', 13);
INSERT INTO endereco.cidade VALUES (2297, '2019-10-23 14:05:31.168533', NULL, 'Campinápolis', 13);
INSERT INTO endereco.cidade VALUES (2299, '2019-10-23 14:05:31.168533', NULL, 'Campo Verde', 13);
INSERT INTO endereco.cidade VALUES (2300, '2019-10-23 14:05:31.168533', NULL, 'Campos De Júlio', 13);
INSERT INTO endereco.cidade VALUES (2302, '2019-10-23 14:05:31.168533', NULL, 'Canarana', 13);
INSERT INTO endereco.cidade VALUES (2304, '2019-10-23 14:05:31.168533', NULL, 'Castanheira', 13);
INSERT INTO endereco.cidade VALUES (2306, '2019-10-23 14:05:31.168533', NULL, 'Cláudia', 13);
INSERT INTO endereco.cidade VALUES (2307, '2019-10-23 14:05:31.168533', NULL, 'Cocalinho', 13);
INSERT INTO endereco.cidade VALUES (2309, '2019-10-23 14:05:31.168533', NULL, 'Colniza', 13);
INSERT INTO endereco.cidade VALUES (2310, '2019-10-23 14:05:31.168533', NULL, 'Comodoro', 13);
INSERT INTO endereco.cidade VALUES (2313, '2019-10-23 14:05:31.168533', NULL, 'Cotriguaçu', 13);
INSERT INTO endereco.cidade VALUES (2314, '2019-10-23 14:05:31.168533', NULL, 'Cuiabá', 13);
INSERT INTO endereco.cidade VALUES (2316, '2019-10-23 14:05:31.168533', NULL, 'Denise', 13);
INSERT INTO endereco.cidade VALUES (2318, '2019-10-23 14:05:31.168533', NULL, 'Dom Aquino', 13);
INSERT INTO endereco.cidade VALUES (2320, '2019-10-23 14:05:31.168533', NULL, 'Figueirópolis D`Oeste', 13);
INSERT INTO endereco.cidade VALUES (2321, '2019-10-23 14:05:31.168533', NULL, 'Gaúcha Do Norte', 13);
INSERT INTO endereco.cidade VALUES (2323, '2019-10-23 14:05:31.168533', NULL, 'Glória D`Oeste', 13);
INSERT INTO endereco.cidade VALUES (2325, '2019-10-23 14:05:31.168533', NULL, 'Guiratinga', 13);
INSERT INTO endereco.cidade VALUES (2327, '2019-10-23 14:05:31.168533', NULL, 'Ipiranga Do Norte', 13);
INSERT INTO endereco.cidade VALUES (2328, '2019-10-23 14:05:31.168533', NULL, 'Itanhangá', 13);
INSERT INTO endereco.cidade VALUES (2330, '2019-10-23 14:05:31.168533', NULL, 'Itiquira', 13);
INSERT INTO endereco.cidade VALUES (2332, '2019-10-23 14:05:31.168533', NULL, 'Jangada', 13);
INSERT INTO endereco.cidade VALUES (2334, '2019-10-23 14:05:31.168533', NULL, 'Juara', 13);
INSERT INTO endereco.cidade VALUES (2335, '2019-10-23 14:05:31.168533', NULL, 'Juína', 13);
INSERT INTO endereco.cidade VALUES (2337, '2019-10-23 14:05:31.168533', NULL, 'Juscimeira', 13);
INSERT INTO endereco.cidade VALUES (2339, '2019-10-23 14:05:31.168533', NULL, 'Lucas Do Rio Verde', 13);
INSERT INTO endereco.cidade VALUES (2341, '2019-10-23 14:05:31.168533', NULL, 'Marcelândia', 13);
INSERT INTO endereco.cidade VALUES (2342, '2019-10-23 14:05:31.168533', NULL, 'Matupá', 13);
INSERT INTO endereco.cidade VALUES (2344, '2019-10-23 14:05:31.168533', NULL, 'Nobres', 13);
INSERT INTO endereco.cidade VALUES (2346, '2019-10-23 14:05:31.168533', NULL, 'Nossa Senhora Do Livramento', 13);
INSERT INTO endereco.cidade VALUES (2348, '2019-10-23 14:05:31.168533', NULL, 'Nova Brasilândia', 13);
INSERT INTO endereco.cidade VALUES (2349, '2019-10-23 14:05:31.168533', NULL, 'Nova Canaã Do Norte', 13);
INSERT INTO endereco.cidade VALUES (2351, '2019-10-23 14:05:31.168533', NULL, 'Nova Lacerda', 13);
INSERT INTO endereco.cidade VALUES (2353, '2019-10-23 14:05:31.168533', NULL, 'Nova Maringá', 13);
INSERT INTO endereco.cidade VALUES (2355, '2019-10-23 14:05:31.168533', NULL, 'Nova Mutum', 13);
INSERT INTO endereco.cidade VALUES (2356, '2019-10-23 14:05:31.168533', NULL, 'Nova Nazaré', 13);
INSERT INTO endereco.cidade VALUES (2358, '2019-10-23 14:05:31.168533', NULL, 'Nova Santa Helena', 13);
INSERT INTO endereco.cidade VALUES (2360, '2019-10-23 14:05:31.168533', NULL, 'Nova Xavantina', 13);
INSERT INTO endereco.cidade VALUES (2362, '2019-10-23 14:05:31.168533', NULL, 'Novo Mundo', 13);
INSERT INTO endereco.cidade VALUES (2363, '2019-10-23 14:05:31.168533', NULL, 'Novo Santo Antônio', 13);
INSERT INTO endereco.cidade VALUES (2365, '2019-10-23 14:05:31.168533', NULL, 'Paranaíta', 13);
INSERT INTO endereco.cidade VALUES (2367, '2019-10-23 14:05:31.168533', NULL, 'Pedra Preta', 13);
INSERT INTO endereco.cidade VALUES (2369, '2019-10-23 14:05:31.168533', NULL, 'Planalto Da Serra', 13);
INSERT INTO endereco.cidade VALUES (2370, '2019-10-23 14:05:31.168533', NULL, 'Poconé', 13);
INSERT INTO endereco.cidade VALUES (2372, '2019-10-23 14:05:31.168533', NULL, 'Ponte Branca', 13);
INSERT INTO endereco.cidade VALUES (2374, '2019-10-23 14:05:31.168533', NULL, 'Porto Alegre Do Norte', 13);
INSERT INTO endereco.cidade VALUES (2376, '2019-10-23 14:05:31.168533', NULL, 'Porto Esperidião', 13);
INSERT INTO endereco.cidade VALUES (2377, '2019-10-23 14:05:31.168533', NULL, 'Porto Estrela', 13);
INSERT INTO endereco.cidade VALUES (2379, '2019-10-23 14:05:31.168533', NULL, 'Primavera Do Leste', 13);
INSERT INTO endereco.cidade VALUES (2381, '2019-10-23 14:05:31.168533', NULL, 'Reserva Do Cabaçal', 13);
INSERT INTO endereco.cidade VALUES (2383, '2019-10-23 14:05:31.168533', NULL, 'Ribeirãozinho', 13);
INSERT INTO endereco.cidade VALUES (2384, '2019-10-23 14:05:31.168533', NULL, 'Rio Branco', 13);
INSERT INTO endereco.cidade VALUES (2386, '2019-10-23 14:05:31.168533', NULL, 'Rondonópolis', 13);
INSERT INTO endereco.cidade VALUES (2388, '2019-10-23 14:05:31.168533', NULL, 'Salto Do Céu', 13);
INSERT INTO endereco.cidade VALUES (2389, '2019-10-23 14:05:31.168533', NULL, 'Santa Carmem', 13);
INSERT INTO endereco.cidade VALUES (2391, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita Do Trivelato', 13);
INSERT INTO endereco.cidade VALUES (2393, '2019-10-23 14:05:31.168533', NULL, 'Santo Afonso', 13);
INSERT INTO endereco.cidade VALUES (2395, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Leverger', 13);
INSERT INTO endereco.cidade VALUES (2736, '2019-10-23 14:05:31.168533', NULL, 'São Bentinho', 15);
INSERT INTO endereco.cidade VALUES (2399, '2019-10-23 14:05:31.168533', NULL, 'São José Do Xingu', 13);
INSERT INTO endereco.cidade VALUES (2401, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Da Cipa', 13);
INSERT INTO endereco.cidade VALUES (2402, '2019-10-23 14:05:31.168533', NULL, 'Sapezal', 13);
INSERT INTO endereco.cidade VALUES (2403, '2019-10-23 14:05:31.168533', NULL, 'Serra Nova Dourada', 13);
INSERT INTO endereco.cidade VALUES (2405, '2019-10-23 14:05:31.168533', NULL, 'Sorriso', 13);
INSERT INTO endereco.cidade VALUES (2407, '2019-10-23 14:05:31.168533', NULL, 'Tangará Da Serra', 13);
INSERT INTO endereco.cidade VALUES (2409, '2019-10-23 14:05:31.168533', NULL, 'Terra Nova Do Norte', 13);
INSERT INTO endereco.cidade VALUES (2410, '2019-10-23 14:05:31.168533', NULL, 'Tesouro', 13);
INSERT INTO endereco.cidade VALUES (2412, '2019-10-23 14:05:31.168533', NULL, 'União Do Sul', 13);
INSERT INTO endereco.cidade VALUES (2414, '2019-10-23 14:05:31.168533', NULL, 'Várzea Grande', 13);
INSERT INTO endereco.cidade VALUES (2415, '2019-10-23 14:05:31.168533', NULL, 'Vera', 13);
INSERT INTO endereco.cidade VALUES (2417, '2019-10-23 14:05:31.168533', NULL, 'Vila Rica', 13);
INSERT INTO endereco.cidade VALUES (2419, '2019-10-23 14:05:31.168533', NULL, 'Abel Figueiredo', 14);
INSERT INTO endereco.cidade VALUES (2421, '2019-10-23 14:05:31.168533', NULL, 'Afuá', 14);
INSERT INTO endereco.cidade VALUES (2422, '2019-10-23 14:05:31.168533', NULL, 'Água Azul Do Norte', 14);
INSERT INTO endereco.cidade VALUES (2424, '2019-10-23 14:05:31.168533', NULL, 'Almeirim', 14);
INSERT INTO endereco.cidade VALUES (2426, '2019-10-23 14:05:31.168533', NULL, 'Anajás', 14);
INSERT INTO endereco.cidade VALUES (2428, '2019-10-23 14:05:31.168533', NULL, 'Anapu', 14);
INSERT INTO endereco.cidade VALUES (2430, '2019-10-23 14:05:31.168533', NULL, 'Aurora Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2431, '2019-10-23 14:05:31.168533', NULL, 'Aveiro', 14);
INSERT INTO endereco.cidade VALUES (2433, '2019-10-23 14:05:31.168533', NULL, 'Baião', 14);
INSERT INTO endereco.cidade VALUES (2435, '2019-10-23 14:05:31.168533', NULL, 'Barcarena', 14);
INSERT INTO endereco.cidade VALUES (2436, '2019-10-23 14:05:31.168533', NULL, 'Belém', 14);
INSERT INTO endereco.cidade VALUES (2438, '2019-10-23 14:05:31.168533', NULL, 'Benevides', 14);
INSERT INTO endereco.cidade VALUES (2440, '2019-10-23 14:05:31.168533', NULL, 'Bonito', 14);
INSERT INTO endereco.cidade VALUES (2442, '2019-10-23 14:05:31.168533', NULL, 'Brasil Novo', 14);
INSERT INTO endereco.cidade VALUES (2444, '2019-10-23 14:05:31.168533', NULL, 'Breu Branco', 14);
INSERT INTO endereco.cidade VALUES (2445, '2019-10-23 14:05:31.168533', NULL, 'Breves', 14);
INSERT INTO endereco.cidade VALUES (2447, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Do Arari', 14);
INSERT INTO endereco.cidade VALUES (2449, '2019-10-23 14:05:31.168533', NULL, 'Cametá', 14);
INSERT INTO endereco.cidade VALUES (2450, '2019-10-23 14:05:31.168533', NULL, 'Canaã Dos Carajás', 14);
INSERT INTO endereco.cidade VALUES (2452, '2019-10-23 14:05:31.168533', NULL, 'Capitão Poço', 14);
INSERT INTO endereco.cidade VALUES (2454, '2019-10-23 14:05:31.168533', NULL, 'Chaves', 14);
INSERT INTO endereco.cidade VALUES (2456, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Araguaia', 14);
INSERT INTO endereco.cidade VALUES (2457, '2019-10-23 14:05:31.168533', NULL, 'Concórdia Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2459, '2019-10-23 14:05:31.168533', NULL, 'Curionópolis', 14);
INSERT INTO endereco.cidade VALUES (2461, '2019-10-23 14:05:31.168533', NULL, 'Curuá', 14);
INSERT INTO endereco.cidade VALUES (2463, '2019-10-23 14:05:31.168533', NULL, 'Dom Eliseu', 14);
INSERT INTO endereco.cidade VALUES (2465, '2019-10-23 14:05:31.168533', NULL, 'Faro', 14);
INSERT INTO endereco.cidade VALUES (2466, '2019-10-23 14:05:31.168533', NULL, 'Floresta Do Araguaia', 14);
INSERT INTO endereco.cidade VALUES (2468, '2019-10-23 14:05:31.168533', NULL, 'Goianésia Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2469, '2019-10-23 14:05:31.168533', NULL, 'Gurupá', 14);
INSERT INTO endereco.cidade VALUES (2471, '2019-10-23 14:05:31.168533', NULL, 'Igarapé Miri', 14);
INSERT INTO endereco.cidade VALUES (2473, '2019-10-23 14:05:31.168533', NULL, 'Ipixuna Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2475, '2019-10-23 14:05:31.168533', NULL, 'Itaituba', 14);
INSERT INTO endereco.cidade VALUES (2476, '2019-10-23 14:05:31.168533', NULL, 'Itupiranga', 14);
INSERT INTO endereco.cidade VALUES (2478, '2019-10-23 14:05:31.168533', NULL, 'Jacundá', 14);
INSERT INTO endereco.cidade VALUES (2480, '2019-10-23 14:05:31.168533', NULL, 'Limoeiro Do Ajuru', 14);
INSERT INTO endereco.cidade VALUES (2482, '2019-10-23 14:05:31.168533', NULL, 'Magalhães Barata', 14);
INSERT INTO endereco.cidade VALUES (2484, '2019-10-23 14:05:31.168533', NULL, 'Maracanã', 14);
INSERT INTO endereco.cidade VALUES (2485, '2019-10-23 14:05:31.168533', NULL, 'Marapanim', 14);
INSERT INTO endereco.cidade VALUES (2487, '2019-10-23 14:05:31.168533', NULL, 'Medicilândia', 14);
INSERT INTO endereco.cidade VALUES (2489, '2019-10-23 14:05:31.168533', NULL, 'Mocajuba', 14);
INSERT INTO endereco.cidade VALUES (2491, '2019-10-23 14:05:31.168533', NULL, 'Monte Alegre', 14);
INSERT INTO endereco.cidade VALUES (2492, '2019-10-23 14:05:31.168533', NULL, 'Muaná', 14);
INSERT INTO endereco.cidade VALUES (2494, '2019-10-23 14:05:31.168533', NULL, 'Nova Ipixuna', 14);
INSERT INTO endereco.cidade VALUES (2496, '2019-10-23 14:05:31.168533', NULL, 'Novo Progresso', 14);
INSERT INTO endereco.cidade VALUES (2498, '2019-10-23 14:05:31.168533', NULL, 'Óbidos', 14);
INSERT INTO endereco.cidade VALUES (2499, '2019-10-23 14:05:31.168533', NULL, 'Oeiras Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2501, '2019-10-23 14:05:31.168533', NULL, 'Ourém', 14);
INSERT INTO endereco.cidade VALUES (2503, '2019-10-23 14:05:31.168533', NULL, 'Pacajá', 14);
INSERT INTO endereco.cidade VALUES (2504, '2019-10-23 14:05:31.168533', NULL, 'Palestina Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2506, '2019-10-23 14:05:31.168533', NULL, 'Parauapebas', 14);
INSERT INTO endereco.cidade VALUES (2508, '2019-10-23 14:05:31.168533', NULL, 'Peixe Boi', 14);
INSERT INTO endereco.cidade VALUES (2509, '2019-10-23 14:05:31.168533', NULL, 'Piçarra', 14);
INSERT INTO endereco.cidade VALUES (2511, '2019-10-23 14:05:31.168533', NULL, 'Ponta De Pedras', 14);
INSERT INTO endereco.cidade VALUES (2513, '2019-10-23 14:05:31.168533', NULL, 'Porto De Moz', 14);
INSERT INTO endereco.cidade VALUES (2515, '2019-10-23 14:05:31.168533', NULL, 'Primavera', 14);
INSERT INTO endereco.cidade VALUES (2517, '2019-10-23 14:05:31.168533', NULL, 'Redenção', 14);
INSERT INTO endereco.cidade VALUES (2518, '2019-10-23 14:05:31.168533', NULL, 'Rio Maria', 14);
INSERT INTO endereco.cidade VALUES (2520, '2019-10-23 14:05:31.168533', NULL, 'Rurópolis', 14);
INSERT INTO endereco.cidade VALUES (2522, '2019-10-23 14:05:31.168533', NULL, 'Salvaterra', 14);
INSERT INTO endereco.cidade VALUES (2872, '2019-10-23 14:05:31.168533', NULL, 'Jaqueira', 16);
INSERT INTO endereco.cidade VALUES (2526, '2019-10-23 14:05:31.168533', NULL, 'Santa Luzia Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2527, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Das Barreiras', 14);
INSERT INTO endereco.cidade VALUES (2529, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Araguaia', 14);
INSERT INTO endereco.cidade VALUES (2530, '2019-10-23 14:05:31.168533', NULL, 'Santarém', 14);
INSERT INTO endereco.cidade VALUES (2532, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Tauá', 14);
INSERT INTO endereco.cidade VALUES (2534, '2019-10-23 14:05:31.168533', NULL, 'São Domingos Do Araguaia', 14);
INSERT INTO endereco.cidade VALUES (2536, '2019-10-23 14:05:31.168533', NULL, 'São Félix Do Xingu', 14);
INSERT INTO endereco.cidade VALUES (2537, '2019-10-23 14:05:31.168533', NULL, 'São Francisco Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2539, '2019-10-23 14:05:31.168533', NULL, 'São João Da Ponta', 14);
INSERT INTO endereco.cidade VALUES (2541, '2019-10-23 14:05:31.168533', NULL, 'São João Do Araguaia', 14);
INSERT INTO endereco.cidade VALUES (2542, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Guamá', 14);
INSERT INTO endereco.cidade VALUES (2544, '2019-10-23 14:05:31.168533', NULL, 'Sapucaia', 14);
INSERT INTO endereco.cidade VALUES (2545, '2019-10-23 14:05:31.168533', NULL, 'Senador José Porfírio', 14);
INSERT INTO endereco.cidade VALUES (2547, '2019-10-23 14:05:31.168533', NULL, 'Tailândia', 14);
INSERT INTO endereco.cidade VALUES (2549, '2019-10-23 14:05:31.168533', NULL, 'Terra Santa', 14);
INSERT INTO endereco.cidade VALUES (2550, '2019-10-23 14:05:31.168533', NULL, 'Tomé Açu', 14);
INSERT INTO endereco.cidade VALUES (2552, '2019-10-23 14:05:31.168533', NULL, 'Trairão', 14);
INSERT INTO endereco.cidade VALUES (2554, '2019-10-23 14:05:31.168533', NULL, 'Tucuruí', 14);
INSERT INTO endereco.cidade VALUES (2556, '2019-10-23 14:05:31.168533', NULL, 'Uruará', 14);
INSERT INTO endereco.cidade VALUES (2557, '2019-10-23 14:05:31.168533', NULL, 'Vigia', 14);
INSERT INTO endereco.cidade VALUES (2559, '2019-10-23 14:05:31.168533', NULL, 'Vitória Do Xingu', 14);
INSERT INTO endereco.cidade VALUES (2561, '2019-10-23 14:05:31.168533', NULL, 'Água Branca', 15);
INSERT INTO endereco.cidade VALUES (2563, '2019-10-23 14:05:31.168533', NULL, 'Alagoa Grande', 15);
INSERT INTO endereco.cidade VALUES (2565, '2019-10-23 14:05:31.168533', NULL, 'Alagoinha', 15);
INSERT INTO endereco.cidade VALUES (2567, '2019-10-23 14:05:31.168533', NULL, 'Algodão De Jandaíra', 15);
INSERT INTO endereco.cidade VALUES (2568, '2019-10-23 14:05:31.168533', NULL, 'Alhandra', 15);
INSERT INTO endereco.cidade VALUES (2570, '2019-10-23 14:05:31.168533', NULL, 'Aparecida', 15);
INSERT INTO endereco.cidade VALUES (2572, '2019-10-23 14:05:31.168533', NULL, 'Arara', 15);
INSERT INTO endereco.cidade VALUES (2574, '2019-10-23 14:05:31.168533', NULL, 'Areia', 15);
INSERT INTO endereco.cidade VALUES (2576, '2019-10-23 14:05:31.168533', NULL, 'Areial', 15);
INSERT INTO endereco.cidade VALUES (2577, '2019-10-23 14:05:31.168533', NULL, 'Aroeiras', 15);
INSERT INTO endereco.cidade VALUES (2579, '2019-10-23 14:05:31.168533', NULL, 'Baía Da Traição', 15);
INSERT INTO endereco.cidade VALUES (2581, '2019-10-23 14:05:31.168533', NULL, 'Baraúna', 15);
INSERT INTO endereco.cidade VALUES (2583, '2019-10-23 14:05:31.168533', NULL, 'Barra De Santana', 15);
INSERT INTO endereco.cidade VALUES (2584, '2019-10-23 14:05:31.168533', NULL, 'Barra De São Miguel', 15);
INSERT INTO endereco.cidade VALUES (2586, '2019-10-23 14:05:31.168533', NULL, 'Belém', 15);
INSERT INTO endereco.cidade VALUES (2588, '2019-10-23 14:05:31.168533', NULL, 'Bernardino Batista', 15);
INSERT INTO endereco.cidade VALUES (2589, '2019-10-23 14:05:31.168533', NULL, 'Boa Ventura', 15);
INSERT INTO endereco.cidade VALUES (2591, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus', 15);
INSERT INTO endereco.cidade VALUES (2593, '2019-10-23 14:05:31.168533', NULL, 'Bonito De Santa Fé', 15);
INSERT INTO endereco.cidade VALUES (2595, '2019-10-23 14:05:31.168533', NULL, 'Borborema', 15);
INSERT INTO endereco.cidade VALUES (2596, '2019-10-23 14:05:31.168533', NULL, 'Brejo Do Cruz', 15);
INSERT INTO endereco.cidade VALUES (2598, '2019-10-23 14:05:31.168533', NULL, 'Caaporã', 15);
INSERT INTO endereco.cidade VALUES (2600, '2019-10-23 14:05:31.168533', NULL, 'Cabedelo', 15);
INSERT INTO endereco.cidade VALUES (2602, '2019-10-23 14:05:31.168533', NULL, 'Cacimba De Areia', 15);
INSERT INTO endereco.cidade VALUES (2603, '2019-10-23 14:05:31.168533', NULL, 'Cacimba De Dentro', 15);
INSERT INTO endereco.cidade VALUES (2605, '2019-10-23 14:05:31.168533', NULL, 'Caiçara', 15);
INSERT INTO endereco.cidade VALUES (2607, '2019-10-23 14:05:31.168533', NULL, 'Cajazeirinhas', 15);
INSERT INTO endereco.cidade VALUES (2609, '2019-10-23 14:05:31.168533', NULL, 'Camalaú', 15);
INSERT INTO endereco.cidade VALUES (2610, '2019-10-23 14:05:31.168533', NULL, 'Campina Grande', 15);
INSERT INTO endereco.cidade VALUES (2612, '2019-10-23 14:05:31.168533', NULL, 'Caraúbas', 15);
INSERT INTO endereco.cidade VALUES (2614, '2019-10-23 14:05:31.168533', NULL, 'Casserengue', 15);
INSERT INTO endereco.cidade VALUES (2616, '2019-10-23 14:05:31.168533', NULL, 'Catolé Do Rocha', 15);
INSERT INTO endereco.cidade VALUES (2617, '2019-10-23 14:05:31.168533', NULL, 'Caturité', 15);
INSERT INTO endereco.cidade VALUES (2619, '2019-10-23 14:05:31.168533', NULL, 'Condado', 15);
INSERT INTO endereco.cidade VALUES (2621, '2019-10-23 14:05:31.168533', NULL, 'Congo', 15);
INSERT INTO endereco.cidade VALUES (2623, '2019-10-23 14:05:31.168533', NULL, 'Coxixola', 15);
INSERT INTO endereco.cidade VALUES (2625, '2019-10-23 14:05:31.168533', NULL, 'Cubati', 15);
INSERT INTO endereco.cidade VALUES (2626, '2019-10-23 14:05:31.168533', NULL, 'Cuité', 15);
INSERT INTO endereco.cidade VALUES (2628, '2019-10-23 14:05:31.168533', NULL, 'Cuitegi', 15);
INSERT INTO endereco.cidade VALUES (2630, '2019-10-23 14:05:31.168533', NULL, 'Curral Velho', 15);
INSERT INTO endereco.cidade VALUES (2631, '2019-10-23 14:05:31.168533', NULL, 'Damião', 15);
INSERT INTO endereco.cidade VALUES (2633, '2019-10-23 14:05:31.168533', NULL, 'Diamante', 15);
INSERT INTO endereco.cidade VALUES (2634, '2019-10-23 14:05:31.168533', NULL, 'Dona Inês', 15);
INSERT INTO endereco.cidade VALUES (2636, '2019-10-23 14:05:31.168533', NULL, 'Emas', 15);
INSERT INTO endereco.cidade VALUES (2638, '2019-10-23 14:05:31.168533', NULL, 'Fagundes', 15);
INSERT INTO endereco.cidade VALUES (2640, '2019-10-23 14:05:31.168533', NULL, 'Gado Bravo', 15);
INSERT INTO endereco.cidade VALUES (2641, '2019-10-23 14:05:31.168533', NULL, 'Guarabira', 15);
INSERT INTO endereco.cidade VALUES (2643, '2019-10-23 14:05:31.168533', NULL, 'Gurjão', 15);
INSERT INTO endereco.cidade VALUES (2645, '2019-10-23 14:05:31.168533', NULL, 'Igaracy', 15);
INSERT INTO endereco.cidade VALUES (2647, '2019-10-23 14:05:31.168533', NULL, 'Ingá', 15);
INSERT INTO endereco.cidade VALUES (2648, '2019-10-23 14:05:31.168533', NULL, 'Itabaiana', 15);
INSERT INTO endereco.cidade VALUES (2714, '2019-10-23 14:05:31.168533', NULL, 'Puxinanã', 15);
INSERT INTO endereco.cidade VALUES (2715, '2019-10-23 14:05:31.168533', NULL, 'Queimadas', 15);
INSERT INTO endereco.cidade VALUES (2717, '2019-10-23 14:05:31.168533', NULL, 'Remígio', 15);
INSERT INTO endereco.cidade VALUES (2719, '2019-10-23 14:05:31.168533', NULL, 'Riachão Do Bacamarte', 15);
INSERT INTO endereco.cidade VALUES (2720, '2019-10-23 14:05:31.168533', NULL, 'Riachão Do Poço', 15);
INSERT INTO endereco.cidade VALUES (2722, '2019-10-23 14:05:31.168533', NULL, 'Riacho Dos Cavalos', 15);
INSERT INTO endereco.cidade VALUES (2723, '2019-10-23 14:05:31.168533', NULL, 'Rio Tinto', 15);
INSERT INTO endereco.cidade VALUES (2725, '2019-10-23 14:05:31.168533', NULL, 'Salgado De São Félix', 15);
INSERT INTO endereco.cidade VALUES (2727, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz', 15);
INSERT INTO endereco.cidade VALUES (2729, '2019-10-23 14:05:31.168533', NULL, 'Santa Inês', 15);
INSERT INTO endereco.cidade VALUES (2730, '2019-10-23 14:05:31.168533', NULL, 'Santa Luzia', 15);
INSERT INTO endereco.cidade VALUES (2732, '2019-10-23 14:05:31.168533', NULL, 'Santa Teresinha', 15);
INSERT INTO endereco.cidade VALUES (2734, '2019-10-23 14:05:31.168533', NULL, 'Santana Dos Garrotes', 15);
INSERT INTO endereco.cidade VALUES (2735, '2019-10-23 14:05:31.168533', NULL, 'Santo André', 15);
INSERT INTO endereco.cidade VALUES (2737, '2019-10-23 14:05:31.168533', NULL, 'São Bento', 15);
INSERT INTO endereco.cidade VALUES (2739, '2019-10-23 14:05:31.168533', NULL, 'São Domingos Do Cariri', 15);
INSERT INTO endereco.cidade VALUES (2740, '2019-10-23 14:05:31.168533', NULL, 'São Francisco', 15);
INSERT INTO endereco.cidade VALUES (2742, '2019-10-23 14:05:31.168533', NULL, 'São João Do Rio Do Peixe', 15);
INSERT INTO endereco.cidade VALUES (2743, '2019-10-23 14:05:31.168533', NULL, 'São João Do Tigre', 15);
INSERT INTO endereco.cidade VALUES (2745, '2019-10-23 14:05:31.168533', NULL, 'São José De Caiana', 15);
INSERT INTO endereco.cidade VALUES (2747, '2019-10-23 14:05:31.168533', NULL, 'São José De Piranhas', 15);
INSERT INTO endereco.cidade VALUES (2748, '2019-10-23 14:05:31.168533', NULL, 'São José De Princesa', 15);
INSERT INTO endereco.cidade VALUES (2750, '2019-10-23 14:05:31.168533', NULL, 'São José Do Brejo Do Cruz', 15);
INSERT INTO endereco.cidade VALUES (2751, '2019-10-23 14:05:31.168533', NULL, 'São José Do Sabugi', 15);
INSERT INTO endereco.cidade VALUES (2753, '2019-10-23 14:05:31.168533', NULL, 'São José Dos Ramos', 15);
INSERT INTO endereco.cidade VALUES (2754, '2019-10-23 14:05:31.168533', NULL, 'São Mamede', 15);
INSERT INTO endereco.cidade VALUES (2756, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião De Lagoa De Roça', 15);
INSERT INTO endereco.cidade VALUES (2758, '2019-10-23 14:05:31.168533', NULL, 'Sapé', 15);
INSERT INTO endereco.cidade VALUES (2652, '2019-10-23 14:05:31.168533', NULL, 'Jacaraú', 15);
INSERT INTO endereco.cidade VALUES (2654, '2019-10-23 14:05:31.168533', NULL, 'João Pessoa', 15);
INSERT INTO endereco.cidade VALUES (2656, '2019-10-23 14:05:31.168533', NULL, 'Juarez Távora', 15);
INSERT INTO endereco.cidade VALUES (2657, '2019-10-23 14:05:31.168533', NULL, 'Juazeirinho', 15);
INSERT INTO endereco.cidade VALUES (2659, '2019-10-23 14:05:31.168533', NULL, 'Juripiranga', 15);
INSERT INTO endereco.cidade VALUES (2660, '2019-10-23 14:05:31.168533', NULL, 'Juru', 15);
INSERT INTO endereco.cidade VALUES (2662, '2019-10-23 14:05:31.168533', NULL, 'Lagoa De Dentro', 15);
INSERT INTO endereco.cidade VALUES (2664, '2019-10-23 14:05:31.168533', NULL, 'Lastro', 15);
INSERT INTO endereco.cidade VALUES (2666, '2019-10-23 14:05:31.168533', NULL, 'Logradouro', 15);
INSERT INTO endereco.cidade VALUES (2668, '2019-10-23 14:05:31.168533', NULL, 'Mãe D`Água', 15);
INSERT INTO endereco.cidade VALUES (2669, '2019-10-23 14:05:31.168533', NULL, 'Malta', 15);
INSERT INTO endereco.cidade VALUES (2671, '2019-10-23 14:05:31.168533', NULL, 'Manaíra', 15);
INSERT INTO endereco.cidade VALUES (2673, '2019-10-23 14:05:31.168533', NULL, 'Mari', 15);
INSERT INTO endereco.cidade VALUES (2675, '2019-10-23 14:05:31.168533', NULL, 'Massaranduba', 15);
INSERT INTO endereco.cidade VALUES (2676, '2019-10-23 14:05:31.168533', NULL, 'Mataraca', 15);
INSERT INTO endereco.cidade VALUES (2678, '2019-10-23 14:05:31.168533', NULL, 'Mato Grosso', 15);
INSERT INTO endereco.cidade VALUES (2680, '2019-10-23 14:05:31.168533', NULL, 'Mogeiro', 15);
INSERT INTO endereco.cidade VALUES (2682, '2019-10-23 14:05:31.168533', NULL, 'Monte Horebe', 15);
INSERT INTO endereco.cidade VALUES (2683, '2019-10-23 14:05:31.168533', NULL, 'Monteiro', 15);
INSERT INTO endereco.cidade VALUES (2685, '2019-10-23 14:05:31.168533', NULL, 'Natuba', 15);
INSERT INTO endereco.cidade VALUES (2687, '2019-10-23 14:05:31.168533', NULL, 'Nova Floresta', 15);
INSERT INTO endereco.cidade VALUES (2689, '2019-10-23 14:05:31.168533', NULL, 'Nova Palmeira', 15);
INSERT INTO endereco.cidade VALUES (2691, '2019-10-23 14:05:31.168533', NULL, 'Olivedos', 15);
INSERT INTO endereco.cidade VALUES (2693, '2019-10-23 14:05:31.168533', NULL, 'Parari', 15);
INSERT INTO endereco.cidade VALUES (2694, '2019-10-23 14:05:31.168533', NULL, 'Passagem', 15);
INSERT INTO endereco.cidade VALUES (2696, '2019-10-23 14:05:31.168533', NULL, 'Paulista', 15);
INSERT INTO endereco.cidade VALUES (2698, '2019-10-23 14:05:31.168533', NULL, 'Pedra Lavrada', 15);
INSERT INTO endereco.cidade VALUES (2700, '2019-10-23 14:05:31.168533', NULL, 'Pedro Régis', 15);
INSERT INTO endereco.cidade VALUES (2701, '2019-10-23 14:05:31.168533', NULL, 'Piancó', 15);
INSERT INTO endereco.cidade VALUES (2703, '2019-10-23 14:05:31.168533', NULL, 'Pilar', 15);
INSERT INTO endereco.cidade VALUES (2705, '2019-10-23 14:05:31.168533', NULL, 'Pilõezinhos', 15);
INSERT INTO endereco.cidade VALUES (2707, '2019-10-23 14:05:31.168533', NULL, 'Pitimbu', 15);
INSERT INTO endereco.cidade VALUES (2708, '2019-10-23 14:05:31.168533', NULL, 'Pocinhos', 15);
INSERT INTO endereco.cidade VALUES (2710, '2019-10-23 14:05:31.168533', NULL, 'Poço De José De Moura', 15);
INSERT INTO endereco.cidade VALUES (2712, '2019-10-23 14:05:31.168533', NULL, 'Prata', 15);
INSERT INTO endereco.cidade VALUES (2759, '2019-10-23 14:05:31.168533', NULL, 'Seridó', 15);
INSERT INTO endereco.cidade VALUES (2761, '2019-10-23 14:05:31.168533', NULL, 'Serra Da Raiz', 15);
INSERT INTO endereco.cidade VALUES (2762, '2019-10-23 14:05:31.168533', NULL, 'Serra Grande', 15);
INSERT INTO endereco.cidade VALUES (2764, '2019-10-23 14:05:31.168533', NULL, 'Serraria', 15);
INSERT INTO endereco.cidade VALUES (2766, '2019-10-23 14:05:31.168533', NULL, 'Sobrado', 15);
INSERT INTO endereco.cidade VALUES (2767, '2019-10-23 14:05:31.168533', NULL, 'Solânea', 15);
INSERT INTO endereco.cidade VALUES (2769, '2019-10-23 14:05:31.168533', NULL, 'Sossêgo', 15);
INSERT INTO endereco.cidade VALUES (2771, '2019-10-23 14:05:31.168533', NULL, 'Sumé', 15);
INSERT INTO endereco.cidade VALUES (2773, '2019-10-23 14:05:31.168533', NULL, 'Taperoá', 15);
INSERT INTO endereco.cidade VALUES (2774, '2019-10-23 14:05:31.168533', NULL, 'Tavares', 15);
INSERT INTO endereco.cidade VALUES (2777, '2019-10-23 14:05:31.168533', NULL, 'Triunfo', 15);
INSERT INTO endereco.cidade VALUES (2779, '2019-10-23 14:05:31.168533', NULL, 'Umbuzeiro', 15);
INSERT INTO endereco.cidade VALUES (2780, '2019-10-23 14:05:31.168533', NULL, 'Várzea', 15);
INSERT INTO endereco.cidade VALUES (2782, '2019-10-23 14:05:31.168533', NULL, 'Vista Serrana', 15);
INSERT INTO endereco.cidade VALUES (2784, '2019-10-23 14:05:31.168533', NULL, 'Abreu E Lima', 16);
INSERT INTO endereco.cidade VALUES (2786, '2019-10-23 14:05:31.168533', NULL, 'Afrânio', 16);
INSERT INTO endereco.cidade VALUES (2787, '2019-10-23 14:05:31.168533', NULL, 'Agrestina', 16);
INSERT INTO endereco.cidade VALUES (2789, '2019-10-23 14:05:31.168533', NULL, 'Águas Belas', 16);
INSERT INTO endereco.cidade VALUES (2791, '2019-10-23 14:05:31.168533', NULL, 'Aliança', 16);
INSERT INTO endereco.cidade VALUES (2793, '2019-10-23 14:05:31.168533', NULL, 'Amaraji', 16);
INSERT INTO endereco.cidade VALUES (2794, '2019-10-23 14:05:31.168533', NULL, 'Angelim', 16);
INSERT INTO endereco.cidade VALUES (2796, '2019-10-23 14:05:31.168533', NULL, 'Araripina', 16);
INSERT INTO endereco.cidade VALUES (2798, '2019-10-23 14:05:31.168533', NULL, 'Barra De Guabiraba', 16);
INSERT INTO endereco.cidade VALUES (2800, '2019-10-23 14:05:31.168533', NULL, 'Belém De Maria', 16);
INSERT INTO endereco.cidade VALUES (2802, '2019-10-23 14:05:31.168533', NULL, 'Belo Jardim', 16);
INSERT INTO endereco.cidade VALUES (2803, '2019-10-23 14:05:31.168533', NULL, 'Betânia', 16);
INSERT INTO endereco.cidade VALUES (2805, '2019-10-23 14:05:31.168533', NULL, 'Bodocó', 16);
INSERT INTO endereco.cidade VALUES (2807, '2019-10-23 14:05:31.168533', NULL, 'Bom Jardim', 16);
INSERT INTO endereco.cidade VALUES (2808, '2019-10-23 14:05:31.168533', NULL, 'Bonito', 16);
INSERT INTO endereco.cidade VALUES (2810, '2019-10-23 14:05:31.168533', NULL, 'Brejinho', 16);
INSERT INTO endereco.cidade VALUES (2812, '2019-10-23 14:05:31.168533', NULL, 'Buenos Aires', 16);
INSERT INTO endereco.cidade VALUES (2813, '2019-10-23 14:05:31.168533', NULL, 'Buíque', 16);
INSERT INTO endereco.cidade VALUES (2815, '2019-10-23 14:05:31.168533', NULL, 'Cabrobó', 16);
INSERT INTO endereco.cidade VALUES (2817, '2019-10-23 14:05:31.168533', NULL, 'Caetés', 16);
INSERT INTO endereco.cidade VALUES (2818, '2019-10-23 14:05:31.168533', NULL, 'Calçado', 16);
INSERT INTO endereco.cidade VALUES (2820, '2019-10-23 14:05:31.168533', NULL, 'Camaragibe', 16);
INSERT INTO endereco.cidade VALUES (2822, '2019-10-23 14:05:31.168533', NULL, 'Camutanga', 16);
INSERT INTO endereco.cidade VALUES (2823, '2019-10-23 14:05:31.168533', NULL, 'Canhotinho', 16);
INSERT INTO endereco.cidade VALUES (2825, '2019-10-23 14:05:31.168533', NULL, 'Carnaíba', 16);
INSERT INTO endereco.cidade VALUES (2827, '2019-10-23 14:05:31.168533', NULL, 'Carpina', 16);
INSERT INTO endereco.cidade VALUES (2828, '2019-10-23 14:05:31.168533', NULL, 'Caruaru', 16);
INSERT INTO endereco.cidade VALUES (2830, '2019-10-23 14:05:31.168533', NULL, 'Catende', 16);
INSERT INTO endereco.cidade VALUES (2832, '2019-10-23 14:05:31.168533', NULL, 'Chã De Alegria', 16);
INSERT INTO endereco.cidade VALUES (2834, '2019-10-23 14:05:31.168533', NULL, 'Condado', 16);
INSERT INTO endereco.cidade VALUES (2836, '2019-10-23 14:05:31.168533', NULL, 'Cortês', 16);
INSERT INTO endereco.cidade VALUES (2837, '2019-10-23 14:05:31.168533', NULL, 'Cumaru', 16);
INSERT INTO endereco.cidade VALUES (2839, '2019-10-23 14:05:31.168533', NULL, 'Custódia', 16);
INSERT INTO endereco.cidade VALUES (2841, '2019-10-23 14:05:31.168533', NULL, 'Escada', 16);
INSERT INTO endereco.cidade VALUES (2843, '2019-10-23 14:05:31.168533', NULL, 'Feira Nova', 16);
INSERT INTO endereco.cidade VALUES (2845, '2019-10-23 14:05:31.168533', NULL, 'Ferreiros', 16);
INSERT INTO endereco.cidade VALUES (2846, '2019-10-23 14:05:31.168533', NULL, 'Flores', 16);
INSERT INTO endereco.cidade VALUES (2848, '2019-10-23 14:05:31.168533', NULL, 'Frei Miguelinho', 16);
INSERT INTO endereco.cidade VALUES (2850, '2019-10-23 14:05:31.168533', NULL, 'Garanhuns', 16);
INSERT INTO endereco.cidade VALUES (2852, '2019-10-23 14:05:31.168533', NULL, 'Goiana', 16);
INSERT INTO endereco.cidade VALUES (2853, '2019-10-23 14:05:31.168533', NULL, 'Granito', 16);
INSERT INTO endereco.cidade VALUES (2855, '2019-10-23 14:05:31.168533', NULL, 'Iati', 16);
INSERT INTO endereco.cidade VALUES (2857, '2019-10-23 14:05:31.168533', NULL, 'Ibirajuba', 16);
INSERT INTO endereco.cidade VALUES (2859, '2019-10-23 14:05:31.168533', NULL, 'Iguaraci', 16);
INSERT INTO endereco.cidade VALUES (2861, '2019-10-23 14:05:31.168533', NULL, 'Inajá', 16);
INSERT INTO endereco.cidade VALUES (2862, '2019-10-23 14:05:31.168533', NULL, 'Ingazeira', 16);
INSERT INTO endereco.cidade VALUES (2864, '2019-10-23 14:05:31.168533', NULL, 'Ipubi', 16);
INSERT INTO endereco.cidade VALUES (2866, '2019-10-23 14:05:31.168533', NULL, 'Itaíba', 16);
INSERT INTO endereco.cidade VALUES (2868, '2019-10-23 14:05:31.168533', NULL, 'Itapetim', 16);
INSERT INTO endereco.cidade VALUES (2869, '2019-10-23 14:05:31.168533', NULL, 'Itapissuma', 16);
INSERT INTO endereco.cidade VALUES (2871, '2019-10-23 14:05:31.168533', NULL, 'Jaboatão Dos Guararapes', 16);
INSERT INTO endereco.cidade VALUES (2873, '2019-10-23 14:05:31.168533', NULL, 'Jataúba', 16);
INSERT INTO endereco.cidade VALUES (2875, '2019-10-23 14:05:31.168533', NULL, 'João Alfredo', 16);
INSERT INTO endereco.cidade VALUES (2877, '2019-10-23 14:05:31.168533', NULL, 'Jucati', 16);
INSERT INTO endereco.cidade VALUES (2878, '2019-10-23 14:05:31.168533', NULL, 'Jupi', 16);
INSERT INTO endereco.cidade VALUES (2880, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Do Carro', 16);
INSERT INTO endereco.cidade VALUES (2882, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Do Ouro', 16);
INSERT INTO endereco.cidade VALUES (2884, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Grande', 16);
INSERT INTO endereco.cidade VALUES (2885, '2019-10-23 14:05:31.168533', NULL, 'Lajedo', 16);
INSERT INTO endereco.cidade VALUES (2887, '2019-10-23 14:05:31.168533', NULL, 'Macaparana', 16);
INSERT INTO endereco.cidade VALUES (2889, '2019-10-23 14:05:31.168533', NULL, 'Manari', 16);
INSERT INTO endereco.cidade VALUES (2891, '2019-10-23 14:05:31.168533', NULL, 'Mirandiba', 16);
INSERT INTO endereco.cidade VALUES (2893, '2019-10-23 14:05:31.168533', NULL, 'Moreno', 16);
INSERT INTO endereco.cidade VALUES (2894, '2019-10-23 14:05:31.168533', NULL, 'Nazaré Da Mata', 16);
INSERT INTO endereco.cidade VALUES (2896, '2019-10-23 14:05:31.168533', NULL, 'Orobó', 16);
INSERT INTO endereco.cidade VALUES (2898, '2019-10-23 14:05:31.168533', NULL, 'Ouricuri', 16);
INSERT INTO endereco.cidade VALUES (2899, '2019-10-23 14:05:31.168533', NULL, 'Palmares', 16);
INSERT INTO endereco.cidade VALUES (2901, '2019-10-23 14:05:31.168533', NULL, 'Panelas', 16);
INSERT INTO endereco.cidade VALUES (2903, '2019-10-23 14:05:31.168533', NULL, 'Parnamirim', 16);
INSERT INTO endereco.cidade VALUES (2905, '2019-10-23 14:05:31.168533', NULL, 'Paudalho', 16);
INSERT INTO endereco.cidade VALUES (5414, '2019-10-23 14:05:31.168533', NULL, 'Valparaíso', 26);
INSERT INTO endereco.cidade VALUES (2908, '2019-10-23 14:05:31.168533', NULL, 'Pesqueira', 16);
INSERT INTO endereco.cidade VALUES (2909, '2019-10-23 14:05:31.168533', NULL, 'Petrolândia', 16);
INSERT INTO endereco.cidade VALUES (2911, '2019-10-23 14:05:31.168533', NULL, 'Poção', 16);
INSERT INTO endereco.cidade VALUES (2913, '2019-10-23 14:05:31.168533', NULL, 'Primavera', 16);
INSERT INTO endereco.cidade VALUES (2915, '2019-10-23 14:05:31.168533', NULL, 'Quixaba', 16);
INSERT INTO endereco.cidade VALUES (2916, '2019-10-23 14:05:31.168533', NULL, 'Recife', 16);
INSERT INTO endereco.cidade VALUES (2918, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão', 16);
INSERT INTO endereco.cidade VALUES (2920, '2019-10-23 14:05:31.168533', NULL, 'Sairé', 16);
INSERT INTO endereco.cidade VALUES (2922, '2019-10-23 14:05:31.168533', NULL, 'Salgueiro', 16);
INSERT INTO endereco.cidade VALUES (2923, '2019-10-23 14:05:31.168533', NULL, 'Saloá', 16);
INSERT INTO endereco.cidade VALUES (2925, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz', 16);
INSERT INTO endereco.cidade VALUES (2927, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Do Capibaribe', 16);
INSERT INTO endereco.cidade VALUES (2929, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Da Boa Vista', 16);
INSERT INTO endereco.cidade VALUES (2930, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Do Cambucá', 16);
INSERT INTO endereco.cidade VALUES (2932, '2019-10-23 14:05:31.168533', NULL, 'São Benedito Do Sul', 16);
INSERT INTO endereco.cidade VALUES (2934, '2019-10-23 14:05:31.168533', NULL, 'São Caitano', 16);
INSERT INTO endereco.cidade VALUES (2935, '2019-10-23 14:05:31.168533', NULL, 'São João', 16);
INSERT INTO endereco.cidade VALUES (2937, '2019-10-23 14:05:31.168533', NULL, 'São José Da Coroa Grande', 16);
INSERT INTO endereco.cidade VALUES (2939, '2019-10-23 14:05:31.168533', NULL, 'São José Do Egito', 16);
INSERT INTO endereco.cidade VALUES (2941, '2019-10-23 14:05:31.168533', NULL, 'São Vicente Ferrer', 16);
INSERT INTO endereco.cidade VALUES (2942, '2019-10-23 14:05:31.168533', NULL, 'Serra Talhada', 16);
INSERT INTO endereco.cidade VALUES (2944, '2019-10-23 14:05:31.168533', NULL, 'Sertânia', 16);
INSERT INTO endereco.cidade VALUES (2946, '2019-10-23 14:05:31.168533', NULL, 'Solidão', 16);
INSERT INTO endereco.cidade VALUES (2947, '2019-10-23 14:05:31.168533', NULL, 'Surubim', 16);
INSERT INTO endereco.cidade VALUES (2949, '2019-10-23 14:05:31.168533', NULL, 'Tacaimbó', 16);
INSERT INTO endereco.cidade VALUES (2951, '2019-10-23 14:05:31.168533', NULL, 'Tamandaré', 16);
INSERT INTO endereco.cidade VALUES (2953, '2019-10-23 14:05:31.168533', NULL, 'Terezinha', 16);
INSERT INTO endereco.cidade VALUES (2954, '2019-10-23 14:05:31.168533', NULL, 'Terra Nova', 16);
INSERT INTO endereco.cidade VALUES (2956, '2019-10-23 14:05:31.168533', NULL, 'Toritama', 16);
INSERT INTO endereco.cidade VALUES (2958, '2019-10-23 14:05:31.168533', NULL, 'Trindade', 16);
INSERT INTO endereco.cidade VALUES (2960, '2019-10-23 14:05:31.168533', NULL, 'Tupanatinga', 16);
INSERT INTO endereco.cidade VALUES (2961, '2019-10-23 14:05:31.168533', NULL, 'Tuparetama', 16);
INSERT INTO endereco.cidade VALUES (2963, '2019-10-23 14:05:31.168533', NULL, 'Verdejante', 16);
INSERT INTO endereco.cidade VALUES (2965, '2019-10-23 14:05:31.168533', NULL, 'Vertentes', 16);
INSERT INTO endereco.cidade VALUES (2967, '2019-10-23 14:05:31.168533', NULL, 'Vitória De Santo Antão', 16);
INSERT INTO endereco.cidade VALUES (2968, '2019-10-23 14:05:31.168533', NULL, 'Xexéu', 16);
INSERT INTO endereco.cidade VALUES (2970, '2019-10-23 14:05:31.168533', NULL, 'Agricolândia', 17);
INSERT INTO endereco.cidade VALUES (2972, '2019-10-23 14:05:31.168533', NULL, 'Alagoinha Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (2974, '2019-10-23 14:05:31.168533', NULL, 'Alto Longá', 17);
INSERT INTO endereco.cidade VALUES (2975, '2019-10-23 14:05:31.168533', NULL, 'Altos', 17);
INSERT INTO endereco.cidade VALUES (2977, '2019-10-23 14:05:31.168533', NULL, 'Amarante', 17);
INSERT INTO endereco.cidade VALUES (2979, '2019-10-23 14:05:31.168533', NULL, 'Anísio De Abreu', 17);
INSERT INTO endereco.cidade VALUES (2981, '2019-10-23 14:05:31.168533', NULL, 'Aroazes', 17);
INSERT INTO endereco.cidade VALUES (2982, '2019-10-23 14:05:31.168533', NULL, 'Aroeiras Do Itaim', 17);
INSERT INTO endereco.cidade VALUES (2984, '2019-10-23 14:05:31.168533', NULL, 'Assunção Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (2985, '2019-10-23 14:05:31.168533', NULL, 'Avelino Lopes', 17);
INSERT INTO endereco.cidade VALUES (2987, '2019-10-23 14:05:31.168533', NULL, 'Barra D`Alcântara', 17);
INSERT INTO endereco.cidade VALUES (2989, '2019-10-23 14:05:31.168533', NULL, 'Barreiras Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (2991, '2019-10-23 14:05:31.168533', NULL, 'Batalha', 17);
INSERT INTO endereco.cidade VALUES (2993, '2019-10-23 14:05:31.168533', NULL, 'Belém Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (2994, '2019-10-23 14:05:31.168533', NULL, 'Beneditinos', 17);
INSERT INTO endereco.cidade VALUES (2996, '2019-10-23 14:05:31.168533', NULL, 'Betânia Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (2997, '2019-10-23 14:05:31.168533', NULL, 'Boa Hora', 17);
INSERT INTO endereco.cidade VALUES (2999, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus', 17);
INSERT INTO endereco.cidade VALUES (3001, '2019-10-23 14:05:31.168533', NULL, 'Bonfim Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3003, '2019-10-23 14:05:31.168533', NULL, 'Brasileira', 17);
INSERT INTO endereco.cidade VALUES (3004, '2019-10-23 14:05:31.168533', NULL, 'Brejo Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3006, '2019-10-23 14:05:31.168533', NULL, 'Buriti Dos Montes', 17);
INSERT INTO endereco.cidade VALUES (3008, '2019-10-23 14:05:31.168533', NULL, 'Cajazeiras Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3009, '2019-10-23 14:05:31.168533', NULL, 'Cajueiro Da Praia', 17);
INSERT INTO endereco.cidade VALUES (3011, '2019-10-23 14:05:31.168533', NULL, 'Campinas Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3012, '2019-10-23 14:05:31.168533', NULL, 'Campo Alegre Do Fidalgo', 17);
INSERT INTO endereco.cidade VALUES (3014, '2019-10-23 14:05:31.168533', NULL, 'Campo Largo Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3015, '2019-10-23 14:05:31.168533', NULL, 'Campo Maior', 17);
INSERT INTO endereco.cidade VALUES (3018, '2019-10-23 14:05:31.168533', NULL, 'Capitão De Campos', 17);
INSERT INTO endereco.cidade VALUES (3019, '2019-10-23 14:05:31.168533', NULL, 'Capitão Gervásio Oliveira', 17);
INSERT INTO endereco.cidade VALUES (3021, '2019-10-23 14:05:31.168533', NULL, 'Caraúbas Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3023, '2019-10-23 14:05:31.168533', NULL, 'Castelo Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3024, '2019-10-23 14:05:31.168533', NULL, 'Caxingó', 17);
INSERT INTO endereco.cidade VALUES (3026, '2019-10-23 14:05:31.168533', NULL, 'Cocal De Telha', 17);
INSERT INTO endereco.cidade VALUES (3028, '2019-10-23 14:05:31.168533', NULL, 'Coivaras', 17);
INSERT INTO endereco.cidade VALUES (5416, '2019-10-23 14:05:31.168533', NULL, 'Vargem Grande Do Sul', 26);
INSERT INTO endereco.cidade VALUES (3032, '2019-10-23 14:05:31.168533', NULL, 'Coronel José Dias', 17);
INSERT INTO endereco.cidade VALUES (3033, '2019-10-23 14:05:31.168533', NULL, 'Corrente', 17);
INSERT INTO endereco.cidade VALUES (3035, '2019-10-23 14:05:31.168533', NULL, 'Cristino Castro', 17);
INSERT INTO endereco.cidade VALUES (3037, '2019-10-23 14:05:31.168533', NULL, 'Currais', 17);
INSERT INTO endereco.cidade VALUES (3039, '2019-10-23 14:05:31.168533', NULL, 'Curralinhos', 17);
INSERT INTO endereco.cidade VALUES (3040, '2019-10-23 14:05:31.168533', NULL, 'Demerval Lobão', 17);
INSERT INTO endereco.cidade VALUES (3042, '2019-10-23 14:05:31.168533', NULL, 'Dom Expedito Lopes', 17);
INSERT INTO endereco.cidade VALUES (3044, '2019-10-23 14:05:31.168533', NULL, 'Domingos Mourão', 17);
INSERT INTO endereco.cidade VALUES (3046, '2019-10-23 14:05:31.168533', NULL, 'Eliseu Martins', 17);
INSERT INTO endereco.cidade VALUES (3047, '2019-10-23 14:05:31.168533', NULL, 'Esperantina', 17);
INSERT INTO endereco.cidade VALUES (3049, '2019-10-23 14:05:31.168533', NULL, 'Flores Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3051, '2019-10-23 14:05:31.168533', NULL, 'Floriano', 17);
INSERT INTO endereco.cidade VALUES (3053, '2019-10-23 14:05:31.168533', NULL, 'Francisco Ayres', 17);
INSERT INTO endereco.cidade VALUES (3054, '2019-10-23 14:05:31.168533', NULL, 'Francisco Macedo', 17);
INSERT INTO endereco.cidade VALUES (3056, '2019-10-23 14:05:31.168533', NULL, 'Fronteiras', 17);
INSERT INTO endereco.cidade VALUES (3058, '2019-10-23 14:05:31.168533', NULL, 'Gilbués', 17);
INSERT INTO endereco.cidade VALUES (3059, '2019-10-23 14:05:31.168533', NULL, 'Guadalupe', 17);
INSERT INTO endereco.cidade VALUES (3061, '2019-10-23 14:05:31.168533', NULL, 'Hugo Napoleão', 17);
INSERT INTO endereco.cidade VALUES (3063, '2019-10-23 14:05:31.168533', NULL, 'Inhuma', 17);
INSERT INTO endereco.cidade VALUES (3065, '2019-10-23 14:05:31.168533', NULL, 'Isaías Coelho', 17);
INSERT INTO endereco.cidade VALUES (3067, '2019-10-23 14:05:31.168533', NULL, 'Itaueira', 17);
INSERT INTO endereco.cidade VALUES (3068, '2019-10-23 14:05:31.168533', NULL, 'Jacobina Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3070, '2019-10-23 14:05:31.168533', NULL, 'Jardim Do Mulato', 17);
INSERT INTO endereco.cidade VALUES (3072, '2019-10-23 14:05:31.168533', NULL, 'Jerumenha', 17);
INSERT INTO endereco.cidade VALUES (3073, '2019-10-23 14:05:31.168533', NULL, 'João Costa', 17);
INSERT INTO endereco.cidade VALUES (3075, '2019-10-23 14:05:31.168533', NULL, 'Joca Marques', 17);
INSERT INTO endereco.cidade VALUES (3077, '2019-10-23 14:05:31.168533', NULL, 'Juazeiro Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3079, '2019-10-23 14:05:31.168533', NULL, 'Jurema', 17);
INSERT INTO endereco.cidade VALUES (3080, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Alegre', 17);
INSERT INTO endereco.cidade VALUES (3082, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Do Barro Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3084, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Do Sítio', 17);
INSERT INTO endereco.cidade VALUES (3085, '2019-10-23 14:05:31.168533', NULL, 'Lagoinha Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3087, '2019-10-23 14:05:31.168533', NULL, 'Luís Correia', 17);
INSERT INTO endereco.cidade VALUES (3089, '2019-10-23 14:05:31.168533', NULL, 'Madeiro', 17);
INSERT INTO endereco.cidade VALUES (3091, '2019-10-23 14:05:31.168533', NULL, 'Marcolândia', 17);
INSERT INTO endereco.cidade VALUES (3092, '2019-10-23 14:05:31.168533', NULL, 'Marcos Parente', 17);
INSERT INTO endereco.cidade VALUES (3094, '2019-10-23 14:05:31.168533', NULL, 'Matias Olímpio', 17);
INSERT INTO endereco.cidade VALUES (3096, '2019-10-23 14:05:31.168533', NULL, 'Miguel Leão', 17);
INSERT INTO endereco.cidade VALUES (3098, '2019-10-23 14:05:31.168533', NULL, 'Monsenhor Gil', 17);
INSERT INTO endereco.cidade VALUES (3099, '2019-10-23 14:05:31.168533', NULL, 'Monsenhor Hipólito', 17);
INSERT INTO endereco.cidade VALUES (3101, '2019-10-23 14:05:31.168533', NULL, 'Morro Cabeça No Tempo', 17);
INSERT INTO endereco.cidade VALUES (3103, '2019-10-23 14:05:31.168533', NULL, 'Murici Dos Portelas', 17);
INSERT INTO endereco.cidade VALUES (3104, '2019-10-23 14:05:31.168533', NULL, 'Nazaré Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3106, '2019-10-23 14:05:31.168533', NULL, 'Nossa Senhora De Nazaré', 17);
INSERT INTO endereco.cidade VALUES (3108, '2019-10-23 14:05:31.168533', NULL, 'Nova Santa Rita', 17);
INSERT INTO endereco.cidade VALUES (3109, '2019-10-23 14:05:31.168533', NULL, 'Novo Oriente Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3111, '2019-10-23 14:05:31.168533', NULL, 'Oeiras', 17);
INSERT INTO endereco.cidade VALUES (3113, '2019-10-23 14:05:31.168533', NULL, 'Padre Marcos', 17);
INSERT INTO endereco.cidade VALUES (3114, '2019-10-23 14:05:31.168533', NULL, 'Paes Landim', 17);
INSERT INTO endereco.cidade VALUES (3116, '2019-10-23 14:05:31.168533', NULL, 'Palmeira Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3117, '2019-10-23 14:05:31.168533', NULL, 'Palmeirais', 17);
INSERT INTO endereco.cidade VALUES (3119, '2019-10-23 14:05:31.168533', NULL, 'Parnaguá', 17);
INSERT INTO endereco.cidade VALUES (3121, '2019-10-23 14:05:31.168533', NULL, 'Passagem Franca Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3123, '2019-10-23 14:05:31.168533', NULL, 'Pau D`Arco Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3125, '2019-10-23 14:05:31.168533', NULL, 'Pavussu', 17);
INSERT INTO endereco.cidade VALUES (3126, '2019-10-23 14:05:31.168533', NULL, 'Pedro Ii', 17);
INSERT INTO endereco.cidade VALUES (3128, '2019-10-23 14:05:31.168533', NULL, 'Picos', 17);
INSERT INTO endereco.cidade VALUES (3130, '2019-10-23 14:05:31.168533', NULL, 'Pio Ix', 17);
INSERT INTO endereco.cidade VALUES (3132, '2019-10-23 14:05:31.168533', NULL, 'Piripiri', 17);
INSERT INTO endereco.cidade VALUES (3133, '2019-10-23 14:05:31.168533', NULL, 'Porto', 17);
INSERT INTO endereco.cidade VALUES (3135, '2019-10-23 14:05:31.168533', NULL, 'Prata Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3137, '2019-10-23 14:05:31.168533', NULL, 'Redenção Do Gurguéia', 17);
INSERT INTO endereco.cidade VALUES (3139, '2019-10-23 14:05:31.168533', NULL, 'Riacho Frio', 17);
INSERT INTO endereco.cidade VALUES (3140, '2019-10-23 14:05:31.168533', NULL, 'Ribeira Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3142, '2019-10-23 14:05:31.168533', NULL, 'Rio Grande Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3144, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Dos Milagres', 17);
INSERT INTO endereco.cidade VALUES (3145, '2019-10-23 14:05:31.168533', NULL, 'Santa Filomena', 17);
INSERT INTO endereco.cidade VALUES (3147, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3149, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio De Lisboa', 17);
INSERT INTO endereco.cidade VALUES (3273, '2019-10-23 14:05:31.168533', NULL, 'Colorado', 18);
INSERT INTO endereco.cidade VALUES (3152, '2019-10-23 14:05:31.168533', NULL, 'São Braz Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3154, '2019-10-23 14:05:31.168533', NULL, 'São Francisco De Assis Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3156, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Do Gurguéia', 17);
INSERT INTO endereco.cidade VALUES (3158, '2019-10-23 14:05:31.168533', NULL, 'São João Da Canabrava', 17);
INSERT INTO endereco.cidade VALUES (3159, '2019-10-23 14:05:31.168533', NULL, 'São João Da Fronteira', 17);
INSERT INTO endereco.cidade VALUES (3161, '2019-10-23 14:05:31.168533', NULL, 'São João Da Varjota', 17);
INSERT INTO endereco.cidade VALUES (3163, '2019-10-23 14:05:31.168533', NULL, 'São João Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3164, '2019-10-23 14:05:31.168533', NULL, 'São José Do Divino', 17);
INSERT INTO endereco.cidade VALUES (3166, '2019-10-23 14:05:31.168533', NULL, 'São José Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3167, '2019-10-23 14:05:31.168533', NULL, 'São Julião', 17);
INSERT INTO endereco.cidade VALUES (3169, '2019-10-23 14:05:31.168533', NULL, 'São Luis Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3171, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Fidalgo', 17);
INSERT INTO endereco.cidade VALUES (3172, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Tapuio', 17);
INSERT INTO endereco.cidade VALUES (3174, '2019-10-23 14:05:31.168533', NULL, 'São Raimundo Nonato', 17);
INSERT INTO endereco.cidade VALUES (3176, '2019-10-23 14:05:31.168533', NULL, 'Sebastião Leal', 17);
INSERT INTO endereco.cidade VALUES (3177, '2019-10-23 14:05:31.168533', NULL, 'Sigefredo Pacheco', 17);
INSERT INTO endereco.cidade VALUES (3179, '2019-10-23 14:05:31.168533', NULL, 'Simplício Mendes', 17);
INSERT INTO endereco.cidade VALUES (3181, '2019-10-23 14:05:31.168533', NULL, 'Sussuapara', 17);
INSERT INTO endereco.cidade VALUES (3183, '2019-10-23 14:05:31.168533', NULL, 'Tanque Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3184, '2019-10-23 14:05:31.168533', NULL, 'Teresina', 17);
INSERT INTO endereco.cidade VALUES (3186, '2019-10-23 14:05:31.168533', NULL, 'Uruçuí', 17);
INSERT INTO endereco.cidade VALUES (3188, '2019-10-23 14:05:31.168533', NULL, 'Várzea Branca', 17);
INSERT INTO endereco.cidade VALUES (3190, '2019-10-23 14:05:31.168533', NULL, 'Vera Mendes', 17);
INSERT INTO endereco.cidade VALUES (3191, '2019-10-23 14:05:31.168533', NULL, 'Vila Nova Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3193, '2019-10-23 14:05:31.168533', NULL, 'Abatiá', 18);
INSERT INTO endereco.cidade VALUES (3195, '2019-10-23 14:05:31.168533', NULL, 'Agudos Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3197, '2019-10-23 14:05:31.168533', NULL, 'Altamira Do Paraná', 18);
INSERT INTO endereco.cidade VALUES (3198, '2019-10-23 14:05:31.168533', NULL, 'Alto Paraíso', 18);
INSERT INTO endereco.cidade VALUES (3200, '2019-10-23 14:05:31.168533', NULL, 'Alto Piquiri', 18);
INSERT INTO endereco.cidade VALUES (3201, '2019-10-23 14:05:31.168533', NULL, 'Altônia', 18);
INSERT INTO endereco.cidade VALUES (3203, '2019-10-23 14:05:31.168533', NULL, 'Amaporã', 18);
INSERT INTO endereco.cidade VALUES (3205, '2019-10-23 14:05:31.168533', NULL, 'Anahy', 18);
INSERT INTO endereco.cidade VALUES (3207, '2019-10-23 14:05:31.168533', NULL, 'Ângulo', 18);
INSERT INTO endereco.cidade VALUES (3208, '2019-10-23 14:05:31.168533', NULL, 'Antonina', 18);
INSERT INTO endereco.cidade VALUES (3210, '2019-10-23 14:05:31.168533', NULL, 'Apucarana', 18);
INSERT INTO endereco.cidade VALUES (3212, '2019-10-23 14:05:31.168533', NULL, 'Arapoti', 18);
INSERT INTO endereco.cidade VALUES (3214, '2019-10-23 14:05:31.168533', NULL, 'Araruna', 18);
INSERT INTO endereco.cidade VALUES (3215, '2019-10-23 14:05:31.168533', NULL, 'Araucária', 18);
INSERT INTO endereco.cidade VALUES (3217, '2019-10-23 14:05:31.168533', NULL, 'Assaí', 18);
INSERT INTO endereco.cidade VALUES (3219, '2019-10-23 14:05:31.168533', NULL, 'Astorga', 18);
INSERT INTO endereco.cidade VALUES (3221, '2019-10-23 14:05:31.168533', NULL, 'Balsa Nova', 18);
INSERT INTO endereco.cidade VALUES (3222, '2019-10-23 14:05:31.168533', NULL, 'Bandeirantes', 18);
INSERT INTO endereco.cidade VALUES (3224, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Jacaré', 18);
INSERT INTO endereco.cidade VALUES (3226, '2019-10-23 14:05:31.168533', NULL, 'Bela Vista Da Caroba', 18);
INSERT INTO endereco.cidade VALUES (3228, '2019-10-23 14:05:31.168533', NULL, 'Bituruna', 18);
INSERT INTO endereco.cidade VALUES (3229, '2019-10-23 14:05:31.168533', NULL, 'Boa Esperança', 18);
INSERT INTO endereco.cidade VALUES (3231, '2019-10-23 14:05:31.168533', NULL, 'Boa Ventura De São Roque', 18);
INSERT INTO endereco.cidade VALUES (3233, '2019-10-23 14:05:31.168533', NULL, 'Bocaiúva Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3234, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3236, '2019-10-23 14:05:31.168533', NULL, 'Bom Sucesso Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3238, '2019-10-23 14:05:31.168533', NULL, 'Braganey', 18);
INSERT INTO endereco.cidade VALUES (3239, '2019-10-23 14:05:31.168533', NULL, 'Brasilândia Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3241, '2019-10-23 14:05:31.168533', NULL, 'Cafelândia', 18);
INSERT INTO endereco.cidade VALUES (3243, '2019-10-23 14:05:31.168533', NULL, 'Califórnia', 18);
INSERT INTO endereco.cidade VALUES (3245, '2019-10-23 14:05:31.168533', NULL, 'Cambé', 18);
INSERT INTO endereco.cidade VALUES (3246, '2019-10-23 14:05:31.168533', NULL, 'Cambira', 18);
INSERT INTO endereco.cidade VALUES (3248, '2019-10-23 14:05:31.168533', NULL, 'Campina Do Simão', 18);
INSERT INTO endereco.cidade VALUES (3250, '2019-10-23 14:05:31.168533', NULL, 'Campo Bonito', 18);
INSERT INTO endereco.cidade VALUES (3252, '2019-10-23 14:05:31.168533', NULL, 'Campo Largo', 18);
INSERT INTO endereco.cidade VALUES (3253, '2019-10-23 14:05:31.168533', NULL, 'Campo Magro', 18);
INSERT INTO endereco.cidade VALUES (3255, '2019-10-23 14:05:31.168533', NULL, 'Cândido De Abreu', 18);
INSERT INTO endereco.cidade VALUES (3257, '2019-10-23 14:05:31.168533', NULL, 'Cantagalo', 18);
INSERT INTO endereco.cidade VALUES (3258, '2019-10-23 14:05:31.168533', NULL, 'Capanema', 18);
INSERT INTO endereco.cidade VALUES (3260, '2019-10-23 14:05:31.168533', NULL, 'Carambeí', 18);
INSERT INTO endereco.cidade VALUES (3262, '2019-10-23 14:05:31.168533', NULL, 'Cascavel', 18);
INSERT INTO endereco.cidade VALUES (3263, '2019-10-23 14:05:31.168533', NULL, 'Castro', 18);
INSERT INTO endereco.cidade VALUES (3265, '2019-10-23 14:05:31.168533', NULL, 'Centenário Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3267, '2019-10-23 14:05:31.168533', NULL, 'Céu Azul', 18);
INSERT INTO endereco.cidade VALUES (3269, '2019-10-23 14:05:31.168533', NULL, 'Cianorte', 18);
INSERT INTO endereco.cidade VALUES (3271, '2019-10-23 14:05:31.168533', NULL, 'Clevelândia', 18);
INSERT INTO endereco.cidade VALUES (3272, '2019-10-23 14:05:31.168533', NULL, 'Colombo', 18);
INSERT INTO endereco.cidade VALUES (5419, '2019-10-23 14:05:31.168533', NULL, 'Vera Cruz', 26);
INSERT INTO endereco.cidade VALUES (3276, '2019-10-23 14:05:31.168533', NULL, 'Contenda', 18);
INSERT INTO endereco.cidade VALUES (3278, '2019-10-23 14:05:31.168533', NULL, 'Cornélio Procópio', 18);
INSERT INTO endereco.cidade VALUES (3280, '2019-10-23 14:05:31.168533', NULL, 'Coronel Vivida', 18);
INSERT INTO endereco.cidade VALUES (3281, '2019-10-23 14:05:31.168533', NULL, 'Corumbataí Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3283, '2019-10-23 14:05:31.168533', NULL, 'Cruzeiro Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3285, '2019-10-23 14:05:31.168533', NULL, 'Cruzeiro Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3286, '2019-10-23 14:05:31.168533', NULL, 'Cruzmaltina', 18);
INSERT INTO endereco.cidade VALUES (3288, '2019-10-23 14:05:31.168533', NULL, 'Curiúva', 18);
INSERT INTO endereco.cidade VALUES (3290, '2019-10-23 14:05:31.168533', NULL, 'Diamante Do Norte', 18);
INSERT INTO endereco.cidade VALUES (3292, '2019-10-23 14:05:31.168533', NULL, 'Dois Vizinhos', 18);
INSERT INTO endereco.cidade VALUES (3293, '2019-10-23 14:05:31.168533', NULL, 'Douradina', 18);
INSERT INTO endereco.cidade VALUES (3295, '2019-10-23 14:05:31.168533', NULL, 'Doutor Ulysses', 18);
INSERT INTO endereco.cidade VALUES (3297, '2019-10-23 14:05:31.168533', NULL, 'Engenheiro Beltrão', 18);
INSERT INTO endereco.cidade VALUES (3298, '2019-10-23 14:05:31.168533', NULL, 'Entre Rios Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3300, '2019-10-23 14:05:31.168533', NULL, 'Espigão Alto Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3302, '2019-10-23 14:05:31.168533', NULL, 'Faxinal', 18);
INSERT INTO endereco.cidade VALUES (3304, '2019-10-23 14:05:31.168533', NULL, 'Fênix', 18);
INSERT INTO endereco.cidade VALUES (3305, '2019-10-23 14:05:31.168533', NULL, 'Fernandes Pinheiro', 18);
INSERT INTO endereco.cidade VALUES (3307, '2019-10-23 14:05:31.168533', NULL, 'Flor Da Serra Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3309, '2019-10-23 14:05:31.168533', NULL, 'Floresta', 18);
INSERT INTO endereco.cidade VALUES (3311, '2019-10-23 14:05:31.168533', NULL, 'Flórida', 18);
INSERT INTO endereco.cidade VALUES (3313, '2019-10-23 14:05:31.168533', NULL, 'Foz Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3314, '2019-10-23 14:05:31.168533', NULL, 'Foz Do Jordão', 18);
INSERT INTO endereco.cidade VALUES (3316, '2019-10-23 14:05:31.168533', NULL, 'Francisco Beltrão', 18);
INSERT INTO endereco.cidade VALUES (3318, '2019-10-23 14:05:31.168533', NULL, 'Godoy Moreira', 18);
INSERT INTO endereco.cidade VALUES (3319, '2019-10-23 14:05:31.168533', NULL, 'Goioerê', 18);
INSERT INTO endereco.cidade VALUES (3321, '2019-10-23 14:05:31.168533', NULL, 'Grandes Rios', 18);
INSERT INTO endereco.cidade VALUES (3323, '2019-10-23 14:05:31.168533', NULL, 'Guairaçá', 18);
INSERT INTO endereco.cidade VALUES (3325, '2019-10-23 14:05:31.168533', NULL, 'Guapirama', 18);
INSERT INTO endereco.cidade VALUES (3326, '2019-10-23 14:05:31.168533', NULL, 'Guaporema', 18);
INSERT INTO endereco.cidade VALUES (3328, '2019-10-23 14:05:31.168533', NULL, 'Guaraniaçu', 18);
INSERT INTO endereco.cidade VALUES (3330, '2019-10-23 14:05:31.168533', NULL, 'Guaraqueçaba', 18);
INSERT INTO endereco.cidade VALUES (3332, '2019-10-23 14:05:31.168533', NULL, 'Honório Serpa', 18);
INSERT INTO endereco.cidade VALUES (3334, '2019-10-23 14:05:31.168533', NULL, 'Ibema', 18);
INSERT INTO endereco.cidade VALUES (3335, '2019-10-23 14:05:31.168533', NULL, 'Ibiporã', 18);
INSERT INTO endereco.cidade VALUES (3337, '2019-10-23 14:05:31.168533', NULL, 'Iguaraçu', 18);
INSERT INTO endereco.cidade VALUES (3339, '2019-10-23 14:05:31.168533', NULL, 'Imbaú', 18);
INSERT INTO endereco.cidade VALUES (3341, '2019-10-23 14:05:31.168533', NULL, 'Inácio Martins', 18);
INSERT INTO endereco.cidade VALUES (3343, '2019-10-23 14:05:31.168533', NULL, 'Indianópolis', 18);
INSERT INTO endereco.cidade VALUES (3344, '2019-10-23 14:05:31.168533', NULL, 'Ipiranga', 18);
INSERT INTO endereco.cidade VALUES (3346, '2019-10-23 14:05:31.168533', NULL, 'Iracema Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3348, '2019-10-23 14:05:31.168533', NULL, 'Iretama', 18);
INSERT INTO endereco.cidade VALUES (3350, '2019-10-23 14:05:31.168533', NULL, 'Itaipulândia', 18);
INSERT INTO endereco.cidade VALUES (3352, '2019-10-23 14:05:31.168533', NULL, 'Itambé', 18);
INSERT INTO endereco.cidade VALUES (3354, '2019-10-23 14:05:31.168533', NULL, 'Itaperuçu', 18);
INSERT INTO endereco.cidade VALUES (3355, '2019-10-23 14:05:31.168533', NULL, 'Itaúna Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3357, '2019-10-23 14:05:31.168533', NULL, 'Ivaiporã', 18);
INSERT INTO endereco.cidade VALUES (3359, '2019-10-23 14:05:31.168533', NULL, 'Ivatuba', 18);
INSERT INTO endereco.cidade VALUES (3360, '2019-10-23 14:05:31.168533', NULL, 'Jaboti', 18);
INSERT INTO endereco.cidade VALUES (3362, '2019-10-23 14:05:31.168533', NULL, 'Jaguapitã', 18);
INSERT INTO endereco.cidade VALUES (3364, '2019-10-23 14:05:31.168533', NULL, 'Jandaia Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3366, '2019-10-23 14:05:31.168533', NULL, 'Japira', 18);
INSERT INTO endereco.cidade VALUES (3368, '2019-10-23 14:05:31.168533', NULL, 'Jardim Alegre', 18);
INSERT INTO endereco.cidade VALUES (3370, '2019-10-23 14:05:31.168533', NULL, 'Jataizinho', 18);
INSERT INTO endereco.cidade VALUES (3371, '2019-10-23 14:05:31.168533', NULL, 'Jesuítas', 18);
INSERT INTO endereco.cidade VALUES (3373, '2019-10-23 14:05:31.168533', NULL, 'Jundiaí Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3375, '2019-10-23 14:05:31.168533', NULL, 'Jussara', 18);
INSERT INTO endereco.cidade VALUES (3376, '2019-10-23 14:05:31.168533', NULL, 'Kaloré', 18);
INSERT INTO endereco.cidade VALUES (3378, '2019-10-23 14:05:31.168533', NULL, 'Laranjal', 18);
INSERT INTO endereco.cidade VALUES (3380, '2019-10-23 14:05:31.168533', NULL, 'Leópolis', 18);
INSERT INTO endereco.cidade VALUES (3382, '2019-10-23 14:05:31.168533', NULL, 'Lindoeste', 18);
INSERT INTO endereco.cidade VALUES (3383, '2019-10-23 14:05:31.168533', NULL, 'Loanda', 18);
INSERT INTO endereco.cidade VALUES (3385, '2019-10-23 14:05:31.168533', NULL, 'Londrina', 18);
INSERT INTO endereco.cidade VALUES (3387, '2019-10-23 14:05:31.168533', NULL, 'Lunardelli', 18);
INSERT INTO endereco.cidade VALUES (3389, '2019-10-23 14:05:31.168533', NULL, 'Mallet', 18);
INSERT INTO endereco.cidade VALUES (3390, '2019-10-23 14:05:31.168533', NULL, 'Mamborê', 18);
INSERT INTO endereco.cidade VALUES (3392, '2019-10-23 14:05:31.168533', NULL, 'Mandaguari', 18);
INSERT INTO endereco.cidade VALUES (3394, '2019-10-23 14:05:31.168533', NULL, 'Manfrinópolis', 18);
INSERT INTO endereco.cidade VALUES (3396, '2019-10-23 14:05:31.168533', NULL, 'Manoel Ribas', 18);
INSERT INTO endereco.cidade VALUES (3398, '2019-10-23 14:05:31.168533', NULL, 'Maria Helena', 18);
INSERT INTO endereco.cidade VALUES (3399, '2019-10-23 14:05:31.168533', NULL, 'Marialva', 18);
INSERT INTO endereco.cidade VALUES (3401, '2019-10-23 14:05:31.168533', NULL, 'Marilena', 18);
INSERT INTO endereco.cidade VALUES (5556, '2019-10-23 14:05:31.168533', NULL, 'Sucupira', 27);
INSERT INTO endereco.cidade VALUES (3404, '2019-10-23 14:05:31.168533', NULL, 'Mariópolis', 18);
INSERT INTO endereco.cidade VALUES (3406, '2019-10-23 14:05:31.168533', NULL, 'Marmeleiro', 18);
INSERT INTO endereco.cidade VALUES (3408, '2019-10-23 14:05:31.168533', NULL, 'Marumbi', 18);
INSERT INTO endereco.cidade VALUES (3409, '2019-10-23 14:05:31.168533', NULL, 'Matelândia', 18);
INSERT INTO endereco.cidade VALUES (3411, '2019-10-23 14:05:31.168533', NULL, 'Mato Rico', 18);
INSERT INTO endereco.cidade VALUES (3413, '2019-10-23 14:05:31.168533', NULL, 'Medianeira', 18);
INSERT INTO endereco.cidade VALUES (3415, '2019-10-23 14:05:31.168533', NULL, 'Mirador', 18);
INSERT INTO endereco.cidade VALUES (3416, '2019-10-23 14:05:31.168533', NULL, 'Miraselva', 18);
INSERT INTO endereco.cidade VALUES (3418, '2019-10-23 14:05:31.168533', NULL, 'Moreira Sales', 18);
INSERT INTO endereco.cidade VALUES (3420, '2019-10-23 14:05:31.168533', NULL, 'Munhoz De Melo', 18);
INSERT INTO endereco.cidade VALUES (3422, '2019-10-23 14:05:31.168533', NULL, 'Nova Aliança Do Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3424, '2019-10-23 14:05:31.168533', NULL, 'Nova Aurora', 18);
INSERT INTO endereco.cidade VALUES (3425, '2019-10-23 14:05:31.168533', NULL, 'Nova Cantu', 18);
INSERT INTO endereco.cidade VALUES (3427, '2019-10-23 14:05:31.168533', NULL, 'Nova Esperança Do Sudoeste', 18);
INSERT INTO endereco.cidade VALUES (3429, '2019-10-23 14:05:31.168533', NULL, 'Nova Laranjeiras', 18);
INSERT INTO endereco.cidade VALUES (3431, '2019-10-23 14:05:31.168533', NULL, 'Nova Olímpia', 18);
INSERT INTO endereco.cidade VALUES (3432, '2019-10-23 14:05:31.168533', NULL, 'Nova Prata Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3434, '2019-10-23 14:05:31.168533', NULL, 'Nova Santa Rosa', 18);
INSERT INTO endereco.cidade VALUES (3435, '2019-10-23 14:05:31.168533', NULL, 'Nova Tebas', 18);
INSERT INTO endereco.cidade VALUES (3437, '2019-10-23 14:05:31.168533', NULL, 'Ortigueira', 18);
INSERT INTO endereco.cidade VALUES (3439, '2019-10-23 14:05:31.168533', NULL, 'Ouro Verde Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3441, '2019-10-23 14:05:31.168533', NULL, 'Palmas', 18);
INSERT INTO endereco.cidade VALUES (3442, '2019-10-23 14:05:31.168533', NULL, 'Palmeira', 18);
INSERT INTO endereco.cidade VALUES (3444, '2019-10-23 14:05:31.168533', NULL, 'Palotina', 18);
INSERT INTO endereco.cidade VALUES (3446, '2019-10-23 14:05:31.168533', NULL, 'Paranacity', 18);
INSERT INTO endereco.cidade VALUES (3448, '2019-10-23 14:05:31.168533', NULL, 'Paranapoema', 18);
INSERT INTO endereco.cidade VALUES (3449, '2019-10-23 14:05:31.168533', NULL, 'Paranavaí', 18);
INSERT INTO endereco.cidade VALUES (3451, '2019-10-23 14:05:31.168533', NULL, 'Pato Branco', 18);
INSERT INTO endereco.cidade VALUES (3453, '2019-10-23 14:05:31.168533', NULL, 'Paulo Frontin', 18);
INSERT INTO endereco.cidade VALUES (3455, '2019-10-23 14:05:31.168533', NULL, 'Perobal', 18);
INSERT INTO endereco.cidade VALUES (3456, '2019-10-23 14:05:31.168533', NULL, 'Pérola', 18);
INSERT INTO endereco.cidade VALUES (3458, '2019-10-23 14:05:31.168533', NULL, 'Piên', 18);
INSERT INTO endereco.cidade VALUES (3460, '2019-10-23 14:05:31.168533', NULL, 'Pinhal De São Bento', 18);
INSERT INTO endereco.cidade VALUES (3462, '2019-10-23 14:05:31.168533', NULL, 'Pinhão', 18);
INSERT INTO endereco.cidade VALUES (3463, '2019-10-23 14:05:31.168533', NULL, 'Piraí Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3465, '2019-10-23 14:05:31.168533', NULL, 'Pitanga', 18);
INSERT INTO endereco.cidade VALUES (3467, '2019-10-23 14:05:31.168533', NULL, 'Planaltina Do Paraná', 18);
INSERT INTO endereco.cidade VALUES (3469, '2019-10-23 14:05:31.168533', NULL, 'Ponta Grossa', 18);
INSERT INTO endereco.cidade VALUES (3470, '2019-10-23 14:05:31.168533', NULL, 'Pontal Do Paraná', 18);
INSERT INTO endereco.cidade VALUES (3472, '2019-10-23 14:05:31.168533', NULL, 'Porto Amazonas', 18);
INSERT INTO endereco.cidade VALUES (3474, '2019-10-23 14:05:31.168533', NULL, 'Porto Rico', 18);
INSERT INTO endereco.cidade VALUES (3476, '2019-10-23 14:05:31.168533', NULL, 'Prado Ferreira', 18);
INSERT INTO endereco.cidade VALUES (3477, '2019-10-23 14:05:31.168533', NULL, 'Pranchita', 18);
INSERT INTO endereco.cidade VALUES (3479, '2019-10-23 14:05:31.168533', NULL, 'Primeiro De Maio', 18);
INSERT INTO endereco.cidade VALUES (3481, '2019-10-23 14:05:31.168533', NULL, 'Quarto Centenário', 18);
INSERT INTO endereco.cidade VALUES (3483, '2019-10-23 14:05:31.168533', NULL, 'Quatro Barras', 18);
INSERT INTO endereco.cidade VALUES (3485, '2019-10-23 14:05:31.168533', NULL, 'Quedas Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3486, '2019-10-23 14:05:31.168533', NULL, 'Querência Do Norte', 18);
INSERT INTO endereco.cidade VALUES (3488, '2019-10-23 14:05:31.168533', NULL, 'Quitandinha', 18);
INSERT INTO endereco.cidade VALUES (3490, '2019-10-23 14:05:31.168533', NULL, 'Rancho Alegre', 18);
INSERT INTO endereco.cidade VALUES (3492, '2019-10-23 14:05:31.168533', NULL, 'Realeza', 18);
INSERT INTO endereco.cidade VALUES (3493, '2019-10-23 14:05:31.168533', NULL, 'Rebouças', 18);
INSERT INTO endereco.cidade VALUES (3495, '2019-10-23 14:05:31.168533', NULL, 'Reserva', 18);
INSERT INTO endereco.cidade VALUES (3497, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Claro', 18);
INSERT INTO endereco.cidade VALUES (3499, '2019-10-23 14:05:31.168533', NULL, 'Rio Azul', 18);
INSERT INTO endereco.cidade VALUES (3500, '2019-10-23 14:05:31.168533', NULL, 'Rio Bom', 18);
INSERT INTO endereco.cidade VALUES (3502, '2019-10-23 14:05:31.168533', NULL, 'Rio Branco Do Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3503, '2019-10-23 14:05:31.168533', NULL, 'Rio Branco Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3505, '2019-10-23 14:05:31.168533', NULL, 'Rolândia', 18);
INSERT INTO endereco.cidade VALUES (3507, '2019-10-23 14:05:31.168533', NULL, 'Rondon', 18);
INSERT INTO endereco.cidade VALUES (3509, '2019-10-23 14:05:31.168533', NULL, 'Sabáudia', 18);
INSERT INTO endereco.cidade VALUES (3510, '2019-10-23 14:05:31.168533', NULL, 'Salgado Filho', 18);
INSERT INTO endereco.cidade VALUES (3512, '2019-10-23 14:05:31.168533', NULL, 'Salto Do Lontra', 18);
INSERT INTO endereco.cidade VALUES (3514, '2019-10-23 14:05:31.168533', NULL, 'Santa Cecília Do Pavão', 18);
INSERT INTO endereco.cidade VALUES (3516, '2019-10-23 14:05:31.168533', NULL, 'Santa Fé', 18);
INSERT INTO endereco.cidade VALUES (3517, '2019-10-23 14:05:31.168533', NULL, 'Santa Helena', 18);
INSERT INTO endereco.cidade VALUES (3519, '2019-10-23 14:05:31.168533', NULL, 'Santa Isabel Do Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3521, '2019-10-23 14:05:31.168533', NULL, 'Santa Lúcia', 18);
INSERT INTO endereco.cidade VALUES (3522, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3524, '2019-10-23 14:05:31.168533', NULL, 'Santa Mônica', 18);
INSERT INTO endereco.cidade VALUES (3526, '2019-10-23 14:05:31.168533', NULL, 'Santa Terezinha De Itaipu', 18);
INSERT INTO endereco.cidade VALUES (3529, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Caiuá', 18);
INSERT INTO endereco.cidade VALUES (3530, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Paraíso', 18);
INSERT INTO endereco.cidade VALUES (3532, '2019-10-23 14:05:31.168533', NULL, 'Santo Inácio', 18);
INSERT INTO endereco.cidade VALUES (3534, '2019-10-23 14:05:31.168533', NULL, 'São Jerônimo Da Serra', 18);
INSERT INTO endereco.cidade VALUES (3535, '2019-10-23 14:05:31.168533', NULL, 'São João', 18);
INSERT INTO endereco.cidade VALUES (3537, '2019-10-23 14:05:31.168533', NULL, 'São João Do Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3538, '2019-10-23 14:05:31.168533', NULL, 'São João Do Triunfo', 18);
INSERT INTO endereco.cidade VALUES (3540, '2019-10-23 14:05:31.168533', NULL, 'São Jorge Do Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3542, '2019-10-23 14:05:31.168533', NULL, 'São José Da Boa Vista', 18);
INSERT INTO endereco.cidade VALUES (3543, '2019-10-23 14:05:31.168533', NULL, 'São José Das Palmeiras', 18);
INSERT INTO endereco.cidade VALUES (3545, '2019-10-23 14:05:31.168533', NULL, 'São Manoel Do Paraná', 18);
INSERT INTO endereco.cidade VALUES (3546, '2019-10-23 14:05:31.168533', NULL, 'São Mateus Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3548, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3550, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Do Paraná', 18);
INSERT INTO endereco.cidade VALUES (3552, '2019-10-23 14:05:31.168533', NULL, 'São Tomé', 18);
INSERT INTO endereco.cidade VALUES (3553, '2019-10-23 14:05:31.168533', NULL, 'Sapopema', 18);
INSERT INTO endereco.cidade VALUES (3555, '2019-10-23 14:05:31.168533', NULL, 'Saudade Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3556, '2019-10-23 14:05:31.168533', NULL, 'Sengés', 18);
INSERT INTO endereco.cidade VALUES (3558, '2019-10-23 14:05:31.168533', NULL, 'Sertaneja', 18);
INSERT INTO endereco.cidade VALUES (3560, '2019-10-23 14:05:31.168533', NULL, 'Siqueira Campos', 18);
INSERT INTO endereco.cidade VALUES (3561, '2019-10-23 14:05:31.168533', NULL, 'Sulina', 18);
INSERT INTO endereco.cidade VALUES (3563, '2019-10-23 14:05:31.168533', NULL, 'Tamboara', 18);
INSERT INTO endereco.cidade VALUES (3565, '2019-10-23 14:05:31.168533', NULL, 'Tapira', 18);
INSERT INTO endereco.cidade VALUES (3567, '2019-10-23 14:05:31.168533', NULL, 'Telêmaco Borba', 18);
INSERT INTO endereco.cidade VALUES (3568, '2019-10-23 14:05:31.168533', NULL, 'Terra Boa', 18);
INSERT INTO endereco.cidade VALUES (3570, '2019-10-23 14:05:31.168533', NULL, 'Terra Roxa', 18);
INSERT INTO endereco.cidade VALUES (3572, '2019-10-23 14:05:31.168533', NULL, 'Tijucas Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3574, '2019-10-23 14:05:31.168533', NULL, 'Tomazina', 18);
INSERT INTO endereco.cidade VALUES (3576, '2019-10-23 14:05:31.168533', NULL, 'Tunas Do Paraná', 18);
INSERT INTO endereco.cidade VALUES (3577, '2019-10-23 14:05:31.168533', NULL, 'Tuneiras Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3579, '2019-10-23 14:05:31.168533', NULL, 'Turvo', 18);
INSERT INTO endereco.cidade VALUES (3581, '2019-10-23 14:05:31.168533', NULL, 'Umuarama', 18);
INSERT INTO endereco.cidade VALUES (3583, '2019-10-23 14:05:31.168533', NULL, 'Uniflor', 18);
INSERT INTO endereco.cidade VALUES (3584, '2019-10-23 14:05:31.168533', NULL, 'Uraí', 18);
INSERT INTO endereco.cidade VALUES (3586, '2019-10-23 14:05:31.168533', NULL, 'Vera Cruz Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3588, '2019-10-23 14:05:31.168533', NULL, 'Virmond', 18);
INSERT INTO endereco.cidade VALUES (3589, '2019-10-23 14:05:31.168533', NULL, 'Vitorino', 18);
INSERT INTO endereco.cidade VALUES (3591, '2019-10-23 14:05:31.168533', NULL, 'Xambrê', 18);
INSERT INTO endereco.cidade VALUES (3593, '2019-10-23 14:05:31.168533', NULL, 'Aperibé', 19);
INSERT INTO endereco.cidade VALUES (3594, '2019-10-23 14:05:31.168533', NULL, 'Araruama', 19);
INSERT INTO endereco.cidade VALUES (3596, '2019-10-23 14:05:31.168533', NULL, 'Armação Dos Búzios', 19);
INSERT INTO endereco.cidade VALUES (3598, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Piraí', 19);
INSERT INTO endereco.cidade VALUES (3600, '2019-10-23 14:05:31.168533', NULL, 'Belford Roxo', 19);
INSERT INTO endereco.cidade VALUES (3601, '2019-10-23 14:05:31.168533', NULL, 'Bom Jardim', 19);
INSERT INTO endereco.cidade VALUES (3603, '2019-10-23 14:05:31.168533', NULL, 'Cabo Frio', 19);
INSERT INTO endereco.cidade VALUES (3605, '2019-10-23 14:05:31.168533', NULL, 'Cambuci', 19);
INSERT INTO endereco.cidade VALUES (3606, '2019-10-23 14:05:31.168533', NULL, 'Campos Dos Goytacazes', 19);
INSERT INTO endereco.cidade VALUES (3608, '2019-10-23 14:05:31.168533', NULL, 'Carapebus', 19);
INSERT INTO endereco.cidade VALUES (3610, '2019-10-23 14:05:31.168533', NULL, 'Carmo', 19);
INSERT INTO endereco.cidade VALUES (3611, '2019-10-23 14:05:31.168533', NULL, 'Casimiro De Abreu', 19);
INSERT INTO endereco.cidade VALUES (3613, '2019-10-23 14:05:31.168533', NULL, 'Conceição De Macabu', 19);
INSERT INTO endereco.cidade VALUES (3615, '2019-10-23 14:05:31.168533', NULL, 'Duas Barras', 19);
INSERT INTO endereco.cidade VALUES (3617, '2019-10-23 14:05:31.168533', NULL, 'Engenheiro Paulo De Frontin', 19);
INSERT INTO endereco.cidade VALUES (3618, '2019-10-23 14:05:31.168533', NULL, 'Guapimirim', 19);
INSERT INTO endereco.cidade VALUES (3620, '2019-10-23 14:05:31.168533', NULL, 'Itaboraí', 19);
INSERT INTO endereco.cidade VALUES (3621, '2019-10-23 14:05:31.168533', NULL, 'Itaguaí', 19);
INSERT INTO endereco.cidade VALUES (3623, '2019-10-23 14:05:31.168533', NULL, 'Itaocara', 19);
INSERT INTO endereco.cidade VALUES (3625, '2019-10-23 14:05:31.168533', NULL, 'Itatiaia', 19);
INSERT INTO endereco.cidade VALUES (3627, '2019-10-23 14:05:31.168533', NULL, 'Laje Do Muriaé', 19);
INSERT INTO endereco.cidade VALUES (3628, '2019-10-23 14:05:31.168533', NULL, 'Macaé', 19);
INSERT INTO endereco.cidade VALUES (3630, '2019-10-23 14:05:31.168533', NULL, 'Magé', 19);
INSERT INTO endereco.cidade VALUES (3632, '2019-10-23 14:05:31.168533', NULL, 'Maricá', 19);
INSERT INTO endereco.cidade VALUES (3634, '2019-10-23 14:05:31.168533', NULL, 'Mesquita', 19);
INSERT INTO endereco.cidade VALUES (3636, '2019-10-23 14:05:31.168533', NULL, 'Miracema', 19);
INSERT INTO endereco.cidade VALUES (3637, '2019-10-23 14:05:31.168533', NULL, 'Natividade', 19);
INSERT INTO endereco.cidade VALUES (3639, '2019-10-23 14:05:31.168533', NULL, 'Niterói', 19);
INSERT INTO endereco.cidade VALUES (3641, '2019-10-23 14:05:31.168533', NULL, 'Nova Iguaçu', 19);
INSERT INTO endereco.cidade VALUES (3642, '2019-10-23 14:05:31.168533', NULL, 'Paracambi', 19);
INSERT INTO endereco.cidade VALUES (3644, '2019-10-23 14:05:31.168533', NULL, 'Parati', 19);
INSERT INTO endereco.cidade VALUES (3646, '2019-10-23 14:05:31.168533', NULL, 'Petrópolis', 19);
INSERT INTO endereco.cidade VALUES (3648, '2019-10-23 14:05:31.168533', NULL, 'Piraí', 19);
INSERT INTO endereco.cidade VALUES (3649, '2019-10-23 14:05:31.168533', NULL, 'Porciúncula', 19);
INSERT INTO endereco.cidade VALUES (3653, '2019-10-23 14:05:31.168533', NULL, 'Quissamã', 19);
INSERT INTO endereco.cidade VALUES (3655, '2019-10-23 14:05:31.168533', NULL, 'Rio Bonito', 19);
INSERT INTO endereco.cidade VALUES (3657, '2019-10-23 14:05:31.168533', NULL, 'Rio Das Flores', 19);
INSERT INTO endereco.cidade VALUES (3659, '2019-10-23 14:05:31.168533', NULL, 'Rio De Janeiro', 19);
INSERT INTO endereco.cidade VALUES (3660, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Madalena', 19);
INSERT INTO endereco.cidade VALUES (3662, '2019-10-23 14:05:31.168533', NULL, 'São Fidélis', 19);
INSERT INTO endereco.cidade VALUES (3664, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo', 19);
INSERT INTO endereco.cidade VALUES (3665, '2019-10-23 14:05:31.168533', NULL, 'São João Da Barra', 19);
INSERT INTO endereco.cidade VALUES (3667, '2019-10-23 14:05:31.168533', NULL, 'São José De Ubá', 19);
INSERT INTO endereco.cidade VALUES (3668, '2019-10-23 14:05:31.168533', NULL, 'São José Do Vale Do Rio Preto', 19);
INSERT INTO endereco.cidade VALUES (3670, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Alto', 19);
INSERT INTO endereco.cidade VALUES (3671, '2019-10-23 14:05:31.168533', NULL, 'Sapucaia', 19);
INSERT INTO endereco.cidade VALUES (3673, '2019-10-23 14:05:31.168533', NULL, 'Seropédica', 19);
INSERT INTO endereco.cidade VALUES (3675, '2019-10-23 14:05:31.168533', NULL, 'Sumidouro', 19);
INSERT INTO endereco.cidade VALUES (3676, '2019-10-23 14:05:31.168533', NULL, 'Tanguá', 19);
INSERT INTO endereco.cidade VALUES (3678, '2019-10-23 14:05:31.168533', NULL, 'Trajano De Moraes', 19);
INSERT INTO endereco.cidade VALUES (3680, '2019-10-23 14:05:31.168533', NULL, 'Valença', 19);
INSERT INTO endereco.cidade VALUES (3681, '2019-10-23 14:05:31.168533', NULL, 'Varre Sai', 19);
INSERT INTO endereco.cidade VALUES (3684, '2019-10-23 14:05:31.168533', NULL, 'Acari', 20);
INSERT INTO endereco.cidade VALUES (3685, '2019-10-23 14:05:31.168533', NULL, 'Açu', 20);
INSERT INTO endereco.cidade VALUES (3687, '2019-10-23 14:05:31.168533', NULL, 'Água Nova', 20);
INSERT INTO endereco.cidade VALUES (3688, '2019-10-23 14:05:31.168533', NULL, 'Alexandria', 20);
INSERT INTO endereco.cidade VALUES (3690, '2019-10-23 14:05:31.168533', NULL, 'Alto Do Rodrigues', 20);
INSERT INTO endereco.cidade VALUES (3692, '2019-10-23 14:05:31.168533', NULL, 'Antônio Martins', 20);
INSERT INTO endereco.cidade VALUES (3694, '2019-10-23 14:05:31.168533', NULL, 'Areia Branca', 20);
INSERT INTO endereco.cidade VALUES (3696, '2019-10-23 14:05:31.168533', NULL, 'Augusto Severo', 20);
INSERT INTO endereco.cidade VALUES (3698, '2019-10-23 14:05:31.168533', NULL, 'Baraúna', 20);
INSERT INTO endereco.cidade VALUES (3699, '2019-10-23 14:05:31.168533', NULL, 'Barcelona', 20);
INSERT INTO endereco.cidade VALUES (3701, '2019-10-23 14:05:31.168533', NULL, 'Bodó', 20);
INSERT INTO endereco.cidade VALUES (3703, '2019-10-23 14:05:31.168533', NULL, 'Brejinho', 20);
INSERT INTO endereco.cidade VALUES (3705, '2019-10-23 14:05:31.168533', NULL, 'Caiçara Do Rio Do Vento', 20);
INSERT INTO endereco.cidade VALUES (3706, '2019-10-23 14:05:31.168533', NULL, 'Caicó', 20);
INSERT INTO endereco.cidade VALUES (3708, '2019-10-23 14:05:31.168533', NULL, 'Canguaretama', 20);
INSERT INTO endereco.cidade VALUES (3710, '2019-10-23 14:05:31.168533', NULL, 'Carnaúba Dos Dantas', 20);
INSERT INTO endereco.cidade VALUES (3712, '2019-10-23 14:05:31.168533', NULL, 'Ceará Mirim', 20);
INSERT INTO endereco.cidade VALUES (3713, '2019-10-23 14:05:31.168533', NULL, 'Cerro Corá', 20);
INSERT INTO endereco.cidade VALUES (3715, '2019-10-23 14:05:31.168533', NULL, 'Coronel João Pessoa', 20);
INSERT INTO endereco.cidade VALUES (3717, '2019-10-23 14:05:31.168533', NULL, 'Currais Novos', 20);
INSERT INTO endereco.cidade VALUES (3719, '2019-10-23 14:05:31.168533', NULL, 'Encanto', 20);
INSERT INTO endereco.cidade VALUES (3720, '2019-10-23 14:05:31.168533', NULL, 'Equador', 20);
INSERT INTO endereco.cidade VALUES (3722, '2019-10-23 14:05:31.168533', NULL, 'Extremoz', 20);
INSERT INTO endereco.cidade VALUES (3724, '2019-10-23 14:05:31.168533', NULL, 'Fernando Pedroza', 20);
INSERT INTO endereco.cidade VALUES (3726, '2019-10-23 14:05:31.168533', NULL, 'Francisco Dantas', 20);
INSERT INTO endereco.cidade VALUES (3728, '2019-10-23 14:05:31.168533', NULL, 'Galinhos', 20);
INSERT INTO endereco.cidade VALUES (3729, '2019-10-23 14:05:31.168533', NULL, 'Goianinha', 20);
INSERT INTO endereco.cidade VALUES (3731, '2019-10-23 14:05:31.168533', NULL, 'Grossos', 20);
INSERT INTO endereco.cidade VALUES (3732, '2019-10-23 14:05:31.168533', NULL, 'Guamaré', 20);
INSERT INTO endereco.cidade VALUES (3734, '2019-10-23 14:05:31.168533', NULL, 'Ipanguaçu', 20);
INSERT INTO endereco.cidade VALUES (3736, '2019-10-23 14:05:31.168533', NULL, 'Itajá', 20);
INSERT INTO endereco.cidade VALUES (3738, '2019-10-23 14:05:31.168533', NULL, 'Jaçanã', 20);
INSERT INTO endereco.cidade VALUES (3739, '2019-10-23 14:05:31.168533', NULL, 'Jandaíra', 20);
INSERT INTO endereco.cidade VALUES (3741, '2019-10-23 14:05:31.168533', NULL, 'Januário Cicco', 20);
INSERT INTO endereco.cidade VALUES (3743, '2019-10-23 14:05:31.168533', NULL, 'Jardim De Angicos', 20);
INSERT INTO endereco.cidade VALUES (3745, '2019-10-23 14:05:31.168533', NULL, 'Jardim Do Seridó', 20);
INSERT INTO endereco.cidade VALUES (3747, '2019-10-23 14:05:31.168533', NULL, 'João Dias', 20);
INSERT INTO endereco.cidade VALUES (3748, '2019-10-23 14:05:31.168533', NULL, 'José Da Penha', 20);
INSERT INTO endereco.cidade VALUES (3750, '2019-10-23 14:05:31.168533', NULL, 'Jundiá', 20);
INSERT INTO endereco.cidade VALUES (3752, '2019-10-23 14:05:31.168533', NULL, 'Lagoa De Pedras', 20);
INSERT INTO endereco.cidade VALUES (3753, '2019-10-23 14:05:31.168533', NULL, 'Lagoa De Velhos', 20);
INSERT INTO endereco.cidade VALUES (3755, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Salgada', 20);
INSERT INTO endereco.cidade VALUES (3757, '2019-10-23 14:05:31.168533', NULL, 'Lajes Pintadas', 20);
INSERT INTO endereco.cidade VALUES (3759, '2019-10-23 14:05:31.168533', NULL, 'Luís Gomes', 20);
INSERT INTO endereco.cidade VALUES (3761, '2019-10-23 14:05:31.168533', NULL, 'Macau', 20);
INSERT INTO endereco.cidade VALUES (3762, '2019-10-23 14:05:31.168533', NULL, 'Major Sales', 20);
INSERT INTO endereco.cidade VALUES (3764, '2019-10-23 14:05:31.168533', NULL, 'Martins', 20);
INSERT INTO endereco.cidade VALUES (3766, '2019-10-23 14:05:31.168533', NULL, 'Messias Targino', 20);
INSERT INTO endereco.cidade VALUES (3768, '2019-10-23 14:05:31.168533', NULL, 'Monte Alegre', 20);
INSERT INTO endereco.cidade VALUES (3770, '2019-10-23 14:05:31.168533', NULL, 'Mossoró', 20);
INSERT INTO endereco.cidade VALUES (3771, '2019-10-23 14:05:31.168533', NULL, 'Natal', 20);
INSERT INTO endereco.cidade VALUES (3773, '2019-10-23 14:05:31.168533', NULL, 'Nova Cruz', 20);
INSERT INTO endereco.cidade VALUES (3775, '2019-10-23 14:05:31.168533', NULL, 'Ouro Branco', 20);
INSERT INTO endereco.cidade VALUES (3776, '2019-10-23 14:05:31.168533', NULL, 'Paraná', 20);
INSERT INTO endereco.cidade VALUES (3779, '2019-10-23 14:05:31.168533', NULL, 'Parelhas', 20);
INSERT INTO endereco.cidade VALUES (3781, '2019-10-23 14:05:31.168533', NULL, 'Passa E Fica', 20);
INSERT INTO endereco.cidade VALUES (3783, '2019-10-23 14:05:31.168533', NULL, 'Patu', 20);
INSERT INTO endereco.cidade VALUES (3785, '2019-10-23 14:05:31.168533', NULL, 'Pedra Grande', 20);
INSERT INTO endereco.cidade VALUES (3786, '2019-10-23 14:05:31.168533', NULL, 'Pedra Preta', 20);
INSERT INTO endereco.cidade VALUES (3788, '2019-10-23 14:05:31.168533', NULL, 'Pedro Velho', 20);
INSERT INTO endereco.cidade VALUES (3789, '2019-10-23 14:05:31.168533', NULL, 'Pendências', 20);
INSERT INTO endereco.cidade VALUES (3792, '2019-10-23 14:05:31.168533', NULL, 'Portalegre', 20);
INSERT INTO endereco.cidade VALUES (3793, '2019-10-23 14:05:31.168533', NULL, 'Porto Do Mangue', 20);
INSERT INTO endereco.cidade VALUES (3795, '2019-10-23 14:05:31.168533', NULL, 'Pureza', 20);
INSERT INTO endereco.cidade VALUES (3796, '2019-10-23 14:05:31.168533', NULL, 'Rafael Fernandes', 20);
INSERT INTO endereco.cidade VALUES (3798, '2019-10-23 14:05:31.168533', NULL, 'Riacho Da Cruz', 20);
INSERT INTO endereco.cidade VALUES (3800, '2019-10-23 14:05:31.168533', NULL, 'Riachuelo', 20);
INSERT INTO endereco.cidade VALUES (3801, '2019-10-23 14:05:31.168533', NULL, 'Rio Do Fogo', 20);
INSERT INTO endereco.cidade VALUES (3803, '2019-10-23 14:05:31.168533', NULL, 'Ruy Barbosa', 20);
INSERT INTO endereco.cidade VALUES (3805, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria', 20);
INSERT INTO endereco.cidade VALUES (3807, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Seridó', 20);
INSERT INTO endereco.cidade VALUES (3809, '2019-10-23 14:05:31.168533', NULL, 'São Bento Do Norte', 20);
INSERT INTO endereco.cidade VALUES (3810, '2019-10-23 14:05:31.168533', NULL, 'São Bento Do Trairí', 20);
INSERT INTO endereco.cidade VALUES (3812, '2019-10-23 14:05:31.168533', NULL, 'São Francisco Do Oeste', 20);
INSERT INTO endereco.cidade VALUES (3814, '2019-10-23 14:05:31.168533', NULL, 'São João Do Sabugi', 20);
INSERT INTO endereco.cidade VALUES (3815, '2019-10-23 14:05:31.168533', NULL, 'São José De Mipibu', 20);
INSERT INTO endereco.cidade VALUES (3817, '2019-10-23 14:05:31.168533', NULL, 'São José Do Seridó', 20);
INSERT INTO endereco.cidade VALUES (3819, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do  Gostoso', 20);
INSERT INTO endereco.cidade VALUES (3821, '2019-10-23 14:05:31.168533', NULL, 'São Pedro', 20);
INSERT INTO endereco.cidade VALUES (3822, '2019-10-23 14:05:31.168533', NULL, 'São Rafael', 20);
INSERT INTO endereco.cidade VALUES (3824, '2019-10-23 14:05:31.168533', NULL, 'São Vicente', 20);
INSERT INTO endereco.cidade VALUES (3826, '2019-10-23 14:05:31.168533', NULL, 'Senador Georgino Avelino', 20);
INSERT INTO endereco.cidade VALUES (3827, '2019-10-23 14:05:31.168533', NULL, 'Serra De São Bento', 20);
INSERT INTO endereco.cidade VALUES (3829, '2019-10-23 14:05:31.168533', NULL, 'Serra Negra Do Norte', 20);
INSERT INTO endereco.cidade VALUES (3831, '2019-10-23 14:05:31.168533', NULL, 'Serrinha Dos Pintos', 20);
INSERT INTO endereco.cidade VALUES (3833, '2019-10-23 14:05:31.168533', NULL, 'Sítio Novo', 20);
INSERT INTO endereco.cidade VALUES (3834, '2019-10-23 14:05:31.168533', NULL, 'Taboleiro Grande', 20);
INSERT INTO endereco.cidade VALUES (3836, '2019-10-23 14:05:31.168533', NULL, 'Tangará', 20);
INSERT INTO endereco.cidade VALUES (3838, '2019-10-23 14:05:31.168533', NULL, 'Tenente Laurentino Cruz', 20);
INSERT INTO endereco.cidade VALUES (3840, '2019-10-23 14:05:31.168533', NULL, 'Tibau Do Sul', 20);
INSERT INTO endereco.cidade VALUES (3842, '2019-10-23 14:05:31.168533', NULL, 'Touros', 20);
INSERT INTO endereco.cidade VALUES (3843, '2019-10-23 14:05:31.168533', NULL, 'Triunfo Potiguar', 20);
INSERT INTO endereco.cidade VALUES (3845, '2019-10-23 14:05:31.168533', NULL, 'Upanema', 20);
INSERT INTO endereco.cidade VALUES (3847, '2019-10-23 14:05:31.168533', NULL, 'Venha Ver', 20);
INSERT INTO endereco.cidade VALUES (3848, '2019-10-23 14:05:31.168533', NULL, 'Vera Cruz', 20);
INSERT INTO endereco.cidade VALUES (3850, '2019-10-23 14:05:31.168533', NULL, 'Vila Flor', 20);
INSERT INTO endereco.cidade VALUES (3852, '2019-10-23 14:05:31.168533', NULL, 'Alto Alegre Dos Parecis', 21);
INSERT INTO endereco.cidade VALUES (3854, '2019-10-23 14:05:31.168533', NULL, 'Alvorada D`Oeste', 21);
INSERT INTO endereco.cidade VALUES (3855, '2019-10-23 14:05:31.168533', NULL, 'Ariquemes', 21);
INSERT INTO endereco.cidade VALUES (3857, '2019-10-23 14:05:31.168533', NULL, 'Cabixi', 21);
INSERT INTO endereco.cidade VALUES (3859, '2019-10-23 14:05:31.168533', NULL, 'Cacoal', 21);
INSERT INTO endereco.cidade VALUES (3861, '2019-10-23 14:05:31.168533', NULL, 'Candeias Do Jamari', 21);
INSERT INTO endereco.cidade VALUES (3862, '2019-10-23 14:05:31.168533', NULL, 'Castanheiras', 21);
INSERT INTO endereco.cidade VALUES (3864, '2019-10-23 14:05:31.168533', NULL, 'Chupinguaia', 21);
INSERT INTO endereco.cidade VALUES (3866, '2019-10-23 14:05:31.168533', NULL, 'Corumbiara', 21);
INSERT INTO endereco.cidade VALUES (3867, '2019-10-23 14:05:31.168533', NULL, 'Costa Marques', 21);
INSERT INTO endereco.cidade VALUES (3869, '2019-10-23 14:05:31.168533', NULL, 'Espigão D`Oeste', 21);
INSERT INTO endereco.cidade VALUES (3871, '2019-10-23 14:05:31.168533', NULL, 'Guajará Mirim', 21);
INSERT INTO endereco.cidade VALUES (3872, '2019-10-23 14:05:31.168533', NULL, 'Itapuã Do Oeste', 21);
INSERT INTO endereco.cidade VALUES (3874, '2019-10-23 14:05:31.168533', NULL, 'Ji Paraná', 21);
INSERT INTO endereco.cidade VALUES (3876, '2019-10-23 14:05:31.168533', NULL, 'Ministro Andreazza', 21);
INSERT INTO endereco.cidade VALUES (3878, '2019-10-23 14:05:31.168533', NULL, 'Monte Negro', 21);
INSERT INTO endereco.cidade VALUES (3879, '2019-10-23 14:05:31.168533', NULL, 'Nova Brasilândia D`Oeste', 21);
INSERT INTO endereco.cidade VALUES (3881, '2019-10-23 14:05:31.168533', NULL, 'Nova União', 21);
INSERT INTO endereco.cidade VALUES (3883, '2019-10-23 14:05:31.168533', NULL, 'Ouro Preto Do Oeste', 21);
INSERT INTO endereco.cidade VALUES (3884, '2019-10-23 14:05:31.168533', NULL, 'Parecis', 21);
INSERT INTO endereco.cidade VALUES (3886, '2019-10-23 14:05:31.168533', NULL, 'Pimenteiras Do Oeste', 21);
INSERT INTO endereco.cidade VALUES (3888, '2019-10-23 14:05:31.168533', NULL, 'Presidente Médici', 21);
INSERT INTO endereco.cidade VALUES (3890, '2019-10-23 14:05:31.168533', NULL, 'Rio Crespo', 21);
INSERT INTO endereco.cidade VALUES (3891, '2019-10-23 14:05:31.168533', NULL, 'Rolim De Moura', 21);
INSERT INTO endereco.cidade VALUES (3893, '2019-10-23 14:05:31.168533', NULL, 'São Felipe D`Oeste', 21);
INSERT INTO endereco.cidade VALUES (3895, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Guaporé', 21);
INSERT INTO endereco.cidade VALUES (3896, '2019-10-23 14:05:31.168533', NULL, 'Seringueiras', 21);
INSERT INTO endereco.cidade VALUES (3898, '2019-10-23 14:05:31.168533', NULL, 'Theobroma', 21);
INSERT INTO endereco.cidade VALUES (3899, '2019-10-23 14:05:31.168533', NULL, 'Urupá', 21);
INSERT INTO endereco.cidade VALUES (5422, '2019-10-23 14:05:31.168533', NULL, 'Vista Alegre Do Alto', 26);
INSERT INTO endereco.cidade VALUES (3902, '2019-10-23 14:05:31.168533', NULL, 'Vilhena', 21);
INSERT INTO endereco.cidade VALUES (3903, '2019-10-23 14:05:31.168533', NULL, 'Alto Alegre', 22);
INSERT INTO endereco.cidade VALUES (3905, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista', 22);
INSERT INTO endereco.cidade VALUES (3907, '2019-10-23 14:05:31.168533', NULL, 'Cantá', 22);
INSERT INTO endereco.cidade VALUES (3909, '2019-10-23 14:05:31.168533', NULL, 'Caroebe', 22);
INSERT INTO endereco.cidade VALUES (3911, '2019-10-23 14:05:31.168533', NULL, 'Mucajaí', 22);
INSERT INTO endereco.cidade VALUES (3912, '2019-10-23 14:05:31.168533', NULL, 'Normandia', 22);
INSERT INTO endereco.cidade VALUES (3914, '2019-10-23 14:05:31.168533', NULL, 'Rorainópolis', 22);
INSERT INTO endereco.cidade VALUES (3916, '2019-10-23 14:05:31.168533', NULL, 'São Luiz', 22);
INSERT INTO endereco.cidade VALUES (3917, '2019-10-23 14:05:31.168533', NULL, 'Uiramutã', 22);
INSERT INTO endereco.cidade VALUES (3919, '2019-10-23 14:05:31.168533', NULL, 'Água Santa', 23);
INSERT INTO endereco.cidade VALUES (3921, '2019-10-23 14:05:31.168533', NULL, 'Ajuricaba', 23);
INSERT INTO endereco.cidade VALUES (3923, '2019-10-23 14:05:31.168533', NULL, 'Alegrete', 23);
INSERT INTO endereco.cidade VALUES (3925, '2019-10-23 14:05:31.168533', NULL, 'Almirante Tamandaré Do Sul', 23);
INSERT INTO endereco.cidade VALUES (3927, '2019-10-23 14:05:31.168533', NULL, 'Alto Alegre', 23);
INSERT INTO endereco.cidade VALUES (3929, '2019-10-23 14:05:31.168533', NULL, 'Alvorada', 23);
INSERT INTO endereco.cidade VALUES (3930, '2019-10-23 14:05:31.168533', NULL, 'Amaral Ferrador', 23);
INSERT INTO endereco.cidade VALUES (3932, '2019-10-23 14:05:31.168533', NULL, 'André Da Rocha', 23);
INSERT INTO endereco.cidade VALUES (3934, '2019-10-23 14:05:31.168533', NULL, 'Antônio Prado', 23);
INSERT INTO endereco.cidade VALUES (3936, '2019-10-23 14:05:31.168533', NULL, 'Araricá', 23);
INSERT INTO endereco.cidade VALUES (3937, '2019-10-23 14:05:31.168533', NULL, 'Aratiba', 23);
INSERT INTO endereco.cidade VALUES (3939, '2019-10-23 14:05:31.168533', NULL, 'Arroio Do Padre', 23);
INSERT INTO endereco.cidade VALUES (3941, '2019-10-23 14:05:31.168533', NULL, 'Arroio Do Tigre', 23);
INSERT INTO endereco.cidade VALUES (3942, '2019-10-23 14:05:31.168533', NULL, 'Arroio Dos Ratos', 23);
INSERT INTO endereco.cidade VALUES (3944, '2019-10-23 14:05:31.168533', NULL, 'Arvorezinha', 23);
INSERT INTO endereco.cidade VALUES (3946, '2019-10-23 14:05:31.168533', NULL, 'Áurea', 23);
INSERT INTO endereco.cidade VALUES (3947, '2019-10-23 14:05:31.168533', NULL, 'Bagé', 23);
INSERT INTO endereco.cidade VALUES (3949, '2019-10-23 14:05:31.168533', NULL, 'Barão', 23);
INSERT INTO endereco.cidade VALUES (3951, '2019-10-23 14:05:31.168533', NULL, 'Barão Do Triunfo', 23);
INSERT INTO endereco.cidade VALUES (3953, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Quaraí', 23);
INSERT INTO endereco.cidade VALUES (3954, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Ribeiro', 23);
INSERT INTO endereco.cidade VALUES (3956, '2019-10-23 14:05:31.168533', NULL, 'Barra Funda', 23);
INSERT INTO endereco.cidade VALUES (3957, '2019-10-23 14:05:31.168533', NULL, 'Barracão', 23);
INSERT INTO endereco.cidade VALUES (3959, '2019-10-23 14:05:31.168533', NULL, 'Benjamin Constant Do Sul', 23);
INSERT INTO endereco.cidade VALUES (3961, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista Das Missões', 23);
INSERT INTO endereco.cidade VALUES (3963, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista Do Cadeado', 23);
INSERT INTO endereco.cidade VALUES (3964, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista Do Incra', 23);
INSERT INTO endereco.cidade VALUES (3966, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus', 23);
INSERT INTO endereco.cidade VALUES (3968, '2019-10-23 14:05:31.168533', NULL, 'Bom Progresso', 23);
INSERT INTO endereco.cidade VALUES (3969, '2019-10-23 14:05:31.168533', NULL, 'Bom Retiro Do Sul', 23);
INSERT INTO endereco.cidade VALUES (3971, '2019-10-23 14:05:31.168533', NULL, 'Bossoroca', 23);
INSERT INTO endereco.cidade VALUES (3973, '2019-10-23 14:05:31.168533', NULL, 'Braga', 23);
INSERT INTO endereco.cidade VALUES (3974, '2019-10-23 14:05:31.168533', NULL, 'Brochier', 23);
INSERT INTO endereco.cidade VALUES (3976, '2019-10-23 14:05:31.168533', NULL, 'Caçapava Do Sul', 23);
INSERT INTO endereco.cidade VALUES (3978, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Do Sul', 23);
INSERT INTO endereco.cidade VALUES (3980, '2019-10-23 14:05:31.168533', NULL, 'Cacique Doble', 23);
INSERT INTO endereco.cidade VALUES (3981, '2019-10-23 14:05:31.168533', NULL, 'Caibaté', 23);
INSERT INTO endereco.cidade VALUES (3983, '2019-10-23 14:05:31.168533', NULL, 'Camaquã', 23);
INSERT INTO endereco.cidade VALUES (3985, '2019-10-23 14:05:31.168533', NULL, 'Cambará Do Sul', 23);
INSERT INTO endereco.cidade VALUES (3987, '2019-10-23 14:05:31.168533', NULL, 'Campina Das Missões', 23);
INSERT INTO endereco.cidade VALUES (3989, '2019-10-23 14:05:31.168533', NULL, 'Campo Bom', 23);
INSERT INTO endereco.cidade VALUES (3990, '2019-10-23 14:05:31.168533', NULL, 'Campo Novo', 23);
INSERT INTO endereco.cidade VALUES (3992, '2019-10-23 14:05:31.168533', NULL, 'Candelária', 23);
INSERT INTO endereco.cidade VALUES (3994, '2019-10-23 14:05:31.168533', NULL, 'Candiota', 23);
INSERT INTO endereco.cidade VALUES (3995, '2019-10-23 14:05:31.168533', NULL, 'Canela', 23);
INSERT INTO endereco.cidade VALUES (3997, '2019-10-23 14:05:31.168533', NULL, 'Canoas', 23);
INSERT INTO endereco.cidade VALUES (3999, '2019-10-23 14:05:31.168533', NULL, 'Capão Bonito Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4001, '2019-10-23 14:05:31.168533', NULL, 'Capão Do Cipó', 23);
INSERT INTO endereco.cidade VALUES (4002, '2019-10-23 14:05:31.168533', NULL, 'Capão Do Leão', 23);
INSERT INTO endereco.cidade VALUES (4004, '2019-10-23 14:05:31.168533', NULL, 'Capitão', 23);
INSERT INTO endereco.cidade VALUES (4006, '2019-10-23 14:05:31.168533', NULL, 'Caraá', 23);
INSERT INTO endereco.cidade VALUES (4007, '2019-10-23 14:05:31.168533', NULL, 'Carazinho', 23);
INSERT INTO endereco.cidade VALUES (4009, '2019-10-23 14:05:31.168533', NULL, 'Carlos Gomes', 23);
INSERT INTO endereco.cidade VALUES (4011, '2019-10-23 14:05:31.168533', NULL, 'Caseiros', 23);
INSERT INTO endereco.cidade VALUES (4012, '2019-10-23 14:05:31.168533', NULL, 'Catuípe', 23);
INSERT INTO endereco.cidade VALUES (4014, '2019-10-23 14:05:31.168533', NULL, 'Centenário', 23);
INSERT INTO endereco.cidade VALUES (4016, '2019-10-23 14:05:31.168533', NULL, 'Cerro Branco', 23);
INSERT INTO endereco.cidade VALUES (4018, '2019-10-23 14:05:31.168533', NULL, 'Cerro Grande Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4020, '2019-10-23 14:05:31.168533', NULL, 'Chapada', 23);
INSERT INTO endereco.cidade VALUES (4021, '2019-10-23 14:05:31.168533', NULL, 'Charqueadas', 23);
INSERT INTO endereco.cidade VALUES (4023, '2019-10-23 14:05:31.168533', NULL, 'Chiapetta', 23);
INSERT INTO endereco.cidade VALUES (4025, '2019-10-23 14:05:31.168533', NULL, 'Chuvisca', 23);
INSERT INTO endereco.cidade VALUES (4028, '2019-10-23 14:05:31.168533', NULL, 'Colinas', 23);
INSERT INTO endereco.cidade VALUES (4030, '2019-10-23 14:05:31.168533', NULL, 'Condor', 23);
INSERT INTO endereco.cidade VALUES (4032, '2019-10-23 14:05:31.168533', NULL, 'Coqueiro Baixo', 23);
INSERT INTO endereco.cidade VALUES (4034, '2019-10-23 14:05:31.168533', NULL, 'Coronel Barros', 23);
INSERT INTO endereco.cidade VALUES (4036, '2019-10-23 14:05:31.168533', NULL, 'Coronel Pilar', 23);
INSERT INTO endereco.cidade VALUES (4037, '2019-10-23 14:05:31.168533', NULL, 'Cotiporã', 23);
INSERT INTO endereco.cidade VALUES (4039, '2019-10-23 14:05:31.168533', NULL, 'Crissiumal', 23);
INSERT INTO endereco.cidade VALUES (4041, '2019-10-23 14:05:31.168533', NULL, 'Cristal Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4043, '2019-10-23 14:05:31.168533', NULL, 'Cruzaltense', 23);
INSERT INTO endereco.cidade VALUES (4045, '2019-10-23 14:05:31.168533', NULL, 'David Canabarro', 23);
INSERT INTO endereco.cidade VALUES (4046, '2019-10-23 14:05:31.168533', NULL, 'Derrubadas', 23);
INSERT INTO endereco.cidade VALUES (4048, '2019-10-23 14:05:31.168533', NULL, 'Dilermando De Aguiar', 23);
INSERT INTO endereco.cidade VALUES (4050, '2019-10-23 14:05:31.168533', NULL, 'Dois Irmãos Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4051, '2019-10-23 14:05:31.168533', NULL, 'Dois Lajeados', 23);
INSERT INTO endereco.cidade VALUES (4053, '2019-10-23 14:05:31.168533', NULL, 'Dom Pedrito', 23);
INSERT INTO endereco.cidade VALUES (4055, '2019-10-23 14:05:31.168533', NULL, 'Dona Francisca', 23);
INSERT INTO endereco.cidade VALUES (4056, '2019-10-23 14:05:31.168533', NULL, 'Doutor Maurício Cardoso', 23);
INSERT INTO endereco.cidade VALUES (4058, '2019-10-23 14:05:31.168533', NULL, 'Eldorado Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4059, '2019-10-23 14:05:31.168533', NULL, 'Encantado', 23);
INSERT INTO endereco.cidade VALUES (4061, '2019-10-23 14:05:31.168533', NULL, 'Engenho Velho', 23);
INSERT INTO endereco.cidade VALUES (4063, '2019-10-23 14:05:31.168533', NULL, 'Entre Ijuís', 23);
INSERT INTO endereco.cidade VALUES (4065, '2019-10-23 14:05:31.168533', NULL, 'Erechim', 23);
INSERT INTO endereco.cidade VALUES (4066, '2019-10-23 14:05:31.168533', NULL, 'Ernestina', 23);
INSERT INTO endereco.cidade VALUES (4068, '2019-10-23 14:05:31.168533', NULL, 'Erval Seco', 23);
INSERT INTO endereco.cidade VALUES (4070, '2019-10-23 14:05:31.168533', NULL, 'Esperança Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4072, '2019-10-23 14:05:31.168533', NULL, 'Estação', 23);
INSERT INTO endereco.cidade VALUES (4074, '2019-10-23 14:05:31.168533', NULL, 'Esteio', 23);
INSERT INTO endereco.cidade VALUES (4075, '2019-10-23 14:05:31.168533', NULL, 'Estrela', 23);
INSERT INTO endereco.cidade VALUES (4077, '2019-10-23 14:05:31.168533', NULL, 'Eugênio De Castro', 23);
INSERT INTO endereco.cidade VALUES (4079, '2019-10-23 14:05:31.168533', NULL, 'Farroupilha', 23);
INSERT INTO endereco.cidade VALUES (4080, '2019-10-23 14:05:31.168533', NULL, 'Faxinal Do Soturno', 23);
INSERT INTO endereco.cidade VALUES (4082, '2019-10-23 14:05:31.168533', NULL, 'Fazenda Vilanova', 23);
INSERT INTO endereco.cidade VALUES (4084, '2019-10-23 14:05:31.168533', NULL, 'Flores Da Cunha', 23);
INSERT INTO endereco.cidade VALUES (4086, '2019-10-23 14:05:31.168533', NULL, 'Fontoura Xavier', 23);
INSERT INTO endereco.cidade VALUES (4087, '2019-10-23 14:05:31.168533', NULL, 'Formigueiro', 23);
INSERT INTO endereco.cidade VALUES (4089, '2019-10-23 14:05:31.168533', NULL, 'Fortaleza Dos Valos', 23);
INSERT INTO endereco.cidade VALUES (4091, '2019-10-23 14:05:31.168533', NULL, 'Garibaldi', 23);
INSERT INTO endereco.cidade VALUES (4092, '2019-10-23 14:05:31.168533', NULL, 'Garruchos', 23);
INSERT INTO endereco.cidade VALUES (4094, '2019-10-23 14:05:31.168533', NULL, 'General Câmara', 23);
INSERT INTO endereco.cidade VALUES (4096, '2019-10-23 14:05:31.168533', NULL, 'Getúlio Vargas', 23);
INSERT INTO endereco.cidade VALUES (4098, '2019-10-23 14:05:31.168533', NULL, 'Glorinha', 23);
INSERT INTO endereco.cidade VALUES (4099, '2019-10-23 14:05:31.168533', NULL, 'Gramado', 23);
INSERT INTO endereco.cidade VALUES (4101, '2019-10-23 14:05:31.168533', NULL, 'Gramado Xavier', 23);
INSERT INTO endereco.cidade VALUES (4103, '2019-10-23 14:05:31.168533', NULL, 'Guabiju', 23);
INSERT INTO endereco.cidade VALUES (4105, '2019-10-23 14:05:31.168533', NULL, 'Guaporé', 23);
INSERT INTO endereco.cidade VALUES (4107, '2019-10-23 14:05:31.168533', NULL, 'Harmonia', 23);
INSERT INTO endereco.cidade VALUES (4108, '2019-10-23 14:05:31.168533', NULL, 'Herval', 23);
INSERT INTO endereco.cidade VALUES (4110, '2019-10-23 14:05:31.168533', NULL, 'Horizontina', 23);
INSERT INTO endereco.cidade VALUES (4111, '2019-10-23 14:05:31.168533', NULL, 'Hulha Negra', 23);
INSERT INTO endereco.cidade VALUES (4113, '2019-10-23 14:05:31.168533', NULL, 'Ibarama', 23);
INSERT INTO endereco.cidade VALUES (4115, '2019-10-23 14:05:31.168533', NULL, 'Ibiraiaras', 23);
INSERT INTO endereco.cidade VALUES (4117, '2019-10-23 14:05:31.168533', NULL, 'Ibirubá', 23);
INSERT INTO endereco.cidade VALUES (4119, '2019-10-23 14:05:31.168533', NULL, 'Ijuí', 23);
INSERT INTO endereco.cidade VALUES (4120, '2019-10-23 14:05:31.168533', NULL, 'Ilópolis', 23);
INSERT INTO endereco.cidade VALUES (4122, '2019-10-23 14:05:31.168533', NULL, 'Imigrante', 23);
INSERT INTO endereco.cidade VALUES (4124, '2019-10-23 14:05:31.168533', NULL, 'Inhacorá', 23);
INSERT INTO endereco.cidade VALUES (4125, '2019-10-23 14:05:31.168533', NULL, 'Ipê', 23);
INSERT INTO endereco.cidade VALUES (4127, '2019-10-23 14:05:31.168533', NULL, 'Iraí', 23);
INSERT INTO endereco.cidade VALUES (4129, '2019-10-23 14:05:31.168533', NULL, 'Itacurubi', 23);
INSERT INTO endereco.cidade VALUES (4131, '2019-10-23 14:05:31.168533', NULL, 'Itaqui', 23);
INSERT INTO endereco.cidade VALUES (4132, '2019-10-23 14:05:31.168533', NULL, 'Itati', 23);
INSERT INTO endereco.cidade VALUES (4134, '2019-10-23 14:05:31.168533', NULL, 'Ivorá', 23);
INSERT INTO endereco.cidade VALUES (4136, '2019-10-23 14:05:31.168533', NULL, 'Jaboticaba', 23);
INSERT INTO endereco.cidade VALUES (4138, '2019-10-23 14:05:31.168533', NULL, 'Jacutinga', 23);
INSERT INTO endereco.cidade VALUES (4139, '2019-10-23 14:05:31.168533', NULL, 'Jaguarão', 23);
INSERT INTO endereco.cidade VALUES (4141, '2019-10-23 14:05:31.168533', NULL, 'Jaquirana', 23);
INSERT INTO endereco.cidade VALUES (4143, '2019-10-23 14:05:31.168533', NULL, 'Jóia', 23);
INSERT INTO endereco.cidade VALUES (4145, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Bonita Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4147, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Vermelha', 23);
INSERT INTO endereco.cidade VALUES (4148, '2019-10-23 14:05:31.168533', NULL, 'Lagoão', 23);
INSERT INTO endereco.cidade VALUES (4150, '2019-10-23 14:05:31.168533', NULL, 'Lajeado Do Bugre', 23);
INSERT INTO endereco.cidade VALUES (4152, '2019-10-23 14:05:31.168533', NULL, 'Liberato Salzano', 23);
INSERT INTO endereco.cidade VALUES (4153, '2019-10-23 14:05:31.168533', NULL, 'Lindolfo Collor', 23);
INSERT INTO endereco.cidade VALUES (5424, '2019-10-23 14:05:31.168533', NULL, 'Votorantim', 26);
INSERT INTO endereco.cidade VALUES (4156, '2019-10-23 14:05:31.168533', NULL, 'Machadinho', 23);
INSERT INTO endereco.cidade VALUES (4158, '2019-10-23 14:05:31.168533', NULL, 'Manoel Viana', 23);
INSERT INTO endereco.cidade VALUES (4160, '2019-10-23 14:05:31.168533', NULL, 'Maratá', 23);
INSERT INTO endereco.cidade VALUES (4161, '2019-10-23 14:05:31.168533', NULL, 'Marau', 23);
INSERT INTO endereco.cidade VALUES (4163, '2019-10-23 14:05:31.168533', NULL, 'Mariana Pimentel', 23);
INSERT INTO endereco.cidade VALUES (4165, '2019-10-23 14:05:31.168533', NULL, 'Marques De Souza', 23);
INSERT INTO endereco.cidade VALUES (4166, '2019-10-23 14:05:31.168533', NULL, 'Mata', 23);
INSERT INTO endereco.cidade VALUES (4168, '2019-10-23 14:05:31.168533', NULL, 'Mato Leitão', 23);
INSERT INTO endereco.cidade VALUES (4170, '2019-10-23 14:05:31.168533', NULL, 'Maximiliano De Almeida', 23);
INSERT INTO endereco.cidade VALUES (4171, '2019-10-23 14:05:31.168533', NULL, 'Minas Do Leão', 23);
INSERT INTO endereco.cidade VALUES (4173, '2019-10-23 14:05:31.168533', NULL, 'Montauri', 23);
INSERT INTO endereco.cidade VALUES (4175, '2019-10-23 14:05:31.168533', NULL, 'Monte Belo Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4176, '2019-10-23 14:05:31.168533', NULL, 'Montenegro', 23);
INSERT INTO endereco.cidade VALUES (4178, '2019-10-23 14:05:31.168533', NULL, 'Morrinhos Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4180, '2019-10-23 14:05:31.168533', NULL, 'Morro Reuter', 23);
INSERT INTO endereco.cidade VALUES (4182, '2019-10-23 14:05:31.168533', NULL, 'Muçum', 23);
INSERT INTO endereco.cidade VALUES (4184, '2019-10-23 14:05:31.168533', NULL, 'Muliterno', 23);
INSERT INTO endereco.cidade VALUES (4185, '2019-10-23 14:05:31.168533', NULL, 'Não Me Toque', 23);
INSERT INTO endereco.cidade VALUES (4187, '2019-10-23 14:05:31.168533', NULL, 'Nonoai', 23);
INSERT INTO endereco.cidade VALUES (4189, '2019-10-23 14:05:31.168533', NULL, 'Nova Araçá', 23);
INSERT INTO endereco.cidade VALUES (4190, '2019-10-23 14:05:31.168533', NULL, 'Nova Bassano', 23);
INSERT INTO endereco.cidade VALUES (4192, '2019-10-23 14:05:31.168533', NULL, 'Nova Bréscia', 23);
INSERT INTO endereco.cidade VALUES (4194, '2019-10-23 14:05:31.168533', NULL, 'Nova Esperança Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4195, '2019-10-23 14:05:31.168533', NULL, 'Nova Hartz', 23);
INSERT INTO endereco.cidade VALUES (4197, '2019-10-23 14:05:31.168533', NULL, 'Nova Palma', 23);
INSERT INTO endereco.cidade VALUES (4199, '2019-10-23 14:05:31.168533', NULL, 'Nova Prata', 23);
INSERT INTO endereco.cidade VALUES (4201, '2019-10-23 14:05:31.168533', NULL, 'Nova Roma Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4203, '2019-10-23 14:05:31.168533', NULL, 'Novo Barreiro', 23);
INSERT INTO endereco.cidade VALUES (4204, '2019-10-23 14:05:31.168533', NULL, 'Novo Cabrais', 23);
INSERT INTO endereco.cidade VALUES (4206, '2019-10-23 14:05:31.168533', NULL, 'Novo Machado', 23);
INSERT INTO endereco.cidade VALUES (4208, '2019-10-23 14:05:31.168533', NULL, 'Novo Xingu', 23);
INSERT INTO endereco.cidade VALUES (4209, '2019-10-23 14:05:31.168533', NULL, 'Osório', 23);
INSERT INTO endereco.cidade VALUES (4211, '2019-10-23 14:05:31.168533', NULL, 'Palmares Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4213, '2019-10-23 14:05:31.168533', NULL, 'Palmitinho', 23);
INSERT INTO endereco.cidade VALUES (4214, '2019-10-23 14:05:31.168533', NULL, 'Panambi', 23);
INSERT INTO endereco.cidade VALUES (4216, '2019-10-23 14:05:31.168533', NULL, 'Paraí', 23);
INSERT INTO endereco.cidade VALUES (4218, '2019-10-23 14:05:31.168533', NULL, 'Pareci Novo', 23);
INSERT INTO endereco.cidade VALUES (4219, '2019-10-23 14:05:31.168533', NULL, 'Parobé', 23);
INSERT INTO endereco.cidade VALUES (4222, '2019-10-23 14:05:31.168533', NULL, 'Passo Fundo', 23);
INSERT INTO endereco.cidade VALUES (4223, '2019-10-23 14:05:31.168533', NULL, 'Paulo Bento', 23);
INSERT INTO endereco.cidade VALUES (4225, '2019-10-23 14:05:31.168533', NULL, 'Pedras Altas', 23);
INSERT INTO endereco.cidade VALUES (4227, '2019-10-23 14:05:31.168533', NULL, 'Pejuçara', 23);
INSERT INTO endereco.cidade VALUES (4228, '2019-10-23 14:05:31.168533', NULL, 'Pelotas', 23);
INSERT INTO endereco.cidade VALUES (4230, '2019-10-23 14:05:31.168533', NULL, 'Pinhal', 23);
INSERT INTO endereco.cidade VALUES (4232, '2019-10-23 14:05:31.168533', NULL, 'Pinhal Grande', 23);
INSERT INTO endereco.cidade VALUES (4233, '2019-10-23 14:05:31.168533', NULL, 'Pinheirinho Do Vale', 23);
INSERT INTO endereco.cidade VALUES (4235, '2019-10-23 14:05:31.168533', NULL, 'Pirapó', 23);
INSERT INTO endereco.cidade VALUES (4237, '2019-10-23 14:05:31.168533', NULL, 'Planalto', 23);
INSERT INTO endereco.cidade VALUES (4239, '2019-10-23 14:05:31.168533', NULL, 'Pontão', 23);
INSERT INTO endereco.cidade VALUES (4240, '2019-10-23 14:05:31.168533', NULL, 'Ponte Preta', 23);
INSERT INTO endereco.cidade VALUES (4242, '2019-10-23 14:05:31.168533', NULL, 'Porto Alegre', 23);
INSERT INTO endereco.cidade VALUES (4244, '2019-10-23 14:05:31.168533', NULL, 'Porto Mauá', 23);
INSERT INTO endereco.cidade VALUES (4246, '2019-10-23 14:05:31.168533', NULL, 'Porto Xavier', 23);
INSERT INTO endereco.cidade VALUES (4247, '2019-10-23 14:05:31.168533', NULL, 'Pouso Novo', 23);
INSERT INTO endereco.cidade VALUES (4249, '2019-10-23 14:05:31.168533', NULL, 'Progresso', 23);
INSERT INTO endereco.cidade VALUES (4251, '2019-10-23 14:05:31.168533', NULL, 'Putinga', 23);
INSERT INTO endereco.cidade VALUES (4252, '2019-10-23 14:05:31.168533', NULL, 'Quaraí', 23);
INSERT INTO endereco.cidade VALUES (4254, '2019-10-23 14:05:31.168533', NULL, 'Quevedos', 23);
INSERT INTO endereco.cidade VALUES (4256, '2019-10-23 14:05:31.168533', NULL, 'Redentora', 23);
INSERT INTO endereco.cidade VALUES (4257, '2019-10-23 14:05:31.168533', NULL, 'Relvado', 23);
INSERT INTO endereco.cidade VALUES (4259, '2019-10-23 14:05:31.168533', NULL, 'Rio Dos Índios', 23);
INSERT INTO endereco.cidade VALUES (4261, '2019-10-23 14:05:31.168533', NULL, 'Rio Pardo', 23);
INSERT INTO endereco.cidade VALUES (4263, '2019-10-23 14:05:31.168533', NULL, 'Roca Sales', 23);
INSERT INTO endereco.cidade VALUES (4264, '2019-10-23 14:05:31.168533', NULL, 'Rodeio Bonito', 23);
INSERT INTO endereco.cidade VALUES (4266, '2019-10-23 14:05:31.168533', NULL, 'Rolante', 23);
INSERT INTO endereco.cidade VALUES (4268, '2019-10-23 14:05:31.168533', NULL, 'Rondinha', 23);
INSERT INTO endereco.cidade VALUES (4270, '2019-10-23 14:05:31.168533', NULL, 'Rosário Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4271, '2019-10-23 14:05:31.168533', NULL, 'Sagrada Família', 23);
INSERT INTO endereco.cidade VALUES (4273, '2019-10-23 14:05:31.168533', NULL, 'Salto Do Jacuí', 23);
INSERT INTO endereco.cidade VALUES (4275, '2019-10-23 14:05:31.168533', NULL, 'Salvador Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4276, '2019-10-23 14:05:31.168533', NULL, 'Sananduva', 23);
INSERT INTO endereco.cidade VALUES (4278, '2019-10-23 14:05:31.168533', NULL, 'Santa Cecília Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4282, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria', 23);
INSERT INTO endereco.cidade VALUES (4284, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa', 23);
INSERT INTO endereco.cidade VALUES (4285, '2019-10-23 14:05:31.168533', NULL, 'Santa Tereza', 23);
INSERT INTO endereco.cidade VALUES (4287, '2019-10-23 14:05:31.168533', NULL, 'Santana Da Boa Vista', 23);
INSERT INTO endereco.cidade VALUES (4289, '2019-10-23 14:05:31.168533', NULL, 'Santiago', 23);
INSERT INTO endereco.cidade VALUES (4290, '2019-10-23 14:05:31.168533', NULL, 'Santo Ângelo', 23);
INSERT INTO endereco.cidade VALUES (4292, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4294, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Planalto', 23);
INSERT INTO endereco.cidade VALUES (4296, '2019-10-23 14:05:31.168533', NULL, 'Santo Cristo', 23);
INSERT INTO endereco.cidade VALUES (4297, '2019-10-23 14:05:31.168533', NULL, 'Santo Expedito Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4299, '2019-10-23 14:05:31.168533', NULL, 'São Domingos Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4301, '2019-10-23 14:05:31.168533', NULL, 'São Francisco De Paula', 23);
INSERT INTO endereco.cidade VALUES (4302, '2019-10-23 14:05:31.168533', NULL, 'São Gabriel', 23);
INSERT INTO endereco.cidade VALUES (4304, '2019-10-23 14:05:31.168533', NULL, 'São João Da Urtiga', 23);
INSERT INTO endereco.cidade VALUES (4306, '2019-10-23 14:05:31.168533', NULL, 'São Jorge', 23);
INSERT INTO endereco.cidade VALUES (4307, '2019-10-23 14:05:31.168533', NULL, 'São José Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4309, '2019-10-23 14:05:31.168533', NULL, 'São José Do Hortêncio', 23);
INSERT INTO endereco.cidade VALUES (4311, '2019-10-23 14:05:31.168533', NULL, 'São José Do Norte', 23);
INSERT INTO endereco.cidade VALUES (4312, '2019-10-23 14:05:31.168533', NULL, 'São José Do Ouro', 23);
INSERT INTO endereco.cidade VALUES (4314, '2019-10-23 14:05:31.168533', NULL, 'São José Dos Ausentes', 23);
INSERT INTO endereco.cidade VALUES (4315, '2019-10-23 14:05:31.168533', NULL, 'São Leopoldo', 23);
INSERT INTO endereco.cidade VALUES (4317, '2019-10-23 14:05:31.168533', NULL, 'São Luiz Gonzaga', 23);
INSERT INTO endereco.cidade VALUES (4318, '2019-10-23 14:05:31.168533', NULL, 'São Marcos', 23);
INSERT INTO endereco.cidade VALUES (4320, '2019-10-23 14:05:31.168533', NULL, 'São Martinho Da Serra', 23);
INSERT INTO endereco.cidade VALUES (4322, '2019-10-23 14:05:31.168533', NULL, 'São Nicolau', 23);
INSERT INTO endereco.cidade VALUES (4324, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Da Serra', 23);
INSERT INTO endereco.cidade VALUES (4325, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4327, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4537, '2019-10-23 14:05:31.168533', NULL, 'Irineópolis', 24);
INSERT INTO endereco.cidade VALUES (4328, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Caí', 23);
INSERT INTO endereco.cidade VALUES (4330, '2019-10-23 14:05:31.168533', NULL, 'São Valentim', 23);
INSERT INTO endereco.cidade VALUES (4332, '2019-10-23 14:05:31.168533', NULL, 'São Valério Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4333, '2019-10-23 14:05:31.168533', NULL, 'São Vendelino', 23);
INSERT INTO endereco.cidade VALUES (4335, '2019-10-23 14:05:31.168533', NULL, 'Sapiranga', 23);
INSERT INTO endereco.cidade VALUES (4337, '2019-10-23 14:05:31.168533', NULL, 'Sarandi', 23);
INSERT INTO endereco.cidade VALUES (4338, '2019-10-23 14:05:31.168533', NULL, 'Seberi', 23);
INSERT INTO endereco.cidade VALUES (4340, '2019-10-23 14:05:31.168533', NULL, 'Segredo', 23);
INSERT INTO endereco.cidade VALUES (4342, '2019-10-23 14:05:31.168533', NULL, 'Senador Salgado Filho', 23);
INSERT INTO endereco.cidade VALUES (4344, '2019-10-23 14:05:31.168533', NULL, 'Serafina Corrêa', 23);
INSERT INTO endereco.cidade VALUES (4345, '2019-10-23 14:05:31.168533', NULL, 'Sério', 23);
INSERT INTO endereco.cidade VALUES (4347, '2019-10-23 14:05:31.168533', NULL, 'Sertão Santana', 23);
INSERT INTO endereco.cidade VALUES (4349, '2019-10-23 14:05:31.168533', NULL, 'Severiano De Almeida', 23);
INSERT INTO endereco.cidade VALUES (4351, '2019-10-23 14:05:31.168533', NULL, 'Sinimbu', 23);
INSERT INTO endereco.cidade VALUES (4352, '2019-10-23 14:05:31.168533', NULL, 'Sobradinho', 23);
INSERT INTO endereco.cidade VALUES (4354, '2019-10-23 14:05:31.168533', NULL, 'Tabaí', 23);
INSERT INTO endereco.cidade VALUES (4356, '2019-10-23 14:05:31.168533', NULL, 'Tapera', 23);
INSERT INTO endereco.cidade VALUES (4357, '2019-10-23 14:05:31.168533', NULL, 'Tapes', 23);
INSERT INTO endereco.cidade VALUES (4359, '2019-10-23 14:05:31.168533', NULL, 'Taquari', 23);
INSERT INTO endereco.cidade VALUES (4361, '2019-10-23 14:05:31.168533', NULL, 'Tavares', 23);
INSERT INTO endereco.cidade VALUES (4363, '2019-10-23 14:05:31.168533', NULL, 'Terra De Areia', 23);
INSERT INTO endereco.cidade VALUES (4364, '2019-10-23 14:05:31.168533', NULL, 'Teutônia', 23);
INSERT INTO endereco.cidade VALUES (4366, '2019-10-23 14:05:31.168533', NULL, 'Tiradentes Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4368, '2019-10-23 14:05:31.168533', NULL, 'Torres', 23);
INSERT INTO endereco.cidade VALUES (4370, '2019-10-23 14:05:31.168533', NULL, 'Travesseiro', 23);
INSERT INTO endereco.cidade VALUES (4372, '2019-10-23 14:05:31.168533', NULL, 'Três Cachoeiras', 23);
INSERT INTO endereco.cidade VALUES (4373, '2019-10-23 14:05:31.168533', NULL, 'Três Coroas', 23);
INSERT INTO endereco.cidade VALUES (4375, '2019-10-23 14:05:31.168533', NULL, 'Três Forquilhas', 23);
INSERT INTO endereco.cidade VALUES (4377, '2019-10-23 14:05:31.168533', NULL, 'Três Passos', 23);
INSERT INTO endereco.cidade VALUES (4378, '2019-10-23 14:05:31.168533', NULL, 'Trindade Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4380, '2019-10-23 14:05:31.168533', NULL, 'Tucunduva', 23);
INSERT INTO endereco.cidade VALUES (4382, '2019-10-23 14:05:31.168533', NULL, 'Tupanci Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4384, '2019-10-23 14:05:31.168533', NULL, 'Tupandi', 23);
INSERT INTO endereco.cidade VALUES (4385, '2019-10-23 14:05:31.168533', NULL, 'Tuparendi', 23);
INSERT INTO endereco.cidade VALUES (4387, '2019-10-23 14:05:31.168533', NULL, 'Ubiretama', 23);
INSERT INTO endereco.cidade VALUES (4389, '2019-10-23 14:05:31.168533', NULL, 'Unistalda', 23);
INSERT INTO endereco.cidade VALUES (4391, '2019-10-23 14:05:31.168533', NULL, 'Vacaria', 23);
INSERT INTO endereco.cidade VALUES (4392, '2019-10-23 14:05:31.168533', NULL, 'Vale Do Sol', 23);
INSERT INTO endereco.cidade VALUES (4394, '2019-10-23 14:05:31.168533', NULL, 'Vale Verde', 23);
INSERT INTO endereco.cidade VALUES (4396, '2019-10-23 14:05:31.168533', NULL, 'Venâncio Aires', 23);
INSERT INTO endereco.cidade VALUES (4398, '2019-10-23 14:05:31.168533', NULL, 'Veranópolis', 23);
INSERT INTO endereco.cidade VALUES (4400, '2019-10-23 14:05:31.168533', NULL, 'Viadutos', 23);
INSERT INTO endereco.cidade VALUES (4401, '2019-10-23 14:05:31.168533', NULL, 'Viamão', 23);
INSERT INTO endereco.cidade VALUES (4404, '2019-10-23 14:05:31.168533', NULL, 'Vila Flores', 23);
INSERT INTO endereco.cidade VALUES (4406, '2019-10-23 14:05:31.168533', NULL, 'Vila Maria', 23);
INSERT INTO endereco.cidade VALUES (4408, '2019-10-23 14:05:31.168533', NULL, 'Vista Alegre', 23);
INSERT INTO endereco.cidade VALUES (4409, '2019-10-23 14:05:31.168533', NULL, 'Vista Alegre Do Prata', 23);
INSERT INTO endereco.cidade VALUES (4411, '2019-10-23 14:05:31.168533', NULL, 'Vitória Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4412, '2019-10-23 14:05:31.168533', NULL, 'Westfália', 23);
INSERT INTO endereco.cidade VALUES (4414, '2019-10-23 14:05:31.168533', NULL, 'Abdon Batista', 24);
INSERT INTO endereco.cidade VALUES (4416, '2019-10-23 14:05:31.168533', NULL, 'Agrolândia', 24);
INSERT INTO endereco.cidade VALUES (4418, '2019-10-23 14:05:31.168533', NULL, 'Água Doce', 24);
INSERT INTO endereco.cidade VALUES (4420, '2019-10-23 14:05:31.168533', NULL, 'Águas Frias', 24);
INSERT INTO endereco.cidade VALUES (4421, '2019-10-23 14:05:31.168533', NULL, 'Águas Mornas', 24);
INSERT INTO endereco.cidade VALUES (4423, '2019-10-23 14:05:31.168533', NULL, 'Alto Bela Vista', 24);
INSERT INTO endereco.cidade VALUES (4425, '2019-10-23 14:05:31.168533', NULL, 'Angelina', 24);
INSERT INTO endereco.cidade VALUES (4427, '2019-10-23 14:05:31.168533', NULL, 'Anitápolis', 24);
INSERT INTO endereco.cidade VALUES (4428, '2019-10-23 14:05:31.168533', NULL, 'Antônio Carlos', 24);
INSERT INTO endereco.cidade VALUES (4430, '2019-10-23 14:05:31.168533', NULL, 'Arabutã', 24);
INSERT INTO endereco.cidade VALUES (4432, '2019-10-23 14:05:31.168533', NULL, 'Araranguá', 24);
INSERT INTO endereco.cidade VALUES (4433, '2019-10-23 14:05:31.168533', NULL, 'Armazém', 24);
INSERT INTO endereco.cidade VALUES (4435, '2019-10-23 14:05:31.168533', NULL, 'Arvoredo', 24);
INSERT INTO endereco.cidade VALUES (4437, '2019-10-23 14:05:31.168533', NULL, 'Atalanta', 24);
INSERT INTO endereco.cidade VALUES (4439, '2019-10-23 14:05:31.168533', NULL, 'Balneário Arroio Do Silva', 24);
INSERT INTO endereco.cidade VALUES (4441, '2019-10-23 14:05:31.168533', NULL, 'Balneário Camboriú', 24);
INSERT INTO endereco.cidade VALUES (4442, '2019-10-23 14:05:31.168533', NULL, 'Balneário Gaivota', 24);
INSERT INTO endereco.cidade VALUES (4444, '2019-10-23 14:05:31.168533', NULL, 'Bandeirante', 24);
INSERT INTO endereco.cidade VALUES (4445, '2019-10-23 14:05:31.168533', NULL, 'Barra Bonita', 24);
INSERT INTO endereco.cidade VALUES (4447, '2019-10-23 14:05:31.168533', NULL, 'Bela Vista Do Toldo', 24);
INSERT INTO endereco.cidade VALUES (4449, '2019-10-23 14:05:31.168533', NULL, 'Benedito Novo', 24);
INSERT INTO endereco.cidade VALUES (4451, '2019-10-23 14:05:31.168533', NULL, 'Blumenau', 24);
INSERT INTO endereco.cidade VALUES (4453, '2019-10-23 14:05:31.168533', NULL, 'Bom Jardim Da Serra', 24);
INSERT INTO endereco.cidade VALUES (4454, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus', 24);
INSERT INTO endereco.cidade VALUES (4456, '2019-10-23 14:05:31.168533', NULL, 'Bom Retiro', 24);
INSERT INTO endereco.cidade VALUES (4457, '2019-10-23 14:05:31.168533', NULL, 'Bombinhas', 24);
INSERT INTO endereco.cidade VALUES (4459, '2019-10-23 14:05:31.168533', NULL, 'Braço Do Norte', 24);
INSERT INTO endereco.cidade VALUES (4461, '2019-10-23 14:05:31.168533', NULL, 'Brunópolis', 24);
INSERT INTO endereco.cidade VALUES (4463, '2019-10-23 14:05:31.168533', NULL, 'Caçador', 24);
INSERT INTO endereco.cidade VALUES (4464, '2019-10-23 14:05:31.168533', NULL, 'Caibi', 24);
INSERT INTO endereco.cidade VALUES (4466, '2019-10-23 14:05:31.168533', NULL, 'Camboriú', 24);
INSERT INTO endereco.cidade VALUES (4468, '2019-10-23 14:05:31.168533', NULL, 'Campo Belo Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4470, '2019-10-23 14:05:31.168533', NULL, 'Campos Novos', 24);
INSERT INTO endereco.cidade VALUES (4471, '2019-10-23 14:05:31.168533', NULL, 'Canelinha', 24);
INSERT INTO endereco.cidade VALUES (4473, '2019-10-23 14:05:31.168533', NULL, 'Capão Alto', 24);
INSERT INTO endereco.cidade VALUES (4475, '2019-10-23 14:05:31.168533', NULL, 'Capivari De Baixo', 24);
INSERT INTO endereco.cidade VALUES (4477, '2019-10-23 14:05:31.168533', NULL, 'Caxambu Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4479, '2019-10-23 14:05:31.168533', NULL, 'Cerro Negro', 24);
INSERT INTO endereco.cidade VALUES (4481, '2019-10-23 14:05:31.168533', NULL, 'Chapecó', 24);
INSERT INTO endereco.cidade VALUES (4482, '2019-10-23 14:05:31.168533', NULL, 'Cocal Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4484, '2019-10-23 14:05:31.168533', NULL, 'Cordilheira Alta', 24);
INSERT INTO endereco.cidade VALUES (4486, '2019-10-23 14:05:31.168533', NULL, 'Coronel Martins', 24);
INSERT INTO endereco.cidade VALUES (4488, '2019-10-23 14:05:31.168533', NULL, 'Corupá', 24);
INSERT INTO endereco.cidade VALUES (4489, '2019-10-23 14:05:31.168533', NULL, 'Criciúma', 24);
INSERT INTO endereco.cidade VALUES (4491, '2019-10-23 14:05:31.168533', NULL, 'Cunhataí', 24);
INSERT INTO endereco.cidade VALUES (4493, '2019-10-23 14:05:31.168533', NULL, 'Descanso', 24);
INSERT INTO endereco.cidade VALUES (4495, '2019-10-23 14:05:31.168533', NULL, 'Dona Emma', 24);
INSERT INTO endereco.cidade VALUES (4496, '2019-10-23 14:05:31.168533', NULL, 'Doutor Pedrinho', 24);
INSERT INTO endereco.cidade VALUES (4498, '2019-10-23 14:05:31.168533', NULL, 'Ermo', 24);
INSERT INTO endereco.cidade VALUES (4499, '2019-10-23 14:05:31.168533', NULL, 'Erval Velho', 24);
INSERT INTO endereco.cidade VALUES (4501, '2019-10-23 14:05:31.168533', NULL, 'Flor Do Sertão', 24);
INSERT INTO endereco.cidade VALUES (4503, '2019-10-23 14:05:31.168533', NULL, 'Formosa Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4505, '2019-10-23 14:05:31.168533', NULL, 'Fraiburgo', 24);
INSERT INTO endereco.cidade VALUES (4506, '2019-10-23 14:05:31.168533', NULL, 'Frei Rogério', 24);
INSERT INTO endereco.cidade VALUES (4508, '2019-10-23 14:05:31.168533', NULL, 'Garopaba', 24);
INSERT INTO endereco.cidade VALUES (4510, '2019-10-23 14:05:31.168533', NULL, 'Gaspar', 24);
INSERT INTO endereco.cidade VALUES (4512, '2019-10-23 14:05:31.168533', NULL, 'Grão Pará', 24);
INSERT INTO endereco.cidade VALUES (4513, '2019-10-23 14:05:31.168533', NULL, 'Gravatal', 24);
INSERT INTO endereco.cidade VALUES (4515, '2019-10-23 14:05:31.168533', NULL, 'Guaraciaba', 24);
INSERT INTO endereco.cidade VALUES (4517, '2019-10-23 14:05:31.168533', NULL, 'Guarujá Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4519, '2019-10-23 14:05:31.168533', NULL, 'Herval D`Oeste', 24);
INSERT INTO endereco.cidade VALUES (4520, '2019-10-23 14:05:31.168533', NULL, 'Ibiam', 24);
INSERT INTO endereco.cidade VALUES (4522, '2019-10-23 14:05:31.168533', NULL, 'Ibirama', 24);
INSERT INTO endereco.cidade VALUES (4524, '2019-10-23 14:05:31.168533', NULL, 'Ilhota', 24);
INSERT INTO endereco.cidade VALUES (4526, '2019-10-23 14:05:31.168533', NULL, 'Imbituba', 24);
INSERT INTO endereco.cidade VALUES (4527, '2019-10-23 14:05:31.168533', NULL, 'Imbuia', 24);
INSERT INTO endereco.cidade VALUES (4529, '2019-10-23 14:05:31.168533', NULL, 'Iomerê', 24);
INSERT INTO endereco.cidade VALUES (5426, '2019-10-23 14:05:31.168533', NULL, 'Zacarias', 26);
INSERT INTO endereco.cidade VALUES (4532, '2019-10-23 14:05:31.168533', NULL, 'Ipuaçu', 24);
INSERT INTO endereco.cidade VALUES (4533, '2019-10-23 14:05:31.168533', NULL, 'Ipumirim', 24);
INSERT INTO endereco.cidade VALUES (4535, '2019-10-23 14:05:31.168533', NULL, 'Irani', 24);
INSERT INTO endereco.cidade VALUES (4538, '2019-10-23 14:05:31.168533', NULL, 'Itá', 24);
INSERT INTO endereco.cidade VALUES (4540, '2019-10-23 14:05:31.168533', NULL, 'Itajaí', 24);
INSERT INTO endereco.cidade VALUES (4542, '2019-10-23 14:05:31.168533', NULL, 'Itapiranga', 24);
INSERT INTO endereco.cidade VALUES (4544, '2019-10-23 14:05:31.168533', NULL, 'Ituporanga', 24);
INSERT INTO endereco.cidade VALUES (4545, '2019-10-23 14:05:31.168533', NULL, 'Jaborá', 24);
INSERT INTO endereco.cidade VALUES (4547, '2019-10-23 14:05:31.168533', NULL, 'Jaguaruna', 24);
INSERT INTO endereco.cidade VALUES (4549, '2019-10-23 14:05:31.168533', NULL, 'Jardinópolis', 24);
INSERT INTO endereco.cidade VALUES (4551, '2019-10-23 14:05:31.168533', NULL, 'Joinville', 24);
INSERT INTO endereco.cidade VALUES (4552, '2019-10-23 14:05:31.168533', NULL, 'José Boiteux', 24);
INSERT INTO endereco.cidade VALUES (4554, '2019-10-23 14:05:31.168533', NULL, 'Lacerdópolis', 24);
INSERT INTO endereco.cidade VALUES (4556, '2019-10-23 14:05:31.168533', NULL, 'Laguna', 24);
INSERT INTO endereco.cidade VALUES (4558, '2019-10-23 14:05:31.168533', NULL, 'Laurentino', 24);
INSERT INTO endereco.cidade VALUES (4559, '2019-10-23 14:05:31.168533', NULL, 'Lauro Muller', 24);
INSERT INTO endereco.cidade VALUES (4561, '2019-10-23 14:05:31.168533', NULL, 'Leoberto Leal', 24);
INSERT INTO endereco.cidade VALUES (4563, '2019-10-23 14:05:31.168533', NULL, 'Lontras', 24);
INSERT INTO endereco.cidade VALUES (4564, '2019-10-23 14:05:31.168533', NULL, 'Luiz Alves', 24);
INSERT INTO endereco.cidade VALUES (4566, '2019-10-23 14:05:31.168533', NULL, 'Macieira', 24);
INSERT INTO endereco.cidade VALUES (4568, '2019-10-23 14:05:31.168533', NULL, 'Major Gercino', 24);
INSERT INTO endereco.cidade VALUES (4570, '2019-10-23 14:05:31.168533', NULL, 'Maracajá', 24);
INSERT INTO endereco.cidade VALUES (4571, '2019-10-23 14:05:31.168533', NULL, 'Maravilha', 24);
INSERT INTO endereco.cidade VALUES (4573, '2019-10-23 14:05:31.168533', NULL, 'Massaranduba', 24);
INSERT INTO endereco.cidade VALUES (4575, '2019-10-23 14:05:31.168533', NULL, 'Meleiro', 24);
INSERT INTO endereco.cidade VALUES (4577, '2019-10-23 14:05:31.168533', NULL, 'Modelo', 24);
INSERT INTO endereco.cidade VALUES (4579, '2019-10-23 14:05:31.168533', NULL, 'Monte Carlo', 24);
INSERT INTO endereco.cidade VALUES (4581, '2019-10-23 14:05:31.168533', NULL, 'Morro Da Fumaça', 24);
INSERT INTO endereco.cidade VALUES (4582, '2019-10-23 14:05:31.168533', NULL, 'Morro Grande', 24);
INSERT INTO endereco.cidade VALUES (4584, '2019-10-23 14:05:31.168533', NULL, 'Nova Erechim', 24);
INSERT INTO endereco.cidade VALUES (4586, '2019-10-23 14:05:31.168533', NULL, 'Nova Trento', 24);
INSERT INTO endereco.cidade VALUES (4587, '2019-10-23 14:05:31.168533', NULL, 'Nova Veneza', 24);
INSERT INTO endereco.cidade VALUES (4589, '2019-10-23 14:05:31.168533', NULL, 'Orleans', 24);
INSERT INTO endereco.cidade VALUES (4591, '2019-10-23 14:05:31.168533', NULL, 'Ouro', 24);
INSERT INTO endereco.cidade VALUES (4592, '2019-10-23 14:05:31.168533', NULL, 'Ouro Verde', 24);
INSERT INTO endereco.cidade VALUES (4594, '2019-10-23 14:05:31.168533', NULL, 'Painel', 24);
INSERT INTO endereco.cidade VALUES (4596, '2019-10-23 14:05:31.168533', NULL, 'Palma Sola', 24);
INSERT INTO endereco.cidade VALUES (4598, '2019-10-23 14:05:31.168533', NULL, 'Palmitos', 24);
INSERT INTO endereco.cidade VALUES (4599, '2019-10-23 14:05:31.168533', NULL, 'Papanduva', 24);
INSERT INTO endereco.cidade VALUES (4601, '2019-10-23 14:05:31.168533', NULL, 'Passo De Torres', 24);
INSERT INTO endereco.cidade VALUES (4603, '2019-10-23 14:05:31.168533', NULL, 'Paulo Lopes', 24);
INSERT INTO endereco.cidade VALUES (4605, '2019-10-23 14:05:31.168533', NULL, 'Penha', 24);
INSERT INTO endereco.cidade VALUES (4606, '2019-10-23 14:05:31.168533', NULL, 'Peritiba', 24);
INSERT INTO endereco.cidade VALUES (4608, '2019-10-23 14:05:31.168533', NULL, 'Pinhalzinho', 24);
INSERT INTO endereco.cidade VALUES (4610, '2019-10-23 14:05:31.168533', NULL, 'Piratuba', 24);
INSERT INTO endereco.cidade VALUES (4612, '2019-10-23 14:05:31.168533', NULL, 'Pomerode', 24);
INSERT INTO endereco.cidade VALUES (4613, '2019-10-23 14:05:31.168533', NULL, 'Ponte Alta', 24);
INSERT INTO endereco.cidade VALUES (4615, '2019-10-23 14:05:31.168533', NULL, 'Ponte Serrada', 24);
INSERT INTO endereco.cidade VALUES (4617, '2019-10-23 14:05:31.168533', NULL, 'Porto União', 24);
INSERT INTO endereco.cidade VALUES (4619, '2019-10-23 14:05:31.168533', NULL, 'Praia Grande', 24);
INSERT INTO endereco.cidade VALUES (4620, '2019-10-23 14:05:31.168533', NULL, 'Presidente Castello Branco', 24);
INSERT INTO endereco.cidade VALUES (4622, '2019-10-23 14:05:31.168533', NULL, 'Presidente Nereu', 24);
INSERT INTO endereco.cidade VALUES (4624, '2019-10-23 14:05:31.168533', NULL, 'Quilombo', 24);
INSERT INTO endereco.cidade VALUES (4626, '2019-10-23 14:05:31.168533', NULL, 'Rio Das Antas', 24);
INSERT INTO endereco.cidade VALUES (4627, '2019-10-23 14:05:31.168533', NULL, 'Rio Do Campo', 24);
INSERT INTO endereco.cidade VALUES (4629, '2019-10-23 14:05:31.168533', NULL, 'Rio Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4631, '2019-10-23 14:05:31.168533', NULL, 'Rio Fortuna', 24);
INSERT INTO endereco.cidade VALUES (4632, '2019-10-23 14:05:31.168533', NULL, 'Rio Negrinho', 24);
INSERT INTO endereco.cidade VALUES (4634, '2019-10-23 14:05:31.168533', NULL, 'Riqueza', 24);
INSERT INTO endereco.cidade VALUES (4636, '2019-10-23 14:05:31.168533', NULL, 'Romelândia', 24);
INSERT INTO endereco.cidade VALUES (4637, '2019-10-23 14:05:31.168533', NULL, 'Salete', 24);
INSERT INTO endereco.cidade VALUES (4639, '2019-10-23 14:05:31.168533', NULL, 'Salto Veloso', 24);
INSERT INTO endereco.cidade VALUES (4641, '2019-10-23 14:05:31.168533', NULL, 'Santa Cecília', 24);
INSERT INTO endereco.cidade VALUES (4643, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa De Lima', 24);
INSERT INTO endereco.cidade VALUES (4645, '2019-10-23 14:05:31.168533', NULL, 'Santa Terezinha', 24);
INSERT INTO endereco.cidade VALUES (4646, '2019-10-23 14:05:31.168533', NULL, 'Santa Terezinha Do Progresso', 24);
INSERT INTO endereco.cidade VALUES (4648, '2019-10-23 14:05:31.168533', NULL, 'Santo Amaro Da Imperatriz', 24);
INSERT INTO endereco.cidade VALUES (4650, '2019-10-23 14:05:31.168533', NULL, 'São Bernardino', 24);
INSERT INTO endereco.cidade VALUES (4651, '2019-10-23 14:05:31.168533', NULL, 'São Bonifácio', 24);
INSERT INTO endereco.cidade VALUES (4653, '2019-10-23 14:05:31.168533', NULL, 'São Cristovão Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4655, '2019-10-23 14:05:31.168533', NULL, 'São Francisco Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4658, '2019-10-23 14:05:31.168533', NULL, 'São João Do Oeste', 24);
INSERT INTO endereco.cidade VALUES (4660, '2019-10-23 14:05:31.168533', NULL, 'São Joaquim', 24);
INSERT INTO endereco.cidade VALUES (4661, '2019-10-23 14:05:31.168533', NULL, 'São José', 24);
INSERT INTO endereco.cidade VALUES (4663, '2019-10-23 14:05:31.168533', NULL, 'São José Do Cerrito', 24);
INSERT INTO endereco.cidade VALUES (4665, '2019-10-23 14:05:31.168533', NULL, 'São Ludgero', 24);
INSERT INTO endereco.cidade VALUES (4667, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Da Boa Vista', 24);
INSERT INTO endereco.cidade VALUES (4668, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Oeste', 24);
INSERT INTO endereco.cidade VALUES (4670, '2019-10-23 14:05:31.168533', NULL, 'Saudades', 24);
INSERT INTO endereco.cidade VALUES (4671, '2019-10-23 14:05:31.168533', NULL, 'Schroeder', 24);
INSERT INTO endereco.cidade VALUES (4673, '2019-10-23 14:05:31.168533', NULL, 'Serra Alta', 24);
INSERT INTO endereco.cidade VALUES (4675, '2019-10-23 14:05:31.168533', NULL, 'Sombrio', 24);
INSERT INTO endereco.cidade VALUES (4677, '2019-10-23 14:05:31.168533', NULL, 'Taió', 24);
INSERT INTO endereco.cidade VALUES (4678, '2019-10-23 14:05:31.168533', NULL, 'Tangará', 24);
INSERT INTO endereco.cidade VALUES (4680, '2019-10-23 14:05:31.168533', NULL, 'Tijucas', 24);
INSERT INTO endereco.cidade VALUES (4682, '2019-10-23 14:05:31.168533', NULL, 'Timbó', 24);
INSERT INTO endereco.cidade VALUES (4684, '2019-10-23 14:05:31.168533', NULL, 'Três Barras', 24);
INSERT INTO endereco.cidade VALUES (4685, '2019-10-23 14:05:31.168533', NULL, 'Treviso', 24);
INSERT INTO endereco.cidade VALUES (4687, '2019-10-23 14:05:31.168533', NULL, 'Treze Tílias', 24);
INSERT INTO endereco.cidade VALUES (4689, '2019-10-23 14:05:31.168533', NULL, 'Tubarão', 24);
INSERT INTO endereco.cidade VALUES (4690, '2019-10-23 14:05:31.168533', NULL, 'Tunápolis', 24);
INSERT INTO endereco.cidade VALUES (4692, '2019-10-23 14:05:31.168533', NULL, 'União Do Oeste', 24);
INSERT INTO endereco.cidade VALUES (4694, '2019-10-23 14:05:31.168533', NULL, 'Urupema', 24);
INSERT INTO endereco.cidade VALUES (4696, '2019-10-23 14:05:31.168533', NULL, 'Vargeão', 24);
INSERT INTO endereco.cidade VALUES (4697, '2019-10-23 14:05:31.168533', NULL, 'Vargem', 24);
INSERT INTO endereco.cidade VALUES (4699, '2019-10-23 14:05:31.168533', NULL, 'Vidal Ramos', 24);
INSERT INTO endereco.cidade VALUES (4701, '2019-10-23 14:05:31.168533', NULL, 'Vitor Meireles', 24);
INSERT INTO endereco.cidade VALUES (4703, '2019-10-23 14:05:31.168533', NULL, 'Xanxerê', 24);
INSERT INTO endereco.cidade VALUES (4705, '2019-10-23 14:05:31.168533', NULL, 'Xaxim', 24);
INSERT INTO endereco.cidade VALUES (4706, '2019-10-23 14:05:31.168533', NULL, 'Zortéa', 24);
INSERT INTO endereco.cidade VALUES (4708, '2019-10-23 14:05:31.168533', NULL, 'Aquidabã', 25);
INSERT INTO endereco.cidade VALUES (4710, '2019-10-23 14:05:31.168533', NULL, 'Arauá', 25);
INSERT INTO endereco.cidade VALUES (4712, '2019-10-23 14:05:31.168533', NULL, 'Barra Dos Coqueiros', 25);
INSERT INTO endereco.cidade VALUES (4713, '2019-10-23 14:05:31.168533', NULL, 'Boquim', 25);
INSERT INTO endereco.cidade VALUES (4715, '2019-10-23 14:05:31.168533', NULL, 'Campo Do Brito', 25);
INSERT INTO endereco.cidade VALUES (4717, '2019-10-23 14:05:31.168533', NULL, 'Canindé De São Francisco', 25);
INSERT INTO endereco.cidade VALUES (4718, '2019-10-23 14:05:31.168533', NULL, 'Capela', 25);
INSERT INTO endereco.cidade VALUES (4720, '2019-10-23 14:05:31.168533', NULL, 'Carmópolis', 25);
INSERT INTO endereco.cidade VALUES (4722, '2019-10-23 14:05:31.168533', NULL, 'Cristinápolis', 25);
INSERT INTO endereco.cidade VALUES (4724, '2019-10-23 14:05:31.168533', NULL, 'Divina Pastora', 25);
INSERT INTO endereco.cidade VALUES (4726, '2019-10-23 14:05:31.168533', NULL, 'Feira Nova', 25);
INSERT INTO endereco.cidade VALUES (4727, '2019-10-23 14:05:31.168533', NULL, 'Frei Paulo', 25);
INSERT INTO endereco.cidade VALUES (4729, '2019-10-23 14:05:31.168533', NULL, 'General Maynard', 25);
INSERT INTO endereco.cidade VALUES (4731, '2019-10-23 14:05:31.168533', NULL, 'Ilha Das Flores', 25);
INSERT INTO endereco.cidade VALUES (4733, '2019-10-23 14:05:31.168533', NULL, 'Itabaiana', 25);
INSERT INTO endereco.cidade VALUES (4734, '2019-10-23 14:05:31.168533', NULL, 'Itabaianinha', 25);
INSERT INTO endereco.cidade VALUES (4736, '2019-10-23 14:05:31.168533', NULL, 'Itaporanga D`Ajuda', 25);
INSERT INTO endereco.cidade VALUES (4738, '2019-10-23 14:05:31.168533', NULL, 'Japoatã', 25);
INSERT INTO endereco.cidade VALUES (4740, '2019-10-23 14:05:31.168533', NULL, 'Laranjeiras', 25);
INSERT INTO endereco.cidade VALUES (4741, '2019-10-23 14:05:31.168533', NULL, 'Macambira', 25);
INSERT INTO endereco.cidade VALUES (4743, '2019-10-23 14:05:31.168533', NULL, 'Malhador', 25);
INSERT INTO endereco.cidade VALUES (4745, '2019-10-23 14:05:31.168533', NULL, 'Moita Bonita', 25);
INSERT INTO endereco.cidade VALUES (4747, '2019-10-23 14:05:31.168533', NULL, 'Muribeca', 25);
INSERT INTO endereco.cidade VALUES (4748, '2019-10-23 14:05:31.168533', NULL, 'Neópolis', 25);
INSERT INTO endereco.cidade VALUES (4750, '2019-10-23 14:05:31.168533', NULL, 'Nossa Senhora Da Glória', 25);
INSERT INTO endereco.cidade VALUES (4752, '2019-10-23 14:05:31.168533', NULL, 'Nossa Senhora De Lourdes', 25);
INSERT INTO endereco.cidade VALUES (4753, '2019-10-23 14:05:31.168533', NULL, 'Nossa Senhora Do Socorro', 25);
INSERT INTO endereco.cidade VALUES (4755, '2019-10-23 14:05:31.168533', NULL, 'Pedra Mole', 25);
INSERT INTO endereco.cidade VALUES (4757, '2019-10-23 14:05:31.168533', NULL, 'Pinhão', 25);
INSERT INTO endereco.cidade VALUES (4758, '2019-10-23 14:05:31.168533', NULL, 'Pirambu', 25);
INSERT INTO endereco.cidade VALUES (4760, '2019-10-23 14:05:31.168533', NULL, 'Poço Verde', 25);
INSERT INTO endereco.cidade VALUES (4762, '2019-10-23 14:05:31.168533', NULL, 'Propriá', 25);
INSERT INTO endereco.cidade VALUES (4764, '2019-10-23 14:05:31.168533', NULL, 'Riachuelo', 25);
INSERT INTO endereco.cidade VALUES (4765, '2019-10-23 14:05:31.168533', NULL, 'Ribeirópolis', 25);
INSERT INTO endereco.cidade VALUES (4767, '2019-10-23 14:05:31.168533', NULL, 'Salgado', 25);
INSERT INTO endereco.cidade VALUES (4769, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa De Lima', 25);
INSERT INTO endereco.cidade VALUES (4770, '2019-10-23 14:05:31.168533', NULL, 'Santana Do São Francisco', 25);
INSERT INTO endereco.cidade VALUES (4772, '2019-10-23 14:05:31.168533', NULL, 'São Cristóvão', 25);
INSERT INTO endereco.cidade VALUES (4774, '2019-10-23 14:05:31.168533', NULL, 'São Francisco', 25);
INSERT INTO endereco.cidade VALUES (4775, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Aleixo', 25);
INSERT INTO endereco.cidade VALUES (4777, '2019-10-23 14:05:31.168533', NULL, 'Siriri', 25);
INSERT INTO endereco.cidade VALUES (4779, '2019-10-23 14:05:31.168533', NULL, 'Tobias Barreto', 25);
INSERT INTO endereco.cidade VALUES (4781, '2019-10-23 14:05:31.168533', NULL, 'Umbaúba', 25);
INSERT INTO endereco.cidade VALUES (4784, '2019-10-23 14:05:31.168533', NULL, 'Aguaí', 26);
INSERT INTO endereco.cidade VALUES (4786, '2019-10-23 14:05:31.168533', NULL, 'Águas De Lindóia', 26);
INSERT INTO endereco.cidade VALUES (4788, '2019-10-23 14:05:31.168533', NULL, 'Águas De São Pedro', 26);
INSERT INTO endereco.cidade VALUES (4789, '2019-10-23 14:05:31.168533', NULL, 'Agudos', 26);
INSERT INTO endereco.cidade VALUES (4791, '2019-10-23 14:05:31.168533', NULL, 'Alfredo Marcondes', 26);
INSERT INTO endereco.cidade VALUES (4793, '2019-10-23 14:05:31.168533', NULL, 'Altinópolis', 26);
INSERT INTO endereco.cidade VALUES (4794, '2019-10-23 14:05:31.168533', NULL, 'Alto Alegre', 26);
INSERT INTO endereco.cidade VALUES (4797, '2019-10-23 14:05:31.168533', NULL, 'Álvares Machado', 26);
INSERT INTO endereco.cidade VALUES (4798, '2019-10-23 14:05:31.168533', NULL, 'Álvaro De Carvalho', 26);
INSERT INTO endereco.cidade VALUES (4800, '2019-10-23 14:05:31.168533', NULL, 'Americana', 26);
INSERT INTO endereco.cidade VALUES (4802, '2019-10-23 14:05:31.168533', NULL, 'Américo De Campos', 26);
INSERT INTO endereco.cidade VALUES (4803, '2019-10-23 14:05:31.168533', NULL, 'Amparo', 26);
INSERT INTO endereco.cidade VALUES (4805, '2019-10-23 14:05:31.168533', NULL, 'Andradina', 26);
INSERT INTO endereco.cidade VALUES (4807, '2019-10-23 14:05:31.168533', NULL, 'Anhembi', 26);
INSERT INTO endereco.cidade VALUES (4808, '2019-10-23 14:05:31.168533', NULL, 'Anhumas', 26);
INSERT INTO endereco.cidade VALUES (4810, '2019-10-23 14:05:31.168533', NULL, 'Aparecida D`Oeste', 26);
INSERT INTO endereco.cidade VALUES (4812, '2019-10-23 14:05:31.168533', NULL, 'Araçariguama', 26);
INSERT INTO endereco.cidade VALUES (4814, '2019-10-23 14:05:31.168533', NULL, 'Araçoiaba Da Serra', 26);
INSERT INTO endereco.cidade VALUES (4816, '2019-10-23 14:05:31.168533', NULL, 'Arandu', 26);
INSERT INTO endereco.cidade VALUES (4817, '2019-10-23 14:05:31.168533', NULL, 'Arapeí', 26);
INSERT INTO endereco.cidade VALUES (4819, '2019-10-23 14:05:31.168533', NULL, 'Araras', 26);
INSERT INTO endereco.cidade VALUES (4821, '2019-10-23 14:05:31.168533', NULL, 'Arealva', 26);
INSERT INTO endereco.cidade VALUES (4823, '2019-10-23 14:05:31.168533', NULL, 'Areiópolis', 26);
INSERT INTO endereco.cidade VALUES (4825, '2019-10-23 14:05:31.168533', NULL, 'Artur Nogueira', 26);
INSERT INTO endereco.cidade VALUES (4826, '2019-10-23 14:05:31.168533', NULL, 'Arujá', 26);
INSERT INTO endereco.cidade VALUES (4828, '2019-10-23 14:05:31.168533', NULL, 'Assis', 26);
INSERT INTO endereco.cidade VALUES (4830, '2019-10-23 14:05:31.168533', NULL, 'Auriflama', 26);
INSERT INTO endereco.cidade VALUES (4832, '2019-10-23 14:05:31.168533', NULL, 'Avanhandava', 26);
INSERT INTO endereco.cidade VALUES (4833, '2019-10-23 14:05:31.168533', NULL, 'Avaré', 26);
INSERT INTO endereco.cidade VALUES (4835, '2019-10-23 14:05:31.168533', NULL, 'Balbinos', 26);
INSERT INTO endereco.cidade VALUES (4837, '2019-10-23 14:05:31.168533', NULL, 'Bananal', 26);
INSERT INTO endereco.cidade VALUES (4839, '2019-10-23 14:05:31.168533', NULL, 'Barbosa', 26);
INSERT INTO endereco.cidade VALUES (4840, '2019-10-23 14:05:31.168533', NULL, 'Bariri', 26);
INSERT INTO endereco.cidade VALUES (4842, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Chapéu', 26);
INSERT INTO endereco.cidade VALUES (4844, '2019-10-23 14:05:31.168533', NULL, 'Barretos', 26);
INSERT INTO endereco.cidade VALUES (4845, '2019-10-23 14:05:31.168533', NULL, 'Barrinha', 26);
INSERT INTO endereco.cidade VALUES (4847, '2019-10-23 14:05:31.168533', NULL, 'Bastos', 26);
INSERT INTO endereco.cidade VALUES (4849, '2019-10-23 14:05:31.168533', NULL, 'Bauru', 26);
INSERT INTO endereco.cidade VALUES (4851, '2019-10-23 14:05:31.168533', NULL, 'Bento De Abreu', 26);
INSERT INTO endereco.cidade VALUES (4853, '2019-10-23 14:05:31.168533', NULL, 'Bertioga', 26);
INSERT INTO endereco.cidade VALUES (4854, '2019-10-23 14:05:31.168533', NULL, 'Bilac', 26);
INSERT INTO endereco.cidade VALUES (4856, '2019-10-23 14:05:31.168533', NULL, 'Biritiba Mirim', 26);
INSERT INTO endereco.cidade VALUES (4858, '2019-10-23 14:05:31.168533', NULL, 'Bocaina', 26);
INSERT INTO endereco.cidade VALUES (4859, '2019-10-23 14:05:31.168533', NULL, 'Bofete', 26);
INSERT INTO endereco.cidade VALUES (4861, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Dos Perdões', 26);
INSERT INTO endereco.cidade VALUES (4863, '2019-10-23 14:05:31.168533', NULL, 'Borá', 26);
INSERT INTO endereco.cidade VALUES (4864, '2019-10-23 14:05:31.168533', NULL, 'Boracéia', 26);
INSERT INTO endereco.cidade VALUES (4866, '2019-10-23 14:05:31.168533', NULL, 'Borebi', 26);
INSERT INTO endereco.cidade VALUES (4868, '2019-10-23 14:05:31.168533', NULL, 'Bragança Paulista', 26);
INSERT INTO endereco.cidade VALUES (4870, '2019-10-23 14:05:31.168533', NULL, 'Brejo Alegre', 26);
INSERT INTO endereco.cidade VALUES (4871, '2019-10-23 14:05:31.168533', NULL, 'Brodowski', 26);
INSERT INTO endereco.cidade VALUES (4873, '2019-10-23 14:05:31.168533', NULL, 'Buri', 26);
INSERT INTO endereco.cidade VALUES (4875, '2019-10-23 14:05:31.168533', NULL, 'Buritizal', 26);
INSERT INTO endereco.cidade VALUES (4877, '2019-10-23 14:05:31.168533', NULL, 'Cabreúva', 26);
INSERT INTO endereco.cidade VALUES (4878, '2019-10-23 14:05:31.168533', NULL, 'Caçapava', 26);
INSERT INTO endereco.cidade VALUES (4880, '2019-10-23 14:05:31.168533', NULL, 'Caconde', 26);
INSERT INTO endereco.cidade VALUES (4882, '2019-10-23 14:05:31.168533', NULL, 'Caiabu', 26);
INSERT INTO endereco.cidade VALUES (4884, '2019-10-23 14:05:31.168533', NULL, 'Caiuá', 26);
INSERT INTO endereco.cidade VALUES (4885, '2019-10-23 14:05:31.168533', NULL, 'Cajamar', 26);
INSERT INTO endereco.cidade VALUES (4887, '2019-10-23 14:05:31.168533', NULL, 'Cajobi', 26);
INSERT INTO endereco.cidade VALUES (4889, '2019-10-23 14:05:31.168533', NULL, 'Campina Do Monte Alegre', 26);
INSERT INTO endereco.cidade VALUES (4891, '2019-10-23 14:05:31.168533', NULL, 'Campo Limpo Paulista', 26);
INSERT INTO endereco.cidade VALUES (4893, '2019-10-23 14:05:31.168533', NULL, 'Campos Novos Paulista', 26);
INSERT INTO endereco.cidade VALUES (4894, '2019-10-23 14:05:31.168533', NULL, 'Cananéia', 26);
INSERT INTO endereco.cidade VALUES (4896, '2019-10-23 14:05:31.168533', NULL, 'Cândido Mota', 26);
INSERT INTO endereco.cidade VALUES (4898, '2019-10-23 14:05:31.168533', NULL, 'Canitar', 26);
INSERT INTO endereco.cidade VALUES (4900, '2019-10-23 14:05:31.168533', NULL, 'Capela Do Alto', 26);
INSERT INTO endereco.cidade VALUES (4901, '2019-10-23 14:05:31.168533', NULL, 'Capivari', 26);
INSERT INTO endereco.cidade VALUES (4903, '2019-10-23 14:05:31.168533', NULL, 'Carapicuíba', 26);
INSERT INTO endereco.cidade VALUES (4905, '2019-10-23 14:05:31.168533', NULL, 'Casa Branca', 26);
INSERT INTO endereco.cidade VALUES (4907, '2019-10-23 14:05:31.168533', NULL, 'Castilho', 26);
INSERT INTO endereco.cidade VALUES (4908, '2019-10-23 14:05:31.168533', NULL, 'Catanduva', 26);
INSERT INTO endereco.cidade VALUES (4910, '2019-10-23 14:05:31.168533', NULL, 'Cedral', 26);
INSERT INTO endereco.cidade VALUES (4913, '2019-10-23 14:05:31.168533', NULL, 'Cesário Lange', 26);
INSERT INTO endereco.cidade VALUES (4915, '2019-10-23 14:05:31.168533', NULL, 'Chavantes', 26);
INSERT INTO endereco.cidade VALUES (4916, '2019-10-23 14:05:31.168533', NULL, 'Clementina', 26);
INSERT INTO endereco.cidade VALUES (4918, '2019-10-23 14:05:31.168533', NULL, 'Colômbia', 26);
INSERT INTO endereco.cidade VALUES (4920, '2019-10-23 14:05:31.168533', NULL, 'Conchas', 26);
INSERT INTO endereco.cidade VALUES (4922, '2019-10-23 14:05:31.168533', NULL, 'Coroados', 26);
INSERT INTO endereco.cidade VALUES (4924, '2019-10-23 14:05:31.168533', NULL, 'Corumbataí', 26);
INSERT INTO endereco.cidade VALUES (4925, '2019-10-23 14:05:31.168533', NULL, 'Cosmópolis', 26);
INSERT INTO endereco.cidade VALUES (4927, '2019-10-23 14:05:31.168533', NULL, 'Cotia', 26);
INSERT INTO endereco.cidade VALUES (4928, '2019-10-23 14:05:31.168533', NULL, 'Cravinhos', 26);
INSERT INTO endereco.cidade VALUES (4930, '2019-10-23 14:05:31.168533', NULL, 'Cruzália', 26);
INSERT INTO endereco.cidade VALUES (4932, '2019-10-23 14:05:31.168533', NULL, 'Cubatão', 26);
INSERT INTO endereco.cidade VALUES (4934, '2019-10-23 14:05:31.168533', NULL, 'Descalvado', 26);
INSERT INTO endereco.cidade VALUES (4935, '2019-10-23 14:05:31.168533', NULL, 'Diadema', 26);
INSERT INTO endereco.cidade VALUES (4937, '2019-10-23 14:05:31.168533', NULL, 'Divinolândia', 26);
INSERT INTO endereco.cidade VALUES (4939, '2019-10-23 14:05:31.168533', NULL, 'Dois Córregos', 26);
INSERT INTO endereco.cidade VALUES (4941, '2019-10-23 14:05:31.168533', NULL, 'Dourado', 26);
INSERT INTO endereco.cidade VALUES (4943, '2019-10-23 14:05:31.168533', NULL, 'Duartina', 26);
INSERT INTO endereco.cidade VALUES (4944, '2019-10-23 14:05:31.168533', NULL, 'Dumont', 26);
INSERT INTO endereco.cidade VALUES (4946, '2019-10-23 14:05:31.168533', NULL, 'Eldorado', 26);
INSERT INTO endereco.cidade VALUES (4948, '2019-10-23 14:05:31.168533', NULL, 'Elisiário', 26);
INSERT INTO endereco.cidade VALUES (4950, '2019-10-23 14:05:31.168533', NULL, 'Embu Das Artes', 26);
INSERT INTO endereco.cidade VALUES (4951, '2019-10-23 14:05:31.168533', NULL, 'Embu Guaçu', 26);
INSERT INTO endereco.cidade VALUES (4953, '2019-10-23 14:05:31.168533', NULL, 'Engenheiro Coelho', 26);
INSERT INTO endereco.cidade VALUES (4955, '2019-10-23 14:05:31.168533', NULL, 'Espírito Santo Do Turvo', 26);
INSERT INTO endereco.cidade VALUES (4957, '2019-10-23 14:05:31.168533', NULL, 'Estrela D`Oeste', 26);
INSERT INTO endereco.cidade VALUES (4958, '2019-10-23 14:05:31.168533', NULL, 'Estrela Do Norte', 26);
INSERT INTO endereco.cidade VALUES (4960, '2019-10-23 14:05:31.168533', NULL, 'Fartura', 26);
INSERT INTO endereco.cidade VALUES (4962, '2019-10-23 14:05:31.168533', NULL, 'Fernandópolis', 26);
INSERT INTO endereco.cidade VALUES (4963, '2019-10-23 14:05:31.168533', NULL, 'Fernão', 26);
INSERT INTO endereco.cidade VALUES (4965, '2019-10-23 14:05:31.168533', NULL, 'Flora Rica', 26);
INSERT INTO endereco.cidade VALUES (4966, '2019-10-23 14:05:31.168533', NULL, 'Floreal', 26);
INSERT INTO endereco.cidade VALUES (4968, '2019-10-23 14:05:31.168533', NULL, 'Flórida Paulista', 26);
INSERT INTO endereco.cidade VALUES (4970, '2019-10-23 14:05:31.168533', NULL, 'Francisco Morato', 26);
INSERT INTO endereco.cidade VALUES (4972, '2019-10-23 14:05:31.168533', NULL, 'Gabriel Monteiro', 26);
INSERT INTO endereco.cidade VALUES (4973, '2019-10-23 14:05:31.168533', NULL, 'Gália', 26);
INSERT INTO endereco.cidade VALUES (4975, '2019-10-23 14:05:31.168533', NULL, 'Gastão Vidigal', 26);
INSERT INTO endereco.cidade VALUES (4977, '2019-10-23 14:05:31.168533', NULL, 'General Salgado', 26);
INSERT INTO endereco.cidade VALUES (4979, '2019-10-23 14:05:31.168533', NULL, 'Glicério', 26);
INSERT INTO endereco.cidade VALUES (4980, '2019-10-23 14:05:31.168533', NULL, 'Guaiçara', 26);
INSERT INTO endereco.cidade VALUES (4982, '2019-10-23 14:05:31.168533', NULL, 'Guaíra', 26);
INSERT INTO endereco.cidade VALUES (4984, '2019-10-23 14:05:31.168533', NULL, 'Guapiara', 26);
INSERT INTO endereco.cidade VALUES (4986, '2019-10-23 14:05:31.168533', NULL, 'Guaraçaí', 26);
INSERT INTO endereco.cidade VALUES (4988, '2019-10-23 14:05:31.168533', NULL, 'Guarani D`Oeste', 26);
INSERT INTO endereco.cidade VALUES (4989, '2019-10-23 14:05:31.168533', NULL, 'Guarantã', 26);
INSERT INTO endereco.cidade VALUES (4991, '2019-10-23 14:05:31.168533', NULL, 'Guararema', 26);
INSERT INTO endereco.cidade VALUES (4993, '2019-10-23 14:05:31.168533', NULL, 'Guareí', 26);
INSERT INTO endereco.cidade VALUES (4995, '2019-10-23 14:05:31.168533', NULL, 'Guarujá', 26);
INSERT INTO endereco.cidade VALUES (4996, '2019-10-23 14:05:31.168533', NULL, 'Guarulhos', 26);
INSERT INTO endereco.cidade VALUES (4998, '2019-10-23 14:05:31.168533', NULL, 'Guzolândia', 26);
INSERT INTO endereco.cidade VALUES (5000, '2019-10-23 14:05:31.168533', NULL, 'Holambra', 26);
INSERT INTO endereco.cidade VALUES (5002, '2019-10-23 14:05:31.168533', NULL, 'Iacanga', 26);
INSERT INTO endereco.cidade VALUES (5003, '2019-10-23 14:05:31.168533', NULL, 'Iacri', 26);
INSERT INTO endereco.cidade VALUES (5005, '2019-10-23 14:05:31.168533', NULL, 'Ibaté', 26);
INSERT INTO endereco.cidade VALUES (5007, '2019-10-23 14:05:31.168533', NULL, 'Ibirarema', 26);
INSERT INTO endereco.cidade VALUES (5009, '2019-10-23 14:05:31.168533', NULL, 'Ibiúna', 26);
INSERT INTO endereco.cidade VALUES (5010, '2019-10-23 14:05:31.168533', NULL, 'Icém', 26);
INSERT INTO endereco.cidade VALUES (5012, '2019-10-23 14:05:31.168533', NULL, 'Igaraçu Do Tietê', 26);
INSERT INTO endereco.cidade VALUES (5014, '2019-10-23 14:05:31.168533', NULL, 'Igaratá', 26);
INSERT INTO endereco.cidade VALUES (5016, '2019-10-23 14:05:31.168533', NULL, 'Ilha Comprida', 26);
INSERT INTO endereco.cidade VALUES (5018, '2019-10-23 14:05:31.168533', NULL, 'Ilhabela', 26);
INSERT INTO endereco.cidade VALUES (5019, '2019-10-23 14:05:31.168533', NULL, 'Indaiatuba', 26);
INSERT INTO endereco.cidade VALUES (5021, '2019-10-23 14:05:31.168533', NULL, 'Indiaporã', 26);
INSERT INTO endereco.cidade VALUES (5023, '2019-10-23 14:05:31.168533', NULL, 'Ipaussu', 26);
INSERT INTO endereco.cidade VALUES (5024, '2019-10-23 14:05:31.168533', NULL, 'Iperó', 26);
INSERT INTO endereco.cidade VALUES (5026, '2019-10-23 14:05:31.168533', NULL, 'Ipiguá', 26);
INSERT INTO endereco.cidade VALUES (5028, '2019-10-23 14:05:31.168533', NULL, 'Ipuã', 26);
INSERT INTO endereco.cidade VALUES (5030, '2019-10-23 14:05:31.168533', NULL, 'Irapuã', 26);
INSERT INTO endereco.cidade VALUES (5031, '2019-10-23 14:05:31.168533', NULL, 'Irapuru', 26);
INSERT INTO endereco.cidade VALUES (5033, '2019-10-23 14:05:31.168533', NULL, 'Itaí', 26);
INSERT INTO endereco.cidade VALUES (5035, '2019-10-23 14:05:31.168533', NULL, 'Itaju', 26);
INSERT INTO endereco.cidade VALUES (5037, '2019-10-23 14:05:31.168533', NULL, 'Itaóca', 26);
INSERT INTO endereco.cidade VALUES (5039, '2019-10-23 14:05:31.168533', NULL, 'Itapetininga', 26);
INSERT INTO endereco.cidade VALUES (5040, '2019-10-23 14:05:31.168533', NULL, 'Itapeva', 26);
INSERT INTO endereco.cidade VALUES (5043, '2019-10-23 14:05:31.168533', NULL, 'Itapirapuã Paulista', 26);
INSERT INTO endereco.cidade VALUES (5044, '2019-10-23 14:05:31.168533', NULL, 'Itápolis', 26);
INSERT INTO endereco.cidade VALUES (5046, '2019-10-23 14:05:31.168533', NULL, 'Itapuí', 26);
INSERT INTO endereco.cidade VALUES (5048, '2019-10-23 14:05:31.168533', NULL, 'Itaquaquecetuba', 26);
INSERT INTO endereco.cidade VALUES (5050, '2019-10-23 14:05:31.168533', NULL, 'Itariri', 26);
INSERT INTO endereco.cidade VALUES (5052, '2019-10-23 14:05:31.168533', NULL, 'Itatinga', 26);
INSERT INTO endereco.cidade VALUES (5054, '2019-10-23 14:05:31.168533', NULL, 'Itirapuã', 26);
INSERT INTO endereco.cidade VALUES (5055, '2019-10-23 14:05:31.168533', NULL, 'Itobi', 26);
INSERT INTO endereco.cidade VALUES (5057, '2019-10-23 14:05:31.168533', NULL, 'Itupeva', 26);
INSERT INTO endereco.cidade VALUES (5059, '2019-10-23 14:05:31.168533', NULL, 'Jaborandi', 26);
INSERT INTO endereco.cidade VALUES (5061, '2019-10-23 14:05:31.168533', NULL, 'Jacareí', 26);
INSERT INTO endereco.cidade VALUES (5063, '2019-10-23 14:05:31.168533', NULL, 'Jacupiranga', 26);
INSERT INTO endereco.cidade VALUES (5064, '2019-10-23 14:05:31.168533', NULL, 'Jaguariúna', 26);
INSERT INTO endereco.cidade VALUES (5066, '2019-10-23 14:05:31.168533', NULL, 'Jambeiro', 26);
INSERT INTO endereco.cidade VALUES (5068, '2019-10-23 14:05:31.168533', NULL, 'Jardinópolis', 26);
INSERT INTO endereco.cidade VALUES (5070, '2019-10-23 14:05:31.168533', NULL, 'Jaú', 26);
INSERT INTO endereco.cidade VALUES (5071, '2019-10-23 14:05:31.168533', NULL, 'Jeriquara', 26);
INSERT INTO endereco.cidade VALUES (5073, '2019-10-23 14:05:31.168533', NULL, 'João Ramalho', 26);
INSERT INTO endereco.cidade VALUES (5075, '2019-10-23 14:05:31.168533', NULL, 'Júlio Mesquita', 26);
INSERT INTO endereco.cidade VALUES (5077, '2019-10-23 14:05:31.168533', NULL, 'Jundiaí', 26);
INSERT INTO endereco.cidade VALUES (5079, '2019-10-23 14:05:31.168533', NULL, 'Juquiá', 26);
INSERT INTO endereco.cidade VALUES (5080, '2019-10-23 14:05:31.168533', NULL, 'Juquitiba', 26);
INSERT INTO endereco.cidade VALUES (5082, '2019-10-23 14:05:31.168533', NULL, 'Laranjal Paulista', 26);
INSERT INTO endereco.cidade VALUES (5084, '2019-10-23 14:05:31.168533', NULL, 'Lavrinhas', 26);
INSERT INTO endereco.cidade VALUES (5085, '2019-10-23 14:05:31.168533', NULL, 'Leme', 26);
INSERT INTO endereco.cidade VALUES (5087, '2019-10-23 14:05:31.168533', NULL, 'Limeira', 26);
INSERT INTO endereco.cidade VALUES (5089, '2019-10-23 14:05:31.168533', NULL, 'Lins', 26);
INSERT INTO endereco.cidade VALUES (5090, '2019-10-23 14:05:31.168533', NULL, 'Lorena', 26);
INSERT INTO endereco.cidade VALUES (5092, '2019-10-23 14:05:31.168533', NULL, 'Louveira', 26);
INSERT INTO endereco.cidade VALUES (5094, '2019-10-23 14:05:31.168533', NULL, 'Lucianópolis', 26);
INSERT INTO endereco.cidade VALUES (5096, '2019-10-23 14:05:31.168533', NULL, 'Luiziânia', 26);
INSERT INTO endereco.cidade VALUES (5097, '2019-10-23 14:05:31.168533', NULL, 'Lupércio', 26);
INSERT INTO endereco.cidade VALUES (5099, '2019-10-23 14:05:31.168533', NULL, 'Macatuba', 26);
INSERT INTO endereco.cidade VALUES (5101, '2019-10-23 14:05:31.168533', NULL, 'Macedônia', 26);
INSERT INTO endereco.cidade VALUES (5103, '2019-10-23 14:05:31.168533', NULL, 'Mairinque', 26);
INSERT INTO endereco.cidade VALUES (5105, '2019-10-23 14:05:31.168533', NULL, 'Manduri', 26);
INSERT INTO endereco.cidade VALUES (5107, '2019-10-23 14:05:31.168533', NULL, 'Maracaí', 26);
INSERT INTO endereco.cidade VALUES (5108, '2019-10-23 14:05:31.168533', NULL, 'Marapoama', 26);
INSERT INTO endereco.cidade VALUES (5110, '2019-10-23 14:05:31.168533', NULL, 'Marília', 26);
INSERT INTO endereco.cidade VALUES (5112, '2019-10-23 14:05:31.168533', NULL, 'Martinópolis', 26);
INSERT INTO endereco.cidade VALUES (5113, '2019-10-23 14:05:31.168533', NULL, 'Matão', 26);
INSERT INTO endereco.cidade VALUES (5115, '2019-10-23 14:05:31.168533', NULL, 'Mendonça', 26);
INSERT INTO endereco.cidade VALUES (5117, '2019-10-23 14:05:31.168533', NULL, 'Mesópolis', 26);
INSERT INTO endereco.cidade VALUES (5119, '2019-10-23 14:05:31.168533', NULL, 'Mineiros Do Tietê', 26);
INSERT INTO endereco.cidade VALUES (5120, '2019-10-23 14:05:31.168533', NULL, 'Mira Estrela', 26);
INSERT INTO endereco.cidade VALUES (5122, '2019-10-23 14:05:31.168533', NULL, 'Mirandópolis', 26);
INSERT INTO endereco.cidade VALUES (5124, '2019-10-23 14:05:31.168533', NULL, 'Mirassol', 26);
INSERT INTO endereco.cidade VALUES (5125, '2019-10-23 14:05:31.168533', NULL, 'Mirassolândia', 26);
INSERT INTO endereco.cidade VALUES (5127, '2019-10-23 14:05:31.168533', NULL, 'Mogi Das Cruzes', 26);
INSERT INTO endereco.cidade VALUES (5129, '2019-10-23 14:05:31.168533', NULL, 'Moji Mirim', 26);
INSERT INTO endereco.cidade VALUES (5131, '2019-10-23 14:05:31.168533', NULL, 'Monções', 26);
INSERT INTO endereco.cidade VALUES (5132, '2019-10-23 14:05:31.168533', NULL, 'Mongaguá', 26);
INSERT INTO endereco.cidade VALUES (5134, '2019-10-23 14:05:31.168533', NULL, 'Monte Alto', 26);
INSERT INTO endereco.cidade VALUES (5136, '2019-10-23 14:05:31.168533', NULL, 'Monte Azul Paulista', 26);
INSERT INTO endereco.cidade VALUES (5138, '2019-10-23 14:05:31.168533', NULL, 'Monte Mor', 26);
INSERT INTO endereco.cidade VALUES (5139, '2019-10-23 14:05:31.168533', NULL, 'Monteiro Lobato', 26);
INSERT INTO endereco.cidade VALUES (5141, '2019-10-23 14:05:31.168533', NULL, 'Morungaba', 26);
INSERT INTO endereco.cidade VALUES (5143, '2019-10-23 14:05:31.168533', NULL, 'Murutinga Do Sul', 26);
INSERT INTO endereco.cidade VALUES (5144, '2019-10-23 14:05:31.168533', NULL, 'Nantes', 26);
INSERT INTO endereco.cidade VALUES (5146, '2019-10-23 14:05:31.168533', NULL, 'Natividade Da Serra', 26);
INSERT INTO endereco.cidade VALUES (5148, '2019-10-23 14:05:31.168533', NULL, 'Neves Paulista', 26);
INSERT INTO endereco.cidade VALUES (5149, '2019-10-23 14:05:31.168533', NULL, 'Nhandeara', 26);
INSERT INTO endereco.cidade VALUES (5151, '2019-10-23 14:05:31.168533', NULL, 'Nova Aliança', 26);
INSERT INTO endereco.cidade VALUES (5153, '2019-10-23 14:05:31.168533', NULL, 'Nova Canaã Paulista', 26);
INSERT INTO endereco.cidade VALUES (5155, '2019-10-23 14:05:31.168533', NULL, 'Nova Europa', 26);
INSERT INTO endereco.cidade VALUES (5156, '2019-10-23 14:05:31.168533', NULL, 'Nova Granada', 26);
INSERT INTO endereco.cidade VALUES (5158, '2019-10-23 14:05:31.168533', NULL, 'Nova Independência', 26);
INSERT INTO endereco.cidade VALUES (5160, '2019-10-23 14:05:31.168533', NULL, 'Nova Odessa', 26);
INSERT INTO endereco.cidade VALUES (5161, '2019-10-23 14:05:31.168533', NULL, 'Novais', 26);
INSERT INTO endereco.cidade VALUES (5163, '2019-10-23 14:05:31.168533', NULL, 'Nuporanga', 26);
INSERT INTO endereco.cidade VALUES (5165, '2019-10-23 14:05:31.168533', NULL, 'Óleo', 26);
INSERT INTO endereco.cidade VALUES (5166, '2019-10-23 14:05:31.168533', NULL, 'Olímpia', 26);
INSERT INTO endereco.cidade VALUES (5168, '2019-10-23 14:05:31.168533', NULL, 'Oriente', 26);
INSERT INTO endereco.cidade VALUES (5170, '2019-10-23 14:05:31.168533', NULL, 'Orlândia', 26);
INSERT INTO endereco.cidade VALUES (5428, '2019-10-23 14:05:31.168533', NULL, 'Aguiarnópolis', 27);
INSERT INTO endereco.cidade VALUES (5173, '2019-10-23 14:05:31.168533', NULL, 'Osvaldo Cruz', 26);
INSERT INTO endereco.cidade VALUES (5174, '2019-10-23 14:05:31.168533', NULL, 'Ourinhos', 26);
INSERT INTO endereco.cidade VALUES (5176, '2019-10-23 14:05:31.168533', NULL, 'Ouroeste', 26);
INSERT INTO endereco.cidade VALUES (5178, '2019-10-23 14:05:31.168533', NULL, 'Palestina', 26);
INSERT INTO endereco.cidade VALUES (5180, '2019-10-23 14:05:31.168533', NULL, 'Palmeira D`Oeste', 26);
INSERT INTO endereco.cidade VALUES (5181, '2019-10-23 14:05:31.168533', NULL, 'Palmital', 26);
INSERT INTO endereco.cidade VALUES (5183, '2019-10-23 14:05:31.168533', NULL, 'Paraguaçu Paulista', 26);
INSERT INTO endereco.cidade VALUES (5185, '2019-10-23 14:05:31.168533', NULL, 'Paraíso', 26);
INSERT INTO endereco.cidade VALUES (5187, '2019-10-23 14:05:31.168533', NULL, 'Paranapuã', 26);
INSERT INTO endereco.cidade VALUES (5188, '2019-10-23 14:05:31.168533', NULL, 'Parapuã', 26);
INSERT INTO endereco.cidade VALUES (5190, '2019-10-23 14:05:31.168533', NULL, 'Pariquera Açu', 26);
INSERT INTO endereco.cidade VALUES (5192, '2019-10-23 14:05:31.168533', NULL, 'Patrocínio Paulista', 26);
INSERT INTO endereco.cidade VALUES (5194, '2019-10-23 14:05:31.168533', NULL, 'Paulínia', 26);
INSERT INTO endereco.cidade VALUES (5195, '2019-10-23 14:05:31.168533', NULL, 'Paulistânia', 26);
INSERT INTO endereco.cidade VALUES (5197, '2019-10-23 14:05:31.168533', NULL, 'Pederneiras', 26);
INSERT INTO endereco.cidade VALUES (5199, '2019-10-23 14:05:31.168533', NULL, 'Pedranópolis', 26);
INSERT INTO endereco.cidade VALUES (5201, '2019-10-23 14:05:31.168533', NULL, 'Pedreira', 26);
INSERT INTO endereco.cidade VALUES (5202, '2019-10-23 14:05:31.168533', NULL, 'Pedrinhas Paulista', 26);
INSERT INTO endereco.cidade VALUES (5204, '2019-10-23 14:05:31.168533', NULL, 'Penápolis', 26);
INSERT INTO endereco.cidade VALUES (5206, '2019-10-23 14:05:31.168533', NULL, 'Pereiras', 26);
INSERT INTO endereco.cidade VALUES (5207, '2019-10-23 14:05:31.168533', NULL, 'Peruíbe', 26);
INSERT INTO endereco.cidade VALUES (5209, '2019-10-23 14:05:31.168533', NULL, 'Piedade', 26);
INSERT INTO endereco.cidade VALUES (5211, '2019-10-23 14:05:31.168533', NULL, 'Pindamonhangaba', 26);
INSERT INTO endereco.cidade VALUES (5213, '2019-10-23 14:05:31.168533', NULL, 'Pinhalzinho', 26);
INSERT INTO endereco.cidade VALUES (5215, '2019-10-23 14:05:31.168533', NULL, 'Piquete', 26);
INSERT INTO endereco.cidade VALUES (5216, '2019-10-23 14:05:31.168533', NULL, 'Piracaia', 26);
INSERT INTO endereco.cidade VALUES (5218, '2019-10-23 14:05:31.168533', NULL, 'Piraju', 26);
INSERT INTO endereco.cidade VALUES (5220, '2019-10-23 14:05:31.168533', NULL, 'Pirangi', 26);
INSERT INTO endereco.cidade VALUES (5222, '2019-10-23 14:05:31.168533', NULL, 'Pirapozinho', 26);
INSERT INTO endereco.cidade VALUES (5223, '2019-10-23 14:05:31.168533', NULL, 'Pirassununga', 26);
INSERT INTO endereco.cidade VALUES (5225, '2019-10-23 14:05:31.168533', NULL, 'Pitangueiras', 26);
INSERT INTO endereco.cidade VALUES (5227, '2019-10-23 14:05:31.168533', NULL, 'Platina', 26);
INSERT INTO endereco.cidade VALUES (5229, '2019-10-23 14:05:31.168533', NULL, 'Poloni', 26);
INSERT INTO endereco.cidade VALUES (5230, '2019-10-23 14:05:31.168533', NULL, 'Pompéia', 26);
INSERT INTO endereco.cidade VALUES (5232, '2019-10-23 14:05:31.168533', NULL, 'Pontal', 26);
INSERT INTO endereco.cidade VALUES (5234, '2019-10-23 14:05:31.168533', NULL, 'Pontes Gestal', 26);
INSERT INTO endereco.cidade VALUES (5236, '2019-10-23 14:05:31.168533', NULL, 'Porangaba', 26);
INSERT INTO endereco.cidade VALUES (5238, '2019-10-23 14:05:31.168533', NULL, 'Porto Ferreira', 26);
INSERT INTO endereco.cidade VALUES (5240, '2019-10-23 14:05:31.168533', NULL, 'Potirendaba', 26);
INSERT INTO endereco.cidade VALUES (5241, '2019-10-23 14:05:31.168533', NULL, 'Pracinha', 26);
INSERT INTO endereco.cidade VALUES (5243, '2019-10-23 14:05:31.168533', NULL, 'Praia Grande', 26);
INSERT INTO endereco.cidade VALUES (5245, '2019-10-23 14:05:31.168533', NULL, 'Presidente Alves', 26);
INSERT INTO endereco.cidade VALUES (5247, '2019-10-23 14:05:31.168533', NULL, 'Presidente Epitácio', 26);
INSERT INTO endereco.cidade VALUES (5248, '2019-10-23 14:05:31.168533', NULL, 'Presidente Prudente', 26);
INSERT INTO endereco.cidade VALUES (5250, '2019-10-23 14:05:31.168533', NULL, 'Promissão', 26);
INSERT INTO endereco.cidade VALUES (5251, '2019-10-23 14:05:31.168533', NULL, 'Quadra', 26);
INSERT INTO endereco.cidade VALUES (5253, '2019-10-23 14:05:31.168533', NULL, 'Queiroz', 26);
INSERT INTO endereco.cidade VALUES (5255, '2019-10-23 14:05:31.168533', NULL, 'Quintana', 26);
INSERT INTO endereco.cidade VALUES (5257, '2019-10-23 14:05:31.168533', NULL, 'Rancharia', 26);
INSERT INTO endereco.cidade VALUES (5259, '2019-10-23 14:05:31.168533', NULL, 'Regente Feijó', 26);
INSERT INTO endereco.cidade VALUES (5260, '2019-10-23 14:05:31.168533', NULL, 'Reginópolis', 26);
INSERT INTO endereco.cidade VALUES (5262, '2019-10-23 14:05:31.168533', NULL, 'Restinga', 26);
INSERT INTO endereco.cidade VALUES (5264, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Bonito', 26);
INSERT INTO endereco.cidade VALUES (5266, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Corrente', 26);
INSERT INTO endereco.cidade VALUES (5268, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Dos Índios', 26);
INSERT INTO endereco.cidade VALUES (5269, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Grande', 26);
INSERT INTO endereco.cidade VALUES (5271, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Preto', 26);
INSERT INTO endereco.cidade VALUES (5273, '2019-10-23 14:05:31.168533', NULL, 'Rincão', 26);
INSERT INTO endereco.cidade VALUES (5275, '2019-10-23 14:05:31.168533', NULL, 'Rio Claro', 26);
INSERT INTO endereco.cidade VALUES (5276, '2019-10-23 14:05:31.168533', NULL, 'Rio Das Pedras', 26);
INSERT INTO endereco.cidade VALUES (5278, '2019-10-23 14:05:31.168533', NULL, 'Riolândia', 26);
INSERT INTO endereco.cidade VALUES (5280, '2019-10-23 14:05:31.168533', NULL, 'Rosana', 26);
INSERT INTO endereco.cidade VALUES (5282, '2019-10-23 14:05:31.168533', NULL, 'Rubiácea', 26);
INSERT INTO endereco.cidade VALUES (5283, '2019-10-23 14:05:31.168533', NULL, 'Rubinéia', 26);
INSERT INTO endereco.cidade VALUES (5285, '2019-10-23 14:05:31.168533', NULL, 'Sagres', 26);
INSERT INTO endereco.cidade VALUES (5287, '2019-10-23 14:05:31.168533', NULL, 'Sales Oliveira', 26);
INSERT INTO endereco.cidade VALUES (5289, '2019-10-23 14:05:31.168533', NULL, 'Salmourão', 26);
INSERT INTO endereco.cidade VALUES (5290, '2019-10-23 14:05:31.168533', NULL, 'Saltinho', 26);
INSERT INTO endereco.cidade VALUES (5292, '2019-10-23 14:05:31.168533', NULL, 'Salto De Pirapora', 26);
INSERT INTO endereco.cidade VALUES (5294, '2019-10-23 14:05:31.168533', NULL, 'Sandovalina', 26);
INSERT INTO endereco.cidade VALUES (5296, '2019-10-23 14:05:31.168533', NULL, 'Santa Albertina', 26);
INSERT INTO endereco.cidade VALUES (5297, '2019-10-23 14:05:31.168533', NULL, 'Santa Bárbara D`Oeste', 26);
INSERT INTO endereco.cidade VALUES (5413, '2019-10-23 14:05:31.168533', NULL, 'Valinhos', 26);
INSERT INTO endereco.cidade VALUES (5415, '2019-10-23 14:05:31.168533', NULL, 'Vargem', 26);
INSERT INTO endereco.cidade VALUES (5417, '2019-10-23 14:05:31.168533', NULL, 'Vargem Grande Paulista', 26);
INSERT INTO endereco.cidade VALUES (5418, '2019-10-23 14:05:31.168533', NULL, 'Várzea Paulista', 26);
INSERT INTO endereco.cidade VALUES (5420, '2019-10-23 14:05:31.168533', NULL, 'Vinhedo', 26);
INSERT INTO endereco.cidade VALUES (5421, '2019-10-23 14:05:31.168533', NULL, 'Viradouro', 26);
INSERT INTO endereco.cidade VALUES (5301, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Da Esperança', 26);
INSERT INTO endereco.cidade VALUES (5303, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Do Rio Pardo', 26);
INSERT INTO endereco.cidade VALUES (5304, '2019-10-23 14:05:31.168533', NULL, 'Santa Ernestina', 26);
INSERT INTO endereco.cidade VALUES (5306, '2019-10-23 14:05:31.168533', NULL, 'Santa Gertrudes', 26);
INSERT INTO endereco.cidade VALUES (5308, '2019-10-23 14:05:31.168533', NULL, 'Santa Lúcia', 26);
INSERT INTO endereco.cidade VALUES (5309, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Da Serra', 26);
INSERT INTO endereco.cidade VALUES (5311, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita D`Oeste', 26);
INSERT INTO endereco.cidade VALUES (5313, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa De Viterbo', 26);
INSERT INTO endereco.cidade VALUES (5314, '2019-10-23 14:05:31.168533', NULL, 'Santa Salete', 26);
INSERT INTO endereco.cidade VALUES (5316, '2019-10-23 14:05:31.168533', NULL, 'Santana De Parnaíba', 26);
INSERT INTO endereco.cidade VALUES (5317, '2019-10-23 14:05:31.168533', NULL, 'Santo Anastácio', 26);
INSERT INTO endereco.cidade VALUES (5319, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Da Alegria', 26);
INSERT INTO endereco.cidade VALUES (5321, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Aracanguá', 26);
INSERT INTO endereco.cidade VALUES (5323, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Pinhal', 26);
INSERT INTO endereco.cidade VALUES (5324, '2019-10-23 14:05:31.168533', NULL, 'Santo Expedito', 26);
INSERT INTO endereco.cidade VALUES (5326, '2019-10-23 14:05:31.168533', NULL, 'Santos', 26);
INSERT INTO endereco.cidade VALUES (5327, '2019-10-23 14:05:31.168533', NULL, 'São Bento Do Sapucaí', 26);
INSERT INTO endereco.cidade VALUES (5329, '2019-10-23 14:05:31.168533', NULL, 'São Caetano Do Sul', 26);
INSERT INTO endereco.cidade VALUES (5330, '2019-10-23 14:05:31.168533', NULL, 'São Carlos', 26);
INSERT INTO endereco.cidade VALUES (5332, '2019-10-23 14:05:31.168533', NULL, 'São João Da Boa Vista', 26);
INSERT INTO endereco.cidade VALUES (5334, '2019-10-23 14:05:31.168533', NULL, 'São João De Iracema', 26);
INSERT INTO endereco.cidade VALUES (5336, '2019-10-23 14:05:31.168533', NULL, 'São Joaquim Da Barra', 26);
INSERT INTO endereco.cidade VALUES (5337, '2019-10-23 14:05:31.168533', NULL, 'São José Da Bela Vista', 26);
INSERT INTO endereco.cidade VALUES (5339, '2019-10-23 14:05:31.168533', NULL, 'São José Do Rio Pardo', 26);
INSERT INTO endereco.cidade VALUES (5340, '2019-10-23 14:05:31.168533', NULL, 'São José Do Rio Preto', 26);
INSERT INTO endereco.cidade VALUES (5342, '2019-10-23 14:05:31.168533', NULL, 'São Lourenço Da Serra', 26);
INSERT INTO endereco.cidade VALUES (5344, '2019-10-23 14:05:31.168533', NULL, 'São Manuel', 26);
INSERT INTO endereco.cidade VALUES (5345, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Arcanjo', 26);
INSERT INTO endereco.cidade VALUES (5347, '2019-10-23 14:05:31.168533', NULL, 'São Pedro', 26);
INSERT INTO endereco.cidade VALUES (5349, '2019-10-23 14:05:31.168533', NULL, 'São Roque', 26);
INSERT INTO endereco.cidade VALUES (5350, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião', 26);
INSERT INTO endereco.cidade VALUES (5352, '2019-10-23 14:05:31.168533', NULL, 'São Simão', 26);
INSERT INTO endereco.cidade VALUES (5353, '2019-10-23 14:05:31.168533', NULL, 'São Vicente', 26);
INSERT INTO endereco.cidade VALUES (5355, '2019-10-23 14:05:31.168533', NULL, 'Sarutaiá', 26);
INSERT INTO endereco.cidade VALUES (5357, '2019-10-23 14:05:31.168533', NULL, 'Serra Azul', 26);
INSERT INTO endereco.cidade VALUES (5358, '2019-10-23 14:05:31.168533', NULL, 'Serra Negra', 26);
INSERT INTO endereco.cidade VALUES (5360, '2019-10-23 14:05:31.168533', NULL, 'Sertãozinho', 26);
INSERT INTO endereco.cidade VALUES (5362, '2019-10-23 14:05:31.168533', NULL, 'Severínia', 26);
INSERT INTO endereco.cidade VALUES (5364, '2019-10-23 14:05:31.168533', NULL, 'Socorro', 26);
INSERT INTO endereco.cidade VALUES (5365, '2019-10-23 14:05:31.168533', NULL, 'Sorocaba', 26);
INSERT INTO endereco.cidade VALUES (5367, '2019-10-23 14:05:31.168533', NULL, 'Sumaré', 26);
INSERT INTO endereco.cidade VALUES (5369, '2019-10-23 14:05:31.168533', NULL, 'Suzano', 26);
INSERT INTO endereco.cidade VALUES (5370, '2019-10-23 14:05:31.168533', NULL, 'Tabapuã', 26);
INSERT INTO endereco.cidade VALUES (5372, '2019-10-23 14:05:31.168533', NULL, 'Taboão Da Serra', 26);
INSERT INTO endereco.cidade VALUES (5374, '2019-10-23 14:05:31.168533', NULL, 'Taguaí', 26);
INSERT INTO endereco.cidade VALUES (5376, '2019-10-23 14:05:31.168533', NULL, 'Taiúva', 26);
INSERT INTO endereco.cidade VALUES (5378, '2019-10-23 14:05:31.168533', NULL, 'Tanabi', 26);
INSERT INTO endereco.cidade VALUES (5379, '2019-10-23 14:05:31.168533', NULL, 'Tapiraí', 26);
INSERT INTO endereco.cidade VALUES (5381, '2019-10-23 14:05:31.168533', NULL, 'Taquaral', 26);
INSERT INTO endereco.cidade VALUES (5383, '2019-10-23 14:05:31.168533', NULL, 'Taquarituba', 26);
INSERT INTO endereco.cidade VALUES (5385, '2019-10-23 14:05:31.168533', NULL, 'Tarabai', 26);
INSERT INTO endereco.cidade VALUES (5386, '2019-10-23 14:05:31.168533', NULL, 'Tarumã', 26);
INSERT INTO endereco.cidade VALUES (5388, '2019-10-23 14:05:31.168533', NULL, 'Taubaté', 26);
INSERT INTO endereco.cidade VALUES (5390, '2019-10-23 14:05:31.168533', NULL, 'Teodoro Sampaio', 26);
INSERT INTO endereco.cidade VALUES (5392, '2019-10-23 14:05:31.168533', NULL, 'Tietê', 26);
INSERT INTO endereco.cidade VALUES (5394, '2019-10-23 14:05:31.168533', NULL, 'Torre De Pedra', 26);
INSERT INTO endereco.cidade VALUES (5395, '2019-10-23 14:05:31.168533', NULL, 'Torrinha', 26);
INSERT INTO endereco.cidade VALUES (5397, '2019-10-23 14:05:31.168533', NULL, 'Tremembé', 26);
INSERT INTO endereco.cidade VALUES (5399, '2019-10-23 14:05:31.168533', NULL, 'Tuiuti', 26);
INSERT INTO endereco.cidade VALUES (5401, '2019-10-23 14:05:31.168533', NULL, 'Tupi Paulista', 26);
INSERT INTO endereco.cidade VALUES (5402, '2019-10-23 14:05:31.168533', NULL, 'Turiúba', 26);
INSERT INTO endereco.cidade VALUES (5404, '2019-10-23 14:05:31.168533', NULL, 'Ubarana', 26);
INSERT INTO endereco.cidade VALUES (5406, '2019-10-23 14:05:31.168533', NULL, 'Ubirajara', 26);
INSERT INTO endereco.cidade VALUES (5408, '2019-10-23 14:05:31.168533', NULL, 'União Paulista', 26);
INSERT INTO endereco.cidade VALUES (5410, '2019-10-23 14:05:31.168533', NULL, 'Uru', 26);
INSERT INTO endereco.cidade VALUES (5411, '2019-10-23 14:05:31.168533', NULL, 'Urupês', 26);
INSERT INTO endereco.cidade VALUES (5423, '2019-10-23 14:05:31.168533', NULL, 'Vitória Brasil', 26);
INSERT INTO endereco.cidade VALUES (5425, '2019-10-23 14:05:31.168533', NULL, 'Votuporanga', 26);
INSERT INTO endereco.cidade VALUES (5427, '2019-10-23 14:05:31.168533', NULL, 'Abreulândia', 27);
INSERT INTO endereco.cidade VALUES (5429, '2019-10-23 14:05:31.168533', NULL, 'Aliança Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5430, '2019-10-23 14:05:31.168533', NULL, 'Almas', 27);
INSERT INTO endereco.cidade VALUES (5432, '2019-10-23 14:05:31.168533', NULL, 'Ananás', 27);
INSERT INTO endereco.cidade VALUES (5434, '2019-10-23 14:05:31.168533', NULL, 'Aparecida Do Rio Negro', 27);
INSERT INTO endereco.cidade VALUES (5436, '2019-10-23 14:05:31.168533', NULL, 'Araguacema', 27);
INSERT INTO endereco.cidade VALUES (5437, '2019-10-23 14:05:31.168533', NULL, 'Araguaçu', 27);
INSERT INTO endereco.cidade VALUES (5439, '2019-10-23 14:05:31.168533', NULL, 'Araguanã', 27);
INSERT INTO endereco.cidade VALUES (5441, '2019-10-23 14:05:31.168533', NULL, 'Arapoema', 27);
INSERT INTO endereco.cidade VALUES (5443, '2019-10-23 14:05:31.168533', NULL, 'Augustinópolis', 27);
INSERT INTO endereco.cidade VALUES (5445, '2019-10-23 14:05:31.168533', NULL, 'Axixá Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5446, '2019-10-23 14:05:31.168533', NULL, 'Babaçulândia', 27);
INSERT INTO endereco.cidade VALUES (5448, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Ouro', 27);
INSERT INTO endereco.cidade VALUES (5450, '2019-10-23 14:05:31.168533', NULL, 'Bernardo Sayão', 27);
INSERT INTO endereco.cidade VALUES (5451, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5453, '2019-10-23 14:05:31.168533', NULL, 'Brejinho De Nazaré', 27);
INSERT INTO endereco.cidade VALUES (5454, '2019-10-23 14:05:31.168533', NULL, 'Buriti Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5456, '2019-10-23 14:05:31.168533', NULL, 'Campos Lindos', 27);
INSERT INTO endereco.cidade VALUES (5458, '2019-10-23 14:05:31.168533', NULL, 'Carmolândia', 27);
INSERT INTO endereco.cidade VALUES (5460, '2019-10-23 14:05:31.168533', NULL, 'Caseara', 27);
INSERT INTO endereco.cidade VALUES (5461, '2019-10-23 14:05:31.168533', NULL, 'Centenário', 27);
INSERT INTO endereco.cidade VALUES (5463, '2019-10-23 14:05:31.168533', NULL, 'Chapada De Areia', 27);
INSERT INTO endereco.cidade VALUES (5465, '2019-10-23 14:05:31.168533', NULL, 'Colméia', 27);
INSERT INTO endereco.cidade VALUES (5466, '2019-10-23 14:05:31.168533', NULL, 'Combinado', 27);
INSERT INTO endereco.cidade VALUES (5468, '2019-10-23 14:05:31.168533', NULL, 'Couto Magalhães', 27);
INSERT INTO endereco.cidade VALUES (5469, '2019-10-23 14:05:31.168533', NULL, 'Cristalândia', 27);
INSERT INTO endereco.cidade VALUES (5471, '2019-10-23 14:05:31.168533', NULL, 'Darcinópolis', 27);
INSERT INTO endereco.cidade VALUES (5472, '2019-10-23 14:05:31.168533', NULL, 'Dianópolis', 27);
INSERT INTO endereco.cidade VALUES (5474, '2019-10-23 14:05:31.168533', NULL, 'Dois Irmãos Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5476, '2019-10-23 14:05:31.168533', NULL, 'Esperantina', 27);
INSERT INTO endereco.cidade VALUES (5477, '2019-10-23 14:05:31.168533', NULL, 'Fátima', 27);
INSERT INTO endereco.cidade VALUES (5479, '2019-10-23 14:05:31.168533', NULL, 'Filadélfia', 27);
INSERT INTO endereco.cidade VALUES (5481, '2019-10-23 14:05:31.168533', NULL, 'Fortaleza Do Tabocão', 27);
INSERT INTO endereco.cidade VALUES (5483, '2019-10-23 14:05:31.168533', NULL, 'Goiatins', 27);
INSERT INTO endereco.cidade VALUES (5484, '2019-10-23 14:05:31.168533', NULL, 'Guaraí', 27);
INSERT INTO endereco.cidade VALUES (5486, '2019-10-23 14:05:31.168533', NULL, 'Ipueiras', 27);
INSERT INTO endereco.cidade VALUES (5488, '2019-10-23 14:05:31.168533', NULL, 'Itaguatins', 27);
INSERT INTO endereco.cidade VALUES (5490, '2019-10-23 14:05:31.168533', NULL, 'Itaporã Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5491, '2019-10-23 14:05:31.168533', NULL, 'Jaú Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5493, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Da Confusão', 27);
INSERT INTO endereco.cidade VALUES (5495, '2019-10-23 14:05:31.168533', NULL, 'Lajeado', 27);
INSERT INTO endereco.cidade VALUES (5497, '2019-10-23 14:05:31.168533', NULL, 'Lizarda', 27);
INSERT INTO endereco.cidade VALUES (5498, '2019-10-23 14:05:31.168533', NULL, 'Luzinópolis', 27);
INSERT INTO endereco.cidade VALUES (5500, '2019-10-23 14:05:31.168533', NULL, 'Mateiros', 27);
INSERT INTO endereco.cidade VALUES (5502, '2019-10-23 14:05:31.168533', NULL, 'Miracema Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5503, '2019-10-23 14:05:31.168533', NULL, 'Miranorte', 27);
INSERT INTO endereco.cidade VALUES (5505, '2019-10-23 14:05:31.168533', NULL, 'Monte Santo Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5507, '2019-10-23 14:05:31.168533', NULL, 'Natividade', 27);
INSERT INTO endereco.cidade VALUES (5508, '2019-10-23 14:05:31.168533', NULL, 'Nazaré', 27);
INSERT INTO endereco.cidade VALUES (5510, '2019-10-23 14:05:31.168533', NULL, 'Nova Rosalândia', 27);
INSERT INTO endereco.cidade VALUES (5512, '2019-10-23 14:05:31.168533', NULL, 'Novo Alegre', 27);
INSERT INTO endereco.cidade VALUES (5514, '2019-10-23 14:05:31.168533', NULL, 'Oliveira De Fátima', 27);
INSERT INTO endereco.cidade VALUES (5515, '2019-10-23 14:05:31.168533', NULL, 'Palmas', 27);
INSERT INTO endereco.cidade VALUES (5517, '2019-10-23 14:05:31.168533', NULL, 'Palmeiras Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5519, '2019-10-23 14:05:31.168533', NULL, 'Paraíso Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5520, '2019-10-23 14:05:31.168533', NULL, 'Paranã', 27);
INSERT INTO endereco.cidade VALUES (5522, '2019-10-23 14:05:31.168533', NULL, 'Pedro Afonso', 27);
INSERT INTO endereco.cidade VALUES (5524, '2019-10-23 14:05:31.168533', NULL, 'Pequizeiro', 27);
INSERT INTO endereco.cidade VALUES (5526, '2019-10-23 14:05:31.168533', NULL, 'Piraquê', 27);
INSERT INTO endereco.cidade VALUES (5527, '2019-10-23 14:05:31.168533', NULL, 'Pium', 27);
INSERT INTO endereco.cidade VALUES (5529, '2019-10-23 14:05:31.168533', NULL, 'Ponte Alta Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5531, '2019-10-23 14:05:31.168533', NULL, 'Porto Nacional', 27);
INSERT INTO endereco.cidade VALUES (5532, '2019-10-23 14:05:31.168533', NULL, 'Praia Norte', 27);
INSERT INTO endereco.cidade VALUES (5534, '2019-10-23 14:05:31.168533', NULL, 'Pugmil', 27);
INSERT INTO endereco.cidade VALUES (5536, '2019-10-23 14:05:31.168533', NULL, 'Riachinho', 27);
INSERT INTO endereco.cidade VALUES (5537, '2019-10-23 14:05:31.168533', NULL, 'Rio Da Conceição', 27);
INSERT INTO endereco.cidade VALUES (5539, '2019-10-23 14:05:31.168533', NULL, 'Rio Sono', 27);
INSERT INTO endereco.cidade VALUES (5541, '2019-10-23 14:05:31.168533', NULL, 'Sandolândia', 27);
INSERT INTO endereco.cidade VALUES (5543, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5544, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (1, '2019-10-23 14:05:31.168533', NULL, 'Acrelândia', 1);
INSERT INTO endereco.cidade VALUES (2, '2019-10-23 14:05:31.168533', NULL, 'Assis Brasil', 1);
INSERT INTO endereco.cidade VALUES (4, '2019-10-23 14:05:31.168533', NULL, 'Bujari', 1);
INSERT INTO endereco.cidade VALUES (6, '2019-10-23 14:05:31.168533', NULL, 'Cruzeiro Do Sul', 1);
INSERT INTO endereco.cidade VALUES (9, '2019-10-23 14:05:31.168533', NULL, 'Jordão', 1);
INSERT INTO endereco.cidade VALUES (11, '2019-10-23 14:05:31.168533', NULL, 'Manoel Urbano', 1);
INSERT INTO endereco.cidade VALUES (13, '2019-10-23 14:05:31.168533', NULL, 'Plácido De Castro', 1);
INSERT INTO endereco.cidade VALUES (16, '2019-10-23 14:05:31.168533', NULL, 'Rio Branco', 1);
INSERT INTO endereco.cidade VALUES (18, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa Do Purus', 1);
INSERT INTO endereco.cidade VALUES (20, '2019-10-23 14:05:31.168533', NULL, 'Senador Guiomard', 1);
INSERT INTO endereco.cidade VALUES (23, '2019-10-23 14:05:31.168533', NULL, 'Água Branca', 2);
INSERT INTO endereco.cidade VALUES (25, '2019-10-23 14:05:31.168533', NULL, 'Arapiraca', 2);
INSERT INTO endereco.cidade VALUES (27, '2019-10-23 14:05:31.168533', NULL, 'Barra De Santo Antônio', 2);
INSERT INTO endereco.cidade VALUES (30, '2019-10-23 14:05:31.168533', NULL, 'Belém', 2);
INSERT INTO endereco.cidade VALUES (32, '2019-10-23 14:05:31.168533', NULL, 'Boca Da Mata', 2);
INSERT INTO endereco.cidade VALUES (35, '2019-10-23 14:05:31.168533', NULL, 'Cajueiro', 2);
INSERT INTO endereco.cidade VALUES (37, '2019-10-23 14:05:31.168533', NULL, 'Campo Alegre', 2);
INSERT INTO endereco.cidade VALUES (39, '2019-10-23 14:05:31.168533', NULL, 'Canapi', 2);
INSERT INTO endereco.cidade VALUES (42, '2019-10-23 14:05:31.168533', NULL, 'Chã Preta', 2);
INSERT INTO endereco.cidade VALUES (44, '2019-10-23 14:05:31.168533', NULL, 'Colônia Leopoldina', 2);
INSERT INTO endereco.cidade VALUES (46, '2019-10-23 14:05:31.168533', NULL, 'Coruripe', 2);
INSERT INTO endereco.cidade VALUES (48, '2019-10-23 14:05:31.168533', NULL, 'Delmiro Gouveia', 2);
INSERT INTO endereco.cidade VALUES (51, '2019-10-23 14:05:31.168533', NULL, 'Feira Grande', 2);
INSERT INTO endereco.cidade VALUES (53, '2019-10-23 14:05:31.168533', NULL, 'Flexeiras', 2);
INSERT INTO endereco.cidade VALUES (56, '2019-10-23 14:05:31.168533', NULL, 'Igaci', 2);
INSERT INTO endereco.cidade VALUES (58, '2019-10-23 14:05:31.168533', NULL, 'Inhapi', 2);
INSERT INTO endereco.cidade VALUES (60, '2019-10-23 14:05:31.168533', NULL, 'Jacuípe', 2);
INSERT INTO endereco.cidade VALUES (62, '2019-10-23 14:05:31.168533', NULL, 'Jaramataia', 2);
INSERT INTO endereco.cidade VALUES (65, '2019-10-23 14:05:31.168533', NULL, 'Jundiá', 2);
INSERT INTO endereco.cidade VALUES (67, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Da Canoa', 2);
INSERT INTO endereco.cidade VALUES (69, '2019-10-23 14:05:31.168533', NULL, 'Maceió', 2);
INSERT INTO endereco.cidade VALUES (72, '2019-10-23 14:05:31.168533', NULL, 'Maragogi', 2);
INSERT INTO endereco.cidade VALUES (74, '2019-10-23 14:05:31.168533', NULL, 'Marechal Deodoro', 2);
INSERT INTO endereco.cidade VALUES (76, '2019-10-23 14:05:31.168533', NULL, 'Mata Grande', 2);
INSERT INTO endereco.cidade VALUES (79, '2019-10-23 14:05:31.168533', NULL, 'Minador Do Negrão', 2);
INSERT INTO endereco.cidade VALUES (81, '2019-10-23 14:05:31.168533', NULL, 'Murici', 2);
INSERT INTO endereco.cidade VALUES (83, '2019-10-23 14:05:31.168533', NULL, 'Olho D`Água Das Flores', 2);
INSERT INTO endereco.cidade VALUES (86, '2019-10-23 14:05:31.168533', NULL, 'Olivença', 2);
INSERT INTO endereco.cidade VALUES (88, '2019-10-23 14:05:31.168533', NULL, 'Palestina', 2);
INSERT INTO endereco.cidade VALUES (90, '2019-10-23 14:05:31.168533', NULL, 'Pão De Açúcar', 2);
INSERT INTO endereco.cidade VALUES (93, '2019-10-23 14:05:31.168533', NULL, 'Passo De Camaragibe', 2);
INSERT INTO endereco.cidade VALUES (95, '2019-10-23 14:05:31.168533', NULL, 'Penedo', 2);
INSERT INTO endereco.cidade VALUES (98, '2019-10-23 14:05:31.168533', NULL, 'Pindoba', 2);
INSERT INTO endereco.cidade VALUES (100, '2019-10-23 14:05:31.168533', NULL, 'Poço Das Trincheiras', 2);
INSERT INTO endereco.cidade VALUES (102, '2019-10-23 14:05:31.168533', NULL, 'Porto De Pedras', 2);
INSERT INTO endereco.cidade VALUES (105, '2019-10-23 14:05:31.168533', NULL, 'Rio Largo', 2);
INSERT INTO endereco.cidade VALUES (107, '2019-10-23 14:05:31.168533', NULL, 'Santa Luzia Do Norte', 2);
INSERT INTO endereco.cidade VALUES (110, '2019-10-23 14:05:31.168533', NULL, 'São Brás', 2);
INSERT INTO endereco.cidade VALUES (112, '2019-10-23 14:05:31.168533', NULL, 'São José Da Tapera', 2);
INSERT INTO endereco.cidade VALUES (115, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Dos Milagres', 2);
INSERT INTO endereco.cidade VALUES (118, '2019-10-23 14:05:31.168533', NULL, 'Senador Rui Palmeira', 2);
INSERT INTO endereco.cidade VALUES (120, '2019-10-23 14:05:31.168533', NULL, 'Taquarana', 2);
INSERT INTO endereco.cidade VALUES (123, '2019-10-23 14:05:31.168533', NULL, 'União Dos Palmares', 2);
INSERT INTO endereco.cidade VALUES (125, '2019-10-23 14:05:31.168533', NULL, 'Alvarães', 3);
INSERT INTO endereco.cidade VALUES (127, '2019-10-23 14:05:31.168533', NULL, 'Anamã', 3);
INSERT INTO endereco.cidade VALUES (128, '2019-10-23 14:05:31.168533', NULL, 'Anori', 3);
INSERT INTO endereco.cidade VALUES (130, '2019-10-23 14:05:31.168533', NULL, 'Atalaia Do Norte', 3);
INSERT INTO endereco.cidade VALUES (133, '2019-10-23 14:05:31.168533', NULL, 'Barreirinha', 3);
INSERT INTO endereco.cidade VALUES (135, '2019-10-23 14:05:31.168533', NULL, 'Beruri', 3);
INSERT INTO endereco.cidade VALUES (137, '2019-10-23 14:05:31.168533', NULL, 'Boca Do Acre', 3);
INSERT INTO endereco.cidade VALUES (139, '2019-10-23 14:05:31.168533', NULL, 'Caapiranga', 3);
INSERT INTO endereco.cidade VALUES (142, '2019-10-23 14:05:31.168533', NULL, 'Careiro', 3);
INSERT INTO endereco.cidade VALUES (144, '2019-10-23 14:05:31.168533', NULL, 'Coari', 3);
INSERT INTO endereco.cidade VALUES (146, '2019-10-23 14:05:31.168533', NULL, 'Eirunepé', 3);
INSERT INTO endereco.cidade VALUES (5547, '2019-10-23 14:05:31.168533', NULL, 'Santa Terezinha Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5549, '2019-10-23 14:05:31.168533', NULL, 'São Félix Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5550, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5552, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5554, '2019-10-23 14:05:31.168533', NULL, 'Silvanópolis', 27);
INSERT INTO endereco.cidade VALUES (5555, '2019-10-23 14:05:31.168533', NULL, 'Sítio Novo Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5557, '2019-10-23 14:05:31.168533', NULL, 'Taguatinga', 27);
INSERT INTO endereco.cidade VALUES (5559, '2019-10-23 14:05:31.168533', NULL, 'Talismã', 27);
INSERT INTO endereco.cidade VALUES (5560, '2019-10-23 14:05:31.168533', NULL, 'Tocantínia', 27);
INSERT INTO endereco.cidade VALUES (5562, '2019-10-23 14:05:31.168533', NULL, 'Tupirama', 27);
INSERT INTO endereco.cidade VALUES (5564, '2019-10-23 14:05:31.168533', NULL, 'Wanderlândia', 27);
INSERT INTO endereco.cidade VALUES (148, '2019-10-23 14:05:31.168533', NULL, 'Fonte Boa', 3);
INSERT INTO endereco.cidade VALUES (151, '2019-10-23 14:05:31.168533', NULL, 'Ipixuna', 3);
INSERT INTO endereco.cidade VALUES (153, '2019-10-23 14:05:31.168533', NULL, 'Itacoatiara', 3);
INSERT INTO endereco.cidade VALUES (155, '2019-10-23 14:05:31.168533', NULL, 'Itapiranga', 3);
INSERT INTO endereco.cidade VALUES (157, '2019-10-23 14:05:31.168533', NULL, 'Juruá', 3);
INSERT INTO endereco.cidade VALUES (160, '2019-10-23 14:05:31.168533', NULL, 'Manacapuru', 3);
INSERT INTO endereco.cidade VALUES (162, '2019-10-23 14:05:31.168533', NULL, 'Manaus', 3);
INSERT INTO endereco.cidade VALUES (164, '2019-10-23 14:05:31.168533', NULL, 'Maraã', 3);
INSERT INTO endereco.cidade VALUES (167, '2019-10-23 14:05:31.168533', NULL, 'Nova Olinda Do Norte', 3);
INSERT INTO endereco.cidade VALUES (169, '2019-10-23 14:05:31.168533', NULL, 'Novo Aripuanã', 3);
INSERT INTO endereco.cidade VALUES (172, '2019-10-23 14:05:31.168533', NULL, 'Presidente Figueiredo', 3);
INSERT INTO endereco.cidade VALUES (174, '2019-10-23 14:05:31.168533', NULL, 'Santa Isabel Do Rio Negro', 3);
INSERT INTO endereco.cidade VALUES (176, '2019-10-23 14:05:31.168533', NULL, 'São Gabriel Da Cachoeira', 3);
INSERT INTO endereco.cidade VALUES (180, '2019-10-23 14:05:31.168533', NULL, 'Tabatinga', 3);
INSERT INTO endereco.cidade VALUES (182, '2019-10-23 14:05:31.168533', NULL, 'Tefé', 3);
INSERT INTO endereco.cidade VALUES (184, '2019-10-23 14:05:31.168533', NULL, 'Uarini', 3);
INSERT INTO endereco.cidade VALUES (186, '2019-10-23 14:05:31.168533', NULL, 'Urucurituba', 3);
INSERT INTO endereco.cidade VALUES (189, '2019-10-23 14:05:31.168533', NULL, 'Cutias', 4);
INSERT INTO endereco.cidade VALUES (191, '2019-10-23 14:05:31.168533', NULL, 'Itaubal', 4);
INSERT INTO endereco.cidade VALUES (193, '2019-10-23 14:05:31.168533', NULL, 'Macapá', 4);
INSERT INTO endereco.cidade VALUES (195, '2019-10-23 14:05:31.168533', NULL, 'Oiapoque', 4);
INSERT INTO endereco.cidade VALUES (197, '2019-10-23 14:05:31.168533', NULL, 'Porto Grande', 4);
INSERT INTO endereco.cidade VALUES (200, '2019-10-23 14:05:31.168533', NULL, 'Serra Do Navio', 4);
INSERT INTO endereco.cidade VALUES (202, '2019-10-23 14:05:31.168533', NULL, 'Vitória Do Jari', 4);
INSERT INTO endereco.cidade VALUES (205, '2019-10-23 14:05:31.168533', NULL, 'Acajutiba', 5);
INSERT INTO endereco.cidade VALUES (207, '2019-10-23 14:05:31.168533', NULL, 'Água Fria', 5);
INSERT INTO endereco.cidade VALUES (209, '2019-10-23 14:05:31.168533', NULL, 'Alagoinhas', 5);
INSERT INTO endereco.cidade VALUES (212, '2019-10-23 14:05:31.168533', NULL, 'Amargosa', 5);
INSERT INTO endereco.cidade VALUES (214, '2019-10-23 14:05:31.168533', NULL, 'América Dourada', 5);
INSERT INTO endereco.cidade VALUES (216, '2019-10-23 14:05:31.168533', NULL, 'Andaraí', 5);
INSERT INTO endereco.cidade VALUES (219, '2019-10-23 14:05:31.168533', NULL, 'Anguera', 5);
INSERT INTO endereco.cidade VALUES (221, '2019-10-23 14:05:31.168533', NULL, 'Antônio Cardoso', 5);
INSERT INTO endereco.cidade VALUES (223, '2019-10-23 14:05:31.168533', NULL, 'Aporá', 5);
INSERT INTO endereco.cidade VALUES (226, '2019-10-23 14:05:31.168533', NULL, 'Aracatu', 5);
INSERT INTO endereco.cidade VALUES (228, '2019-10-23 14:05:31.168533', NULL, 'Aramari', 5);
INSERT INTO endereco.cidade VALUES (230, '2019-10-23 14:05:31.168533', NULL, 'Aratuípe', 5);
INSERT INTO endereco.cidade VALUES (232, '2019-10-23 14:05:31.168533', NULL, 'Baianópolis', 5);
INSERT INTO endereco.cidade VALUES (235, '2019-10-23 14:05:31.168533', NULL, 'Barra', 5);
INSERT INTO endereco.cidade VALUES (237, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Choça', 5);
INSERT INTO endereco.cidade VALUES (240, '2019-10-23 14:05:31.168533', NULL, 'Barreiras', 5);
INSERT INTO endereco.cidade VALUES (242, '2019-10-23 14:05:31.168533', NULL, 'Barrocas', 5);
INSERT INTO endereco.cidade VALUES (244, '2019-10-23 14:05:31.168533', NULL, 'Belmonte', 5);
INSERT INTO endereco.cidade VALUES (247, '2019-10-23 14:05:31.168533', NULL, 'Boa Nova', 5);
INSERT INTO endereco.cidade VALUES (249, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Da Lapa', 5);
INSERT INTO endereco.cidade VALUES (251, '2019-10-23 14:05:31.168533', NULL, 'Boninal', 5);
INSERT INTO endereco.cidade VALUES (254, '2019-10-23 14:05:31.168533', NULL, 'Botuporã', 5);
INSERT INTO endereco.cidade VALUES (256, '2019-10-23 14:05:31.168533', NULL, 'Brejolândia', 5);
INSERT INTO endereco.cidade VALUES (257, '2019-10-23 14:05:31.168533', NULL, 'Brotas De Macaúbas', 5);
INSERT INTO endereco.cidade VALUES (259, '2019-10-23 14:05:31.168533', NULL, 'Buerarema', 5);
INSERT INTO endereco.cidade VALUES (261, '2019-10-23 14:05:31.168533', NULL, 'Caatiba', 5);
INSERT INTO endereco.cidade VALUES (263, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira', 5);
INSERT INTO endereco.cidade VALUES (266, '2019-10-23 14:05:31.168533', NULL, 'Caetanos', 5);
INSERT INTO endereco.cidade VALUES (268, '2019-10-23 14:05:31.168533', NULL, 'Cafarnaum', 5);
INSERT INTO endereco.cidade VALUES (270, '2019-10-23 14:05:31.168533', NULL, 'Caldeirão Grande', 5);
INSERT INTO endereco.cidade VALUES (273, '2019-10-23 14:05:31.168533', NULL, 'Camamu', 5);
INSERT INTO endereco.cidade VALUES (274, '2019-10-23 14:05:31.168533', NULL, 'Campo Alegre De Lourdes', 5);
INSERT INTO endereco.cidade VALUES (277, '2019-10-23 14:05:31.168533', NULL, 'Canarana', 5);
INSERT INTO endereco.cidade VALUES (280, '2019-10-23 14:05:31.168533', NULL, 'Candeias', 5);
INSERT INTO endereco.cidade VALUES (282, '2019-10-23 14:05:31.168533', NULL, 'Cândido Sales', 5);
INSERT INTO endereco.cidade VALUES (284, '2019-10-23 14:05:31.168533', NULL, 'Canudos', 5);
INSERT INTO endereco.cidade VALUES (286, '2019-10-23 14:05:31.168533', NULL, 'Capim Grosso', 5);
INSERT INTO endereco.cidade VALUES (289, '2019-10-23 14:05:31.168533', NULL, 'Cardeal Da Silva', 5);
INSERT INTO endereco.cidade VALUES (291, '2019-10-23 14:05:31.168533', NULL, 'Casa Nova', 5);
INSERT INTO endereco.cidade VALUES (294, '2019-10-23 14:05:31.168533', NULL, 'Catu', 5);
INSERT INTO endereco.cidade VALUES (296, '2019-10-23 14:05:31.168533', NULL, 'Central', 5);
INSERT INTO endereco.cidade VALUES (298, '2019-10-23 14:05:31.168533', NULL, 'Cícero Dantas', 5);
INSERT INTO endereco.cidade VALUES (301, '2019-10-23 14:05:31.168533', NULL, 'Cocos', 5);
INSERT INTO endereco.cidade VALUES (303, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Almeida', 5);
INSERT INTO endereco.cidade VALUES (305, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Jacuípe', 5);
INSERT INTO endereco.cidade VALUES (308, '2019-10-23 14:05:31.168533', NULL, 'Contendas Do Sincorá', 5);
INSERT INTO endereco.cidade VALUES (311, '2019-10-23 14:05:31.168533', NULL, 'Coribe', 5);
INSERT INTO endereco.cidade VALUES (313, '2019-10-23 14:05:31.168533', NULL, 'Correntina', 5);
INSERT INTO endereco.cidade VALUES (315, '2019-10-23 14:05:31.168533', NULL, 'Cravolândia', 5);
INSERT INTO endereco.cidade VALUES (318, '2019-10-23 14:05:31.168533', NULL, 'Cruz Das Almas', 5);
INSERT INTO endereco.cidade VALUES (320, '2019-10-23 14:05:31.168533', NULL, 'Dário Meira', 5);
INSERT INTO endereco.cidade VALUES (323, '2019-10-23 14:05:31.168533', NULL, 'Dom Macedo Costa', 5);
INSERT INTO endereco.cidade VALUES (325, '2019-10-23 14:05:31.168533', NULL, 'Encruzilhada', 5);
INSERT INTO endereco.cidade VALUES (327, '2019-10-23 14:05:31.168533', NULL, 'Érico Cardoso', 5);
INSERT INTO endereco.cidade VALUES (330, '2019-10-23 14:05:31.168533', NULL, 'Eunápolis', 5);
INSERT INTO endereco.cidade VALUES (332, '2019-10-23 14:05:31.168533', NULL, 'Feira Da Mata', 5);
INSERT INTO endereco.cidade VALUES (335, '2019-10-23 14:05:31.168533', NULL, 'Firmino Alves', 5);
INSERT INTO endereco.cidade VALUES (337, '2019-10-23 14:05:31.168533', NULL, 'Formosa Do Rio Preto', 5);
INSERT INTO endereco.cidade VALUES (340, '2019-10-23 14:05:31.168533', NULL, 'Gentio Do Ouro', 5);
INSERT INTO endereco.cidade VALUES (342, '2019-10-23 14:05:31.168533', NULL, 'Gongogi', 5);
INSERT INTO endereco.cidade VALUES (345, '2019-10-23 14:05:31.168533', NULL, 'Guanambi', 5);
INSERT INTO endereco.cidade VALUES (347, '2019-10-23 14:05:31.168533', NULL, 'Heliópolis', 5);
INSERT INTO endereco.cidade VALUES (349, '2019-10-23 14:05:31.168533', NULL, 'Ibiassucê', 5);
INSERT INTO endereco.cidade VALUES (351, '2019-10-23 14:05:31.168533', NULL, 'Ibicoara', 5);
INSERT INTO endereco.cidade VALUES (354, '2019-10-23 14:05:31.168533', NULL, 'Ibipitanga', 5);
INSERT INTO endereco.cidade VALUES (356, '2019-10-23 14:05:31.168533', NULL, 'Ibirapitanga', 5);
INSERT INTO endereco.cidade VALUES (358, '2019-10-23 14:05:31.168533', NULL, 'Ibirataia', 5);
INSERT INTO endereco.cidade VALUES (361, '2019-10-23 14:05:31.168533', NULL, 'Ibotirama', 5);
INSERT INTO endereco.cidade VALUES (363, '2019-10-23 14:05:31.168533', NULL, 'Igaporã', 5);
INSERT INTO endereco.cidade VALUES (365, '2019-10-23 14:05:31.168533', NULL, 'Iguaí', 5);
INSERT INTO endereco.cidade VALUES (367, '2019-10-23 14:05:31.168533', NULL, 'Inhambupe', 5);
INSERT INTO endereco.cidade VALUES (370, '2019-10-23 14:05:31.168533', NULL, 'Ipirá', 5);
INSERT INTO endereco.cidade VALUES (372, '2019-10-23 14:05:31.168533', NULL, 'Irajuba', 5);
INSERT INTO endereco.cidade VALUES (374, '2019-10-23 14:05:31.168533', NULL, 'Iraquara', 5);
INSERT INTO endereco.cidade VALUES (377, '2019-10-23 14:05:31.168533', NULL, 'Itabela', 5);
INSERT INTO endereco.cidade VALUES (379, '2019-10-23 14:05:31.168533', NULL, 'Itabuna', 5);
INSERT INTO endereco.cidade VALUES (381, '2019-10-23 14:05:31.168533', NULL, 'Itaeté', 5);
INSERT INTO endereco.cidade VALUES (383, '2019-10-23 14:05:31.168533', NULL, 'Itagibá', 5);
INSERT INTO endereco.cidade VALUES (385, '2019-10-23 14:05:31.168533', NULL, 'Itaguaçu Da Bahia', 5);
INSERT INTO endereco.cidade VALUES (386, '2019-10-23 14:05:31.168533', NULL, 'Itaju Do Colônia', 5);
INSERT INTO endereco.cidade VALUES (388, '2019-10-23 14:05:31.168533', NULL, 'Itamaraju', 5);
INSERT INTO endereco.cidade VALUES (390, '2019-10-23 14:05:31.168533', NULL, 'Itambé', 5);
INSERT INTO endereco.cidade VALUES (393, '2019-10-23 14:05:31.168533', NULL, 'Itaparica', 5);
INSERT INTO endereco.cidade VALUES (395, '2019-10-23 14:05:31.168533', NULL, 'Itapebi', 5);
INSERT INTO endereco.cidade VALUES (397, '2019-10-23 14:05:31.168533', NULL, 'Itapicuru', 5);
INSERT INTO endereco.cidade VALUES (399, '2019-10-23 14:05:31.168533', NULL, 'Itaquara', 5);
INSERT INTO endereco.cidade VALUES (402, '2019-10-23 14:05:31.168533', NULL, 'Itiruçu', 5);
INSERT INTO endereco.cidade VALUES (404, '2019-10-23 14:05:31.168533', NULL, 'Itororó', 5);
INSERT INTO endereco.cidade VALUES (406, '2019-10-23 14:05:31.168533', NULL, 'Ituberá', 5);
INSERT INTO endereco.cidade VALUES (408, '2019-10-23 14:05:31.168533', NULL, 'Jaborandi', 5);
INSERT INTO endereco.cidade VALUES (411, '2019-10-23 14:05:31.168533', NULL, 'Jaguaquara', 5);
INSERT INTO endereco.cidade VALUES (413, '2019-10-23 14:05:31.168533', NULL, 'Jaguaripe', 5);
INSERT INTO endereco.cidade VALUES (415, '2019-10-23 14:05:31.168533', NULL, 'Jequié', 5);
INSERT INTO endereco.cidade VALUES (418, '2019-10-23 14:05:31.168533', NULL, 'Jitaúna', 5);
INSERT INTO endereco.cidade VALUES (419, '2019-10-23 14:05:31.168533', NULL, 'João Dourado', 5);
INSERT INTO endereco.cidade VALUES (422, '2019-10-23 14:05:31.168533', NULL, 'Jussara', 5);
INSERT INTO endereco.cidade VALUES (425, '2019-10-23 14:05:31.168533', NULL, 'Lafaiete Coutinho', 5);
INSERT INTO endereco.cidade VALUES (427, '2019-10-23 14:05:31.168533', NULL, 'Laje', 5);
INSERT INTO endereco.cidade VALUES (429, '2019-10-23 14:05:31.168533', NULL, 'Lajedinho', 5);
INSERT INTO endereco.cidade VALUES (432, '2019-10-23 14:05:31.168533', NULL, 'Lapão', 5);
INSERT INTO endereco.cidade VALUES (434, '2019-10-23 14:05:31.168533', NULL, 'Lençóis', 5);
INSERT INTO endereco.cidade VALUES (436, '2019-10-23 14:05:31.168533', NULL, 'Livramento De Nossa Senhora', 5);
INSERT INTO endereco.cidade VALUES (439, '2019-10-23 14:05:31.168533', NULL, 'Macarani', 5);
INSERT INTO endereco.cidade VALUES (441, '2019-10-23 14:05:31.168533', NULL, 'Macururé', 5);
INSERT INTO endereco.cidade VALUES (443, '2019-10-23 14:05:31.168533', NULL, 'Maetinga', 5);
INSERT INTO endereco.cidade VALUES (445, '2019-10-23 14:05:31.168533', NULL, 'Mairi', 5);
INSERT INTO endereco.cidade VALUES (447, '2019-10-23 14:05:31.168533', NULL, 'Malhada De Pedras', 5);
INSERT INTO endereco.cidade VALUES (450, '2019-10-23 14:05:31.168533', NULL, 'Maracás', 5);
INSERT INTO endereco.cidade VALUES (452, '2019-10-23 14:05:31.168533', NULL, 'Maraú', 5);
INSERT INTO endereco.cidade VALUES (454, '2019-10-23 14:05:31.168533', NULL, 'Mascote', 5);
INSERT INTO endereco.cidade VALUES (457, '2019-10-23 14:05:31.168533', NULL, 'Medeiros Neto', 5);
INSERT INTO endereco.cidade VALUES (459, '2019-10-23 14:05:31.168533', NULL, 'Milagres', 5);
INSERT INTO endereco.cidade VALUES (462, '2019-10-23 14:05:31.168533', NULL, 'Monte Santo', 5);
INSERT INTO endereco.cidade VALUES (464, '2019-10-23 14:05:31.168533', NULL, 'Morro Do Chapéu', 5);
INSERT INTO endereco.cidade VALUES (466, '2019-10-23 14:05:31.168533', NULL, 'Mucugê', 5);
INSERT INTO endereco.cidade VALUES (468, '2019-10-23 14:05:31.168533', NULL, 'Mulungu Do Morro', 5);
INSERT INTO endereco.cidade VALUES (471, '2019-10-23 14:05:31.168533', NULL, 'Muquém De São Francisco', 5);
INSERT INTO endereco.cidade VALUES (474, '2019-10-23 14:05:31.168533', NULL, 'Nazaré', 5);
INSERT INTO endereco.cidade VALUES (476, '2019-10-23 14:05:31.168533', NULL, 'Nordestina', 5);
INSERT INTO endereco.cidade VALUES (478, '2019-10-23 14:05:31.168533', NULL, 'Nova Fátima', 5);
INSERT INTO endereco.cidade VALUES (480, '2019-10-23 14:05:31.168533', NULL, 'Nova Itarana', 5);
INSERT INTO endereco.cidade VALUES (483, '2019-10-23 14:05:31.168533', NULL, 'Nova Viçosa', 5);
INSERT INTO endereco.cidade VALUES (485, '2019-10-23 14:05:31.168533', NULL, 'Novo Triunfo', 5);
INSERT INTO endereco.cidade VALUES (487, '2019-10-23 14:05:31.168533', NULL, 'Oliveira Dos Brejinhos', 5);
INSERT INTO endereco.cidade VALUES (490, '2019-10-23 14:05:31.168533', NULL, 'Palmas De Monte Alto', 5);
INSERT INTO endereco.cidade VALUES (493, '2019-10-23 14:05:31.168533', NULL, 'Paratinga', 5);
INSERT INTO endereco.cidade VALUES (495, '2019-10-23 14:05:31.168533', NULL, 'Pau Brasil', 5);
INSERT INTO endereco.cidade VALUES (497, '2019-10-23 14:05:31.168533', NULL, 'Pé De Serra', 5);
INSERT INTO endereco.cidade VALUES (499, '2019-10-23 14:05:31.168533', NULL, 'Pedro Alexandre', 5);
INSERT INTO endereco.cidade VALUES (502, '2019-10-23 14:05:31.168533', NULL, 'Pindaí', 5);
INSERT INTO endereco.cidade VALUES (504, '2019-10-23 14:05:31.168533', NULL, 'Pintadas', 5);
INSERT INTO endereco.cidade VALUES (507, '2019-10-23 14:05:31.168533', NULL, 'Piritiba', 5);
INSERT INTO endereco.cidade VALUES (509, '2019-10-23 14:05:31.168533', NULL, 'Planalto', 5);
INSERT INTO endereco.cidade VALUES (511, '2019-10-23 14:05:31.168533', NULL, 'Pojuca', 5);
INSERT INTO endereco.cidade VALUES (513, '2019-10-23 14:05:31.168533', NULL, 'Porto Seguro', 5);
INSERT INTO endereco.cidade VALUES (516, '2019-10-23 14:05:31.168533', NULL, 'Presidente Dutra', 5);
INSERT INTO endereco.cidade VALUES (517, '2019-10-23 14:05:31.168533', NULL, 'Presidente Jânio Quadros', 5);
INSERT INTO endereco.cidade VALUES (520, '2019-10-23 14:05:31.168533', NULL, 'Quijingue', 5);
INSERT INTO endereco.cidade VALUES (522, '2019-10-23 14:05:31.168533', NULL, 'Rafael Jambeiro', 5);
INSERT INTO endereco.cidade VALUES (524, '2019-10-23 14:05:31.168533', NULL, 'Retirolândia', 5);
INSERT INTO endereco.cidade VALUES (526, '2019-10-23 14:05:31.168533', NULL, 'Riachão Do Jacuípe', 5);
INSERT INTO endereco.cidade VALUES (529, '2019-10-23 14:05:31.168533', NULL, 'Ribeira Do Pombal', 5);
INSERT INTO endereco.cidade VALUES (532, '2019-10-23 14:05:31.168533', NULL, 'Rio Do Antônio', 5);
INSERT INTO endereco.cidade VALUES (534, '2019-10-23 14:05:31.168533', NULL, 'Rio Real', 5);
INSERT INTO endereco.cidade VALUES (537, '2019-10-23 14:05:31.168533', NULL, 'Salinas Da Margarida', 5);
INSERT INTO endereco.cidade VALUES (539, '2019-10-23 14:05:31.168533', NULL, 'Santa Bárbara', 5);
INSERT INTO endereco.cidade VALUES (541, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Cabrália', 5);
INSERT INTO endereco.cidade VALUES (544, '2019-10-23 14:05:31.168533', NULL, 'Santa Luzia', 5);
INSERT INTO endereco.cidade VALUES (546, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita De Cássia', 5);
INSERT INTO endereco.cidade VALUES (549, '2019-10-23 14:05:31.168533', NULL, 'Santana', 5);
INSERT INTO endereco.cidade VALUES (551, '2019-10-23 14:05:31.168533', NULL, 'Santo Amaro', 5);
INSERT INTO endereco.cidade VALUES (553, '2019-10-23 14:05:31.168533', NULL, 'Santo Estêvão', 5);
INSERT INTO endereco.cidade VALUES (555, '2019-10-23 14:05:31.168533', NULL, 'São Domingos', 5);
INSERT INTO endereco.cidade VALUES (558, '2019-10-23 14:05:31.168533', NULL, 'São Félix Do Coribe', 5);
INSERT INTO endereco.cidade VALUES (560, '2019-10-23 14:05:31.168533', NULL, 'São Gabriel', 5);
INSERT INTO endereco.cidade VALUES (562, '2019-10-23 14:05:31.168533', NULL, 'São José Da Vitória', 5);
INSERT INTO endereco.cidade VALUES (565, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Passé', 5);
INSERT INTO endereco.cidade VALUES (568, '2019-10-23 14:05:31.168533', NULL, 'Saubara', 5);
INSERT INTO endereco.cidade VALUES (570, '2019-10-23 14:05:31.168533', NULL, 'Seabra', 5);
INSERT INTO endereco.cidade VALUES (572, '2019-10-23 14:05:31.168533', NULL, 'Senhor Do Bonfim', 5);
INSERT INTO endereco.cidade VALUES (575, '2019-10-23 14:05:31.168533', NULL, 'Serra Dourada', 5);
INSERT INTO endereco.cidade VALUES (578, '2019-10-23 14:05:31.168533', NULL, 'Serrolândia', 5);
INSERT INTO endereco.cidade VALUES (580, '2019-10-23 14:05:31.168533', NULL, 'Sítio Do Mato', 5);
INSERT INTO endereco.cidade VALUES (582, '2019-10-23 14:05:31.168533', NULL, 'Sobradinho', 5);
INSERT INTO endereco.cidade VALUES (584, '2019-10-23 14:05:31.168533', NULL, 'Tabocas Do Brejo Velho', 5);
INSERT INTO endereco.cidade VALUES (587, '2019-10-23 14:05:31.168533', NULL, 'Tanquinho', 5);
INSERT INTO endereco.cidade VALUES (589, '2019-10-23 14:05:31.168533', NULL, 'Tapiramutá', 5);
INSERT INTO endereco.cidade VALUES (591, '2019-10-23 14:05:31.168533', NULL, 'Teodoro Sampaio', 5);
INSERT INTO endereco.cidade VALUES (594, '2019-10-23 14:05:31.168533', NULL, 'Terra Nova', 5);
INSERT INTO endereco.cidade VALUES (596, '2019-10-23 14:05:31.168533', NULL, 'Tucano', 5);
INSERT INTO endereco.cidade VALUES (599, '2019-10-23 14:05:31.168533', NULL, 'Ubaitaba', 5);
INSERT INTO endereco.cidade VALUES (601, '2019-10-23 14:05:31.168533', NULL, 'Uibaí', 5);
INSERT INTO endereco.cidade VALUES (603, '2019-10-23 14:05:31.168533', NULL, 'Una', 5);
INSERT INTO endereco.cidade VALUES (605, '2019-10-23 14:05:31.168533', NULL, 'Uruçuca', 5);
INSERT INTO endereco.cidade VALUES (607, '2019-10-23 14:05:31.168533', NULL, 'Valença', 5);
INSERT INTO endereco.cidade VALUES (610, '2019-10-23 14:05:31.168533', NULL, 'Várzea Do Poço', 5);
INSERT INTO endereco.cidade VALUES (612, '2019-10-23 14:05:31.168533', NULL, 'Varzedo', 5);
INSERT INTO endereco.cidade VALUES (614, '2019-10-23 14:05:31.168533', NULL, 'Vereda', 5);
INSERT INTO endereco.cidade VALUES (617, '2019-10-23 14:05:31.168533', NULL, 'Wanderley', 5);
INSERT INTO endereco.cidade VALUES (618, '2019-10-23 14:05:31.168533', NULL, 'Wenceslau Guimarães', 5);
INSERT INTO endereco.cidade VALUES (622, '2019-10-23 14:05:31.168533', NULL, 'Acaraú', 6);
INSERT INTO endereco.cidade VALUES (624, '2019-10-23 14:05:31.168533', NULL, 'Aiuaba', 6);
INSERT INTO endereco.cidade VALUES (626, '2019-10-23 14:05:31.168533', NULL, 'Altaneira', 6);
INSERT INTO endereco.cidade VALUES (628, '2019-10-23 14:05:31.168533', NULL, 'Amontada', 6);
INSERT INTO endereco.cidade VALUES (631, '2019-10-23 14:05:31.168533', NULL, 'Aquiraz', 6);
INSERT INTO endereco.cidade VALUES (633, '2019-10-23 14:05:31.168533', NULL, 'Aracoiaba', 6);
INSERT INTO endereco.cidade VALUES (635, '2019-10-23 14:05:31.168533', NULL, 'Araripe', 6);
INSERT INTO endereco.cidade VALUES (638, '2019-10-23 14:05:31.168533', NULL, 'Assaré', 6);
INSERT INTO endereco.cidade VALUES (640, '2019-10-23 14:05:31.168533', NULL, 'Baixio', 6);
INSERT INTO endereco.cidade VALUES (642, '2019-10-23 14:05:31.168533', NULL, 'Barbalha', 6);
INSERT INTO endereco.cidade VALUES (644, '2019-10-23 14:05:31.168533', NULL, 'Barro', 6);
INSERT INTO endereco.cidade VALUES (646, '2019-10-23 14:05:31.168533', NULL, 'Baturité', 6);
INSERT INTO endereco.cidade VALUES (648, '2019-10-23 14:05:31.168533', NULL, 'Bela Cruz', 6);
INSERT INTO endereco.cidade VALUES (651, '2019-10-23 14:05:31.168533', NULL, 'Camocim', 6);
INSERT INTO endereco.cidade VALUES (653, '2019-10-23 14:05:31.168533', NULL, 'Canindé', 6);
INSERT INTO endereco.cidade VALUES (655, '2019-10-23 14:05:31.168533', NULL, 'Caridade', 6);
INSERT INTO endereco.cidade VALUES (657, '2019-10-23 14:05:31.168533', NULL, 'Caririaçu', 6);
INSERT INTO endereco.cidade VALUES (660, '2019-10-23 14:05:31.168533', NULL, 'Cascavel', 6);
INSERT INTO endereco.cidade VALUES (662, '2019-10-23 14:05:31.168533', NULL, 'Catunda', 6);
INSERT INTO endereco.cidade VALUES (664, '2019-10-23 14:05:31.168533', NULL, 'Cedro', 6);
INSERT INTO endereco.cidade VALUES (667, '2019-10-23 14:05:31.168533', NULL, 'Chorozinho', 6);
INSERT INTO endereco.cidade VALUES (669, '2019-10-23 14:05:31.168533', NULL, 'Crateús', 6);
INSERT INTO endereco.cidade VALUES (671, '2019-10-23 14:05:31.168533', NULL, 'Croatá', 6);
INSERT INTO endereco.cidade VALUES (673, '2019-10-23 14:05:31.168533', NULL, 'Deputado Irapuan Pinheiro', 6);
INSERT INTO endereco.cidade VALUES (676, '2019-10-23 14:05:31.168533', NULL, 'Farias Brito', 6);
INSERT INTO endereco.cidade VALUES (678, '2019-10-23 14:05:31.168533', NULL, 'Fortaleza', 6);
INSERT INTO endereco.cidade VALUES (681, '2019-10-23 14:05:31.168533', NULL, 'General Sampaio', 6);
INSERT INTO endereco.cidade VALUES (683, '2019-10-23 14:05:31.168533', NULL, 'Granja', 6);
INSERT INTO endereco.cidade VALUES (685, '2019-10-23 14:05:31.168533', NULL, 'Groaíras', 6);
INSERT INTO endereco.cidade VALUES (688, '2019-10-23 14:05:31.168533', NULL, 'Guaramiranga', 6);
INSERT INTO endereco.cidade VALUES (690, '2019-10-23 14:05:31.168533', NULL, 'Horizonte', 6);
INSERT INTO endereco.cidade VALUES (692, '2019-10-23 14:05:31.168533', NULL, 'Ibiapina', 6);
INSERT INTO endereco.cidade VALUES (695, '2019-10-23 14:05:31.168533', NULL, 'Icó', 6);
INSERT INTO endereco.cidade VALUES (697, '2019-10-23 14:05:31.168533', NULL, 'Independência', 6);
INSERT INTO endereco.cidade VALUES (699, '2019-10-23 14:05:31.168533', NULL, 'Ipaumirim', 6);
INSERT INTO endereco.cidade VALUES (702, '2019-10-23 14:05:31.168533', NULL, 'Iracema', 6);
INSERT INTO endereco.cidade VALUES (704, '2019-10-23 14:05:31.168533', NULL, 'Itaiçaba', 6);
INSERT INTO endereco.cidade VALUES (706, '2019-10-23 14:05:31.168533', NULL, 'Itapagé', 6);
INSERT INTO endereco.cidade VALUES (709, '2019-10-23 14:05:31.168533', NULL, 'Itarema', 6);
INSERT INTO endereco.cidade VALUES (711, '2019-10-23 14:05:31.168533', NULL, 'Jaguaretama', 6);
INSERT INTO endereco.cidade VALUES (713, '2019-10-23 14:05:31.168533', NULL, 'Jaguaribe', 6);
INSERT INTO endereco.cidade VALUES (716, '2019-10-23 14:05:31.168533', NULL, 'Jati', 6);
INSERT INTO endereco.cidade VALUES (717, '2019-10-23 14:05:31.168533', NULL, 'Jijoca De Jericoacoara', 6);
INSERT INTO endereco.cidade VALUES (720, '2019-10-23 14:05:31.168533', NULL, 'Lavras Da Mangabeira', 6);
INSERT INTO endereco.cidade VALUES (723, '2019-10-23 14:05:31.168533', NULL, 'Maracanaú', 6);
INSERT INTO endereco.cidade VALUES (725, '2019-10-23 14:05:31.168533', NULL, 'Marco', 6);
INSERT INTO endereco.cidade VALUES (727, '2019-10-23 14:05:31.168533', NULL, 'Massapê', 6);
INSERT INTO endereco.cidade VALUES (730, '2019-10-23 14:05:31.168533', NULL, 'Milagres', 6);
INSERT INTO endereco.cidade VALUES (732, '2019-10-23 14:05:31.168533', NULL, 'Miraíma', 6);
INSERT INTO endereco.cidade VALUES (734, '2019-10-23 14:05:31.168533', NULL, 'Mombaça', 6);
INSERT INTO endereco.cidade VALUES (737, '2019-10-23 14:05:31.168533', NULL, 'Moraújo', 6);
INSERT INTO endereco.cidade VALUES (739, '2019-10-23 14:05:31.168533', NULL, 'Mucambo', 6);
INSERT INTO endereco.cidade VALUES (741, '2019-10-23 14:05:31.168533', NULL, 'Nova Olinda', 6);
INSERT INTO endereco.cidade VALUES (743, '2019-10-23 14:05:31.168533', NULL, 'Novo Oriente', 6);
INSERT INTO endereco.cidade VALUES (746, '2019-10-23 14:05:31.168533', NULL, 'Pacajus', 6);
INSERT INTO endereco.cidade VALUES (748, '2019-10-23 14:05:31.168533', NULL, 'Pacoti', 6);
INSERT INTO endereco.cidade VALUES (751, '2019-10-23 14:05:31.168533', NULL, 'Palmácia', 6);
INSERT INTO endereco.cidade VALUES (753, '2019-10-23 14:05:31.168533', NULL, 'Paraipaba', 6);
INSERT INTO endereco.cidade VALUES (755, '2019-10-23 14:05:31.168533', NULL, 'Paramoti', 6);
INSERT INTO endereco.cidade VALUES (757, '2019-10-23 14:05:31.168533', NULL, 'Penaforte', 6);
INSERT INTO endereco.cidade VALUES (759, '2019-10-23 14:05:31.168533', NULL, 'Pereiro', 6);
INSERT INTO endereco.cidade VALUES (761, '2019-10-23 14:05:31.168533', NULL, 'Piquet Carneiro', 6);
INSERT INTO endereco.cidade VALUES (764, '2019-10-23 14:05:31.168533', NULL, 'Porteiras', 6);
INSERT INTO endereco.cidade VALUES (766, '2019-10-23 14:05:31.168533', NULL, 'Potiretama', 6);
INSERT INTO endereco.cidade VALUES (768, '2019-10-23 14:05:31.168533', NULL, 'Quixadá', 6);
INSERT INTO endereco.cidade VALUES (770, '2019-10-23 14:05:31.168533', NULL, 'Quixeramobim', 6);
INSERT INTO endereco.cidade VALUES (773, '2019-10-23 14:05:31.168533', NULL, 'Reriutaba', 6);
INSERT INTO endereco.cidade VALUES (774, '2019-10-23 14:05:31.168533', NULL, 'Russas', 6);
INSERT INTO endereco.cidade VALUES (775, '2019-10-23 14:05:31.168533', NULL, 'Saboeiro', 6);
INSERT INTO endereco.cidade VALUES (777, '2019-10-23 14:05:31.168533', NULL, 'Santa Quitéria', 6);
INSERT INTO endereco.cidade VALUES (779, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Cariri', 6);
INSERT INTO endereco.cidade VALUES (782, '2019-10-23 14:05:31.168533', NULL, 'São João Do Jaguaribe', 6);
INSERT INTO endereco.cidade VALUES (785, '2019-10-23 14:05:31.168533', NULL, 'Senador Sá', 6);
INSERT INTO endereco.cidade VALUES (787, '2019-10-23 14:05:31.168533', NULL, 'Solonópole', 6);
INSERT INTO endereco.cidade VALUES (789, '2019-10-23 14:05:31.168533', NULL, 'Tamboril', 6);
INSERT INTO endereco.cidade VALUES (792, '2019-10-23 14:05:31.168533', NULL, 'Tejuçuoca', 6);
INSERT INTO endereco.cidade VALUES (794, '2019-10-23 14:05:31.168533', NULL, 'Trairi', 6);
INSERT INTO endereco.cidade VALUES (796, '2019-10-23 14:05:31.168533', NULL, 'Ubajara', 6);
INSERT INTO endereco.cidade VALUES (799, '2019-10-23 14:05:31.168533', NULL, 'Uruburetama', 6);
INSERT INTO endereco.cidade VALUES (801, '2019-10-23 14:05:31.168533', NULL, 'Varjota', 6);
INSERT INTO endereco.cidade VALUES (803, '2019-10-23 14:05:31.168533', NULL, 'Viçosa Do Ceará', 6);
INSERT INTO endereco.cidade VALUES (805, '2019-10-23 14:05:31.168533', NULL, 'Afonso Cláudio', 8);
INSERT INTO endereco.cidade VALUES (808, '2019-10-23 14:05:31.168533', NULL, 'Alegre', 8);
INSERT INTO endereco.cidade VALUES (810, '2019-10-23 14:05:31.168533', NULL, 'Alto Rio Novo', 8);
INSERT INTO endereco.cidade VALUES (813, '2019-10-23 14:05:31.168533', NULL, 'Aracruz', 8);
INSERT INTO endereco.cidade VALUES (814, '2019-10-23 14:05:31.168533', NULL, 'Atilio Vivacqua', 8);
INSERT INTO endereco.cidade VALUES (817, '2019-10-23 14:05:31.168533', NULL, 'Boa Esperança', 8);
INSERT INTO endereco.cidade VALUES (820, '2019-10-23 14:05:31.168533', NULL, 'Cachoeiro De Itapemirim', 8);
INSERT INTO endereco.cidade VALUES (822, '2019-10-23 14:05:31.168533', NULL, 'Castelo', 8);
INSERT INTO endereco.cidade VALUES (824, '2019-10-23 14:05:31.168533', NULL, 'Conceição Da Barra', 8);
INSERT INTO endereco.cidade VALUES (827, '2019-10-23 14:05:31.168533', NULL, 'Domingos Martins', 8);
INSERT INTO endereco.cidade VALUES (830, '2019-10-23 14:05:31.168533', NULL, 'Fundão', 8);
INSERT INTO endereco.cidade VALUES (831, '2019-10-23 14:05:31.168533', NULL, 'Governador Lindenberg', 8);
INSERT INTO endereco.cidade VALUES (834, '2019-10-23 14:05:31.168533', NULL, 'Ibatiba', 8);
INSERT INTO endereco.cidade VALUES (837, '2019-10-23 14:05:31.168533', NULL, 'Iconha', 8);
INSERT INTO endereco.cidade VALUES (839, '2019-10-23 14:05:31.168533', NULL, 'Itaguaçu', 8);
INSERT INTO endereco.cidade VALUES (841, '2019-10-23 14:05:31.168533', NULL, 'Itarana', 8);
INSERT INTO endereco.cidade VALUES (844, '2019-10-23 14:05:31.168533', NULL, 'Jerônimo Monteiro', 8);
INSERT INTO endereco.cidade VALUES (846, '2019-10-23 14:05:31.168533', NULL, 'Laranja Da Terra', 8);
INSERT INTO endereco.cidade VALUES (848, '2019-10-23 14:05:31.168533', NULL, 'Mantenópolis', 8);
INSERT INTO endereco.cidade VALUES (851, '2019-10-23 14:05:31.168533', NULL, 'Marilândia', 8);
INSERT INTO endereco.cidade VALUES (853, '2019-10-23 14:05:31.168533', NULL, 'Montanha', 8);
INSERT INTO endereco.cidade VALUES (855, '2019-10-23 14:05:31.168533', NULL, 'Muniz Freire', 8);
INSERT INTO endereco.cidade VALUES (858, '2019-10-23 14:05:31.168533', NULL, 'Pancas', 8);
INSERT INTO endereco.cidade VALUES (860, '2019-10-23 14:05:31.168533', NULL, 'Pinheiros', 8);
INSERT INTO endereco.cidade VALUES (863, '2019-10-23 14:05:31.168533', NULL, 'Presidente Kennedy', 8);
INSERT INTO endereco.cidade VALUES (865, '2019-10-23 14:05:31.168533', NULL, 'Rio Novo Do Sul', 8);
INSERT INTO endereco.cidade VALUES (867, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria De Jetibá', 8);
INSERT INTO endereco.cidade VALUES (870, '2019-10-23 14:05:31.168533', NULL, 'São Gabriel Da Palha', 8);
INSERT INTO endereco.cidade VALUES (873, '2019-10-23 14:05:31.168533', NULL, 'São Roque Do Canaã', 8);
INSERT INTO endereco.cidade VALUES (876, '2019-10-23 14:05:31.168533', NULL, 'Vargem Alta', 8);
INSERT INTO endereco.cidade VALUES (878, '2019-10-23 14:05:31.168533', NULL, 'Viana', 8);
INSERT INTO endereco.cidade VALUES (880, '2019-10-23 14:05:31.168533', NULL, 'Vila Valério', 8);
INSERT INTO endereco.cidade VALUES (883, '2019-10-23 14:05:31.168533', NULL, 'Abadia De Goiás', 9);
INSERT INTO endereco.cidade VALUES (885, '2019-10-23 14:05:31.168533', NULL, 'Acreúna', 9);
INSERT INTO endereco.cidade VALUES (887, '2019-10-23 14:05:31.168533', NULL, 'Água Fria De Goiás', 9);
INSERT INTO endereco.cidade VALUES (890, '2019-10-23 14:05:31.168533', NULL, 'Alexânia', 9);
INSERT INTO endereco.cidade VALUES (892, '2019-10-23 14:05:31.168533', NULL, 'Alto Horizonte', 9);
INSERT INTO endereco.cidade VALUES (895, '2019-10-23 14:05:31.168533', NULL, 'Amaralina', 9);
INSERT INTO endereco.cidade VALUES (897, '2019-10-23 14:05:31.168533', NULL, 'Amorinópolis', 9);
INSERT INTO endereco.cidade VALUES (899, '2019-10-23 14:05:31.168533', NULL, 'Anhanguera', 9);
INSERT INTO endereco.cidade VALUES (900, '2019-10-23 14:05:31.168533', NULL, 'Anicuns', 9);
INSERT INTO endereco.cidade VALUES (901, '2019-10-23 14:05:31.168533', NULL, 'Aparecida De Goiânia', 9);
INSERT INTO endereco.cidade VALUES (904, '2019-10-23 14:05:31.168533', NULL, 'Araçu', 9);
INSERT INTO endereco.cidade VALUES (906, '2019-10-23 14:05:31.168533', NULL, 'Aragoiânia', 9);
INSERT INTO endereco.cidade VALUES (908, '2019-10-23 14:05:31.168533', NULL, 'Arenópolis', 9);
INSERT INTO endereco.cidade VALUES (911, '2019-10-23 14:05:31.168533', NULL, 'Avelinópolis', 9);
INSERT INTO endereco.cidade VALUES (913, '2019-10-23 14:05:31.168533', NULL, 'Barro Alto', 9);
INSERT INTO endereco.cidade VALUES (915, '2019-10-23 14:05:31.168533', NULL, 'Bom Jardim De Goiás', 9);
INSERT INTO endereco.cidade VALUES (918, '2019-10-23 14:05:31.168533', NULL, 'Bonópolis', 9);
INSERT INTO endereco.cidade VALUES (920, '2019-10-23 14:05:31.168533', NULL, 'Britânia', 9);
INSERT INTO endereco.cidade VALUES (922, '2019-10-23 14:05:31.168533', NULL, 'Buriti De Goiás', 9);
INSERT INTO endereco.cidade VALUES (925, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Alta', 9);
INSERT INTO endereco.cidade VALUES (927, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Dourada', 9);
INSERT INTO endereco.cidade VALUES (930, '2019-10-23 14:05:31.168533', NULL, 'Caldas Novas', 9);
INSERT INTO endereco.cidade VALUES (932, '2019-10-23 14:05:31.168533', NULL, 'Campestre De Goiás', 9);
INSERT INTO endereco.cidade VALUES (935, '2019-10-23 14:05:31.168533', NULL, 'Campo Alegre De Goiás', 9);
INSERT INTO endereco.cidade VALUES (937, '2019-10-23 14:05:31.168533', NULL, 'Campos Belos', 9);
INSERT INTO endereco.cidade VALUES (939, '2019-10-23 14:05:31.168533', NULL, 'Carmo Do Rio Verde', 9);
INSERT INTO endereco.cidade VALUES (942, '2019-10-23 14:05:31.168533', NULL, 'Caturaí', 9);
INSERT INTO endereco.cidade VALUES (945, '2019-10-23 14:05:31.168533', NULL, 'Cezarina', 9);
INSERT INTO endereco.cidade VALUES (947, '2019-10-23 14:05:31.168533', NULL, 'Cidade Ocidental', 9);
INSERT INTO endereco.cidade VALUES (949, '2019-10-23 14:05:31.168533', NULL, 'Colinas Do Sul', 9);
INSERT INTO endereco.cidade VALUES (952, '2019-10-23 14:05:31.168533', NULL, 'Corumbaíba', 9);
INSERT INTO endereco.cidade VALUES (954, '2019-10-23 14:05:31.168533', NULL, 'Cristianópolis', 9);
INSERT INTO endereco.cidade VALUES (956, '2019-10-23 14:05:31.168533', NULL, 'Cromínia', 9);
INSERT INTO endereco.cidade VALUES (959, '2019-10-23 14:05:31.168533', NULL, 'Damolândia', 9);
INSERT INTO endereco.cidade VALUES (961, '2019-10-23 14:05:31.168533', NULL, 'Diorama', 9);
INSERT INTO endereco.cidade VALUES (963, '2019-10-23 14:05:31.168533', NULL, 'Doverlândia', 9);
INSERT INTO endereco.cidade VALUES (966, '2019-10-23 14:05:31.168533', NULL, 'Estrela Do Norte', 9);
INSERT INTO endereco.cidade VALUES (968, '2019-10-23 14:05:31.168533', NULL, 'Fazenda Nova', 9);
INSERT INTO endereco.cidade VALUES (970, '2019-10-23 14:05:31.168533', NULL, 'Flores De Goiás', 9);
INSERT INTO endereco.cidade VALUES (973, '2019-10-23 14:05:31.168533', NULL, 'Gameleira De Goiás', 9);
INSERT INTO endereco.cidade VALUES (976, '2019-10-23 14:05:31.168533', NULL, 'Goianésia', 9);
INSERT INTO endereco.cidade VALUES (978, '2019-10-23 14:05:31.168533', NULL, 'Goianira', 9);
INSERT INTO endereco.cidade VALUES (980, '2019-10-23 14:05:31.168533', NULL, 'Goiatuba', 9);
INSERT INTO endereco.cidade VALUES (983, '2019-10-23 14:05:31.168533', NULL, 'Guaraíta', 9);
INSERT INTO endereco.cidade VALUES (985, '2019-10-23 14:05:31.168533', NULL, 'Guarinos', 9);
INSERT INTO endereco.cidade VALUES (987, '2019-10-23 14:05:31.168533', NULL, 'Hidrolândia', 9);
INSERT INTO endereco.cidade VALUES (990, '2019-10-23 14:05:31.168533', NULL, 'Inaciolândia', 9);
INSERT INTO endereco.cidade VALUES (992, '2019-10-23 14:05:31.168533', NULL, 'Inhumas', 9);
INSERT INTO endereco.cidade VALUES (994, '2019-10-23 14:05:31.168533', NULL, 'Ipiranga De Goiás', 9);
INSERT INTO endereco.cidade VALUES (997, '2019-10-23 14:05:31.168533', NULL, 'Itaberaí', 9);
INSERT INTO endereco.cidade VALUES (999, '2019-10-23 14:05:31.168533', NULL, 'Itaguaru', 9);
INSERT INTO endereco.cidade VALUES (1001, '2019-10-23 14:05:31.168533', NULL, 'Itapaci', 9);
INSERT INTO endereco.cidade VALUES (1004, '2019-10-23 14:05:31.168533', NULL, 'Itarumã', 9);
INSERT INTO endereco.cidade VALUES (1006, '2019-10-23 14:05:31.168533', NULL, 'Itumbiara', 9);
INSERT INTO endereco.cidade VALUES (1008, '2019-10-23 14:05:31.168533', NULL, 'Jandaia', 9);
INSERT INTO endereco.cidade VALUES (1010, '2019-10-23 14:05:31.168533', NULL, 'Jataí', 9);
INSERT INTO endereco.cidade VALUES (1013, '2019-10-23 14:05:31.168533', NULL, 'Joviânia', 9);
INSERT INTO endereco.cidade VALUES (1015, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Santa', 9);
INSERT INTO endereco.cidade VALUES (1017, '2019-10-23 14:05:31.168533', NULL, 'Luziânia', 9);
INSERT INTO endereco.cidade VALUES (1019, '2019-10-23 14:05:31.168533', NULL, 'Mambaí', 9);
INSERT INTO endereco.cidade VALUES (1022, '2019-10-23 14:05:31.168533', NULL, 'Matrinchã', 9);
INSERT INTO endereco.cidade VALUES (1024, '2019-10-23 14:05:31.168533', NULL, 'Mimoso De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1026, '2019-10-23 14:05:31.168533', NULL, 'Mineiros', 9);
INSERT INTO endereco.cidade VALUES (1028, '2019-10-23 14:05:31.168533', NULL, 'Monte Alegre De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1030, '2019-10-23 14:05:31.168533', NULL, 'Montividiu', 9);
INSERT INTO endereco.cidade VALUES (1032, '2019-10-23 14:05:31.168533', NULL, 'Morrinhos', 9);
INSERT INTO endereco.cidade VALUES (1035, '2019-10-23 14:05:31.168533', NULL, 'Mozarlândia', 9);
INSERT INTO endereco.cidade VALUES (1037, '2019-10-23 14:05:31.168533', NULL, 'Mutunópolis', 9);
INSERT INTO endereco.cidade VALUES (1040, '2019-10-23 14:05:31.168533', NULL, 'Niquelândia', 9);
INSERT INTO endereco.cidade VALUES (1042, '2019-10-23 14:05:31.168533', NULL, 'Nova Aurora', 9);
INSERT INTO endereco.cidade VALUES (1044, '2019-10-23 14:05:31.168533', NULL, 'Nova Glória', 9);
INSERT INTO endereco.cidade VALUES (1047, '2019-10-23 14:05:31.168533', NULL, 'Nova Veneza', 9);
INSERT INTO endereco.cidade VALUES (1049, '2019-10-23 14:05:31.168533', NULL, 'Novo Gama', 9);
INSERT INTO endereco.cidade VALUES (1051, '2019-10-23 14:05:31.168533', NULL, 'Orizona', 9);
INSERT INTO endereco.cidade VALUES (1053, '2019-10-23 14:05:31.168533', NULL, 'Ouvidor', 9);
INSERT INTO endereco.cidade VALUES (1055, '2019-10-23 14:05:31.168533', NULL, 'Palestina De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1058, '2019-10-23 14:05:31.168533', NULL, 'Palminópolis', 9);
INSERT INTO endereco.cidade VALUES (1060, '2019-10-23 14:05:31.168533', NULL, 'Paranaiguara', 9);
INSERT INTO endereco.cidade VALUES (1063, '2019-10-23 14:05:31.168533', NULL, 'Petrolina De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1065, '2019-10-23 14:05:31.168533', NULL, 'Piracanjuba', 9);
INSERT INTO endereco.cidade VALUES (1067, '2019-10-23 14:05:31.168533', NULL, 'Pirenópolis', 9);
INSERT INTO endereco.cidade VALUES (1070, '2019-10-23 14:05:31.168533', NULL, 'Pontalina', 9);
INSERT INTO endereco.cidade VALUES (1072, '2019-10-23 14:05:31.168533', NULL, 'Porteirão', 9);
INSERT INTO endereco.cidade VALUES (1075, '2019-10-23 14:05:31.168533', NULL, 'Professor Jamil', 9);
INSERT INTO endereco.cidade VALUES (1077, '2019-10-23 14:05:31.168533', NULL, 'Rialma', 9);
INSERT INTO endereco.cidade VALUES (1079, '2019-10-23 14:05:31.168533', NULL, 'Rio Quente', 9);
INSERT INTO endereco.cidade VALUES (1082, '2019-10-23 14:05:31.168533', NULL, 'Sanclerlândia', 9);
INSERT INTO endereco.cidade VALUES (1084, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1086, '2019-10-23 14:05:31.168533', NULL, 'Santa Helena De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1089, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita Do Novo Destino', 9);
INSERT INTO endereco.cidade VALUES (1091, '2019-10-23 14:05:31.168533', NULL, 'Santa Tereza De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1094, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1097, '2019-10-23 14:05:31.168533', NULL, 'São Francisco De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1099, '2019-10-23 14:05:31.168533', NULL, 'São João Da Paraúna', 9);
INSERT INTO endereco.cidade VALUES (1102, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Araguaia', 9);
INSERT INTO endereco.cidade VALUES (1105, '2019-10-23 14:05:31.168533', NULL, 'São Simão', 9);
INSERT INTO endereco.cidade VALUES (1107, '2019-10-23 14:05:31.168533', NULL, 'Serranópolis', 9);
INSERT INTO endereco.cidade VALUES (1110, '2019-10-23 14:05:31.168533', NULL, 'Sítio D`Abadia', 9);
INSERT INTO endereco.cidade VALUES (1112, '2019-10-23 14:05:31.168533', NULL, 'Teresina De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1114, '2019-10-23 14:05:31.168533', NULL, 'Três Ranchos', 9);
INSERT INTO endereco.cidade VALUES (1117, '2019-10-23 14:05:31.168533', NULL, 'Turvânia', 9);
INSERT INTO endereco.cidade VALUES (1119, '2019-10-23 14:05:31.168533', NULL, 'Uirapuru', 9);
INSERT INTO endereco.cidade VALUES (1121, '2019-10-23 14:05:31.168533', NULL, 'Uruana', 9);
INSERT INTO endereco.cidade VALUES (1123, '2019-10-23 14:05:31.168533', NULL, 'Valparaíso De Goiás', 9);
INSERT INTO endereco.cidade VALUES (1126, '2019-10-23 14:05:31.168533', NULL, 'Vicentinópolis', 9);
INSERT INTO endereco.cidade VALUES (1129, '2019-10-23 14:05:31.168533', NULL, 'Açailândia', 10);
INSERT INTO endereco.cidade VALUES (1131, '2019-10-23 14:05:31.168533', NULL, 'Água Doce Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1134, '2019-10-23 14:05:31.168533', NULL, 'Altamira Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1136, '2019-10-23 14:05:31.168533', NULL, 'Alto Alegre Do Pindaré', 10);
INSERT INTO endereco.cidade VALUES (1139, '2019-10-23 14:05:31.168533', NULL, 'Amarante Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1142, '2019-10-23 14:05:31.168533', NULL, 'Apicum Açu', 10);
INSERT INTO endereco.cidade VALUES (1144, '2019-10-23 14:05:31.168533', NULL, 'Araioses', 10);
INSERT INTO endereco.cidade VALUES (1146, '2019-10-23 14:05:31.168533', NULL, 'Arari', 10);
INSERT INTO endereco.cidade VALUES (1149, '2019-10-23 14:05:31.168533', NULL, 'Bacabeira', 10);
INSERT INTO endereco.cidade VALUES (1150, '2019-10-23 14:05:31.168533', NULL, 'Bacuri', 10);
INSERT INTO endereco.cidade VALUES (1151, '2019-10-23 14:05:31.168533', NULL, 'Bacurituba', 10);
INSERT INTO endereco.cidade VALUES (1153, '2019-10-23 14:05:31.168533', NULL, 'Barão De Grajaú', 10);
INSERT INTO endereco.cidade VALUES (1155, '2019-10-23 14:05:31.168533', NULL, 'Barreirinhas', 10);
INSERT INTO endereco.cidade VALUES (1158, '2019-10-23 14:05:31.168533', NULL, 'Benedito Leite', 10);
INSERT INTO endereco.cidade VALUES (1160, '2019-10-23 14:05:31.168533', NULL, 'Bernardo Do Mearim', 10);
INSERT INTO endereco.cidade VALUES (1163, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Das Selvas', 10);
INSERT INTO endereco.cidade VALUES (1165, '2019-10-23 14:05:31.168533', NULL, 'Brejo', 10);
INSERT INTO endereco.cidade VALUES (1168, '2019-10-23 14:05:31.168533', NULL, 'Buriti Bravo', 10);
INSERT INTO endereco.cidade VALUES (1170, '2019-10-23 14:05:31.168533', NULL, 'Buritirana', 10);
INSERT INTO endereco.cidade VALUES (1172, '2019-10-23 14:05:31.168533', NULL, 'Cajapió', 10);
INSERT INTO endereco.cidade VALUES (1174, '2019-10-23 14:05:31.168533', NULL, 'Campestre Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1177, '2019-10-23 14:05:31.168533', NULL, 'Capinzal Do Norte', 10);
INSERT INTO endereco.cidade VALUES (1180, '2019-10-23 14:05:31.168533', NULL, 'Caxias', 10);
INSERT INTO endereco.cidade VALUES (1182, '2019-10-23 14:05:31.168533', NULL, 'Central Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1184, '2019-10-23 14:05:31.168533', NULL, 'Centro Novo Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1187, '2019-10-23 14:05:31.168533', NULL, 'Codó', 10);
INSERT INTO endereco.cidade VALUES (1189, '2019-10-23 14:05:31.168533', NULL, 'Colinas', 10);
INSERT INTO endereco.cidade VALUES (1192, '2019-10-23 14:05:31.168533', NULL, 'Cururupu', 10);
INSERT INTO endereco.cidade VALUES (1194, '2019-10-23 14:05:31.168533', NULL, 'Dom Pedro', 10);
INSERT INTO endereco.cidade VALUES (1196, '2019-10-23 14:05:31.168533', NULL, 'Esperantinópolis', 10);
INSERT INTO endereco.cidade VALUES (1198, '2019-10-23 14:05:31.168533', NULL, 'Feira Nova Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1201, '2019-10-23 14:05:31.168533', NULL, 'Fortaleza Dos Nogueiras', 10);
INSERT INTO endereco.cidade VALUES (1204, '2019-10-23 14:05:31.168533', NULL, 'Gonçalves Dias', 10);
INSERT INTO endereco.cidade VALUES (1206, '2019-10-23 14:05:31.168533', NULL, 'Governador Edison Lobão', 10);
INSERT INTO endereco.cidade VALUES (1208, '2019-10-23 14:05:31.168533', NULL, 'Governador Luiz Rocha', 10);
INSERT INTO endereco.cidade VALUES (1211, '2019-10-23 14:05:31.168533', NULL, 'Graça Aranha', 10);
INSERT INTO endereco.cidade VALUES (1214, '2019-10-23 14:05:31.168533', NULL, 'Humberto De Campos', 10);
INSERT INTO endereco.cidade VALUES (1216, '2019-10-23 14:05:31.168533', NULL, 'Igarapé Do Meio', 10);
INSERT INTO endereco.cidade VALUES (1219, '2019-10-23 14:05:31.168533', NULL, 'Itaipava Do Grajaú', 10);
INSERT INTO endereco.cidade VALUES (1221, '2019-10-23 14:05:31.168533', NULL, 'Itinga Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1223, '2019-10-23 14:05:31.168533', NULL, 'Jenipapo Dos Vieiras', 10);
INSERT INTO endereco.cidade VALUES (1226, '2019-10-23 14:05:31.168533', NULL, 'Junco Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1228, '2019-10-23 14:05:31.168533', NULL, 'Lago Do Junco', 10);
INSERT INTO endereco.cidade VALUES (1231, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Do Mato', 10);
INSERT INTO endereco.cidade VALUES (1233, '2019-10-23 14:05:31.168533', NULL, 'Lajeado Novo', 10);
INSERT INTO endereco.cidade VALUES (1236, '2019-10-23 14:05:31.168533', NULL, 'Luís Domingues', 10);
INSERT INTO endereco.cidade VALUES (1238, '2019-10-23 14:05:31.168533', NULL, 'Maracaçumé', 10);
INSERT INTO endereco.cidade VALUES (1240, '2019-10-23 14:05:31.168533', NULL, 'Maranhãozinho', 10);
INSERT INTO endereco.cidade VALUES (1243, '2019-10-23 14:05:31.168533', NULL, 'Matões', 10);
INSERT INTO endereco.cidade VALUES (1245, '2019-10-23 14:05:31.168533', NULL, 'Milagres Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1248, '2019-10-23 14:05:31.168533', NULL, 'Mirinzal', 10);
INSERT INTO endereco.cidade VALUES (1250, '2019-10-23 14:05:31.168533', NULL, 'Montes Altos', 10);
INSERT INTO endereco.cidade VALUES (1253, '2019-10-23 14:05:31.168533', NULL, 'Nova Colinas', 10);
INSERT INTO endereco.cidade VALUES (1255, '2019-10-23 14:05:31.168533', NULL, 'Nova Olinda Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1257, '2019-10-23 14:05:31.168533', NULL, 'Olinda Nova Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1260, '2019-10-23 14:05:31.168533', NULL, 'Paraibano', 10);
INSERT INTO endereco.cidade VALUES (1263, '2019-10-23 14:05:31.168533', NULL, 'Pastos Bons', 10);
INSERT INTO endereco.cidade VALUES (1265, '2019-10-23 14:05:31.168533', NULL, 'Paulo Ramos', 10);
INSERT INTO endereco.cidade VALUES (1267, '2019-10-23 14:05:31.168533', NULL, 'Pedro Do Rosário', 10);
INSERT INTO endereco.cidade VALUES (1270, '2019-10-23 14:05:31.168533', NULL, 'Peritoró', 10);
INSERT INTO endereco.cidade VALUES (1272, '2019-10-23 14:05:31.168533', NULL, 'Pinheiro', 10);
INSERT INTO endereco.cidade VALUES (1274, '2019-10-23 14:05:31.168533', NULL, 'Pirapemas', 10);
INSERT INTO endereco.cidade VALUES (1275, '2019-10-23 14:05:31.168533', NULL, 'Poção De Pedras', 10);
INSERT INTO endereco.cidade VALUES (1278, '2019-10-23 14:05:31.168533', NULL, 'Presidente Dutra', 10);
INSERT INTO endereco.cidade VALUES (1280, '2019-10-23 14:05:31.168533', NULL, 'Presidente Médici', 10);
INSERT INTO endereco.cidade VALUES (1283, '2019-10-23 14:05:31.168533', NULL, 'Primeira Cruz', 10);
INSERT INTO endereco.cidade VALUES (1285, '2019-10-23 14:05:31.168533', NULL, 'Riachão', 10);
INSERT INTO endereco.cidade VALUES (1288, '2019-10-23 14:05:31.168533', NULL, 'Sambaíba', 10);
INSERT INTO endereco.cidade VALUES (1289, '2019-10-23 14:05:31.168533', NULL, 'Santa Filomena Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1292, '2019-10-23 14:05:31.168533', NULL, 'Santa Luzia', 10);
INSERT INTO endereco.cidade VALUES (1294, '2019-10-23 14:05:31.168533', NULL, 'Santa Quitéria Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1297, '2019-10-23 14:05:31.168533', NULL, 'Santo Amaro Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1299, '2019-10-23 14:05:31.168533', NULL, 'São Benedito Do Rio Preto', 10);
INSERT INTO endereco.cidade VALUES (1302, '2019-10-23 14:05:31.168533', NULL, 'São Domingos Do Azeitão', 10);
INSERT INTO endereco.cidade VALUES (1305, '2019-10-23 14:05:31.168533', NULL, 'São Francisco Do Brejão', 10);
INSERT INTO endereco.cidade VALUES (1307, '2019-10-23 14:05:31.168533', NULL, 'São João Batista', 10);
INSERT INTO endereco.cidade VALUES (1309, '2019-10-23 14:05:31.168533', NULL, 'São João Do Paraíso', 10);
INSERT INTO endereco.cidade VALUES (1312, '2019-10-23 14:05:31.168533', NULL, 'São José De Ribamar', 10);
INSERT INTO endereco.cidade VALUES (1315, '2019-10-23 14:05:31.168533', NULL, 'São Luís Gonzaga Do Maranhão', 10);
INSERT INTO endereco.cidade VALUES (1318, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Dos Crentes', 10);
INSERT INTO endereco.cidade VALUES (1320, '2019-10-23 14:05:31.168533', NULL, 'São Raimundo Do Doca Bezerra', 10);
INSERT INTO endereco.cidade VALUES (1323, '2019-10-23 14:05:31.168533', NULL, 'Satubinha', 10);
INSERT INTO endereco.cidade VALUES (1325, '2019-10-23 14:05:31.168533', NULL, 'Senador La Rocque', 10);
INSERT INTO endereco.cidade VALUES (1328, '2019-10-23 14:05:31.168533', NULL, 'Sucupira Do Norte', 10);
INSERT INTO endereco.cidade VALUES (1330, '2019-10-23 14:05:31.168533', NULL, 'Tasso Fragoso', 10);
INSERT INTO endereco.cidade VALUES (1333, '2019-10-23 14:05:31.168533', NULL, 'Trizidela Do Vale', 10);
INSERT INTO endereco.cidade VALUES (1335, '2019-10-23 14:05:31.168533', NULL, 'Tuntum', 10);
INSERT INTO endereco.cidade VALUES (1338, '2019-10-23 14:05:31.168533', NULL, 'Tutóia', 10);
INSERT INTO endereco.cidade VALUES (1340, '2019-10-23 14:05:31.168533', NULL, 'Vargem Grande', 10);
INSERT INTO endereco.cidade VALUES (1342, '2019-10-23 14:05:31.168533', NULL, 'Vila Nova Dos Martírios', 10);
INSERT INTO endereco.cidade VALUES (1345, '2019-10-23 14:05:31.168533', NULL, 'Zé Doca', 10);
INSERT INTO endereco.cidade VALUES (1347, '2019-10-23 14:05:31.168533', NULL, 'Abaeté', 11);
INSERT INTO endereco.cidade VALUES (1349, '2019-10-23 14:05:31.168533', NULL, 'Acaiaca', 11);
INSERT INTO endereco.cidade VALUES (1352, '2019-10-23 14:05:31.168533', NULL, 'Água Comprida', 11);
INSERT INTO endereco.cidade VALUES (1354, '2019-10-23 14:05:31.168533', NULL, 'Águas Formosas', 11);
INSERT INTO endereco.cidade VALUES (1356, '2019-10-23 14:05:31.168533', NULL, 'Aimorés', 11);
INSERT INTO endereco.cidade VALUES (1359, '2019-10-23 14:05:31.168533', NULL, 'Albertina', 11);
INSERT INTO endereco.cidade VALUES (1361, '2019-10-23 14:05:31.168533', NULL, 'Alfenas', 11);
INSERT INTO endereco.cidade VALUES (1363, '2019-10-23 14:05:31.168533', NULL, 'Almenara', 11);
INSERT INTO endereco.cidade VALUES (1365, '2019-10-23 14:05:31.168533', NULL, 'Alpinópolis', 11);
INSERT INTO endereco.cidade VALUES (1368, '2019-10-23 14:05:31.168533', NULL, 'Alto JequitibÁ', 11);
INSERT INTO endereco.cidade VALUES (1370, '2019-10-23 14:05:31.168533', NULL, 'Alvarenga', 11);
INSERT INTO endereco.cidade VALUES (1372, '2019-10-23 14:05:31.168533', NULL, 'Alvorada De Minas', 11);
INSERT INTO endereco.cidade VALUES (1375, '2019-10-23 14:05:31.168533', NULL, 'Andrelândia', 11);
INSERT INTO endereco.cidade VALUES (1377, '2019-10-23 14:05:31.168533', NULL, 'Antônio Carlos', 11);
INSERT INTO endereco.cidade VALUES (1379, '2019-10-23 14:05:31.168533', NULL, 'Antônio Prado De Minas', 11);
INSERT INTO endereco.cidade VALUES (1382, '2019-10-23 14:05:31.168533', NULL, 'Araçuaí', 11);
INSERT INTO endereco.cidade VALUES (1385, '2019-10-23 14:05:31.168533', NULL, 'Araponga', 11);
INSERT INTO endereco.cidade VALUES (1387, '2019-10-23 14:05:31.168533', NULL, 'ArapuÁ', 11);
INSERT INTO endereco.cidade VALUES (1389, '2019-10-23 14:05:31.168533', NULL, 'AraxÁ', 11);
INSERT INTO endereco.cidade VALUES (1391, '2019-10-23 14:05:31.168533', NULL, 'Arcos', 11);
INSERT INTO endereco.cidade VALUES (1394, '2019-10-23 14:05:31.168533', NULL, 'Aricanduva', 11);
INSERT INTO endereco.cidade VALUES (1396, '2019-10-23 14:05:31.168533', NULL, 'Astolfo Dutra', 11);
INSERT INTO endereco.cidade VALUES (1397, '2019-10-23 14:05:31.168533', NULL, 'Ataléia', 11);
INSERT INTO endereco.cidade VALUES (1400, '2019-10-23 14:05:31.168533', NULL, 'Baldim', 11);
INSERT INTO endereco.cidade VALUES (1402, '2019-10-23 14:05:31.168533', NULL, 'Bandeira', 11);
INSERT INTO endereco.cidade VALUES (1404, '2019-10-23 14:05:31.168533', NULL, 'Barão De Cocais', 11);
INSERT INTO endereco.cidade VALUES (1407, '2019-10-23 14:05:31.168533', NULL, 'Barra Longa', 11);
INSERT INTO endereco.cidade VALUES (1409, '2019-10-23 14:05:31.168533', NULL, 'Bela Vista De Minas', 11);
INSERT INTO endereco.cidade VALUES (1411, '2019-10-23 14:05:31.168533', NULL, 'Belo Horizonte', 11);
INSERT INTO endereco.cidade VALUES (1414, '2019-10-23 14:05:31.168533', NULL, 'Berilo', 11);
INSERT INTO endereco.cidade VALUES (1416, '2019-10-23 14:05:31.168533', NULL, 'Bertópolis', 11);
INSERT INTO endereco.cidade VALUES (1418, '2019-10-23 14:05:31.168533', NULL, 'Bias Fortes', 11);
INSERT INTO endereco.cidade VALUES (1421, '2019-10-23 14:05:31.168533', NULL, 'Boa Esperança', 11);
INSERT INTO endereco.cidade VALUES (1423, '2019-10-23 14:05:31.168533', NULL, 'Bocaiúva', 11);
INSERT INTO endereco.cidade VALUES (1425, '2019-10-23 14:05:31.168533', NULL, 'Bom Jardim De Minas', 11);
INSERT INTO endereco.cidade VALUES (1428, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Do Galho', 11);
INSERT INTO endereco.cidade VALUES (1430, '2019-10-23 14:05:31.168533', NULL, 'Bom Sucesso', 11);
INSERT INTO endereco.cidade VALUES (1432, '2019-10-23 14:05:31.168533', NULL, 'Bonfinópolis De Minas', 11);
INSERT INTO endereco.cidade VALUES (1435, '2019-10-23 14:05:31.168533', NULL, 'Botelhos', 11);
INSERT INTO endereco.cidade VALUES (1437, '2019-10-23 14:05:31.168533', NULL, 'BrÁs Pires', 11);
INSERT INTO endereco.cidade VALUES (1439, '2019-10-23 14:05:31.168533', NULL, 'Brasília De Minas', 11);
INSERT INTO endereco.cidade VALUES (1442, '2019-10-23 14:05:31.168533', NULL, 'Brumadinho', 11);
INSERT INTO endereco.cidade VALUES (1444, '2019-10-23 14:05:31.168533', NULL, 'Buenópolis', 11);
INSERT INTO endereco.cidade VALUES (1447, '2019-10-23 14:05:31.168533', NULL, 'Buritizeiro', 11);
INSERT INTO endereco.cidade VALUES (1449, '2019-10-23 14:05:31.168533', NULL, 'Cabo Verde', 11);
INSERT INTO endereco.cidade VALUES (1451, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira De Minas', 11);
INSERT INTO endereco.cidade VALUES (1453, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Dourada', 11);
INSERT INTO endereco.cidade VALUES (1456, '2019-10-23 14:05:31.168533', NULL, 'Caiana', 11);
INSERT INTO endereco.cidade VALUES (1458, '2019-10-23 14:05:31.168533', NULL, 'Caldas', 11);
INSERT INTO endereco.cidade VALUES (1461, '2019-10-23 14:05:31.168533', NULL, 'Cambuí', 11);
INSERT INTO endereco.cidade VALUES (1463, '2019-10-23 14:05:31.168533', NULL, 'CampanÁrio', 11);
INSERT INTO endereco.cidade VALUES (1465, '2019-10-23 14:05:31.168533', NULL, 'Campestre', 11);
INSERT INTO endereco.cidade VALUES (1467, '2019-10-23 14:05:31.168533', NULL, 'Campo Azul', 11);
INSERT INTO endereco.cidade VALUES (1469, '2019-10-23 14:05:31.168533', NULL, 'Campo Do Meio', 11);
INSERT INTO endereco.cidade VALUES (1472, '2019-10-23 14:05:31.168533', NULL, 'Campos Gerais', 11);
INSERT INTO endereco.cidade VALUES (1474, '2019-10-23 14:05:31.168533', NULL, 'Canaã', 11);
INSERT INTO endereco.cidade VALUES (1477, '2019-10-23 14:05:31.168533', NULL, 'Cantagalo', 11);
INSERT INTO endereco.cidade VALUES (1479, '2019-10-23 14:05:31.168533', NULL, 'Capela Nova', 11);
INSERT INTO endereco.cidade VALUES (1481, '2019-10-23 14:05:31.168533', NULL, 'Capetinga', 11);
INSERT INTO endereco.cidade VALUES (1483, '2019-10-23 14:05:31.168533', NULL, 'Capinópolis', 11);
INSERT INTO endereco.cidade VALUES (1485, '2019-10-23 14:05:31.168533', NULL, 'Capitão Enéas', 11);
INSERT INTO endereco.cidade VALUES (1488, '2019-10-23 14:05:31.168533', NULL, 'Caraí', 11);
INSERT INTO endereco.cidade VALUES (1490, '2019-10-23 14:05:31.168533', NULL, 'Carandaí', 11);
INSERT INTO endereco.cidade VALUES (1492, '2019-10-23 14:05:31.168533', NULL, 'Caratinga', 11);
INSERT INTO endereco.cidade VALUES (1495, '2019-10-23 14:05:31.168533', NULL, 'Carlos Chagas', 11);
INSERT INTO endereco.cidade VALUES (1497, '2019-10-23 14:05:31.168533', NULL, 'Carmo Da Cachoeira', 11);
INSERT INTO endereco.cidade VALUES (1499, '2019-10-23 14:05:31.168533', NULL, 'Carmo De Minas', 11);
INSERT INTO endereco.cidade VALUES (1502, '2019-10-23 14:05:31.168533', NULL, 'Carmo Do Rio Claro', 11);
INSERT INTO endereco.cidade VALUES (1505, '2019-10-23 14:05:31.168533', NULL, 'Carrancas', 11);
INSERT INTO endereco.cidade VALUES (1507, '2019-10-23 14:05:31.168533', NULL, 'Carvalhos', 11);
INSERT INTO endereco.cidade VALUES (1509, '2019-10-23 14:05:31.168533', NULL, 'Cascalho Rico', 11);
INSERT INTO endereco.cidade VALUES (1512, '2019-10-23 14:05:31.168533', NULL, 'Catas Altas', 11);
INSERT INTO endereco.cidade VALUES (1513, '2019-10-23 14:05:31.168533', NULL, 'Catas Altas Da Noruega', 11);
INSERT INTO endereco.cidade VALUES (1516, '2019-10-23 14:05:31.168533', NULL, 'Caxambu', 11);
INSERT INTO endereco.cidade VALUES (1519, '2019-10-23 14:05:31.168533', NULL, 'Centralina', 11);
INSERT INTO endereco.cidade VALUES (1521, '2019-10-23 14:05:31.168533', NULL, 'Chalé', 11);
INSERT INTO endereco.cidade VALUES (1523, '2019-10-23 14:05:31.168533', NULL, 'Chapada Gaúcha', 11);
INSERT INTO endereco.cidade VALUES (1524, '2019-10-23 14:05:31.168533', NULL, 'Chiador', 11);
INSERT INTO endereco.cidade VALUES (1526, '2019-10-23 14:05:31.168533', NULL, 'Claraval', 11);
INSERT INTO endereco.cidade VALUES (1528, '2019-10-23 14:05:31.168533', NULL, 'ClÁudio', 11);
INSERT INTO endereco.cidade VALUES (1530, '2019-10-23 14:05:31.168533', NULL, 'Coluna', 11);
INSERT INTO endereco.cidade VALUES (1532, '2019-10-23 14:05:31.168533', NULL, 'Comercinho', 11);
INSERT INTO endereco.cidade VALUES (1534, '2019-10-23 14:05:31.168533', NULL, 'Conceição Da Barra De Minas', 11);
INSERT INTO endereco.cidade VALUES (1537, '2019-10-23 14:05:31.168533', NULL, 'Conceição De Ipanema', 11);
INSERT INTO endereco.cidade VALUES (1540, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Rio Verde', 11);
INSERT INTO endereco.cidade VALUES (1542, '2019-10-23 14:05:31.168533', NULL, 'Cônego Marinho', 11);
INSERT INTO endereco.cidade VALUES (1545, '2019-10-23 14:05:31.168533', NULL, 'Congonhas', 11);
INSERT INTO endereco.cidade VALUES (1547, '2019-10-23 14:05:31.168533', NULL, 'Conquista', 11);
INSERT INTO endereco.cidade VALUES (1549, '2019-10-23 14:05:31.168533', NULL, 'Conselheiro Pena', 11);
INSERT INTO endereco.cidade VALUES (1552, '2019-10-23 14:05:31.168533', NULL, 'Coqueiral', 11);
INSERT INTO endereco.cidade VALUES (1554, '2019-10-23 14:05:31.168533', NULL, 'Cordisburgo', 11);
INSERT INTO endereco.cidade VALUES (1557, '2019-10-23 14:05:31.168533', NULL, 'Coroaci', 11);
INSERT INTO endereco.cidade VALUES (1559, '2019-10-23 14:05:31.168533', NULL, 'Coronel Fabriciano', 11);
INSERT INTO endereco.cidade VALUES (1561, '2019-10-23 14:05:31.168533', NULL, 'Coronel Pacheco', 11);
INSERT INTO endereco.cidade VALUES (1564, '2019-10-23 14:05:31.168533', NULL, 'Córrego Do Bom Jesus', 11);
INSERT INTO endereco.cidade VALUES (1566, '2019-10-23 14:05:31.168533', NULL, 'Córrego Novo', 11);
INSERT INTO endereco.cidade VALUES (1569, '2019-10-23 14:05:31.168533', NULL, 'Cristais', 11);
INSERT INTO endereco.cidade VALUES (1571, '2019-10-23 14:05:31.168533', NULL, 'Cristiano Otoni', 11);
INSERT INTO endereco.cidade VALUES (1573, '2019-10-23 14:05:31.168533', NULL, 'Crucilândia', 11);
INSERT INTO endereco.cidade VALUES (1576, '2019-10-23 14:05:31.168533', NULL, 'Cuparaque', 11);
INSERT INTO endereco.cidade VALUES (1578, '2019-10-23 14:05:31.168533', NULL, 'Curvelo', 11);
INSERT INTO endereco.cidade VALUES (1580, '2019-10-23 14:05:31.168533', NULL, 'Delfim Moreira', 11);
INSERT INTO endereco.cidade VALUES (1583, '2019-10-23 14:05:31.168533', NULL, 'Descoberto', 11);
INSERT INTO endereco.cidade VALUES (1585, '2019-10-23 14:05:31.168533', NULL, 'Desterro Do Melo', 11);
INSERT INTO endereco.cidade VALUES (1587, '2019-10-23 14:05:31.168533', NULL, 'Diogo De Vasconcelos', 11);
INSERT INTO endereco.cidade VALUES (1590, '2019-10-23 14:05:31.168533', NULL, 'Divino', 11);
INSERT INTO endereco.cidade VALUES (1592, '2019-10-23 14:05:31.168533', NULL, 'Divinolândia De Minas', 11);
INSERT INTO endereco.cidade VALUES (1595, '2019-10-23 14:05:31.168533', NULL, 'Divisa Nova', 11);
INSERT INTO endereco.cidade VALUES (1598, '2019-10-23 14:05:31.168533', NULL, 'Dom Cavati', 11);
INSERT INTO endereco.cidade VALUES (1600, '2019-10-23 14:05:31.168533', NULL, 'Dom Silvério', 11);
INSERT INTO endereco.cidade VALUES (1602, '2019-10-23 14:05:31.168533', NULL, 'Dona Eusébia', 11);
INSERT INTO endereco.cidade VALUES (1605, '2019-10-23 14:05:31.168533', NULL, 'Dores Do IndaiÁ', 11);
INSERT INTO endereco.cidade VALUES (1608, '2019-10-23 14:05:31.168533', NULL, 'Douradoquara', 11);
INSERT INTO endereco.cidade VALUES (1610, '2019-10-23 14:05:31.168533', NULL, 'Elói Mendes', 11);
INSERT INTO endereco.cidade VALUES (1612, '2019-10-23 14:05:31.168533', NULL, 'Engenheiro Navarro', 11);
INSERT INTO endereco.cidade VALUES (1615, '2019-10-23 14:05:31.168533', NULL, 'ErvÁlia', 11);
INSERT INTO endereco.cidade VALUES (1617, '2019-10-23 14:05:31.168533', NULL, 'Espera Feliz', 11);
INSERT INTO endereco.cidade VALUES (1619, '2019-10-23 14:05:31.168533', NULL, 'Espírito Santo Do Dourado', 11);
INSERT INTO endereco.cidade VALUES (1622, '2019-10-23 14:05:31.168533', NULL, 'Estrela Do IndaiÁ', 11);
INSERT INTO endereco.cidade VALUES (1624, '2019-10-23 14:05:31.168533', NULL, 'Eugenópolis', 11);
INSERT INTO endereco.cidade VALUES (1627, '2019-10-23 14:05:31.168533', NULL, 'Fama', 11);
INSERT INTO endereco.cidade VALUES (1629, '2019-10-23 14:05:31.168533', NULL, 'Felício Dos Santos', 11);
INSERT INTO endereco.cidade VALUES (1632, '2019-10-23 14:05:31.168533', NULL, 'Fernandes Tourinho', 11);
INSERT INTO endereco.cidade VALUES (1634, '2019-10-23 14:05:31.168533', NULL, 'Fervedouro', 11);
INSERT INTO endereco.cidade VALUES (1636, '2019-10-23 14:05:31.168533', NULL, 'Formiga', 11);
INSERT INTO endereco.cidade VALUES (1639, '2019-10-23 14:05:31.168533', NULL, 'Fortuna De Minas', 11);
INSERT INTO endereco.cidade VALUES (1641, '2019-10-23 14:05:31.168533', NULL, 'Francisco Dumont', 11);
INSERT INTO endereco.cidade VALUES (1643, '2019-10-23 14:05:31.168533', NULL, 'Franciscópolis', 11);
INSERT INTO endereco.cidade VALUES (1646, '2019-10-23 14:05:31.168533', NULL, 'Frei Lagonegro', 11);
INSERT INTO endereco.cidade VALUES (1647, '2019-10-23 14:05:31.168533', NULL, 'Fronteira', 11);
INSERT INTO endereco.cidade VALUES (1648, '2019-10-23 14:05:31.168533', NULL, 'Fronteira Dos Vales', 11);
INSERT INTO endereco.cidade VALUES (1651, '2019-10-23 14:05:31.168533', NULL, 'Funilândia', 11);
INSERT INTO endereco.cidade VALUES (1653, '2019-10-23 14:05:31.168533', NULL, 'Gameleiras', 11);
INSERT INTO endereco.cidade VALUES (1656, '2019-10-23 14:05:31.168533', NULL, 'GoianÁ', 11);
INSERT INTO endereco.cidade VALUES (1658, '2019-10-23 14:05:31.168533', NULL, 'Gonzaga', 11);
INSERT INTO endereco.cidade VALUES (1660, '2019-10-23 14:05:31.168533', NULL, 'Governador Valadares', 11);
INSERT INTO endereco.cidade VALUES (1663, '2019-10-23 14:05:31.168533', NULL, 'Guanhães', 11);
INSERT INTO endereco.cidade VALUES (1665, '2019-10-23 14:05:31.168533', NULL, 'Guaraciaba', 11);
INSERT INTO endereco.cidade VALUES (1667, '2019-10-23 14:05:31.168533', NULL, 'Guaranésia', 11);
INSERT INTO endereco.cidade VALUES (1670, '2019-10-23 14:05:31.168533', NULL, 'Guarda Mor', 11);
INSERT INTO endereco.cidade VALUES (1672, '2019-10-23 14:05:31.168533', NULL, 'Guidoval', 11);
INSERT INTO endereco.cidade VALUES (1674, '2019-10-23 14:05:31.168533', NULL, 'Guiricema', 11);
INSERT INTO endereco.cidade VALUES (1676, '2019-10-23 14:05:31.168533', NULL, 'Heliodora', 11);
INSERT INTO endereco.cidade VALUES (1679, '2019-10-23 14:05:31.168533', NULL, 'IbiÁ', 11);
INSERT INTO endereco.cidade VALUES (1681, '2019-10-23 14:05:31.168533', NULL, 'Ibiracatu', 11);
INSERT INTO endereco.cidade VALUES (1683, '2019-10-23 14:05:31.168533', NULL, 'Ibirité', 11);
INSERT INTO endereco.cidade VALUES (1685, '2019-10-23 14:05:31.168533', NULL, 'Ibituruna', 11);
INSERT INTO endereco.cidade VALUES (1688, '2019-10-23 14:05:31.168533', NULL, 'Igaratinga', 11);
INSERT INTO endereco.cidade VALUES (1690, '2019-10-23 14:05:31.168533', NULL, 'Ijaci', 11);
INSERT INTO endereco.cidade VALUES (1692, '2019-10-23 14:05:31.168533', NULL, 'Imbé De Minas', 11);
INSERT INTO endereco.cidade VALUES (1695, '2019-10-23 14:05:31.168533', NULL, 'Indianópolis', 11);
INSERT INTO endereco.cidade VALUES (1697, '2019-10-23 14:05:31.168533', NULL, 'Inhapim', 11);
INSERT INTO endereco.cidade VALUES (1699, '2019-10-23 14:05:31.168533', NULL, 'Inimutaba', 11);
INSERT INTO endereco.cidade VALUES (1702, '2019-10-23 14:05:31.168533', NULL, 'Ipatinga', 11);
INSERT INTO endereco.cidade VALUES (1704, '2019-10-23 14:05:31.168533', NULL, 'Ipuiúna', 11);
INSERT INTO endereco.cidade VALUES (1706, '2019-10-23 14:05:31.168533', NULL, 'Itabira', 11);
INSERT INTO endereco.cidade VALUES (1709, '2019-10-23 14:05:31.168533', NULL, 'Itacambira', 11);
INSERT INTO endereco.cidade VALUES (1711, '2019-10-23 14:05:31.168533', NULL, 'Itaguara', 11);
INSERT INTO endereco.cidade VALUES (1713, '2019-10-23 14:05:31.168533', NULL, 'ItajubÁ', 11);
INSERT INTO endereco.cidade VALUES (1715, '2019-10-23 14:05:31.168533', NULL, 'Itamarati De Minas', 11);
INSERT INTO endereco.cidade VALUES (1717, '2019-10-23 14:05:31.168533', NULL, 'Itambé Do Mato Dentro', 11);
INSERT INTO endereco.cidade VALUES (1720, '2019-10-23 14:05:31.168533', NULL, 'Itanhandu', 11);
INSERT INTO endereco.cidade VALUES (1723, '2019-10-23 14:05:31.168533', NULL, 'Itapagipe', 11);
INSERT INTO endereco.cidade VALUES (1725, '2019-10-23 14:05:31.168533', NULL, 'Itapeva', 11);
INSERT INTO endereco.cidade VALUES (1727, '2019-10-23 14:05:31.168533', NULL, 'Itaú De Minas', 11);
INSERT INTO endereco.cidade VALUES (1730, '2019-10-23 14:05:31.168533', NULL, 'Itinga', 11);
INSERT INTO endereco.cidade VALUES (1732, '2019-10-23 14:05:31.168533', NULL, 'Ituiutaba', 11);
INSERT INTO endereco.cidade VALUES (1734, '2019-10-23 14:05:31.168533', NULL, 'Iturama', 11);
INSERT INTO endereco.cidade VALUES (1736, '2019-10-23 14:05:31.168533', NULL, 'Jaboticatubas', 11);
INSERT INTO endereco.cidade VALUES (1739, '2019-10-23 14:05:31.168533', NULL, 'Jacutinga', 11);
INSERT INTO endereco.cidade VALUES (1741, '2019-10-23 14:05:31.168533', NULL, 'Jaíba', 11);
INSERT INTO endereco.cidade VALUES (1744, '2019-10-23 14:05:31.168533', NULL, 'JanuÁria', 11);
INSERT INTO endereco.cidade VALUES (1746, '2019-10-23 14:05:31.168533', NULL, 'Japonvar', 11);
INSERT INTO endereco.cidade VALUES (1748, '2019-10-23 14:05:31.168533', NULL, 'Jenipapo De Minas', 11);
INSERT INTO endereco.cidade VALUES (1751, '2019-10-23 14:05:31.168533', NULL, 'JequitibÁ', 11);
INSERT INTO endereco.cidade VALUES (1752, '2019-10-23 14:05:31.168533', NULL, 'Jequitinhonha', 11);
INSERT INTO endereco.cidade VALUES (1755, '2019-10-23 14:05:31.168533', NULL, 'Joanésia', 11);
INSERT INTO endereco.cidade VALUES (1757, '2019-10-23 14:05:31.168533', NULL, 'João Pinheiro', 11);
INSERT INTO endereco.cidade VALUES (1760, '2019-10-23 14:05:31.168533', NULL, 'José Gonçalves De Minas', 11);
INSERT INTO endereco.cidade VALUES (1762, '2019-10-23 14:05:31.168533', NULL, 'Josenópolis', 11);
INSERT INTO endereco.cidade VALUES (1765, '2019-10-23 14:05:31.168533', NULL, 'Juramento', 11);
INSERT INTO endereco.cidade VALUES (1767, '2019-10-23 14:05:31.168533', NULL, 'Juvenília', 11);
INSERT INTO endereco.cidade VALUES (1769, '2019-10-23 14:05:31.168533', NULL, 'Lagamar', 11);
INSERT INTO endereco.cidade VALUES (1772, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Dourada', 11);
INSERT INTO endereco.cidade VALUES (1774, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Grande', 11);
INSERT INTO endereco.cidade VALUES (1777, '2019-10-23 14:05:31.168533', NULL, 'Lambari', 11);
INSERT INTO endereco.cidade VALUES (1778, '2019-10-23 14:05:31.168533', NULL, 'Lamim', 11);
INSERT INTO endereco.cidade VALUES (1780, '2019-10-23 14:05:31.168533', NULL, 'Lassance', 11);
INSERT INTO endereco.cidade VALUES (1782, '2019-10-23 14:05:31.168533', NULL, 'Leandro Ferreira', 11);
INSERT INTO endereco.cidade VALUES (1784, '2019-10-23 14:05:31.168533', NULL, 'Leopoldina', 11);
INSERT INTO endereco.cidade VALUES (1787, '2019-10-23 14:05:31.168533', NULL, 'Limeira Do Oeste', 11);
INSERT INTO endereco.cidade VALUES (1789, '2019-10-23 14:05:31.168533', NULL, 'Luisburgo', 11);
INSERT INTO endereco.cidade VALUES (1791, '2019-10-23 14:05:31.168533', NULL, 'LuminÁrias', 11);
INSERT INTO endereco.cidade VALUES (1794, '2019-10-23 14:05:31.168533', NULL, 'Machado', 11);
INSERT INTO endereco.cidade VALUES (1796, '2019-10-23 14:05:31.168533', NULL, 'Malacacheta', 11);
INSERT INTO endereco.cidade VALUES (1798, '2019-10-23 14:05:31.168533', NULL, 'Manga', 11);
INSERT INTO endereco.cidade VALUES (1801, '2019-10-23 14:05:31.168533', NULL, 'Mantena', 11);
INSERT INTO endereco.cidade VALUES (1803, '2019-10-23 14:05:31.168533', NULL, 'Maravilhas', 11);
INSERT INTO endereco.cidade VALUES (1805, '2019-10-23 14:05:31.168533', NULL, 'Mariana', 11);
INSERT INTO endereco.cidade VALUES (1807, '2019-10-23 14:05:31.168533', NULL, 'MÁrio Campos', 11);
INSERT INTO endereco.cidade VALUES (1810, '2019-10-23 14:05:31.168533', NULL, 'Marmelópolis', 11);
INSERT INTO endereco.cidade VALUES (1812, '2019-10-23 14:05:31.168533', NULL, 'Martins Soares', 11);
INSERT INTO endereco.cidade VALUES (1814, '2019-10-23 14:05:31.168533', NULL, 'Materlândia', 11);
INSERT INTO endereco.cidade VALUES (1817, '2019-10-23 14:05:31.168533', NULL, 'Matias Barbosa', 11);
INSERT INTO endereco.cidade VALUES (1819, '2019-10-23 14:05:31.168533', NULL, 'Matipó', 11);
INSERT INTO endereco.cidade VALUES (1822, '2019-10-23 14:05:31.168533', NULL, 'Matutina', 11);
INSERT INTO endereco.cidade VALUES (1824, '2019-10-23 14:05:31.168533', NULL, 'Medina', 11);
INSERT INTO endereco.cidade VALUES (1826, '2019-10-23 14:05:31.168533', NULL, 'Mercês', 11);
INSERT INTO endereco.cidade VALUES (1829, '2019-10-23 14:05:31.168533', NULL, 'Minduri', 11);
INSERT INTO endereco.cidade VALUES (1831, '2019-10-23 14:05:31.168533', NULL, 'Miradouro', 11);
INSERT INTO endereco.cidade VALUES (1833, '2019-10-23 14:05:31.168533', NULL, 'Miravânia', 11);
INSERT INTO endereco.cidade VALUES (1835, '2019-10-23 14:05:31.168533', NULL, 'Moema', 11);
INSERT INTO endereco.cidade VALUES (1837, '2019-10-23 14:05:31.168533', NULL, 'Monsenhor Paulo', 11);
INSERT INTO endereco.cidade VALUES (1840, '2019-10-23 14:05:31.168533', NULL, 'Monte Azul', 11);
INSERT INTO endereco.cidade VALUES (1842, '2019-10-23 14:05:31.168533', NULL, 'Monte Carmelo', 11);
INSERT INTO endereco.cidade VALUES (1844, '2019-10-23 14:05:31.168533', NULL, 'Monte Santo De Minas', 11);
INSERT INTO endereco.cidade VALUES (1847, '2019-10-23 14:05:31.168533', NULL, 'Montezuma', 11);
INSERT INTO endereco.cidade VALUES (1849, '2019-10-23 14:05:31.168533', NULL, 'Morro Da Garça', 11);
INSERT INTO endereco.cidade VALUES (1852, '2019-10-23 14:05:31.168533', NULL, 'Muriaé', 11);
INSERT INTO endereco.cidade VALUES (1854, '2019-10-23 14:05:31.168533', NULL, 'Muzambinho', 11);
INSERT INTO endereco.cidade VALUES (1857, '2019-10-23 14:05:31.168533', NULL, 'Naque', 11);
INSERT INTO endereco.cidade VALUES (1859, '2019-10-23 14:05:31.168533', NULL, 'Natércia', 11);
INSERT INTO endereco.cidade VALUES (1861, '2019-10-23 14:05:31.168533', NULL, 'Nepomuceno', 11);
INSERT INTO endereco.cidade VALUES (1863, '2019-10-23 14:05:31.168533', NULL, 'Nova Belém', 11);
INSERT INTO endereco.cidade VALUES (1866, '2019-10-23 14:05:31.168533', NULL, 'Nova Módica', 11);
INSERT INTO endereco.cidade VALUES (1868, '2019-10-23 14:05:31.168533', NULL, 'Nova Porteirinha', 11);
INSERT INTO endereco.cidade VALUES (1870, '2019-10-23 14:05:31.168533', NULL, 'Nova Serrana', 11);
INSERT INTO endereco.cidade VALUES (1873, '2019-10-23 14:05:31.168533', NULL, 'Novo Oriente De Minas', 11);
INSERT INTO endereco.cidade VALUES (1875, '2019-10-23 14:05:31.168533', NULL, 'Olaria', 11);
INSERT INTO endereco.cidade VALUES (1877, '2019-10-23 14:05:31.168533', NULL, 'Olímpio Noronha', 11);
INSERT INTO endereco.cidade VALUES (1880, '2019-10-23 14:05:31.168533', NULL, 'Onça De Pitangui', 11);
INSERT INTO endereco.cidade VALUES (1882, '2019-10-23 14:05:31.168533', NULL, 'Orizânia', 11);
INSERT INTO endereco.cidade VALUES (1885, '2019-10-23 14:05:31.168533', NULL, 'Ouro Preto', 11);
INSERT INTO endereco.cidade VALUES (1887, '2019-10-23 14:05:31.168533', NULL, 'Padre Carvalho', 11);
INSERT INTO endereco.cidade VALUES (1889, '2019-10-23 14:05:31.168533', NULL, 'Pai Pedro', 11);
INSERT INTO endereco.cidade VALUES (1892, '2019-10-23 14:05:31.168533', NULL, 'Paiva', 11);
INSERT INTO endereco.cidade VALUES (1894, '2019-10-23 14:05:31.168533', NULL, 'Palmópolis', 11);
INSERT INTO endereco.cidade VALUES (1896, '2019-10-23 14:05:31.168533', NULL, 'ParÁ De Minas', 11);
INSERT INTO endereco.cidade VALUES (1899, '2019-10-23 14:05:31.168533', NULL, 'Paraisópolis', 11);
INSERT INTO endereco.cidade VALUES (1901, '2019-10-23 14:05:31.168533', NULL, 'Passa Quatro', 11);
INSERT INTO endereco.cidade VALUES (1903, '2019-10-23 14:05:31.168533', NULL, 'Passa Vinte', 11);
INSERT INTO endereco.cidade VALUES (1906, '2019-10-23 14:05:31.168533', NULL, 'Patis', 11);
INSERT INTO endereco.cidade VALUES (1907, '2019-10-23 14:05:31.168533', NULL, 'Patos De Minas', 11);
INSERT INTO endereco.cidade VALUES (1909, '2019-10-23 14:05:31.168533', NULL, 'Patrocínio Do Muriaé', 11);
INSERT INTO endereco.cidade VALUES (1911, '2019-10-23 14:05:31.168533', NULL, 'Paulistas', 11);
INSERT INTO endereco.cidade VALUES (1914, '2019-10-23 14:05:31.168533', NULL, 'Pedra Azul', 11);
INSERT INTO endereco.cidade VALUES (1916, '2019-10-23 14:05:31.168533', NULL, 'Pedra Do Anta', 11);
INSERT INTO endereco.cidade VALUES (1918, '2019-10-23 14:05:31.168533', NULL, 'Pedra Dourada', 11);
INSERT INTO endereco.cidade VALUES (1921, '2019-10-23 14:05:31.168533', NULL, 'Pedrinópolis', 11);
INSERT INTO endereco.cidade VALUES (1923, '2019-10-23 14:05:31.168533', NULL, 'Pedro Teixeira', 11);
INSERT INTO endereco.cidade VALUES (1926, '2019-10-23 14:05:31.168533', NULL, 'Perdigão', 11);
INSERT INTO endereco.cidade VALUES (1928, '2019-10-23 14:05:31.168533', NULL, 'Perdões', 11);
INSERT INTO endereco.cidade VALUES (1930, '2019-10-23 14:05:31.168533', NULL, 'Pescador', 11);
INSERT INTO endereco.cidade VALUES (1932, '2019-10-23 14:05:31.168533', NULL, 'Piedade De Caratinga', 11);
INSERT INTO endereco.cidade VALUES (1935, '2019-10-23 14:05:31.168533', NULL, 'Piedade Dos Gerais', 11);
INSERT INTO endereco.cidade VALUES (1937, '2019-10-23 14:05:31.168533', NULL, 'Pingo D`Água', 11);
INSERT INTO endereco.cidade VALUES (1940, '2019-10-23 14:05:31.168533', NULL, 'Pirajuba', 11);
INSERT INTO endereco.cidade VALUES (1942, '2019-10-23 14:05:31.168533', NULL, 'Piranguçu', 11);
INSERT INTO endereco.cidade VALUES (1945, '2019-10-23 14:05:31.168533', NULL, 'Pirapora', 11);
INSERT INTO endereco.cidade VALUES (1947, '2019-10-23 14:05:31.168533', NULL, 'Pitangui', 11);
INSERT INTO endereco.cidade VALUES (1949, '2019-10-23 14:05:31.168533', NULL, 'Planura', 11);
INSERT INTO endereco.cidade VALUES (1951, '2019-10-23 14:05:31.168533', NULL, 'Poços De Caldas', 11);
INSERT INTO endereco.cidade VALUES (1954, '2019-10-23 14:05:31.168533', NULL, 'Ponte Nova', 11);
INSERT INTO endereco.cidade VALUES (1956, '2019-10-23 14:05:31.168533', NULL, 'Ponto Dos Volantes', 11);
INSERT INTO endereco.cidade VALUES (1959, '2019-10-23 14:05:31.168533', NULL, 'Poté', 11);
INSERT INTO endereco.cidade VALUES (1961, '2019-10-23 14:05:31.168533', NULL, 'Pouso Alto', 11);
INSERT INTO endereco.cidade VALUES (1963, '2019-10-23 14:05:31.168533', NULL, 'Prata', 11);
INSERT INTO endereco.cidade VALUES (1965, '2019-10-23 14:05:31.168533', NULL, 'Pratinha', 11);
INSERT INTO endereco.cidade VALUES (1967, '2019-10-23 14:05:31.168533', NULL, 'Presidente Juscelino', 11);
INSERT INTO endereco.cidade VALUES (1970, '2019-10-23 14:05:31.168533', NULL, 'Prudente De Morais', 11);
INSERT INTO endereco.cidade VALUES (1973, '2019-10-23 14:05:31.168533', NULL, 'Raposos', 11);
INSERT INTO endereco.cidade VALUES (1975, '2019-10-23 14:05:31.168533', NULL, 'Recreio', 11);
INSERT INTO endereco.cidade VALUES (1977, '2019-10-23 14:05:31.168533', NULL, 'Resende Costa', 11);
INSERT INTO endereco.cidade VALUES (1980, '2019-10-23 14:05:31.168533', NULL, 'Riachinho', 11);
INSERT INTO endereco.cidade VALUES (1982, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Das Neves', 11);
INSERT INTO endereco.cidade VALUES (1984, '2019-10-23 14:05:31.168533', NULL, 'Rio Acima', 11);
INSERT INTO endereco.cidade VALUES (1987, '2019-10-23 14:05:31.168533', NULL, 'Rio Doce', 11);
INSERT INTO endereco.cidade VALUES (1989, '2019-10-23 14:05:31.168533', NULL, 'Rio Manso', 11);
INSERT INTO endereco.cidade VALUES (1991, '2019-10-23 14:05:31.168533', NULL, 'Rio Paranaíba', 11);
INSERT INTO endereco.cidade VALUES (1994, '2019-10-23 14:05:31.168533', NULL, 'Rio Pomba', 11);
INSERT INTO endereco.cidade VALUES (1996, '2019-10-23 14:05:31.168533', NULL, 'Rio Vermelho', 11);
INSERT INTO endereco.cidade VALUES (1998, '2019-10-23 14:05:31.168533', NULL, 'Rochedo De Minas', 11);
INSERT INTO endereco.cidade VALUES (2001, '2019-10-23 14:05:31.168533', NULL, 'RosÁrio Da Limeira', 11);
INSERT INTO endereco.cidade VALUES (2003, '2019-10-23 14:05:31.168533', NULL, 'Rubim', 11);
INSERT INTO endereco.cidade VALUES (2005, '2019-10-23 14:05:31.168533', NULL, 'Sabinópolis', 11);
INSERT INTO endereco.cidade VALUES (2008, '2019-10-23 14:05:31.168533', NULL, 'Salto Da Divisa', 11);
INSERT INTO endereco.cidade VALUES (2010, '2019-10-23 14:05:31.168533', NULL, 'Santa BÁrbara Do Leste', 11);
INSERT INTO endereco.cidade VALUES (2013, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz De Minas', 11);
INSERT INTO endereco.cidade VALUES (2015, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Do Escalvado', 11);
INSERT INTO endereco.cidade VALUES (2018, '2019-10-23 14:05:31.168533', NULL, 'Santa Helena De Minas', 11);
INSERT INTO endereco.cidade VALUES (2021, '2019-10-23 14:05:31.168533', NULL, 'Santa Margarida', 11);
INSERT INTO endereco.cidade VALUES (2023, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Do Salto', 11);
INSERT INTO endereco.cidade VALUES (2025, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita De Caldas', 11);
INSERT INTO endereco.cidade VALUES (2028, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita De Minas', 11);
INSERT INTO endereco.cidade VALUES (2030, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita Do Sapucaí', 11);
INSERT INTO endereco.cidade VALUES (2031, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa Da Serra', 11);
INSERT INTO endereco.cidade VALUES (2032, '2019-10-23 14:05:31.168533', NULL, 'Santa Vitória', 11);
INSERT INTO endereco.cidade VALUES (2034, '2019-10-23 14:05:31.168533', NULL, 'Santana De Cataguases', 11);
INSERT INTO endereco.cidade VALUES (2037, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Garambéu', 11);
INSERT INTO endereco.cidade VALUES (2039, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Manhuaçu', 11);
INSERT INTO endereco.cidade VALUES (2042, '2019-10-23 14:05:31.168533', NULL, 'Santana Dos Montes', 11);
INSERT INTO endereco.cidade VALUES (2044, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Aventureiro', 11);
INSERT INTO endereco.cidade VALUES (2047, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Jacinto', 11);
INSERT INTO endereco.cidade VALUES (2049, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Retiro', 11);
INSERT INTO endereco.cidade VALUES (2052, '2019-10-23 14:05:31.168533', NULL, 'Santos Dumont', 11);
INSERT INTO endereco.cidade VALUES (2054, '2019-10-23 14:05:31.168533', NULL, 'São BrÁs Do Suaçuí', 11);
INSERT INTO endereco.cidade VALUES (2057, '2019-10-23 14:05:31.168533', NULL, 'São Félix De Minas', 11);
INSERT INTO endereco.cidade VALUES (2059, '2019-10-23 14:05:31.168533', NULL, 'São Francisco De Paula', 11);
INSERT INTO endereco.cidade VALUES (2061, '2019-10-23 14:05:31.168533', NULL, 'São Francisco Do Glória', 11);
INSERT INTO endereco.cidade VALUES (2064, '2019-10-23 14:05:31.168533', NULL, 'São Geraldo Do Baixio', 11);
INSERT INTO endereco.cidade VALUES (2067, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Do Rio Abaixo', 11);
INSERT INTO endereco.cidade VALUES (2069, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Do Sapucaí', 11);
INSERT INTO endereco.cidade VALUES (2072, '2019-10-23 14:05:31.168533', NULL, 'São João Da Lagoa', 11);
INSERT INTO endereco.cidade VALUES (2074, '2019-10-23 14:05:31.168533', NULL, 'São João Da Ponte', 11);
INSERT INTO endereco.cidade VALUES (2077, '2019-10-23 14:05:31.168533', NULL, 'São João Do Manhuaçu', 11);
INSERT INTO endereco.cidade VALUES (2079, '2019-10-23 14:05:31.168533', NULL, 'São João Do Oriente', 11);
INSERT INTO endereco.cidade VALUES (2082, '2019-10-23 14:05:31.168533', NULL, 'São João Evangelista', 11);
INSERT INTO endereco.cidade VALUES (2084, '2019-10-23 14:05:31.168533', NULL, 'São Joaquim De Bicas', 11);
INSERT INTO endereco.cidade VALUES (2087, '2019-10-23 14:05:31.168533', NULL, 'São José Da Safira', 11);
INSERT INTO endereco.cidade VALUES (2089, '2019-10-23 14:05:31.168533', NULL, 'São José Do Alegre', 11);
INSERT INTO endereco.cidade VALUES (2092, '2019-10-23 14:05:31.168533', NULL, 'São José Do Jacuri', 11);
INSERT INTO endereco.cidade VALUES (2094, '2019-10-23 14:05:31.168533', NULL, 'São Lourenço', 11);
INSERT INTO endereco.cidade VALUES (2097, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Do Suaçuí', 11);
INSERT INTO endereco.cidade VALUES (2100, '2019-10-23 14:05:31.168533', NULL, 'São Roque De Minas', 11);
INSERT INTO endereco.cidade VALUES (2102, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Da Vargem Alegre', 11);
INSERT INTO endereco.cidade VALUES (2105, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Oeste', 11);
INSERT INTO endereco.cidade VALUES (2107, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Rio Preto', 11);
INSERT INTO endereco.cidade VALUES (2111, '2019-10-23 14:05:31.168533', NULL, 'São TomÁs De Aquino', 11);
INSERT INTO endereco.cidade VALUES (2113, '2019-10-23 14:05:31.168533', NULL, 'Sapucaí Mirim', 11);
INSERT INTO endereco.cidade VALUES (2116, '2019-10-23 14:05:31.168533', NULL, 'Sem Peixe', 11);
INSERT INTO endereco.cidade VALUES (2118, '2019-10-23 14:05:31.168533', NULL, 'Senador Cortes', 11);
INSERT INTO endereco.cidade VALUES (2120, '2019-10-23 14:05:31.168533', NULL, 'Senador José Bento', 11);
INSERT INTO endereco.cidade VALUES (2123, '2019-10-23 14:05:31.168533', NULL, 'Senhora Do Porto', 11);
INSERT INTO endereco.cidade VALUES (2125, '2019-10-23 14:05:31.168533', NULL, 'Sericita', 11);
INSERT INTO endereco.cidade VALUES (2128, '2019-10-23 14:05:31.168533', NULL, 'Serra Da Saudade', 11);
INSERT INTO endereco.cidade VALUES (2130, '2019-10-23 14:05:31.168533', NULL, 'Serra Dos Aimorés', 11);
INSERT INTO endereco.cidade VALUES (2132, '2019-10-23 14:05:31.168533', NULL, 'Serranópolis De Minas', 11);
INSERT INTO endereco.cidade VALUES (2135, '2019-10-23 14:05:31.168533', NULL, 'Sete Lagoas', 11);
INSERT INTO endereco.cidade VALUES (2138, '2019-10-23 14:05:31.168533', NULL, 'Silvianópolis', 11);
INSERT INTO endereco.cidade VALUES (2140, '2019-10-23 14:05:31.168533', NULL, 'Simonésia', 11);
INSERT INTO endereco.cidade VALUES (2142, '2019-10-23 14:05:31.168533', NULL, 'Soledade De Minas', 11);
INSERT INTO endereco.cidade VALUES (2145, '2019-10-23 14:05:31.168533', NULL, 'Taparuba', 11);
INSERT INTO endereco.cidade VALUES (2146, '2019-10-23 14:05:31.168533', NULL, 'Tapira', 11);
INSERT INTO endereco.cidade VALUES (2148, '2019-10-23 14:05:31.168533', NULL, 'Taquaraçu De Minas', 11);
INSERT INTO endereco.cidade VALUES (2151, '2019-10-23 14:05:31.168533', NULL, 'Teófilo Otoni', 11);
INSERT INTO endereco.cidade VALUES (2153, '2019-10-23 14:05:31.168533', NULL, 'Tiradentes', 11);
INSERT INTO endereco.cidade VALUES (2156, '2019-10-23 14:05:31.168533', NULL, 'Tocos Do Moji', 11);
INSERT INTO endereco.cidade VALUES (2158, '2019-10-23 14:05:31.168533', NULL, 'Tombos', 11);
INSERT INTO endereco.cidade VALUES (2160, '2019-10-23 14:05:31.168533', NULL, 'Três Marias', 11);
INSERT INTO endereco.cidade VALUES (2163, '2019-10-23 14:05:31.168533', NULL, 'Tupaciguara', 11);
INSERT INTO endereco.cidade VALUES (2165, '2019-10-23 14:05:31.168533', NULL, 'Turvolândia', 11);
INSERT INTO endereco.cidade VALUES (2168, '2019-10-23 14:05:31.168533', NULL, 'Ubaporanga', 11);
INSERT INTO endereco.cidade VALUES (2170, '2019-10-23 14:05:31.168533', NULL, 'Uberlândia', 11);
INSERT INTO endereco.cidade VALUES (2172, '2019-10-23 14:05:31.168533', NULL, 'Unaí', 11);
INSERT INTO endereco.cidade VALUES (2175, '2019-10-23 14:05:31.168533', NULL, 'Urucânia', 11);
INSERT INTO endereco.cidade VALUES (2177, '2019-10-23 14:05:31.168533', NULL, 'Vargem Alegre', 11);
INSERT INTO endereco.cidade VALUES (2179, '2019-10-23 14:05:31.168533', NULL, 'Vargem Grande Do Rio Pardo', 11);
INSERT INTO endereco.cidade VALUES (2182, '2019-10-23 14:05:31.168533', NULL, 'VÁrzea Da Palma', 11);
INSERT INTO endereco.cidade VALUES (2185, '2019-10-23 14:05:31.168533', NULL, 'Verdelândia', 11);
INSERT INTO endereco.cidade VALUES (2187, '2019-10-23 14:05:31.168533', NULL, 'Veríssimo', 11);
INSERT INTO endereco.cidade VALUES (2189, '2019-10-23 14:05:31.168533', NULL, 'Vespasiano', 11);
INSERT INTO endereco.cidade VALUES (2192, '2019-10-23 14:05:31.168533', NULL, 'Virgem Da Lapa', 11);
INSERT INTO endereco.cidade VALUES (2194, '2019-10-23 14:05:31.168533', NULL, 'Virginópolis', 11);
INSERT INTO endereco.cidade VALUES (2196, '2019-10-23 14:05:31.168533', NULL, 'Visconde Do Rio Branco', 11);
INSERT INTO endereco.cidade VALUES (2199, '2019-10-23 14:05:31.168533', NULL, 'Água Clara', 12);
INSERT INTO endereco.cidade VALUES (2201, '2019-10-23 14:05:31.168533', NULL, 'Amambai', 12);
INSERT INTO endereco.cidade VALUES (2204, '2019-10-23 14:05:31.168533', NULL, 'Angélica', 12);
INSERT INTO endereco.cidade VALUES (2206, '2019-10-23 14:05:31.168533', NULL, 'Aparecida Do Taboado', 12);
INSERT INTO endereco.cidade VALUES (2208, '2019-10-23 14:05:31.168533', NULL, 'Aral Moreira', 12);
INSERT INTO endereco.cidade VALUES (2211, '2019-10-23 14:05:31.168533', NULL, 'Batayporã', 12);
INSERT INTO endereco.cidade VALUES (2213, '2019-10-23 14:05:31.168533', NULL, 'Bodoquena', 12);
INSERT INTO endereco.cidade VALUES (2215, '2019-10-23 14:05:31.168533', NULL, 'Brasilândia', 12);
INSERT INTO endereco.cidade VALUES (2218, '2019-10-23 14:05:31.168533', NULL, 'Campo Grande', 12);
INSERT INTO endereco.cidade VALUES (2220, '2019-10-23 14:05:31.168533', NULL, 'Cassilândia', 12);
INSERT INTO endereco.cidade VALUES (2223, '2019-10-23 14:05:31.168533', NULL, 'Coronel Sapucaia', 12);
INSERT INTO endereco.cidade VALUES (2225, '2019-10-23 14:05:31.168533', NULL, 'Costa Rica', 12);
INSERT INTO endereco.cidade VALUES (2228, '2019-10-23 14:05:31.168533', NULL, 'Dois Irmãos Do Buriti', 12);
INSERT INTO endereco.cidade VALUES (2230, '2019-10-23 14:05:31.168533', NULL, 'Dourados', 12);
INSERT INTO endereco.cidade VALUES (2232, '2019-10-23 14:05:31.168533', NULL, 'Fátima Do Sul', 12);
INSERT INTO endereco.cidade VALUES (2235, '2019-10-23 14:05:31.168533', NULL, 'Guia Lopes Da Laguna', 12);
INSERT INTO endereco.cidade VALUES (2238, '2019-10-23 14:05:31.168533', NULL, 'Itaporã', 12);
INSERT INTO endereco.cidade VALUES (2240, '2019-10-23 14:05:31.168533', NULL, 'Ivinhema', 12);
INSERT INTO endereco.cidade VALUES (2242, '2019-10-23 14:05:31.168533', NULL, 'Jaraguari', 12);
INSERT INTO endereco.cidade VALUES (2244, '2019-10-23 14:05:31.168533', NULL, 'Jateí', 12);
INSERT INTO endereco.cidade VALUES (2247, '2019-10-23 14:05:31.168533', NULL, 'Laguna Carapã', 12);
INSERT INTO endereco.cidade VALUES (2249, '2019-10-23 14:05:31.168533', NULL, 'Miranda', 12);
INSERT INTO endereco.cidade VALUES (2251, '2019-10-23 14:05:31.168533', NULL, 'Naviraí', 12);
INSERT INTO endereco.cidade VALUES (2253, '2019-10-23 14:05:31.168533', NULL, 'Nova Alvorada Do Sul', 12);
INSERT INTO endereco.cidade VALUES (2256, '2019-10-23 14:05:31.168533', NULL, 'Paranaíba', 12);
INSERT INTO endereco.cidade VALUES (2258, '2019-10-23 14:05:31.168533', NULL, 'Pedro Gomes', 12);
INSERT INTO endereco.cidade VALUES (2260, '2019-10-23 14:05:31.168533', NULL, 'Porto Murtinho', 12);
INSERT INTO endereco.cidade VALUES (2263, '2019-10-23 14:05:31.168533', NULL, 'Rio Negro', 12);
INSERT INTO endereco.cidade VALUES (2265, '2019-10-23 14:05:31.168533', NULL, 'Rochedo', 12);
INSERT INTO endereco.cidade VALUES (2267, '2019-10-23 14:05:31.168533', NULL, 'São Gabriel Do Oeste', 12);
INSERT INTO endereco.cidade VALUES (2270, '2019-10-23 14:05:31.168533', NULL, 'Sidrolândia', 12);
INSERT INTO endereco.cidade VALUES (2272, '2019-10-23 14:05:31.168533', NULL, 'Tacuru', 12);
INSERT INTO endereco.cidade VALUES (2273, '2019-10-23 14:05:31.168533', NULL, 'Taquarussu', 12);
INSERT INTO endereco.cidade VALUES (2275, '2019-10-23 14:05:31.168533', NULL, 'Três Lagoas', 12);
INSERT INTO endereco.cidade VALUES (2277, '2019-10-23 14:05:31.168533', NULL, 'Acorizal', 13);
INSERT INTO endereco.cidade VALUES (2279, '2019-10-23 14:05:31.168533', NULL, 'Alta Floresta', 13);
INSERT INTO endereco.cidade VALUES (2282, '2019-10-23 14:05:31.168533', NULL, 'Alto Garças', 13);
INSERT INTO endereco.cidade VALUES (2284, '2019-10-23 14:05:31.168533', NULL, 'Alto Taquari', 13);
INSERT INTO endereco.cidade VALUES (2287, '2019-10-23 14:05:31.168533', NULL, 'Araguainha', 13);
INSERT INTO endereco.cidade VALUES (2289, '2019-10-23 14:05:31.168533', NULL, 'Arenápolis', 13);
INSERT INTO endereco.cidade VALUES (2291, '2019-10-23 14:05:31.168533', NULL, 'Barão De Melgaço', 13);
INSERT INTO endereco.cidade VALUES (2293, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Garças', 13);
INSERT INTO endereco.cidade VALUES (2296, '2019-10-23 14:05:31.168533', NULL, 'Cáceres', 13);
INSERT INTO endereco.cidade VALUES (2298, '2019-10-23 14:05:31.168533', NULL, 'Campo Novo Do Parecis', 13);
INSERT INTO endereco.cidade VALUES (2301, '2019-10-23 14:05:31.168533', NULL, 'Canabrava Do Norte', 13);
INSERT INTO endereco.cidade VALUES (2303, '2019-10-23 14:05:31.168533', NULL, 'Carlinda', 13);
INSERT INTO endereco.cidade VALUES (2305, '2019-10-23 14:05:31.168533', NULL, 'Chapada Dos Guimarães', 13);
INSERT INTO endereco.cidade VALUES (2308, '2019-10-23 14:05:31.168533', NULL, 'Colíder', 13);
INSERT INTO endereco.cidade VALUES (2311, '2019-10-23 14:05:31.168533', NULL, 'Confresa', 13);
INSERT INTO endereco.cidade VALUES (2312, '2019-10-23 14:05:31.168533', NULL, 'Conquista D`Oeste', 13);
INSERT INTO endereco.cidade VALUES (2315, '2019-10-23 14:05:31.168533', NULL, 'Curvelândia', 13);
INSERT INTO endereco.cidade VALUES (2317, '2019-10-23 14:05:31.168533', NULL, 'Diamantino', 13);
INSERT INTO endereco.cidade VALUES (2319, '2019-10-23 14:05:31.168533', NULL, 'Feliz Natal', 13);
INSERT INTO endereco.cidade VALUES (2322, '2019-10-23 14:05:31.168533', NULL, 'General Carneiro', 13);
INSERT INTO endereco.cidade VALUES (2324, '2019-10-23 14:05:31.168533', NULL, 'Guarantã Do Norte', 13);
INSERT INTO endereco.cidade VALUES (2326, '2019-10-23 14:05:31.168533', NULL, 'Indiavaí', 13);
INSERT INTO endereco.cidade VALUES (2329, '2019-10-23 14:05:31.168533', NULL, 'Itaúba', 13);
INSERT INTO endereco.cidade VALUES (2331, '2019-10-23 14:05:31.168533', NULL, 'Jaciara', 13);
INSERT INTO endereco.cidade VALUES (2333, '2019-10-23 14:05:31.168533', NULL, 'Jauru', 13);
INSERT INTO endereco.cidade VALUES (2336, '2019-10-23 14:05:31.168533', NULL, 'Juruena', 13);
INSERT INTO endereco.cidade VALUES (2338, '2019-10-23 14:05:31.168533', NULL, 'Lambari D`Oeste', 13);
INSERT INTO endereco.cidade VALUES (2340, '2019-10-23 14:05:31.168533', NULL, 'Luciára', 13);
INSERT INTO endereco.cidade VALUES (2343, '2019-10-23 14:05:31.168533', NULL, 'Mirassol D`Oeste', 13);
INSERT INTO endereco.cidade VALUES (2345, '2019-10-23 14:05:31.168533', NULL, 'Nortelândia', 13);
INSERT INTO endereco.cidade VALUES (2347, '2019-10-23 14:05:31.168533', NULL, 'Nova Bandeirantes', 13);
INSERT INTO endereco.cidade VALUES (2350, '2019-10-23 14:05:31.168533', NULL, 'Nova Guarita', 13);
INSERT INTO endereco.cidade VALUES (2352, '2019-10-23 14:05:31.168533', NULL, 'Nova Marilândia', 13);
INSERT INTO endereco.cidade VALUES (2354, '2019-10-23 14:05:31.168533', NULL, 'Nova Monte Verde', 13);
INSERT INTO endereco.cidade VALUES (2357, '2019-10-23 14:05:31.168533', NULL, 'Nova Olímpia', 13);
INSERT INTO endereco.cidade VALUES (2359, '2019-10-23 14:05:31.168533', NULL, 'Nova Ubiratã', 13);
INSERT INTO endereco.cidade VALUES (2361, '2019-10-23 14:05:31.168533', NULL, 'Novo Horizonte Do Norte', 13);
INSERT INTO endereco.cidade VALUES (2364, '2019-10-23 14:05:31.168533', NULL, 'Novo São Joaquim', 13);
INSERT INTO endereco.cidade VALUES (2366, '2019-10-23 14:05:31.168533', NULL, 'Paranatinga', 13);
INSERT INTO endereco.cidade VALUES (2368, '2019-10-23 14:05:31.168533', NULL, 'Peixoto De Azevedo', 13);
INSERT INTO endereco.cidade VALUES (2371, '2019-10-23 14:05:31.168533', NULL, 'Pontal Do Araguaia', 13);
INSERT INTO endereco.cidade VALUES (2373, '2019-10-23 14:05:31.168533', NULL, 'Pontes E Lacerda', 13);
INSERT INTO endereco.cidade VALUES (2375, '2019-10-23 14:05:31.168533', NULL, 'Porto Dos Gaúchos', 13);
INSERT INTO endereco.cidade VALUES (2378, '2019-10-23 14:05:31.168533', NULL, 'Poxoréo', 13);
INSERT INTO endereco.cidade VALUES (2380, '2019-10-23 14:05:31.168533', NULL, 'Querência', 13);
INSERT INTO endereco.cidade VALUES (2382, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Cascalheira', 13);
INSERT INTO endereco.cidade VALUES (2385, '2019-10-23 14:05:31.168533', NULL, 'Rondolândia', 13);
INSERT INTO endereco.cidade VALUES (2387, '2019-10-23 14:05:31.168533', NULL, 'Rosário Oeste', 13);
INSERT INTO endereco.cidade VALUES (2390, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Do Xingu', 13);
INSERT INTO endereco.cidade VALUES (2392, '2019-10-23 14:05:31.168533', NULL, 'Santa Terezinha', 13);
INSERT INTO endereco.cidade VALUES (2394, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Leste', 13);
INSERT INTO endereco.cidade VALUES (2396, '2019-10-23 14:05:31.168533', NULL, 'São Félix Do Araguaia', 13);
INSERT INTO endereco.cidade VALUES (2397, '2019-10-23 14:05:31.168533', NULL, 'São José Do Povo', 13);
INSERT INTO endereco.cidade VALUES (2398, '2019-10-23 14:05:31.168533', NULL, 'São José Do Rio Claro', 13);
INSERT INTO endereco.cidade VALUES (2400, '2019-10-23 14:05:31.168533', NULL, 'São José Dos Quatro Marcos', 13);
INSERT INTO endereco.cidade VALUES (2404, '2019-10-23 14:05:31.168533', NULL, 'Sinop', 13);
INSERT INTO endereco.cidade VALUES (2406, '2019-10-23 14:05:31.168533', NULL, 'Tabaporã', 13);
INSERT INTO endereco.cidade VALUES (2408, '2019-10-23 14:05:31.168533', NULL, 'Tapurah', 13);
INSERT INTO endereco.cidade VALUES (2411, '2019-10-23 14:05:31.168533', NULL, 'Torixoréu', 13);
INSERT INTO endereco.cidade VALUES (2413, '2019-10-23 14:05:31.168533', NULL, 'Vale De São Domingos', 13);
INSERT INTO endereco.cidade VALUES (2416, '2019-10-23 14:05:31.168533', NULL, 'Vila Bela Da Santíssima Trindade', 13);
INSERT INTO endereco.cidade VALUES (2418, '2019-10-23 14:05:31.168533', NULL, 'Abaetetuba', 14);
INSERT INTO endereco.cidade VALUES (2420, '2019-10-23 14:05:31.168533', NULL, 'Acará', 14);
INSERT INTO endereco.cidade VALUES (2423, '2019-10-23 14:05:31.168533', NULL, 'Alenquer', 14);
INSERT INTO endereco.cidade VALUES (2425, '2019-10-23 14:05:31.168533', NULL, 'Altamira', 14);
INSERT INTO endereco.cidade VALUES (2427, '2019-10-23 14:05:31.168533', NULL, 'Ananindeua', 14);
INSERT INTO endereco.cidade VALUES (2429, '2019-10-23 14:05:31.168533', NULL, 'Augusto Corrêa', 14);
INSERT INTO endereco.cidade VALUES (2432, '2019-10-23 14:05:31.168533', NULL, 'Bagre', 14);
INSERT INTO endereco.cidade VALUES (2434, '2019-10-23 14:05:31.168533', NULL, 'Bannach', 14);
INSERT INTO endereco.cidade VALUES (2437, '2019-10-23 14:05:31.168533', NULL, 'Belterra', 14);
INSERT INTO endereco.cidade VALUES (2439, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Do Tocantins', 14);
INSERT INTO endereco.cidade VALUES (2441, '2019-10-23 14:05:31.168533', NULL, 'Bragança', 14);
INSERT INTO endereco.cidade VALUES (2443, '2019-10-23 14:05:31.168533', NULL, 'Brejo Grande Do Araguaia', 14);
INSERT INTO endereco.cidade VALUES (2446, '2019-10-23 14:05:31.168533', NULL, 'Bujaru', 14);
INSERT INTO endereco.cidade VALUES (2448, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Do Piriá', 14);
INSERT INTO endereco.cidade VALUES (2451, '2019-10-23 14:05:31.168533', NULL, 'Capanema', 14);
INSERT INTO endereco.cidade VALUES (2453, '2019-10-23 14:05:31.168533', NULL, 'Castanhal', 14);
INSERT INTO endereco.cidade VALUES (2455, '2019-10-23 14:05:31.168533', NULL, 'Colares', 14);
INSERT INTO endereco.cidade VALUES (2458, '2019-10-23 14:05:31.168533', NULL, 'Cumaru Do Norte', 14);
INSERT INTO endereco.cidade VALUES (2460, '2019-10-23 14:05:31.168533', NULL, 'Curralinho', 14);
INSERT INTO endereco.cidade VALUES (2462, '2019-10-23 14:05:31.168533', NULL, 'Curuçá', 14);
INSERT INTO endereco.cidade VALUES (2464, '2019-10-23 14:05:31.168533', NULL, 'Eldorado Dos Carajás', 14);
INSERT INTO endereco.cidade VALUES (2467, '2019-10-23 14:05:31.168533', NULL, 'Garrafão Do Norte', 14);
INSERT INTO endereco.cidade VALUES (2470, '2019-10-23 14:05:31.168533', NULL, 'Igarapé Açu', 14);
INSERT INTO endereco.cidade VALUES (2472, '2019-10-23 14:05:31.168533', NULL, 'Inhangapi', 14);
INSERT INTO endereco.cidade VALUES (2474, '2019-10-23 14:05:31.168533', NULL, 'Irituia', 14);
INSERT INTO endereco.cidade VALUES (2477, '2019-10-23 14:05:31.168533', NULL, 'Jacareacanga', 14);
INSERT INTO endereco.cidade VALUES (2479, '2019-10-23 14:05:31.168533', NULL, 'Juruti', 14);
INSERT INTO endereco.cidade VALUES (2481, '2019-10-23 14:05:31.168533', NULL, 'Mãe Do Rio', 14);
INSERT INTO endereco.cidade VALUES (2483, '2019-10-23 14:05:31.168533', NULL, 'Marabá', 14);
INSERT INTO endereco.cidade VALUES (2486, '2019-10-23 14:05:31.168533', NULL, 'Marituba', 14);
INSERT INTO endereco.cidade VALUES (2488, '2019-10-23 14:05:31.168533', NULL, 'Melgaço', 14);
INSERT INTO endereco.cidade VALUES (2490, '2019-10-23 14:05:31.168533', NULL, 'Moju', 14);
INSERT INTO endereco.cidade VALUES (2493, '2019-10-23 14:05:31.168533', NULL, 'Nova Esperança Do Piriá', 14);
INSERT INTO endereco.cidade VALUES (2495, '2019-10-23 14:05:31.168533', NULL, 'Nova Timboteua', 14);
INSERT INTO endereco.cidade VALUES (2497, '2019-10-23 14:05:31.168533', NULL, 'Novo Repartimento', 14);
INSERT INTO endereco.cidade VALUES (2500, '2019-10-23 14:05:31.168533', NULL, 'Oriximiná', 14);
INSERT INTO endereco.cidade VALUES (2502, '2019-10-23 14:05:31.168533', NULL, 'Ourilândia Do Norte', 14);
INSERT INTO endereco.cidade VALUES (2505, '2019-10-23 14:05:31.168533', NULL, 'Paragominas', 14);
INSERT INTO endereco.cidade VALUES (2507, '2019-10-23 14:05:31.168533', NULL, 'Pau D`Arco', 14);
INSERT INTO endereco.cidade VALUES (2510, '2019-10-23 14:05:31.168533', NULL, 'Placas', 14);
INSERT INTO endereco.cidade VALUES (2512, '2019-10-23 14:05:31.168533', NULL, 'Portel', 14);
INSERT INTO endereco.cidade VALUES (2514, '2019-10-23 14:05:31.168533', NULL, 'Prainha', 14);
INSERT INTO endereco.cidade VALUES (2516, '2019-10-23 14:05:31.168533', NULL, 'Quatipuru', 14);
INSERT INTO endereco.cidade VALUES (2519, '2019-10-23 14:05:31.168533', NULL, 'Rondon Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2521, '2019-10-23 14:05:31.168533', NULL, 'Salinópolis', 14);
INSERT INTO endereco.cidade VALUES (2523, '2019-10-23 14:05:31.168533', NULL, 'Santa Bárbara Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2524, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Do Arari', 14);
INSERT INTO endereco.cidade VALUES (2525, '2019-10-23 14:05:31.168533', NULL, 'Santa Isabel Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2528, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Do Pará', 14);
INSERT INTO endereco.cidade VALUES (2531, '2019-10-23 14:05:31.168533', NULL, 'Santarém Novo', 14);
INSERT INTO endereco.cidade VALUES (2533, '2019-10-23 14:05:31.168533', NULL, 'São Caetano De Odivelas', 14);
INSERT INTO endereco.cidade VALUES (2535, '2019-10-23 14:05:31.168533', NULL, 'São Domingos Do Capim', 14);
INSERT INTO endereco.cidade VALUES (2538, '2019-10-23 14:05:31.168533', NULL, 'São Geraldo Do Araguaia', 14);
INSERT INTO endereco.cidade VALUES (2540, '2019-10-23 14:05:31.168533', NULL, 'São João De Pirabas', 14);
INSERT INTO endereco.cidade VALUES (2543, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Da Boa Vista', 14);
INSERT INTO endereco.cidade VALUES (2546, '2019-10-23 14:05:31.168533', NULL, 'Soure', 14);
INSERT INTO endereco.cidade VALUES (2548, '2019-10-23 14:05:31.168533', NULL, 'Terra Alta', 14);
INSERT INTO endereco.cidade VALUES (2551, '2019-10-23 14:05:31.168533', NULL, 'Tracuateua', 14);
INSERT INTO endereco.cidade VALUES (2553, '2019-10-23 14:05:31.168533', NULL, 'Tucumã', 14);
INSERT INTO endereco.cidade VALUES (2555, '2019-10-23 14:05:31.168533', NULL, 'Ulianópolis', 14);
INSERT INTO endereco.cidade VALUES (2558, '2019-10-23 14:05:31.168533', NULL, 'Viseu', 14);
INSERT INTO endereco.cidade VALUES (2560, '2019-10-23 14:05:31.168533', NULL, 'Xinguara', 14);
INSERT INTO endereco.cidade VALUES (2562, '2019-10-23 14:05:31.168533', NULL, 'Aguiar', 15);
INSERT INTO endereco.cidade VALUES (2564, '2019-10-23 14:05:31.168533', NULL, 'Alagoa Nova', 15);
INSERT INTO endereco.cidade VALUES (2566, '2019-10-23 14:05:31.168533', NULL, 'Alcantil', 15);
INSERT INTO endereco.cidade VALUES (2569, '2019-10-23 14:05:31.168533', NULL, 'Amparo', 15);
INSERT INTO endereco.cidade VALUES (2571, '2019-10-23 14:05:31.168533', NULL, 'Araçagi', 15);
INSERT INTO endereco.cidade VALUES (2573, '2019-10-23 14:05:31.168533', NULL, 'Araruna', 15);
INSERT INTO endereco.cidade VALUES (2575, '2019-10-23 14:05:31.168533', NULL, 'Areia De Baraúnas', 15);
INSERT INTO endereco.cidade VALUES (2578, '2019-10-23 14:05:31.168533', NULL, 'Assunção', 15);
INSERT INTO endereco.cidade VALUES (2580, '2019-10-23 14:05:31.168533', NULL, 'Bananeiras', 15);
INSERT INTO endereco.cidade VALUES (2582, '2019-10-23 14:05:31.168533', NULL, 'Barra De Santa Rosa', 15);
INSERT INTO endereco.cidade VALUES (2585, '2019-10-23 14:05:31.168533', NULL, 'Bayeux', 15);
INSERT INTO endereco.cidade VALUES (2587, '2019-10-23 14:05:31.168533', NULL, 'Belém Do Brejo Do Cruz', 15);
INSERT INTO endereco.cidade VALUES (2590, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista', 15);
INSERT INTO endereco.cidade VALUES (2592, '2019-10-23 14:05:31.168533', NULL, 'Bom Sucesso', 15);
INSERT INTO endereco.cidade VALUES (2594, '2019-10-23 14:05:31.168533', NULL, 'Boqueirão', 15);
INSERT INTO endereco.cidade VALUES (2597, '2019-10-23 14:05:31.168533', NULL, 'Brejo Dos Santos', 15);
INSERT INTO endereco.cidade VALUES (2599, '2019-10-23 14:05:31.168533', NULL, 'Cabaceiras', 15);
INSERT INTO endereco.cidade VALUES (2601, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Dos Índios', 15);
INSERT INTO endereco.cidade VALUES (2604, '2019-10-23 14:05:31.168533', NULL, 'Cacimbas', 15);
INSERT INTO endereco.cidade VALUES (2606, '2019-10-23 14:05:31.168533', NULL, 'Cajazeiras', 15);
INSERT INTO endereco.cidade VALUES (2608, '2019-10-23 14:05:31.168533', NULL, 'Caldas Brandão', 15);
INSERT INTO endereco.cidade VALUES (2611, '2019-10-23 14:05:31.168533', NULL, 'Capim', 15);
INSERT INTO endereco.cidade VALUES (2613, '2019-10-23 14:05:31.168533', NULL, 'Carrapateira', 15);
INSERT INTO endereco.cidade VALUES (2615, '2019-10-23 14:05:31.168533', NULL, 'Catingueira', 15);
INSERT INTO endereco.cidade VALUES (2618, '2019-10-23 14:05:31.168533', NULL, 'Conceição', 15);
INSERT INTO endereco.cidade VALUES (2620, '2019-10-23 14:05:31.168533', NULL, 'Conde', 15);
INSERT INTO endereco.cidade VALUES (2622, '2019-10-23 14:05:31.168533', NULL, 'Coremas', 15);
INSERT INTO endereco.cidade VALUES (2624, '2019-10-23 14:05:31.168533', NULL, 'Cruz Do Espírito Santo', 15);
INSERT INTO endereco.cidade VALUES (2627, '2019-10-23 14:05:31.168533', NULL, 'Cuité De Mamanguape', 15);
INSERT INTO endereco.cidade VALUES (2629, '2019-10-23 14:05:31.168533', NULL, 'Curral De Cima', 15);
INSERT INTO endereco.cidade VALUES (2632, '2019-10-23 14:05:31.168533', NULL, 'Desterro', 15);
INSERT INTO endereco.cidade VALUES (2635, '2019-10-23 14:05:31.168533', NULL, 'Duas Estradas', 15);
INSERT INTO endereco.cidade VALUES (2637, '2019-10-23 14:05:31.168533', NULL, 'Esperança', 15);
INSERT INTO endereco.cidade VALUES (2639, '2019-10-23 14:05:31.168533', NULL, 'Frei Martinho', 15);
INSERT INTO endereco.cidade VALUES (2642, '2019-10-23 14:05:31.168533', NULL, 'Gurinhém', 15);
INSERT INTO endereco.cidade VALUES (2644, '2019-10-23 14:05:31.168533', NULL, 'Ibiara', 15);
INSERT INTO endereco.cidade VALUES (2646, '2019-10-23 14:05:31.168533', NULL, 'Imaculada', 15);
INSERT INTO endereco.cidade VALUES (2649, '2019-10-23 14:05:31.168533', NULL, 'Itaporanga', 15);
INSERT INTO endereco.cidade VALUES (2650, '2019-10-23 14:05:31.168533', NULL, 'Itapororoca', 15);
INSERT INTO endereco.cidade VALUES (2651, '2019-10-23 14:05:31.168533', NULL, 'Itatuba', 15);
INSERT INTO endereco.cidade VALUES (2653, '2019-10-23 14:05:31.168533', NULL, 'Jericó', 15);
INSERT INTO endereco.cidade VALUES (2655, '2019-10-23 14:05:31.168533', NULL, 'Joca Claudino', 15);
INSERT INTO endereco.cidade VALUES (2658, '2019-10-23 14:05:31.168533', NULL, 'Junco Do Seridó', 15);
INSERT INTO endereco.cidade VALUES (2661, '2019-10-23 14:05:31.168533', NULL, 'Lagoa', 15);
INSERT INTO endereco.cidade VALUES (2663, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Seca', 15);
INSERT INTO endereco.cidade VALUES (2665, '2019-10-23 14:05:31.168533', NULL, 'Livramento', 15);
INSERT INTO endereco.cidade VALUES (2667, '2019-10-23 14:05:31.168533', NULL, 'Lucena', 15);
INSERT INTO endereco.cidade VALUES (2670, '2019-10-23 14:05:31.168533', NULL, 'Mamanguape', 15);
INSERT INTO endereco.cidade VALUES (2672, '2019-10-23 14:05:31.168533', NULL, 'Marcação', 15);
INSERT INTO endereco.cidade VALUES (2674, '2019-10-23 14:05:31.168533', NULL, 'Marizópolis', 15);
INSERT INTO endereco.cidade VALUES (2677, '2019-10-23 14:05:31.168533', NULL, 'Matinhas', 15);
INSERT INTO endereco.cidade VALUES (2679, '2019-10-23 14:05:31.168533', NULL, 'Maturéia', 15);
INSERT INTO endereco.cidade VALUES (2681, '2019-10-23 14:05:31.168533', NULL, 'Montadas', 15);
INSERT INTO endereco.cidade VALUES (2684, '2019-10-23 14:05:31.168533', NULL, 'Mulungu', 15);
INSERT INTO endereco.cidade VALUES (2686, '2019-10-23 14:05:31.168533', NULL, 'Nazarezinho', 15);
INSERT INTO endereco.cidade VALUES (2688, '2019-10-23 14:05:31.168533', NULL, 'Nova Olinda', 15);
INSERT INTO endereco.cidade VALUES (2690, '2019-10-23 14:05:31.168533', NULL, 'Olho D`Água', 15);
INSERT INTO endereco.cidade VALUES (2692, '2019-10-23 14:05:31.168533', NULL, 'Ouro Velho', 15);
INSERT INTO endereco.cidade VALUES (2695, '2019-10-23 14:05:31.168533', NULL, 'Patos', 15);
INSERT INTO endereco.cidade VALUES (2697, '2019-10-23 14:05:31.168533', NULL, 'Pedra Branca', 15);
INSERT INTO endereco.cidade VALUES (2699, '2019-10-23 14:05:31.168533', NULL, 'Pedras De Fogo', 15);
INSERT INTO endereco.cidade VALUES (2702, '2019-10-23 14:05:31.168533', NULL, 'Picuí', 15);
INSERT INTO endereco.cidade VALUES (2704, '2019-10-23 14:05:31.168533', NULL, 'Pilões', 15);
INSERT INTO endereco.cidade VALUES (2706, '2019-10-23 14:05:31.168533', NULL, 'Pirpirituba', 15);
INSERT INTO endereco.cidade VALUES (2709, '2019-10-23 14:05:31.168533', NULL, 'Poço Dantas', 15);
INSERT INTO endereco.cidade VALUES (2711, '2019-10-23 14:05:31.168533', NULL, 'Pombal', 15);
INSERT INTO endereco.cidade VALUES (2738, '2019-10-23 14:05:31.168533', NULL, 'São Domingos', 15);
INSERT INTO endereco.cidade VALUES (2741, '2019-10-23 14:05:31.168533', NULL, 'São João Do Cariri', 15);
INSERT INTO endereco.cidade VALUES (2744, '2019-10-23 14:05:31.168533', NULL, 'São José Da Lagoa Tapada', 15);
INSERT INTO endereco.cidade VALUES (2746, '2019-10-23 14:05:31.168533', NULL, 'São José De Espinharas', 15);
INSERT INTO endereco.cidade VALUES (2749, '2019-10-23 14:05:31.168533', NULL, 'São José Do Bonfim', 15);
INSERT INTO endereco.cidade VALUES (2752, '2019-10-23 14:05:31.168533', NULL, 'São José Dos Cordeiros', 15);
INSERT INTO endereco.cidade VALUES (2755, '2019-10-23 14:05:31.168533', NULL, 'São Miguel De Taipu', 15);
INSERT INTO endereco.cidade VALUES (2757, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Do Umbuzeiro', 15);
INSERT INTO endereco.cidade VALUES (2760, '2019-10-23 14:05:31.168533', NULL, 'Serra Branca', 15);
INSERT INTO endereco.cidade VALUES (2763, '2019-10-23 14:05:31.168533', NULL, 'Serra Redonda', 15);
INSERT INTO endereco.cidade VALUES (2765, '2019-10-23 14:05:31.168533', NULL, 'Sertãozinho', 15);
INSERT INTO endereco.cidade VALUES (2768, '2019-10-23 14:05:31.168533', NULL, 'Soledade', 15);
INSERT INTO endereco.cidade VALUES (2770, '2019-10-23 14:05:31.168533', NULL, 'Sousa', 15);
INSERT INTO endereco.cidade VALUES (2772, '2019-10-23 14:05:31.168533', NULL, 'Tacima', 15);
INSERT INTO endereco.cidade VALUES (2775, '2019-10-23 14:05:31.168533', NULL, 'Teixeira', 15);
INSERT INTO endereco.cidade VALUES (2776, '2019-10-23 14:05:31.168533', NULL, 'Tenório', 15);
INSERT INTO endereco.cidade VALUES (2778, '2019-10-23 14:05:31.168533', NULL, 'Uiraúna', 15);
INSERT INTO endereco.cidade VALUES (2781, '2019-10-23 14:05:31.168533', NULL, 'Vieirópolis', 15);
INSERT INTO endereco.cidade VALUES (2783, '2019-10-23 14:05:31.168533', NULL, 'Zabelê', 15);
INSERT INTO endereco.cidade VALUES (2785, '2019-10-23 14:05:31.168533', NULL, 'Afogados Da Ingazeira', 16);
INSERT INTO endereco.cidade VALUES (2788, '2019-10-23 14:05:31.168533', NULL, 'Água Preta', 16);
INSERT INTO endereco.cidade VALUES (2790, '2019-10-23 14:05:31.168533', NULL, 'Alagoinha', 16);
INSERT INTO endereco.cidade VALUES (2792, '2019-10-23 14:05:31.168533', NULL, 'Altinho', 16);
INSERT INTO endereco.cidade VALUES (2795, '2019-10-23 14:05:31.168533', NULL, 'Araçoiaba', 16);
INSERT INTO endereco.cidade VALUES (2797, '2019-10-23 14:05:31.168533', NULL, 'Arcoverde', 16);
INSERT INTO endereco.cidade VALUES (2799, '2019-10-23 14:05:31.168533', NULL, 'Barreiros', 16);
INSERT INTO endereco.cidade VALUES (2801, '2019-10-23 14:05:31.168533', NULL, 'Belém De São Francisco', 16);
INSERT INTO endereco.cidade VALUES (2804, '2019-10-23 14:05:31.168533', NULL, 'Bezerros', 16);
INSERT INTO endereco.cidade VALUES (2806, '2019-10-23 14:05:31.168533', NULL, 'Bom Conselho', 16);
INSERT INTO endereco.cidade VALUES (2809, '2019-10-23 14:05:31.168533', NULL, 'Brejão', 16);
INSERT INTO endereco.cidade VALUES (2811, '2019-10-23 14:05:31.168533', NULL, 'Brejo Da Madre De Deus', 16);
INSERT INTO endereco.cidade VALUES (2814, '2019-10-23 14:05:31.168533', NULL, 'Cabo De Santo Agostinho', 16);
INSERT INTO endereco.cidade VALUES (2816, '2019-10-23 14:05:31.168533', NULL, 'Cachoeirinha', 16);
INSERT INTO endereco.cidade VALUES (2819, '2019-10-23 14:05:31.168533', NULL, 'Calumbi', 16);
INSERT INTO endereco.cidade VALUES (2821, '2019-10-23 14:05:31.168533', NULL, 'Camocim De São Félix', 16);
INSERT INTO endereco.cidade VALUES (2824, '2019-10-23 14:05:31.168533', NULL, 'Capoeiras', 16);
INSERT INTO endereco.cidade VALUES (2826, '2019-10-23 14:05:31.168533', NULL, 'Carnaubeira Da Penha', 16);
INSERT INTO endereco.cidade VALUES (2829, '2019-10-23 14:05:31.168533', NULL, 'Casinhas', 16);
INSERT INTO endereco.cidade VALUES (2831, '2019-10-23 14:05:31.168533', NULL, 'Cedro', 16);
INSERT INTO endereco.cidade VALUES (2833, '2019-10-23 14:05:31.168533', NULL, 'Chã Grande', 16);
INSERT INTO endereco.cidade VALUES (2835, '2019-10-23 14:05:31.168533', NULL, 'Correntes', 16);
INSERT INTO endereco.cidade VALUES (2838, '2019-10-23 14:05:31.168533', NULL, 'Cupira', 16);
INSERT INTO endereco.cidade VALUES (2840, '2019-10-23 14:05:31.168533', NULL, 'Dormentes', 16);
INSERT INTO endereco.cidade VALUES (2842, '2019-10-23 14:05:31.168533', NULL, 'Exu', 16);
INSERT INTO endereco.cidade VALUES (2844, '2019-10-23 14:05:31.168533', NULL, 'Fernando De Noronha', 16);
INSERT INTO endereco.cidade VALUES (2847, '2019-10-23 14:05:31.168533', NULL, 'Floresta', 16);
INSERT INTO endereco.cidade VALUES (2849, '2019-10-23 14:05:31.168533', NULL, 'Gameleira', 16);
INSERT INTO endereco.cidade VALUES (2851, '2019-10-23 14:05:31.168533', NULL, 'Glória Do Goitá', 16);
INSERT INTO endereco.cidade VALUES (2854, '2019-10-23 14:05:31.168533', NULL, 'Gravatá', 16);
INSERT INTO endereco.cidade VALUES (2856, '2019-10-23 14:05:31.168533', NULL, 'Ibimirim', 16);
INSERT INTO endereco.cidade VALUES (2858, '2019-10-23 14:05:31.168533', NULL, 'Igarassu', 16);
INSERT INTO endereco.cidade VALUES (2860, '2019-10-23 14:05:31.168533', NULL, 'Ilha De Itamaracá', 16);
INSERT INTO endereco.cidade VALUES (2874, '2019-10-23 14:05:31.168533', NULL, 'Jatobá', 16);
INSERT INTO endereco.cidade VALUES (2876, '2019-10-23 14:05:31.168533', NULL, 'Joaquim Nabuco', 16);
INSERT INTO endereco.cidade VALUES (2879, '2019-10-23 14:05:31.168533', NULL, 'Jurema', 16);
INSERT INTO endereco.cidade VALUES (2881, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Do Itaenga', 16);
INSERT INTO endereco.cidade VALUES (2883, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Dos Gatos', 16);
INSERT INTO endereco.cidade VALUES (2886, '2019-10-23 14:05:31.168533', NULL, 'Limoeiro', 16);
INSERT INTO endereco.cidade VALUES (2888, '2019-10-23 14:05:31.168533', NULL, 'Machados', 16);
INSERT INTO endereco.cidade VALUES (2890, '2019-10-23 14:05:31.168533', NULL, 'Maraial', 16);
INSERT INTO endereco.cidade VALUES (2892, '2019-10-23 14:05:31.168533', NULL, 'Moreilândia', 16);
INSERT INTO endereco.cidade VALUES (2895, '2019-10-23 14:05:31.168533', NULL, 'Olinda', 16);
INSERT INTO endereco.cidade VALUES (2897, '2019-10-23 14:05:31.168533', NULL, 'Orocó', 16);
INSERT INTO endereco.cidade VALUES (2900, '2019-10-23 14:05:31.168533', NULL, 'Palmeirina', 16);
INSERT INTO endereco.cidade VALUES (2902, '2019-10-23 14:05:31.168533', NULL, 'Paranatama', 16);
INSERT INTO endereco.cidade VALUES (2904, '2019-10-23 14:05:31.168533', NULL, 'Passira', 16);
INSERT INTO endereco.cidade VALUES (2906, '2019-10-23 14:05:31.168533', NULL, 'Paulista', 16);
INSERT INTO endereco.cidade VALUES (2907, '2019-10-23 14:05:31.168533', NULL, 'Pedra', 16);
INSERT INTO endereco.cidade VALUES (2910, '2019-10-23 14:05:31.168533', NULL, 'Petrolina', 16);
INSERT INTO endereco.cidade VALUES (2912, '2019-10-23 14:05:31.168533', NULL, 'Pombos', 16);
INSERT INTO endereco.cidade VALUES (2914, '2019-10-23 14:05:31.168533', NULL, 'Quipapá', 16);
INSERT INTO endereco.cidade VALUES (2917, '2019-10-23 14:05:31.168533', NULL, 'Riacho Das Almas', 16);
INSERT INTO endereco.cidade VALUES (2919, '2019-10-23 14:05:31.168533', NULL, 'Rio Formoso', 16);
INSERT INTO endereco.cidade VALUES (2921, '2019-10-23 14:05:31.168533', NULL, 'Salgadinho', 16);
INSERT INTO endereco.cidade VALUES (2924, '2019-10-23 14:05:31.168533', NULL, 'Sanharó', 16);
INSERT INTO endereco.cidade VALUES (2926, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Da Baixa Verde', 16);
INSERT INTO endereco.cidade VALUES (2928, '2019-10-23 14:05:31.168533', NULL, 'Santa Filomena', 16);
INSERT INTO endereco.cidade VALUES (2931, '2019-10-23 14:05:31.168533', NULL, 'Santa Terezinha', 16);
INSERT INTO endereco.cidade VALUES (2933, '2019-10-23 14:05:31.168533', NULL, 'São Bento Do Una', 16);
INSERT INTO endereco.cidade VALUES (2936, '2019-10-23 14:05:31.168533', NULL, 'São Joaquim Do Monte', 16);
INSERT INTO endereco.cidade VALUES (2938, '2019-10-23 14:05:31.168533', NULL, 'São José Do Belmonte', 16);
INSERT INTO endereco.cidade VALUES (2940, '2019-10-23 14:05:31.168533', NULL, 'São Lourenço Da Mata', 16);
INSERT INTO endereco.cidade VALUES (2943, '2019-10-23 14:05:31.168533', NULL, 'Serrita', 16);
INSERT INTO endereco.cidade VALUES (2945, '2019-10-23 14:05:31.168533', NULL, 'Sirinhaém', 16);
INSERT INTO endereco.cidade VALUES (2948, '2019-10-23 14:05:31.168533', NULL, 'Tabira', 16);
INSERT INTO endereco.cidade VALUES (2950, '2019-10-23 14:05:31.168533', NULL, 'Tacaratu', 16);
INSERT INTO endereco.cidade VALUES (2952, '2019-10-23 14:05:31.168533', NULL, 'Taquaritinga Do Norte', 16);
INSERT INTO endereco.cidade VALUES (2955, '2019-10-23 14:05:31.168533', NULL, 'Timbaúba', 16);
INSERT INTO endereco.cidade VALUES (2957, '2019-10-23 14:05:31.168533', NULL, 'Tracunhaém', 16);
INSERT INTO endereco.cidade VALUES (2959, '2019-10-23 14:05:31.168533', NULL, 'Triunfo', 16);
INSERT INTO endereco.cidade VALUES (2962, '2019-10-23 14:05:31.168533', NULL, 'Venturosa', 16);
INSERT INTO endereco.cidade VALUES (2964, '2019-10-23 14:05:31.168533', NULL, 'Vertente Do Lério', 16);
INSERT INTO endereco.cidade VALUES (2966, '2019-10-23 14:05:31.168533', NULL, 'Vicência', 16);
INSERT INTO endereco.cidade VALUES (2969, '2019-10-23 14:05:31.168533', NULL, 'Acauã', 17);
INSERT INTO endereco.cidade VALUES (2971, '2019-10-23 14:05:31.168533', NULL, 'Água Branca', 17);
INSERT INTO endereco.cidade VALUES (2973, '2019-10-23 14:05:31.168533', NULL, 'Alegrete Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (2976, '2019-10-23 14:05:31.168533', NULL, 'Alvorada Do Gurguéia', 17);
INSERT INTO endereco.cidade VALUES (2978, '2019-10-23 14:05:31.168533', NULL, 'Angical Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (2980, '2019-10-23 14:05:31.168533', NULL, 'Antônio Almeida', 17);
INSERT INTO endereco.cidade VALUES (2983, '2019-10-23 14:05:31.168533', NULL, 'Arraial', 17);
INSERT INTO endereco.cidade VALUES (2986, '2019-10-23 14:05:31.168533', NULL, 'Baixa Grande Do Ribeiro', 17);
INSERT INTO endereco.cidade VALUES (2988, '2019-10-23 14:05:31.168533', NULL, 'Barras', 17);
INSERT INTO endereco.cidade VALUES (2990, '2019-10-23 14:05:31.168533', NULL, 'Barro Duro', 17);
INSERT INTO endereco.cidade VALUES (2992, '2019-10-23 14:05:31.168533', NULL, 'Bela Vista Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (2995, '2019-10-23 14:05:31.168533', NULL, 'Bertolínia', 17);
INSERT INTO endereco.cidade VALUES (2998, '2019-10-23 14:05:31.168533', NULL, 'Bocaina', 17);
INSERT INTO endereco.cidade VALUES (3000, '2019-10-23 14:05:31.168533', NULL, 'Bom Princípio Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3002, '2019-10-23 14:05:31.168533', NULL, 'Boqueirão Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3005, '2019-10-23 14:05:31.168533', NULL, 'Buriti Dos Lopes', 17);
INSERT INTO endereco.cidade VALUES (3007, '2019-10-23 14:05:31.168533', NULL, 'Cabeceiras Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3010, '2019-10-23 14:05:31.168533', NULL, 'Caldeirão Grande Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3013, '2019-10-23 14:05:31.168533', NULL, 'Campo Grande Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3016, '2019-10-23 14:05:31.168533', NULL, 'Canavieira', 17);
INSERT INTO endereco.cidade VALUES (3017, '2019-10-23 14:05:31.168533', NULL, 'Canto Do Buriti', 17);
INSERT INTO endereco.cidade VALUES (3020, '2019-10-23 14:05:31.168533', NULL, 'Caracol', 17);
INSERT INTO endereco.cidade VALUES (3022, '2019-10-23 14:05:31.168533', NULL, 'Caridade Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3025, '2019-10-23 14:05:31.168533', NULL, 'Cocal', 17);
INSERT INTO endereco.cidade VALUES (3027, '2019-10-23 14:05:31.168533', NULL, 'Cocal Dos Alves', 17);
INSERT INTO endereco.cidade VALUES (3029, '2019-10-23 14:05:31.168533', NULL, 'Colônia Do Gurguéia', 17);
INSERT INTO endereco.cidade VALUES (3030, '2019-10-23 14:05:31.168533', NULL, 'Colônia Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3031, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Canindé', 17);
INSERT INTO endereco.cidade VALUES (3034, '2019-10-23 14:05:31.168533', NULL, 'Cristalândia Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3036, '2019-10-23 14:05:31.168533', NULL, 'Curimatá', 17);
INSERT INTO endereco.cidade VALUES (3038, '2019-10-23 14:05:31.168533', NULL, 'Curral Novo Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3041, '2019-10-23 14:05:31.168533', NULL, 'Dirceu Arcoverde', 17);
INSERT INTO endereco.cidade VALUES (3043, '2019-10-23 14:05:31.168533', NULL, 'Dom Inocêncio', 17);
INSERT INTO endereco.cidade VALUES (3045, '2019-10-23 14:05:31.168533', NULL, 'Elesbão Veloso', 17);
INSERT INTO endereco.cidade VALUES (3048, '2019-10-23 14:05:31.168533', NULL, 'Fartura Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3050, '2019-10-23 14:05:31.168533', NULL, 'Floresta Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3052, '2019-10-23 14:05:31.168533', NULL, 'Francinópolis', 17);
INSERT INTO endereco.cidade VALUES (3055, '2019-10-23 14:05:31.168533', NULL, 'Francisco Santos', 17);
INSERT INTO endereco.cidade VALUES (3057, '2019-10-23 14:05:31.168533', NULL, 'Geminiano', 17);
INSERT INTO endereco.cidade VALUES (3060, '2019-10-23 14:05:31.168533', NULL, 'Guaribas', 17);
INSERT INTO endereco.cidade VALUES (3062, '2019-10-23 14:05:31.168533', NULL, 'Ilha Grande', 17);
INSERT INTO endereco.cidade VALUES (3064, '2019-10-23 14:05:31.168533', NULL, 'Ipiranga Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3066, '2019-10-23 14:05:31.168533', NULL, 'Itainópolis', 17);
INSERT INTO endereco.cidade VALUES (3069, '2019-10-23 14:05:31.168533', NULL, 'Jaicós', 17);
INSERT INTO endereco.cidade VALUES (3071, '2019-10-23 14:05:31.168533', NULL, 'Jatobá Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3074, '2019-10-23 14:05:31.168533', NULL, 'Joaquim Pires', 17);
INSERT INTO endereco.cidade VALUES (3076, '2019-10-23 14:05:31.168533', NULL, 'José De Freitas', 17);
INSERT INTO endereco.cidade VALUES (3078, '2019-10-23 14:05:31.168533', NULL, 'Júlio Borges', 17);
INSERT INTO endereco.cidade VALUES (3081, '2019-10-23 14:05:31.168533', NULL, 'Lagoa De São Francisco', 17);
INSERT INTO endereco.cidade VALUES (3083, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3086, '2019-10-23 14:05:31.168533', NULL, 'Landri Sales', 17);
INSERT INTO endereco.cidade VALUES (3088, '2019-10-23 14:05:31.168533', NULL, 'Luzilândia', 17);
INSERT INTO endereco.cidade VALUES (3090, '2019-10-23 14:05:31.168533', NULL, 'Manoel Emídio', 17);
INSERT INTO endereco.cidade VALUES (3093, '2019-10-23 14:05:31.168533', NULL, 'Massapê Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3095, '2019-10-23 14:05:31.168533', NULL, 'Miguel Alves', 17);
INSERT INTO endereco.cidade VALUES (3097, '2019-10-23 14:05:31.168533', NULL, 'Milton Brandão', 17);
INSERT INTO endereco.cidade VALUES (3100, '2019-10-23 14:05:31.168533', NULL, 'Monte Alegre Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3102, '2019-10-23 14:05:31.168533', NULL, 'Morro Do Chapéu Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3105, '2019-10-23 14:05:31.168533', NULL, 'Nazária', 17);
INSERT INTO endereco.cidade VALUES (3107, '2019-10-23 14:05:31.168533', NULL, 'Nossa Senhora Dos Remédios', 17);
INSERT INTO endereco.cidade VALUES (3110, '2019-10-23 14:05:31.168533', NULL, 'Novo Santo Antônio', 17);
INSERT INTO endereco.cidade VALUES (3112, '2019-10-23 14:05:31.168533', NULL, 'Olho D`Água Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3115, '2019-10-23 14:05:31.168533', NULL, 'Pajeú Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3118, '2019-10-23 14:05:31.168533', NULL, 'Paquetá', 17);
INSERT INTO endereco.cidade VALUES (3120, '2019-10-23 14:05:31.168533', NULL, 'Parnaíba', 17);
INSERT INTO endereco.cidade VALUES (3122, '2019-10-23 14:05:31.168533', NULL, 'Patos Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3124, '2019-10-23 14:05:31.168533', NULL, 'Paulistana', 17);
INSERT INTO endereco.cidade VALUES (3127, '2019-10-23 14:05:31.168533', NULL, 'Pedro Laurentino', 17);
INSERT INTO endereco.cidade VALUES (3129, '2019-10-23 14:05:31.168533', NULL, 'Pimenteiras', 17);
INSERT INTO endereco.cidade VALUES (3131, '2019-10-23 14:05:31.168533', NULL, 'Piracuruca', 17);
INSERT INTO endereco.cidade VALUES (3134, '2019-10-23 14:05:31.168533', NULL, 'Porto Alegre Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3136, '2019-10-23 14:05:31.168533', NULL, 'Queimada Nova', 17);
INSERT INTO endereco.cidade VALUES (3138, '2019-10-23 14:05:31.168533', NULL, 'Regeneração', 17);
INSERT INTO endereco.cidade VALUES (3141, '2019-10-23 14:05:31.168533', NULL, 'Ribeiro Gonçalves', 17);
INSERT INTO endereco.cidade VALUES (3143, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3146, '2019-10-23 14:05:31.168533', NULL, 'Santa Luz', 17);
INSERT INTO endereco.cidade VALUES (3148, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3150, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Dos Milagres', 17);
INSERT INTO endereco.cidade VALUES (3151, '2019-10-23 14:05:31.168533', NULL, 'Santo Inácio Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3153, '2019-10-23 14:05:31.168533', NULL, 'São Félix Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3155, '2019-10-23 14:05:31.168533', NULL, 'São Francisco Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3157, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3160, '2019-10-23 14:05:31.168533', NULL, 'São João Da Serra', 17);
INSERT INTO endereco.cidade VALUES (3162, '2019-10-23 14:05:31.168533', NULL, 'São João Do Arraial', 17);
INSERT INTO endereco.cidade VALUES (3165, '2019-10-23 14:05:31.168533', NULL, 'São José Do Peixe', 17);
INSERT INTO endereco.cidade VALUES (3168, '2019-10-23 14:05:31.168533', NULL, 'São Lourenço Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3170, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Da Baixa Grande', 17);
INSERT INTO endereco.cidade VALUES (3173, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3175, '2019-10-23 14:05:31.168533', NULL, 'Sebastião Barros', 17);
INSERT INTO endereco.cidade VALUES (3178, '2019-10-23 14:05:31.168533', NULL, 'Simões', 17);
INSERT INTO endereco.cidade VALUES (3180, '2019-10-23 14:05:31.168533', NULL, 'Socorro Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3182, '2019-10-23 14:05:31.168533', NULL, 'Tamboril Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3185, '2019-10-23 14:05:31.168533', NULL, 'União', 17);
INSERT INTO endereco.cidade VALUES (3187, '2019-10-23 14:05:31.168533', NULL, 'Valença Do Piauí', 17);
INSERT INTO endereco.cidade VALUES (3189, '2019-10-23 14:05:31.168533', NULL, 'Várzea Grande', 17);
INSERT INTO endereco.cidade VALUES (3192, '2019-10-23 14:05:31.168533', NULL, 'Wall Ferraz', 17);
INSERT INTO endereco.cidade VALUES (3194, '2019-10-23 14:05:31.168533', NULL, 'Adrianópolis', 18);
INSERT INTO endereco.cidade VALUES (3196, '2019-10-23 14:05:31.168533', NULL, 'Almirante Tamandaré', 18);
INSERT INTO endereco.cidade VALUES (3199, '2019-10-23 14:05:31.168533', NULL, 'Alto Paraná', 18);
INSERT INTO endereco.cidade VALUES (3202, '2019-10-23 14:05:31.168533', NULL, 'Alvorada Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3204, '2019-10-23 14:05:31.168533', NULL, 'Ampére', 18);
INSERT INTO endereco.cidade VALUES (3206, '2019-10-23 14:05:31.168533', NULL, 'Andirá', 18);
INSERT INTO endereco.cidade VALUES (3209, '2019-10-23 14:05:31.168533', NULL, 'Antônio Olinto', 18);
INSERT INTO endereco.cidade VALUES (3211, '2019-10-23 14:05:31.168533', NULL, 'Arapongas', 18);
INSERT INTO endereco.cidade VALUES (3213, '2019-10-23 14:05:31.168533', NULL, 'Arapuã', 18);
INSERT INTO endereco.cidade VALUES (3216, '2019-10-23 14:05:31.168533', NULL, 'Ariranha Do Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3218, '2019-10-23 14:05:31.168533', NULL, 'Assis Chateaubriand', 18);
INSERT INTO endereco.cidade VALUES (3220, '2019-10-23 14:05:31.168533', NULL, 'Atalaia', 18);
INSERT INTO endereco.cidade VALUES (3223, '2019-10-23 14:05:31.168533', NULL, 'Barbosa Ferraz', 18);
INSERT INTO endereco.cidade VALUES (3225, '2019-10-23 14:05:31.168533', NULL, 'Barracão', 18);
INSERT INTO endereco.cidade VALUES (3227, '2019-10-23 14:05:31.168533', NULL, 'Bela Vista Do Paraíso', 18);
INSERT INTO endereco.cidade VALUES (3230, '2019-10-23 14:05:31.168533', NULL, 'Boa Esperança Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3232, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista Da Aparecida', 18);
INSERT INTO endereco.cidade VALUES (3235, '2019-10-23 14:05:31.168533', NULL, 'Bom Sucesso', 18);
INSERT INTO endereco.cidade VALUES (3237, '2019-10-23 14:05:31.168533', NULL, 'Borrazópolis', 18);
INSERT INTO endereco.cidade VALUES (3240, '2019-10-23 14:05:31.168533', NULL, 'Cafeara', 18);
INSERT INTO endereco.cidade VALUES (3242, '2019-10-23 14:05:31.168533', NULL, 'Cafezal Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3244, '2019-10-23 14:05:31.168533', NULL, 'Cambará', 18);
INSERT INTO endereco.cidade VALUES (3247, '2019-10-23 14:05:31.168533', NULL, 'Campina Da Lagoa', 18);
INSERT INTO endereco.cidade VALUES (3249, '2019-10-23 14:05:31.168533', NULL, 'Campina Grande Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3251, '2019-10-23 14:05:31.168533', NULL, 'Campo Do Tenente', 18);
INSERT INTO endereco.cidade VALUES (3254, '2019-10-23 14:05:31.168533', NULL, 'Campo Mourão', 18);
INSERT INTO endereco.cidade VALUES (3256, '2019-10-23 14:05:31.168533', NULL, 'Candói', 18);
INSERT INTO endereco.cidade VALUES (3259, '2019-10-23 14:05:31.168533', NULL, 'Capitão Leônidas Marques', 18);
INSERT INTO endereco.cidade VALUES (3261, '2019-10-23 14:05:31.168533', NULL, 'Carlópolis', 18);
INSERT INTO endereco.cidade VALUES (3264, '2019-10-23 14:05:31.168533', NULL, 'Catanduvas', 18);
INSERT INTO endereco.cidade VALUES (3266, '2019-10-23 14:05:31.168533', NULL, 'Cerro Azul', 18);
INSERT INTO endereco.cidade VALUES (3268, '2019-10-23 14:05:31.168533', NULL, 'Chopinzinho', 18);
INSERT INTO endereco.cidade VALUES (3270, '2019-10-23 14:05:31.168533', NULL, 'Cidade Gaúcha', 18);
INSERT INTO endereco.cidade VALUES (3274, '2019-10-23 14:05:31.168533', NULL, 'Congonhinhas', 18);
INSERT INTO endereco.cidade VALUES (3275, '2019-10-23 14:05:31.168533', NULL, 'Conselheiro Mairinck', 18);
INSERT INTO endereco.cidade VALUES (3277, '2019-10-23 14:05:31.168533', NULL, 'Corbélia', 18);
INSERT INTO endereco.cidade VALUES (3279, '2019-10-23 14:05:31.168533', NULL, 'Coronel Domingos Soares', 18);
INSERT INTO endereco.cidade VALUES (3282, '2019-10-23 14:05:31.168533', NULL, 'Cruz Machado', 18);
INSERT INTO endereco.cidade VALUES (3284, '2019-10-23 14:05:31.168533', NULL, 'Cruzeiro Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3287, '2019-10-23 14:05:31.168533', NULL, 'Curitiba', 18);
INSERT INTO endereco.cidade VALUES (3289, '2019-10-23 14:05:31.168533', NULL, 'Diamante D`Oeste', 18);
INSERT INTO endereco.cidade VALUES (3291, '2019-10-23 14:05:31.168533', NULL, 'Diamante Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3294, '2019-10-23 14:05:31.168533', NULL, 'Doutor Camargo', 18);
INSERT INTO endereco.cidade VALUES (3296, '2019-10-23 14:05:31.168533', NULL, 'Enéas Marques', 18);
INSERT INTO endereco.cidade VALUES (3299, '2019-10-23 14:05:31.168533', NULL, 'Esperança Nova', 18);
INSERT INTO endereco.cidade VALUES (3301, '2019-10-23 14:05:31.168533', NULL, 'Farol', 18);
INSERT INTO endereco.cidade VALUES (3303, '2019-10-23 14:05:31.168533', NULL, 'Fazenda Rio Grande', 18);
INSERT INTO endereco.cidade VALUES (3306, '2019-10-23 14:05:31.168533', NULL, 'Figueira', 18);
INSERT INTO endereco.cidade VALUES (3308, '2019-10-23 14:05:31.168533', NULL, 'Floraí', 18);
INSERT INTO endereco.cidade VALUES (3310, '2019-10-23 14:05:31.168533', NULL, 'Florestópolis', 18);
INSERT INTO endereco.cidade VALUES (3312, '2019-10-23 14:05:31.168533', NULL, 'Formosa Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3315, '2019-10-23 14:05:31.168533', NULL, 'Francisco Alves', 18);
INSERT INTO endereco.cidade VALUES (3317, '2019-10-23 14:05:31.168533', NULL, 'General Carneiro', 18);
INSERT INTO endereco.cidade VALUES (3320, '2019-10-23 14:05:31.168533', NULL, 'Goioxim', 18);
INSERT INTO endereco.cidade VALUES (3322, '2019-10-23 14:05:31.168533', NULL, 'Guaíra', 18);
INSERT INTO endereco.cidade VALUES (3324, '2019-10-23 14:05:31.168533', NULL, 'Guamiranga', 18);
INSERT INTO endereco.cidade VALUES (3327, '2019-10-23 14:05:31.168533', NULL, 'Guaraci', 18);
INSERT INTO endereco.cidade VALUES (3329, '2019-10-23 14:05:31.168533', NULL, 'Guarapuava', 18);
INSERT INTO endereco.cidade VALUES (3331, '2019-10-23 14:05:31.168533', NULL, 'Guaratuba', 18);
INSERT INTO endereco.cidade VALUES (3333, '2019-10-23 14:05:31.168533', NULL, 'Ibaiti', 18);
INSERT INTO endereco.cidade VALUES (3336, '2019-10-23 14:05:31.168533', NULL, 'Icaraíma', 18);
INSERT INTO endereco.cidade VALUES (3338, '2019-10-23 14:05:31.168533', NULL, 'Iguatu', 18);
INSERT INTO endereco.cidade VALUES (3340, '2019-10-23 14:05:31.168533', NULL, 'Imbituva', 18);
INSERT INTO endereco.cidade VALUES (3342, '2019-10-23 14:05:31.168533', NULL, 'Inajá', 18);
INSERT INTO endereco.cidade VALUES (3345, '2019-10-23 14:05:31.168533', NULL, 'Iporã', 18);
INSERT INTO endereco.cidade VALUES (3347, '2019-10-23 14:05:31.168533', NULL, 'Irati', 18);
INSERT INTO endereco.cidade VALUES (3349, '2019-10-23 14:05:31.168533', NULL, 'Itaguajé', 18);
INSERT INTO endereco.cidade VALUES (3351, '2019-10-23 14:05:31.168533', NULL, 'Itambaracá', 18);
INSERT INTO endereco.cidade VALUES (3353, '2019-10-23 14:05:31.168533', NULL, 'Itapejara D`Oeste', 18);
INSERT INTO endereco.cidade VALUES (3356, '2019-10-23 14:05:31.168533', NULL, 'Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3358, '2019-10-23 14:05:31.168533', NULL, 'Ivaté', 18);
INSERT INTO endereco.cidade VALUES (3361, '2019-10-23 14:05:31.168533', NULL, 'Jacarezinho', 18);
INSERT INTO endereco.cidade VALUES (3363, '2019-10-23 14:05:31.168533', NULL, 'Jaguariaíva', 18);
INSERT INTO endereco.cidade VALUES (3365, '2019-10-23 14:05:31.168533', NULL, 'Janiópolis', 18);
INSERT INTO endereco.cidade VALUES (3367, '2019-10-23 14:05:31.168533', NULL, 'Japurá', 18);
INSERT INTO endereco.cidade VALUES (3369, '2019-10-23 14:05:31.168533', NULL, 'Jardim Olinda', 18);
INSERT INTO endereco.cidade VALUES (3372, '2019-10-23 14:05:31.168533', NULL, 'Joaquim Távora', 18);
INSERT INTO endereco.cidade VALUES (3374, '2019-10-23 14:05:31.168533', NULL, 'Juranda', 18);
INSERT INTO endereco.cidade VALUES (3377, '2019-10-23 14:05:31.168533', NULL, 'Lapa', 18);
INSERT INTO endereco.cidade VALUES (3379, '2019-10-23 14:05:31.168533', NULL, 'Laranjeiras Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3381, '2019-10-23 14:05:31.168533', NULL, 'Lidianópolis', 18);
INSERT INTO endereco.cidade VALUES (3384, '2019-10-23 14:05:31.168533', NULL, 'Lobato', 18);
INSERT INTO endereco.cidade VALUES (3386, '2019-10-23 14:05:31.168533', NULL, 'Luiziana', 18);
INSERT INTO endereco.cidade VALUES (3388, '2019-10-23 14:05:31.168533', NULL, 'Lupionópolis', 18);
INSERT INTO endereco.cidade VALUES (3391, '2019-10-23 14:05:31.168533', NULL, 'Mandaguaçu', 18);
INSERT INTO endereco.cidade VALUES (3393, '2019-10-23 14:05:31.168533', NULL, 'Mandirituba', 18);
INSERT INTO endereco.cidade VALUES (3395, '2019-10-23 14:05:31.168533', NULL, 'Mangueirinha', 18);
INSERT INTO endereco.cidade VALUES (3397, '2019-10-23 14:05:31.168533', NULL, 'Marechal Cândido Rondon', 18);
INSERT INTO endereco.cidade VALUES (3400, '2019-10-23 14:05:31.168533', NULL, 'Marilândia Do Sul', 18);
INSERT INTO endereco.cidade VALUES (3402, '2019-10-23 14:05:31.168533', NULL, 'Mariluz', 18);
INSERT INTO endereco.cidade VALUES (3403, '2019-10-23 14:05:31.168533', NULL, 'Maringá', 18);
INSERT INTO endereco.cidade VALUES (3405, '2019-10-23 14:05:31.168533', NULL, 'Maripá', 18);
INSERT INTO endereco.cidade VALUES (3407, '2019-10-23 14:05:31.168533', NULL, 'Marquinho', 18);
INSERT INTO endereco.cidade VALUES (3410, '2019-10-23 14:05:31.168533', NULL, 'Matinhos', 18);
INSERT INTO endereco.cidade VALUES (3412, '2019-10-23 14:05:31.168533', NULL, 'Mauá Da Serra', 18);
INSERT INTO endereco.cidade VALUES (3414, '2019-10-23 14:05:31.168533', NULL, 'Mercedes', 18);
INSERT INTO endereco.cidade VALUES (3417, '2019-10-23 14:05:31.168533', NULL, 'Missal', 18);
INSERT INTO endereco.cidade VALUES (3419, '2019-10-23 14:05:31.168533', NULL, 'Morretes', 18);
INSERT INTO endereco.cidade VALUES (3421, '2019-10-23 14:05:31.168533', NULL, 'Nossa Senhora Das Graças', 18);
INSERT INTO endereco.cidade VALUES (3423, '2019-10-23 14:05:31.168533', NULL, 'Nova América Da Colina', 18);
INSERT INTO endereco.cidade VALUES (3426, '2019-10-23 14:05:31.168533', NULL, 'Nova Esperança', 18);
INSERT INTO endereco.cidade VALUES (3428, '2019-10-23 14:05:31.168533', NULL, 'Nova Fátima', 18);
INSERT INTO endereco.cidade VALUES (3430, '2019-10-23 14:05:31.168533', NULL, 'Nova Londrina', 18);
INSERT INTO endereco.cidade VALUES (3433, '2019-10-23 14:05:31.168533', NULL, 'Nova Santa Bárbara', 18);
INSERT INTO endereco.cidade VALUES (3436, '2019-10-23 14:05:31.168533', NULL, 'Novo Itacolomi', 18);
INSERT INTO endereco.cidade VALUES (3438, '2019-10-23 14:05:31.168533', NULL, 'Ourizona', 18);
INSERT INTO endereco.cidade VALUES (3440, '2019-10-23 14:05:31.168533', NULL, 'Paiçandu', 18);
INSERT INTO endereco.cidade VALUES (3443, '2019-10-23 14:05:31.168533', NULL, 'Palmital', 18);
INSERT INTO endereco.cidade VALUES (3445, '2019-10-23 14:05:31.168533', NULL, 'Paraíso Do Norte', 18);
INSERT INTO endereco.cidade VALUES (3447, '2019-10-23 14:05:31.168533', NULL, 'Paranaguá', 18);
INSERT INTO endereco.cidade VALUES (3450, '2019-10-23 14:05:31.168533', NULL, 'Pato Bragado', 18);
INSERT INTO endereco.cidade VALUES (3452, '2019-10-23 14:05:31.168533', NULL, 'Paula Freitas', 18);
INSERT INTO endereco.cidade VALUES (3454, '2019-10-23 14:05:31.168533', NULL, 'Peabiru', 18);
INSERT INTO endereco.cidade VALUES (3457, '2019-10-23 14:05:31.168533', NULL, 'Pérola D`Oeste', 18);
INSERT INTO endereco.cidade VALUES (3459, '2019-10-23 14:05:31.168533', NULL, 'Pinhais', 18);
INSERT INTO endereco.cidade VALUES (3461, '2019-10-23 14:05:31.168533', NULL, 'Pinhalão', 18);
INSERT INTO endereco.cidade VALUES (3464, '2019-10-23 14:05:31.168533', NULL, 'Piraquara', 18);
INSERT INTO endereco.cidade VALUES (3466, '2019-10-23 14:05:31.168533', NULL, 'Pitangueiras', 18);
INSERT INTO endereco.cidade VALUES (3468, '2019-10-23 14:05:31.168533', NULL, 'Planalto', 18);
INSERT INTO endereco.cidade VALUES (3471, '2019-10-23 14:05:31.168533', NULL, 'Porecatu', 18);
INSERT INTO endereco.cidade VALUES (3473, '2019-10-23 14:05:31.168533', NULL, 'Porto Barreiro', 18);
INSERT INTO endereco.cidade VALUES (3475, '2019-10-23 14:05:31.168533', NULL, 'Porto Vitória', 18);
INSERT INTO endereco.cidade VALUES (3478, '2019-10-23 14:05:31.168533', NULL, 'Presidente Castelo Branco', 18);
INSERT INTO endereco.cidade VALUES (3480, '2019-10-23 14:05:31.168533', NULL, 'Prudentópolis', 18);
INSERT INTO endereco.cidade VALUES (3482, '2019-10-23 14:05:31.168533', NULL, 'Quatiguá', 18);
INSERT INTO endereco.cidade VALUES (3484, '2019-10-23 14:05:31.168533', NULL, 'Quatro Pontes', 18);
INSERT INTO endereco.cidade VALUES (3487, '2019-10-23 14:05:31.168533', NULL, 'Quinta Do Sol', 18);
INSERT INTO endereco.cidade VALUES (3489, '2019-10-23 14:05:31.168533', NULL, 'Ramilândia', 18);
INSERT INTO endereco.cidade VALUES (3491, '2019-10-23 14:05:31.168533', NULL, 'Rancho Alegre D`Oeste', 18);
INSERT INTO endereco.cidade VALUES (3494, '2019-10-23 14:05:31.168533', NULL, 'Renascença', 18);
INSERT INTO endereco.cidade VALUES (3496, '2019-10-23 14:05:31.168533', NULL, 'Reserva Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3498, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Do Pinhal', 18);
INSERT INTO endereco.cidade VALUES (3501, '2019-10-23 14:05:31.168533', NULL, 'Rio Bonito Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3504, '2019-10-23 14:05:31.168533', NULL, 'Rio Negro', 18);
INSERT INTO endereco.cidade VALUES (3506, '2019-10-23 14:05:31.168533', NULL, 'Roncador', 18);
INSERT INTO endereco.cidade VALUES (3508, '2019-10-23 14:05:31.168533', NULL, 'Rosário Do Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3511, '2019-10-23 14:05:31.168533', NULL, 'Salto Do Itararé', 18);
INSERT INTO endereco.cidade VALUES (3513, '2019-10-23 14:05:31.168533', NULL, 'Santa Amélia', 18);
INSERT INTO endereco.cidade VALUES (3515, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz De Monte Castelo', 18);
INSERT INTO endereco.cidade VALUES (3518, '2019-10-23 14:05:31.168533', NULL, 'Santa Inês', 18);
INSERT INTO endereco.cidade VALUES (3520, '2019-10-23 14:05:31.168533', NULL, 'Santa Izabel Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3523, '2019-10-23 14:05:31.168533', NULL, 'Santa Mariana', 18);
INSERT INTO endereco.cidade VALUES (3525, '2019-10-23 14:05:31.168533', NULL, 'Santa Tereza Do Oeste', 18);
INSERT INTO endereco.cidade VALUES (3527, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Itararé', 18);
INSERT INTO endereco.cidade VALUES (3528, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Da Platina', 18);
INSERT INTO endereco.cidade VALUES (3531, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Sudoeste', 18);
INSERT INTO endereco.cidade VALUES (3533, '2019-10-23 14:05:31.168533', NULL, 'São Carlos Do Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3536, '2019-10-23 14:05:31.168533', NULL, 'São João Do Caiuá', 18);
INSERT INTO endereco.cidade VALUES (3539, '2019-10-23 14:05:31.168533', NULL, 'São Jorge D`Oeste', 18);
INSERT INTO endereco.cidade VALUES (3541, '2019-10-23 14:05:31.168533', NULL, 'São Jorge Do Patrocínio', 18);
INSERT INTO endereco.cidade VALUES (3544, '2019-10-23 14:05:31.168533', NULL, 'São José Dos Pinhais', 18);
INSERT INTO endereco.cidade VALUES (3547, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3549, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Do Ivaí', 18);
INSERT INTO endereco.cidade VALUES (3551, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Da Amoreira', 18);
INSERT INTO endereco.cidade VALUES (3554, '2019-10-23 14:05:31.168533', NULL, 'Sarandi', 18);
INSERT INTO endereco.cidade VALUES (3557, '2019-10-23 14:05:31.168533', NULL, 'Serranópolis Do Iguaçu', 18);
INSERT INTO endereco.cidade VALUES (3559, '2019-10-23 14:05:31.168533', NULL, 'Sertanópolis', 18);
INSERT INTO endereco.cidade VALUES (3562, '2019-10-23 14:05:31.168533', NULL, 'Tamarana', 18);
INSERT INTO endereco.cidade VALUES (3564, '2019-10-23 14:05:31.168533', NULL, 'Tapejara', 18);
INSERT INTO endereco.cidade VALUES (3566, '2019-10-23 14:05:31.168533', NULL, 'Teixeira Soares', 18);
INSERT INTO endereco.cidade VALUES (3569, '2019-10-23 14:05:31.168533', NULL, 'Terra Rica', 18);
INSERT INTO endereco.cidade VALUES (3571, '2019-10-23 14:05:31.168533', NULL, 'Tibagi', 18);
INSERT INTO endereco.cidade VALUES (3573, '2019-10-23 14:05:31.168533', NULL, 'Toledo', 18);
INSERT INTO endereco.cidade VALUES (3575, '2019-10-23 14:05:31.168533', NULL, 'Três Barras Do Paraná', 18);
INSERT INTO endereco.cidade VALUES (3578, '2019-10-23 14:05:31.168533', NULL, 'Tupãssi', 18);
INSERT INTO endereco.cidade VALUES (3580, '2019-10-23 14:05:31.168533', NULL, 'Ubiratã', 18);
INSERT INTO endereco.cidade VALUES (3582, '2019-10-23 14:05:31.168533', NULL, 'União Da Vitória', 18);
INSERT INTO endereco.cidade VALUES (3585, '2019-10-23 14:05:31.168533', NULL, 'Ventania', 18);
INSERT INTO endereco.cidade VALUES (3587, '2019-10-23 14:05:31.168533', NULL, 'Verê', 18);
INSERT INTO endereco.cidade VALUES (3590, '2019-10-23 14:05:31.168533', NULL, 'Wenceslau Braz', 18);
INSERT INTO endereco.cidade VALUES (3592, '2019-10-23 14:05:31.168533', NULL, 'Angra Dos Reis', 19);
INSERT INTO endereco.cidade VALUES (3595, '2019-10-23 14:05:31.168533', NULL, 'Areal', 19);
INSERT INTO endereco.cidade VALUES (3597, '2019-10-23 14:05:31.168533', NULL, 'Arraial Do Cabo', 19);
INSERT INTO endereco.cidade VALUES (3599, '2019-10-23 14:05:31.168533', NULL, 'Barra Mansa', 19);
INSERT INTO endereco.cidade VALUES (3602, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Do Itabapoana', 19);
INSERT INTO endereco.cidade VALUES (3604, '2019-10-23 14:05:31.168533', NULL, 'Cachoeiras De Macacu', 19);
INSERT INTO endereco.cidade VALUES (3607, '2019-10-23 14:05:31.168533', NULL, 'Cantagalo', 19);
INSERT INTO endereco.cidade VALUES (3609, '2019-10-23 14:05:31.168533', NULL, 'Cardoso Moreira', 19);
INSERT INTO endereco.cidade VALUES (3612, '2019-10-23 14:05:31.168533', NULL, 'Comendador Levy Gasparian', 19);
INSERT INTO endereco.cidade VALUES (3614, '2019-10-23 14:05:31.168533', NULL, 'Cordeiro', 19);
INSERT INTO endereco.cidade VALUES (3616, '2019-10-23 14:05:31.168533', NULL, 'Duque De Caxias', 19);
INSERT INTO endereco.cidade VALUES (3619, '2019-10-23 14:05:31.168533', NULL, 'Iguaba Grande', 19);
INSERT INTO endereco.cidade VALUES (3622, '2019-10-23 14:05:31.168533', NULL, 'Italva', 19);
INSERT INTO endereco.cidade VALUES (3624, '2019-10-23 14:05:31.168533', NULL, 'Itaperuna', 19);
INSERT INTO endereco.cidade VALUES (3626, '2019-10-23 14:05:31.168533', NULL, 'Japeri', 19);
INSERT INTO endereco.cidade VALUES (3629, '2019-10-23 14:05:31.168533', NULL, 'Macuco', 19);
INSERT INTO endereco.cidade VALUES (3631, '2019-10-23 14:05:31.168533', NULL, 'Mangaratiba', 19);
INSERT INTO endereco.cidade VALUES (3633, '2019-10-23 14:05:31.168533', NULL, 'Mendes', 19);
INSERT INTO endereco.cidade VALUES (3635, '2019-10-23 14:05:31.168533', NULL, 'Miguel Pereira', 19);
INSERT INTO endereco.cidade VALUES (3638, '2019-10-23 14:05:31.168533', NULL, 'Nilópolis', 19);
INSERT INTO endereco.cidade VALUES (3640, '2019-10-23 14:05:31.168533', NULL, 'Nova Friburgo', 19);
INSERT INTO endereco.cidade VALUES (3643, '2019-10-23 14:05:31.168533', NULL, 'Paraíba Do Sul', 19);
INSERT INTO endereco.cidade VALUES (3645, '2019-10-23 14:05:31.168533', NULL, 'Paty Do Alferes', 19);
INSERT INTO endereco.cidade VALUES (3647, '2019-10-23 14:05:31.168533', NULL, 'Pinheiral', 19);
INSERT INTO endereco.cidade VALUES (3650, '2019-10-23 14:05:31.168533', NULL, 'Porto Real', 19);
INSERT INTO endereco.cidade VALUES (3651, '2019-10-23 14:05:31.168533', NULL, 'Quatis', 19);
INSERT INTO endereco.cidade VALUES (3652, '2019-10-23 14:05:31.168533', NULL, 'Queimados', 19);
INSERT INTO endereco.cidade VALUES (3654, '2019-10-23 14:05:31.168533', NULL, 'Resende', 19);
INSERT INTO endereco.cidade VALUES (3656, '2019-10-23 14:05:31.168533', NULL, 'Rio Claro', 19);
INSERT INTO endereco.cidade VALUES (3658, '2019-10-23 14:05:31.168533', NULL, 'Rio Das Ostras', 19);
INSERT INTO endereco.cidade VALUES (3661, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio De Pádua', 19);
INSERT INTO endereco.cidade VALUES (3663, '2019-10-23 14:05:31.168533', NULL, 'São Francisco De Itabapoana', 19);
INSERT INTO endereco.cidade VALUES (3666, '2019-10-23 14:05:31.168533', NULL, 'São João De Meriti', 19);
INSERT INTO endereco.cidade VALUES (3669, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Da Aldeia', 19);
INSERT INTO endereco.cidade VALUES (3672, '2019-10-23 14:05:31.168533', NULL, 'Saquarema', 19);
INSERT INTO endereco.cidade VALUES (3674, '2019-10-23 14:05:31.168533', NULL, 'Silva Jardim', 19);
INSERT INTO endereco.cidade VALUES (3677, '2019-10-23 14:05:31.168533', NULL, 'Teresópolis', 19);
INSERT INTO endereco.cidade VALUES (3679, '2019-10-23 14:05:31.168533', NULL, 'Três Rios', 19);
INSERT INTO endereco.cidade VALUES (3682, '2019-10-23 14:05:31.168533', NULL, 'Vassouras', 19);
INSERT INTO endereco.cidade VALUES (3683, '2019-10-23 14:05:31.168533', NULL, 'Volta Redonda', 19);
INSERT INTO endereco.cidade VALUES (3686, '2019-10-23 14:05:31.168533', NULL, 'Afonso Bezerra', 20);
INSERT INTO endereco.cidade VALUES (3689, '2019-10-23 14:05:31.168533', NULL, 'Almino Afonso', 20);
INSERT INTO endereco.cidade VALUES (3691, '2019-10-23 14:05:31.168533', NULL, 'Angicos', 20);
INSERT INTO endereco.cidade VALUES (3693, '2019-10-23 14:05:31.168533', NULL, 'Apodi', 20);
INSERT INTO endereco.cidade VALUES (3695, '2019-10-23 14:05:31.168533', NULL, 'Arês', 20);
INSERT INTO endereco.cidade VALUES (3697, '2019-10-23 14:05:31.168533', NULL, 'Baía Formosa', 20);
INSERT INTO endereco.cidade VALUES (3700, '2019-10-23 14:05:31.168533', NULL, 'Bento Fernandes', 20);
INSERT INTO endereco.cidade VALUES (3702, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus', 20);
INSERT INTO endereco.cidade VALUES (3704, '2019-10-23 14:05:31.168533', NULL, 'Caiçara Do Norte', 20);
INSERT INTO endereco.cidade VALUES (3707, '2019-10-23 14:05:31.168533', NULL, 'Campo Redondo', 20);
INSERT INTO endereco.cidade VALUES (3709, '2019-10-23 14:05:31.168533', NULL, 'Caraúbas', 20);
INSERT INTO endereco.cidade VALUES (3711, '2019-10-23 14:05:31.168533', NULL, 'Carnaubais', 20);
INSERT INTO endereco.cidade VALUES (3714, '2019-10-23 14:05:31.168533', NULL, 'Coronel Ezequiel', 20);
INSERT INTO endereco.cidade VALUES (3716, '2019-10-23 14:05:31.168533', NULL, 'Cruzeta', 20);
INSERT INTO endereco.cidade VALUES (3718, '2019-10-23 14:05:31.168533', NULL, 'Doutor Severiano', 20);
INSERT INTO endereco.cidade VALUES (3721, '2019-10-23 14:05:31.168533', NULL, 'Espírito Santo', 20);
INSERT INTO endereco.cidade VALUES (3723, '2019-10-23 14:05:31.168533', NULL, 'Felipe Guerra', 20);
INSERT INTO endereco.cidade VALUES (3725, '2019-10-23 14:05:31.168533', NULL, 'Florânia', 20);
INSERT INTO endereco.cidade VALUES (3727, '2019-10-23 14:05:31.168533', NULL, 'Frutuoso Gomes', 20);
INSERT INTO endereco.cidade VALUES (3730, '2019-10-23 14:05:31.168533', NULL, 'Governador Dix Sept Rosado', 20);
INSERT INTO endereco.cidade VALUES (3733, '2019-10-23 14:05:31.168533', NULL, 'Ielmo Marinho', 20);
INSERT INTO endereco.cidade VALUES (3735, '2019-10-23 14:05:31.168533', NULL, 'Ipueira', 20);
INSERT INTO endereco.cidade VALUES (3737, '2019-10-23 14:05:31.168533', NULL, 'Itaú', 20);
INSERT INTO endereco.cidade VALUES (3740, '2019-10-23 14:05:31.168533', NULL, 'Janduís', 20);
INSERT INTO endereco.cidade VALUES (3742, '2019-10-23 14:05:31.168533', NULL, 'Japi', 20);
INSERT INTO endereco.cidade VALUES (3744, '2019-10-23 14:05:31.168533', NULL, 'Jardim De Piranhas', 20);
INSERT INTO endereco.cidade VALUES (3746, '2019-10-23 14:05:31.168533', NULL, 'João Câmara', 20);
INSERT INTO endereco.cidade VALUES (3749, '2019-10-23 14:05:31.168533', NULL, 'Jucurutu', 20);
INSERT INTO endereco.cidade VALUES (3751, '2019-10-23 14:05:31.168533', NULL, 'Lagoa D`Anta', 20);
INSERT INTO endereco.cidade VALUES (3754, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Nova', 20);
INSERT INTO endereco.cidade VALUES (3756, '2019-10-23 14:05:31.168533', NULL, 'Lajes', 20);
INSERT INTO endereco.cidade VALUES (3758, '2019-10-23 14:05:31.168533', NULL, 'Lucrécia', 20);
INSERT INTO endereco.cidade VALUES (3760, '2019-10-23 14:05:31.168533', NULL, 'Macaíba', 20);
INSERT INTO endereco.cidade VALUES (3763, '2019-10-23 14:05:31.168533', NULL, 'Marcelino Vieira', 20);
INSERT INTO endereco.cidade VALUES (3765, '2019-10-23 14:05:31.168533', NULL, 'Maxaranguape', 20);
INSERT INTO endereco.cidade VALUES (3767, '2019-10-23 14:05:31.168533', NULL, 'Montanhas', 20);
INSERT INTO endereco.cidade VALUES (3769, '2019-10-23 14:05:31.168533', NULL, 'Monte Das Gameleiras', 20);
INSERT INTO endereco.cidade VALUES (3772, '2019-10-23 14:05:31.168533', NULL, 'Nísia Floresta', 20);
INSERT INTO endereco.cidade VALUES (3774, '2019-10-23 14:05:31.168533', NULL, 'Olho D`Água Do Borges', 20);
INSERT INTO endereco.cidade VALUES (3777, '2019-10-23 14:05:31.168533', NULL, 'Paraú', 20);
INSERT INTO endereco.cidade VALUES (3778, '2019-10-23 14:05:31.168533', NULL, 'Parazinho', 20);
INSERT INTO endereco.cidade VALUES (3780, '2019-10-23 14:05:31.168533', NULL, 'Parnamirim', 20);
INSERT INTO endereco.cidade VALUES (3782, '2019-10-23 14:05:31.168533', NULL, 'Passagem', 20);
INSERT INTO endereco.cidade VALUES (3784, '2019-10-23 14:05:31.168533', NULL, 'Pau Dos Ferros', 20);
INSERT INTO endereco.cidade VALUES (3787, '2019-10-23 14:05:31.168533', NULL, 'Pedro Avelino', 20);
INSERT INTO endereco.cidade VALUES (3790, '2019-10-23 14:05:31.168533', NULL, 'Pilões', 20);
INSERT INTO endereco.cidade VALUES (3791, '2019-10-23 14:05:31.168533', NULL, 'Poço Branco', 20);
INSERT INTO endereco.cidade VALUES (3794, '2019-10-23 14:05:31.168533', NULL, 'Presidente Juscelino', 20);
INSERT INTO endereco.cidade VALUES (3797, '2019-10-23 14:05:31.168533', NULL, 'Rafael Godeiro', 20);
INSERT INTO endereco.cidade VALUES (3799, '2019-10-23 14:05:31.168533', NULL, 'Riacho De Santana', 20);
INSERT INTO endereco.cidade VALUES (3802, '2019-10-23 14:05:31.168533', NULL, 'Rodolfo Fernandes', 20);
INSERT INTO endereco.cidade VALUES (3804, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz', 20);
INSERT INTO endereco.cidade VALUES (3806, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Matos', 20);
INSERT INTO endereco.cidade VALUES (3808, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio', 20);
INSERT INTO endereco.cidade VALUES (3811, '2019-10-23 14:05:31.168533', NULL, 'São Fernando', 20);
INSERT INTO endereco.cidade VALUES (3813, '2019-10-23 14:05:31.168533', NULL, 'São Gonçalo Do Amarante', 20);
INSERT INTO endereco.cidade VALUES (3816, '2019-10-23 14:05:31.168533', NULL, 'São José Do Campestre', 20);
INSERT INTO endereco.cidade VALUES (3818, '2019-10-23 14:05:31.168533', NULL, 'São Miguel', 20);
INSERT INTO endereco.cidade VALUES (3820, '2019-10-23 14:05:31.168533', NULL, 'São Paulo Do Potengi', 20);
INSERT INTO endereco.cidade VALUES (3823, '2019-10-23 14:05:31.168533', NULL, 'São Tomé', 20);
INSERT INTO endereco.cidade VALUES (3825, '2019-10-23 14:05:31.168533', NULL, 'Senador Elói De Souza', 20);
INSERT INTO endereco.cidade VALUES (3828, '2019-10-23 14:05:31.168533', NULL, 'Serra Do Mel', 20);
INSERT INTO endereco.cidade VALUES (3830, '2019-10-23 14:05:31.168533', NULL, 'Serrinha', 20);
INSERT INTO endereco.cidade VALUES (3832, '2019-10-23 14:05:31.168533', NULL, 'Severiano Melo', 20);
INSERT INTO endereco.cidade VALUES (3835, '2019-10-23 14:05:31.168533', NULL, 'Taipu', 20);
INSERT INTO endereco.cidade VALUES (3837, '2019-10-23 14:05:31.168533', NULL, 'Tenente Ananias', 20);
INSERT INTO endereco.cidade VALUES (3839, '2019-10-23 14:05:31.168533', NULL, 'Tibau', 20);
INSERT INTO endereco.cidade VALUES (3841, '2019-10-23 14:05:31.168533', NULL, 'Timbaúba Dos Batistas', 20);
INSERT INTO endereco.cidade VALUES (3844, '2019-10-23 14:05:31.168533', NULL, 'Umarizal', 20);
INSERT INTO endereco.cidade VALUES (3846, '2019-10-23 14:05:31.168533', NULL, 'Várzea', 20);
INSERT INTO endereco.cidade VALUES (3849, '2019-10-23 14:05:31.168533', NULL, 'Viçosa', 20);
INSERT INTO endereco.cidade VALUES (3851, '2019-10-23 14:05:31.168533', NULL, 'Alta Floresta D`Oeste', 21);
INSERT INTO endereco.cidade VALUES (3853, '2019-10-23 14:05:31.168533', NULL, 'Alto Paraíso', 21);
INSERT INTO endereco.cidade VALUES (3856, '2019-10-23 14:05:31.168533', NULL, 'Buritis', 21);
INSERT INTO endereco.cidade VALUES (3858, '2019-10-23 14:05:31.168533', NULL, 'Cacaulândia', 21);
INSERT INTO endereco.cidade VALUES (3860, '2019-10-23 14:05:31.168533', NULL, 'Campo Novo De Rondônia', 21);
INSERT INTO endereco.cidade VALUES (3863, '2019-10-23 14:05:31.168533', NULL, 'Cerejeiras', 21);
INSERT INTO endereco.cidade VALUES (3865, '2019-10-23 14:05:31.168533', NULL, 'Colorado Do Oeste', 21);
INSERT INTO endereco.cidade VALUES (3868, '2019-10-23 14:05:31.168533', NULL, 'Cujubim', 21);
INSERT INTO endereco.cidade VALUES (3870, '2019-10-23 14:05:31.168533', NULL, 'Governador Jorge Teixeira', 21);
INSERT INTO endereco.cidade VALUES (3873, '2019-10-23 14:05:31.168533', NULL, 'Jaru', 21);
INSERT INTO endereco.cidade VALUES (3875, '2019-10-23 14:05:31.168533', NULL, 'Machadinho D`Oeste', 21);
INSERT INTO endereco.cidade VALUES (3877, '2019-10-23 14:05:31.168533', NULL, 'Mirante Da Serra', 21);
INSERT INTO endereco.cidade VALUES (3880, '2019-10-23 14:05:31.168533', NULL, 'Nova Mamoré', 21);
INSERT INTO endereco.cidade VALUES (3882, '2019-10-23 14:05:31.168533', NULL, 'Novo Horizonte Do Oeste', 21);
INSERT INTO endereco.cidade VALUES (3885, '2019-10-23 14:05:31.168533', NULL, 'Pimenta Bueno', 21);
INSERT INTO endereco.cidade VALUES (3887, '2019-10-23 14:05:31.168533', NULL, 'Porto Velho', 21);
INSERT INTO endereco.cidade VALUES (3889, '2019-10-23 14:05:31.168533', NULL, 'Primavera De Rondônia', 21);
INSERT INTO endereco.cidade VALUES (3892, '2019-10-23 14:05:31.168533', NULL, 'Santa Luzia D`Oeste', 21);
INSERT INTO endereco.cidade VALUES (3894, '2019-10-23 14:05:31.168533', NULL, 'São Francisco Do Guaporé', 21);
INSERT INTO endereco.cidade VALUES (3897, '2019-10-23 14:05:31.168533', NULL, 'Teixeirópolis', 21);
INSERT INTO endereco.cidade VALUES (3900, '2019-10-23 14:05:31.168533', NULL, 'Vale Do Anari', 21);
INSERT INTO endereco.cidade VALUES (3901, '2019-10-23 14:05:31.168533', NULL, 'Vale Do Paraíso', 21);
INSERT INTO endereco.cidade VALUES (3904, '2019-10-23 14:05:31.168533', NULL, 'Amajari', 22);
INSERT INTO endereco.cidade VALUES (3906, '2019-10-23 14:05:31.168533', NULL, 'Bonfim', 22);
INSERT INTO endereco.cidade VALUES (3908, '2019-10-23 14:05:31.168533', NULL, 'Caracaraí', 22);
INSERT INTO endereco.cidade VALUES (3910, '2019-10-23 14:05:31.168533', NULL, 'Iracema', 22);
INSERT INTO endereco.cidade VALUES (3913, '2019-10-23 14:05:31.168533', NULL, 'Pacaraima', 22);
INSERT INTO endereco.cidade VALUES (3915, '2019-10-23 14:05:31.168533', NULL, 'São João Da Baliza', 22);
INSERT INTO endereco.cidade VALUES (3918, '2019-10-23 14:05:31.168533', NULL, 'Aceguá', 23);
INSERT INTO endereco.cidade VALUES (3920, '2019-10-23 14:05:31.168533', NULL, 'Agudo', 23);
INSERT INTO endereco.cidade VALUES (3922, '2019-10-23 14:05:31.168533', NULL, 'Alecrim', 23);
INSERT INTO endereco.cidade VALUES (3924, '2019-10-23 14:05:31.168533', NULL, 'Alegria', 23);
INSERT INTO endereco.cidade VALUES (3926, '2019-10-23 14:05:31.168533', NULL, 'Alpestre', 23);
INSERT INTO endereco.cidade VALUES (3928, '2019-10-23 14:05:31.168533', NULL, 'Alto Feliz', 23);
INSERT INTO endereco.cidade VALUES (4109, '2019-10-23 14:05:31.168533', NULL, 'Herveiras', 23);
INSERT INTO endereco.cidade VALUES (3931, '2019-10-23 14:05:31.168533', NULL, 'Ametista Do Sul', 23);
INSERT INTO endereco.cidade VALUES (3933, '2019-10-23 14:05:31.168533', NULL, 'Anta Gorda', 23);
INSERT INTO endereco.cidade VALUES (3935, '2019-10-23 14:05:31.168533', NULL, 'Arambaré', 23);
INSERT INTO endereco.cidade VALUES (3938, '2019-10-23 14:05:31.168533', NULL, 'Arroio Do Meio', 23);
INSERT INTO endereco.cidade VALUES (3940, '2019-10-23 14:05:31.168533', NULL, 'Arroio Do Sal', 23);
INSERT INTO endereco.cidade VALUES (3943, '2019-10-23 14:05:31.168533', NULL, 'Arroio Grande', 23);
INSERT INTO endereco.cidade VALUES (3945, '2019-10-23 14:05:31.168533', NULL, 'Augusto Pestana', 23);
INSERT INTO endereco.cidade VALUES (3948, '2019-10-23 14:05:31.168533', NULL, 'Balneário Pinhal', 23);
INSERT INTO endereco.cidade VALUES (3950, '2019-10-23 14:05:31.168533', NULL, 'Barão De Cotegipe', 23);
INSERT INTO endereco.cidade VALUES (3952, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Guarita', 23);
INSERT INTO endereco.cidade VALUES (3955, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Rio Azul', 23);
INSERT INTO endereco.cidade VALUES (3958, '2019-10-23 14:05:31.168533', NULL, 'Barros Cassal', 23);
INSERT INTO endereco.cidade VALUES (3960, '2019-10-23 14:05:31.168533', NULL, 'Bento Gonçalves', 23);
INSERT INTO endereco.cidade VALUES (3962, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista Do Buricá', 23);
INSERT INTO endereco.cidade VALUES (3965, '2019-10-23 14:05:31.168533', NULL, 'Boa Vista Do Sul', 23);
INSERT INTO endereco.cidade VALUES (3967, '2019-10-23 14:05:31.168533', NULL, 'Bom Princípio', 23);
INSERT INTO endereco.cidade VALUES (3970, '2019-10-23 14:05:31.168533', NULL, 'Boqueirão Do Leão', 23);
INSERT INTO endereco.cidade VALUES (3972, '2019-10-23 14:05:31.168533', NULL, 'Bozano', 23);
INSERT INTO endereco.cidade VALUES (3975, '2019-10-23 14:05:31.168533', NULL, 'Butiá', 23);
INSERT INTO endereco.cidade VALUES (3977, '2019-10-23 14:05:31.168533', NULL, 'Cacequi', 23);
INSERT INTO endereco.cidade VALUES (3979, '2019-10-23 14:05:31.168533', NULL, 'Cachoeirinha', 23);
INSERT INTO endereco.cidade VALUES (3982, '2019-10-23 14:05:31.168533', NULL, 'Caiçara', 23);
INSERT INTO endereco.cidade VALUES (3984, '2019-10-23 14:05:31.168533', NULL, 'Camargo', 23);
INSERT INTO endereco.cidade VALUES (3986, '2019-10-23 14:05:31.168533', NULL, 'Campestre Da Serra', 23);
INSERT INTO endereco.cidade VALUES (3988, '2019-10-23 14:05:31.168533', NULL, 'Campinas Do Sul', 23);
INSERT INTO endereco.cidade VALUES (3991, '2019-10-23 14:05:31.168533', NULL, 'Campos Borges', 23);
INSERT INTO endereco.cidade VALUES (3993, '2019-10-23 14:05:31.168533', NULL, 'Cândido Godói', 23);
INSERT INTO endereco.cidade VALUES (3996, '2019-10-23 14:05:31.168533', NULL, 'Canguçu', 23);
INSERT INTO endereco.cidade VALUES (3998, '2019-10-23 14:05:31.168533', NULL, 'Canudos Do Vale', 23);
INSERT INTO endereco.cidade VALUES (4000, '2019-10-23 14:05:31.168533', NULL, 'Capão Da Canoa', 23);
INSERT INTO endereco.cidade VALUES (4003, '2019-10-23 14:05:31.168533', NULL, 'Capela De Santana', 23);
INSERT INTO endereco.cidade VALUES (4005, '2019-10-23 14:05:31.168533', NULL, 'Capivari Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4008, '2019-10-23 14:05:31.168533', NULL, 'Carlos Barbosa', 23);
INSERT INTO endereco.cidade VALUES (4010, '2019-10-23 14:05:31.168533', NULL, 'Casca', 23);
INSERT INTO endereco.cidade VALUES (4013, '2019-10-23 14:05:31.168533', NULL, 'Caxias Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4015, '2019-10-23 14:05:31.168533', NULL, 'Cerrito', 23);
INSERT INTO endereco.cidade VALUES (4017, '2019-10-23 14:05:31.168533', NULL, 'Cerro Grande', 23);
INSERT INTO endereco.cidade VALUES (4019, '2019-10-23 14:05:31.168533', NULL, 'Cerro Largo', 23);
INSERT INTO endereco.cidade VALUES (4022, '2019-10-23 14:05:31.168533', NULL, 'Charrua', 23);
INSERT INTO endereco.cidade VALUES (4024, '2019-10-23 14:05:31.168533', NULL, 'Chuí', 23);
INSERT INTO endereco.cidade VALUES (4026, '2019-10-23 14:05:31.168533', NULL, 'Cidreira', 23);
INSERT INTO endereco.cidade VALUES (4027, '2019-10-23 14:05:31.168533', NULL, 'Ciríaco', 23);
INSERT INTO endereco.cidade VALUES (4029, '2019-10-23 14:05:31.168533', NULL, 'Colorado', 23);
INSERT INTO endereco.cidade VALUES (4031, '2019-10-23 14:05:31.168533', NULL, 'Constantina', 23);
INSERT INTO endereco.cidade VALUES (4033, '2019-10-23 14:05:31.168533', NULL, 'Coqueiros Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4035, '2019-10-23 14:05:31.168533', NULL, 'Coronel Bicaco', 23);
INSERT INTO endereco.cidade VALUES (4038, '2019-10-23 14:05:31.168533', NULL, 'Coxilha', 23);
INSERT INTO endereco.cidade VALUES (4040, '2019-10-23 14:05:31.168533', NULL, 'Cristal', 23);
INSERT INTO endereco.cidade VALUES (4042, '2019-10-23 14:05:31.168533', NULL, 'Cruz Alta', 23);
INSERT INTO endereco.cidade VALUES (4044, '2019-10-23 14:05:31.168533', NULL, 'Cruzeiro Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4047, '2019-10-23 14:05:31.168533', NULL, 'Dezesseis De Novembro', 23);
INSERT INTO endereco.cidade VALUES (4049, '2019-10-23 14:05:31.168533', NULL, 'Dois Irmãos', 23);
INSERT INTO endereco.cidade VALUES (4052, '2019-10-23 14:05:31.168533', NULL, 'Dom Feliciano', 23);
INSERT INTO endereco.cidade VALUES (4054, '2019-10-23 14:05:31.168533', NULL, 'Dom Pedro De Alcântara', 23);
INSERT INTO endereco.cidade VALUES (4057, '2019-10-23 14:05:31.168533', NULL, 'Doutor Ricardo', 23);
INSERT INTO endereco.cidade VALUES (4060, '2019-10-23 14:05:31.168533', NULL, 'Encruzilhada Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4062, '2019-10-23 14:05:31.168533', NULL, 'Entre Rios Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4064, '2019-10-23 14:05:31.168533', NULL, 'Erebango', 23);
INSERT INTO endereco.cidade VALUES (4067, '2019-10-23 14:05:31.168533', NULL, 'Erval Grande', 23);
INSERT INTO endereco.cidade VALUES (4069, '2019-10-23 14:05:31.168533', NULL, 'Esmeralda', 23);
INSERT INTO endereco.cidade VALUES (4071, '2019-10-23 14:05:31.168533', NULL, 'Espumoso', 23);
INSERT INTO endereco.cidade VALUES (4073, '2019-10-23 14:05:31.168533', NULL, 'Estância Velha', 23);
INSERT INTO endereco.cidade VALUES (4076, '2019-10-23 14:05:31.168533', NULL, 'Estrela Velha', 23);
INSERT INTO endereco.cidade VALUES (4078, '2019-10-23 14:05:31.168533', NULL, 'Fagundes Varela', 23);
INSERT INTO endereco.cidade VALUES (4081, '2019-10-23 14:05:31.168533', NULL, 'Faxinalzinho', 23);
INSERT INTO endereco.cidade VALUES (4083, '2019-10-23 14:05:31.168533', NULL, 'Feliz', 23);
INSERT INTO endereco.cidade VALUES (4085, '2019-10-23 14:05:31.168533', NULL, 'Floriano Peixoto', 23);
INSERT INTO endereco.cidade VALUES (4088, '2019-10-23 14:05:31.168533', NULL, 'Forquetinha', 23);
INSERT INTO endereco.cidade VALUES (4090, '2019-10-23 14:05:31.168533', NULL, 'Frederico Westphalen', 23);
INSERT INTO endereco.cidade VALUES (4093, '2019-10-23 14:05:31.168533', NULL, 'Gaurama', 23);
INSERT INTO endereco.cidade VALUES (4095, '2019-10-23 14:05:31.168533', NULL, 'Gentil', 23);
INSERT INTO endereco.cidade VALUES (4097, '2019-10-23 14:05:31.168533', NULL, 'Giruá', 23);
INSERT INTO endereco.cidade VALUES (4100, '2019-10-23 14:05:31.168533', NULL, 'Gramado Dos Loureiros', 23);
INSERT INTO endereco.cidade VALUES (4102, '2019-10-23 14:05:31.168533', NULL, 'Gravataí', 23);
INSERT INTO endereco.cidade VALUES (4104, '2019-10-23 14:05:31.168533', NULL, 'Guaíba', 23);
INSERT INTO endereco.cidade VALUES (4106, '2019-10-23 14:05:31.168533', NULL, 'Guarani Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4112, '2019-10-23 14:05:31.168533', NULL, 'Humaitá', 23);
INSERT INTO endereco.cidade VALUES (4114, '2019-10-23 14:05:31.168533', NULL, 'Ibiaçá', 23);
INSERT INTO endereco.cidade VALUES (4116, '2019-10-23 14:05:31.168533', NULL, 'Ibirapuitã', 23);
INSERT INTO endereco.cidade VALUES (4118, '2019-10-23 14:05:31.168533', NULL, 'Igrejinha', 23);
INSERT INTO endereco.cidade VALUES (4121, '2019-10-23 14:05:31.168533', NULL, 'Imbé', 23);
INSERT INTO endereco.cidade VALUES (4123, '2019-10-23 14:05:31.168533', NULL, 'Independência', 23);
INSERT INTO endereco.cidade VALUES (4126, '2019-10-23 14:05:31.168533', NULL, 'Ipiranga Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4128, '2019-10-23 14:05:31.168533', NULL, 'Itaara', 23);
INSERT INTO endereco.cidade VALUES (4130, '2019-10-23 14:05:31.168533', NULL, 'Itapuca', 23);
INSERT INTO endereco.cidade VALUES (4133, '2019-10-23 14:05:31.168533', NULL, 'Itatiba Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4135, '2019-10-23 14:05:31.168533', NULL, 'Ivoti', 23);
INSERT INTO endereco.cidade VALUES (4137, '2019-10-23 14:05:31.168533', NULL, 'Jacuizinho', 23);
INSERT INTO endereco.cidade VALUES (4140, '2019-10-23 14:05:31.168533', NULL, 'Jaguari', 23);
INSERT INTO endereco.cidade VALUES (4142, '2019-10-23 14:05:31.168533', NULL, 'Jari', 23);
INSERT INTO endereco.cidade VALUES (4144, '2019-10-23 14:05:31.168533', NULL, 'Júlio De Castilhos', 23);
INSERT INTO endereco.cidade VALUES (4146, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Dos Três Cantos', 23);
INSERT INTO endereco.cidade VALUES (4149, '2019-10-23 14:05:31.168533', NULL, 'Lajeado', 23);
INSERT INTO endereco.cidade VALUES (4151, '2019-10-23 14:05:31.168533', NULL, 'Lavras Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4154, '2019-10-23 14:05:31.168533', NULL, 'Linha Nova', 23);
INSERT INTO endereco.cidade VALUES (4155, '2019-10-23 14:05:31.168533', NULL, 'Maçambara', 23);
INSERT INTO endereco.cidade VALUES (4157, '2019-10-23 14:05:31.168533', NULL, 'Mampituba', 23);
INSERT INTO endereco.cidade VALUES (4159, '2019-10-23 14:05:31.168533', NULL, 'Maquiné', 23);
INSERT INTO endereco.cidade VALUES (4162, '2019-10-23 14:05:31.168533', NULL, 'Marcelino Ramos', 23);
INSERT INTO endereco.cidade VALUES (4164, '2019-10-23 14:05:31.168533', NULL, 'Mariano Moro', 23);
INSERT INTO endereco.cidade VALUES (4167, '2019-10-23 14:05:31.168533', NULL, 'Mato Castelhano', 23);
INSERT INTO endereco.cidade VALUES (4169, '2019-10-23 14:05:31.168533', NULL, 'Mato Queimado', 23);
INSERT INTO endereco.cidade VALUES (4172, '2019-10-23 14:05:31.168533', NULL, 'Miraguaí', 23);
INSERT INTO endereco.cidade VALUES (4174, '2019-10-23 14:05:31.168533', NULL, 'Monte Alegre Dos Campos', 23);
INSERT INTO endereco.cidade VALUES (4177, '2019-10-23 14:05:31.168533', NULL, 'Mormaço', 23);
INSERT INTO endereco.cidade VALUES (4179, '2019-10-23 14:05:31.168533', NULL, 'Morro Redondo', 23);
INSERT INTO endereco.cidade VALUES (4181, '2019-10-23 14:05:31.168533', NULL, 'Mostardas', 23);
INSERT INTO endereco.cidade VALUES (4183, '2019-10-23 14:05:31.168533', NULL, 'Muitos Capões', 23);
INSERT INTO endereco.cidade VALUES (4186, '2019-10-23 14:05:31.168533', NULL, 'Nicolau Vergueiro', 23);
INSERT INTO endereco.cidade VALUES (4188, '2019-10-23 14:05:31.168533', NULL, 'Nova Alvorada', 23);
INSERT INTO endereco.cidade VALUES (4191, '2019-10-23 14:05:31.168533', NULL, 'Nova Boa Vista', 23);
INSERT INTO endereco.cidade VALUES (4193, '2019-10-23 14:05:31.168533', NULL, 'Nova Candelária', 23);
INSERT INTO endereco.cidade VALUES (4196, '2019-10-23 14:05:31.168533', NULL, 'Nova Pádua', 23);
INSERT INTO endereco.cidade VALUES (4198, '2019-10-23 14:05:31.168533', NULL, 'Nova Petrópolis', 23);
INSERT INTO endereco.cidade VALUES (4200, '2019-10-23 14:05:31.168533', NULL, 'Nova Ramada', 23);
INSERT INTO endereco.cidade VALUES (4202, '2019-10-23 14:05:31.168533', NULL, 'Nova Santa Rita', 23);
INSERT INTO endereco.cidade VALUES (4205, '2019-10-23 14:05:31.168533', NULL, 'Novo Hamburgo', 23);
INSERT INTO endereco.cidade VALUES (4207, '2019-10-23 14:05:31.168533', NULL, 'Novo Tiradentes', 23);
INSERT INTO endereco.cidade VALUES (4210, '2019-10-23 14:05:31.168533', NULL, 'Paim Filho', 23);
INSERT INTO endereco.cidade VALUES (4212, '2019-10-23 14:05:31.168533', NULL, 'Palmeira Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4215, '2019-10-23 14:05:31.168533', NULL, 'Pantano Grande', 23);
INSERT INTO endereco.cidade VALUES (4217, '2019-10-23 14:05:31.168533', NULL, 'Paraíso Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4220, '2019-10-23 14:05:31.168533', NULL, 'Passa Sete', 23);
INSERT INTO endereco.cidade VALUES (4221, '2019-10-23 14:05:31.168533', NULL, 'Passo Do Sobrado', 23);
INSERT INTO endereco.cidade VALUES (4224, '2019-10-23 14:05:31.168533', NULL, 'Paverama', 23);
INSERT INTO endereco.cidade VALUES (4226, '2019-10-23 14:05:31.168533', NULL, 'Pedro Osório', 23);
INSERT INTO endereco.cidade VALUES (4229, '2019-10-23 14:05:31.168533', NULL, 'Picada Café', 23);
INSERT INTO endereco.cidade VALUES (4231, '2019-10-23 14:05:31.168533', NULL, 'Pinhal Da Serra', 23);
INSERT INTO endereco.cidade VALUES (4234, '2019-10-23 14:05:31.168533', NULL, 'Pinheiro Machado', 23);
INSERT INTO endereco.cidade VALUES (4236, '2019-10-23 14:05:31.168533', NULL, 'Piratini', 23);
INSERT INTO endereco.cidade VALUES (4238, '2019-10-23 14:05:31.168533', NULL, 'Poço Das Antas', 23);
INSERT INTO endereco.cidade VALUES (4241, '2019-10-23 14:05:31.168533', NULL, 'Portão', 23);
INSERT INTO endereco.cidade VALUES (4243, '2019-10-23 14:05:31.168533', NULL, 'Porto Lucena', 23);
INSERT INTO endereco.cidade VALUES (4245, '2019-10-23 14:05:31.168533', NULL, 'Porto Vera Cruz', 23);
INSERT INTO endereco.cidade VALUES (4248, '2019-10-23 14:05:31.168533', NULL, 'Presidente Lucena', 23);
INSERT INTO endereco.cidade VALUES (4250, '2019-10-23 14:05:31.168533', NULL, 'Protásio Alves', 23);
INSERT INTO endereco.cidade VALUES (4253, '2019-10-23 14:05:31.168533', NULL, 'Quatro Irmãos', 23);
INSERT INTO endereco.cidade VALUES (4255, '2019-10-23 14:05:31.168533', NULL, 'Quinze De Novembro', 23);
INSERT INTO endereco.cidade VALUES (4258, '2019-10-23 14:05:31.168533', NULL, 'Restinga Seca', 23);
INSERT INTO endereco.cidade VALUES (4260, '2019-10-23 14:05:31.168533', NULL, 'Rio Grande', 23);
INSERT INTO endereco.cidade VALUES (4262, '2019-10-23 14:05:31.168533', NULL, 'Riozinho', 23);
INSERT INTO endereco.cidade VALUES (4265, '2019-10-23 14:05:31.168533', NULL, 'Rolador', 23);
INSERT INTO endereco.cidade VALUES (4267, '2019-10-23 14:05:31.168533', NULL, 'Ronda Alta', 23);
INSERT INTO endereco.cidade VALUES (4269, '2019-10-23 14:05:31.168533', NULL, 'Roque Gonzales', 23);
INSERT INTO endereco.cidade VALUES (4272, '2019-10-23 14:05:31.168533', NULL, 'Saldanha Marinho', 23);
INSERT INTO endereco.cidade VALUES (4274, '2019-10-23 14:05:31.168533', NULL, 'Salvador Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4277, '2019-10-23 14:05:31.168533', NULL, 'Santa Bárbara Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4279, '2019-10-23 14:05:31.168533', NULL, 'Santa Clara Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4280, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4281, '2019-10-23 14:05:31.168533', NULL, 'Santa Margarida Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4283, '2019-10-23 14:05:31.168533', NULL, 'Santa Maria Do Herval', 23);
INSERT INTO endereco.cidade VALUES (4286, '2019-10-23 14:05:31.168533', NULL, 'Santa Vitória Do Palmar', 23);
INSERT INTO endereco.cidade VALUES (4288, '2019-10-23 14:05:31.168533', NULL, 'Santana Do Livramento', 23);
INSERT INTO endereco.cidade VALUES (4291, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Da Patrulha', 23);
INSERT INTO endereco.cidade VALUES (4293, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Palma', 23);
INSERT INTO endereco.cidade VALUES (4295, '2019-10-23 14:05:31.168533', NULL, 'Santo Augusto', 23);
INSERT INTO endereco.cidade VALUES (4298, '2019-10-23 14:05:31.168533', NULL, 'São Borja', 23);
INSERT INTO endereco.cidade VALUES (4300, '2019-10-23 14:05:31.168533', NULL, 'São Francisco De Assis', 23);
INSERT INTO endereco.cidade VALUES (4303, '2019-10-23 14:05:31.168533', NULL, 'São Jerônimo', 23);
INSERT INTO endereco.cidade VALUES (4305, '2019-10-23 14:05:31.168533', NULL, 'São João Do Polêsine', 23);
INSERT INTO endereco.cidade VALUES (4308, '2019-10-23 14:05:31.168533', NULL, 'São José Do Herval', 23);
INSERT INTO endereco.cidade VALUES (4310, '2019-10-23 14:05:31.168533', NULL, 'São José Do Inhacorá', 23);
INSERT INTO endereco.cidade VALUES (4313, '2019-10-23 14:05:31.168533', NULL, 'São José Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4316, '2019-10-23 14:05:31.168533', NULL, 'São Lourenço Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4319, '2019-10-23 14:05:31.168533', NULL, 'São Martinho', 23);
INSERT INTO endereco.cidade VALUES (4321, '2019-10-23 14:05:31.168533', NULL, 'São Miguel Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4528, '2019-10-23 14:05:31.168533', NULL, 'Indaial', 24);
INSERT INTO endereco.cidade VALUES (4323, '2019-10-23 14:05:31.168533', NULL, 'São Paulo Das Missões', 23);
INSERT INTO endereco.cidade VALUES (4326, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Do Butiá', 23);
INSERT INTO endereco.cidade VALUES (4329, '2019-10-23 14:05:31.168533', NULL, 'São Sepé', 23);
INSERT INTO endereco.cidade VALUES (4331, '2019-10-23 14:05:31.168533', NULL, 'São Valentim Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4334, '2019-10-23 14:05:31.168533', NULL, 'São Vicente Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4336, '2019-10-23 14:05:31.168533', NULL, 'Sapucaia Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4339, '2019-10-23 14:05:31.168533', NULL, 'Sede Nova', 23);
INSERT INTO endereco.cidade VALUES (4341, '2019-10-23 14:05:31.168533', NULL, 'Selbach', 23);
INSERT INTO endereco.cidade VALUES (4343, '2019-10-23 14:05:31.168533', NULL, 'Sentinela Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4346, '2019-10-23 14:05:31.168533', NULL, 'Sertão', 23);
INSERT INTO endereco.cidade VALUES (4348, '2019-10-23 14:05:31.168533', NULL, 'Sete De Setembro', 23);
INSERT INTO endereco.cidade VALUES (4350, '2019-10-23 14:05:31.168533', NULL, 'Silveira Martins', 23);
INSERT INTO endereco.cidade VALUES (4353, '2019-10-23 14:05:31.168533', NULL, 'Soledade', 23);
INSERT INTO endereco.cidade VALUES (4355, '2019-10-23 14:05:31.168533', NULL, 'Tapejara', 23);
INSERT INTO endereco.cidade VALUES (4358, '2019-10-23 14:05:31.168533', NULL, 'Taquara', 23);
INSERT INTO endereco.cidade VALUES (4360, '2019-10-23 14:05:31.168533', NULL, 'Taquaruçu Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4362, '2019-10-23 14:05:31.168533', NULL, 'Tenente Portela', 23);
INSERT INTO endereco.cidade VALUES (4365, '2019-10-23 14:05:31.168533', NULL, 'Tio Hugo', 23);
INSERT INTO endereco.cidade VALUES (4367, '2019-10-23 14:05:31.168533', NULL, 'Toropi', 23);
INSERT INTO endereco.cidade VALUES (4369, '2019-10-23 14:05:31.168533', NULL, 'Tramandaí', 23);
INSERT INTO endereco.cidade VALUES (4371, '2019-10-23 14:05:31.168533', NULL, 'Três Arroios', 23);
INSERT INTO endereco.cidade VALUES (4374, '2019-10-23 14:05:31.168533', NULL, 'Três De Maio', 23);
INSERT INTO endereco.cidade VALUES (4376, '2019-10-23 14:05:31.168533', NULL, 'Três Palmeiras', 23);
INSERT INTO endereco.cidade VALUES (4379, '2019-10-23 14:05:31.168533', NULL, 'Triunfo', 23);
INSERT INTO endereco.cidade VALUES (4381, '2019-10-23 14:05:31.168533', NULL, 'Tunas', 23);
INSERT INTO endereco.cidade VALUES (4383, '2019-10-23 14:05:31.168533', NULL, 'Tupanciretã', 23);
INSERT INTO endereco.cidade VALUES (4386, '2019-10-23 14:05:31.168533', NULL, 'Turuçu', 23);
INSERT INTO endereco.cidade VALUES (4388, '2019-10-23 14:05:31.168533', NULL, 'União Da Serra', 23);
INSERT INTO endereco.cidade VALUES (4390, '2019-10-23 14:05:31.168533', NULL, 'Uruguaiana', 23);
INSERT INTO endereco.cidade VALUES (4393, '2019-10-23 14:05:31.168533', NULL, 'Vale Real', 23);
INSERT INTO endereco.cidade VALUES (4395, '2019-10-23 14:05:31.168533', NULL, 'Vanini', 23);
INSERT INTO endereco.cidade VALUES (4397, '2019-10-23 14:05:31.168533', NULL, 'Vera Cruz', 23);
INSERT INTO endereco.cidade VALUES (4399, '2019-10-23 14:05:31.168533', NULL, 'Vespasiano Correa', 23);
INSERT INTO endereco.cidade VALUES (4402, '2019-10-23 14:05:31.168533', NULL, 'Vicente Dutra', 23);
INSERT INTO endereco.cidade VALUES (4403, '2019-10-23 14:05:31.168533', NULL, 'Victor Graeff', 23);
INSERT INTO endereco.cidade VALUES (4405, '2019-10-23 14:05:31.168533', NULL, 'Vila Lângaro', 23);
INSERT INTO endereco.cidade VALUES (4407, '2019-10-23 14:05:31.168533', NULL, 'Vila Nova Do Sul', 23);
INSERT INTO endereco.cidade VALUES (4410, '2019-10-23 14:05:31.168533', NULL, 'Vista Gaúcha', 23);
INSERT INTO endereco.cidade VALUES (4413, '2019-10-23 14:05:31.168533', NULL, 'Xangri Lá', 23);
INSERT INTO endereco.cidade VALUES (4415, '2019-10-23 14:05:31.168533', NULL, 'Abelardo Luz', 24);
INSERT INTO endereco.cidade VALUES (4417, '2019-10-23 14:05:31.168533', NULL, 'Agronômica', 24);
INSERT INTO endereco.cidade VALUES (4419, '2019-10-23 14:05:31.168533', NULL, 'Águas De Chapecó', 24);
INSERT INTO endereco.cidade VALUES (4422, '2019-10-23 14:05:31.168533', NULL, 'Alfredo Wagner', 24);
INSERT INTO endereco.cidade VALUES (4424, '2019-10-23 14:05:31.168533', NULL, 'Anchieta', 24);
INSERT INTO endereco.cidade VALUES (4426, '2019-10-23 14:05:31.168533', NULL, 'Anita Garibaldi', 24);
INSERT INTO endereco.cidade VALUES (4429, '2019-10-23 14:05:31.168533', NULL, 'Apiúna', 24);
INSERT INTO endereco.cidade VALUES (4431, '2019-10-23 14:05:31.168533', NULL, 'Araquari', 24);
INSERT INTO endereco.cidade VALUES (4434, '2019-10-23 14:05:31.168533', NULL, 'Arroio Trinta', 24);
INSERT INTO endereco.cidade VALUES (4436, '2019-10-23 14:05:31.168533', NULL, 'Ascurra', 24);
INSERT INTO endereco.cidade VALUES (4438, '2019-10-23 14:05:31.168533', NULL, 'Aurora', 24);
INSERT INTO endereco.cidade VALUES (4440, '2019-10-23 14:05:31.168533', NULL, 'Balneário Barra Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4443, '2019-10-23 14:05:31.168533', NULL, 'Balneáreo Piçarras', 24);
INSERT INTO endereco.cidade VALUES (4446, '2019-10-23 14:05:31.168533', NULL, 'Barra Velha', 24);
INSERT INTO endereco.cidade VALUES (4448, '2019-10-23 14:05:31.168533', NULL, 'Belmonte', 24);
INSERT INTO endereco.cidade VALUES (4450, '2019-10-23 14:05:31.168533', NULL, 'Biguaçu', 24);
INSERT INTO endereco.cidade VALUES (4452, '2019-10-23 14:05:31.168533', NULL, 'Bocaina Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4455, '2019-10-23 14:05:31.168533', NULL, 'Bom Jesus Do Oeste', 24);
INSERT INTO endereco.cidade VALUES (4458, '2019-10-23 14:05:31.168533', NULL, 'Botuverá', 24);
INSERT INTO endereco.cidade VALUES (4460, '2019-10-23 14:05:31.168533', NULL, 'Braço Do Trombudo', 24);
INSERT INTO endereco.cidade VALUES (4462, '2019-10-23 14:05:31.168533', NULL, 'Brusque', 24);
INSERT INTO endereco.cidade VALUES (4465, '2019-10-23 14:05:31.168533', NULL, 'Calmon', 24);
INSERT INTO endereco.cidade VALUES (4467, '2019-10-23 14:05:31.168533', NULL, 'Campo Alegre', 24);
INSERT INTO endereco.cidade VALUES (4469, '2019-10-23 14:05:31.168533', NULL, 'Campo Erê', 24);
INSERT INTO endereco.cidade VALUES (4472, '2019-10-23 14:05:31.168533', NULL, 'Canoinhas', 24);
INSERT INTO endereco.cidade VALUES (4474, '2019-10-23 14:05:31.168533', NULL, 'Capinzal', 24);
INSERT INTO endereco.cidade VALUES (4476, '2019-10-23 14:05:31.168533', NULL, 'Catanduvas', 24);
INSERT INTO endereco.cidade VALUES (4478, '2019-10-23 14:05:31.168533', NULL, 'Celso Ramos', 24);
INSERT INTO endereco.cidade VALUES (4480, '2019-10-23 14:05:31.168533', NULL, 'Chapadão Do Lageado', 24);
INSERT INTO endereco.cidade VALUES (4483, '2019-10-23 14:05:31.168533', NULL, 'Concórdia', 24);
INSERT INTO endereco.cidade VALUES (4485, '2019-10-23 14:05:31.168533', NULL, 'Coronel Freitas', 24);
INSERT INTO endereco.cidade VALUES (4487, '2019-10-23 14:05:31.168533', NULL, 'Correia Pinto', 24);
INSERT INTO endereco.cidade VALUES (4490, '2019-10-23 14:05:31.168533', NULL, 'Cunha Porã', 24);
INSERT INTO endereco.cidade VALUES (4492, '2019-10-23 14:05:31.168533', NULL, 'Curitibanos', 24);
INSERT INTO endereco.cidade VALUES (4494, '2019-10-23 14:05:31.168533', NULL, 'Dionísio Cerqueira', 24);
INSERT INTO endereco.cidade VALUES (4497, '2019-10-23 14:05:31.168533', NULL, 'Entre Rios', 24);
INSERT INTO endereco.cidade VALUES (4500, '2019-10-23 14:05:31.168533', NULL, 'Faxinal Dos Guedes', 24);
INSERT INTO endereco.cidade VALUES (4502, '2019-10-23 14:05:31.168533', NULL, 'Florianópolis', 24);
INSERT INTO endereco.cidade VALUES (4504, '2019-10-23 14:05:31.168533', NULL, 'Forquilhinha', 24);
INSERT INTO endereco.cidade VALUES (4507, '2019-10-23 14:05:31.168533', NULL, 'Galvão', 24);
INSERT INTO endereco.cidade VALUES (4509, '2019-10-23 14:05:31.168533', NULL, 'Garuva', 24);
INSERT INTO endereco.cidade VALUES (4511, '2019-10-23 14:05:31.168533', NULL, 'Governador Celso Ramos', 24);
INSERT INTO endereco.cidade VALUES (4514, '2019-10-23 14:05:31.168533', NULL, 'Guabiruba', 24);
INSERT INTO endereco.cidade VALUES (4516, '2019-10-23 14:05:31.168533', NULL, 'Guaramirim', 24);
INSERT INTO endereco.cidade VALUES (4518, '2019-10-23 14:05:31.168533', NULL, 'Guatambú', 24);
INSERT INTO endereco.cidade VALUES (4521, '2019-10-23 14:05:31.168533', NULL, 'Ibicaré', 24);
INSERT INTO endereco.cidade VALUES (4523, '2019-10-23 14:05:31.168533', NULL, 'Içara', 24);
INSERT INTO endereco.cidade VALUES (4525, '2019-10-23 14:05:31.168533', NULL, 'Imaruí', 24);
INSERT INTO endereco.cidade VALUES (4530, '2019-10-23 14:05:31.168533', NULL, 'Ipira', 24);
INSERT INTO endereco.cidade VALUES (4531, '2019-10-23 14:05:31.168533', NULL, 'Iporã Do Oeste', 24);
INSERT INTO endereco.cidade VALUES (4534, '2019-10-23 14:05:31.168533', NULL, 'Iraceminha', 24);
INSERT INTO endereco.cidade VALUES (4536, '2019-10-23 14:05:31.168533', NULL, 'Irati', 24);
INSERT INTO endereco.cidade VALUES (4539, '2019-10-23 14:05:31.168533', NULL, 'Itaiópolis', 24);
INSERT INTO endereco.cidade VALUES (4541, '2019-10-23 14:05:31.168533', NULL, 'Itapema', 24);
INSERT INTO endereco.cidade VALUES (4543, '2019-10-23 14:05:31.168533', NULL, 'Itapoá', 24);
INSERT INTO endereco.cidade VALUES (4546, '2019-10-23 14:05:31.168533', NULL, 'Jacinto Machado', 24);
INSERT INTO endereco.cidade VALUES (4548, '2019-10-23 14:05:31.168533', NULL, 'Jaraguá Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4550, '2019-10-23 14:05:31.168533', NULL, 'Joaçaba', 24);
INSERT INTO endereco.cidade VALUES (4553, '2019-10-23 14:05:31.168533', NULL, 'Jupiá', 24);
INSERT INTO endereco.cidade VALUES (4555, '2019-10-23 14:05:31.168533', NULL, 'Lages', 24);
INSERT INTO endereco.cidade VALUES (4557, '2019-10-23 14:05:31.168533', NULL, 'Lajeado Grande', 24);
INSERT INTO endereco.cidade VALUES (4560, '2019-10-23 14:05:31.168533', NULL, 'Lebon Régis', 24);
INSERT INTO endereco.cidade VALUES (4562, '2019-10-23 14:05:31.168533', NULL, 'Lindóia Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4565, '2019-10-23 14:05:31.168533', NULL, 'Luzerna', 24);
INSERT INTO endereco.cidade VALUES (4567, '2019-10-23 14:05:31.168533', NULL, 'Mafra', 24);
INSERT INTO endereco.cidade VALUES (4569, '2019-10-23 14:05:31.168533', NULL, 'Major Vieira', 24);
INSERT INTO endereco.cidade VALUES (4572, '2019-10-23 14:05:31.168533', NULL, 'Marema', 24);
INSERT INTO endereco.cidade VALUES (4574, '2019-10-23 14:05:31.168533', NULL, 'Matos Costa', 24);
INSERT INTO endereco.cidade VALUES (4576, '2019-10-23 14:05:31.168533', NULL, 'Mirim Doce', 24);
INSERT INTO endereco.cidade VALUES (4578, '2019-10-23 14:05:31.168533', NULL, 'Mondaí', 24);
INSERT INTO endereco.cidade VALUES (4580, '2019-10-23 14:05:31.168533', NULL, 'Monte Castelo', 24);
INSERT INTO endereco.cidade VALUES (4583, '2019-10-23 14:05:31.168533', NULL, 'Navegantes', 24);
INSERT INTO endereco.cidade VALUES (4585, '2019-10-23 14:05:31.168533', NULL, 'Nova Itaberaba', 24);
INSERT INTO endereco.cidade VALUES (4588, '2019-10-23 14:05:31.168533', NULL, 'Novo Horizonte', 24);
INSERT INTO endereco.cidade VALUES (4590, '2019-10-23 14:05:31.168533', NULL, 'Otacílio Costa', 24);
INSERT INTO endereco.cidade VALUES (4593, '2019-10-23 14:05:31.168533', NULL, 'Paial', 24);
INSERT INTO endereco.cidade VALUES (4595, '2019-10-23 14:05:31.168533', NULL, 'Palhoça', 24);
INSERT INTO endereco.cidade VALUES (4597, '2019-10-23 14:05:31.168533', NULL, 'Palmeira', 24);
INSERT INTO endereco.cidade VALUES (4600, '2019-10-23 14:05:31.168533', NULL, 'Paraíso', 24);
INSERT INTO endereco.cidade VALUES (4602, '2019-10-23 14:05:31.168533', NULL, 'Passos Maia', 24);
INSERT INTO endereco.cidade VALUES (4604, '2019-10-23 14:05:31.168533', NULL, 'Pedras Grandes', 24);
INSERT INTO endereco.cidade VALUES (4607, '2019-10-23 14:05:31.168533', NULL, 'Petrolândia', 24);
INSERT INTO endereco.cidade VALUES (4609, '2019-10-23 14:05:31.168533', NULL, 'Pinheiro Preto', 24);
INSERT INTO endereco.cidade VALUES (4611, '2019-10-23 14:05:31.168533', NULL, 'Planalto Alegre', 24);
INSERT INTO endereco.cidade VALUES (4614, '2019-10-23 14:05:31.168533', NULL, 'Ponte Alta Do Norte', 24);
INSERT INTO endereco.cidade VALUES (4616, '2019-10-23 14:05:31.168533', NULL, 'Porto Belo', 24);
INSERT INTO endereco.cidade VALUES (4618, '2019-10-23 14:05:31.168533', NULL, 'Pouso Redondo', 24);
INSERT INTO endereco.cidade VALUES (4621, '2019-10-23 14:05:31.168533', NULL, 'Presidente Getúlio', 24);
INSERT INTO endereco.cidade VALUES (4623, '2019-10-23 14:05:31.168533', NULL, 'Princesa', 24);
INSERT INTO endereco.cidade VALUES (4625, '2019-10-23 14:05:31.168533', NULL, 'Rancho Queimado', 24);
INSERT INTO endereco.cidade VALUES (4628, '2019-10-23 14:05:31.168533', NULL, 'Rio Do Oeste', 24);
INSERT INTO endereco.cidade VALUES (4630, '2019-10-23 14:05:31.168533', NULL, 'Rio Dos Cedros', 24);
INSERT INTO endereco.cidade VALUES (4633, '2019-10-23 14:05:31.168533', NULL, 'Rio Rufino', 24);
INSERT INTO endereco.cidade VALUES (4635, '2019-10-23 14:05:31.168533', NULL, 'Rodeio', 24);
INSERT INTO endereco.cidade VALUES (4638, '2019-10-23 14:05:31.168533', NULL, 'Saltinho', 24);
INSERT INTO endereco.cidade VALUES (4640, '2019-10-23 14:05:31.168533', NULL, 'Sangão', 24);
INSERT INTO endereco.cidade VALUES (4642, '2019-10-23 14:05:31.168533', NULL, 'Santa Helena', 24);
INSERT INTO endereco.cidade VALUES (4644, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4647, '2019-10-23 14:05:31.168533', NULL, 'Santiago Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4827, '2019-10-23 14:05:31.168533', NULL, 'Aspásia', 26);
INSERT INTO endereco.cidade VALUES (4649, '2019-10-23 14:05:31.168533', NULL, 'São Bento Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4652, '2019-10-23 14:05:31.168533', NULL, 'São Carlos', 24);
INSERT INTO endereco.cidade VALUES (4654, '2019-10-23 14:05:31.168533', NULL, 'São Domingos', 24);
INSERT INTO endereco.cidade VALUES (4656, '2019-10-23 14:05:31.168533', NULL, 'São João Batista', 24);
INSERT INTO endereco.cidade VALUES (4657, '2019-10-23 14:05:31.168533', NULL, 'São João Do Itaperiú', 24);
INSERT INTO endereco.cidade VALUES (4659, '2019-10-23 14:05:31.168533', NULL, 'São João Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4662, '2019-10-23 14:05:31.168533', NULL, 'São José Do Cedro', 24);
INSERT INTO endereco.cidade VALUES (4664, '2019-10-23 14:05:31.168533', NULL, 'São Lourenço Do Oeste', 24);
INSERT INTO endereco.cidade VALUES (4666, '2019-10-23 14:05:31.168533', NULL, 'São Martinho', 24);
INSERT INTO endereco.cidade VALUES (4669, '2019-10-23 14:05:31.168533', NULL, 'São Pedro De Alcântara', 24);
INSERT INTO endereco.cidade VALUES (4672, '2019-10-23 14:05:31.168533', NULL, 'Seara', 24);
INSERT INTO endereco.cidade VALUES (4674, '2019-10-23 14:05:31.168533', NULL, 'Siderópolis', 24);
INSERT INTO endereco.cidade VALUES (4676, '2019-10-23 14:05:31.168533', NULL, 'Sul Brasil', 24);
INSERT INTO endereco.cidade VALUES (4679, '2019-10-23 14:05:31.168533', NULL, 'Tigrinhos', 24);
INSERT INTO endereco.cidade VALUES (4681, '2019-10-23 14:05:31.168533', NULL, 'Timbé Do Sul', 24);
INSERT INTO endereco.cidade VALUES (4683, '2019-10-23 14:05:31.168533', NULL, 'Timbó Grande', 24);
INSERT INTO endereco.cidade VALUES (4686, '2019-10-23 14:05:31.168533', NULL, 'Treze De Maio', 24);
INSERT INTO endereco.cidade VALUES (4688, '2019-10-23 14:05:31.168533', NULL, 'Trombudo Central', 24);
INSERT INTO endereco.cidade VALUES (4691, '2019-10-23 14:05:31.168533', NULL, 'Turvo', 24);
INSERT INTO endereco.cidade VALUES (4693, '2019-10-23 14:05:31.168533', NULL, 'Urubici', 24);
INSERT INTO endereco.cidade VALUES (4695, '2019-10-23 14:05:31.168533', NULL, 'Urussanga', 24);
INSERT INTO endereco.cidade VALUES (4698, '2019-10-23 14:05:31.168533', NULL, 'Vargem Bonita', 24);
INSERT INTO endereco.cidade VALUES (4700, '2019-10-23 14:05:31.168533', NULL, 'Videira', 24);
INSERT INTO endereco.cidade VALUES (4702, '2019-10-23 14:05:31.168533', NULL, 'Witmarsum', 24);
INSERT INTO endereco.cidade VALUES (4704, '2019-10-23 14:05:31.168533', NULL, 'Xavantina', 24);
INSERT INTO endereco.cidade VALUES (4707, '2019-10-23 14:05:31.168533', NULL, 'Amparo De São Francisco', 25);
INSERT INTO endereco.cidade VALUES (4709, '2019-10-23 14:05:31.168533', NULL, 'Aracaju', 25);
INSERT INTO endereco.cidade VALUES (4711, '2019-10-23 14:05:31.168533', NULL, 'Areia Branca', 25);
INSERT INTO endereco.cidade VALUES (4714, '2019-10-23 14:05:31.168533', NULL, 'Brejo Grande', 25);
INSERT INTO endereco.cidade VALUES (4716, '2019-10-23 14:05:31.168533', NULL, 'Canhoba', 25);
INSERT INTO endereco.cidade VALUES (4719, '2019-10-23 14:05:31.168533', NULL, 'Carira', 25);
INSERT INTO endereco.cidade VALUES (4721, '2019-10-23 14:05:31.168533', NULL, 'Cedro De São João', 25);
INSERT INTO endereco.cidade VALUES (4723, '2019-10-23 14:05:31.168533', NULL, 'Cumbe', 25);
INSERT INTO endereco.cidade VALUES (4725, '2019-10-23 14:05:31.168533', NULL, 'Estância', 25);
INSERT INTO endereco.cidade VALUES (4728, '2019-10-23 14:05:31.168533', NULL, 'Gararu', 25);
INSERT INTO endereco.cidade VALUES (4730, '2019-10-23 14:05:31.168533', NULL, 'Gracho Cardoso', 25);
INSERT INTO endereco.cidade VALUES (4732, '2019-10-23 14:05:31.168533', NULL, 'Indiaroba', 25);
INSERT INTO endereco.cidade VALUES (4735, '2019-10-23 14:05:31.168533', NULL, 'Itabi', 25);
INSERT INTO endereco.cidade VALUES (4737, '2019-10-23 14:05:31.168533', NULL, 'Japaratuba', 25);
INSERT INTO endereco.cidade VALUES (4739, '2019-10-23 14:05:31.168533', NULL, 'Lagarto', 25);
INSERT INTO endereco.cidade VALUES (4742, '2019-10-23 14:05:31.168533', NULL, 'Malhada Dos Bois', 25);
INSERT INTO endereco.cidade VALUES (4744, '2019-10-23 14:05:31.168533', NULL, 'Maruim', 25);
INSERT INTO endereco.cidade VALUES (4746, '2019-10-23 14:05:31.168533', NULL, 'Monte Alegre De Sergipe', 25);
INSERT INTO endereco.cidade VALUES (4749, '2019-10-23 14:05:31.168533', NULL, 'Nossa Senhora Aparecida', 25);
INSERT INTO endereco.cidade VALUES (4751, '2019-10-23 14:05:31.168533', NULL, 'Nossa Senhora Das Dores', 25);
INSERT INTO endereco.cidade VALUES (4754, '2019-10-23 14:05:31.168533', NULL, 'Pacatuba', 25);
INSERT INTO endereco.cidade VALUES (4756, '2019-10-23 14:05:31.168533', NULL, 'Pedrinhas', 25);
INSERT INTO endereco.cidade VALUES (4759, '2019-10-23 14:05:31.168533', NULL, 'Poço Redondo', 25);
INSERT INTO endereco.cidade VALUES (4761, '2019-10-23 14:05:31.168533', NULL, 'Porto Da Folha', 25);
INSERT INTO endereco.cidade VALUES (4763, '2019-10-23 14:05:31.168533', NULL, 'Riachão Do Dantas', 25);
INSERT INTO endereco.cidade VALUES (4766, '2019-10-23 14:05:31.168533', NULL, 'Rosário Do Catete', 25);
INSERT INTO endereco.cidade VALUES (4768, '2019-10-23 14:05:31.168533', NULL, 'Santa Luzia Do Itanhy', 25);
INSERT INTO endereco.cidade VALUES (4771, '2019-10-23 14:05:31.168533', NULL, 'Santo Amaro Das Brotas', 25);
INSERT INTO endereco.cidade VALUES (4773, '2019-10-23 14:05:31.168533', NULL, 'São Domingos', 25);
INSERT INTO endereco.cidade VALUES (4776, '2019-10-23 14:05:31.168533', NULL, 'Simão Dias', 25);
INSERT INTO endereco.cidade VALUES (4778, '2019-10-23 14:05:31.168533', NULL, 'Telha', 25);
INSERT INTO endereco.cidade VALUES (4780, '2019-10-23 14:05:31.168533', NULL, 'Tomar Do Geru', 25);
INSERT INTO endereco.cidade VALUES (4782, '2019-10-23 14:05:31.168533', NULL, 'Adamantina', 26);
INSERT INTO endereco.cidade VALUES (4783, '2019-10-23 14:05:31.168533', NULL, 'Adolfo', 26);
INSERT INTO endereco.cidade VALUES (4785, '2019-10-23 14:05:31.168533', NULL, 'Águas Da Prata', 26);
INSERT INTO endereco.cidade VALUES (4787, '2019-10-23 14:05:31.168533', NULL, 'Águas De Santa Bárbara', 26);
INSERT INTO endereco.cidade VALUES (4790, '2019-10-23 14:05:31.168533', NULL, 'Alambari', 26);
INSERT INTO endereco.cidade VALUES (4792, '2019-10-23 14:05:31.168533', NULL, 'Altair', 26);
INSERT INTO endereco.cidade VALUES (4795, '2019-10-23 14:05:31.168533', NULL, 'Alumínio', 26);
INSERT INTO endereco.cidade VALUES (4796, '2019-10-23 14:05:31.168533', NULL, 'Álvares Florence', 26);
INSERT INTO endereco.cidade VALUES (4799, '2019-10-23 14:05:31.168533', NULL, 'Alvinlândia', 26);
INSERT INTO endereco.cidade VALUES (4801, '2019-10-23 14:05:31.168533', NULL, 'Américo Brasiliense', 26);
INSERT INTO endereco.cidade VALUES (4804, '2019-10-23 14:05:31.168533', NULL, 'Analândia', 26);
INSERT INTO endereco.cidade VALUES (4806, '2019-10-23 14:05:31.168533', NULL, 'Angatuba', 26);
INSERT INTO endereco.cidade VALUES (4809, '2019-10-23 14:05:31.168533', NULL, 'Aparecida', 26);
INSERT INTO endereco.cidade VALUES (4811, '2019-10-23 14:05:31.168533', NULL, 'Apiaí', 26);
INSERT INTO endereco.cidade VALUES (4813, '2019-10-23 14:05:31.168533', NULL, 'Araçatuba', 26);
INSERT INTO endereco.cidade VALUES (4815, '2019-10-23 14:05:31.168533', NULL, 'Aramina', 26);
INSERT INTO endereco.cidade VALUES (4818, '2019-10-23 14:05:31.168533', NULL, 'Araraquara', 26);
INSERT INTO endereco.cidade VALUES (4820, '2019-10-23 14:05:31.168533', NULL, 'Arco Íris', 26);
INSERT INTO endereco.cidade VALUES (4822, '2019-10-23 14:05:31.168533', NULL, 'Areias', 26);
INSERT INTO endereco.cidade VALUES (4824, '2019-10-23 14:05:31.168533', NULL, 'Ariranha', 26);
INSERT INTO endereco.cidade VALUES (4829, '2019-10-23 14:05:31.168533', NULL, 'Atibaia', 26);
INSERT INTO endereco.cidade VALUES (4831, '2019-10-23 14:05:31.168533', NULL, 'Avaí', 26);
INSERT INTO endereco.cidade VALUES (4834, '2019-10-23 14:05:31.168533', NULL, 'Bady Bassitt', 26);
INSERT INTO endereco.cidade VALUES (4836, '2019-10-23 14:05:31.168533', NULL, 'Bálsamo', 26);
INSERT INTO endereco.cidade VALUES (4838, '2019-10-23 14:05:31.168533', NULL, 'Barão De Antonina', 26);
INSERT INTO endereco.cidade VALUES (4841, '2019-10-23 14:05:31.168533', NULL, 'Barra Bonita', 26);
INSERT INTO endereco.cidade VALUES (4843, '2019-10-23 14:05:31.168533', NULL, 'Barra Do Turvo', 26);
INSERT INTO endereco.cidade VALUES (4846, '2019-10-23 14:05:31.168533', NULL, 'Barueri', 26);
INSERT INTO endereco.cidade VALUES (4848, '2019-10-23 14:05:31.168533', NULL, 'Batatais', 26);
INSERT INTO endereco.cidade VALUES (4850, '2019-10-23 14:05:31.168533', NULL, 'Bebedouro', 26);
INSERT INTO endereco.cidade VALUES (4852, '2019-10-23 14:05:31.168533', NULL, 'Bernardino De Campos', 26);
INSERT INTO endereco.cidade VALUES (4855, '2019-10-23 14:05:31.168533', NULL, 'Birigui', 26);
INSERT INTO endereco.cidade VALUES (4857, '2019-10-23 14:05:31.168533', NULL, 'Boa Esperança Do Sul', 26);
INSERT INTO endereco.cidade VALUES (4860, '2019-10-23 14:05:31.168533', NULL, 'Boituva', 26);
INSERT INTO endereco.cidade VALUES (4862, '2019-10-23 14:05:31.168533', NULL, 'Bom Sucesso De Itararé', 26);
INSERT INTO endereco.cidade VALUES (4865, '2019-10-23 14:05:31.168533', NULL, 'Borborema', 26);
INSERT INTO endereco.cidade VALUES (4867, '2019-10-23 14:05:31.168533', NULL, 'Botucatu', 26);
INSERT INTO endereco.cidade VALUES (4869, '2019-10-23 14:05:31.168533', NULL, 'Braúna', 26);
INSERT INTO endereco.cidade VALUES (4872, '2019-10-23 14:05:31.168533', NULL, 'Brotas', 26);
INSERT INTO endereco.cidade VALUES (4874, '2019-10-23 14:05:31.168533', NULL, 'Buritama', 26);
INSERT INTO endereco.cidade VALUES (4876, '2019-10-23 14:05:31.168533', NULL, 'Cabrália Paulista', 26);
INSERT INTO endereco.cidade VALUES (4879, '2019-10-23 14:05:31.168533', NULL, 'Cachoeira Paulista', 26);
INSERT INTO endereco.cidade VALUES (4881, '2019-10-23 14:05:31.168533', NULL, 'Cafelândia', 26);
INSERT INTO endereco.cidade VALUES (4883, '2019-10-23 14:05:31.168533', NULL, 'Caieiras', 26);
INSERT INTO endereco.cidade VALUES (4886, '2019-10-23 14:05:31.168533', NULL, 'Cajati', 26);
INSERT INTO endereco.cidade VALUES (4888, '2019-10-23 14:05:31.168533', NULL, 'Cajuru', 26);
INSERT INTO endereco.cidade VALUES (4890, '2019-10-23 14:05:31.168533', NULL, 'Campinas', 26);
INSERT INTO endereco.cidade VALUES (4892, '2019-10-23 14:05:31.168533', NULL, 'Campos Do Jordão', 26);
INSERT INTO endereco.cidade VALUES (4895, '2019-10-23 14:05:31.168533', NULL, 'Canas', 26);
INSERT INTO endereco.cidade VALUES (4897, '2019-10-23 14:05:31.168533', NULL, 'Cândido Rodrigues', 26);
INSERT INTO endereco.cidade VALUES (4899, '2019-10-23 14:05:31.168533', NULL, 'Capão Bonito', 26);
INSERT INTO endereco.cidade VALUES (4902, '2019-10-23 14:05:31.168533', NULL, 'Caraguatatuba', 26);
INSERT INTO endereco.cidade VALUES (4904, '2019-10-23 14:05:31.168533', NULL, 'Cardoso', 26);
INSERT INTO endereco.cidade VALUES (4906, '2019-10-23 14:05:31.168533', NULL, 'Cássia Dos Coqueiros', 26);
INSERT INTO endereco.cidade VALUES (4909, '2019-10-23 14:05:31.168533', NULL, 'Catiguá', 26);
INSERT INTO endereco.cidade VALUES (4911, '2019-10-23 14:05:31.168533', NULL, 'Cerqueira César', 26);
INSERT INTO endereco.cidade VALUES (4912, '2019-10-23 14:05:31.168533', NULL, 'Cerquilho', 26);
INSERT INTO endereco.cidade VALUES (4914, '2019-10-23 14:05:31.168533', NULL, 'Charqueada', 26);
INSERT INTO endereco.cidade VALUES (4917, '2019-10-23 14:05:31.168533', NULL, 'Colina', 26);
INSERT INTO endereco.cidade VALUES (4919, '2019-10-23 14:05:31.168533', NULL, 'Conchal', 26);
INSERT INTO endereco.cidade VALUES (4921, '2019-10-23 14:05:31.168533', NULL, 'Cordeirópolis', 26);
INSERT INTO endereco.cidade VALUES (4923, '2019-10-23 14:05:31.168533', NULL, 'Coronel Macedo', 26);
INSERT INTO endereco.cidade VALUES (4926, '2019-10-23 14:05:31.168533', NULL, 'Cosmorama', 26);
INSERT INTO endereco.cidade VALUES (4929, '2019-10-23 14:05:31.168533', NULL, 'Cristais Paulista', 26);
INSERT INTO endereco.cidade VALUES (4931, '2019-10-23 14:05:31.168533', NULL, 'Cruzeiro', 26);
INSERT INTO endereco.cidade VALUES (4933, '2019-10-23 14:05:31.168533', NULL, 'Cunha', 26);
INSERT INTO endereco.cidade VALUES (4936, '2019-10-23 14:05:31.168533', NULL, 'Dirce Reis', 26);
INSERT INTO endereco.cidade VALUES (4938, '2019-10-23 14:05:31.168533', NULL, 'Dobrada', 26);
INSERT INTO endereco.cidade VALUES (4940, '2019-10-23 14:05:31.168533', NULL, 'Dolcinópolis', 26);
INSERT INTO endereco.cidade VALUES (4942, '2019-10-23 14:05:31.168533', NULL, 'Dracena', 26);
INSERT INTO endereco.cidade VALUES (4945, '2019-10-23 14:05:31.168533', NULL, 'Echaporã', 26);
INSERT INTO endereco.cidade VALUES (4947, '2019-10-23 14:05:31.168533', NULL, 'Elias Fausto', 26);
INSERT INTO endereco.cidade VALUES (4949, '2019-10-23 14:05:31.168533', NULL, 'Embaúba', 26);
INSERT INTO endereco.cidade VALUES (4952, '2019-10-23 14:05:31.168533', NULL, 'Emilianópolis', 26);
INSERT INTO endereco.cidade VALUES (4954, '2019-10-23 14:05:31.168533', NULL, 'Espírito Santo Do Pinhal', 26);
INSERT INTO endereco.cidade VALUES (4956, '2019-10-23 14:05:31.168533', NULL, 'Estiva Gerbi', 26);
INSERT INTO endereco.cidade VALUES (4959, '2019-10-23 14:05:31.168533', NULL, 'Euclides Da Cunha Paulista', 26);
INSERT INTO endereco.cidade VALUES (4961, '2019-10-23 14:05:31.168533', NULL, 'Fernando Prestes', 26);
INSERT INTO endereco.cidade VALUES (4964, '2019-10-23 14:05:31.168533', NULL, 'Ferraz De Vasconcelos', 26);
INSERT INTO endereco.cidade VALUES (4967, '2019-10-23 14:05:31.168533', NULL, 'Florínia', 26);
INSERT INTO endereco.cidade VALUES (4969, '2019-10-23 14:05:31.168533', NULL, 'Franca', 26);
INSERT INTO endereco.cidade VALUES (4971, '2019-10-23 14:05:31.168533', NULL, 'Franco Da Rocha', 26);
INSERT INTO endereco.cidade VALUES (4974, '2019-10-23 14:05:31.168533', NULL, 'Garça', 26);
INSERT INTO endereco.cidade VALUES (4976, '2019-10-23 14:05:31.168533', NULL, 'Gavião Peixoto', 26);
INSERT INTO endereco.cidade VALUES (4978, '2019-10-23 14:05:31.168533', NULL, 'Getulina', 26);
INSERT INTO endereco.cidade VALUES (4981, '2019-10-23 14:05:31.168533', NULL, 'Guaimbê', 26);
INSERT INTO endereco.cidade VALUES (4983, '2019-10-23 14:05:31.168533', NULL, 'Guapiaçu', 26);
INSERT INTO endereco.cidade VALUES (4985, '2019-10-23 14:05:31.168533', NULL, 'Guará', 26);
INSERT INTO endereco.cidade VALUES (4987, '2019-10-23 14:05:31.168533', NULL, 'Guaraci', 26);
INSERT INTO endereco.cidade VALUES (4990, '2019-10-23 14:05:31.168533', NULL, 'Guararapes', 26);
INSERT INTO endereco.cidade VALUES (4992, '2019-10-23 14:05:31.168533', NULL, 'Guaratinguetá', 26);
INSERT INTO endereco.cidade VALUES (4994, '2019-10-23 14:05:31.168533', NULL, 'Guariba', 26);
INSERT INTO endereco.cidade VALUES (4997, '2019-10-23 14:05:31.168533', NULL, 'Guatapará', 26);
INSERT INTO endereco.cidade VALUES (4999, '2019-10-23 14:05:31.168533', NULL, 'Herculândia', 26);
INSERT INTO endereco.cidade VALUES (5001, '2019-10-23 14:05:31.168533', NULL, 'Hortolândia', 26);
INSERT INTO endereco.cidade VALUES (5004, '2019-10-23 14:05:31.168533', NULL, 'Iaras', 26);
INSERT INTO endereco.cidade VALUES (5006, '2019-10-23 14:05:31.168533', NULL, 'Ibirá', 26);
INSERT INTO endereco.cidade VALUES (5008, '2019-10-23 14:05:31.168533', NULL, 'Ibitinga', 26);
INSERT INTO endereco.cidade VALUES (5011, '2019-10-23 14:05:31.168533', NULL, 'Iepê', 26);
INSERT INTO endereco.cidade VALUES (5013, '2019-10-23 14:05:31.168533', NULL, 'Igarapava', 26);
INSERT INTO endereco.cidade VALUES (5015, '2019-10-23 14:05:31.168533', NULL, 'Iguape', 26);
INSERT INTO endereco.cidade VALUES (5017, '2019-10-23 14:05:31.168533', NULL, 'Ilha Solteira', 26);
INSERT INTO endereco.cidade VALUES (5020, '2019-10-23 14:05:31.168533', NULL, 'Indiana', 26);
INSERT INTO endereco.cidade VALUES (5022, '2019-10-23 14:05:31.168533', NULL, 'Inúbia Paulista', 26);
INSERT INTO endereco.cidade VALUES (5025, '2019-10-23 14:05:31.168533', NULL, 'Ipeúna', 26);
INSERT INTO endereco.cidade VALUES (5027, '2019-10-23 14:05:31.168533', NULL, 'Iporanga', 26);
INSERT INTO endereco.cidade VALUES (5029, '2019-10-23 14:05:31.168533', NULL, 'Iracemápolis', 26);
INSERT INTO endereco.cidade VALUES (5032, '2019-10-23 14:05:31.168533', NULL, 'Itaberá', 26);
INSERT INTO endereco.cidade VALUES (5034, '2019-10-23 14:05:31.168533', NULL, 'Itajobi', 26);
INSERT INTO endereco.cidade VALUES (5036, '2019-10-23 14:05:31.168533', NULL, 'Itanhaém', 26);
INSERT INTO endereco.cidade VALUES (5038, '2019-10-23 14:05:31.168533', NULL, 'Itapecerica Da Serra', 26);
INSERT INTO endereco.cidade VALUES (5041, '2019-10-23 14:05:31.168533', NULL, 'Itapevi', 26);
INSERT INTO endereco.cidade VALUES (5042, '2019-10-23 14:05:31.168533', NULL, 'Itapira', 26);
INSERT INTO endereco.cidade VALUES (5045, '2019-10-23 14:05:31.168533', NULL, 'Itaporanga', 26);
INSERT INTO endereco.cidade VALUES (5047, '2019-10-23 14:05:31.168533', NULL, 'Itapura', 26);
INSERT INTO endereco.cidade VALUES (5049, '2019-10-23 14:05:31.168533', NULL, 'Itararé', 26);
INSERT INTO endereco.cidade VALUES (5051, '2019-10-23 14:05:31.168533', NULL, 'Itatiba', 26);
INSERT INTO endereco.cidade VALUES (5053, '2019-10-23 14:05:31.168533', NULL, 'Itirapina', 26);
INSERT INTO endereco.cidade VALUES (5056, '2019-10-23 14:05:31.168533', NULL, 'Itu', 26);
INSERT INTO endereco.cidade VALUES (5058, '2019-10-23 14:05:31.168533', NULL, 'Ituverava', 26);
INSERT INTO endereco.cidade VALUES (5060, '2019-10-23 14:05:31.168533', NULL, 'Jaboticabal', 26);
INSERT INTO endereco.cidade VALUES (5062, '2019-10-23 14:05:31.168533', NULL, 'Jaci', 26);
INSERT INTO endereco.cidade VALUES (5065, '2019-10-23 14:05:31.168533', NULL, 'Jales', 26);
INSERT INTO endereco.cidade VALUES (5067, '2019-10-23 14:05:31.168533', NULL, 'Jandira', 26);
INSERT INTO endereco.cidade VALUES (5069, '2019-10-23 14:05:31.168533', NULL, 'Jarinu', 26);
INSERT INTO endereco.cidade VALUES (5072, '2019-10-23 14:05:31.168533', NULL, 'Joanópolis', 26);
INSERT INTO endereco.cidade VALUES (5074, '2019-10-23 14:05:31.168533', NULL, 'José Bonifácio', 26);
INSERT INTO endereco.cidade VALUES (5076, '2019-10-23 14:05:31.168533', NULL, 'Jumirim', 26);
INSERT INTO endereco.cidade VALUES (5078, '2019-10-23 14:05:31.168533', NULL, 'Junqueirópolis', 26);
INSERT INTO endereco.cidade VALUES (5081, '2019-10-23 14:05:31.168533', NULL, 'Lagoinha', 26);
INSERT INTO endereco.cidade VALUES (5083, '2019-10-23 14:05:31.168533', NULL, 'Lavínia', 26);
INSERT INTO endereco.cidade VALUES (5086, '2019-10-23 14:05:31.168533', NULL, 'Lençóis Paulista', 26);
INSERT INTO endereco.cidade VALUES (5088, '2019-10-23 14:05:31.168533', NULL, 'Lindóia', 26);
INSERT INTO endereco.cidade VALUES (5091, '2019-10-23 14:05:31.168533', NULL, 'Lourdes', 26);
INSERT INTO endereco.cidade VALUES (5093, '2019-10-23 14:05:31.168533', NULL, 'Lucélia', 26);
INSERT INTO endereco.cidade VALUES (5095, '2019-10-23 14:05:31.168533', NULL, 'Luís Antônio', 26);
INSERT INTO endereco.cidade VALUES (5098, '2019-10-23 14:05:31.168533', NULL, 'Lutécia', 26);
INSERT INTO endereco.cidade VALUES (5100, '2019-10-23 14:05:31.168533', NULL, 'Macaubal', 26);
INSERT INTO endereco.cidade VALUES (5102, '2019-10-23 14:05:31.168533', NULL, 'Magda', 26);
INSERT INTO endereco.cidade VALUES (5104, '2019-10-23 14:05:31.168533', NULL, 'Mairiporã', 26);
INSERT INTO endereco.cidade VALUES (5106, '2019-10-23 14:05:31.168533', NULL, 'Marabá Paulista', 26);
INSERT INTO endereco.cidade VALUES (5109, '2019-10-23 14:05:31.168533', NULL, 'Mariápolis', 26);
INSERT INTO endereco.cidade VALUES (5111, '2019-10-23 14:05:31.168533', NULL, 'Marinópolis', 26);
INSERT INTO endereco.cidade VALUES (5114, '2019-10-23 14:05:31.168533', NULL, 'Mauá', 26);
INSERT INTO endereco.cidade VALUES (5116, '2019-10-23 14:05:31.168533', NULL, 'Meridiano', 26);
INSERT INTO endereco.cidade VALUES (5118, '2019-10-23 14:05:31.168533', NULL, 'Miguelópolis', 26);
INSERT INTO endereco.cidade VALUES (5121, '2019-10-23 14:05:31.168533', NULL, 'Miracatu', 26);
INSERT INTO endereco.cidade VALUES (5123, '2019-10-23 14:05:31.168533', NULL, 'Mirante Do Paranapanema', 26);
INSERT INTO endereco.cidade VALUES (5126, '2019-10-23 14:05:31.168533', NULL, 'Mococa', 26);
INSERT INTO endereco.cidade VALUES (5128, '2019-10-23 14:05:31.168533', NULL, 'Mogi Guaçu', 26);
INSERT INTO endereco.cidade VALUES (5130, '2019-10-23 14:05:31.168533', NULL, 'Mombuca', 26);
INSERT INTO endereco.cidade VALUES (5133, '2019-10-23 14:05:31.168533', NULL, 'Monte Alegre Do Sul', 26);
INSERT INTO endereco.cidade VALUES (5135, '2019-10-23 14:05:31.168533', NULL, 'Monte Aprazível', 26);
INSERT INTO endereco.cidade VALUES (5137, '2019-10-23 14:05:31.168533', NULL, 'Monte Castelo', 26);
INSERT INTO endereco.cidade VALUES (5140, '2019-10-23 14:05:31.168533', NULL, 'Morro Agudo', 26);
INSERT INTO endereco.cidade VALUES (5142, '2019-10-23 14:05:31.168533', NULL, 'Motuca', 26);
INSERT INTO endereco.cidade VALUES (5145, '2019-10-23 14:05:31.168533', NULL, 'Narandiba', 26);
INSERT INTO endereco.cidade VALUES (5147, '2019-10-23 14:05:31.168533', NULL, 'Nazaré Paulista', 26);
INSERT INTO endereco.cidade VALUES (5150, '2019-10-23 14:05:31.168533', NULL, 'Nipoã', 26);
INSERT INTO endereco.cidade VALUES (5152, '2019-10-23 14:05:31.168533', NULL, 'Nova Campina', 26);
INSERT INTO endereco.cidade VALUES (5154, '2019-10-23 14:05:31.168533', NULL, 'Nova Castilho', 26);
INSERT INTO endereco.cidade VALUES (5157, '2019-10-23 14:05:31.168533', NULL, 'Nova Guataporanga', 26);
INSERT INTO endereco.cidade VALUES (5159, '2019-10-23 14:05:31.168533', NULL, 'Nova Luzitânia', 26);
INSERT INTO endereco.cidade VALUES (5162, '2019-10-23 14:05:31.168533', NULL, 'Novo Horizonte', 26);
INSERT INTO endereco.cidade VALUES (5164, '2019-10-23 14:05:31.168533', NULL, 'Ocauçu', 26);
INSERT INTO endereco.cidade VALUES (5167, '2019-10-23 14:05:31.168533', NULL, 'Onda Verde', 26);
INSERT INTO endereco.cidade VALUES (5169, '2019-10-23 14:05:31.168533', NULL, 'Orindiúva', 26);
INSERT INTO endereco.cidade VALUES (5171, '2019-10-23 14:05:31.168533', NULL, 'Osasco', 26);
INSERT INTO endereco.cidade VALUES (5172, '2019-10-23 14:05:31.168533', NULL, 'Oscar Bressane', 26);
INSERT INTO endereco.cidade VALUES (5175, '2019-10-23 14:05:31.168533', NULL, 'Ouro Verde', 26);
INSERT INTO endereco.cidade VALUES (5177, '2019-10-23 14:05:31.168533', NULL, 'Pacaembu', 26);
INSERT INTO endereco.cidade VALUES (5179, '2019-10-23 14:05:31.168533', NULL, 'Palmares Paulista', 26);
INSERT INTO endereco.cidade VALUES (5182, '2019-10-23 14:05:31.168533', NULL, 'Panorama', 26);
INSERT INTO endereco.cidade VALUES (5184, '2019-10-23 14:05:31.168533', NULL, 'Paraibuna', 26);
INSERT INTO endereco.cidade VALUES (5186, '2019-10-23 14:05:31.168533', NULL, 'Paranapanema', 26);
INSERT INTO endereco.cidade VALUES (5189, '2019-10-23 14:05:31.168533', NULL, 'Pardinho', 26);
INSERT INTO endereco.cidade VALUES (5191, '2019-10-23 14:05:31.168533', NULL, 'Parisi', 26);
INSERT INTO endereco.cidade VALUES (5193, '2019-10-23 14:05:31.168533', NULL, 'Paulicéia', 26);
INSERT INTO endereco.cidade VALUES (5196, '2019-10-23 14:05:31.168533', NULL, 'Paulo De Faria', 26);
INSERT INTO endereco.cidade VALUES (5198, '2019-10-23 14:05:31.168533', NULL, 'Pedra Bela', 26);
INSERT INTO endereco.cidade VALUES (5200, '2019-10-23 14:05:31.168533', NULL, 'Pedregulho', 26);
INSERT INTO endereco.cidade VALUES (5203, '2019-10-23 14:05:31.168533', NULL, 'Pedro De Toledo', 26);
INSERT INTO endereco.cidade VALUES (5205, '2019-10-23 14:05:31.168533', NULL, 'Pereira Barreto', 26);
INSERT INTO endereco.cidade VALUES (5208, '2019-10-23 14:05:31.168533', NULL, 'Piacatu', 26);
INSERT INTO endereco.cidade VALUES (5210, '2019-10-23 14:05:31.168533', NULL, 'Pilar Do Sul', 26);
INSERT INTO endereco.cidade VALUES (5212, '2019-10-23 14:05:31.168533', NULL, 'Pindorama', 26);
INSERT INTO endereco.cidade VALUES (5214, '2019-10-23 14:05:31.168533', NULL, 'Piquerobi', 26);
INSERT INTO endereco.cidade VALUES (5217, '2019-10-23 14:05:31.168533', NULL, 'Piracicaba', 26);
INSERT INTO endereco.cidade VALUES (5219, '2019-10-23 14:05:31.168533', NULL, 'Pirajuí', 26);
INSERT INTO endereco.cidade VALUES (5221, '2019-10-23 14:05:31.168533', NULL, 'Pirapora Do Bom Jesus', 26);
INSERT INTO endereco.cidade VALUES (5224, '2019-10-23 14:05:31.168533', NULL, 'Piratininga', 26);
INSERT INTO endereco.cidade VALUES (5226, '2019-10-23 14:05:31.168533', NULL, 'Planalto', 26);
INSERT INTO endereco.cidade VALUES (5228, '2019-10-23 14:05:31.168533', NULL, 'Poá', 26);
INSERT INTO endereco.cidade VALUES (5231, '2019-10-23 14:05:31.168533', NULL, 'Pongaí', 26);
INSERT INTO endereco.cidade VALUES (5233, '2019-10-23 14:05:31.168533', NULL, 'Pontalinda', 26);
INSERT INTO endereco.cidade VALUES (5235, '2019-10-23 14:05:31.168533', NULL, 'Populina', 26);
INSERT INTO endereco.cidade VALUES (5237, '2019-10-23 14:05:31.168533', NULL, 'Porto Feliz', 26);
INSERT INTO endereco.cidade VALUES (5239, '2019-10-23 14:05:31.168533', NULL, 'Potim', 26);
INSERT INTO endereco.cidade VALUES (5242, '2019-10-23 14:05:31.168533', NULL, 'Pradópolis', 26);
INSERT INTO endereco.cidade VALUES (5244, '2019-10-23 14:05:31.168533', NULL, 'Pratânia', 26);
INSERT INTO endereco.cidade VALUES (5246, '2019-10-23 14:05:31.168533', NULL, 'Presidente Bernardes', 26);
INSERT INTO endereco.cidade VALUES (5249, '2019-10-23 14:05:31.168533', NULL, 'Presidente Venceslau', 26);
INSERT INTO endereco.cidade VALUES (5252, '2019-10-23 14:05:31.168533', NULL, 'Quatá', 26);
INSERT INTO endereco.cidade VALUES (5254, '2019-10-23 14:05:31.168533', NULL, 'Queluz', 26);
INSERT INTO endereco.cidade VALUES (5256, '2019-10-23 14:05:31.168533', NULL, 'Rafard', 26);
INSERT INTO endereco.cidade VALUES (5258, '2019-10-23 14:05:31.168533', NULL, 'Redenção Da Serra', 26);
INSERT INTO endereco.cidade VALUES (5261, '2019-10-23 14:05:31.168533', NULL, 'Registro', 26);
INSERT INTO endereco.cidade VALUES (5263, '2019-10-23 14:05:31.168533', NULL, 'Ribeira', 26);
INSERT INTO endereco.cidade VALUES (5265, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Branco', 26);
INSERT INTO endereco.cidade VALUES (5267, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Do Sul', 26);
INSERT INTO endereco.cidade VALUES (5270, '2019-10-23 14:05:31.168533', NULL, 'Ribeirão Pires', 26);
INSERT INTO endereco.cidade VALUES (5272, '2019-10-23 14:05:31.168533', NULL, 'Rifaina', 26);
INSERT INTO endereco.cidade VALUES (5274, '2019-10-23 14:05:31.168533', NULL, 'Rinópolis', 26);
INSERT INTO endereco.cidade VALUES (5277, '2019-10-23 14:05:31.168533', NULL, 'Rio Grande Da Serra', 26);
INSERT INTO endereco.cidade VALUES (5279, '2019-10-23 14:05:31.168533', NULL, 'Riversul', 26);
INSERT INTO endereco.cidade VALUES (5281, '2019-10-23 14:05:31.168533', NULL, 'Roseira', 26);
INSERT INTO endereco.cidade VALUES (5284, '2019-10-23 14:05:31.168533', NULL, 'Sabino', 26);
INSERT INTO endereco.cidade VALUES (5286, '2019-10-23 14:05:31.168533', NULL, 'Sales', 26);
INSERT INTO endereco.cidade VALUES (5288, '2019-10-23 14:05:31.168533', NULL, 'Salesópolis', 26);
INSERT INTO endereco.cidade VALUES (5291, '2019-10-23 14:05:31.168533', NULL, 'Salto', 26);
INSERT INTO endereco.cidade VALUES (5293, '2019-10-23 14:05:31.168533', NULL, 'Salto Grande', 26);
INSERT INTO endereco.cidade VALUES (5295, '2019-10-23 14:05:31.168533', NULL, 'Santa Adélia', 26);
INSERT INTO endereco.cidade VALUES (5298, '2019-10-23 14:05:31.168533', NULL, 'Santa Branca', 26);
INSERT INTO endereco.cidade VALUES (5299, '2019-10-23 14:05:31.168533', NULL, 'Santa Clara D`Oeste', 26);
INSERT INTO endereco.cidade VALUES (5300, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Da Conceição', 26);
INSERT INTO endereco.cidade VALUES (5302, '2019-10-23 14:05:31.168533', NULL, 'Santa Cruz Das Palmeiras', 26);
INSERT INTO endereco.cidade VALUES (5305, '2019-10-23 14:05:31.168533', NULL, 'Santa Fé Do Sul', 26);
INSERT INTO endereco.cidade VALUES (5307, '2019-10-23 14:05:31.168533', NULL, 'Santa Isabel', 26);
INSERT INTO endereco.cidade VALUES (5310, '2019-10-23 14:05:31.168533', NULL, 'Santa Mercedes', 26);
INSERT INTO endereco.cidade VALUES (5312, '2019-10-23 14:05:31.168533', NULL, 'Santa Rita Do Passa Quatro', 26);
INSERT INTO endereco.cidade VALUES (5315, '2019-10-23 14:05:31.168533', NULL, 'Santana Da Ponte Pensa', 26);
INSERT INTO endereco.cidade VALUES (5318, '2019-10-23 14:05:31.168533', NULL, 'Santo André', 26);
INSERT INTO endereco.cidade VALUES (5320, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio De Posse', 26);
INSERT INTO endereco.cidade VALUES (5322, '2019-10-23 14:05:31.168533', NULL, 'Santo Antônio Do Jardim', 26);
INSERT INTO endereco.cidade VALUES (5325, '2019-10-23 14:05:31.168533', NULL, 'Santópolis Do Aguapeí', 26);
INSERT INTO endereco.cidade VALUES (5328, '2019-10-23 14:05:31.168533', NULL, 'São Bernardo Do Campo', 26);
INSERT INTO endereco.cidade VALUES (5331, '2019-10-23 14:05:31.168533', NULL, 'São Francisco', 26);
INSERT INTO endereco.cidade VALUES (5333, '2019-10-23 14:05:31.168533', NULL, 'São João Das Duas Pontes', 26);
INSERT INTO endereco.cidade VALUES (5335, '2019-10-23 14:05:31.168533', NULL, 'São João Do Pau D`Alho', 26);
INSERT INTO endereco.cidade VALUES (5338, '2019-10-23 14:05:31.168533', NULL, 'São José Do Barreiro', 26);
INSERT INTO endereco.cidade VALUES (5341, '2019-10-23 14:05:31.168533', NULL, 'São José Dos Campos', 26);
INSERT INTO endereco.cidade VALUES (5343, '2019-10-23 14:05:31.168533', NULL, 'São Luís Do Paraitinga', 26);
INSERT INTO endereco.cidade VALUES (5346, '2019-10-23 14:05:31.168533', NULL, 'São Paulo', 26);
INSERT INTO endereco.cidade VALUES (5348, '2019-10-23 14:05:31.168533', NULL, 'São Pedro Do Turvo', 26);
INSERT INTO endereco.cidade VALUES (5351, '2019-10-23 14:05:31.168533', NULL, 'São Sebastião Da Grama', 26);
INSERT INTO endereco.cidade VALUES (5354, '2019-10-23 14:05:31.168533', NULL, 'Sarapuí', 26);
INSERT INTO endereco.cidade VALUES (5356, '2019-10-23 14:05:31.168533', NULL, 'Sebastianópolis Do Sul', 26);
INSERT INTO endereco.cidade VALUES (5359, '2019-10-23 14:05:31.168533', NULL, 'Serrana', 26);
INSERT INTO endereco.cidade VALUES (5361, '2019-10-23 14:05:31.168533', NULL, 'Sete Barras', 26);
INSERT INTO endereco.cidade VALUES (5363, '2019-10-23 14:05:31.168533', NULL, 'Silveiras', 26);
INSERT INTO endereco.cidade VALUES (5366, '2019-10-23 14:05:31.168533', NULL, 'Sud Mennucci', 26);
INSERT INTO endereco.cidade VALUES (5368, '2019-10-23 14:05:31.168533', NULL, 'Suzanápolis', 26);
INSERT INTO endereco.cidade VALUES (5371, '2019-10-23 14:05:31.168533', NULL, 'Tabatinga', 26);
INSERT INTO endereco.cidade VALUES (5373, '2019-10-23 14:05:31.168533', NULL, 'Taciba', 26);
INSERT INTO endereco.cidade VALUES (5375, '2019-10-23 14:05:31.168533', NULL, 'Taiaçu', 26);
INSERT INTO endereco.cidade VALUES (5377, '2019-10-23 14:05:31.168533', NULL, 'Tambaú', 26);
INSERT INTO endereco.cidade VALUES (5380, '2019-10-23 14:05:31.168533', NULL, 'Tapiratiba', 26);
INSERT INTO endereco.cidade VALUES (5382, '2019-10-23 14:05:31.168533', NULL, 'Taquaritinga', 26);
INSERT INTO endereco.cidade VALUES (5384, '2019-10-23 14:05:31.168533', NULL, 'Taquarivaí', 26);
INSERT INTO endereco.cidade VALUES (5387, '2019-10-23 14:05:31.168533', NULL, 'Tatuí', 26);
INSERT INTO endereco.cidade VALUES (5389, '2019-10-23 14:05:31.168533', NULL, 'Tejupá', 26);
INSERT INTO endereco.cidade VALUES (5391, '2019-10-23 14:05:31.168533', NULL, 'Terra Roxa', 26);
INSERT INTO endereco.cidade VALUES (5393, '2019-10-23 14:05:31.168533', NULL, 'Timburi', 26);
INSERT INTO endereco.cidade VALUES (5396, '2019-10-23 14:05:31.168533', NULL, 'Trabiju', 26);
INSERT INTO endereco.cidade VALUES (5398, '2019-10-23 14:05:31.168533', NULL, 'Três Fronteiras', 26);
INSERT INTO endereco.cidade VALUES (5400, '2019-10-23 14:05:31.168533', NULL, 'Tupã', 26);
INSERT INTO endereco.cidade VALUES (5403, '2019-10-23 14:05:31.168533', NULL, 'Turmalina', 26);
INSERT INTO endereco.cidade VALUES (5405, '2019-10-23 14:05:31.168533', NULL, 'Ubatuba', 26);
INSERT INTO endereco.cidade VALUES (5407, '2019-10-23 14:05:31.168533', NULL, 'Uchoa', 26);
INSERT INTO endereco.cidade VALUES (5409, '2019-10-23 14:05:31.168533', NULL, 'Urânia', 26);
INSERT INTO endereco.cidade VALUES (5412, '2019-10-23 14:05:31.168533', NULL, 'Valentim Gentil', 26);
INSERT INTO endereco.cidade VALUES (5431, '2019-10-23 14:05:31.168533', NULL, 'Alvorada', 27);
INSERT INTO endereco.cidade VALUES (5433, '2019-10-23 14:05:31.168533', NULL, 'Angico', 27);
INSERT INTO endereco.cidade VALUES (5435, '2019-10-23 14:05:31.168533', NULL, 'Aragominas', 27);
INSERT INTO endereco.cidade VALUES (5438, '2019-10-23 14:05:31.168533', NULL, 'Araguaína', 27);
INSERT INTO endereco.cidade VALUES (5440, '2019-10-23 14:05:31.168533', NULL, 'Araguatins', 27);
INSERT INTO endereco.cidade VALUES (5442, '2019-10-23 14:05:31.168533', NULL, 'Arraias', 27);
INSERT INTO endereco.cidade VALUES (5444, '2019-10-23 14:05:31.168533', NULL, 'Aurora Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5447, '2019-10-23 14:05:31.168533', NULL, 'Bandeirantes Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5449, '2019-10-23 14:05:31.168533', NULL, 'Barrolândia', 27);
INSERT INTO endereco.cidade VALUES (5452, '2019-10-23 14:05:31.168533', NULL, 'Brasilândia Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5455, '2019-10-23 14:05:31.168533', NULL, 'Cachoeirinha', 27);
INSERT INTO endereco.cidade VALUES (5457, '2019-10-23 14:05:31.168533', NULL, 'Cariri Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5459, '2019-10-23 14:05:31.168533', NULL, 'Carrasco Bonito', 27);
INSERT INTO endereco.cidade VALUES (5462, '2019-10-23 14:05:31.168533', NULL, 'Chapada Da Natividade', 27);
INSERT INTO endereco.cidade VALUES (5464, '2019-10-23 14:05:31.168533', NULL, 'Colinas Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5467, '2019-10-23 14:05:31.168533', NULL, 'Conceição Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5470, '2019-10-23 14:05:31.168533', NULL, 'Crixás Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5473, '2019-10-23 14:05:31.168533', NULL, 'Divinópolis Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5475, '2019-10-23 14:05:31.168533', NULL, 'Dueré', 27);
INSERT INTO endereco.cidade VALUES (5478, '2019-10-23 14:05:31.168533', NULL, 'Figueirópolis', 27);
INSERT INTO endereco.cidade VALUES (5480, '2019-10-23 14:05:31.168533', NULL, 'Formoso Do Araguaia', 27);
INSERT INTO endereco.cidade VALUES (5482, '2019-10-23 14:05:31.168533', NULL, 'Goianorte', 27);
INSERT INTO endereco.cidade VALUES (5485, '2019-10-23 14:05:31.168533', NULL, 'Gurupi', 27);
INSERT INTO endereco.cidade VALUES (5487, '2019-10-23 14:05:31.168533', NULL, 'Itacajá', 27);
INSERT INTO endereco.cidade VALUES (5489, '2019-10-23 14:05:31.168533', NULL, 'Itapiratins', 27);
INSERT INTO endereco.cidade VALUES (5492, '2019-10-23 14:05:31.168533', NULL, 'Juarina', 27);
INSERT INTO endereco.cidade VALUES (5494, '2019-10-23 14:05:31.168533', NULL, 'Lagoa Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5496, '2019-10-23 14:05:31.168533', NULL, 'Lavandeira', 27);
INSERT INTO endereco.cidade VALUES (5499, '2019-10-23 14:05:31.168533', NULL, 'Marianópolis Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5501, '2019-10-23 14:05:31.168533', NULL, 'Maurilândia Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5504, '2019-10-23 14:05:31.168533', NULL, 'Monte Do Carmo', 27);
INSERT INTO endereco.cidade VALUES (5506, '2019-10-23 14:05:31.168533', NULL, 'Muricilândia', 27);
INSERT INTO endereco.cidade VALUES (5509, '2019-10-23 14:05:31.168533', NULL, 'Nova Olinda', 27);
INSERT INTO endereco.cidade VALUES (5511, '2019-10-23 14:05:31.168533', NULL, 'Novo Acordo', 27);
INSERT INTO endereco.cidade VALUES (5513, '2019-10-23 14:05:31.168533', NULL, 'Novo Jardim', 27);
INSERT INTO endereco.cidade VALUES (5516, '2019-10-23 14:05:31.168533', NULL, 'Palmeirante', 27);
INSERT INTO endereco.cidade VALUES (5518, '2019-10-23 14:05:31.168533', NULL, 'Palmeirópolis', 27);
INSERT INTO endereco.cidade VALUES (5521, '2019-10-23 14:05:31.168533', NULL, 'Pau D`Arco', 27);
INSERT INTO endereco.cidade VALUES (5523, '2019-10-23 14:05:31.168533', NULL, 'Peixe', 27);
INSERT INTO endereco.cidade VALUES (5525, '2019-10-23 14:05:31.168533', NULL, 'Pindorama Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5528, '2019-10-23 14:05:31.168533', NULL, 'Ponte Alta Do Bom Jesus', 27);
INSERT INTO endereco.cidade VALUES (5530, '2019-10-23 14:05:31.168533', NULL, 'Porto Alegre Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5533, '2019-10-23 14:05:31.168533', NULL, 'Presidente Kennedy', 27);
INSERT INTO endereco.cidade VALUES (5535, '2019-10-23 14:05:31.168533', NULL, 'Recursolândia', 27);
INSERT INTO endereco.cidade VALUES (5538, '2019-10-23 14:05:31.168533', NULL, 'Rio Dos Bois', 27);
INSERT INTO endereco.cidade VALUES (5540, '2019-10-23 14:05:31.168533', NULL, 'Sampaio', 27);
INSERT INTO endereco.cidade VALUES (5542, '2019-10-23 14:05:31.168533', NULL, 'Santa Fé Do Araguaia', 27);
INSERT INTO endereco.cidade VALUES (5545, '2019-10-23 14:05:31.168533', NULL, 'Santa Rosa Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5546, '2019-10-23 14:05:31.168533', NULL, 'Santa Tereza Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5548, '2019-10-23 14:05:31.168533', NULL, 'São Bento Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5551, '2019-10-23 14:05:31.168533', NULL, 'São Salvador Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5553, '2019-10-23 14:05:31.168533', NULL, 'São Valério', 27);
INSERT INTO endereco.cidade VALUES (5558, '2019-10-23 14:05:31.168533', NULL, 'Taipas Do Tocantins', 27);
INSERT INTO endereco.cidade VALUES (5561, '2019-10-23 14:05:31.168533', NULL, 'Tocantinópolis', 27);
INSERT INTO endereco.cidade VALUES (5563, '2019-10-23 14:05:31.168533', NULL, 'Tupiratins', 27);
INSERT INTO endereco.cidade VALUES (5565, '2019-10-23 14:05:31.168533', NULL, 'Xambioá', 27);


--
-- TOC entry 3448 (class 0 OID 68422)
-- Dependencies: 240
-- Data for Name: endereco; Type: TABLE DATA; Schema: endereco; Owner: -
--



--
-- TOC entry 3450 (class 0 OID 68430)
-- Dependencies: 242
-- Data for Name: estado; Type: TABLE DATA; Schema: endereco; Owner: -
--

INSERT INTO endereco.estado VALUES (1, '2019-10-23 14:05:27.829319', NULL, 'Acre', 'AC', 1000);
INSERT INTO endereco.estado VALUES (2, '2019-10-23 14:05:27.829319', NULL, 'Alagoas', 'AL', 1000);
INSERT INTO endereco.estado VALUES (3, '2019-10-23 14:05:27.829319', NULL, 'Amazonas', 'AM', 1000);
INSERT INTO endereco.estado VALUES (4, '2019-10-23 14:05:27.829319', NULL, 'Amapá', 'AP', 1000);
INSERT INTO endereco.estado VALUES (5, '2019-10-23 14:05:27.829319', NULL, 'Bahia', 'BA', 1000);
INSERT INTO endereco.estado VALUES (6, '2019-10-23 14:05:27.829319', NULL, 'Ceará', 'CE', 1000);
INSERT INTO endereco.estado VALUES (7, '2019-10-23 14:05:27.829319', NULL, 'Distrito Federal', 'DF', 1000);
INSERT INTO endereco.estado VALUES (8, '2019-10-23 14:05:27.829319', NULL, 'Espirito Santo', 'ES', 1000);
INSERT INTO endereco.estado VALUES (9, '2019-10-23 14:05:27.829319', NULL, 'Goiás', 'GO', 1000);
INSERT INTO endereco.estado VALUES (10, '2019-10-23 14:05:27.829319', NULL, 'Maranhão', 'MA', 1000);
INSERT INTO endereco.estado VALUES (11, '2019-10-23 14:05:27.829319', NULL, 'Minas Gerais', 'MG', 1000);
INSERT INTO endereco.estado VALUES (12, '2019-10-23 14:05:27.829319', NULL, 'Mato Grosso do Sul', 'MS', 1000);
INSERT INTO endereco.estado VALUES (13, '2019-10-23 14:05:27.829319', NULL, 'Mato Grosso', 'MT', 1000);
INSERT INTO endereco.estado VALUES (14, '2019-10-23 14:05:27.829319', NULL, 'Pará', 'PA', 1000);
INSERT INTO endereco.estado VALUES (15, '2019-10-23 14:05:27.829319', NULL, 'Paraíba', 'PB', 1000);
INSERT INTO endereco.estado VALUES (16, '2019-10-23 14:05:27.829319', NULL, 'Pernambuco', 'PE', 1000);
INSERT INTO endereco.estado VALUES (17, '2019-10-23 14:05:27.829319', NULL, 'Piauí', 'PI', 1000);
INSERT INTO endereco.estado VALUES (18, '2019-10-23 14:05:27.829319', NULL, 'Paraná', 'PR', 1000);
INSERT INTO endereco.estado VALUES (19, '2019-10-23 14:05:27.829319', NULL, 'Rio de Janeiro', 'RJ', 1000);
INSERT INTO endereco.estado VALUES (20, '2019-10-23 14:05:27.829319', NULL, 'Rio Grande do Norte', 'RN', 1000);
INSERT INTO endereco.estado VALUES (21, '2019-10-23 14:05:27.829319', NULL, 'Rondônia', 'RO', 1000);
INSERT INTO endereco.estado VALUES (22, '2019-10-23 14:05:27.829319', NULL, 'Roraima', 'RR', 1000);
INSERT INTO endereco.estado VALUES (23, '2019-10-23 14:05:27.829319', NULL, 'Rio Grande do Sul', 'RS', 1000);
INSERT INTO endereco.estado VALUES (24, '2019-10-23 14:05:27.829319', NULL, 'Santa Catarina', 'SC', 1000);
INSERT INTO endereco.estado VALUES (25, '2019-10-23 14:05:27.829319', NULL, 'Sergipe', 'SE', 1000);
INSERT INTO endereco.estado VALUES (26, '2019-10-23 14:05:27.829319', NULL, 'São Paulo', 'SP', 1000);
INSERT INTO endereco.estado VALUES (27, '2019-10-23 14:05:27.829319', NULL, 'Tocantins', 'TO', 1000);


--
-- TOC entry 3452 (class 0 OID 68438)
-- Dependencies: 244
-- Data for Name: pais; Type: TABLE DATA; Schema: endereco; Owner: -
--

INSERT INTO endereco.pais VALUES (1000, '2019-10-23 14:05:27.437256', NULL, 'Brasil');


--
-- TOC entry 3453 (class 0 OID 68448)
-- Dependencies: 245
-- Data for Name: cidade_audit; Type: TABLE DATA; Schema: endereco_audit; Owner: -
--



--
-- TOC entry 3454 (class 0 OID 68453)
-- Dependencies: 246
-- Data for Name: endereco_audit; Type: TABLE DATA; Schema: endereco_audit; Owner: -
--



--
-- TOC entry 3455 (class 0 OID 68461)
-- Dependencies: 247
-- Data for Name: estado_audit; Type: TABLE DATA; Schema: endereco_audit; Owner: -
--



--
-- TOC entry 3456 (class 0 OID 68466)
-- Dependencies: 248
-- Data for Name: pais_audit; Type: TABLE DATA; Schema: endereco_audit; Owner: -
--



--
-- TOC entry 3460 (class 0 OID 68489)
-- Dependencies: 252
-- Data for Name: atividade_economica; Type: TABLE DATA; Schema: fornecedor; Owner: -
--

INSERT INTO fornecedor.atividade_economica VALUES ('0111-3/01', 'Cultivo de arroz');
INSERT INTO fornecedor.atividade_economica VALUES ('0111-3/02', 'Cultivo de milho');
INSERT INTO fornecedor.atividade_economica VALUES ('0111-3/03', 'Cultivo de trigo');
INSERT INTO fornecedor.atividade_economica VALUES ('0111-3/99', 'Cultivo de outros cereais não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0112-1/01', 'Cultivo de algodão herbáceo');
INSERT INTO fornecedor.atividade_economica VALUES ('0112-1/02', 'Cultivo de juta');
INSERT INTO fornecedor.atividade_economica VALUES ('0112-1/99', 'Cultivo de outras fibras de lavoura temporária não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0113-0/00', 'Cultivo de cana-de-açúcar');
INSERT INTO fornecedor.atividade_economica VALUES ('0114-8/00', 'Cultivo de fumo');
INSERT INTO fornecedor.atividade_economica VALUES ('0115-6/00', 'Cultivo de soja');
INSERT INTO fornecedor.atividade_economica VALUES ('0116-4/01', 'Cultivo de amendoim');
INSERT INTO fornecedor.atividade_economica VALUES ('0116-4/02', 'Cultivo de girassol');
INSERT INTO fornecedor.atividade_economica VALUES ('0116-4/03', 'Cultivo de mamona');
INSERT INTO fornecedor.atividade_economica VALUES ('0116-4/99', 'Cultivo de outras oleaginosas de lavoura temporária não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/01', 'Cultivo de abacaxi');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/02', 'Cultivo de alho');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/03', 'Cultivo de batata-inglesa');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/04', 'Cultivo de cebola');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/05', 'Cultivo de feijão');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/06', 'Cultivo de mandioca');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/07', 'Cultivo de melão');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/08', 'Cultivo de melancia');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/09', 'Cultivo de tomate rasteiro');
INSERT INTO fornecedor.atividade_economica VALUES ('0119-9/99', 'Cultivo de outras plantas de lavoura temporária não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0121-1/01', 'Horticultura, exceto morango');
INSERT INTO fornecedor.atividade_economica VALUES ('0121-1/02', 'Cultivo de morango');
INSERT INTO fornecedor.atividade_economica VALUES ('0122-9/00', 'Floricultura');
INSERT INTO fornecedor.atividade_economica VALUES ('0131-8/00', 'Cultivo de laranja');
INSERT INTO fornecedor.atividade_economica VALUES ('0132-6/00', 'Cultivo de uva');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/01', 'Cultivo de açaí');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/02', 'Cultivo de banana');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/03', 'Cultivo de caju');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/04', 'Cultivo de cítricos, exceto laranja');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/05', 'Cultivo de coco-da-baía');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/06', 'Cultivo de guaraná');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/07', 'Cultivo de maçã');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/08', 'Cultivo de mamão');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/09', 'Cultivo de maracujá');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/10', 'Cultivo de manga');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/11', 'Cultivo de pêssego');
INSERT INTO fornecedor.atividade_economica VALUES ('0133-4/99', 'Cultivo de frutas de lavoura permanente não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0134-2/00', 'Cultivo de café');
INSERT INTO fornecedor.atividade_economica VALUES ('0135-1/00', 'Cultivo de cacau');
INSERT INTO fornecedor.atividade_economica VALUES ('0139-3/01', 'Cultivo de chá-da-índia');
INSERT INTO fornecedor.atividade_economica VALUES ('0139-3/02', 'Cultivo de erva-mate');
INSERT INTO fornecedor.atividade_economica VALUES ('0139-3/03', 'Cultivo de pimenta-do-reino');
INSERT INTO fornecedor.atividade_economica VALUES ('0139-3/04', 'Cultivo de plantas para condimento, exceto pimenta-do-reino');
INSERT INTO fornecedor.atividade_economica VALUES ('0139-3/05', 'Cultivo de dendê');
INSERT INTO fornecedor.atividade_economica VALUES ('0139-3/06', 'Cultivo de seringueira');
INSERT INTO fornecedor.atividade_economica VALUES ('0139-3/99', 'Cultivo de outras plantas de lavoura permanente não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0141-5/01', 'Produção de sementes certificadas, exceto de forrageiras para pasto');
INSERT INTO fornecedor.atividade_economica VALUES ('0141-5/02', 'Produção de sementes certificadas de forrageiras para formação de pasto');
INSERT INTO fornecedor.atividade_economica VALUES ('0142-3/00', 'Produção de mudas e outras formas de propagação vegetal, certificadas');
INSERT INTO fornecedor.atividade_economica VALUES ('0151-2/01', 'Criação de bovinos para corte');
INSERT INTO fornecedor.atividade_economica VALUES ('0151-2/02', 'Criação de bovinos para leite');
INSERT INTO fornecedor.atividade_economica VALUES ('0151-2/03', 'Criação de bovinos, exceto para corte e leite');
INSERT INTO fornecedor.atividade_economica VALUES ('0152-1/01', 'Criação de bufalinos');
INSERT INTO fornecedor.atividade_economica VALUES ('0152-1/02', 'Criação de eqüinos');
INSERT INTO fornecedor.atividade_economica VALUES ('0152-1/03', 'Criação de asininos e muares');
INSERT INTO fornecedor.atividade_economica VALUES ('0153-9/01', 'Criação de caprinos');
INSERT INTO fornecedor.atividade_economica VALUES ('0153-9/02', 'Criação de ovinos, inclusive para produção de lã');
INSERT INTO fornecedor.atividade_economica VALUES ('0154-7/00', 'Criação de suínos');
INSERT INTO fornecedor.atividade_economica VALUES ('0155-5/01', 'Criação de frangos para corte');
INSERT INTO fornecedor.atividade_economica VALUES ('0155-5/02', 'Produção de pintos de um dia');
INSERT INTO fornecedor.atividade_economica VALUES ('0155-5/03', 'Criação de outros galináceos, exceto para corte');
INSERT INTO fornecedor.atividade_economica VALUES ('0155-5/04', 'Criação de aves, exceto galináceos');
INSERT INTO fornecedor.atividade_economica VALUES ('0155-5/05', 'Produção de ovos');
INSERT INTO fornecedor.atividade_economica VALUES ('0159-8/01', 'Apicultura');
INSERT INTO fornecedor.atividade_economica VALUES ('0159-8/02', 'Criação de animais de estimação');
INSERT INTO fornecedor.atividade_economica VALUES ('0159-8/03', 'Criação de escargô');
INSERT INTO fornecedor.atividade_economica VALUES ('0159-8/04', 'Criação de bicho-da-seda');
INSERT INTO fornecedor.atividade_economica VALUES ('0159-8/99', 'Criação de outros animais não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0161-0/01', 'Serviço de pulverização e controle de pragas agrícolas');
INSERT INTO fornecedor.atividade_economica VALUES ('0161-0/02', 'Serviço de poda de árvores para lavouras');
INSERT INTO fornecedor.atividade_economica VALUES ('0161-0/03', 'Serviço de preparação de terreno, cultivo e colheita');
INSERT INTO fornecedor.atividade_economica VALUES ('0161-0/99', 'Atividades de apoio à agricultura não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0162-8/01', 'Serviço de inseminação artificial de animais *');
INSERT INTO fornecedor.atividade_economica VALUES ('0162-8/02', 'Serviço de tosquiamento de ovinos');
INSERT INTO fornecedor.atividade_economica VALUES ('0162-8/03', 'Serviço de manejo de animais');
INSERT INTO fornecedor.atividade_economica VALUES ('0162-8/99', 'Atividades de apoio à pecuária não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0163-6/00', 'Atividades de pós-colheita');
INSERT INTO fornecedor.atividade_economica VALUES ('0170-9/00', 'Caça e serviços relacionados');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/01', 'Cultivo de eucalipto');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/02', 'Cultivo de acácia-negra');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/03', 'Cultivo de pinus');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/04', 'Cultivo de teca');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/05', 'Cultivo de espécies madeireiras, exceto eucalipto, acácia-negra, pinus e teca');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/06', 'Cultivo de mudas em viveiros florestais');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/07', 'Extração de madeira em florestas plantadas');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/08', 'Produção de carvão vegetal - florestas plantadas');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/09', 'Produção de casca de acácia-negra - florestas plantadas');
INSERT INTO fornecedor.atividade_economica VALUES ('0210-1/99', 'Produção de produtos não-madeireiros não especificados anteriormente em florestas plantadas');
INSERT INTO fornecedor.atividade_economica VALUES ('0220-9/01', 'Extração de madeira em florestas nativas');
INSERT INTO fornecedor.atividade_economica VALUES ('0220-9/02', 'Produção de carvão vegetal - florestas nativas');
INSERT INTO fornecedor.atividade_economica VALUES ('0220-9/03', 'Coleta de castanha-do-pará em florestas nativas');
INSERT INTO fornecedor.atividade_economica VALUES ('0220-9/04', 'Coleta de látex em florestas nativas');
INSERT INTO fornecedor.atividade_economica VALUES ('0220-9/05', 'Coleta de palmito em florestas nativas');
INSERT INTO fornecedor.atividade_economica VALUES ('0220-9/06', 'Conservação de florestas nativas');
INSERT INTO fornecedor.atividade_economica VALUES ('0220-9/99', 'Coleta de produtos não-madeireiros não especificados anteriormente em florestas nativas');
INSERT INTO fornecedor.atividade_economica VALUES ('0230-6/00', 'Atividades de apoio à produção florestal');
INSERT INTO fornecedor.atividade_economica VALUES ('0311-6/01', 'Pesca de peixes em água salgada');
INSERT INTO fornecedor.atividade_economica VALUES ('0311-6/02', 'Pesca de crustáceos e moluscos em água salgada');
INSERT INTO fornecedor.atividade_economica VALUES ('0311-6/03', 'Coleta de outros produtos marinhos');
INSERT INTO fornecedor.atividade_economica VALUES ('0311-6/04', 'Atividades de apoio à pesca em água salgada');
INSERT INTO fornecedor.atividade_economica VALUES ('0312-4/01', 'Pesca de peixes em água doce');
INSERT INTO fornecedor.atividade_economica VALUES ('0312-4/02', 'Pesca de crustáceos e moluscos em água doce');
INSERT INTO fornecedor.atividade_economica VALUES ('0312-4/03', 'Coleta de outros produtos aquáticos de água doce');
INSERT INTO fornecedor.atividade_economica VALUES ('0312-4/04', 'Atividades de apoio à pesca em água doce');
INSERT INTO fornecedor.atividade_economica VALUES ('0321-3/01', 'Criação de peixes em água salgada e salobra');
INSERT INTO fornecedor.atividade_economica VALUES ('0321-3/02', 'Criação de camarões em água salgada e salobra');
INSERT INTO fornecedor.atividade_economica VALUES ('0321-3/03', 'Criação de ostras e mexilhões em água salgada e salobra');
INSERT INTO fornecedor.atividade_economica VALUES ('0321-3/04', 'Criação de peixes ornamentais em água salgada e salobra');
INSERT INTO fornecedor.atividade_economica VALUES ('0321-3/05', 'Atividades de apoio à aqüicultura em água salgada e salobra');
INSERT INTO fornecedor.atividade_economica VALUES ('0321-3/99', 'Cultivos e semicultivos da aqüicultura em água salgada e salobra não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0322-1/01', 'Criação de peixes em água doce');
INSERT INTO fornecedor.atividade_economica VALUES ('0322-1/02', 'Criação de camarões em água doce');
INSERT INTO fornecedor.atividade_economica VALUES ('0322-1/03', 'Criação de ostras e mexilhões em água doce');
INSERT INTO fornecedor.atividade_economica VALUES ('0322-1/04', 'Criação de peixes ornamentais em água doce');
INSERT INTO fornecedor.atividade_economica VALUES ('0322-1/05', 'Ranicultura');
INSERT INTO fornecedor.atividade_economica VALUES ('0322-1/06', 'Criação de jacaré');
INSERT INTO fornecedor.atividade_economica VALUES ('0322-1/07', 'Atividades de apoio à aqüicultura em água doce');
INSERT INTO fornecedor.atividade_economica VALUES ('0322-1/99', 'Cultivos e semicultivos da aqüicultura em água doce não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0500-3/01', 'Extração de carvão mineral');
INSERT INTO fornecedor.atividade_economica VALUES ('0500-3/02', 'Beneficiamento de carvão mineral');
INSERT INTO fornecedor.atividade_economica VALUES ('0600-0/01', 'Extração de petróleo e gás natural');
INSERT INTO fornecedor.atividade_economica VALUES ('0600-0/02', 'Extração e beneficiamento de xisto');
INSERT INTO fornecedor.atividade_economica VALUES ('0600-0/03', 'Extração e beneficiamento de areias betuminosas');
INSERT INTO fornecedor.atividade_economica VALUES ('0710-3/01', 'Extração de minério de ferro');
INSERT INTO fornecedor.atividade_economica VALUES ('0710-3/02', 'Pelotização, sinterização e outros beneficiamentos de minério de ferro');
INSERT INTO fornecedor.atividade_economica VALUES ('0721-9/01', 'Extração de minério de alumínio');
INSERT INTO fornecedor.atividade_economica VALUES ('0721-9/02', 'Beneficiamento de minério de alumínio');
INSERT INTO fornecedor.atividade_economica VALUES ('0722-7/01', 'Extração de minério de estanho');
INSERT INTO fornecedor.atividade_economica VALUES ('0722-7/02', 'Beneficiamento de minério de estanho');
INSERT INTO fornecedor.atividade_economica VALUES ('0723-5/01', 'Extração de minério de manganês');
INSERT INTO fornecedor.atividade_economica VALUES ('0723-5/02', 'Beneficiamento de minério de manganês');
INSERT INTO fornecedor.atividade_economica VALUES ('0724-3/01', 'Extração de minério de metais preciosos');
INSERT INTO fornecedor.atividade_economica VALUES ('0724-3/02', 'Beneficiamento de minério de metais preciosos');
INSERT INTO fornecedor.atividade_economica VALUES ('0725-1/00', 'Extração de minerais radioativos');
INSERT INTO fornecedor.atividade_economica VALUES ('0729-4/01', 'Extração de minérios de nióbio e titânio');
INSERT INTO fornecedor.atividade_economica VALUES ('0729-4/02', 'Extração de minério de tungstênio');
INSERT INTO fornecedor.atividade_economica VALUES ('0729-4/03', 'Extração de minério de níquel');
INSERT INTO fornecedor.atividade_economica VALUES ('0729-4/04', 'Extração de minérios de cobre, chumbo, zinco e outros minerais metálicos não-ferrosos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0729-4/05', 'Beneficiamento de minérios de cobre, chumbo, zinco e outros minerais metálicos não-ferrosos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/01', 'Extração de ardósia e beneficiamento associado');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/02', 'Extração de granito e beneficiamento associado');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/03', 'Extração de mármore e beneficiamento associado');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/04', 'Extração de calcário e dolomita e beneficiamento associado');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/05', 'Extração de gesso e caulim');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/06', 'Extração de areia, cascalho ou pedregulho e beneficiamento associado');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/07', 'Extração de argila e beneficiamento associado');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/08', 'Extração de saibro e beneficiamento associado');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/09', 'Extração de basalto e beneficiamento associado');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/10', 'Beneficiamento de gesso e caulim associado à extração');
INSERT INTO fornecedor.atividade_economica VALUES ('0810-0/99', 'Extração e britamento de pedras e outros materiais para construção e beneficiamento associado');
INSERT INTO fornecedor.atividade_economica VALUES ('0891-6/00', 'Extração de minerais para fabricação de adubos, fertilizantes e outros produtos químicos');
INSERT INTO fornecedor.atividade_economica VALUES ('0892-4/01', 'Extração de sal marinho');
INSERT INTO fornecedor.atividade_economica VALUES ('0892-4/02', 'Extração de sal-gema');
INSERT INTO fornecedor.atividade_economica VALUES ('0892-4/03', 'Refino e outros tratamentos do sal');
INSERT INTO fornecedor.atividade_economica VALUES ('0893-2/00', 'Extração de gemas (pedras preciosas e semipreciosas)');
INSERT INTO fornecedor.atividade_economica VALUES ('0899-1/01', 'Extração de grafita');
INSERT INTO fornecedor.atividade_economica VALUES ('0899-1/02', 'Extração de quartzo');
INSERT INTO fornecedor.atividade_economica VALUES ('0899-1/03', 'Extração de amianto');
INSERT INTO fornecedor.atividade_economica VALUES ('0899-1/99', 'Extração de outros minerais não-metálicos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('0910-6/00', 'Atividades de apoio à extração de petróleo e gás natural');
INSERT INTO fornecedor.atividade_economica VALUES ('0990-4/01', 'Atividades de apoio à extração de minério de ferro');
INSERT INTO fornecedor.atividade_economica VALUES ('0990-4/02', 'Atividades de apoio à extração de minerais metálicos não-ferrosos');
INSERT INTO fornecedor.atividade_economica VALUES ('0990-4/03', 'Atividades de apoio à extração de minerais não-metálicos');
INSERT INTO fornecedor.atividade_economica VALUES ('1011-2/01', 'Frigorífico - abate de bovinos');
INSERT INTO fornecedor.atividade_economica VALUES ('1011-2/02', 'Frigorífico - abate de eqüinos');
INSERT INTO fornecedor.atividade_economica VALUES ('1011-2/03', 'Frigorífico - abate de ovinos e caprinos');
INSERT INTO fornecedor.atividade_economica VALUES ('1011-2/04', 'Frigorífico - abate de bufalinos');
INSERT INTO fornecedor.atividade_economica VALUES ('1011-2/05', 'Matadouro - abate de reses sob contrato - exceto abate de suínos');
INSERT INTO fornecedor.atividade_economica VALUES ('1012-1/01', 'Abate de aves');
INSERT INTO fornecedor.atividade_economica VALUES ('1012-1/02', 'Abate de pequenos animais');
INSERT INTO fornecedor.atividade_economica VALUES ('1012-1/03', 'Frigorífico - abate de suínos');
INSERT INTO fornecedor.atividade_economica VALUES ('1012-1/04', 'Matadouro - abate de suínos sob contrato');
INSERT INTO fornecedor.atividade_economica VALUES ('1013-9/01', 'Fabricação de produtos de carne');
INSERT INTO fornecedor.atividade_economica VALUES ('1013-9/02', 'Preparação de subprodutos do abate');
INSERT INTO fornecedor.atividade_economica VALUES ('1020-1/01', 'Preservação de peixes, crustáceos e moluscos');
INSERT INTO fornecedor.atividade_economica VALUES ('1020-1/02', 'Fabricação de conservas de peixes, crustáceos e moluscos');
INSERT INTO fornecedor.atividade_economica VALUES ('1031-7/00', 'Fabricação de conservas de frutas');
INSERT INTO fornecedor.atividade_economica VALUES ('1032-5/01', 'Fabricação de conservas de palmito');
INSERT INTO fornecedor.atividade_economica VALUES ('1032-5/99', 'Fabricação de conservas de legumes e outros vegetais, exceto palmito');
INSERT INTO fornecedor.atividade_economica VALUES ('1033-3/01', 'Fabricação de sucos concentrados de frutas, hortaliças e legumes');
INSERT INTO fornecedor.atividade_economica VALUES ('1033-3/02', 'Fabricação de sucos de frutas, hortaliças e legumes, exceto concentrados');
INSERT INTO fornecedor.atividade_economica VALUES ('1041-4/00', 'Fabricação de óleos vegetais em bruto, exceto óleo de milho');
INSERT INTO fornecedor.atividade_economica VALUES ('1042-2/00', 'Fabricação de óleos vegetais refinados, exceto óleo de milho');
INSERT INTO fornecedor.atividade_economica VALUES ('1043-1/00', 'Fabricação de margarina e outras gorduras vegetais e de óleos não-comestíveis de animais');
INSERT INTO fornecedor.atividade_economica VALUES ('1051-1/00', 'Preparação do leite');
INSERT INTO fornecedor.atividade_economica VALUES ('1052-0/00', 'Fabricação de laticínios');
INSERT INTO fornecedor.atividade_economica VALUES ('1053-8/00', 'Fabricação de sorvetes e outros gelados comestíveis');
INSERT INTO fornecedor.atividade_economica VALUES ('1061-9/01', 'Beneficiamento de arroz');
INSERT INTO fornecedor.atividade_economica VALUES ('1061-9/02', 'Fabricação de produtos do arroz');
INSERT INTO fornecedor.atividade_economica VALUES ('1062-7/00', 'Moagem de trigo e fabricação de derivados');
INSERT INTO fornecedor.atividade_economica VALUES ('1063-5/00', 'Fabricação de farinha de mandioca e derivados');
INSERT INTO fornecedor.atividade_economica VALUES ('1064-3/00', 'Fabricação de farinha de milho e derivados, exceto óleos de milho');
INSERT INTO fornecedor.atividade_economica VALUES ('1065-1/01', 'Fabricação de amidos e féculas de vegetais');
INSERT INTO fornecedor.atividade_economica VALUES ('1065-1/02', 'Fabricação de óleo de milho em bruto');
INSERT INTO fornecedor.atividade_economica VALUES ('1065-1/03', 'Fabricação de óleo de milho refinado');
INSERT INTO fornecedor.atividade_economica VALUES ('1066-0/00', 'Fabricação de alimentos para animais');
INSERT INTO fornecedor.atividade_economica VALUES ('1069-4/00', 'Moagem e fabricação de produtos de origem vegetal não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('1071-6/00', 'Fabricação de açúcar em bruto');
INSERT INTO fornecedor.atividade_economica VALUES ('1072-4/01', 'Fabricação de açúcar de cana refinado');
INSERT INTO fornecedor.atividade_economica VALUES ('1072-4/02', 'Fabricação de açúcar de cereais (dextrose) e de beterraba');
INSERT INTO fornecedor.atividade_economica VALUES ('1081-3/01', 'Beneficiamento de café');
INSERT INTO fornecedor.atividade_economica VALUES ('1081-3/02', 'Torrefação e moagem de café');
INSERT INTO fornecedor.atividade_economica VALUES ('1082-1/00', 'Fabricação de produtos à base de café');
INSERT INTO fornecedor.atividade_economica VALUES ('1091-1/00', 'Fabricação de produtos de panificação');
INSERT INTO fornecedor.atividade_economica VALUES ('1092-9/00', 'Fabricação de biscoitos e bolachas');
INSERT INTO fornecedor.atividade_economica VALUES ('1093-7/01', 'Fabricação de produtos derivados do cacau e de chocolates');
INSERT INTO fornecedor.atividade_economica VALUES ('1093-7/02', 'Fabricação de frutas cristalizadas, balas e semelhantes');
INSERT INTO fornecedor.atividade_economica VALUES ('1094-5/00', 'Fabricação de massas alimentícias');
INSERT INTO fornecedor.atividade_economica VALUES ('1095-3/00', 'Fabricação de especiarias, molhos, temperos e condimentos');
INSERT INTO fornecedor.atividade_economica VALUES ('1096-1/00', 'Fabricação de alimentos e pratos prontos');
INSERT INTO fornecedor.atividade_economica VALUES ('1099-6/01', 'Fabricação de vinagres');
INSERT INTO fornecedor.atividade_economica VALUES ('1099-6/02', 'Fabricação de pós alimentícios');
INSERT INTO fornecedor.atividade_economica VALUES ('1099-6/03', 'Fabricação de fermentos e leveduras');
INSERT INTO fornecedor.atividade_economica VALUES ('1099-6/04', 'Fabricação de gelo comum');
INSERT INTO fornecedor.atividade_economica VALUES ('1099-6/05', 'Fabricação de produtos para infusão (chá, mate, etc.)');
INSERT INTO fornecedor.atividade_economica VALUES ('1099-6/06', 'Fabricação de adoçantes naturais e artificiais');
INSERT INTO fornecedor.atividade_economica VALUES ('1099-6/99', 'Fabricação de outros produtos alimentícios não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('1111-9/01', 'Fabricação de aguardente de cana-de-açúcar');
INSERT INTO fornecedor.atividade_economica VALUES ('1111-9/02', 'Fabricação de outras aguardentes e bebidas destiladas');
INSERT INTO fornecedor.atividade_economica VALUES ('1112-7/00', 'Fabricação de vinho');
INSERT INTO fornecedor.atividade_economica VALUES ('1113-5/01', 'Fabricação de malte, inclusive malte uísque');
INSERT INTO fornecedor.atividade_economica VALUES ('1113-5/02', 'Fabricação de cervejas e chopes');
INSERT INTO fornecedor.atividade_economica VALUES ('1121-6/00', 'Fabricação de águas envasadas');
INSERT INTO fornecedor.atividade_economica VALUES ('1122-4/01', 'Fabricação de refrigerantes');
INSERT INTO fornecedor.atividade_economica VALUES ('1122-4/02', 'Fabricação de chá mate e outros chás prontos para consumo');
INSERT INTO fornecedor.atividade_economica VALUES ('1122-4/03', 'Fabricação de refrescos, xaropes e pós para refrescos, exceto refrescos de frutas');
INSERT INTO fornecedor.atividade_economica VALUES ('1122-4/99', 'Fabricação de outras bebidas não-alcoólicas não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('1210-7/00', 'Processamento industrial do fumo');
INSERT INTO fornecedor.atividade_economica VALUES ('1220-4/01', 'Fabricação de cigarros');
INSERT INTO fornecedor.atividade_economica VALUES ('1220-4/02', 'Fabricação de cigarrilhas e charutos');
INSERT INTO fornecedor.atividade_economica VALUES ('1220-4/03', 'Fabricação de filtros para cigarros');
INSERT INTO fornecedor.atividade_economica VALUES ('1220-4/99', 'Fabricação de outros produtos do fumo, exceto cigarros, cigarrilhas e charutos');
INSERT INTO fornecedor.atividade_economica VALUES ('1311-1/00', 'Preparação e fiação de fibras de algodão');
INSERT INTO fornecedor.atividade_economica VALUES ('1312-0/00', 'Preparação e fiação de fibras têxteis naturais, exceto algodão');
INSERT INTO fornecedor.atividade_economica VALUES ('1313-8/00', 'Fiação de fibras artificiais e sintéticas');
INSERT INTO fornecedor.atividade_economica VALUES ('1314-6/00', 'Fabricação de linhas para costurar e bordar');
INSERT INTO fornecedor.atividade_economica VALUES ('1321-9/00', 'Tecelagem de fios de algodão');
INSERT INTO fornecedor.atividade_economica VALUES ('1322-7/00', 'Tecelagem de fios de fibras têxteis naturais, exceto algodão');
INSERT INTO fornecedor.atividade_economica VALUES ('1323-5/00', 'Tecelagem de fios de fibras artificiais e sintéticas');
INSERT INTO fornecedor.atividade_economica VALUES ('1330-8/00', 'Fabricação de tecidos de malha');
INSERT INTO fornecedor.atividade_economica VALUES ('1340-5/01', 'Estamparia e texturização em fios, tecidos, artefatos têxteis e peças do vestuário');
INSERT INTO fornecedor.atividade_economica VALUES ('1340-5/02', 'Alvejamento, tingimento e torção em fios, tecidos, artefatos têxteis e peças do vestuário');
INSERT INTO fornecedor.atividade_economica VALUES ('1340-5/99', 'Outros serviços de acabamento em fios, tecidos, artefatos têxteis e peças do vestuário');
INSERT INTO fornecedor.atividade_economica VALUES ('1351-1/00', 'Fabricação de artefatos têxteis para uso doméstico');
INSERT INTO fornecedor.atividade_economica VALUES ('1352-9/00', 'Fabricação de artefatos de tapeçaria');
INSERT INTO fornecedor.atividade_economica VALUES ('1353-7/00', 'Fabricação de artefatos de cordoaria');
INSERT INTO fornecedor.atividade_economica VALUES ('1354-5/00', 'Fabricação de tecidos especiais, inclusive artefatos');
INSERT INTO fornecedor.atividade_economica VALUES ('1359-6/00', 'Fabricação de outros produtos têxteis não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('1411-8/01', 'Confecção de roupas íntimas');
INSERT INTO fornecedor.atividade_economica VALUES ('1411-8/02', 'Facção de roupas íntimas');
INSERT INTO fornecedor.atividade_economica VALUES ('1412-6/01', 'Confecção de peças do vestuário, exceto roupas íntimas e as confeccionadas sob medida');
INSERT INTO fornecedor.atividade_economica VALUES ('1412-6/02', 'Confecção, sob medida, de peças do vestuário, exceto roupas íntimas');
INSERT INTO fornecedor.atividade_economica VALUES ('1412-6/03', 'Facção de peças do vestuário, exceto roupas íntimas');
INSERT INTO fornecedor.atividade_economica VALUES ('1413-4/01', 'Confecção de roupas profissionais, exceto sob medida');
INSERT INTO fornecedor.atividade_economica VALUES ('1413-4/02', 'Confecção, sob medida, de roupas profissionais');
INSERT INTO fornecedor.atividade_economica VALUES ('1413-4/03', 'Facção de roupas profissionais');
INSERT INTO fornecedor.atividade_economica VALUES ('1414-2/00', 'Fabricação de acessórios do vestuário, exceto para segurança e proteção');
INSERT INTO fornecedor.atividade_economica VALUES ('1421-5/00', 'Fabricação de meias');
INSERT INTO fornecedor.atividade_economica VALUES ('1422-3/00', 'Fabricação de artigos do vestuário, produzidos em malharias e tricotagens, exceto meias');
INSERT INTO fornecedor.atividade_economica VALUES ('1510-6/00', 'Curtimento e outras preparações de couro');
INSERT INTO fornecedor.atividade_economica VALUES ('1521-1/00', 'Fabricação de artigos para viagem, bolsas e semelhantes de qualquer material');
INSERT INTO fornecedor.atividade_economica VALUES ('1529-7/00', 'Fabricação de artefatos de couro não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('1531-9/01', 'Fabricação de calçados de couro');
INSERT INTO fornecedor.atividade_economica VALUES ('1531-9/02', 'Acabamento de calçados de couro sob contrato');
INSERT INTO fornecedor.atividade_economica VALUES ('1532-7/00', 'Fabricação de tênis de qualquer material');
INSERT INTO fornecedor.atividade_economica VALUES ('1533-5/00', 'Fabricação de calçados de material sintético');
INSERT INTO fornecedor.atividade_economica VALUES ('1539-4/00', 'Fabricação de calçados de materiais não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('1540-8/00', 'Fabricação de partes para calçados, de qualquer material');
INSERT INTO fornecedor.atividade_economica VALUES ('1610-2/01', 'Serrarias com desdobramento de madeira');
INSERT INTO fornecedor.atividade_economica VALUES ('1610-2/02', 'Serrarias sem desdobramento de madeira');
INSERT INTO fornecedor.atividade_economica VALUES ('1621-8/00', 'Fabricação de madeira laminada e de chapas de madeira compensada, prensada e aglomerada');
INSERT INTO fornecedor.atividade_economica VALUES ('1622-6/01', 'Fabricação de casas de madeira pré-fabricadas');
INSERT INTO fornecedor.atividade_economica VALUES ('1622-6/02', 'Fabricação de esquadrias de madeira e de peças de madeira para instalações industriais e comerciais');
INSERT INTO fornecedor.atividade_economica VALUES ('1622-6/99', 'Fabricação de outros artigos de carpintaria para construção');
INSERT INTO fornecedor.atividade_economica VALUES ('1623-4/00', 'Fabricação de artefatos de tanoaria e de embalagens de madeira');
INSERT INTO fornecedor.atividade_economica VALUES ('1629-3/01', 'Fabricação de artefatos diversos de madeira, exceto móveis');
INSERT INTO fornecedor.atividade_economica VALUES ('1629-3/02', 'Fabricação de artefatos diversos de cortiça, bambu, palha, vime e outros materiais trançados, exceto móveis');
INSERT INTO fornecedor.atividade_economica VALUES ('1710-9/00', 'Fabricação de celulose e outras pastas para a fabricação de papel');
INSERT INTO fornecedor.atividade_economica VALUES ('1721-4/00', 'Fabricação de papel');
INSERT INTO fornecedor.atividade_economica VALUES ('1722-2/00', 'Fabricação de cartolina e papel-cartão');
INSERT INTO fornecedor.atividade_economica VALUES ('1731-1/00', 'Fabricação de embalagens de papel');
INSERT INTO fornecedor.atividade_economica VALUES ('1732-0/00', 'Fabricação de embalagens de cartolina e papel-cartão');
INSERT INTO fornecedor.atividade_economica VALUES ('1733-8/00', 'Fabricação de chapas e de embalagens de papelão ondulado');
INSERT INTO fornecedor.atividade_economica VALUES ('1741-9/01', 'Fabricação de formulários contínuos');
INSERT INTO fornecedor.atividade_economica VALUES ('1741-9/02', 'Fabricação de produtos de papel, cartolina, papel-cartão e papelão ondulado para uso industrial, comercial e de escritório, exceto formulário contínuo');
INSERT INTO fornecedor.atividade_economica VALUES ('1742-7/01', 'Fabricação de fraldas descartáveis');
INSERT INTO fornecedor.atividade_economica VALUES ('1742-7/02', 'Fabricação de absorventes higiênicos');
INSERT INTO fornecedor.atividade_economica VALUES ('1742-7/99', 'Fabricação de produtos de papel para uso doméstico e higiênico-sanitário não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('1749-4/00', 'Fabricação de produtos de pastas celulósicas, papel, cartolina, papel-cartão e papelão ondulado não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('1811-3/01', 'Impressão de jornais');
INSERT INTO fornecedor.atividade_economica VALUES ('1811-3/02', 'Impressão de livros, revistas e outras publicações periódicas');
INSERT INTO fornecedor.atividade_economica VALUES ('1812-1/00', 'Impressão de material de segurança');
INSERT INTO fornecedor.atividade_economica VALUES ('1813-0/01', 'Impressão de material para uso publicitário');
INSERT INTO fornecedor.atividade_economica VALUES ('1813-0/99', 'Impressão de material para outros usos');
INSERT INTO fornecedor.atividade_economica VALUES ('1821-1/00', 'Serviços de pré-impressão');
INSERT INTO fornecedor.atividade_economica VALUES ('1822-9/00', 'Serviços de acabamentos gráficos');
INSERT INTO fornecedor.atividade_economica VALUES ('1830-0/01', 'Reprodução de som em qualquer suporte');
INSERT INTO fornecedor.atividade_economica VALUES ('1830-0/02', 'Reprodução de vídeo em qualquer suporte');
INSERT INTO fornecedor.atividade_economica VALUES ('1830-0/03', 'Reprodução de software em qualquer suporte');
INSERT INTO fornecedor.atividade_economica VALUES ('1910-1/00', 'Coquerias');
INSERT INTO fornecedor.atividade_economica VALUES ('1921-7/00', 'Fabricação de produtos do refino de petróleo');
INSERT INTO fornecedor.atividade_economica VALUES ('1922-5/01', 'Formulação de combustíveis');
INSERT INTO fornecedor.atividade_economica VALUES ('1922-5/02', 'Rerrefino de óleos lubrificantes');
INSERT INTO fornecedor.atividade_economica VALUES ('1922-5/99', 'Fabricação de outros produtos derivados do petróleo, exceto produtos do refino');
INSERT INTO fornecedor.atividade_economica VALUES ('1931-4/00', 'Fabricação de álcool');
INSERT INTO fornecedor.atividade_economica VALUES ('1932-2/00', 'Fabricação de biocombustíveis, exceto álcool');
INSERT INTO fornecedor.atividade_economica VALUES ('2011-8/00', 'Fabricação de cloro e álcalis');
INSERT INTO fornecedor.atividade_economica VALUES ('2012-6/00', 'Fabricação de intermediários para fertilizantes');
INSERT INTO fornecedor.atividade_economica VALUES ('2013-4/00', 'Fabricação de adubos e fertilizantes');
INSERT INTO fornecedor.atividade_economica VALUES ('2014-2/00', 'Fabricação de gases industriais');
INSERT INTO fornecedor.atividade_economica VALUES ('2019-3/01', 'Elaboração de combustíveis nucleares');
INSERT INTO fornecedor.atividade_economica VALUES ('2019-3/99', 'Fabricação de outros produtos químicos inorgânicos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2021-5/00', 'Fabricação de produtos petroquímicos básicos');
INSERT INTO fornecedor.atividade_economica VALUES ('2022-3/00', 'Fabricação de intermediários para plastificantes, resinas e fibras');
INSERT INTO fornecedor.atividade_economica VALUES ('2029-1/00', 'Fabricação de produtos químicos orgânicos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2031-2/00', 'Fabricação de resinas termoplásticas');
INSERT INTO fornecedor.atividade_economica VALUES ('2032-1/00', 'Fabricação de resinas termofixas');
INSERT INTO fornecedor.atividade_economica VALUES ('2033-9/00', 'Fabricação de elastômeros');
INSERT INTO fornecedor.atividade_economica VALUES ('2040-1/00', 'Fabricação de fibras artificiais e sintéticas');
INSERT INTO fornecedor.atividade_economica VALUES ('2051-7/00', 'Fabricação de defensivos agrícolas');
INSERT INTO fornecedor.atividade_economica VALUES ('2052-5/00', 'Fabricação de desinfestantes domissanitários');
INSERT INTO fornecedor.atividade_economica VALUES ('2061-4/00', 'Fabricação de sabões e detergentes sintéticos');
INSERT INTO fornecedor.atividade_economica VALUES ('2062-2/00', 'Fabricação de produtos de limpeza e polimento');
INSERT INTO fornecedor.atividade_economica VALUES ('2063-1/00', 'Fabricação de cosméticos, produtos de perfumaria e de higiene pessoal');
INSERT INTO fornecedor.atividade_economica VALUES ('2071-1/00', 'Fabricação de tintas, vernizes, esmaltes e lacas');
INSERT INTO fornecedor.atividade_economica VALUES ('2072-0/00', 'Fabricação de tintas de impressão');
INSERT INTO fornecedor.atividade_economica VALUES ('2073-8/00', 'Fabricação de impermeabilizantes, solventes e produtos afins');
INSERT INTO fornecedor.atividade_economica VALUES ('2091-6/00', 'Fabricação de adesivos e selantes');
INSERT INTO fornecedor.atividade_economica VALUES ('2092-4/01', 'Fabricação de pólvoras, explosivos e detonantes');
INSERT INTO fornecedor.atividade_economica VALUES ('2092-4/02', 'Fabricação de artigos pirotécnicos');
INSERT INTO fornecedor.atividade_economica VALUES ('2092-4/03', 'Fabricação de fósforos de segurança');
INSERT INTO fornecedor.atividade_economica VALUES ('2093-2/00', 'Fabricação de aditivos de uso industrial');
INSERT INTO fornecedor.atividade_economica VALUES ('2094-1/00', 'Fabricação de catalisadores');
INSERT INTO fornecedor.atividade_economica VALUES ('2099-1/01', 'Fabricação de chapas, filmes, papéis e outros materiais e produtos químicos para fotografia');
INSERT INTO fornecedor.atividade_economica VALUES ('2099-1/99', 'Fabricação de outros produtos químicos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2110-6/00', 'Fabricação de produtos farmoquímicos');
INSERT INTO fornecedor.atividade_economica VALUES ('2121-1/01', 'Fabricação de medicamentos alopáticos para uso humano');
INSERT INTO fornecedor.atividade_economica VALUES ('2121-1/02', 'Fabricação de medicamentos homeopáticos para uso humano');
INSERT INTO fornecedor.atividade_economica VALUES ('2121-1/03', 'Fabricação de medicamentos fitoterápicos para uso humano');
INSERT INTO fornecedor.atividade_economica VALUES ('2122-0/00', 'Fabricação de medicamentos para uso veterinário');
INSERT INTO fornecedor.atividade_economica VALUES ('2123-8/00', 'Fabricação de preparações farmacêuticas');
INSERT INTO fornecedor.atividade_economica VALUES ('2211-1/00', 'Fabricação de pneumáticos e de câmaras-de-ar');
INSERT INTO fornecedor.atividade_economica VALUES ('2212-9/00', 'Reforma de pneumáticos usados');
INSERT INTO fornecedor.atividade_economica VALUES ('2219-6/00', 'Fabricação de artefatos de borracha não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2221-8/00', 'Fabricação de laminados planos e tubulares de material plástico');
INSERT INTO fornecedor.atividade_economica VALUES ('2222-6/00', 'Fabricação de embalagens de material plástico');
INSERT INTO fornecedor.atividade_economica VALUES ('2223-4/00', 'Fabricação de tubos e acessórios de material plástico para uso na construção');
INSERT INTO fornecedor.atividade_economica VALUES ('2229-3/01', 'Fabricação de artefatos de material plástico para uso pessoal e doméstico');
INSERT INTO fornecedor.atividade_economica VALUES ('2229-3/02', 'Fabricação de artefatos de material plástico para usos industriais');
INSERT INTO fornecedor.atividade_economica VALUES ('2229-3/03', 'Fabricação de artefatos de material plástico para uso na construção, exceto tubos e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2229-3/99', 'Fabricação de artefatos de material plástico para outros usos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2311-7/00', 'Fabricação de vidro plano e de segurança');
INSERT INTO fornecedor.atividade_economica VALUES ('2312-5/00', 'Fabricação de embalagens de vidro');
INSERT INTO fornecedor.atividade_economica VALUES ('2319-2/00', 'Fabricação de artigos de vidro');
INSERT INTO fornecedor.atividade_economica VALUES ('2320-6/00', 'Fabricação de cimento');
INSERT INTO fornecedor.atividade_economica VALUES ('2330-3/01', 'Fabricação de estruturas pré-moldadas de concreto armado, em série e sob encomenda');
INSERT INTO fornecedor.atividade_economica VALUES ('2330-3/02', 'Fabricação de artefatos de cimento para uso na construção');
INSERT INTO fornecedor.atividade_economica VALUES ('2330-3/03', 'Fabricação de artefatos de fibrocimento para uso na construção');
INSERT INTO fornecedor.atividade_economica VALUES ('2330-3/04', 'Fabricação de casas pré-moldadas de concreto');
INSERT INTO fornecedor.atividade_economica VALUES ('2330-3/05', 'Preparação de massa de concreto e argamassa para construção');
INSERT INTO fornecedor.atividade_economica VALUES ('2330-3/99', 'Fabricação de outros artefatos e produtos de concreto, cimento, fibrocimento, gesso e materiais semelhantes');
INSERT INTO fornecedor.atividade_economica VALUES ('2341-9/00', 'Fabricação de produtos cerâmicos refratários');
INSERT INTO fornecedor.atividade_economica VALUES ('2342-7/01', 'Fabricação de azulejos e pisos');
INSERT INTO fornecedor.atividade_economica VALUES ('8424-8/00', 'Segurança e ordem pública');
INSERT INTO fornecedor.atividade_economica VALUES ('2342-7/02', 'Fabricação de artefatos de cerâmica e barro cozido para uso na construção, exceto azulejos e pisos');
INSERT INTO fornecedor.atividade_economica VALUES ('2349-4/01', 'Fabricação de material sanitário de cerâmica');
INSERT INTO fornecedor.atividade_economica VALUES ('2349-4/99', 'Fabricação de produtos cerâmicos não-refratários não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2391-5/01', 'Britamento de pedras, exceto associado à extração');
INSERT INTO fornecedor.atividade_economica VALUES ('2391-5/02', 'Aparelhamento de pedras para construção, exceto associado à extração');
INSERT INTO fornecedor.atividade_economica VALUES ('2391-5/03', 'Aparelhamento de placas e execução de trabalhos em mármore, granito, ardósia e outras pedras');
INSERT INTO fornecedor.atividade_economica VALUES ('2392-3/00', 'Fabricação de cal e gesso');
INSERT INTO fornecedor.atividade_economica VALUES ('2399-1/01', 'Decoração, lapidação, gravação, vitrificação e outros trabalhos em cerâmica, louça, vidro e cristal');
INSERT INTO fornecedor.atividade_economica VALUES ('2399-1/99', 'Fabricação de outros produtos de minerais não-metálicos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2411-3/00', 'Produção de ferro-gusa');
INSERT INTO fornecedor.atividade_economica VALUES ('2412-1/00', 'Produção de ferroligas');
INSERT INTO fornecedor.atividade_economica VALUES ('2421-1/00', 'Produção de semi-acabados de aço');
INSERT INTO fornecedor.atividade_economica VALUES ('2422-9/01', 'Produção de laminados planos de aço ao carbono, revestidos ou não');
INSERT INTO fornecedor.atividade_economica VALUES ('2422-9/02', 'Produção de laminados planos de aços especiais');
INSERT INTO fornecedor.atividade_economica VALUES ('2423-7/01', 'Produção de tubos de aço sem costura');
INSERT INTO fornecedor.atividade_economica VALUES ('2423-7/02', 'Produção de laminados longos de aço, exceto tubos');
INSERT INTO fornecedor.atividade_economica VALUES ('2424-5/01', 'Produção de arames de aço');
INSERT INTO fornecedor.atividade_economica VALUES ('2424-5/02', 'Produção de relaminados, trefilados e perfilados de aço, exceto arames');
INSERT INTO fornecedor.atividade_economica VALUES ('2431-8/00', 'Produção de tubos de aço com costura');
INSERT INTO fornecedor.atividade_economica VALUES ('2439-3/00', 'Produção de outros tubos de ferro e aço');
INSERT INTO fornecedor.atividade_economica VALUES ('2441-5/01', 'Produção de alumínio e suas ligas em formas primárias');
INSERT INTO fornecedor.atividade_economica VALUES ('2441-5/02', 'Produção de laminados de alumínio');
INSERT INTO fornecedor.atividade_economica VALUES ('2442-3/00', 'Metalurgia dos metais preciosos');
INSERT INTO fornecedor.atividade_economica VALUES ('2443-1/00', 'Metalurgia do cobre');
INSERT INTO fornecedor.atividade_economica VALUES ('2449-1/01', 'Produção de zinco em formas primárias');
INSERT INTO fornecedor.atividade_economica VALUES ('2449-1/02', 'Produção de laminados de zinco');
INSERT INTO fornecedor.atividade_economica VALUES ('2449-1/03', 'Produção de soldas e ânodos para galvanoplastia');
INSERT INTO fornecedor.atividade_economica VALUES ('2449-1/99', 'Metalurgia de outros metais não-ferrosos e suas ligas não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2451-2/00', 'Fundição de ferro e aço');
INSERT INTO fornecedor.atividade_economica VALUES ('2452-1/00', 'Fundição de metais não-ferrosos e suas ligas');
INSERT INTO fornecedor.atividade_economica VALUES ('2511-0/00', 'Fabricação de estruturas metálicas');
INSERT INTO fornecedor.atividade_economica VALUES ('2512-8/00', 'Fabricação de esquadrias de metal');
INSERT INTO fornecedor.atividade_economica VALUES ('2513-6/00', 'Fabricação de obras de caldeiraria pesada');
INSERT INTO fornecedor.atividade_economica VALUES ('2521-7/00', 'Fabricação de tanques, reservatórios metálicos e caldeiras para aquecimento central');
INSERT INTO fornecedor.atividade_economica VALUES ('2522-5/00', 'Fabricação de caldeiras geradoras de vapor, exceto para aquecimento central e para veículos');
INSERT INTO fornecedor.atividade_economica VALUES ('2531-4/01', 'Produção de forjados de aço');
INSERT INTO fornecedor.atividade_economica VALUES ('2531-4/02', 'Produção de forjados de metais não-ferrosos e suas ligas');
INSERT INTO fornecedor.atividade_economica VALUES ('2532-2/01', 'Produção de artefatos estampados de metal');
INSERT INTO fornecedor.atividade_economica VALUES ('2532-2/02', 'Metalurgia do pó');
INSERT INTO fornecedor.atividade_economica VALUES ('2539-0/00', 'Serviços de usinagem, solda, tratamento e revestimento em metais');
INSERT INTO fornecedor.atividade_economica VALUES ('2541-1/00', 'Fabricação de artigos de cutelaria');
INSERT INTO fornecedor.atividade_economica VALUES ('2542-0/00', 'Fabricação de artigos de serralheria, exceto esquadrias');
INSERT INTO fornecedor.atividade_economica VALUES ('2543-8/00', 'Fabricação de ferramentas');
INSERT INTO fornecedor.atividade_economica VALUES ('2550-1/01', 'Fabricação de equipamento bélico pesado, exceto veículos militares de combate');
INSERT INTO fornecedor.atividade_economica VALUES ('2550-1/02', 'Fabricação de armas de fogo e munições');
INSERT INTO fornecedor.atividade_economica VALUES ('2591-8/00', 'Fabricação de embalagens metálicas');
INSERT INTO fornecedor.atividade_economica VALUES ('2592-6/01', 'Fabricação de produtos de trefilados de metal padronizados');
INSERT INTO fornecedor.atividade_economica VALUES ('2592-6/02', 'Fabricação de produtos de trefilados de metal, exceto padronizados');
INSERT INTO fornecedor.atividade_economica VALUES ('2593-4/00', 'Fabricação de artigos de metal para uso doméstico e pessoal');
INSERT INTO fornecedor.atividade_economica VALUES ('2599-3/01', 'Serviços de confecção de armações metálicas para a construção');
INSERT INTO fornecedor.atividade_economica VALUES ('2599-3/99', 'Fabricação de outros produtos de metal não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2610-8/00', 'Fabricação de componentes eletrônicos');
INSERT INTO fornecedor.atividade_economica VALUES ('2621-3/00', 'Fabricação de equipamentos de informática');
INSERT INTO fornecedor.atividade_economica VALUES ('2622-1/00', 'Fabricação de periféricos para equipamentos de informática');
INSERT INTO fornecedor.atividade_economica VALUES ('2631-1/00', 'Fabricação de equipamentos transmissores de comunicação, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2632-9/00', 'Fabricação de aparelhos telefônicos e de outros equipamentos de comunicação, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2640-0/00', 'Fabricação de aparelhos de recepção, reprodução, gravação e amplificação de áudio e vídeo');
INSERT INTO fornecedor.atividade_economica VALUES ('2651-5/00', 'Fabricação de aparelhos e equipamentos de medida, teste e controle');
INSERT INTO fornecedor.atividade_economica VALUES ('2652-3/00', 'Fabricação de cronômetros e relógios');
INSERT INTO fornecedor.atividade_economica VALUES ('2660-4/00', 'Fabricação de aparelhos eletromédicos e eletroterapêuticos e equipamentos de irradiação');
INSERT INTO fornecedor.atividade_economica VALUES ('2670-1/01', 'Fabricação de equipamentos e instrumentos ópticos, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2670-1/02', 'Fabricação de aparelhos fotográficos e cinematográficos, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2680-9/00', 'Fabricação de mídias virgens, magnéticas e ópticas');
INSERT INTO fornecedor.atividade_economica VALUES ('2710-4/01', 'Fabricação de geradores de corrente contínua e alternada, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2710-4/02', 'Fabricação de transformadores, indutores, conversores, sincronizadores e semelhantes, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2710-4/03', 'Fabricação de motores elétricos, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2721-0/00', 'Fabricação de pilhas, baterias e acumuladores elétricos, exceto para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('2722-8/01', 'Fabricação de baterias e acumuladores para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('2722-8/02', 'Recondicionamento de baterias e acumuladores para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('2731-7/00', 'Fabricação de aparelhos e equipamentos para distribuição e controle de energia elétrica');
INSERT INTO fornecedor.atividade_economica VALUES ('2732-5/00', 'Fabricação de material elétrico para instalações em circuito de consumo');
INSERT INTO fornecedor.atividade_economica VALUES ('2733-3/00', 'Fabricação de fios, cabos e condutores elétricos isolados');
INSERT INTO fornecedor.atividade_economica VALUES ('2740-6/01', 'Fabricação de lâmpadas');
INSERT INTO fornecedor.atividade_economica VALUES ('2740-6/02', 'Fabricação de luminárias e outros equipamentos de iluminação');
INSERT INTO fornecedor.atividade_economica VALUES ('2751-1/00', 'Fabricação de fogões, refrigeradores e máquinas de lavar e secar para uso doméstico, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2759-7/01', 'Fabricação de aparelhos elétricos de uso pessoal, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2759-7/99', 'Fabricação de outros aparelhos eletrodomésticos não especificados anteriormente, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2790-2/01', 'Fabricação de eletrodos, contatos e outros artigos de carvão e grafita para uso elétrico, eletroímãs e isoladores');
INSERT INTO fornecedor.atividade_economica VALUES ('2790-2/02', 'Fabricação de equipamentos para sinalização e alarme');
INSERT INTO fornecedor.atividade_economica VALUES ('2790-2/99', 'Fabricação de outros equipamentos e aparelhos elétricos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2811-9/00', 'Fabricação de motores e turbinas, peças e acessórios, exceto para aviões e veículos rodoviários');
INSERT INTO fornecedor.atividade_economica VALUES ('2812-7/00', 'Fabricação de equipamentos hidráulicos e pneumáticos, peças e acessórios, exceto válvulas');
INSERT INTO fornecedor.atividade_economica VALUES ('2813-5/00', 'Fabricação de válvulas, registros e dispositivos semelhantes, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2814-3/01', 'Fabricação de compressores para uso industrial, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2814-3/02', 'Fabricação de compressores para uso não industrial, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2815-1/01', 'Fabricação de rolamentos para fins industriais');
INSERT INTO fornecedor.atividade_economica VALUES ('2815-1/02', 'Fabricação de equipamentos de transmissão para fins industriais, exceto rolamentos');
INSERT INTO fornecedor.atividade_economica VALUES ('2821-6/01', 'Fabricação de fornos industriais, aparelhos e equipamentos não-elétricos para instalações térmicas, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2821-6/02', 'Fabricação de estufas e fornos elétricos para fins industriais, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2822-4/01', 'Fabricação de máquinas, equipamentos e aparelhos para transporte e elevação de pessoas, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2822-4/02', 'Fabricação de máquinas, equipamentos e aparelhos para transporte e elevação de cargas, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2823-2/00', 'Fabricação de máquinas e aparelhos de refrigeração e ventilação para uso industrial e comercial, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2824-1/01', 'Fabricação de aparelhos e equipamentos de ar condicionado para uso industrial');
INSERT INTO fornecedor.atividade_economica VALUES ('2824-1/02', 'Fabricação de aparelhos e equipamentos de ar condicionado para uso não-industrial');
INSERT INTO fornecedor.atividade_economica VALUES ('2825-9/00', 'Fabricação de máquinas e equipamentos para saneamento básico e ambiental, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2829-1/01', 'Fabricação de máquinas de escrever, calcular e outros equipamentos não-eletrônicos para escritório, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2829-1/99', 'Fabricação de outras máquinas e equipamentos de uso geral não especificados anteriormente, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2831-3/00', 'Fabricação de tratores agrícolas, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2832-1/00', 'Fabricação de equipamentos para irrigação agrícola, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2833-0/00', 'Fabricação de máquinas e equipamentos para a agricultura e pecuária, peças e acessórios, exceto para irrigação');
INSERT INTO fornecedor.atividade_economica VALUES ('2840-2/00', 'Fabricação de máquinas-ferramenta, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2851-8/00', 'Fabricação de máquinas e equipamentos para a prospecção e extração de petróleo, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2852-6/00', 'Fabricação de outras máquinas e equipamentos para uso na extração mineral, peças e acessórios, exceto na extração de petróleo');
INSERT INTO fornecedor.atividade_economica VALUES ('2853-4/00', 'Fabricação de tratores, peças e acessórios, exceto agrícolas');
INSERT INTO fornecedor.atividade_economica VALUES ('2854-2/00', 'Fabricação de máquinas e equipamentos para terraplenagem, pavimentação e construção, peças e acessórios, exceto tratores');
INSERT INTO fornecedor.atividade_economica VALUES ('2861-5/00', 'Fabricação de máquinas para a indústria metalúrgica, peças e acessórios, exceto máquinas-ferramenta');
INSERT INTO fornecedor.atividade_economica VALUES ('2862-3/00', 'Fabricação de máquinas e equipamentos para as indústrias de alimentos, bebidas e fumo, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2863-1/00', 'Fabricação de máquinas e equipamentos para a indústria têxtil, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2864-0/00', 'Fabricação de máquinas e equipamentos para as indústrias do vestuário, do couro e de calçados, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2865-8/00', 'Fabricação de máquinas e equipamentos para as indústrias de celulose, papel e papelão e artefatos, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2866-6/00', 'Fabricação de máquinas e equipamentos para a indústria do plástico, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2869-1/00', 'Fabricação de máquinas e equipamentos para uso industrial específico não especificados anteriormente, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('2910-7/01', 'Fabricação de automóveis, camionetas e utilitários');
INSERT INTO fornecedor.atividade_economica VALUES ('2910-7/02', 'Fabricação de chassis com motor para automóveis, camionetas e utilitários');
INSERT INTO fornecedor.atividade_economica VALUES ('2910-7/03', 'Fabricação de motores para automóveis, camionetas e utilitários');
INSERT INTO fornecedor.atividade_economica VALUES ('2920-4/01', 'Fabricação de caminhões e ônibus');
INSERT INTO fornecedor.atividade_economica VALUES ('2920-4/02', 'Fabricação de motores para caminhões e ônibus');
INSERT INTO fornecedor.atividade_economica VALUES ('2930-1/01', 'Fabricação de cabines, carrocerias e reboques para caminhões');
INSERT INTO fornecedor.atividade_economica VALUES ('2930-1/02', 'Fabricação de carrocerias para ônibus');
INSERT INTO fornecedor.atividade_economica VALUES ('2930-1/03', 'Fabricação de cabines, carrocerias e reboques para outros veículos automotores, exceto caminhões e ônibus');
INSERT INTO fornecedor.atividade_economica VALUES ('2941-7/00', 'Fabricação de peças e acessórios para o sistema motor de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('2942-5/00', 'Fabricação de peças e acessórios para os sistemas de marcha e transmissão de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('2943-3/00', 'Fabricação de peças e acessórios para o sistema de freios de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('2944-1/00', 'Fabricação de peças e acessórios para o sistema de direção e suspensão de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('2945-0/00', 'Fabricação de material elétrico e eletrônico para veículos automotores, exceto baterias');
INSERT INTO fornecedor.atividade_economica VALUES ('2949-2/01', 'Fabricação de bancos e estofados para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('2949-2/99', 'Fabricação de outras peças e acessórios para veículos automotores não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('2950-6/00', 'Recondicionamento e recuperação de motores para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('3011-3/01', 'Construção de embarcações de grande porte');
INSERT INTO fornecedor.atividade_economica VALUES ('3011-3/02', 'Construção de embarcações para uso comercial e para usos especiais, exceto de grande porte');
INSERT INTO fornecedor.atividade_economica VALUES ('3012-1/00', 'Construção de embarcações para esporte e lazer');
INSERT INTO fornecedor.atividade_economica VALUES ('3021-1/00', 'Manutenção e reparação de embarcações e estruturas flutuantes');
INSERT INTO fornecedor.atividade_economica VALUES ('3022-9/00', 'Manutenção e reparação de embarcações para esporte e lazer');
INSERT INTO fornecedor.atividade_economica VALUES ('3031-8/00', 'Fabricação de locomotivas, vagões e outros materiais rodantes');
INSERT INTO fornecedor.atividade_economica VALUES ('3032-6/00', 'Fabricação de peças e acessórios para veículos ferroviários');
INSERT INTO fornecedor.atividade_economica VALUES ('3041-5/00', 'Fabricação de aeronaves');
INSERT INTO fornecedor.atividade_economica VALUES ('3042-3/00', 'Fabricação de turbinas, motores e outros componentes e peças para aeronaves');
INSERT INTO fornecedor.atividade_economica VALUES ('3050-4/00', 'Fabricação de veículos militares de combate');
INSERT INTO fornecedor.atividade_economica VALUES ('3091-1/00', 'Fabricação de motocicletas, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('3092-0/00', 'Fabricação de bicicletas e triciclos não-motorizados, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('3099-7/00', 'Fabricação de equipamentos de transporte não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('3101-2/00', 'Fabricação de móveis com predominância de madeira');
INSERT INTO fornecedor.atividade_economica VALUES ('3102-1/00', 'Fabricação de móveis com predominância de metal');
INSERT INTO fornecedor.atividade_economica VALUES ('8425-6/00', 'Defesa Civil');
INSERT INTO fornecedor.atividade_economica VALUES ('3103-9/00', 'Fabricação de móveis de outros materiais, exceto madeira e metal');
INSERT INTO fornecedor.atividade_economica VALUES ('3104-7/00', 'Fabricação de colchões');
INSERT INTO fornecedor.atividade_economica VALUES ('3211-6/01', 'Lapidação de gemas');
INSERT INTO fornecedor.atividade_economica VALUES ('3211-6/02', 'Fabricação de artefatos de joalheria e ourivesaria');
INSERT INTO fornecedor.atividade_economica VALUES ('3211-6/03', 'Cunhagem de moedas e medalhas');
INSERT INTO fornecedor.atividade_economica VALUES ('3212-4/00', 'Fabricação de bijuterias e artefatos semelhantes');
INSERT INTO fornecedor.atividade_economica VALUES ('3220-5/00', 'Fabricação de instrumentos musicais, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('3230-2/00', 'Fabricação de artefatos para pesca e esporte');
INSERT INTO fornecedor.atividade_economica VALUES ('3240-0/01', 'Fabricação de jogos eletrônicos');
INSERT INTO fornecedor.atividade_economica VALUES ('3240-0/02', 'Fabricação de mesas de bilhar, de sinuca e acessórios não associada à locação');
INSERT INTO fornecedor.atividade_economica VALUES ('3240-0/03', 'Fabricação de mesas de bilhar, de sinuca e acessórios associada à locação');
INSERT INTO fornecedor.atividade_economica VALUES ('3240-0/99', 'Fabricação de outros brinquedos e jogos recreativos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('3250-7/01', 'Fabricação de instrumentos não-eletrônicos e utensílios para uso médico, cirúrgico, odontológico e de laboratório');
INSERT INTO fornecedor.atividade_economica VALUES ('3250-7/02', 'Fabricação de mobiliário para uso médico, cirúrgico, odontológico e de laboratório');
INSERT INTO fornecedor.atividade_economica VALUES ('3250-7/03', 'Fabricação de aparelhos e utensílios para correção de defeitos físicos e aparelhos ortopédicos em geral sob encomenda');
INSERT INTO fornecedor.atividade_economica VALUES ('3250-7/04', 'Fabricação de aparelhos e utensílios para correção de defeitos físicos e aparelhos ortopédicos em geral, exceto sob encomenda');
INSERT INTO fornecedor.atividade_economica VALUES ('3250-7/05', 'Fabricação de materiais para medicina e odontologia');
INSERT INTO fornecedor.atividade_economica VALUES ('3250-7/06', 'Serviços de prótese dentária');
INSERT INTO fornecedor.atividade_economica VALUES ('3250-7/07', 'Fabricação de artigos ópticos');
INSERT INTO fornecedor.atividade_economica VALUES ('3250-7/08', 'Fabricação de artefatos de tecido não tecido para uso odonto-médico-hospitalar');
INSERT INTO fornecedor.atividade_economica VALUES ('3291-4/00', 'Fabricação de escovas, pincéis e vassouras');
INSERT INTO fornecedor.atividade_economica VALUES ('3292-2/01', 'Fabricação de roupas de proteção e segurança e resistentes a fogo');
INSERT INTO fornecedor.atividade_economica VALUES ('3292-2/02', 'Fabricação de equipamentos e acessórios para segurança pessoal e profissional');
INSERT INTO fornecedor.atividade_economica VALUES ('3299-0/01', 'Fabricação de guarda-chuvas e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('3299-0/02', 'Fabricação de canetas, lápis e outros artigos para escritório');
INSERT INTO fornecedor.atividade_economica VALUES ('3299-0/03', 'Fabricação de letras, letreiros e placas de qualquer material, exceto luminosos');
INSERT INTO fornecedor.atividade_economica VALUES ('3299-0/04', 'Fabricação de painéis e letreiros luminosos');
INSERT INTO fornecedor.atividade_economica VALUES ('3299-0/05', 'Fabricação de aviamentos para costura');
INSERT INTO fornecedor.atividade_economica VALUES ('3299-0/99', 'Fabricação de produtos diversos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('3311-2/00', 'Manutenção e reparação de tanques, reservatórios metálicos e caldeiras, exceto para veículos');
INSERT INTO fornecedor.atividade_economica VALUES ('3312-1/01', 'Manutenção e reparação de equipamentos transmissores de comunicação');
INSERT INTO fornecedor.atividade_economica VALUES ('3312-1/02', 'Manutenção e reparação de aparelhos e instrumentos de medida, teste e controle');
INSERT INTO fornecedor.atividade_economica VALUES ('3312-1/03', 'Manutenção e reparação de aparelhos eletromédicos e eletroterapêuticos e equipamentos de irradiação');
INSERT INTO fornecedor.atividade_economica VALUES ('3312-1/04', 'Manutenção e reparação de equipamentos e instrumentos ópticos');
INSERT INTO fornecedor.atividade_economica VALUES ('3313-9/01', 'Manutenção e reparação de geradores, transformadores e motores elétricos');
INSERT INTO fornecedor.atividade_economica VALUES ('3313-9/02', 'Manutenção e reparação de baterias e acumuladores elétricos, exceto para veículos');
INSERT INTO fornecedor.atividade_economica VALUES ('3313-9/99', 'Manutenção e reparação de máquinas, aparelhos e materiais elétricos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/01', 'Manutenção e reparação de máquinas motrizes não-elétricas');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/02', 'Manutenção e reparação de equipamentos hidráulicos e pneumáticos, exceto válvulas');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/03', 'Manutenção e reparação de válvulas industriais');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/04', 'Manutenção e reparação de compressores');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/05', 'Manutenção e reparação de equipamentos de transmissão para fins industriais');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/06', 'Manutenção e reparação de máquinas, aparelhos e equipamentos para instalações térmicas');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/07', 'Manutenção e reparação de máquinas e aparelhos de refrigeração e ventilação para uso industrial e comercial');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/08', 'Manutenção e reparação de máquinas, equipamentos e aparelhos para transporte e elevação de cargas');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/09', 'Manutenção e reparação de máquinas de escrever, calcular e de outros equipamentos não-eletrônicos para escritório');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/10', 'Manutenção e reparação de máquinas e equipamentos para uso geral não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/11', 'Manutenção e reparação de máquinas e equipamentos para agricultura e pecuária');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/12', 'Manutenção e reparação de tratores agrícolas');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/13', 'Manutenção e reparação de máquinas-ferramenta');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/14', 'Manutenção e reparação de máquinas e equipamentos para a prospecção e extração de petróleo');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/15', 'Manutenção e reparação de máquinas e equipamentos para uso na extração mineral, exceto na extração de petróleo');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/16', 'Manutenção e reparação de tratores, exceto agrícolas');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/17', 'Manutenção e reparação de máquinas e equipamentos de terraplenagem, pavimentação e construção, exceto tratores');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/18', 'Manutenção e reparação de máquinas para a indústria metalúrgica, exceto máquinas-ferramenta');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/19', 'Manutenção e reparação de máquinas e equipamentos para as indústrias de alimentos, bebidas e fumo');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/20', 'Manutenção e reparação de máquinas e equipamentos para a indústria têxtil, do vestuário, do couro e calçados');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/21', 'Manutenção e reparação de máquinas e aparelhos para a indústria de celulose, papel e papelão e artefatos');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/22', 'Manutenção e reparação de máquinas e aparelhos para a indústria do plástico');
INSERT INTO fornecedor.atividade_economica VALUES ('3314-7/99', 'Manutenção e reparação de outras máquinas e equipamentos para usos industriais não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('3315-5/00', 'Manutenção e reparação de veículos ferroviários');
INSERT INTO fornecedor.atividade_economica VALUES ('3316-3/01', 'Manutenção e reparação de aeronaves, exceto a manutenção na pista');
INSERT INTO fornecedor.atividade_economica VALUES ('3316-3/02', 'Manutenção de aeronaves na pista *');
INSERT INTO fornecedor.atividade_economica VALUES ('3319-8/00', 'Manutenção e reparação de equipamentos e produtos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('3321-0/00', 'Instalação de máquinas e equipamentos industriais');
INSERT INTO fornecedor.atividade_economica VALUES ('3329-5/01', 'Serviços de montagem de móveis de qualquer material');
INSERT INTO fornecedor.atividade_economica VALUES ('3329-5/99', 'Instalação de outros equipamentos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('3511-5/00', 'Geração de energia elétrica');
INSERT INTO fornecedor.atividade_economica VALUES ('3512-3/00', 'Transmissão de energia elétrica');
INSERT INTO fornecedor.atividade_economica VALUES ('3513-1/00', 'Comércio atacadista de energia elétrica');
INSERT INTO fornecedor.atividade_economica VALUES ('3514-0/00', 'Distribuição de energia elétrica');
INSERT INTO fornecedor.atividade_economica VALUES ('3520-4/01', 'Produção de gás; processamento de gás natural');
INSERT INTO fornecedor.atividade_economica VALUES ('3520-4/02', 'Distribuição de combustíveis gasosos por redes urbanas');
INSERT INTO fornecedor.atividade_economica VALUES ('3530-1/00', 'Produção e distribuição de vapor, água quente e ar condicionado');
INSERT INTO fornecedor.atividade_economica VALUES ('3600-6/01', 'Captação, tratamento e distribuição de água');
INSERT INTO fornecedor.atividade_economica VALUES ('3600-6/02', 'Distribuição de água por caminhões');
INSERT INTO fornecedor.atividade_economica VALUES ('3701-1/00', 'Gestão de redes de esgoto');
INSERT INTO fornecedor.atividade_economica VALUES ('3702-9/00', 'Atividades relacionadas a esgoto, exceto a gestão de redes');
INSERT INTO fornecedor.atividade_economica VALUES ('3811-4/00', 'Coleta de resíduos não-perigosos');
INSERT INTO fornecedor.atividade_economica VALUES ('3812-2/00', 'Coleta de resíduos perigosos');
INSERT INTO fornecedor.atividade_economica VALUES ('3821-1/00', 'Tratamento e disposição de resíduos não-perigosos');
INSERT INTO fornecedor.atividade_economica VALUES ('3822-0/00', 'Tratamento e disposição de resíduos perigosos');
INSERT INTO fornecedor.atividade_economica VALUES ('3831-9/01', 'Recuperação de sucatas de alumínio');
INSERT INTO fornecedor.atividade_economica VALUES ('3831-9/99', 'Recuperação de materiais metálicos, exceto alumínio');
INSERT INTO fornecedor.atividade_economica VALUES ('3832-7/00', 'Recuperação de materiais plásticos');
INSERT INTO fornecedor.atividade_economica VALUES ('3839-4/01', 'Usinas de compostagem');
INSERT INTO fornecedor.atividade_economica VALUES ('3839-4/99', 'Recuperação de materiais não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('3900-5/00', 'Descontaminação e outros serviços de gestão de resíduos');
INSERT INTO fornecedor.atividade_economica VALUES ('4110-7/00', 'Incorporação de empreendimentos imobiliários');
INSERT INTO fornecedor.atividade_economica VALUES ('4120-4/00', 'Construção de edifícios');
INSERT INTO fornecedor.atividade_economica VALUES ('4211-1/01', 'Construção de rodovias e ferrovias');
INSERT INTO fornecedor.atividade_economica VALUES ('4211-1/02', 'Pintura para sinalização em pistas rodoviárias e aeroportos');
INSERT INTO fornecedor.atividade_economica VALUES ('4212-0/00', 'Construção de obras de arte especiais');
INSERT INTO fornecedor.atividade_economica VALUES ('4213-8/00', 'Obras de urbanização - ruas, praças e calçadas');
INSERT INTO fornecedor.atividade_economica VALUES ('4221-9/01', 'Construção de barragens e represas para geração de energia elétrica');
INSERT INTO fornecedor.atividade_economica VALUES ('4221-9/02', 'Construção de estações e redes de distribuição de energia elétrica');
INSERT INTO fornecedor.atividade_economica VALUES ('4221-9/03', 'Manutenção de redes de distribuição de energia elétrica');
INSERT INTO fornecedor.atividade_economica VALUES ('4221-9/04', 'Construção de estações e redes de telecomunicações');
INSERT INTO fornecedor.atividade_economica VALUES ('4221-9/05', 'Manutenção de estações e redes de telecomunicações');
INSERT INTO fornecedor.atividade_economica VALUES ('4222-7/01', 'Construção de redes de abastecimento de água, coleta de esgoto e construções correlatas, exceto obras de irrigação');
INSERT INTO fornecedor.atividade_economica VALUES ('4222-7/02', 'Obras de irrigação');
INSERT INTO fornecedor.atividade_economica VALUES ('4223-5/00', 'Construção de redes de transportes por dutos, exceto para água e esgoto');
INSERT INTO fornecedor.atividade_economica VALUES ('4291-0/00', 'Obras portuárias, marítimas e fluviais');
INSERT INTO fornecedor.atividade_economica VALUES ('4292-8/01', 'Montagem de estruturas metálicas');
INSERT INTO fornecedor.atividade_economica VALUES ('4292-8/02', 'Obras de montagem industrial');
INSERT INTO fornecedor.atividade_economica VALUES ('4299-5/01', 'Construção de instalações esportivas e recreativas');
INSERT INTO fornecedor.atividade_economica VALUES ('4299-5/99', 'Outras obras de engenharia civil não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4311-8/01', 'Demolição de edifícios e outras estruturas');
INSERT INTO fornecedor.atividade_economica VALUES ('4311-8/02', 'Preparação de canteiro e limpeza de terreno');
INSERT INTO fornecedor.atividade_economica VALUES ('4312-6/00', 'Perfurações e sondagens');
INSERT INTO fornecedor.atividade_economica VALUES ('4313-4/00', 'Obras de terraplenagem');
INSERT INTO fornecedor.atividade_economica VALUES ('4319-3/00', 'Serviços de preparação do terreno não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4321-5/00', 'Instalação e manutenção elétrica');
INSERT INTO fornecedor.atividade_economica VALUES ('4322-3/01', 'Instalações hidráulicas, sanitárias e de gás');
INSERT INTO fornecedor.atividade_economica VALUES ('4322-3/02', 'Instalação e manutenção de sistemas centrais de ar condicionado, de ventilação e refrigeração');
INSERT INTO fornecedor.atividade_economica VALUES ('4322-3/03', 'Instalações de sistema de prevenção contra incêndio');
INSERT INTO fornecedor.atividade_economica VALUES ('4329-1/01', 'Instalação de painéis publicitários');
INSERT INTO fornecedor.atividade_economica VALUES ('4329-1/02', 'Instalação de equipamentos para orientação à navegação marítima, fluvial e lacustre');
INSERT INTO fornecedor.atividade_economica VALUES ('4329-1/03', 'Instalação, manutenção e reparação de elevadores, escadas e esteiras rolantes, exceto de fabricação própria');
INSERT INTO fornecedor.atividade_economica VALUES ('4329-1/04', 'Montagem e instalação de sistemas e equipamentos de iluminação e sinalização em vias públicas, portos e aeroportos');
INSERT INTO fornecedor.atividade_economica VALUES ('4329-1/05', 'Tratamentos térmicos, acústicos ou de vibração');
INSERT INTO fornecedor.atividade_economica VALUES ('4329-1/99', 'Outras obras de instalações em construções não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4330-4/01', 'Impermeabilização em obras de engenharia civil');
INSERT INTO fornecedor.atividade_economica VALUES ('4330-4/02', 'Instalação de portas, janelas, tetos, divisórias e armários embutidos de qualquer material');
INSERT INTO fornecedor.atividade_economica VALUES ('4330-4/03', 'Obras de acabamento em gesso e estuque');
INSERT INTO fornecedor.atividade_economica VALUES ('4330-4/04', 'Serviços de pintura de edifícios em geral');
INSERT INTO fornecedor.atividade_economica VALUES ('4330-4/05', 'Aplicação de revestimentos e de resinas em interiores e exteriores');
INSERT INTO fornecedor.atividade_economica VALUES ('4330-4/99', 'Outras obras de acabamento da construção');
INSERT INTO fornecedor.atividade_economica VALUES ('4391-6/00', 'Obras de fundações');
INSERT INTO fornecedor.atividade_economica VALUES ('4399-1/01', 'Administração de obras');
INSERT INTO fornecedor.atividade_economica VALUES ('4399-1/02', 'Montagem e desmontagem de andaimes e outras estruturas temporárias');
INSERT INTO fornecedor.atividade_economica VALUES ('4399-1/03', 'Obras de alvenaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4399-1/04', 'Serviços de operação e fornecimento de equipamentos para transporte e elevação de cargas e pessoas para uso em obras');
INSERT INTO fornecedor.atividade_economica VALUES ('4399-1/05', 'Perfuração e construção de poços de água');
INSERT INTO fornecedor.atividade_economica VALUES ('4399-1/99', 'Serviços especializados para construção não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4511-1/01', 'Comércio a varejo de automóveis, camionetas e utilitários novos');
INSERT INTO fornecedor.atividade_economica VALUES ('4511-1/02', 'Comércio a varejo de automóveis, camionetas e utilitários usados');
INSERT INTO fornecedor.atividade_economica VALUES ('4511-1/03', 'Comércio por atacado de automóveis, camionetas e utilitários novos e usados');
INSERT INTO fornecedor.atividade_economica VALUES ('4511-1/04', 'Comércio por atacado de caminhões novos e usados');
INSERT INTO fornecedor.atividade_economica VALUES ('4511-1/05', 'Comércio por atacado de reboques e semi-reboques novos e usados');
INSERT INTO fornecedor.atividade_economica VALUES ('4511-1/06', 'Comércio por atacado de ônibus e microônibus novos e usados');
INSERT INTO fornecedor.atividade_economica VALUES ('4512-9/01', 'Representantes comerciais e agentes do comércio de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4512-9/02', 'Comércio sob consignação de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4520-0/01', 'Serviços de manutenção e reparação mecânica de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4520-0/02', 'Serviços de lanternagem ou funilaria e pintura de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4520-0/03', 'Serviços de manutenção e reparação elétrica de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4520-0/04', 'Serviços de alinhamento e balanceamento de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4520-0/05', 'Serviços de lavagem, lubrificação e polimento de veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4520-0/06', 'Serviços de borracharia para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4520-0/07', 'Serviços de instalação, manutenção e reparação de acessórios para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4530-7/01', 'Comércio por atacado de peças e acessórios novos para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4530-7/02', 'Comércio por atacado de pneumáticos e câmaras-de-ar');
INSERT INTO fornecedor.atividade_economica VALUES ('4530-7/03', 'Comércio a varejo de peças e acessórios novos para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4530-7/04', 'Comércio a varejo de peças e acessórios usados para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4530-7/05', 'Comércio a varejo de pneumáticos e câmaras-de-ar');
INSERT INTO fornecedor.atividade_economica VALUES ('4530-7/06', 'Representantes comerciais e agentes do comércio de peças e acessórios novos e usados para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4541-2/01', 'Comércio por atacado de motocicletas e motonetas');
INSERT INTO fornecedor.atividade_economica VALUES ('4541-2/02', 'Comércio por atacado de peças e acessórios para motocicletas e motonetas');
INSERT INTO fornecedor.atividade_economica VALUES ('4541-2/03', 'Comércio a varejo de motocicletas e motonetas novas');
INSERT INTO fornecedor.atividade_economica VALUES ('4541-2/04', 'Comércio a varejo de motocicletas e motonetas usadas');
INSERT INTO fornecedor.atividade_economica VALUES ('4541-2/05', 'Comércio a varejo de peças e acessórios para motocicletas e motonetas');
INSERT INTO fornecedor.atividade_economica VALUES ('4542-1/01', 'Representantes comerciais e agentes do comércio de motocicletas e motonetas, peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('4542-1/02', 'Comércio sob consignação de motocicletas e motonetas');
INSERT INTO fornecedor.atividade_economica VALUES ('4543-9/00', 'Manutenção e reparação de motocicletas e motonetas');
INSERT INTO fornecedor.atividade_economica VALUES ('4611-7/00', 'Representantes comerciais e agentes do comércio de matérias-primas agrícolas e animais vivos');
INSERT INTO fornecedor.atividade_economica VALUES ('4612-5/00', 'Representantes comerciais e agentes do comércio de combustíveis, minerais, produtos siderúrgicos e químicos');
INSERT INTO fornecedor.atividade_economica VALUES ('4613-3/00', 'Representantes comerciais e agentes do comércio de madeira, material de construção e ferragens');
INSERT INTO fornecedor.atividade_economica VALUES ('4614-1/00', 'Representantes comerciais e agentes do comércio de máquinas, equipamentos, embarcações e aeronaves');
INSERT INTO fornecedor.atividade_economica VALUES ('4615-0/00', 'Representantes comerciais e agentes do comércio de eletrodomésticos, móveis e artigos de uso doméstico');
INSERT INTO fornecedor.atividade_economica VALUES ('4616-8/00', 'Representantes comerciais e agentes do comércio de têxteis, vestuário, calçados e artigos de viagem');
INSERT INTO fornecedor.atividade_economica VALUES ('4617-6/00', 'Representantes comerciais e agentes do comércio de produtos alimentícios, bebidas e fumo');
INSERT INTO fornecedor.atividade_economica VALUES ('4618-4/01', 'Representantes comerciais e agentes do comércio de medicamentos, cosméticos e produtos de perfumaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4618-4/02', 'Representantes comerciais e agentes do comércio de instrumentos e materiais odonto-médico-hospitalares');
INSERT INTO fornecedor.atividade_economica VALUES ('4618-4/03', 'Representantes comerciais e agentes do comércio de jornais, revistas e outras publicações');
INSERT INTO fornecedor.atividade_economica VALUES ('4618-4/99', 'Outros representantes comerciais e agentes do comércio especializado em produtos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4619-2/00', 'Representantes comerciais e agentes do comércio de mercadorias em geral não especializado');
INSERT INTO fornecedor.atividade_economica VALUES ('4621-4/00', 'Comércio atacadista de café em grão');
INSERT INTO fornecedor.atividade_economica VALUES ('4622-2/00', 'Comércio atacadista de soja');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/01', 'Comércio atacadista de animais vivos');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/02', 'Comércio atacadista de couros, lãs, peles e outros subprodutos não-comestíveis de origem animal');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/03', 'Comércio atacadista de algodão');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/04', 'Comércio atacadista de fumo em folha não beneficiado');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/05', 'Comércio atacadista de cacau *');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/06', 'Comércio atacadista de sementes, flores, plantas e gramas');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/07', 'Comércio atacadista de sisal');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/08', 'Comércio atacadista de matérias-primas agrícolas com atividade de fracionamento e acondicionamento associada');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/09', 'Comércio atacadista de alimentos para animais');
INSERT INTO fornecedor.atividade_economica VALUES ('4623-1/99', 'Comércio atacadista de matérias-primas agrícolas não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4631-1/00', 'Comércio atacadista de leite e laticínios');
INSERT INTO fornecedor.atividade_economica VALUES ('4632-0/01', 'Comércio atacadista de cereais e leguminosas beneficiados');
INSERT INTO fornecedor.atividade_economica VALUES ('4632-0/02', 'Comércio atacadista de farinhas, amidos e féculas');
INSERT INTO fornecedor.atividade_economica VALUES ('4632-0/03', 'Comércio atacadista de cereais e leguminosas beneficiados, farinhas, amidos e féculas, com atividade de fracionamento e acondicionamento associada');
INSERT INTO fornecedor.atividade_economica VALUES ('4633-8/01', 'Comércio atacadista de frutas, verduras, raízes, tubérculos, hortaliças e legumes frescos');
INSERT INTO fornecedor.atividade_economica VALUES ('4633-8/02', 'Comércio atacadista de aves vivas e ovos');
INSERT INTO fornecedor.atividade_economica VALUES ('4633-8/03', 'Comércio atacadista de coelhos e outros pequenos animais vivos para alimentação');
INSERT INTO fornecedor.atividade_economica VALUES ('4634-6/01', 'Comércio atacadista de carnes bovinas e suínas e derivados');
INSERT INTO fornecedor.atividade_economica VALUES ('4634-6/02', 'Comércio atacadista de aves abatidas e derivados');
INSERT INTO fornecedor.atividade_economica VALUES ('4634-6/03', 'Comércio atacadista de pescados e frutos do mar');
INSERT INTO fornecedor.atividade_economica VALUES ('4634-6/99', 'Comércio atacadista de carnes e derivados de outros animais');
INSERT INTO fornecedor.atividade_economica VALUES ('4635-4/01', 'Comércio atacadista de água mineral');
INSERT INTO fornecedor.atividade_economica VALUES ('4635-4/02', 'Comércio atacadista de cerveja, chope e refrigerante');
INSERT INTO fornecedor.atividade_economica VALUES ('4635-4/03', 'Comércio atacadista de bebidas com atividade de fracionamento e acondicionamento associada');
INSERT INTO fornecedor.atividade_economica VALUES ('4635-4/99', 'Comércio atacadista de bebidas não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4636-2/01', 'Comércio atacadista de fumo beneficiado');
INSERT INTO fornecedor.atividade_economica VALUES ('4636-2/02', 'Comércio atacadista de cigarros, cigarrilhas e charutos');
INSERT INTO fornecedor.atividade_economica VALUES ('4637-1/01', 'Comércio atacadista de café torrado, moído e solúvel');
INSERT INTO fornecedor.atividade_economica VALUES ('4637-1/02', 'Comércio atacadista de açúcar');
INSERT INTO fornecedor.atividade_economica VALUES ('4637-1/03', 'Comércio atacadista de óleos e gorduras');
INSERT INTO fornecedor.atividade_economica VALUES ('4637-1/04', 'Comércio atacadista de pães, bolos, biscoitos e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('4637-1/05', 'Comércio atacadista de massas alimentícias');
INSERT INTO fornecedor.atividade_economica VALUES ('4637-1/06', 'Comércio atacadista de sorvetes');
INSERT INTO fornecedor.atividade_economica VALUES ('4637-1/07', 'Comércio atacadista de chocolates, confeitos, balas, bombons e semelhantes');
INSERT INTO fornecedor.atividade_economica VALUES ('4637-1/99', 'Comércio atacadista especializado em outros produtos alimentícios não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4639-7/01', 'Comércio atacadista de produtos alimentícios em geral');
INSERT INTO fornecedor.atividade_economica VALUES ('4639-7/02', 'Comércio atacadista de produtos alimentícios em geral, com atividade de fracionamento e acondicionamento associada');
INSERT INTO fornecedor.atividade_economica VALUES ('4641-9/01', 'Comércio atacadista de tecidos');
INSERT INTO fornecedor.atividade_economica VALUES ('4641-9/02', 'Comércio atacadista de artigos de cama, mesa e banho');
INSERT INTO fornecedor.atividade_economica VALUES ('4641-9/03', 'Comércio atacadista de artigos de armarinho');
INSERT INTO fornecedor.atividade_economica VALUES ('4642-7/01', 'Comércio atacadista de artigos do vestuário e acessórios, exceto profissionais e de segurança');
INSERT INTO fornecedor.atividade_economica VALUES ('4642-7/02', 'Comércio atacadista de roupas e acessórios para uso profissional e de segurança do trabalho');
INSERT INTO fornecedor.atividade_economica VALUES ('4643-5/01', 'Comércio atacadista de calçados');
INSERT INTO fornecedor.atividade_economica VALUES ('4643-5/02', 'Comércio atacadista de bolsas, malas e artigos de viagem');
INSERT INTO fornecedor.atividade_economica VALUES ('4644-3/01', 'Comércio atacadista de medicamentos e drogas de uso humano');
INSERT INTO fornecedor.atividade_economica VALUES ('4644-3/02', 'Comércio atacadista de medicamentos e drogas de uso veterinário');
INSERT INTO fornecedor.atividade_economica VALUES ('4645-1/01', 'Comércio atacadista de instrumentos e materiais para uso médico, cirúrgico, hospitalar e de laboratórios');
INSERT INTO fornecedor.atividade_economica VALUES ('4645-1/02', 'Comércio atacadista de próteses e artigos de ortopedia');
INSERT INTO fornecedor.atividade_economica VALUES ('4645-1/03', 'Comércio atacadista de produtos odontológicos');
INSERT INTO fornecedor.atividade_economica VALUES ('4646-0/01', 'Comércio atacadista de cosméticos e produtos de perfumaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4646-0/02', 'Comércio atacadista de produtos de higiene pessoal');
INSERT INTO fornecedor.atividade_economica VALUES ('4647-8/01', 'Comércio atacadista de artigos de escritório e de papelaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4647-8/02', 'Comércio atacadista de livros, jornais e outras publicações');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/01', 'Comércio atacadista de equipamentos elétricos de uso pessoal e doméstico');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/02', 'Comércio atacadista de aparelhos eletrônicos de uso pessoal e doméstico');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/03', 'Comércio atacadista de bicicletas, triciclos e outros veículos recreativos');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/04', 'Comércio atacadista de móveis e artigos de colchoaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/05', 'Comércio atacadista de artigos de tapeçaria; persianas e cortinas');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/06', 'Comércio atacadista de lustres, luminárias e abajures');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/07', 'Comércio atacadista de filmes, CDs, DVDs, fitas e discos');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/08', 'Comércio atacadista de produtos de higiene, limpeza e conservação domiciliar');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/09', 'Comércio atacadista de produtos de higiene, limpeza e conservação domiciliar, com atividade de fracionamento e acondicionamento associada');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/10', 'Comércio atacadista de jóias, relógios e bijuterias, inclusive pedras preciosas e semipreciosas lapidadas');
INSERT INTO fornecedor.atividade_economica VALUES ('4649-4/99', 'Comércio atacadista de outros equipamentos e artigos de uso pessoal e doméstico não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4651-6/01', 'Comércio atacadista de equipamentos de informática');
INSERT INTO fornecedor.atividade_economica VALUES ('4651-6/02', 'Comércio atacadista de suprimentos para informática');
INSERT INTO fornecedor.atividade_economica VALUES ('4652-4/00', 'Comércio atacadista de componentes eletrônicos e equipamentos de telefonia e comunicação');
INSERT INTO fornecedor.atividade_economica VALUES ('4661-3/00', 'Comércio atacadista de máquinas, aparelhos e equipamentos para uso agropecuário; partes e peças');
INSERT INTO fornecedor.atividade_economica VALUES ('4662-1/00', 'Comércio atacadista de máquinas, equipamentos para terraplenagem, mineração e construção; partes e peças');
INSERT INTO fornecedor.atividade_economica VALUES ('4663-0/00', 'Comércio atacadista de máquinas e equipamentos para uso industrial; partes e peças');
INSERT INTO fornecedor.atividade_economica VALUES ('4664-8/00', 'Comércio atacadista de máquinas, aparelhos e equipamentos para uso odonto-médico-hospitalar; partes e peças');
INSERT INTO fornecedor.atividade_economica VALUES ('4665-6/00', 'Comércio atacadista de máquinas e equipamentos para uso comercial; partes e peças');
INSERT INTO fornecedor.atividade_economica VALUES ('4669-9/01', 'Comércio atacadista de bombas e compressores; partes e peças');
INSERT INTO fornecedor.atividade_economica VALUES ('4669-9/99', 'Comércio atacadista de outras máquinas e equipamentos não especificados anteriormente; partes e peças');
INSERT INTO fornecedor.atividade_economica VALUES ('4671-1/00', 'Comércio atacadista de madeira e produtos derivados');
INSERT INTO fornecedor.atividade_economica VALUES ('4672-9/00', 'Comércio atacadista de ferragens e ferramentas');
INSERT INTO fornecedor.atividade_economica VALUES ('4673-7/00', 'Comércio atacadista de material elétrico');
INSERT INTO fornecedor.atividade_economica VALUES ('4674-5/00', 'Comércio atacadista de cimento');
INSERT INTO fornecedor.atividade_economica VALUES ('4679-6/01', 'Comércio atacadista de tintas, vernizes e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('4679-6/02', 'Comércio atacadista de mármores e granitos');
INSERT INTO fornecedor.atividade_economica VALUES ('4679-6/03', 'Comércio atacadista de vidros, espelhos e vitrais');
INSERT INTO fornecedor.atividade_economica VALUES ('4679-6/04', 'Comércio atacadista especializado de materiais de construção não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4679-6/99', 'Comércio atacadista de materiais de construção em geral');
INSERT INTO fornecedor.atividade_economica VALUES ('4681-8/01', 'Comércio atacadista de álcool carburante, biodiesel, gasolina e demais derivados de petróleo, exceto lubrificantes, não realizado por transportador retalhista (TRR)');
INSERT INTO fornecedor.atividade_economica VALUES ('4681-8/02', 'Comércio atacadista de combustíveis realizado por transportador retalhista (TRR)');
INSERT INTO fornecedor.atividade_economica VALUES ('4681-8/03', 'Comércio atacadista de combustíveis de origem vegetal, exceto álcool carburante');
INSERT INTO fornecedor.atividade_economica VALUES ('4681-8/04', 'Comércio atacadista de combustíveis de origem mineral em bruto');
INSERT INTO fornecedor.atividade_economica VALUES ('4681-8/05', 'Comércio atacadista de lubrificantes');
INSERT INTO fornecedor.atividade_economica VALUES ('4682-6/00', 'Comércio atacadista de gás liqüefeito de petróleo (GLP)');
INSERT INTO fornecedor.atividade_economica VALUES ('4683-4/00', 'Comércio atacadista de defensivos agrícolas, adubos, fertilizantes e corretivos do solo');
INSERT INTO fornecedor.atividade_economica VALUES ('4684-2/01', 'Comércio atacadista de resinas e elastômeros');
INSERT INTO fornecedor.atividade_economica VALUES ('4684-2/02', 'Comércio atacadista de solventes');
INSERT INTO fornecedor.atividade_economica VALUES ('4684-2/99', 'Comércio atacadista de outros produtos químicos e petroquímicos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4685-1/00', 'Comércio atacadista de produtos siderúrgicos e metalúrgicos, exceto para construção');
INSERT INTO fornecedor.atividade_economica VALUES ('4686-9/01', 'Comércio atacadista de papel e papelão em bruto');
INSERT INTO fornecedor.atividade_economica VALUES ('4686-9/02', 'Comércio atacadista de embalagens');
INSERT INTO fornecedor.atividade_economica VALUES ('4687-7/01', 'Comércio atacadista de resíduos de papel e papelão');
INSERT INTO fornecedor.atividade_economica VALUES ('4687-7/02', 'Comércio atacadista de resíduos e sucatas não-metálicos, exceto de papel e papelão');
INSERT INTO fornecedor.atividade_economica VALUES ('4687-7/03', 'Comércio atacadista de resíduos e sucatas metálicos');
INSERT INTO fornecedor.atividade_economica VALUES ('4689-3/01', 'Comércio atacadista de produtos da extração mineral, exceto combustíveis');
INSERT INTO fornecedor.atividade_economica VALUES ('4689-3/02', 'Comércio atacadista de fios e fibras têxteis beneficiados *');
INSERT INTO fornecedor.atividade_economica VALUES ('4689-3/99', 'Comércio atacadista especializado em outros produtos intermediários não especificados anteriormente *');
INSERT INTO fornecedor.atividade_economica VALUES ('4691-5/00', 'Comércio atacadista de mercadorias em geral, com predominância de produtos alimentícios');
INSERT INTO fornecedor.atividade_economica VALUES ('4692-3/00', 'Comércio atacadista de mercadorias em geral, com predominância de insumos agropecuários');
INSERT INTO fornecedor.atividade_economica VALUES ('4693-1/00', 'Comércio atacadista de mercadorias em geral, sem predominância de alimentos ou de insumos agropecuários');
INSERT INTO fornecedor.atividade_economica VALUES ('4711-3/01', 'Comércio varejista de mercadorias em geral, com predominância de produtos alimentícios - hipermercados');
INSERT INTO fornecedor.atividade_economica VALUES ('4711-3/02', 'Comércio varejista de mercadorias em geral, com predominância de produtos alimentícios - supermercados');
INSERT INTO fornecedor.atividade_economica VALUES ('4712-1/00', 'Comércio varejista de mercadorias em geral, com predominância de produtos alimentícios - minimercados, mercearias e armazéns');
INSERT INTO fornecedor.atividade_economica VALUES ('4713-0/01', 'Lojas de departamentos ou magazines');
INSERT INTO fornecedor.atividade_economica VALUES ('4713-0/02', 'Lojas de variedades, exceto lojas de departamentos ou magazines');
INSERT INTO fornecedor.atividade_economica VALUES ('4713-0/03', 'Lojas duty free de aeroportos internacionais');
INSERT INTO fornecedor.atividade_economica VALUES ('4721-1/01', 'Padaria e confeitaria com predominância de produção própria');
INSERT INTO fornecedor.atividade_economica VALUES ('4721-1/02', 'Padaria e confeitaria com predominância de revenda');
INSERT INTO fornecedor.atividade_economica VALUES ('4721-1/03', 'Comércio varejista de laticínios e frios');
INSERT INTO fornecedor.atividade_economica VALUES ('4721-1/04', 'Comércio varejista de doces, balas, bombons e semelhantes');
INSERT INTO fornecedor.atividade_economica VALUES ('4722-9/01', 'Comércio varejista de carnes - açougues');
INSERT INTO fornecedor.atividade_economica VALUES ('4722-9/02', 'Peixaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4723-7/00', 'Comércio varejista de bebidas');
INSERT INTO fornecedor.atividade_economica VALUES ('4724-5/00', 'Comércio varejista de hortifrutigranjeiros');
INSERT INTO fornecedor.atividade_economica VALUES ('4729-6/01', 'Tabacaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4729-6/99', 'Comércio varejista de produtos alimentícios em geral ou especializado em produtos alimentícios não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4731-8/00', 'Comércio varejista de combustíveis para veículos automotores');
INSERT INTO fornecedor.atividade_economica VALUES ('4732-6/00', 'Comércio varejista de lubrificantes');
INSERT INTO fornecedor.atividade_economica VALUES ('4741-5/00', 'Comércio varejista de tintas e materiais para pintura');
INSERT INTO fornecedor.atividade_economica VALUES ('4742-3/00', 'Comércio varejista de material elétrico');
INSERT INTO fornecedor.atividade_economica VALUES ('4743-1/00', 'Comércio varejista de vidros');
INSERT INTO fornecedor.atividade_economica VALUES ('4744-0/01', 'Comércio varejista de ferragens e ferramentas');
INSERT INTO fornecedor.atividade_economica VALUES ('4744-0/02', 'Comércio varejista de madeira e artefatos');
INSERT INTO fornecedor.atividade_economica VALUES ('4744-0/03', 'Comércio varejista de materiais hidráulicos');
INSERT INTO fornecedor.atividade_economica VALUES ('4744-0/04', 'Comércio varejista de cal, areia, pedra britada, tijolos e telhas');
INSERT INTO fornecedor.atividade_economica VALUES ('4744-0/05', 'Comércio varejista de materiais de construção não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4744-0/99', 'Comércio varejista de materiais de construção em geral');
INSERT INTO fornecedor.atividade_economica VALUES ('4751-2/00', 'Comércio varejista especializado de equipamentos e suprimentos de informática');
INSERT INTO fornecedor.atividade_economica VALUES ('4752-1/00', 'Comércio varejista especializado de equipamentos de telefonia e comunicação');
INSERT INTO fornecedor.atividade_economica VALUES ('4753-9/00', 'Comércio varejista especializado de eletrodomésticos e equipamentos de áudio e vídeo');
INSERT INTO fornecedor.atividade_economica VALUES ('4754-7/01', 'Comércio varejista de móveis');
INSERT INTO fornecedor.atividade_economica VALUES ('4754-7/02', 'Comércio varejista de artigos de colchoaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4754-7/03', 'Comércio varejista de artigos de iluminação');
INSERT INTO fornecedor.atividade_economica VALUES ('4755-5/01', 'Comércio varejista de tecidos');
INSERT INTO fornecedor.atividade_economica VALUES ('4755-5/02', 'Comercio varejista de artigos de armarinho');
INSERT INTO fornecedor.atividade_economica VALUES ('4755-5/03', 'Comercio varejista de artigos de cama, mesa e banho');
INSERT INTO fornecedor.atividade_economica VALUES ('4756-3/00', 'Comércio varejista especializado de instrumentos musicais e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('4757-1/00', 'Comércio varejista especializado de peças e acessórios para aparelhos eletroeletrônicos para uso doméstico, exceto informática e comunicação');
INSERT INTO fornecedor.atividade_economica VALUES ('4759-8/01', 'Comércio varejista de artigos de tapeçaria, cortinas e persianas');
INSERT INTO fornecedor.atividade_economica VALUES ('4759-8/99', 'Comércio varejista de outros artigos de uso doméstico não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4761-0/01', 'Comércio varejista de livros');
INSERT INTO fornecedor.atividade_economica VALUES ('4761-0/02', 'Comércio varejista de jornais e revistas');
INSERT INTO fornecedor.atividade_economica VALUES ('4761-0/03', 'Comércio varejista de artigos de papelaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4762-8/00', 'Comércio varejista de discos, CDs, DVDs e fitas');
INSERT INTO fornecedor.atividade_economica VALUES ('4763-6/01', 'Comércio varejista de brinquedos e artigos recreativos');
INSERT INTO fornecedor.atividade_economica VALUES ('4763-6/02', 'Comércio varejista de artigos esportivos');
INSERT INTO fornecedor.atividade_economica VALUES ('4763-6/03', 'Comércio varejista de bicicletas e triciclos; peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('4763-6/04', 'Comércio varejista de artigos de caça, pesca e camping');
INSERT INTO fornecedor.atividade_economica VALUES ('4763-6/05', 'Comércio varejista de embarcações e outros veículos recreativos; peças e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('4771-7/01', 'Comércio varejista de produtos farmacêuticos, sem manipulação de fórmulas');
INSERT INTO fornecedor.atividade_economica VALUES ('4771-7/02', 'Comércio varejista de produtos farmacêuticos, com manipulação de fórmulas');
INSERT INTO fornecedor.atividade_economica VALUES ('4771-7/03', 'Comércio varejista de produtos farmacêuticos homeopáticos');
INSERT INTO fornecedor.atividade_economica VALUES ('4771-7/04', 'Comércio varejista de medicamentos veterinários');
INSERT INTO fornecedor.atividade_economica VALUES ('4772-5/00', 'Comércio varejista de cosméticos, produtos de perfumaria e de higiene pessoal');
INSERT INTO fornecedor.atividade_economica VALUES ('4773-3/00', 'Comércio varejista de artigos médicos e ortopédicos');
INSERT INTO fornecedor.atividade_economica VALUES ('4774-1/00', 'Comércio varejista de artigos de óptica');
INSERT INTO fornecedor.atividade_economica VALUES ('4781-4/00', 'Comércio varejista de artigos do vestuário e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('4782-2/01', 'Comércio varejista de calçados');
INSERT INTO fornecedor.atividade_economica VALUES ('4782-2/02', 'Comércio varejista de artigos de viagem');
INSERT INTO fornecedor.atividade_economica VALUES ('4783-1/01', 'Comércio varejista de artigos de joalheria');
INSERT INTO fornecedor.atividade_economica VALUES ('4783-1/02', 'Comércio varejista de artigos de relojoaria');
INSERT INTO fornecedor.atividade_economica VALUES ('4784-9/00', 'Comércio varejista de gás liqüefeito de petróleo (GLP)');
INSERT INTO fornecedor.atividade_economica VALUES ('4785-7/01', 'Comércio varejista de antigüidades');
INSERT INTO fornecedor.atividade_economica VALUES ('4785-7/99', 'Comércio varejista de outros artigos usados');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/01', 'Comércio varejista de suvenires, bijuterias e artesanatos');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/02', 'Comércio varejista de plantas e flores naturais');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/03', 'Comércio varejista de objetos de arte');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/04', 'Comércio varejista de animais vivos e de artigos e alimentos para animais de estimação');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/05', 'Comércio varejista de produtos saneantes domissanitários');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/06', 'Comércio varejista de fogos de artifício e artigos pirotécnicos');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/07', 'Comércio varejista de equipamentos para escritório');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/08', 'Comércio varejista de artigos fotográficos e para filmagem');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/09', 'Comércio varejista de armas e munições');
INSERT INTO fornecedor.atividade_economica VALUES ('4789-0/99', 'Comércio varejista de outros produtos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4911-6/00', 'Transporte ferroviário de carga');
INSERT INTO fornecedor.atividade_economica VALUES ('4912-4/01', 'Transporte ferroviário de passageiros intermunicipal e interestadual');
INSERT INTO fornecedor.atividade_economica VALUES ('4912-4/02', 'Transporte ferroviário de passageiros municipal e em região metropolitana');
INSERT INTO fornecedor.atividade_economica VALUES ('4912-4/03', 'Transporte metroviário');
INSERT INTO fornecedor.atividade_economica VALUES ('4921-3/01', 'Transporte rodoviário coletivo de passageiros, com itinerário fixo, municipal');
INSERT INTO fornecedor.atividade_economica VALUES ('4921-3/02', 'Transporte rodoviário coletivo de passageiros, com itinerário fixo, intermunicipal em região metropolitana');
INSERT INTO fornecedor.atividade_economica VALUES ('4922-1/01', 'Transporte rodoviário coletivo de passageiros, com itinerário fixo, intermunicipal, exceto em região metropolitana');
INSERT INTO fornecedor.atividade_economica VALUES ('4922-1/02', 'Transporte rodoviário coletivo de passageiros, com itinerário fixo, interestadual');
INSERT INTO fornecedor.atividade_economica VALUES ('4922-1/03', 'Transporte rodoviário coletivo de passageiros, com itinerário fixo, internacional');
INSERT INTO fornecedor.atividade_economica VALUES ('4923-0/01', 'Serviço de táxi');
INSERT INTO fornecedor.atividade_economica VALUES ('4923-0/02', 'Serviço de transporte de passageiros - locação de automóveis com motorista');
INSERT INTO fornecedor.atividade_economica VALUES ('4924-8/00', 'Transporte escolar');
INSERT INTO fornecedor.atividade_economica VALUES ('4929-9/01', 'Transporte rodoviário coletivo de passageiros, sob regime de fretamento, municipal');
INSERT INTO fornecedor.atividade_economica VALUES ('4929-9/02', 'Transporte rodoviário coletivo de passageiros, sob regime de fretamento, intermunicipal, interestadual e internacional');
INSERT INTO fornecedor.atividade_economica VALUES ('4929-9/03', 'Organização de excursões em veículos rodoviários próprios, municipal');
INSERT INTO fornecedor.atividade_economica VALUES ('4929-9/04', 'Organização de excursões em veículos rodoviários próprios, intermunicipal, interestadual e internacional');
INSERT INTO fornecedor.atividade_economica VALUES ('4929-9/99', 'Outros transportes rodoviários de passageiros não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('4930-2/01', 'Transporte rodoviário de carga, exceto produtos perigosos e mudanças, municipal');
INSERT INTO fornecedor.atividade_economica VALUES ('6120-5/02', 'Serviço móvel especializado - SME');
INSERT INTO fornecedor.atividade_economica VALUES ('4930-2/02', 'Transporte rodoviário de carga, exceto produtos perigosos e mudanças, intermunicipal, interestadual e internacional');
INSERT INTO fornecedor.atividade_economica VALUES ('4930-2/03', 'Transporte rodoviário de produtos perigosos');
INSERT INTO fornecedor.atividade_economica VALUES ('4930-2/04', 'Transporte rodoviário de mudanças');
INSERT INTO fornecedor.atividade_economica VALUES ('4940-0/00', 'Transporte dutoviário');
INSERT INTO fornecedor.atividade_economica VALUES ('4950-7/00', 'Trens turísticos, teleféricos e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('5011-4/01', 'Transporte marítimo de cabotagem - Carga');
INSERT INTO fornecedor.atividade_economica VALUES ('5011-4/02', 'Transporte marítimo de cabotagem - passageiros');
INSERT INTO fornecedor.atividade_economica VALUES ('5012-2/01', 'Transporte marítimo de longo curso - Carga');
INSERT INTO fornecedor.atividade_economica VALUES ('5012-2/02', 'Transporte marítimo de longo curso - Passageiros');
INSERT INTO fornecedor.atividade_economica VALUES ('5021-1/01', 'Transporte por navegação interior de carga, municipal, exceto travessia');
INSERT INTO fornecedor.atividade_economica VALUES ('5021-1/02', 'Transporte por navegação interior de carga, intermunicipal, interestadual e internacional, exceto travessia');
INSERT INTO fornecedor.atividade_economica VALUES ('5022-0/01', 'Transporte por navegação interior de passageiros em linhas regulares, municipal, exceto travessia');
INSERT INTO fornecedor.atividade_economica VALUES ('5022-0/02', 'Transporte por navegação interior de passageiros em linhas regulares, intermunicipal, interestadual e internacional, exceto travessia');
INSERT INTO fornecedor.atividade_economica VALUES ('5030-1/01', 'Navegação de apoio marítimo');
INSERT INTO fornecedor.atividade_economica VALUES ('5030-1/02', 'Navegação de apoio portuário');
INSERT INTO fornecedor.atividade_economica VALUES ('5091-2/01', 'Transporte por navegação de travessia, municipal');
INSERT INTO fornecedor.atividade_economica VALUES ('5091-2/02', 'Transporte por navegação de travessia, intermunicipal');
INSERT INTO fornecedor.atividade_economica VALUES ('5099-8/01', 'Transporte aquaviário para passeios turísticos');
INSERT INTO fornecedor.atividade_economica VALUES ('5099-8/99', 'Outros transportes aquaviários não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('5111-1/00', 'Transporte aéreo de passageiros regular');
INSERT INTO fornecedor.atividade_economica VALUES ('5112-9/01', 'Serviço de táxi aéreo e locação de aeronaves com tripulação');
INSERT INTO fornecedor.atividade_economica VALUES ('5112-9/99', 'Outros serviços de transporte aéreo de passageiros não-regular');
INSERT INTO fornecedor.atividade_economica VALUES ('5120-0/00', 'Transporte aéreo de carga');
INSERT INTO fornecedor.atividade_economica VALUES ('5130-7/00', 'Transporte espacial');
INSERT INTO fornecedor.atividade_economica VALUES ('5211-7/01', 'Armazéns gerais - emissão de warrant');
INSERT INTO fornecedor.atividade_economica VALUES ('5211-7/02', 'Guarda-móveis');
INSERT INTO fornecedor.atividade_economica VALUES ('5211-7/99', 'Depósitos de mercadorias para terceiros, exceto armazéns gerais e guarda-móveis');
INSERT INTO fornecedor.atividade_economica VALUES ('5212-5/00', 'Carga e descarga');
INSERT INTO fornecedor.atividade_economica VALUES ('5221-4/00', 'Concessionárias de rodovias, pontes, túneis e serviços relacionados');
INSERT INTO fornecedor.atividade_economica VALUES ('5222-2/00', 'Terminais rodoviários e ferroviários');
INSERT INTO fornecedor.atividade_economica VALUES ('5223-1/00', 'Estacionamento de veículos');
INSERT INTO fornecedor.atividade_economica VALUES ('5229-0/01', 'Serviços de apoio ao transporte por táxi, inclusive centrais de chamada');
INSERT INTO fornecedor.atividade_economica VALUES ('5229-0/02', 'Serviços de reboque de veículos');
INSERT INTO fornecedor.atividade_economica VALUES ('5229-0/99', 'Outras atividades auxiliares dos transportes terrestres não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('5231-1/01', 'Administração da infra-estrutura portuária');
INSERT INTO fornecedor.atividade_economica VALUES ('5231-1/02', 'Operações de terminais');
INSERT INTO fornecedor.atividade_economica VALUES ('5232-0/00', 'Atividades de agenciamento marítimo');
INSERT INTO fornecedor.atividade_economica VALUES ('5239-7/00', 'Atividades auxiliares dos transportes aquaviários não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('5240-1/01', 'Operação dos aeroportos e campos de aterrissagem');
INSERT INTO fornecedor.atividade_economica VALUES ('5240-1/99', 'Atividades auxiliares dos transportes aéreos, exceto operação dos aeroportos e campos de aterrissagem');
INSERT INTO fornecedor.atividade_economica VALUES ('5250-8/01', 'Comissaria de despachos');
INSERT INTO fornecedor.atividade_economica VALUES ('5250-8/02', 'Atividades de despachantes aduaneiros');
INSERT INTO fornecedor.atividade_economica VALUES ('5250-8/03', 'Agenciamento de cargas, exceto para o transporte marítimo');
INSERT INTO fornecedor.atividade_economica VALUES ('5250-8/04', 'Organização logística do transporte de carga');
INSERT INTO fornecedor.atividade_economica VALUES ('5250-8/05', 'Operador de transporte multimodal - OTM');
INSERT INTO fornecedor.atividade_economica VALUES ('5310-5/01', 'Atividades do Correio Nacional');
INSERT INTO fornecedor.atividade_economica VALUES ('5310-5/02', 'Atividades de  franqueadas e permissionárias do Correio Nacional');
INSERT INTO fornecedor.atividade_economica VALUES ('5320-2/01', 'Serviços de malote não realizados pelo Correio Nacional');
INSERT INTO fornecedor.atividade_economica VALUES ('5320-2/02', 'Serviços de entrega rápida');
INSERT INTO fornecedor.atividade_economica VALUES ('5510-8/01', 'Hotéis');
INSERT INTO fornecedor.atividade_economica VALUES ('5510-8/02', 'Apart-hotéis');
INSERT INTO fornecedor.atividade_economica VALUES ('5510-8/03', 'Motéis');
INSERT INTO fornecedor.atividade_economica VALUES ('5590-6/01', 'Albergues, exceto assistenciais');
INSERT INTO fornecedor.atividade_economica VALUES ('5590-6/02', 'Campings');
INSERT INTO fornecedor.atividade_economica VALUES ('5590-6/03', 'Pensões');
INSERT INTO fornecedor.atividade_economica VALUES ('5590-6/99', 'Outros alojamentos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('5611-2/01', 'Restaurantes e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('5611-2/02', 'Bares e outros estabelecimentos especializados em servir bebidas');
INSERT INTO fornecedor.atividade_economica VALUES ('5611-2/03', 'Lanchonetes, casas de chá, de sucos e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('5612-1/00', 'Serviços ambulantes de alimentação');
INSERT INTO fornecedor.atividade_economica VALUES ('5620-1/01', 'Fornecimento de alimentos preparados preponderantemente para empresas');
INSERT INTO fornecedor.atividade_economica VALUES ('5620-1/02', 'Serviços de alimentação para eventos e recepções - bufê');
INSERT INTO fornecedor.atividade_economica VALUES ('5620-1/03', 'Cantinas - serviços de alimentação privativos');
INSERT INTO fornecedor.atividade_economica VALUES ('5620-1/04', 'Fornecimento de alimentos preparados preponderantemente para consumo domiciliar');
INSERT INTO fornecedor.atividade_economica VALUES ('5811-5/00', 'Edição de livros');
INSERT INTO fornecedor.atividade_economica VALUES ('5812-3/00', 'Edição de jornais');
INSERT INTO fornecedor.atividade_economica VALUES ('5813-1/00', 'Edição de revistas');
INSERT INTO fornecedor.atividade_economica VALUES ('5819-1/00', 'Edição de cadastros, listas e de outros produtos gráficos');
INSERT INTO fornecedor.atividade_economica VALUES ('5821-2/00', 'Edição integrada à impressão de livros');
INSERT INTO fornecedor.atividade_economica VALUES ('5822-1/00', 'Edição integrada à impressão de jornais');
INSERT INTO fornecedor.atividade_economica VALUES ('5823-9/00', 'Edição integrada à impressão de revistas');
INSERT INTO fornecedor.atividade_economica VALUES ('5829-8/00', 'Edição integrada à impressão de cadastros, listas e de outros produtos gráficos');
INSERT INTO fornecedor.atividade_economica VALUES ('5911-1/01', 'Estúdios cinematográficos');
INSERT INTO fornecedor.atividade_economica VALUES ('5911-1/02', 'Produção de filmes para publicidade');
INSERT INTO fornecedor.atividade_economica VALUES ('5911-1/99', 'Atividades de produção cinematográfica, de vídeos e de programas de televisão não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('5912-0/01', 'Serviços de dublagem');
INSERT INTO fornecedor.atividade_economica VALUES ('5912-0/02', 'Serviços de mixagem sonora');
INSERT INTO fornecedor.atividade_economica VALUES ('5912-0/99', 'Atividades de pós-produção cinematográfica, de vídeos e de programas de televisão não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('5913-8/00', 'Distribuição cinematográfica, de vídeo e de programas de televisão');
INSERT INTO fornecedor.atividade_economica VALUES ('5914-6/00', 'Atividades de exibição cinematográfica');
INSERT INTO fornecedor.atividade_economica VALUES ('5920-1/00', 'Atividades de gravação de som e de edição de música');
INSERT INTO fornecedor.atividade_economica VALUES ('6010-1/00', 'Atividades de rádio');
INSERT INTO fornecedor.atividade_economica VALUES ('6021-7/00', 'Atividades de televisão aberta');
INSERT INTO fornecedor.atividade_economica VALUES ('6022-5/01', 'Programadoras');
INSERT INTO fornecedor.atividade_economica VALUES ('6022-5/02', 'Atividades relacionadas à televisão por assinatura, exceto programadoras');
INSERT INTO fornecedor.atividade_economica VALUES ('6110-8/01', 'Serviços de telefonia fixa comutada - STFC');
INSERT INTO fornecedor.atividade_economica VALUES ('6110-8/02', 'Serviços de redes de transportes de telecomunicações - SRTT');
INSERT INTO fornecedor.atividade_economica VALUES ('6110-8/03', 'Serviços de comunicação multimídia - SMC');
INSERT INTO fornecedor.atividade_economica VALUES ('6110-8/99', 'Serviços de telecomunicações por fio não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('6120-5/01', 'Telefonia móvel celular');
INSERT INTO fornecedor.atividade_economica VALUES ('6120-5/99', 'Serviços de telecomunicações sem fio não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('6130-2/00', 'Telecomunicações por satélite');
INSERT INTO fornecedor.atividade_economica VALUES ('6141-8/00', 'Operadoras de televisão por assinatura por cabo');
INSERT INTO fornecedor.atividade_economica VALUES ('6142-6/00', 'Operadoras de televisão por assinatura por microondas');
INSERT INTO fornecedor.atividade_economica VALUES ('6143-4/00', 'Operadoras de televisão por assinatura por satélite');
INSERT INTO fornecedor.atividade_economica VALUES ('6190-6/01', 'Provedores de acesso às redes de comunicações');
INSERT INTO fornecedor.atividade_economica VALUES ('6190-6/02', 'Provedores de voz sobre protocolo internet - VOIP');
INSERT INTO fornecedor.atividade_economica VALUES ('6190-6/99', 'Outras atividades de telecomunicações não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('6201-5/00', 'Desenvolvimento de programas de computador sob encomenda');
INSERT INTO fornecedor.atividade_economica VALUES ('6202-3/00', 'Desenvolvimento e licenciamento de programas de computador customizáveis');
INSERT INTO fornecedor.atividade_economica VALUES ('6203-1/00', 'Desenvolvimento e licenciamento de programas de computador não-customizáveis');
INSERT INTO fornecedor.atividade_economica VALUES ('6204-0/00', 'Consultoria em tecnologia da informação');
INSERT INTO fornecedor.atividade_economica VALUES ('6209-1/00', 'Suporte técnico, manutenção e outros serviços em tecnologia da informação');
INSERT INTO fornecedor.atividade_economica VALUES ('6311-9/00', 'Tratamento de dados, provedores de serviços de aplicação e serviços de hospedagem na internet');
INSERT INTO fornecedor.atividade_economica VALUES ('6319-4/00', 'Portais, provedores de conteúdo e outros serviços de informação na internet');
INSERT INTO fornecedor.atividade_economica VALUES ('6391-7/00', 'Agências de notícias');
INSERT INTO fornecedor.atividade_economica VALUES ('6399-2/00', 'Outras atividades de prestação de serviços de informação não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('6410-7/00', 'Banco Central');
INSERT INTO fornecedor.atividade_economica VALUES ('6421-2/00', 'Bancos comerciais');
INSERT INTO fornecedor.atividade_economica VALUES ('6422-1/00', 'Bancos múltiplos, com carteira comercial');
INSERT INTO fornecedor.atividade_economica VALUES ('6423-9/00', 'Caixas econômicas');
INSERT INTO fornecedor.atividade_economica VALUES ('6424-7/01', 'Bancos cooperativos');
INSERT INTO fornecedor.atividade_economica VALUES ('6424-7/02', 'Cooperativas centrais de crédito');
INSERT INTO fornecedor.atividade_economica VALUES ('6424-7/03', 'Cooperativas de crédito mútuo');
INSERT INTO fornecedor.atividade_economica VALUES ('6424-7/04', 'Cooperativas de crédito rural');
INSERT INTO fornecedor.atividade_economica VALUES ('6431-0/00', 'Bancos múltiplos, sem carteira comercial');
INSERT INTO fornecedor.atividade_economica VALUES ('6432-8/00', 'Bancos de investimento');
INSERT INTO fornecedor.atividade_economica VALUES ('6433-6/00', 'Bancos de desenvolvimento');
INSERT INTO fornecedor.atividade_economica VALUES ('6434-4/00', 'Agências de fomento');
INSERT INTO fornecedor.atividade_economica VALUES ('6435-2/01', 'Sociedades de crédito imobiliário');
INSERT INTO fornecedor.atividade_economica VALUES ('6435-2/02', 'Associações de poupança e empréstimo');
INSERT INTO fornecedor.atividade_economica VALUES ('6435-2/03', 'Companhias hipotecárias');
INSERT INTO fornecedor.atividade_economica VALUES ('6436-1/00', 'Sociedades de crédito, financiamento e investimento - financeiras');
INSERT INTO fornecedor.atividade_economica VALUES ('6437-9/00', 'Sociedades de crédito ao microempreendedor');
INSERT INTO fornecedor.atividade_economica VALUES ('6440-9/00', 'Arrendamento mercantil');
INSERT INTO fornecedor.atividade_economica VALUES ('6450-6/00', 'Sociedades de capitalização');
INSERT INTO fornecedor.atividade_economica VALUES ('6461-1/00', 'Holdings de instituições financeiras');
INSERT INTO fornecedor.atividade_economica VALUES ('6462-0/00', 'Holdings de instituições não-financeiras');
INSERT INTO fornecedor.atividade_economica VALUES ('6463-8/00', 'Outras sociedades de participação, exceto holdings');
INSERT INTO fornecedor.atividade_economica VALUES ('6470-1/01', 'Fundos de investimento, exceto previdenciários e imobiliários');
INSERT INTO fornecedor.atividade_economica VALUES ('6470-1/02', 'Fundos de investimento previdenciários');
INSERT INTO fornecedor.atividade_economica VALUES ('6470-1/03', 'Fundos de investimento imobiliários');
INSERT INTO fornecedor.atividade_economica VALUES ('6491-3/00', 'Sociedades de fomento mercantil - factoring');
INSERT INTO fornecedor.atividade_economica VALUES ('6492-1/00', 'Securitização de créditos');
INSERT INTO fornecedor.atividade_economica VALUES ('6493-0/00', 'Administração de consórcios para aquisição de bens e direitos');
INSERT INTO fornecedor.atividade_economica VALUES ('6499-9/01', 'Clubes de investimento');
INSERT INTO fornecedor.atividade_economica VALUES ('6499-9/02', 'Sociedades de investimento');
INSERT INTO fornecedor.atividade_economica VALUES ('6499-9/03', 'Fundo garantidor de crédito');
INSERT INTO fornecedor.atividade_economica VALUES ('6499-9/04', 'Caixas de financiamento de corporações');
INSERT INTO fornecedor.atividade_economica VALUES ('6499-9/05', 'Concessão de crédito pelas OSCIP');
INSERT INTO fornecedor.atividade_economica VALUES ('6499-9/99', 'Outras atividades de serviços financeiros não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('6511-1/01', 'Seguros de vida');
INSERT INTO fornecedor.atividade_economica VALUES ('6511-1/02', 'Planos de auxílio-funeral');
INSERT INTO fornecedor.atividade_economica VALUES ('6512-0/00', 'Seguros não-vida');
INSERT INTO fornecedor.atividade_economica VALUES ('6520-1/00', 'Seguros-saúde');
INSERT INTO fornecedor.atividade_economica VALUES ('6530-8/00', 'Resseguros');
INSERT INTO fornecedor.atividade_economica VALUES ('6541-3/00', 'Previdência complementar fechada');
INSERT INTO fornecedor.atividade_economica VALUES ('6542-1/00', 'Previdência complementar aberta');
INSERT INTO fornecedor.atividade_economica VALUES ('6550-2/00', 'Planos de saúde');
INSERT INTO fornecedor.atividade_economica VALUES ('6611-8/01', 'Bolsa de valores');
INSERT INTO fornecedor.atividade_economica VALUES ('6611-8/02', 'Bolsa de mercadorias');
INSERT INTO fornecedor.atividade_economica VALUES ('6611-8/03', 'Bolsa de mercadorias e futuros');
INSERT INTO fornecedor.atividade_economica VALUES ('6611-8/04', 'Administração de mercados de balcão organizados');
INSERT INTO fornecedor.atividade_economica VALUES ('6612-6/01', 'Corretoras de títulos e valores mobiliários');
INSERT INTO fornecedor.atividade_economica VALUES ('6612-6/02', 'Distribuidoras de títulos e valores mobiliários');
INSERT INTO fornecedor.atividade_economica VALUES ('6612-6/03', 'Corretoras de câmbio');
INSERT INTO fornecedor.atividade_economica VALUES ('6612-6/04', 'Corretoras de contratos de mercadorias');
INSERT INTO fornecedor.atividade_economica VALUES ('6612-6/05', 'Agentes de investimentos em aplicações financeiras');
INSERT INTO fornecedor.atividade_economica VALUES ('6613-4/00', 'Administração de cartões de crédito');
INSERT INTO fornecedor.atividade_economica VALUES ('6619-3/01', 'Serviços de liquidação e custódia');
INSERT INTO fornecedor.atividade_economica VALUES ('6619-3/02', 'Correspondentes de instituições financeiras');
INSERT INTO fornecedor.atividade_economica VALUES ('6619-3/03', 'Representações de bancos estrangeiros');
INSERT INTO fornecedor.atividade_economica VALUES ('6619-3/04', 'Caixas eletrônicos');
INSERT INTO fornecedor.atividade_economica VALUES ('6619-3/05', 'Operadoras de cartões de débito');
INSERT INTO fornecedor.atividade_economica VALUES ('6619-3/99', 'Outras atividades auxiliares dos serviços financeiros não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('6621-5/01', 'Peritos e avaliadores de seguros');
INSERT INTO fornecedor.atividade_economica VALUES ('6621-5/02', 'Auditoria e consultoria atuarial');
INSERT INTO fornecedor.atividade_economica VALUES ('6622-3/00', 'Corretores e agentes de seguros, de planos de previdência complementar e de saúde');
INSERT INTO fornecedor.atividade_economica VALUES ('6629-1/00', 'Atividades auxiliares dos seguros, da previdência complementar e dos planos de saúde não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('6630-4/00', 'Atividades de administração de fundos por contrato ou comissão');
INSERT INTO fornecedor.atividade_economica VALUES ('6810-2/01', 'Compra e venda de imóveis próprios');
INSERT INTO fornecedor.atividade_economica VALUES ('6810-2/02', 'Aluguel de imóveis próprios');
INSERT INTO fornecedor.atividade_economica VALUES ('6821-8/01', 'Corretagem na compra e venda e avaliação de imóveis');
INSERT INTO fornecedor.atividade_economica VALUES ('6821-8/02', 'Corretagem no aluguel de imóveis');
INSERT INTO fornecedor.atividade_economica VALUES ('6822-6/00', 'Gestão e administração da propriedade imobiliária*');
INSERT INTO fornecedor.atividade_economica VALUES ('6911-7/01', 'Serviços advocatícios');
INSERT INTO fornecedor.atividade_economica VALUES ('6911-7/02', 'Atividades auxiliares da justiça');
INSERT INTO fornecedor.atividade_economica VALUES ('6911-7/03', 'Agente de propriedade industrial');
INSERT INTO fornecedor.atividade_economica VALUES ('6912-5/00', 'Cartórios');
INSERT INTO fornecedor.atividade_economica VALUES ('6920-6/01', 'Atividades de contabilidade');
INSERT INTO fornecedor.atividade_economica VALUES ('6920-6/02', 'Atividades de consultoria e auditoria contábil e tributária');
INSERT INTO fornecedor.atividade_economica VALUES ('7020-4/00', 'Atividades de consultoria em gestão empresarial, exceto consultoria técnica específica');
INSERT INTO fornecedor.atividade_economica VALUES ('7111-1/00', 'Serviços de arquitetura');
INSERT INTO fornecedor.atividade_economica VALUES ('7112-0/00', 'Serviços de engenharia');
INSERT INTO fornecedor.atividade_economica VALUES ('7119-7/01', 'Serviços de cartografia, topografia e geodésia');
INSERT INTO fornecedor.atividade_economica VALUES ('7119-7/02', 'Atividades de estudos geológicos');
INSERT INTO fornecedor.atividade_economica VALUES ('8423-0/00', 'Justiça');
INSERT INTO fornecedor.atividade_economica VALUES ('7119-7/03', 'Serviços de desenho técnico relacionados à arquitetura e engenharia');
INSERT INTO fornecedor.atividade_economica VALUES ('7119-7/04', 'Serviços de perícia técnica relacionados à segurança do trabalho');
INSERT INTO fornecedor.atividade_economica VALUES ('7119-7/99', 'Atividades técnicas relacionadas à engenharia e arquitetura não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('7120-1/00', 'Testes e análises técnicas');
INSERT INTO fornecedor.atividade_economica VALUES ('7210-0/00', 'Pesquisa e desenvolvimento experimental em ciências físicas e naturais');
INSERT INTO fornecedor.atividade_economica VALUES ('7220-7/00', 'Pesquisa e desenvolvimento experimental em ciências sociais e humanas');
INSERT INTO fornecedor.atividade_economica VALUES ('7311-4/00', 'Agências de publicidade');
INSERT INTO fornecedor.atividade_economica VALUES ('7312-2/00', 'Agenciamento de espaços para publicidade, exceto em veículos de comunicação');
INSERT INTO fornecedor.atividade_economica VALUES ('7319-0/01', 'Criação e montagem de estandes para feiras e exposições');
INSERT INTO fornecedor.atividade_economica VALUES ('7319-0/02', 'Promoção de vendas');
INSERT INTO fornecedor.atividade_economica VALUES ('7319-0/03', 'Marketing direto');
INSERT INTO fornecedor.atividade_economica VALUES ('7319-0/04', 'Consultoria em publicidade');
INSERT INTO fornecedor.atividade_economica VALUES ('7319-0/99', 'Outras atividades de publicidade não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('7320-3/00', 'Pesquisas de mercado e de opinião pública');
INSERT INTO fornecedor.atividade_economica VALUES ('7410-2/01', 'Design');
INSERT INTO fornecedor.atividade_economica VALUES ('7410-2/02', 'Decoração de interiores');
INSERT INTO fornecedor.atividade_economica VALUES ('7420-0/01', 'Atividades de produção de fotografias, exceto aérea e submarina');
INSERT INTO fornecedor.atividade_economica VALUES ('7420-0/02', 'Atividades de produção de fotografias aéreas e submarinas');
INSERT INTO fornecedor.atividade_economica VALUES ('7420-0/03', 'Laboratórios fotográficos');
INSERT INTO fornecedor.atividade_economica VALUES ('7420-0/04', 'Filmagem de festas e eventos');
INSERT INTO fornecedor.atividade_economica VALUES ('7420-0/05', 'Serviços de microfilmagem');
INSERT INTO fornecedor.atividade_economica VALUES ('7490-1/01', 'Serviços de tradução, interpretação e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('7490-1/02', 'Escafandria e mergulho');
INSERT INTO fornecedor.atividade_economica VALUES ('7490-1/03', 'Serviços de agronomia e de consultoria às atividades agrícolas e pecuárias');
INSERT INTO fornecedor.atividade_economica VALUES ('7490-1/04', 'Atividades de intermediação e agenciamento de serviços e negócios em geral, exceto imobiliários');
INSERT INTO fornecedor.atividade_economica VALUES ('7490-1/05', 'Agenciamento de profissionais para atividades esportivas, culturais e artísticas');
INSERT INTO fornecedor.atividade_economica VALUES ('7490-1/99', 'Outras atividades profissionais, científicas e técnicas não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('7500-1/00', 'Atividades veterinárias');
INSERT INTO fornecedor.atividade_economica VALUES ('7711-0/00', 'Locação de automóveis sem condutor');
INSERT INTO fornecedor.atividade_economica VALUES ('7719-5/01', 'Locação de embarcações sem tripulação, exceto para fins recreativos');
INSERT INTO fornecedor.atividade_economica VALUES ('7719-5/02', 'Locação de aeronaves sem tripulação');
INSERT INTO fornecedor.atividade_economica VALUES ('7719-5/99', 'Locação de outros meios de transporte não especificados anteriormente, sem condutor');
INSERT INTO fornecedor.atividade_economica VALUES ('7721-7/00', 'Aluguel de equipamentos recreativos e esportivos');
INSERT INTO fornecedor.atividade_economica VALUES ('7722-5/00', 'Aluguel de fitas de vídeo, DVDs e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('7723-3/00', 'Aluguel de objetos do vestuário, jóias e acessórios');
INSERT INTO fornecedor.atividade_economica VALUES ('7729-2/01', 'Aluguel de aparelhos de jogos eletrônicos');
INSERT INTO fornecedor.atividade_economica VALUES ('7729-2/02', 'Aluguel de móveis, utensílios e aparelhos de uso doméstico e pessoal; instrumentos musicais');
INSERT INTO fornecedor.atividade_economica VALUES ('7729-2/03', 'Aluguel de material médico*');
INSERT INTO fornecedor.atividade_economica VALUES ('7729-2/99', 'Aluguel de outros objetos pessoais e domésticos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('7731-4/00', 'Aluguel de máquinas e equipamentos agrícolas sem operador');
INSERT INTO fornecedor.atividade_economica VALUES ('7732-2/01', 'Aluguel de máquinas e equipamentos para construção sem operador, exceto andaimes');
INSERT INTO fornecedor.atividade_economica VALUES ('7732-2/02', 'Aluguel de andaimes');
INSERT INTO fornecedor.atividade_economica VALUES ('7733-1/00', 'Aluguel de máquinas e equipamentos para escritórios');
INSERT INTO fornecedor.atividade_economica VALUES ('7739-0/01', 'Aluguel de máquinas e equipamentos para extração de minérios e petróleo, sem operador');
INSERT INTO fornecedor.atividade_economica VALUES ('7739-0/02', 'Aluguel de equipamentos científicos, médicos e hospitalares, sem operador');
INSERT INTO fornecedor.atividade_economica VALUES ('7739-0/03', 'Aluguel de palcos, coberturas e outras estruturas de uso temporário, exceto andaimes');
INSERT INTO fornecedor.atividade_economica VALUES ('7739-0/99', 'Aluguel de outras máquinas e equipamentos comerciais e industriais não especificados anteriormente, sem operador');
INSERT INTO fornecedor.atividade_economica VALUES ('7740-3/00', 'Gestão de ativos intangíveis não-financeiros');
INSERT INTO fornecedor.atividade_economica VALUES ('7810-8/00', 'Seleção e agenciamento de mão-de-obra');
INSERT INTO fornecedor.atividade_economica VALUES ('7820-5/00', 'Locação de mão-de-obra temporária');
INSERT INTO fornecedor.atividade_economica VALUES ('7830-2/00', 'Fornecimento e gestão de recursos humanos para terceiros');
INSERT INTO fornecedor.atividade_economica VALUES ('7911-2/00', 'Agências de viagens');
INSERT INTO fornecedor.atividade_economica VALUES ('7912-1/00', 'Operadores turísticos');
INSERT INTO fornecedor.atividade_economica VALUES ('7990-2/00', 'Serviços de reservas e outros serviços de turismo não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8011-1/01', 'Atividades de vigilância e segurança privada');
INSERT INTO fornecedor.atividade_economica VALUES ('8011-1/02', 'Serviços de adestramento de cães de guarda');
INSERT INTO fornecedor.atividade_economica VALUES ('8012-9/00', 'Atividades de transporte de valores');
INSERT INTO fornecedor.atividade_economica VALUES ('8020-0/00', 'Atividades de monitoramento de sistemas de segurança');
INSERT INTO fornecedor.atividade_economica VALUES ('8030-7/00', 'Atividades de investigação particular');
INSERT INTO fornecedor.atividade_economica VALUES ('8111-7/00', 'Serviços combinados para apoio a edifícios, exceto condomínios prediais');
INSERT INTO fornecedor.atividade_economica VALUES ('8112-5/00', 'Condomínios prediais');
INSERT INTO fornecedor.atividade_economica VALUES ('8121-4/00', 'Limpeza em prédios e em domicílios');
INSERT INTO fornecedor.atividade_economica VALUES ('8122-2/00', 'Imunização e controle de pragas urbanas');
INSERT INTO fornecedor.atividade_economica VALUES ('8129-0/00', 'Atividades de limpeza não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8130-3/00', 'Atividades paisagísticas');
INSERT INTO fornecedor.atividade_economica VALUES ('8211-3/00', 'Serviços combinados de escritório e apoio administrativo');
INSERT INTO fornecedor.atividade_economica VALUES ('8219-9/01', 'Fotocópias');
INSERT INTO fornecedor.atividade_economica VALUES ('8219-9/99', 'Preparação de documentos e serviços especializados de apoio administrativo não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8220-2/00', 'Atividades de teleatendimento');
INSERT INTO fornecedor.atividade_economica VALUES ('8230-0/01', 'Serviços de organização de feiras, congressos, exposições e festas');
INSERT INTO fornecedor.atividade_economica VALUES ('8230-0/02', 'Casas de festas e eventos');
INSERT INTO fornecedor.atividade_economica VALUES ('8291-1/00', 'Atividades de cobranças e informações cadastrais');
INSERT INTO fornecedor.atividade_economica VALUES ('8292-0/00', 'Envasamento e empacotamento sob contrato');
INSERT INTO fornecedor.atividade_economica VALUES ('8299-7/01', 'Medição de consumo de energia elétrica, gás e água');
INSERT INTO fornecedor.atividade_economica VALUES ('8299-7/02', 'Emissão de vales-alimentação, vales-transporte e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('8299-7/03', 'Serviços de gravação de carimbos, exceto confecção');
INSERT INTO fornecedor.atividade_economica VALUES ('8299-7/04', 'Leiloeiros independentes');
INSERT INTO fornecedor.atividade_economica VALUES ('8299-7/05', 'Serviços de levantamento de fundos sob contrato');
INSERT INTO fornecedor.atividade_economica VALUES ('8299-7/06', 'Casas lotéricas');
INSERT INTO fornecedor.atividade_economica VALUES ('8299-7/07', 'Salas de acesso à internet');
INSERT INTO fornecedor.atividade_economica VALUES ('8299-7/99', 'Outras atividades de serviços prestados principalmente às empresas não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8411-6/00', 'Administração pública em geral');
INSERT INTO fornecedor.atividade_economica VALUES ('8412-4/00', 'Regulação das atividades de saúde, educação, serviços culturais e outros serviços sociais');
INSERT INTO fornecedor.atividade_economica VALUES ('8413-2/00', 'Regulação das atividades econômicas');
INSERT INTO fornecedor.atividade_economica VALUES ('8414-1/00', 'Atividades de suporte à administração pública');
INSERT INTO fornecedor.atividade_economica VALUES ('8421-3/00', 'Relações exteriores');
INSERT INTO fornecedor.atividade_economica VALUES ('8422-1/00', 'Defesa');
INSERT INTO fornecedor.atividade_economica VALUES ('8430-2/00', 'Seguridade social obrigatória');
INSERT INTO fornecedor.atividade_economica VALUES ('8511-2/00', 'Educação infantil - creche');
INSERT INTO fornecedor.atividade_economica VALUES ('8512-1/00', 'Educação infantil - pré-escola');
INSERT INTO fornecedor.atividade_economica VALUES ('8513-9/00', 'Ensino fundamental');
INSERT INTO fornecedor.atividade_economica VALUES ('8520-1/00', 'Ensino médio');
INSERT INTO fornecedor.atividade_economica VALUES ('8531-7/00', 'Educação superior - graduação');
INSERT INTO fornecedor.atividade_economica VALUES ('8532-5/00', 'Educação superior - graduação e pós-graduação');
INSERT INTO fornecedor.atividade_economica VALUES ('8533-3/00', 'Educação superior - pós-graduação e extensão');
INSERT INTO fornecedor.atividade_economica VALUES ('8541-4/00', 'Educação profissional de nível técnico');
INSERT INTO fornecedor.atividade_economica VALUES ('8542-2/00', 'Educação profissional de nível tecnológico');
INSERT INTO fornecedor.atividade_economica VALUES ('8550-3/01', 'Administração de caixas escolares');
INSERT INTO fornecedor.atividade_economica VALUES ('8550-3/02', 'Serviços auxiliares à educação');
INSERT INTO fornecedor.atividade_economica VALUES ('8591-1/00', 'Ensino de esportes');
INSERT INTO fornecedor.atividade_economica VALUES ('8592-9/01', 'Ensino de dança');
INSERT INTO fornecedor.atividade_economica VALUES ('8592-9/02', 'Ensino de artes cênicas, exceto dança');
INSERT INTO fornecedor.atividade_economica VALUES ('8592-9/03', 'Ensino de música');
INSERT INTO fornecedor.atividade_economica VALUES ('8592-9/99', 'Ensino de arte e cultura não especificado anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8593-7/00', 'Ensino de idiomas');
INSERT INTO fornecedor.atividade_economica VALUES ('8599-6/01', 'Formação de condutores');
INSERT INTO fornecedor.atividade_economica VALUES ('8599-6/02', 'Cursos de pilotagem');
INSERT INTO fornecedor.atividade_economica VALUES ('8599-6/03', 'Treinamento em informática');
INSERT INTO fornecedor.atividade_economica VALUES ('8599-6/04', 'Treinamento em desenvolvimento profissional e gerencial');
INSERT INTO fornecedor.atividade_economica VALUES ('8599-6/05', 'Cursos preparatórios para concursos');
INSERT INTO fornecedor.atividade_economica VALUES ('8599-6/99', 'Outras atividades de ensino não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8610-1/01', 'Atividades de atendimento hospitalar, exceto pronto-socorro e unidades para atendimento a urgências');
INSERT INTO fornecedor.atividade_economica VALUES ('8610-1/02', 'Atividades de atendimento em pronto-socorro e unidades hospitalares para atendimento a urgências');
INSERT INTO fornecedor.atividade_economica VALUES ('8621-6/01', 'UTI móvel');
INSERT INTO fornecedor.atividade_economica VALUES ('8621-6/02', 'Serviços móveis de atendimento a urgências, exceto por UTI móvel');
INSERT INTO fornecedor.atividade_economica VALUES ('8622-4/00', 'Serviços de remoção de pacientes, exceto os serviços móveis de atendimento a urgências');
INSERT INTO fornecedor.atividade_economica VALUES ('8630-5/01', 'Atividade médica ambulatorial com recursos para realização de procedimentos cirúrgicos');
INSERT INTO fornecedor.atividade_economica VALUES ('8630-5/02', 'Atividade médica ambulatorial com recursos para realização de exames complementares');
INSERT INTO fornecedor.atividade_economica VALUES ('8630-5/03', 'Atividade médica ambulatorial restrita a consultas');
INSERT INTO fornecedor.atividade_economica VALUES ('8630-5/04', 'Atividade odontológica com recursos para realização de procedimentos cirúrgicos');
INSERT INTO fornecedor.atividade_economica VALUES ('8630-5/05', 'Atividade odontológica sem recursos para realização de procedimentos cirúrgicos');
INSERT INTO fornecedor.atividade_economica VALUES ('8630-5/06', 'Serviços de vacinação e imunização humana');
INSERT INTO fornecedor.atividade_economica VALUES ('8630-5/07', 'Atividades de reprodução humana assistida');
INSERT INTO fornecedor.atividade_economica VALUES ('8630-5/99', 'Atividades de atenção ambulatorial não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/01', 'Laboratórios de anatomia patológica e citológica');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/02', 'Laboratórios clínicos');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/03', 'Serviços de diálise e nefrologia');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/04', 'Serviços de tomografia');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/05', 'Serviços de diagnóstico por imagem com uso de radiação ionizante, exceto tomografia');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/06', 'Serviços de ressonância magnética');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/07', 'Serviços de diagnóstico por imagem sem uso de radiação ionizante, exceto ressonância magnética');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/08', 'Serviços de diagnóstico por registro gráfico - ECG, EEG e outros exames análogos');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/09', 'Serviços de diagnóstico por métodos ópticos - endoscopia e outros exames análogos');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/10', 'Serviços de quimioterapia');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/11', 'Serviços de radioterapia');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/12', 'Serviços de hemoterapia');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/13', 'Serviços de litotripsia');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/14', 'Serviços de bancos de células e tecidos humanos');
INSERT INTO fornecedor.atividade_economica VALUES ('8640-2/99', 'Atividades de serviços de complementação diagnóstica e terapêutica não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8650-0/01', 'Atividades de enfermagem');
INSERT INTO fornecedor.atividade_economica VALUES ('8650-0/02', 'Atividades de profissionais da nutrição');
INSERT INTO fornecedor.atividade_economica VALUES ('8650-0/03', 'Atividades de psicologia e psicanálise');
INSERT INTO fornecedor.atividade_economica VALUES ('8650-0/04', 'Atividades de fisioterapia');
INSERT INTO fornecedor.atividade_economica VALUES ('8650-0/05', 'Atividades de terapia ocupacional');
INSERT INTO fornecedor.atividade_economica VALUES ('8650-0/06', 'Atividades de fonoaudiologia');
INSERT INTO fornecedor.atividade_economica VALUES ('8650-0/07', 'Atividades de terapia de nutrição enteral e parenteral');
INSERT INTO fornecedor.atividade_economica VALUES ('8650-0/99', 'Atividades de profissionais da área de saúde não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8660-7/00', 'Atividades de apoio à gestão de saúde');
INSERT INTO fornecedor.atividade_economica VALUES ('8690-9/01', 'Atividades de práticas integrativas e complementares em saúde humana');
INSERT INTO fornecedor.atividade_economica VALUES ('8690-9/02', 'Atividades de banco de leite humano');
INSERT INTO fornecedor.atividade_economica VALUES ('8690-9/99', 'Outras atividades de atenção à saúde humana não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8711-5/01', 'Clínicas e residências geriátricas');
INSERT INTO fornecedor.atividade_economica VALUES ('8711-5/02', 'Instituições de longa permanência para idosos');
INSERT INTO fornecedor.atividade_economica VALUES ('8711-5/03', 'Atividades de assistência a deficientes físicos, imunodeprimidos e convalescentes');
INSERT INTO fornecedor.atividade_economica VALUES ('8711-5/04', 'Centros de apoio a pacientes com câncer e com AIDS');
INSERT INTO fornecedor.atividade_economica VALUES ('8711-5/05', 'Condomínios residenciais para idosos');
INSERT INTO fornecedor.atividade_economica VALUES ('8712-3/00', 'Atividades de fornecimento de infra-estrutura de apoio e assistência a paciente no domicílio');
INSERT INTO fornecedor.atividade_economica VALUES ('8720-4/01', 'Atividades de centros de assistência psicossocial');
INSERT INTO fornecedor.atividade_economica VALUES ('8720-4/99', 'Atividades de assistência psicossocial e à saúde a portadores de distúrbios psíquicos, deficiência mental e dependência química não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8730-1/01', 'Orfanatos');
INSERT INTO fornecedor.atividade_economica VALUES ('8730-1/02', 'Albergues assistenciais');
INSERT INTO fornecedor.atividade_economica VALUES ('8730-1/99', 'Atividades de assistência social prestadas em residências coletivas e particulares não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('8800-6/00', 'Serviços de assistência social sem alojamento');
INSERT INTO fornecedor.atividade_economica VALUES ('9001-9/01', 'Produção teatral');
INSERT INTO fornecedor.atividade_economica VALUES ('9001-9/02', 'Produção musical');
INSERT INTO fornecedor.atividade_economica VALUES ('9001-9/03', 'Produção de espetáculos de dança');
INSERT INTO fornecedor.atividade_economica VALUES ('9001-9/04', 'Produção de espetáculos circenses, de marionetes e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('9001-9/05', 'Produção de espetáculos de rodeios, vaquejadas e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('9001-9/06', 'Atividades de sonorização e de iluminação');
INSERT INTO fornecedor.atividade_economica VALUES ('9001-9/99', 'Artes cênicas, espetáculos e atividades complementares não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('9002-7/01', 'Atividades de artistas plásticos, jornalistas independentes e escritores');
INSERT INTO fornecedor.atividade_economica VALUES ('9002-7/02', 'Restauração de obras-de-arte');
INSERT INTO fornecedor.atividade_economica VALUES ('9003-5/00', 'Gestão de espaços para artes cênicas, espetáculos e outras atividades artísticas');
INSERT INTO fornecedor.atividade_economica VALUES ('9101-5/00', 'Atividades de bibliotecas e arquivos');
INSERT INTO fornecedor.atividade_economica VALUES ('9102-3/01', 'Atividades de museus e de exploração de lugares e prédios históricos e atrações similares');
INSERT INTO fornecedor.atividade_economica VALUES ('9102-3/02', 'Restauração e conservação de lugares e prédios históricos');
INSERT INTO fornecedor.atividade_economica VALUES ('9103-1/00', 'Atividades de jardins botânicos, zoológicos, parques nacionais, reservas ecológicas e áreas de proteção ambiental');
INSERT INTO fornecedor.atividade_economica VALUES ('9200-3/01', 'Casas de bingo');
INSERT INTO fornecedor.atividade_economica VALUES ('9200-3/02', 'Exploração de apostas em corridas de cavalos');
INSERT INTO fornecedor.atividade_economica VALUES ('9200-3/99', 'Exploração de jogos de azar e apostas não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('9311-5/00', 'Gestão de instalações de esportes');
INSERT INTO fornecedor.atividade_economica VALUES ('9312-3/00', 'Clubes sociais, esportivos e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('9313-1/00', 'Atividades de condicionamento físico');
INSERT INTO fornecedor.atividade_economica VALUES ('9319-1/01', 'Produção e promoção de eventos esportivos');
INSERT INTO fornecedor.atividade_economica VALUES ('9319-1/99', 'Outras atividades esportivas não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('9321-2/00', 'Parques de diversão e parques temáticos');
INSERT INTO fornecedor.atividade_economica VALUES ('9329-8/01', 'Discotecas, danceterias, salões de dança e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('9329-8/02', 'Exploração de boliches');
INSERT INTO fornecedor.atividade_economica VALUES ('9329-8/03', 'Exploração de jogos de sinuca, bilhar e similares');
INSERT INTO fornecedor.atividade_economica VALUES ('9329-8/04', 'Exploração de jogos eletrônicos recreativos');
INSERT INTO fornecedor.atividade_economica VALUES ('9329-8/99', 'Outras atividades de recreação e lazer não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('9411-1/00', 'Atividades de organizações associativas patronais e empresariais');
INSERT INTO fornecedor.atividade_economica VALUES ('9412-0/00', 'Atividades de organizações associativas profissionais');
INSERT INTO fornecedor.atividade_economica VALUES ('9420-1/00', 'Atividades de organizações sindicais');
INSERT INTO fornecedor.atividade_economica VALUES ('9430-8/00', 'Atividades de associações de defesa de direitos sociais');
INSERT INTO fornecedor.atividade_economica VALUES ('9491-0/00', 'Atividades de organizações religiosas');
INSERT INTO fornecedor.atividade_economica VALUES ('9492-8/00', 'Atividades de organizações políticas');
INSERT INTO fornecedor.atividade_economica VALUES ('9493-6/00', 'Atividades de organizações associativas ligadas à cultura e à arte');
INSERT INTO fornecedor.atividade_economica VALUES ('9499-5/00', 'Atividades associativas não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('9511-8/00', 'Reparação e manutenção de computadores e de equipamentos periféricos');
INSERT INTO fornecedor.atividade_economica VALUES ('9512-6/00', 'Reparação e manutenção de equipamentos de comunicação');
INSERT INTO fornecedor.atividade_economica VALUES ('9521-5/00', 'Reparação e manutenção de equipamentos eletroeletrônicos de uso pessoal e doméstico');
INSERT INTO fornecedor.atividade_economica VALUES ('9529-1/01', 'Reparação de calçados, de bolsas e artigos de viagem*');
INSERT INTO fornecedor.atividade_economica VALUES ('9529-1/02', 'Chaveiros');
INSERT INTO fornecedor.atividade_economica VALUES ('9529-1/03', 'Reparação de relógios');
INSERT INTO fornecedor.atividade_economica VALUES ('9529-1/04', 'Reparação de bicicletas, triciclos e outros veículos não-motorizados');
INSERT INTO fornecedor.atividade_economica VALUES ('9529-1/05', 'Reparação de artigos do mobiliário');
INSERT INTO fornecedor.atividade_economica VALUES ('9529-1/06', 'Reparação de jóias');
INSERT INTO fornecedor.atividade_economica VALUES ('9529-1/99', 'Reparação e manutenção de outros objetos e equipamentos pessoais e domésticos não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('9601-7/01', 'Lavanderias');
INSERT INTO fornecedor.atividade_economica VALUES ('9601-7/02', 'Tinturarias');
INSERT INTO fornecedor.atividade_economica VALUES ('9601-7/03', 'Toalheiros');
INSERT INTO fornecedor.atividade_economica VALUES ('9602-5/01', 'Cabeleireiros');
INSERT INTO fornecedor.atividade_economica VALUES ('9602-5/02', 'Outras atividades de tratamento de beleza');
INSERT INTO fornecedor.atividade_economica VALUES ('9603-3/01', 'Gestão e manutenção de cemitérios');
INSERT INTO fornecedor.atividade_economica VALUES ('9603-3/02', 'Serviços de cremação');
INSERT INTO fornecedor.atividade_economica VALUES ('9603-3/03', 'Serviços de sepultamento');
INSERT INTO fornecedor.atividade_economica VALUES ('9603-3/04', 'Serviços de funerárias');
INSERT INTO fornecedor.atividade_economica VALUES ('9603-3/05', 'Serviços de somatoconservação');
INSERT INTO fornecedor.atividade_economica VALUES ('9603-3/99', 'Atividades funerárias e serviços relacionados não especificados anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('9609-2/01', 'Clínicas de estética e similares*');
INSERT INTO fornecedor.atividade_economica VALUES ('9609-2/02', 'Agências matrimoniais');
INSERT INTO fornecedor.atividade_economica VALUES ('9609-2/03', 'Alojamento, higiene e embelezamento de animais');
INSERT INTO fornecedor.atividade_economica VALUES ('9609-2/04', 'Exploração de máquinas de serviços pessoais acionadas por moeda');
INSERT INTO fornecedor.atividade_economica VALUES ('9609-2/99', 'Outras atividades de serviços pessoais não especificadas anteriormente');
INSERT INTO fornecedor.atividade_economica VALUES ('9700-5/00', 'Serviços domésticos');
INSERT INTO fornecedor.atividade_economica VALUES ('9900-8/00', 'Organismos internacionais e outras instituições extraterritoriais');


--
-- TOC entry 3457 (class 0 OID 68471)
-- Dependencies: 249
-- Data for Name: atividade_economica_audited; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3458 (class 0 OID 68479)
-- Dependencies: 250
-- Data for Name: atividade_economica_primaria; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3459 (class 0 OID 68484)
-- Dependencies: 251
-- Data for Name: atividade_economica_secundaria; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3462 (class 0 OID 68499)
-- Dependencies: 254
-- Data for Name: categoria_fornecedor; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3464 (class 0 OID 68507)
-- Dependencies: 256
-- Data for Name: conta_bancaria; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3466 (class 0 OID 68515)
-- Dependencies: 258
-- Data for Name: dados_recebimento; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3467 (class 0 OID 68521)
-- Dependencies: 259
-- Data for Name: documento; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3469 (class 0 OID 68528)
-- Dependencies: 261
-- Data for Name: fornecedor; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3470 (class 0 OID 68537)
-- Dependencies: 262
-- Data for Name: fornecedor_atividades_economicas; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3471 (class 0 OID 68540)
-- Dependencies: 263
-- Data for Name: fornecedor_emails; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3472 (class 0 OID 68543)
-- Dependencies: 264
-- Data for Name: fornecedor_telefones; Type: TABLE DATA; Schema: fornecedor; Owner: -
--



--
-- TOC entry 3473 (class 0 OID 68558)
-- Dependencies: 265
-- Data for Name: atividade_economica_primaria_audit; Type: TABLE DATA; Schema: fornecedor_audit; Owner: -
--



--
-- TOC entry 3474 (class 0 OID 68563)
-- Dependencies: 266
-- Data for Name: atividade_economica_secundaria_audit; Type: TABLE DATA; Schema: fornecedor_audit; Owner: -
--



--
-- TOC entry 3475 (class 0 OID 68568)
-- Dependencies: 267
-- Data for Name: conta_bancaria_audit; Type: TABLE DATA; Schema: fornecedor_audit; Owner: -
--



--
-- TOC entry 3476 (class 0 OID 68573)
-- Dependencies: 268
-- Data for Name: dados_recebimento_audit; Type: TABLE DATA; Schema: fornecedor_audit; Owner: -
--



--
-- TOC entry 3477 (class 0 OID 68578)
-- Dependencies: 269
-- Data for Name: documento_audit; Type: TABLE DATA; Schema: fornecedor_audit; Owner: -
--



--
-- TOC entry 3478 (class 0 OID 68583)
-- Dependencies: 270
-- Data for Name: fornecedor_atividades_economicas_audited; Type: TABLE DATA; Schema: fornecedor_audit; Owner: -
--



--
-- TOC entry 3479 (class 0 OID 68588)
-- Dependencies: 271
-- Data for Name: fornecedor_audit; Type: TABLE DATA; Schema: fornecedor_audit; Owner: -
--



--
-- TOC entry 3480 (class 0 OID 68596)
-- Dependencies: 272
-- Data for Name: fornecedor_emails_audited; Type: TABLE DATA; Schema: fornecedor_audit; Owner: -
--



--
-- TOC entry 3481 (class 0 OID 68601)
-- Dependencies: 273
-- Data for Name: fornecedor_telefones_audited; Type: TABLE DATA; Schema: fornecedor_audit; Owner: -
--



--
-- TOC entry 3483 (class 0 OID 68608)
-- Dependencies: 275
-- Data for Name: revision; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.revision VALUES (1, 1571839144882, NULL);


--
-- TOC entry 3484 (class 0 OID 68614)
-- Dependencies: 276
-- Data for Name: anexo; Type: TABLE DATA; Schema: publicacao; Owner: -
--



--
-- TOC entry 3486 (class 0 OID 68621)
-- Dependencies: 278
-- Data for Name: arquivo; Type: TABLE DATA; Schema: publicacao; Owner: -
--



--
-- TOC entry 3487 (class 0 OID 68630)
-- Dependencies: 279
-- Data for Name: aviso_contratacao; Type: TABLE DATA; Schema: publicacao; Owner: -
--



--
-- TOC entry 3488 (class 0 OID 68635)
-- Dependencies: 280
-- Data for Name: aviso_edital; Type: TABLE DATA; Schema: publicacao; Owner: -
--



--
-- TOC entry 3489 (class 0 OID 68640)
-- Dependencies: 281
-- Data for Name: extrato_contrato; Type: TABLE DATA; Schema: publicacao; Owner: -
--



--
-- TOC entry 3491 (class 0 OID 68647)
-- Dependencies: 283
-- Data for Name: publicacao; Type: TABLE DATA; Schema: publicacao; Owner: -
--



--
-- TOC entry 3492 (class 0 OID 68666)
-- Dependencies: 284
-- Data for Name: anexo_audit; Type: TABLE DATA; Schema: publicacao_audit; Owner: -
--



--
-- TOC entry 3493 (class 0 OID 68671)
-- Dependencies: 285
-- Data for Name: arquivo_audit; Type: TABLE DATA; Schema: publicacao_audit; Owner: -
--



--
-- TOC entry 3494 (class 0 OID 68679)
-- Dependencies: 286
-- Data for Name: aviso_contratacao_audit; Type: TABLE DATA; Schema: publicacao_audit; Owner: -
--



--
-- TOC entry 3495 (class 0 OID 68684)
-- Dependencies: 287
-- Data for Name: aviso_edital_audit; Type: TABLE DATA; Schema: publicacao_audit; Owner: -
--



--
-- TOC entry 3496 (class 0 OID 68689)
-- Dependencies: 288
-- Data for Name: categoria_aviso_edital_audit; Type: TABLE DATA; Schema: publicacao_audit; Owner: -
--



--
-- TOC entry 3497 (class 0 OID 68694)
-- Dependencies: 289
-- Data for Name: extrato_contrato_audit; Type: TABLE DATA; Schema: publicacao_audit; Owner: -
--



--
-- TOC entry 3498 (class 0 OID 68699)
-- Dependencies: 290
-- Data for Name: publicacao_audit; Type: TABLE DATA; Schema: publicacao_audit; Owner: -
--



--
-- TOC entry 3525 (class 0 OID 0)
-- Dependencies: 206
-- Name: banco_id_seq; Type: SEQUENCE SET; Schema: cadastro; Owner: -
--

SELECT pg_catalog.setval('cadastro.banco_id_seq', 1, false);


--
-- TOC entry 3526 (class 0 OID 0)
-- Dependencies: 208
-- Name: categoria_id_seq; Type: SEQUENCE SET; Schema: cadastro; Owner: -
--

SELECT pg_catalog.setval('cadastro.categoria_id_seq', 1, false);


--
-- TOC entry 3527 (class 0 OID 0)
-- Dependencies: 210
-- Name: tipo_cadastro_id_seq; Type: SEQUENCE SET; Schema: cadastro; Owner: -
--

SELECT pg_catalog.setval('cadastro.tipo_cadastro_id_seq', 1, false);


--
-- TOC entry 3528 (class 0 OID 0)
-- Dependencies: 212
-- Name: tipo_cadastro_tipo_documento_id_seq; Type: SEQUENCE SET; Schema: cadastro; Owner: -
--

SELECT pg_catalog.setval('cadastro.tipo_cadastro_tipo_documento_id_seq', 1, false);


--
-- TOC entry 3529 (class 0 OID 0)
-- Dependencies: 214
-- Name: tipo_documento_id_seq; Type: SEQUENCE SET; Schema: cadastro; Owner: -
--

SELECT pg_catalog.setval('cadastro.tipo_documento_id_seq', 1, false);


--
-- TOC entry 3530 (class 0 OID 0)
-- Dependencies: 221
-- Name: categoria_aviso_edital_id_seq; Type: SEQUENCE SET; Schema: configuracao; Owner: -
--

SELECT pg_catalog.setval('configuracao.categoria_aviso_edital_id_seq', 1, false);


--
-- TOC entry 3531 (class 0 OID 0)
-- Dependencies: 223
-- Name: grupo_acesso_id_seq; Type: SEQUENCE SET; Schema: configuracao; Owner: -
--

SELECT pg_catalog.setval('configuracao.grupo_acesso_id_seq', 1, false);


--
-- TOC entry 3532 (class 0 OID 0)
-- Dependencies: 225
-- Name: grupo_acesso_permissao_id_seq; Type: SEQUENCE SET; Schema: configuracao; Owner: -
--

SELECT pg_catalog.setval('configuracao.grupo_acesso_permissao_id_seq', 1, false);


--
-- TOC entry 3533 (class 0 OID 0)
-- Dependencies: 227
-- Name: permissao_id_seq; Type: SEQUENCE SET; Schema: configuracao; Owner: -
--

SELECT pg_catalog.setval('configuracao.permissao_id_seq', 1, false);


--
-- TOC entry 3534 (class 0 OID 0)
-- Dependencies: 229
-- Name: pessoa_id_seq; Type: SEQUENCE SET; Schema: configuracao; Owner: -
--

SELECT pg_catalog.setval('configuracao.pessoa_id_seq', 1, true);


--
-- TOC entry 3535 (class 0 OID 0)
-- Dependencies: 237
-- Name: cidade_id_seq; Type: SEQUENCE SET; Schema: endereco; Owner: -
--

SELECT pg_catalog.setval('endereco.cidade_id_seq', 1, false);


--
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 239
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: endereco; Owner: -
--

SELECT pg_catalog.setval('endereco.endereco_id_seq', 1, false);


--
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 241
-- Name: estado_id_seq; Type: SEQUENCE SET; Schema: endereco; Owner: -
--

SELECT pg_catalog.setval('endereco.estado_id_seq', 1, false);


--
-- TOC entry 3538 (class 0 OID 0)
-- Dependencies: 243
-- Name: pais_id_seq; Type: SEQUENCE SET; Schema: endereco; Owner: -
--

SELECT pg_catalog.setval('endereco.pais_id_seq', 1, false);


--
-- TOC entry 3539 (class 0 OID 0)
-- Dependencies: 253
-- Name: categoria_fornecedor_id_seq; Type: SEQUENCE SET; Schema: fornecedor; Owner: -
--

SELECT pg_catalog.setval('fornecedor.categoria_fornecedor_id_seq', 1, false);


--
-- TOC entry 3540 (class 0 OID 0)
-- Dependencies: 255
-- Name: conta_bancaria_id_seq; Type: SEQUENCE SET; Schema: fornecedor; Owner: -
--

SELECT pg_catalog.setval('fornecedor.conta_bancaria_id_seq', 1, false);


--
-- TOC entry 3541 (class 0 OID 0)
-- Dependencies: 257
-- Name: dados_recebimento_id_seq; Type: SEQUENCE SET; Schema: fornecedor; Owner: -
--

SELECT pg_catalog.setval('fornecedor.dados_recebimento_id_seq', 1, false);


--
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 260
-- Name: fornecedor_id_seq; Type: SEQUENCE SET; Schema: fornecedor; Owner: -
--

SELECT pg_catalog.setval('fornecedor.fornecedor_id_seq', 1, false);


--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 274
-- Name: revision_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.revision_id_seq', 1, true);


--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 277
-- Name: arquivo_id_seq; Type: SEQUENCE SET; Schema: publicacao; Owner: -
--

SELECT pg_catalog.setval('publicacao.arquivo_id_seq', 1, false);


--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 282
-- Name: publicacao_id_seq; Type: SEQUENCE SET; Schema: publicacao; Owner: -
--

SELECT pg_catalog.setval('publicacao.publicacao_id_seq', 1, false);


--
-- TOC entry 3064 (class 2606 OID 68250)
-- Name: banco banco_pkey; Type: CONSTRAINT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.banco
    ADD CONSTRAINT banco_pkey PRIMARY KEY (id);


--
-- TOC entry 3066 (class 2606 OID 68258)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- TOC entry 3068 (class 2606 OID 68266)
-- Name: tipo_cadastro tipo_cadastro_pkey; Type: CONSTRAINT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_cadastro
    ADD CONSTRAINT tipo_cadastro_pkey PRIMARY KEY (id);


--
-- TOC entry 3072 (class 2606 OID 68274)
-- Name: tipo_cadastro_tipo_documento tipo_cadastro_tipo_documento_pkey; Type: CONSTRAINT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_cadastro_tipo_documento
    ADD CONSTRAINT tipo_cadastro_tipo_documento_pkey PRIMARY KEY (id);


--
-- TOC entry 3076 (class 2606 OID 68285)
-- Name: tipo_documento tipo_documento_pkey; Type: CONSTRAINT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_documento
    ADD CONSTRAINT tipo_documento_pkey PRIMARY KEY (id);


--
-- TOC entry 3074 (class 2606 OID 68289)
-- Name: tipo_cadastro_tipo_documento uk44s04rckjlbwoiya9lw69bx5h; Type: CONSTRAINT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_cadastro_tipo_documento
    ADD CONSTRAINT uk44s04rckjlbwoiya9lw69bx5h UNIQUE (tipo_cadastro_id, tipo_documento_id);


--
-- TOC entry 3070 (class 2606 OID 68287)
-- Name: tipo_cadastro uk_tipo_cadastro_nome; Type: CONSTRAINT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_cadastro
    ADD CONSTRAINT uk_tipo_cadastro_nome UNIQUE (nome);


--
-- TOC entry 3078 (class 2606 OID 68294)
-- Name: banco_audit banco_audit_pkey; Type: CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.banco_audit
    ADD CONSTRAINT banco_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3080 (class 2606 OID 68299)
-- Name: categoria_audit categoria_audit_pkey; Type: CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.categoria_audit
    ADD CONSTRAINT categoria_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3082 (class 2606 OID 68304)
-- Name: tipo_cadastro_audit tipo_cadastro_audit_pkey; Type: CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.tipo_cadastro_audit
    ADD CONSTRAINT tipo_cadastro_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3084 (class 2606 OID 68309)
-- Name: tipo_cadastro_tipo_documento_audit tipo_cadastro_tipo_documento_audit_pkey; Type: CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.tipo_cadastro_tipo_documento_audit
    ADD CONSTRAINT tipo_cadastro_tipo_documento_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3086 (class 2606 OID 68317)
-- Name: tipo_documento_audit tipo_documento_audit_pkey; Type: CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.tipo_documento_audit
    ADD CONSTRAINT tipo_documento_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3088 (class 2606 OID 68325)
-- Name: categoria_aviso_edital categoria_aviso_edital_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.categoria_aviso_edital
    ADD CONSTRAINT categoria_aviso_edital_pkey PRIMARY KEY (id);


--
-- TOC entry 3096 (class 2606 OID 68341)
-- Name: grupo_acesso_permissao grupo_acesso_permissao_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso_permissao
    ADD CONSTRAINT grupo_acesso_permissao_pkey PRIMARY KEY (id);


--
-- TOC entry 3092 (class 2606 OID 68333)
-- Name: grupo_acesso grupo_acesso_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso
    ADD CONSTRAINT grupo_acesso_pkey PRIMARY KEY (id);


--
-- TOC entry 3100 (class 2606 OID 68352)
-- Name: permissao permissao_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);


--
-- TOC entry 3104 (class 2606 OID 68360)
-- Name: pessoa pessoa_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);


--
-- TOC entry 3094 (class 2606 OID 68372)
-- Name: grupo_acesso uk_grupo_acesso_nome; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso
    ADD CONSTRAINT uk_grupo_acesso_nome UNIQUE (nome);


--
-- TOC entry 3102 (class 2606 OID 68376)
-- Name: permissao uk_permissao_identificador; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.permissao
    ADD CONSTRAINT uk_permissao_identificador UNIQUE (identificador);


--
-- TOC entry 3106 (class 2606 OID 68378)
-- Name: pessoa uk_pessoa_documento; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.pessoa
    ADD CONSTRAINT uk_pessoa_documento UNIQUE (documento);


--
-- TOC entry 3108 (class 2606 OID 68380)
-- Name: usuario uk_usuario_email; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.usuario
    ADD CONSTRAINT uk_usuario_email UNIQUE (email);


--
-- TOC entry 3098 (class 2606 OID 68374)
-- Name: grupo_acesso_permissao ukb9wo361ijcpjnbqbcsrydgg1o; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso_permissao
    ADD CONSTRAINT ukb9wo361ijcpjnbqbcsrydgg1o UNIQUE (grupo_acesso_permissao_id, permissao_id);


--
-- TOC entry 3090 (class 2606 OID 68370)
-- Name: categoria_aviso_edital ukqe17lqax9n9x16qkpyp777lg0; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.categoria_aviso_edital
    ADD CONSTRAINT ukqe17lqax9n9x16qkpyp777lg0 UNIQUE (aviso_edital_id, categoria_id);


--
-- TOC entry 3110 (class 2606 OID 68368)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 3112 (class 2606 OID 68385)
-- Name: grupo_acesso_audit grupo_acesso_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.grupo_acesso_audit
    ADD CONSTRAINT grupo_acesso_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3114 (class 2606 OID 68390)
-- Name: grupo_acesso_permissao_audit grupo_acesso_permissao_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.grupo_acesso_permissao_audit
    ADD CONSTRAINT grupo_acesso_permissao_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3116 (class 2606 OID 68398)
-- Name: permissao_audit permissao_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.permissao_audit
    ADD CONSTRAINT permissao_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3118 (class 2606 OID 68403)
-- Name: pessoa_audit pessoa_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.pessoa_audit
    ADD CONSTRAINT pessoa_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3120 (class 2606 OID 68411)
-- Name: usuario_audit usuario_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.usuario_audit
    ADD CONSTRAINT usuario_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3122 (class 2606 OID 68419)
-- Name: cidade cidade_pkey; Type: CONSTRAINT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- TOC entry 3126 (class 2606 OID 68427)
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 3128 (class 2606 OID 68435)
-- Name: estado estado_pkey; Type: CONSTRAINT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id);


--
-- TOC entry 3132 (class 2606 OID 68443)
-- Name: pais pais_pkey; Type: CONSTRAINT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.pais
    ADD CONSTRAINT pais_pkey PRIMARY KEY (id);


--
-- TOC entry 3124 (class 2606 OID 68445)
-- Name: cidade ukgx7khulpkfsgyqbw5noivqui7; Type: CONSTRAINT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.cidade
    ADD CONSTRAINT ukgx7khulpkfsgyqbw5noivqui7 UNIQUE (nome, estado_id);


--
-- TOC entry 3130 (class 2606 OID 68447)
-- Name: estado ukjobe8rm12hn6m9hjnsgbdvp94; Type: CONSTRAINT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.estado
    ADD CONSTRAINT ukjobe8rm12hn6m9hjnsgbdvp94 UNIQUE (nome, pais_id);


--
-- TOC entry 3134 (class 2606 OID 68452)
-- Name: cidade_audit cidade_audit_pkey; Type: CONSTRAINT; Schema: endereco_audit; Owner: -
--

ALTER TABLE ONLY endereco_audit.cidade_audit
    ADD CONSTRAINT cidade_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3136 (class 2606 OID 68460)
-- Name: endereco_audit endereco_audit_pkey; Type: CONSTRAINT; Schema: endereco_audit; Owner: -
--

ALTER TABLE ONLY endereco_audit.endereco_audit
    ADD CONSTRAINT endereco_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3138 (class 2606 OID 68465)
-- Name: estado_audit estado_audit_pkey; Type: CONSTRAINT; Schema: endereco_audit; Owner: -
--

ALTER TABLE ONLY endereco_audit.estado_audit
    ADD CONSTRAINT estado_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3140 (class 2606 OID 68470)
-- Name: pais_audit pais_audit_pkey; Type: CONSTRAINT; Schema: endereco_audit; Owner: -
--

ALTER TABLE ONLY endereco_audit.pais_audit
    ADD CONSTRAINT pais_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3142 (class 2606 OID 68478)
-- Name: atividade_economica_audited atividade_economica_audited_pkey; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica_audited
    ADD CONSTRAINT atividade_economica_audited_pkey PRIMARY KEY (code, revision);


--
-- TOC entry 3148 (class 2606 OID 68547)
-- Name: atividade_economica atividade_economica_codigo_descricao_unique_constraint; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica
    ADD CONSTRAINT atividade_economica_codigo_descricao_unique_constraint UNIQUE (code, text);


--
-- TOC entry 3150 (class 2606 OID 68496)
-- Name: atividade_economica atividade_economica_pkey; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica
    ADD CONSTRAINT atividade_economica_pkey PRIMARY KEY (code);


--
-- TOC entry 3144 (class 2606 OID 68483)
-- Name: atividade_economica_primaria atividade_economica_primaria_pkey; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica_primaria
    ADD CONSTRAINT atividade_economica_primaria_pkey PRIMARY KEY (fornecedor_id, atividade_economica_id);


--
-- TOC entry 3146 (class 2606 OID 68488)
-- Name: atividade_economica_secundaria atividade_economica_secundaria_pkey; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica_secundaria
    ADD CONSTRAINT atividade_economica_secundaria_pkey PRIMARY KEY (fornecedor_id, atividade_economica_id);


--
-- TOC entry 3152 (class 2606 OID 68504)
-- Name: categoria_fornecedor categoria_fornecedor_pkey; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.categoria_fornecedor
    ADD CONSTRAINT categoria_fornecedor_pkey PRIMARY KEY (id);


--
-- TOC entry 3156 (class 2606 OID 68512)
-- Name: conta_bancaria conta_bancaria_pkey; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.conta_bancaria
    ADD CONSTRAINT conta_bancaria_pkey PRIMARY KEY (id);


--
-- TOC entry 3160 (class 2606 OID 68520)
-- Name: dados_recebimento dados_recebimento_pkey; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.dados_recebimento
    ADD CONSTRAINT dados_recebimento_pkey PRIMARY KEY (id);


--
-- TOC entry 3162 (class 2606 OID 68525)
-- Name: documento documento_pkey; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.documento
    ADD CONSTRAINT documento_pkey PRIMARY KEY (id);


--
-- TOC entry 3166 (class 2606 OID 68536)
-- Name: fornecedor fornecedor_pkey; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor
    ADD CONSTRAINT fornecedor_pkey PRIMARY KEY (id);


--
-- TOC entry 3154 (class 2606 OID 68549)
-- Name: categoria_fornecedor uk3ptybdccmpot8enn97us5xrtv; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.categoria_fornecedor
    ADD CONSTRAINT uk3ptybdccmpot8enn97us5xrtv UNIQUE (fornecedor_id, categoria_id);


--
-- TOC entry 3158 (class 2606 OID 68551)
-- Name: conta_bancaria uk6rogn8dw5ryec860rr94swvhp; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.conta_bancaria
    ADD CONSTRAINT uk6rogn8dw5ryec860rr94swvhp UNIQUE (agencia, digito, numero, banco_id);


--
-- TOC entry 3168 (class 2606 OID 68557)
-- Name: fornecedor uk_fornecedor_codigo_protheus; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor
    ADD CONSTRAINT uk_fornecedor_codigo_protheus UNIQUE (codigo_protheus);


--
-- TOC entry 3170 (class 2606 OID 68555)
-- Name: fornecedor uk_fornecedor_usuario_id; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor
    ADD CONSTRAINT uk_fornecedor_usuario_id UNIQUE (usuario_id);


--
-- TOC entry 3164 (class 2606 OID 68553)
-- Name: documento ukawyadlyg4qbfdd2qp7755r4sq; Type: CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.documento
    ADD CONSTRAINT ukawyadlyg4qbfdd2qp7755r4sq UNIQUE (nome, fornecedor_id);


--
-- TOC entry 3172 (class 2606 OID 68562)
-- Name: atividade_economica_primaria_audit atividade_economica_primaria_audit_pkey; Type: CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.atividade_economica_primaria_audit
    ADD CONSTRAINT atividade_economica_primaria_audit_pkey PRIMARY KEY (revision, fornecedor_id, atividade_economica_id);


--
-- TOC entry 3174 (class 2606 OID 68567)
-- Name: atividade_economica_secundaria_audit atividade_economica_secundaria_audit_pkey; Type: CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.atividade_economica_secundaria_audit
    ADD CONSTRAINT atividade_economica_secundaria_audit_pkey PRIMARY KEY (revision, fornecedor_id, atividade_economica_id);


--
-- TOC entry 3176 (class 2606 OID 68572)
-- Name: conta_bancaria_audit conta_bancaria_audit_pkey; Type: CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.conta_bancaria_audit
    ADD CONSTRAINT conta_bancaria_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3178 (class 2606 OID 68577)
-- Name: dados_recebimento_audit dados_recebimento_audit_pkey; Type: CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.dados_recebimento_audit
    ADD CONSTRAINT dados_recebimento_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3180 (class 2606 OID 68582)
-- Name: documento_audit documento_audit_pkey; Type: CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.documento_audit
    ADD CONSTRAINT documento_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3182 (class 2606 OID 68587)
-- Name: fornecedor_atividades_economicas_audited fornecedor_atividades_economicas_audited_pkey; Type: CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.fornecedor_atividades_economicas_audited
    ADD CONSTRAINT fornecedor_atividades_economicas_audited_pkey PRIMARY KEY (revision, fornecedor_id, atividades_economicas);


--
-- TOC entry 3184 (class 2606 OID 68595)
-- Name: fornecedor_audit fornecedor_audit_pkey; Type: CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.fornecedor_audit
    ADD CONSTRAINT fornecedor_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3186 (class 2606 OID 68600)
-- Name: fornecedor_emails_audited fornecedor_emails_audited_pkey; Type: CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.fornecedor_emails_audited
    ADD CONSTRAINT fornecedor_emails_audited_pkey PRIMARY KEY (revision, fornecedor_id, emails);


--
-- TOC entry 3188 (class 2606 OID 68605)
-- Name: fornecedor_telefones_audited fornecedor_telefones_audited_pkey; Type: CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.fornecedor_telefones_audited
    ADD CONSTRAINT fornecedor_telefones_audited_pkey PRIMARY KEY (revision, fornecedor_id, telefones);


--
-- TOC entry 3190 (class 2606 OID 68613)
-- Name: revision revision_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.revision
    ADD CONSTRAINT revision_pkey PRIMARY KEY (id);


--
-- TOC entry 3192 (class 2606 OID 68618)
-- Name: anexo anexo_pkey; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.anexo
    ADD CONSTRAINT anexo_pkey PRIMARY KEY (id);


--
-- TOC entry 3196 (class 2606 OID 68629)
-- Name: arquivo arquivo_pkey; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.arquivo
    ADD CONSTRAINT arquivo_pkey PRIMARY KEY (id);


--
-- TOC entry 3198 (class 2606 OID 68634)
-- Name: aviso_contratacao aviso_contratacao_pkey; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.aviso_contratacao
    ADD CONSTRAINT aviso_contratacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3202 (class 2606 OID 68639)
-- Name: aviso_edital aviso_edital_pkey; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.aviso_edital
    ADD CONSTRAINT aviso_edital_pkey PRIMARY KEY (id);


--
-- TOC entry 3208 (class 2606 OID 68644)
-- Name: extrato_contrato extrato_contrato_pkey; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.extrato_contrato
    ADD CONSTRAINT extrato_contrato_pkey PRIMARY KEY (id);


--
-- TOC entry 3212 (class 2606 OID 68655)
-- Name: publicacao publicacao_pkey; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.publicacao
    ADD CONSTRAINT publicacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3200 (class 2606 OID 68659)
-- Name: aviso_contratacao uk_aviso_contratacao_numero_processo; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.aviso_contratacao
    ADD CONSTRAINT uk_aviso_contratacao_numero_processo UNIQUE (numero_processo);


--
-- TOC entry 3204 (class 2606 OID 68661)
-- Name: aviso_edital uk_aviso_edital_numero_edital; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.aviso_edital
    ADD CONSTRAINT uk_aviso_edital_numero_edital UNIQUE (numero_edital);


--
-- TOC entry 3206 (class 2606 OID 68663)
-- Name: aviso_edital uk_aviso_edital_numero_processo; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.aviso_edital
    ADD CONSTRAINT uk_aviso_edital_numero_processo UNIQUE (numero_processo);


--
-- TOC entry 3210 (class 2606 OID 68665)
-- Name: extrato_contrato uk_extrato_contrato_numero_processo; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.extrato_contrato
    ADD CONSTRAINT uk_extrato_contrato_numero_processo UNIQUE (numero_processo);


--
-- TOC entry 3194 (class 2606 OID 68657)
-- Name: anexo uksuhb22xcxjivf0718w5kr7nfa; Type: CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.anexo
    ADD CONSTRAINT uksuhb22xcxjivf0718w5kr7nfa UNIQUE (nome, publicacao_id);


--
-- TOC entry 3214 (class 2606 OID 68670)
-- Name: anexo_audit anexo_audit_pkey; Type: CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.anexo_audit
    ADD CONSTRAINT anexo_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3216 (class 2606 OID 68678)
-- Name: arquivo_audit arquivo_audit_pkey; Type: CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.arquivo_audit
    ADD CONSTRAINT arquivo_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3218 (class 2606 OID 68683)
-- Name: aviso_contratacao_audit aviso_contratacao_audit_pkey; Type: CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.aviso_contratacao_audit
    ADD CONSTRAINT aviso_contratacao_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3220 (class 2606 OID 68688)
-- Name: aviso_edital_audit aviso_edital_audit_pkey; Type: CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.aviso_edital_audit
    ADD CONSTRAINT aviso_edital_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3222 (class 2606 OID 68693)
-- Name: categoria_aviso_edital_audit categoria_aviso_edital_audit_pkey; Type: CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.categoria_aviso_edital_audit
    ADD CONSTRAINT categoria_aviso_edital_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3224 (class 2606 OID 68698)
-- Name: extrato_contrato_audit extrato_contrato_audit_pkey; Type: CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.extrato_contrato_audit
    ADD CONSTRAINT extrato_contrato_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3226 (class 2606 OID 68706)
-- Name: publicacao_audit publicacao_audit_pkey; Type: CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.publicacao_audit
    ADD CONSTRAINT publicacao_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3227 (class 2606 OID 68707)
-- Name: tipo_cadastro_tipo_documento fk_tipo_cadastro_tipo_documento_tipo_cadastro_id; Type: FK CONSTRAINT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_cadastro_tipo_documento
    ADD CONSTRAINT fk_tipo_cadastro_tipo_documento_tipo_cadastro_id FOREIGN KEY (tipo_cadastro_id) REFERENCES cadastro.tipo_cadastro(id);


--
-- TOC entry 3228 (class 2606 OID 68712)
-- Name: tipo_cadastro_tipo_documento fk_tipo_cadastro_tipo_documento_tipo_documento_id; Type: FK CONSTRAINT; Schema: cadastro; Owner: -
--

ALTER TABLE ONLY cadastro.tipo_cadastro_tipo_documento
    ADD CONSTRAINT fk_tipo_cadastro_tipo_documento_tipo_documento_id FOREIGN KEY (tipo_documento_id) REFERENCES cadastro.tipo_documento(id);


--
-- TOC entry 3229 (class 2606 OID 68717)
-- Name: banco_audit fk_banco_audit_revision; Type: FK CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.banco_audit
    ADD CONSTRAINT fk_banco_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3230 (class 2606 OID 68722)
-- Name: categoria_audit fk_categoria_audit_revision; Type: FK CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.categoria_audit
    ADD CONSTRAINT fk_categoria_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3231 (class 2606 OID 68727)
-- Name: tipo_cadastro_audit fk_tipo_cadastro_audit_revision; Type: FK CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.tipo_cadastro_audit
    ADD CONSTRAINT fk_tipo_cadastro_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3232 (class 2606 OID 68732)
-- Name: tipo_cadastro_tipo_documento_audit fk_tipo_cadastro_tipo_documento_audit_revision; Type: FK CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.tipo_cadastro_tipo_documento_audit
    ADD CONSTRAINT fk_tipo_cadastro_tipo_documento_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3233 (class 2606 OID 68737)
-- Name: tipo_documento_audit fk_tipo_documento_audit_revision; Type: FK CONSTRAINT; Schema: cadastro_audit; Owner: -
--

ALTER TABLE ONLY cadastro_audit.tipo_documento_audit
    ADD CONSTRAINT fk_tipo_documento_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3234 (class 2606 OID 68742)
-- Name: categoria_aviso_edital fk_categoria_aviso_edital_aviso_edital_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.categoria_aviso_edital
    ADD CONSTRAINT fk_categoria_aviso_edital_aviso_edital_id FOREIGN KEY (aviso_edital_id) REFERENCES publicacao.aviso_edital(id);


--
-- TOC entry 3235 (class 2606 OID 68747)
-- Name: categoria_aviso_edital fk_categoria_aviso_edital_categoria_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.categoria_aviso_edital
    ADD CONSTRAINT fk_categoria_aviso_edital_categoria_id FOREIGN KEY (categoria_id) REFERENCES cadastro.categoria(id);


--
-- TOC entry 3236 (class 2606 OID 68752)
-- Name: grupo_acesso_permissao fk_grupo_acesso_permissao_grupo_acesso_permissao_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso_permissao
    ADD CONSTRAINT fk_grupo_acesso_permissao_grupo_acesso_permissao_id FOREIGN KEY (grupo_acesso_permissao_id) REFERENCES configuracao.grupo_acesso(id);


--
-- TOC entry 3237 (class 2606 OID 68757)
-- Name: grupo_acesso_permissao fk_grupo_acesso_permissao_permissao_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso_permissao
    ADD CONSTRAINT fk_grupo_acesso_permissao_permissao_id FOREIGN KEY (permissao_id) REFERENCES configuracao.permissao(id);


--
-- TOC entry 3238 (class 2606 OID 68762)
-- Name: permissao fk_permissao_permissao_superior_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.permissao
    ADD CONSTRAINT fk_permissao_permissao_superior_id FOREIGN KEY (permissao_superior_id) REFERENCES configuracao.permissao(id);


--
-- TOC entry 3239 (class 2606 OID 68767)
-- Name: usuario fk_usuario_grupo_acesso_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.usuario
    ADD CONSTRAINT fk_usuario_grupo_acesso_id FOREIGN KEY (grupo_acesso_id) REFERENCES configuracao.grupo_acesso(id);


--
-- TOC entry 3240 (class 2606 OID 68772)
-- Name: usuario fk_usuario_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.usuario
    ADD CONSTRAINT fk_usuario_id FOREIGN KEY (id) REFERENCES configuracao.pessoa(id);


--
-- TOC entry 3241 (class 2606 OID 68777)
-- Name: grupo_acesso_audit fk_grupo_acesso_audit_revision; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.grupo_acesso_audit
    ADD CONSTRAINT fk_grupo_acesso_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3242 (class 2606 OID 68782)
-- Name: grupo_acesso_permissao_audit fk_grupo_acesso_permissao_audit_revision; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.grupo_acesso_permissao_audit
    ADD CONSTRAINT fk_grupo_acesso_permissao_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3243 (class 2606 OID 68787)
-- Name: permissao_audit fk_permissao_audit_revision; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.permissao_audit
    ADD CONSTRAINT fk_permissao_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3244 (class 2606 OID 68792)
-- Name: pessoa_audit fk_pessoa_audit_revision; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.pessoa_audit
    ADD CONSTRAINT fk_pessoa_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3245 (class 2606 OID 68797)
-- Name: usuario_audit fk_usuario_audit_id; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.usuario_audit
    ADD CONSTRAINT fk_usuario_audit_id FOREIGN KEY (id, revision) REFERENCES configuracao_audit.pessoa_audit(id, revision);


--
-- TOC entry 3246 (class 2606 OID 68802)
-- Name: cidade fk_cidade_estado_id; Type: FK CONSTRAINT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.cidade
    ADD CONSTRAINT fk_cidade_estado_id FOREIGN KEY (estado_id) REFERENCES endereco.estado(id);


--
-- TOC entry 3247 (class 2606 OID 68807)
-- Name: endereco fk_endereco_cidade_id; Type: FK CONSTRAINT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.endereco
    ADD CONSTRAINT fk_endereco_cidade_id FOREIGN KEY (cidade_id) REFERENCES endereco.cidade(id);


--
-- TOC entry 3248 (class 2606 OID 68812)
-- Name: estado fk_estado_pais_id; Type: FK CONSTRAINT; Schema: endereco; Owner: -
--

ALTER TABLE ONLY endereco.estado
    ADD CONSTRAINT fk_estado_pais_id FOREIGN KEY (pais_id) REFERENCES endereco.pais(id);


--
-- TOC entry 3249 (class 2606 OID 68817)
-- Name: cidade_audit fk_cidade_audit_revision; Type: FK CONSTRAINT; Schema: endereco_audit; Owner: -
--

ALTER TABLE ONLY endereco_audit.cidade_audit
    ADD CONSTRAINT fk_cidade_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3250 (class 2606 OID 68822)
-- Name: endereco_audit fk_endereco_audit_revision; Type: FK CONSTRAINT; Schema: endereco_audit; Owner: -
--

ALTER TABLE ONLY endereco_audit.endereco_audit
    ADD CONSTRAINT fk_endereco_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3251 (class 2606 OID 68827)
-- Name: estado_audit fk_estado_audit_revision; Type: FK CONSTRAINT; Schema: endereco_audit; Owner: -
--

ALTER TABLE ONLY endereco_audit.estado_audit
    ADD CONSTRAINT fk_estado_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3252 (class 2606 OID 68832)
-- Name: pais_audit fk_pais_audit_revision; Type: FK CONSTRAINT; Schema: endereco_audit; Owner: -
--

ALTER TABLE ONLY endereco_audit.pais_audit
    ADD CONSTRAINT fk_pais_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3253 (class 2606 OID 68837)
-- Name: atividade_economica_audited fk_atividade_economica_audited_revision; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica_audited
    ADD CONSTRAINT fk_atividade_economica_audited_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3255 (class 2606 OID 68847)
-- Name: atividade_economica_primaria fk_atividade_economica_primaria_atividade_economica_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica_primaria
    ADD CONSTRAINT fk_atividade_economica_primaria_atividade_economica_id FOREIGN KEY (atividade_economica_id) REFERENCES fornecedor.atividade_economica(code);


--
-- TOC entry 3254 (class 2606 OID 68842)
-- Name: atividade_economica_primaria fk_atividade_economica_primaria_fornecedor_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica_primaria
    ADD CONSTRAINT fk_atividade_economica_primaria_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES fornecedor.fornecedor(id);


--
-- TOC entry 3257 (class 2606 OID 68857)
-- Name: atividade_economica_secundaria fk_atividade_economica_secundaria_atividade_economica_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica_secundaria
    ADD CONSTRAINT fk_atividade_economica_secundaria_atividade_economica_id FOREIGN KEY (atividade_economica_id) REFERENCES fornecedor.atividade_economica(code);


--
-- TOC entry 3256 (class 2606 OID 68852)
-- Name: atividade_economica_secundaria fk_atividade_economica_secundaria_fornecedor_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.atividade_economica_secundaria
    ADD CONSTRAINT fk_atividade_economica_secundaria_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES fornecedor.fornecedor(id);


--
-- TOC entry 3258 (class 2606 OID 68862)
-- Name: categoria_fornecedor fk_categoria_fornecedor_categoria_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.categoria_fornecedor
    ADD CONSTRAINT fk_categoria_fornecedor_categoria_id FOREIGN KEY (categoria_id) REFERENCES cadastro.categoria(id);


--
-- TOC entry 3259 (class 2606 OID 68867)
-- Name: categoria_fornecedor fk_categoria_fornecedor_fornecedor_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.categoria_fornecedor
    ADD CONSTRAINT fk_categoria_fornecedor_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES fornecedor.fornecedor(id);


--
-- TOC entry 3260 (class 2606 OID 68872)
-- Name: conta_bancaria fk_conta_bancaria_banco_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.conta_bancaria
    ADD CONSTRAINT fk_conta_bancaria_banco_id FOREIGN KEY (banco_id) REFERENCES cadastro.banco(id);


--
-- TOC entry 3261 (class 2606 OID 68877)
-- Name: dados_recebimento fk_dados_recebimento_conta_bancaria_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.dados_recebimento
    ADD CONSTRAINT fk_dados_recebimento_conta_bancaria_id FOREIGN KEY (conta_bancaria_id) REFERENCES fornecedor.conta_bancaria(id);


--
-- TOC entry 3262 (class 2606 OID 68882)
-- Name: documento fk_documento_fornecedor_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.documento
    ADD CONSTRAINT fk_documento_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES fornecedor.fornecedor(id);


--
-- TOC entry 3264 (class 2606 OID 68892)
-- Name: documento fk_documento_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.documento
    ADD CONSTRAINT fk_documento_id FOREIGN KEY (id) REFERENCES publicacao.arquivo(id);


--
-- TOC entry 3263 (class 2606 OID 68887)
-- Name: documento fk_documento_tipo_cadastro_tipo_documento_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.documento
    ADD CONSTRAINT fk_documento_tipo_cadastro_tipo_documento_id FOREIGN KEY (tipo_cadastro_tipo_documento_id) REFERENCES cadastro.tipo_cadastro_tipo_documento(id);


--
-- TOC entry 3269 (class 2606 OID 68917)
-- Name: fornecedor_atividades_economicas fk_fornecedor_atividades_economicas_fornecedor_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor_atividades_economicas
    ADD CONSTRAINT fk_fornecedor_atividades_economicas_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES fornecedor.fornecedor(id);


--
-- TOC entry 3265 (class 2606 OID 68897)
-- Name: fornecedor fk_fornecedor_dados_recebimento_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor
    ADD CONSTRAINT fk_fornecedor_dados_recebimento_id FOREIGN KEY (dados_recebimento_id) REFERENCES fornecedor.dados_recebimento(id);


--
-- TOC entry 3270 (class 2606 OID 68922)
-- Name: fornecedor_emails fk_fornecedor_emails_fornecedor_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor_emails
    ADD CONSTRAINT fk_fornecedor_emails_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES fornecedor.fornecedor(id);


--
-- TOC entry 3266 (class 2606 OID 68902)
-- Name: fornecedor fk_fornecedor_endereco_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor
    ADD CONSTRAINT fk_fornecedor_endereco_id FOREIGN KEY (endereco_id) REFERENCES endereco.endereco(id);


--
-- TOC entry 3271 (class 2606 OID 68927)
-- Name: fornecedor_telefones fk_fornecedor_telefones_fornecedor_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor_telefones
    ADD CONSTRAINT fk_fornecedor_telefones_fornecedor_id FOREIGN KEY (fornecedor_id) REFERENCES fornecedor.fornecedor(id);


--
-- TOC entry 3267 (class 2606 OID 68907)
-- Name: fornecedor fk_fornecedor_tipo_cadastro_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor
    ADD CONSTRAINT fk_fornecedor_tipo_cadastro_id FOREIGN KEY (tipo_cadastro_id) REFERENCES cadastro.tipo_cadastro(id);


--
-- TOC entry 3268 (class 2606 OID 68912)
-- Name: fornecedor fk_fornecedor_usuario_id; Type: FK CONSTRAINT; Schema: fornecedor; Owner: -
--

ALTER TABLE ONLY fornecedor.fornecedor
    ADD CONSTRAINT fk_fornecedor_usuario_id FOREIGN KEY (usuario_id) REFERENCES configuracao.usuario(id);


--
-- TOC entry 3272 (class 2606 OID 68932)
-- Name: atividade_economica_primaria_audit fk_atividade_economica_primaria_audit_revision; Type: FK CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.atividade_economica_primaria_audit
    ADD CONSTRAINT fk_atividade_economica_primaria_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3273 (class 2606 OID 68937)
-- Name: atividade_economica_secundaria_audit fk_atividade_economica_secundaria_audit_revision; Type: FK CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.atividade_economica_secundaria_audit
    ADD CONSTRAINT fk_atividade_economica_secundaria_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3274 (class 2606 OID 68942)
-- Name: conta_bancaria_audit fk_conta_bancaria_audit_revision; Type: FK CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.conta_bancaria_audit
    ADD CONSTRAINT fk_conta_bancaria_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3275 (class 2606 OID 68947)
-- Name: dados_recebimento_audit fk_dados_recebimento_audit_revision; Type: FK CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.dados_recebimento_audit
    ADD CONSTRAINT fk_dados_recebimento_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3276 (class 2606 OID 68952)
-- Name: documento_audit fk_documento_audit_id; Type: FK CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.documento_audit
    ADD CONSTRAINT fk_documento_audit_id FOREIGN KEY (id, revision) REFERENCES publicacao_audit.arquivo_audit(id, revision);


--
-- TOC entry 3277 (class 2606 OID 68957)
-- Name: fornecedor_atividades_economicas_audited fk_fornecedor_atividades_economicas_audited_revision; Type: FK CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.fornecedor_atividades_economicas_audited
    ADD CONSTRAINT fk_fornecedor_atividades_economicas_audited_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3278 (class 2606 OID 68962)
-- Name: fornecedor_audit fk_fornecedor_audit_revision; Type: FK CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.fornecedor_audit
    ADD CONSTRAINT fk_fornecedor_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3279 (class 2606 OID 68967)
-- Name: fornecedor_emails_audited fk_fornecedor_emails_audited_revision; Type: FK CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.fornecedor_emails_audited
    ADD CONSTRAINT fk_fornecedor_emails_audited_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3280 (class 2606 OID 68972)
-- Name: fornecedor_telefones_audited fk_fornecedor_telefones_audited_revision; Type: FK CONSTRAINT; Schema: fornecedor_audit; Owner: -
--

ALTER TABLE ONLY fornecedor_audit.fornecedor_telefones_audited
    ADD CONSTRAINT fk_fornecedor_telefones_audited_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3282 (class 2606 OID 68982)
-- Name: anexo fk_anexo_id; Type: FK CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.anexo
    ADD CONSTRAINT fk_anexo_id FOREIGN KEY (id) REFERENCES publicacao.arquivo(id);


--
-- TOC entry 3281 (class 2606 OID 68977)
-- Name: anexo fk_anexo_publicacao_id; Type: FK CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.anexo
    ADD CONSTRAINT fk_anexo_publicacao_id FOREIGN KEY (publicacao_id) REFERENCES publicacao.publicacao(id);


--
-- TOC entry 3283 (class 2606 OID 68987)
-- Name: aviso_contratacao fk_aviso_contratacao_id; Type: FK CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.aviso_contratacao
    ADD CONSTRAINT fk_aviso_contratacao_id FOREIGN KEY (id) REFERENCES publicacao.publicacao(id);


--
-- TOC entry 3284 (class 2606 OID 68992)
-- Name: aviso_edital fk_aviso_edital_id; Type: FK CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.aviso_edital
    ADD CONSTRAINT fk_aviso_edital_id FOREIGN KEY (id) REFERENCES publicacao.publicacao(id);


--
-- TOC entry 3285 (class 2606 OID 68997)
-- Name: extrato_contrato fk_extrato_contrato_id; Type: FK CONSTRAINT; Schema: publicacao; Owner: -
--

ALTER TABLE ONLY publicacao.extrato_contrato
    ADD CONSTRAINT fk_extrato_contrato_id FOREIGN KEY (id) REFERENCES publicacao.publicacao(id);


--
-- TOC entry 3286 (class 2606 OID 69002)
-- Name: anexo_audit fk_anexo_audit_id; Type: FK CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.anexo_audit
    ADD CONSTRAINT fk_anexo_audit_id FOREIGN KEY (id, revision) REFERENCES publicacao_audit.arquivo_audit(id, revision);


--
-- TOC entry 3287 (class 2606 OID 69007)
-- Name: arquivo_audit fk_arquivo_audit_revision; Type: FK CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.arquivo_audit
    ADD CONSTRAINT fk_arquivo_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3288 (class 2606 OID 69012)
-- Name: aviso_contratacao_audit fk_aviso_contratacao_audit_id; Type: FK CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.aviso_contratacao_audit
    ADD CONSTRAINT fk_aviso_contratacao_audit_id FOREIGN KEY (id, revision) REFERENCES publicacao_audit.publicacao_audit(id, revision);


--
-- TOC entry 3289 (class 2606 OID 69017)
-- Name: aviso_edital_audit fk_aviso_edital_audit_id; Type: FK CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.aviso_edital_audit
    ADD CONSTRAINT fk_aviso_edital_audit_id FOREIGN KEY (id, revision) REFERENCES publicacao_audit.publicacao_audit(id, revision);


--
-- TOC entry 3290 (class 2606 OID 69022)
-- Name: categoria_aviso_edital_audit fk_categoria_aviso_edital_audit_revision; Type: FK CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.categoria_aviso_edital_audit
    ADD CONSTRAINT fk_categoria_aviso_edital_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3291 (class 2606 OID 69027)
-- Name: extrato_contrato_audit fk_extrato_contrato_audit_id; Type: FK CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.extrato_contrato_audit
    ADD CONSTRAINT fk_extrato_contrato_audit_id FOREIGN KEY (id, revision) REFERENCES publicacao_audit.publicacao_audit(id, revision);


--
-- TOC entry 3292 (class 2606 OID 69032)
-- Name: publicacao_audit fk_publicacao_audit_revision; Type: FK CONSTRAINT; Schema: publicacao_audit; Owner: -
--

ALTER TABLE ONLY publicacao_audit.publicacao_audit
    ADD CONSTRAINT fk_publicacao_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


-- Completed on 2019-10-23 14:14:38 UTC

--
-- PostgreSQL database dump complete
--

create table fornecedor_audit.categoria_fornecedor_audit (id int8 not null, revision int8 not null, revision_type int2, categoria_id int8, fornecedor_id int8, primary key (id, revision));
alter table fornecedor_audit.categoria_fornecedor_audit add constraint FK_categoria_fornecedor_audit_revision foreign key (revision) references public.revision(id);
