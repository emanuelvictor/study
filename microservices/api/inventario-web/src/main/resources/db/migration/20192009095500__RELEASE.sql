alter table patrimonio.patrimonio add column centro_custo_anterior_id int8;
alter table patrimonio_audit.patrimonio_audit add column centro_custo_anterior_id int8;
alter table patrimonio.patrimonio add constraint FK_patrimonio_centro_custo_anterior_id foreign key (centro_custo_anterior_id) references pessoal.centro_custo;