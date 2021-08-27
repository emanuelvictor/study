CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA pg_catalog;

--
-- -- TOC entry 2174 (class 0 OID 0)
-- -- Dependencies: 1
-- -- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner:
-- --
--
-- COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


CREATE OR REPLACE FUNCTION filter(needles text, VARIADIC haystacks text[])
    RETURNS boolean AS
$$
SELECT needles IS NULL OR trim(needles) = '' OR EXISTS(
        SELECT DISTINCT 1
        FROM unnest(haystacks) haystack,
             unnest(string_to_array(needles, ',')) needle
        WHERE unaccent(haystack) ILIKE '%' || unaccent(needle) || '%');
$$ LANGUAGE SQL;


create table public.editora
(
    id      bigserial    not null,
    created timestamp    not null,
    updated timestamp,
    nome    varchar(100) not null,
    primary key (id)
);
create table public.editora_audited
(
    id            int8 not null,
    revision      int8 not null,
    revision_type int2,
    nome          varchar(100),
    primary key (id, revision)
);
create table public.livro
(
    id         bigserial    not null,
    created    timestamp    not null,
    updated    timestamp,
    nome       varchar(100) not null,
    editora_id int8         not null,
    primary key (id)
);
create table public.livro_audited
(
    id            int8 not null,
    revision      int8 not null,
    revision_type int2,
    nome          varchar(100),
    editora_id    int8,
    primary key (id, revision)
);
create table public.revision
(
    id        bigserial not null,
    timestamp int8      not null,
    user_id   int8,
    primary key (id)
);
alter table public.editora
    drop constraint if exists UK_editora_nome;
alter table public.editora
    add constraint UK_editora_nome unique (nome);
alter table public.livro
    drop constraint if exists UK_livro_nome;
alter table public.livro
    add constraint UK_livro_nome unique (nome);
alter table public.editora_audited
    add constraint FK_editora_audited_revision foreign key (revision) references public.revision;
alter table public.livro
    add constraint FK_livro_editora_id foreign key (editora_id) references public.editora;
alter table public.livro_audited
    add constraint FK_livro_audited_revision foreign key (revision) references public.revision;
