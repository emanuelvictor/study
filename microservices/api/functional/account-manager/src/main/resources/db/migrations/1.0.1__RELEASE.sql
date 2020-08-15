INSERT INTO permission(id, created_on, authority) VALUES (1, now(), 'root');

INSERT INTO access_group(id, created_on, name) values (1, now(), 'root');

INSERT INTO access_group_permission(id, created_on, access_group_permission_id, permission_id) values (1, now(), 1, 1);

INSERT INTO "user"(id, created_on, name, username, password, enabled, locked, access_group_id) VALUES (1, now(),'Admin',  'admin@admin.com', '$2a$12$mTo6JlUbNTNC525MUszZXuU/7hiDZblRdWDesccgdMq80p4DXMiSm', true, false, 1);

INSERT INTO application(id, created_on, client_id, client_secret, access_group_id, refresh_token_validity_seconds, access_token_validity_seconds, scoped, secret_required, authorized_grant_types, resource_ids, registered_redirect_uri) VALUES (1, now(), 'browser', '$2a$12$ILW56I/ajyGEKU2SlIABremEL73qzqq.BKtg7yHZ6vfVJM1MBGU.q', 1,  999999999, 60000, true, true, 'authorization_code;token;refresh_token;client_credentials', null, 'http://localhost:8080/test;http://localhost:4200;http://localhost:8084/api/test;http://localhost:8084/api/logged;http://localhost:8080/account-manager/api/logged;http://localhost:8080/account-manager/api/test');
