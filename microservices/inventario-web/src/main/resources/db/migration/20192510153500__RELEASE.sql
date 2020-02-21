alter table patrimonio.patrimonio add column localizacao_anterior_id int8;
alter table patrimonio_audit.patrimonio_audit add column localizacao_anterior_id int8;
alter table patrimonio.patrimonio add constraint FK_patrimonio_localizacao_anterior_id foreign key (localizacao_anterior_id) references patrimonio.localizacao;