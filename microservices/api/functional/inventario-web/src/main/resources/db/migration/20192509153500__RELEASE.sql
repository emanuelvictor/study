ALTER TABLE patrimonio.patrimonio RENAME COLUMN nome TO item;
alter table patrimonio_audit.patrimonio_audit RENAME COLUMN nome TO item;

alter table patrimonio.patrimonio drop constraint if exists UK3fvhxukh57sp62iqmd5guyot4;
alter table patrimonio.patrimonio add constraint UK3fvhxukh57sp62iqmd5guyot4 unique (numero, item, codigo_base);