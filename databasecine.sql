drop database if exists cine;
create database cine;

use cine;

DROP TABLE IF EXISTS `peliculas`;

CREATE TABLE `peliculas` (
  `id` int  NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL ,
  `precio` double DEFAULT NULL,
   `disponible` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `salas`;

CREATE TABLE `salas` (
  `id` int  NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL ,
  PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `proyecciones`;

CREATE TABLE `proyecciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sala_id` int  NOT NULL ,
  `pelicula_id` int,
  `fecha` varchar(15) NOT NULL,
  `hora` varchar(50) NOT NULL ,
   CONSTRAINT `sala_fk` FOREIGN KEY (`sala_id`) REFERENCES `salas` (`id`),
   CONSTRAINT `pelicula_fk` FOREIGN KEY (`pelicula_id`) REFERENCES `peliculas` (`id`),
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id` varchar(50)  NOT NULL,
  `nombre` varchar(50) NOT NULL ,
   `contrasenna` varchar(50) NOT NULL ,
   `rol` int NOT NULL ,
  PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `tiquetes`;

CREATE TABLE `tiquetes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_proyeccion` int DEFAULT NULL,
  `id_cliente` varchar(9) DEFAULT NULL,
  `tarjeta` varchar(20) DEFAULT NULL,
  `asiento` varchar(9) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `proyec_fk` FOREIGN KEY (`id_proyeccion`) REFERENCES `proyecciones` (`id`),
  CONSTRAINT `profe_fk` FOREIGN KEY (`id_cliente`) REFERENCES `usuarios` (`id`)
);



insert into usuarios (id,nombre,contrasenna,rol) values ('1111','Admin Pueba','1111','1');
insert into usuarios (id,nombre,contrasenna,rol) values ('2222','cliente Pueba','2222','2');
insert into peliculas (id,nombre,precio,disponible) values(1,'Peli Prueba',100,0);
insert into salas (id,nombre) values(1,'Sala A');
insert into proyecciones (sala_id,fecha,hora,pelicula_id) values(1,'1999-03-01','11:00',1);
insert into tiquetes (id,id_proyeccion,id_cliente,tarjeta,asiento) values(1,1,'2222','9999999','A1');