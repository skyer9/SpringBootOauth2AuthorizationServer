
create database db_oauth2;

create table IF NOT EXISTS oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

insert into oauth_client_details(client_id, resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove)
values('foo',null,'{bcrypt}$2a$10$wPb4BM6c/IqweuscNtQqgu0npxBn0i1qKbx3hGwJ26C3Wi5fHonuy','read,write','authorization_code,password,client_credentials,implicit,refresh_token','http://localhost:8080/test/auth','ROLE_USER',36000,50000,null,null);

CREATE TABLE `tbl_user` (
    `user_id` BIGINT(20) NOT NULL,
    `email` varchar(255) NOT NULL,
    `password` VARCHAR(100) NULL DEFAULT NULL,
    `first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(100) NOT NULL,
    `roles` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `UK_tbl_user_email` (`email`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

create table IF NOT EXISTS oauth_client_token (
    token_id VARCHAR(256),
    token BLOB,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name VARCHAR(256),
    client_id VARCHAR(256)
);

create table IF NOT EXISTS oauth_access_token (
    token_id VARCHAR(256),
    token BLOB,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name VARCHAR(256),
    client_id VARCHAR(256),
    authentication BLOB,
    refresh_token VARCHAR(256)
);

create table IF NOT EXISTS oauth_refresh_token (
    token_id VARCHAR(256),
    token BLOB,
    authentication BLOB
);

create table IF NOT EXISTS oauth_code (
    code VARCHAR(256), authentication BLOB
);

create table IF NOT EXISTS oauth_approvals (
    userId VARCHAR(256),
    clientId VARCHAR(256),
    scope VARCHAR(256),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
