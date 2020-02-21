INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (7000, now(), NULL, 'inventarios', 'Inventário', NULL);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (70000, now(), NULL, 'inventarios/post', 'Adicionar', 7000);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (70001, now(), NULL, 'inventarios/put', 'Alterar', 7000);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (70002, now(), NULL, 'inventarios/get', 'Consultar', 7000);
INSERT INTO configuracao.permissao (id, created, updated, identificador, nome, permissao_superior_id) VALUES (70003, now(), NULL, 'inventarios/delete', 'Remover', 7000);
