alter table patrimonio.centro_custo_inventario add column status int4 not null default 1;
alter table patrimonio_audit.centro_custo_inventario_audit add column status int4 not null default 1;