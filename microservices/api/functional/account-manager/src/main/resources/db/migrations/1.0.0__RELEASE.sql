create table access_group_audit (id int8 not null, revision int8 not null, revision_type int2, name varchar(50), primary key (id, revision));
create table access_group_permission_audit (id int8 not null, revision int8 not null, revision_type int2, access_group_permission_id int8, permission_id int8, primary key (id, revision));
create table access_group (id int8 generated by default as identity, created_on timestamp not null, updated_on timestamp, name varchar(50) not null, primary key (id));
create table access_group_permission (id int8 generated by default as identity, created_on timestamp not null, updated_on timestamp, access_group_permission_id int8 not null, permission_id int8 not null, primary key (id));
create table aplicacao (id int8 generated by default as identity, created_on timestamp not null, updated_on timestamp, client_id varchar(45) not null, client_secret varchar(90) not null, ativo boolean not null, access_group_id int8, primary key (id));
create table aplicacao_audit (id int8 not null, revision int8 not null, revision_type int2, client_id varchar(45), client_secret varchar(90), ativo boolean, access_group_id int8, primary key (id, revision));
create table permission (id int8 generated by default as identity, created_on timestamp not null, updated_on timestamp, authority varchar(255) not null, upper_permission_id int8, primary key (id));
create table permission_audit (id int8 not null, revision int8 not null, revision_type int2, authority varchar(255), upper_permission_id int8, primary key (id, revision));
create table revisions (id int8 generated by default as identity, created_by varchar(45) not null, created_on timestamp not null, primary key (id));
create table "user" (id int8 generated by default as identity, created_on timestamp not null, updated_on timestamp, enabled boolean not null, password varchar(100) not null, username varchar(150) not null, access_group_id int8 not null, primary key (id));
create table user_audit (id int8 not null, revision int8 not null, revision_type int2, enabled boolean, password varchar(100), username varchar(150), access_group_id int8, primary key (id, revision));
alter table if exists access_group drop constraint if exists UK_2rpgfvxump55l2ycp3bjhgi8j;


alter table if exists access_group add constraint UK_2rpgfvxump55l2ycp3bjhgi8j unique (name);
alter table if exists access_group_permission drop constraint if exists uk_access_group_permission;
alter table if exists access_group_permission add constraint uk_access_group_permission unique (access_group_permission_id, permission_id);
alter table if exists permission drop constraint if exists UK_atogak43scua5hr2pnnaevywq;
alter table if exists permission add constraint UK_atogak43scua5hr2pnnaevywq unique (authority);
alter table if exists "user" drop constraint if exists UK_sb8bbouer5wak8vyiiy4pf2bx;
alter table if exists "user" add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table if exists access_group_audit add constraint fk_access_group_audit_revisions foreign key (revision) references revisions;
alter table if exists access_group_permission_audit add constraint fk_access_group_permission_audit_revisions foreign key (revision) references revisions;
alter table if exists access_group_permission add constraint fk_access_group_permission_access_group foreign key (access_group_permission_id) references access_group;
alter table if exists access_group_permission add constraint fk_access_group_permission_permission foreign key (permission_id) references permission;
alter table if exists aplicacao add constraint fk_aplicacao_access_group foreign key (access_group_id) references access_group;
alter table if exists aplicacao_audit add constraint fk_aplicacao_audit_revisions foreign key (revision) references revisions;
alter table if exists permission add constraint fk_permission_permission foreign key (upper_permission_id) references permission;
alter table if exists permission_audit add constraint fk_permission_audit_revisions foreign key (revision) references revisions;
alter table if exists "user" add constraint fk_user_access_group foreign key (access_group_id) references access_group;
alter table if exists user_audit add constraint fk_user_audit_revisions foreign key (revision) references revisions;