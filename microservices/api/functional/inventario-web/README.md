# FPTI - Inventário

- Aplicação destinada ao gerenciamento de inventários da Fundação Parque Tecnológico Itaipu

# Função de filtro customizado para aplicação

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA pg_catalog;


CREATE OR REPLACE FUNCTION filter(needles text, VARIADIC haystacks text [])
RETURNS boolean AS $$
SELECT needles IS NULL OR trim(needles) = '' OR EXISTS(
    SELECT DISTINCT 1
    FROM unnest(haystacks) haystack,
    unnest(string_to_array(needles, ',')) needle
    WHERE unaccent(haystack) ILIKE '%' || unaccent(needle) || '%');
$$ LANGUAGE SQL;
