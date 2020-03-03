drop table patrimonio.modelo cascade;
drop table patrimonio_audit.modelo_audit cascade;

drop table patrimonio.marca cascade;
drop table patrimonio_audit.marca_audit cascade;


alter table patrimonio.patrimonio add column modelo varchar;
alter table patrimonio.patrimonio add column marca varchar;
alter table patrimonio_audit.patrimonio_audit add column modelo varchar;
alter table patrimonio_audit.patrimonio_audit add column marca varchar;