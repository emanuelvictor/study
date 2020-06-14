INSERT INTO permission(id, created_on, authority) VALUES (1, now(), 'root');

INSERT INTO access_group(id, created_on, name) values (1, now(), 'root');

INSERT INTO access_group_permission(id, created_on, access_group_permission_id, permission_id) values (1, now(), 1, 1);

INSERT INTO "user"(id, created_on, username, password, enabled, access_group_id) VALUES (1, now(), 'admin@admin.com', '$2a$12$mTo6JlUbNTNC525MUszZXuU/7hiDZblRdWDesccgdMq80p4DXMiSm', true, 1);

INSERT INTO application(id, created_on, client_id, client_secret, enabled, access_group_id) VALUES (1, now(), 'browser', '$2a$12$ILW56I/ajyGEKU2SlIABremEL73qzqq.BKtg7yHZ6vfVJM1MBGU.q', true, 1);
