INSERT INTO permission(id, created_on, authority, name) VALUES (1, now(), 'root', 'Root');

INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (4000, now(), 'users', 'Usuários', 1);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (40000, now(), 'users/post', 'Adicionar', 4000);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (40001, now(), 'users/put', 'Alterar', 4000);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (40002, now(), 'users/get', 'Consultar', 4000);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (400001, now(), 'users/put/change-password', 'Alterar senha', 40001);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (400002, now(), 'users/put/activate', 'Ativar/Desativar', 40001);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (40003, now(), 'users/delete', 'Remover', 4000);

INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (5000, now(), 'access-groups', 'Grupos de Acesso', 1);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (50000, now(), 'access-groups/post', 'Adicionar', 5000);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (50001, now(), 'access-groups/put', 'Alterar', 5000);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (50002, now(), 'access-groups/get', 'Consultar', 5000);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (500001, now(), 'access-groups/put/activate', 'Ativar', 50001);
INSERT INTO permission (id, created_on, authority, name, upper_permission_id) VALUES (50003, now(), 'access-groups/delete', 'Remover', 5000);

INSERT INTO access_group(id, created_on, name) values (1, now(), 'Root');

INSERT INTO access_group_permission(id, created_on, access_group_permission_id, permission_id) values (1, now(), 1, 1);

INSERT INTO "user"(id, created_on, name, username, password, enabled, locked, access_group_id) VALUES (1, now(),'Admin',  'admin@admin.com', '$2a$12$mTo6JlUbNTNC525MUszZXuU/7hiDZblRdWDesccgdMq80p4DXMiSm', true, false, 1);

INSERT INTO application(id, created_on, client_id, client_secret, access_group_id, refresh_token_validity_seconds, access_token_validity_seconds, scoped, secret_required, authorized_grant_types, resource_ids, registered_redirect_uri) VALUES (1, now(), 'browser', '$2a$12$ILW56I/ajyGEKU2SlIABremEL73qzqq.BKtg7yHZ6vfVJM1MBGU.q', 1,  999999999, 3600, false, false, 'authorization_code;token;refresh_token;client_credentials;password', null, 'http://localhost:8080/test;http://localhost:4200;http://localhost:8084/api/test;http://localhost:8084/api/logged;http://localhost:8080/account-manager/api/logged;http://localhost:8080/account-manager/api/test');
