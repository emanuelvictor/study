--
-- PostgreSQL database dump
--

-- Dumped from database version 11.4
-- Dumped by pg_dump version 11.4

-- Started on 2019-07-15 17:06:21 -03

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
-- TOC entry 12 (class 2615 OID 311105)
-- Name: configuracao; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA configuracao;


--
-- TOC entry 9 (class 2615 OID 311108)
-- Name: configuracao_audit; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA configuracao_audit;


SET default_with_oids = false;


--
-- TOC entry 218 (class 1259 OID 311178)
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
-- TOC entry 217 (class 1259 OID 311176)
-- Name: grupo_acesso_id_seq; Type: SEQUENCE; Schema: configuracao; Owner: -
--

CREATE SEQUENCE configuracao.grupo_acesso_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3225 (class 0 OID 0)
-- Dependencies: 217
-- Name: grupo_acesso_id_seq; Type: SEQUENCE OWNED BY; Schema: configuracao; Owner: -
--

ALTER SEQUENCE configuracao.grupo_acesso_id_seq OWNED BY configuracao.grupo_acesso.id;


--
-- TOC entry 220 (class 1259 OID 311186)
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
-- TOC entry 219 (class 1259 OID 311184)
-- Name: grupo_acesso_permissao_id_seq; Type: SEQUENCE; Schema: configuracao; Owner: -
--

CREATE SEQUENCE configuracao.grupo_acesso_permissao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3226 (class 0 OID 0)
-- Dependencies: 219
-- Name: grupo_acesso_permissao_id_seq; Type: SEQUENCE OWNED BY; Schema: configuracao; Owner: -
--

ALTER SEQUENCE configuracao.grupo_acesso_permissao_id_seq OWNED BY configuracao.grupo_acesso_permissao.id;


--
-- TOC entry 222 (class 1259 OID 311194)
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
-- TOC entry 221 (class 1259 OID 311192)
-- Name: permissao_id_seq; Type: SEQUENCE; Schema: configuracao; Owner: -
--

CREATE SEQUENCE configuracao.permissao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3227 (class 0 OID 0)
-- Dependencies: 221
-- Name: permissao_id_seq; Type: SEQUENCE OWNED BY; Schema: configuracao; Owner: -
--

ALTER SEQUENCE configuracao.permissao_id_seq OWNED BY configuracao.permissao.id;


--
-- TOC entry 224 (class 1259 OID 311205)
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
-- TOC entry 223 (class 1259 OID 311203)
-- Name: pessoa_id_seq; Type: SEQUENCE; Schema: configuracao; Owner: -
--

CREATE SEQUENCE configuracao.pessoa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3228 (class 0 OID 0)
-- Dependencies: 223
-- Name: pessoa_id_seq; Type: SEQUENCE OWNED BY; Schema: configuracao; Owner: -
--

ALTER SEQUENCE configuracao.pessoa_id_seq OWNED BY configuracao.pessoa.id;


--
-- TOC entry 225 (class 1259 OID 311211)
-- Name: usuario; Type: TABLE; Schema: configuracao; Owner: -
--

CREATE TABLE configuracao.usuario (
    ativo boolean NOT NULL,
    codigo character varying(255),
    email character varying(150) NOT NULL,
    interno boolean NOT NULL,
    root boolean NOT NULL,
    senha character varying(100) NOT NULL,
    tentativas_login smallint,
    ultima_tentativa_login timestamp without time zone,
    ultimo_acesso timestamp without time zone,
    id bigint NOT NULL,
    grupo_acesso_id bigint
);


--
-- TOC entry 226 (class 1259 OID 311229)
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
-- TOC entry 227 (class 1259 OID 311234)
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
-- TOC entry 228 (class 1259 OID 311239)
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
-- TOC entry 229 (class 1259 OID 311247)
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
-- TOC entry 230 (class 1259 OID 311252)
-- Name: usuario_audit; Type: TABLE; Schema: configuracao_audit; Owner: -
--

CREATE TABLE configuracao_audit.usuario_audit (
    id bigint NOT NULL,
    revision bigint NOT NULL,
    ativo boolean,
    codigo character varying(255),
    email character varying(150),
    interno boolean,
    root boolean,
    senha character varying(100),
    tentativas_login smallint,
    ultima_tentativa_login timestamp without time zone,
    ultimo_acesso timestamp without time zone,
    grupo_acesso_id bigint
);


--
-- TOC entry 244 (class 1259 OID 311321)
-- Name: revision; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.revision (
    id bigint NOT NULL,
    "timestamp" bigint NOT NULL,
    user_id bigint
);


--
-- TOC entry 243 (class 1259 OID 311319)
-- Name: revision_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.revision_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3233 (class 0 OID 0)
-- Dependencies: 243
-- Name: revision_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.revision_id_seq OWNED BY public.revision.id;


--
-- TOC entry 2948 (class 2604 OID 311181)
-- Name: grupo_acesso id; Type: DEFAULT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso ALTER COLUMN id SET DEFAULT nextval('configuracao.grupo_acesso_id_seq'::regclass);


--
-- TOC entry 2949 (class 2604 OID 311189)
-- Name: grupo_acesso_permissao id; Type: DEFAULT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso_permissao ALTER COLUMN id SET DEFAULT nextval('configuracao.grupo_acesso_permissao_id_seq'::regclass);


--
-- TOC entry 2950 (class 2604 OID 311197)
-- Name: permissao id; Type: DEFAULT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.permissao ALTER COLUMN id SET DEFAULT nextval('configuracao.permissao_id_seq'::regclass);


--
-- TOC entry 2951 (class 2604 OID 311208)
-- Name: pessoa id; Type: DEFAULT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.pessoa ALTER COLUMN id SET DEFAULT nextval('configuracao.pessoa_id_seq'::regclass);

--
-- TOC entry 2956 (class 2604 OID 311324)
-- Name: revision id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.revision ALTER COLUMN id SET DEFAULT nextval('public.revision_id_seq'::regclass);


--
-- TOC entry 3239 (class 0 OID 0)
-- Dependencies: 217
-- Name: grupo_acesso_id_seq; Type: SEQUENCE SET; Schema: configuracao; Owner: -
--

SELECT pg_catalog.setval('configuracao.grupo_acesso_id_seq', 1, false);


--
-- TOC entry 3240 (class 0 OID 0)
-- Dependencies: 219
-- Name: grupo_acesso_permissao_id_seq; Type: SEQUENCE SET; Schema: configuracao; Owner: -
--

SELECT pg_catalog.setval('configuracao.grupo_acesso_permissao_id_seq', 1, false);


--
-- TOC entry 3241 (class 0 OID 0)
-- Dependencies: 221
-- Name: permissao_id_seq; Type: SEQUENCE SET; Schema: configuracao; Owner: -
--

SELECT pg_catalog.setval('configuracao.permissao_id_seq', 1, false);


--
-- TOC entry 3242 (class 0 OID 0)
-- Dependencies: 223
-- Name: pessoa_id_seq; Type: SEQUENCE SET; Schema: configuracao; Owner: -
--

SELECT pg_catalog.setval('configuracao.pessoa_id_seq', 1, false);


--
-- TOC entry 3247 (class 0 OID 0)
-- Dependencies: 243
-- Name: revision_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.revision_id_seq', 1, false);


ALTER TABLE ONLY configuracao.grupo_acesso_permissao
    ADD CONSTRAINT grupo_acesso_permissao_pkey PRIMARY KEY (id);


--
-- TOC entry 2978 (class 2606 OID 311183)
-- Name: grupo_acesso grupo_acesso_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso
    ADD CONSTRAINT grupo_acesso_pkey PRIMARY KEY (id);


--
-- TOC entry 2986 (class 2606 OID 311202)
-- Name: permissao permissao_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);


--
-- TOC entry 2990 (class 2606 OID 311210)
-- Name: pessoa pessoa_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);


--
-- TOC entry 2980 (class 2606 OID 311220)
-- Name: grupo_acesso uk_grupo_acesso_nome; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso
    ADD CONSTRAINT uk_grupo_acesso_nome UNIQUE (nome);


--
-- TOC entry 2988 (class 2606 OID 311224)
-- Name: permissao uk_permissao_identificador; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.permissao
    ADD CONSTRAINT uk_permissao_identificador UNIQUE (identificador);


--
-- TOC entry 2992 (class 2606 OID 311226)
-- Name: pessoa uk_pessoa_documento; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.pessoa
    ADD CONSTRAINT uk_pessoa_documento UNIQUE (documento);


--
-- TOC entry 2994 (class 2606 OID 311228)
-- Name: usuario uk_usuario_email; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.usuario
    ADD CONSTRAINT uk_usuario_email UNIQUE (email);


--
-- TOC entry 2984 (class 2606 OID 311222)
-- Name: grupo_acesso_permissao ukb9wo361ijcpjnbqbcsrydgg1o; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso_permissao
    ADD CONSTRAINT ukb9wo361ijcpjnbqbcsrydgg1o UNIQUE (grupo_acesso_permissao_id, permissao_id);


--
-- TOC entry 2996 (class 2606 OID 311218)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2998 (class 2606 OID 311233)
-- Name: grupo_acesso_audit grupo_acesso_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.grupo_acesso_audit
    ADD CONSTRAINT grupo_acesso_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3000 (class 2606 OID 311238)
-- Name: grupo_acesso_permissao_audit grupo_acesso_permissao_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.grupo_acesso_permissao_audit
    ADD CONSTRAINT grupo_acesso_permissao_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3002 (class 2606 OID 311246)
-- Name: permissao_audit permissao_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.permissao_audit
    ADD CONSTRAINT permissao_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3004 (class 2606 OID 311251)
-- Name: pessoa_audit pessoa_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.pessoa_audit
    ADD CONSTRAINT pessoa_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3006 (class 2606 OID 311259)
-- Name: usuario_audit usuario_audit_pkey; Type: CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.usuario_audit
    ADD CONSTRAINT usuario_audit_pkey PRIMARY KEY (id, revision);


--
-- TOC entry 3028 (class 2606 OID 311326)
-- Name: revision revision_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.revision
    ADD CONSTRAINT revision_pkey PRIMARY KEY (id);

--
-- TOC entry 3034 (class 2606 OID 311352)
-- Name: grupo_acesso_permissao fk_grupo_acesso_permissao_grupo_acesso_permissao_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso_permissao
    ADD CONSTRAINT fk_grupo_acesso_permissao_grupo_acesso_permissao_id FOREIGN KEY (grupo_acesso_permissao_id) REFERENCES configuracao.grupo_acesso(id);


--
-- TOC entry 3035 (class 2606 OID 311357)
-- Name: grupo_acesso_permissao fk_grupo_acesso_permissao_permissao_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.grupo_acesso_permissao
    ADD CONSTRAINT fk_grupo_acesso_permissao_permissao_id FOREIGN KEY (permissao_id) REFERENCES configuracao.permissao(id);


--
-- TOC entry 3036 (class 2606 OID 311362)
-- Name: permissao fk_permissao_permissao_superior_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.permissao
    ADD CONSTRAINT fk_permissao_permissao_superior_id FOREIGN KEY (permissao_superior_id) REFERENCES configuracao.permissao(id);


--
-- TOC entry 3037 (class 2606 OID 311367)
-- Name: usuario fk_usuario_grupo_acesso_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.usuario
    ADD CONSTRAINT fk_usuario_grupo_acesso_id FOREIGN KEY (grupo_acesso_id) REFERENCES configuracao.grupo_acesso(id);


--
-- TOC entry 3038 (class 2606 OID 311372)
-- Name: usuario fk_usuario_id; Type: FK CONSTRAINT; Schema: configuracao; Owner: -
--

ALTER TABLE ONLY configuracao.usuario
    ADD CONSTRAINT fk_usuario_id FOREIGN KEY (id) REFERENCES configuracao.pessoa(id);


--
-- TOC entry 3039 (class 2606 OID 311377)
-- Name: grupo_acesso_audit fk_grupo_acesso_audit_revision; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.grupo_acesso_audit
    ADD CONSTRAINT fk_grupo_acesso_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3040 (class 2606 OID 311382)
-- Name: grupo_acesso_permissao_audit fk_grupo_acesso_permissao_audit_revision; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.grupo_acesso_permissao_audit
    ADD CONSTRAINT fk_grupo_acesso_permissao_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3041 (class 2606 OID 311387)
-- Name: permissao_audit fk_permissao_audit_revision; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.permissao_audit
    ADD CONSTRAINT fk_permissao_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3042 (class 2606 OID 311392)
-- Name: pessoa_audit fk_pessoa_audit_revision; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.pessoa_audit
    ADD CONSTRAINT fk_pessoa_audit_revision FOREIGN KEY (revision) REFERENCES public.revision(id);


--
-- TOC entry 3043 (class 2606 OID 311397)
-- Name: usuario_audit fk_usuario_audit_id; Type: FK CONSTRAINT; Schema: configuracao_audit; Owner: -
--

ALTER TABLE ONLY configuracao_audit.usuario_audit
    ADD CONSTRAINT fk_usuario_audit_id FOREIGN KEY (id, revision) REFERENCES configuracao_audit.pessoa_audit(id, revision);

-- Completed on 2019-07-15 17:06:21 -03

--
-- PostgreSQL database dump complete
--


INSERT INTO configuracao.pessoa (id, created, updated, documento, nome) VALUES (1, '2019-02-11 19:49:53.65032', NULL, NULL, 'Administrador');
INSERT INTO configuracao.usuario (root, ativo, codigo, email, senha, tentativas_login, ultima_tentativa_login, ultimo_acesso, id, grupo_acesso_id, interno) VALUES (true, true, NULL, 'admin@pti.org.br', '$2a$10$ZpR4ethoJ1Ne4Qn42OSX5euVeGi3tkrm4UwO6MlwZ5xCeOAoVB25m', NULL, NULL, NULL, 1, NULL, false);


INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (4000, now(), NULL, 'usuarios', 'Usuario', NULL);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (40000, now(), NULL, 'usuarios/post', 'Adicionar', 4000);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (40001, now(), NULL, 'usuarios/put', 'Alterar', 4000);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (40002, now(), NULL, 'usuarios/get', 'Consultar', 4000);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (400001, now(), NULL, 'usuarios/put/change-password', 'Alterar senha', 40001);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (400002, now(), NULL, 'usuarios/put/activate', 'Ativar/Desativar', 40001);

INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (5000, now(), NULL, 'grupos-acesso', 'Grupo de Acesso', NULL);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (50000, now(), NULL, 'grupos-acesso/post', 'Adicionar', 5000);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (50001, now(), NULL, 'grupos-acesso/put', 'Alterar', 5000);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (50002, now(), NULL, 'grupos-acesso/get', 'Consultar', 5000);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (500001, now(), NULL, 'grupos-acesso/put/activate', 'Ativar', 50001);


UPDATE configuracao.permissao SET permissao_superior_id = 10001 WHERE ID = 100001;
UPDATE configuracao.permissao SET permissao_superior_id = 20001 WHERE ID = 200001;

UPDATE configuracao.usuario SET senha = '$2a$10$yeFHO/NHNKs7JQ7pfX8EUuntyzCf/fgkZKa4vDJJL2ipBxJcRWSO6' WHERE email = 'admin@pti.org.br';

INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (50003, now(), NULL, 'grupos-acesso/delete', 'Remover', 5000);

UPDATE configuracao.permissao SET nome = 'Usuários' WHERE id = 4000;
