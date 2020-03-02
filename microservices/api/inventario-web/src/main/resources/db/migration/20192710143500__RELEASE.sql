alter table patrimonio.patrimonio alter column codigo_base drop not null;
alter table patrimonio_audit.patrimonio_audit alter column codigo_base drop not null;

alter table patrimonio.patrimonio add column sobra_fisica boolean not null default false;
alter table patrimonio_audit.patrimonio_audit add column sobra_fisica boolean;

alter table patrimonio_audit.centro_custo_inventario_audit alter column status drop not null;