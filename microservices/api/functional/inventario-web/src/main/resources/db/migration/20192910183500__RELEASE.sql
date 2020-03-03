alter table patrimonio.centro_custo_inventario add column if not exists data_termino_extendida date;
alter table patrimonio_audit.centro_custo_inventario_audit add column if not exists  data_termino_extendida date;
