INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (6000, now(), 'root/access-manager/applications', 'Aplicativos', 100);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (60000, now(), 'root/access-manager/applications/post', 'Adicionar', 6000);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (60001, now(), 'root/access-manager/applications/put', 'Alterar', 6000);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (60002, now(), 'root/access-manager/applications/get', 'Consultar', 6000);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (600001, now(), 'root/access-manager/applications/put/change-password', 'Alterar senha', 60001);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (600002, now(), 'root/access-manager/applications/put/activate', 'Ativar/Desativar', 60001);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (60003, now(), 'root/access-manager/applications/delete', 'Remover', 6000);
