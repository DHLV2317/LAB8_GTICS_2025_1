CREATE DATABASE IF NOT EXISTS blogdb;

USE blogdb;

CREATE TABLE blog_post (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    contenido LONGTEXT NOT NULL,
    autor VARCHAR(100),
    fecha_publicacion DATETIME,
    categoria VARCHAR(100),

    PRIMARY KEY (id)
);
