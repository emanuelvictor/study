DROP TABLE IF EXISTS piada CASCADE;

CREATE TABLE IF NOT EXISTS piada
(
  id       bigint NOT NULL,
  conteudo character varying(500),
  CONSTRAINT piada_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE piada_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

ALTER SEQUENCE piada_id_seq OWNED BY piada.id;
ALTER TABLE ONLY piada ALTER COLUMN id SET DEFAULT nextval('piada_id_seq'::regclass);

INSERT INTO piada (id, conteudo)
VALUES (1,'O que é feito para andar e não anda? A rua.'),
       (2,'O que dá muitas voltas e não sai do lugar? O relógio.'),
       (3,'O que tem cabeça e tem dente, não é bicho e nem é gente? O alho.'),
       (4,'O que não se come, mas é bom para se comer? O talher.'),
       (5,'O que uma impressora disse para a outra? A primeira impressão é a que fica.'),
       (6,'O que quanto mais rugas tem mais novo é? O pneu.'),
       (7,'O que 4 disse para o 40? Passa a bola.'),
       (8,'O que anda com os pés na cabeça? O piolho.'),
       (9,'O que quanto mais se tira mais se aumenta? O buraco.'),
       (10,'O que nasce grande e morre pequeno? O lápis.'),
       (11,'O que está sempre no meio da rua e de pernas para o ar? A letra "u".'),
       (12,'O que o cavalo foi fazer no orelhão? Passar um trote.'),
       (14,'O que nasce a socos e morre a facadas? O pão.');
