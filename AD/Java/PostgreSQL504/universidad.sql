CREATE DATABASE universidad;

CREATE SCHEMA objetos;

CREATE TYPE objetos.persona AS (
nombre varchar(50),
edad Integer
);

CREATE TYPE objetos.profesor AS (
cedula varchar(9),
departamento varchar(20)
);

CREATE TYPE objetos.estudiante AS (
matricula varchar(20),
carrera varchar(20)
);

CREATE TABLE objetos.estudiantes(
estudiante_id serial,
datos_personales objetos.persona,
estudiante_info objetos.estudiante,
PRIMARY KEY (estudiante_id)
);

CREATE TABLE objetos.cursos(
curso_id serial,
nombre varchar(30),
PRIMARY KEY (curso_id)
);

CREATE TABLE objetos.inscripciones(
inscripcion_id serial,
estudiante_id Integer,
curso_id Integer,
PRIMARY KEY (inscripcion_id),
FOREIGN KEY (estudiante_id) REFERENCES objetos.estudiantes(estudiante_id),
FOREIGN KEY (curso_id) REFERENCES objetos.cursos(curso_id)
);

CREATE TABLE objetos.profesores(
profesor_id serial,
datos_personales objetos.persona,
profesor_info objetos.profesor,
curso_id Integer,
PRIMARY KEY (profesor_id),
FOREIGN KEY (curso_id) REFERENCES objetos.cursos(curso_id)
);

/*INSERT DE DATOS*/

INSERT INTO objetos.Estudiantes (datos_personales, estudiante_info)
VALUES (ROW('Juan Pérez', 20), ROW('2021001', 'Ingeniería Informática'));

INSERT INTO objetos.Estudiantes (datos_personales, estudiante_info)
VALUES (ROW('María Gómez', 22), ROW('2021002', 'Psicología'));

INSERT INTO objetos.Profesores (datos_personales, profesor_info)
VALUES (ROW('Carlos Rodríguez', 40), ROW('PR001', 'Matemáticas'));

INSERT INTO objetos.Profesores (datos_personales, profesor_info)
VALUES (ROW('Ana Martínez', 35), ROW('PR002', 'Historia'));

INSERT INTO objetos.Cursos (nombre)
VALUES ('Álgebra Lineal');

INSERT INTO objetos.Cursos (nombre)
VALUES ('Historia del Arte');

INSERT INTO objetos.inscripciones (estudiante_id, curso_id)
VALUES (1, 1);

INSERT INTO objetos.inscripciones (estudiante_id, curso_id)
VALUES (2, 2);

INSERT INTO objetos.estudiantes (datos_personales, estudiante_info)
VALUES (ROW('Laura Torres', 21), ROW('2021003', 'Biología'));

INSERT INTO objetos.estudiantes (datos_personales, estudiante_info)
VALUES (ROW('Eduardo López', 23), ROW('2021004', 'Ingeniería Civil'));

INSERT INTO objetos.cursos (nombre)
VALUES ('Programación en C');

INSERT INTO objetos.cursos (nombre)
VALUES ('Historia Antigua');

INSERT INTO objetos.profesores (datos_personales, profesor_info, curso_id)
VALUES (ROW('Isabel Fernández', 37), ROW('PR003', 'Informática'), 3);

INSERT INTO objetos.profesores (datos_personales, profesor_info, curso_id)
VALUES (ROW('Luis Sánchez', 45), ROW('PR004', 'Historia del Arte'), 4);

INSERT INTO objetos.inscripciones (estudiante_id, curso_id)
VALUES (3, 3);

INSERT INTO objetos.inscripciones (estudiante_id, curso_id)
VALUES (4, 4);