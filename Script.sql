 -- drop database if exists dbventas;
create database dbventas;
use dbventas;


CREATE TABLE permisos (
    id_permiso BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE roles (
    id_roles BIGINT AUTO_INCREMENT PRIMARY KEY,
    name ENUM('ADMIN', 'DEVELOPER', 'INVITED', 'USER') NOT NULL
);


CREATE TABLE roles_permisos (
    role_id BIGINT NOT NULL,
    permiso_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permiso_id),
    FOREIGN KEY (role_id) REFERENCES roles(id_roles) ON DELETE CASCADE,
    FOREIGN KEY (permiso_id) REFERENCES permisos(id_permiso) ON DELETE CASCADE
);


CREATE TABLE user_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_no_expired BIT(1) NOT NULL,
    account_no_locked BIT(1) NOT NULL,
    credential_no_expired BIT(1) NOT NULL,
    dni VARCHAR(8) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    is_enabled BIT(1) NOT NULL,
    nombre_cliente VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user_entity(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id_roles) ON DELETE CASCADE
);
