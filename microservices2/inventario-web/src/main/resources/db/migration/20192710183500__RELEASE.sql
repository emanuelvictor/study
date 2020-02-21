alter table patrimonio.localizacao drop constraint if exists unique_codigo_localizacao;
alter table patrimonio.localizacao add constraint unique_codigo_localizacao unique (codigo);