CREATE DATABASE hospital;

CREATE SCHEMA objetos;

CREATE TYPE objetos.persona AS (
nombre varchar(20),
edad Integer
);

CREATE TYPE objetos.paciente AS (
numero_historia varchar(40),
grupo_sanguineo varchar(10)
);

CREATE TYPE objetos.examenMedico AS (
nombre_examen varchar(20),
fecha_realizacion date,
resultados varchar(100)
);

CREATE TYPE objetos.medico AS (
codigo_medico varchar(20),
especialidad varchar(40)
);

CREATE TABLE objetos.medicos(
medico_id serial,
datos_personales objetos.persona,
medico_info objetos.medico,
PRIMARY KEY (medico_id)
);

CREATE TABLE objetos.pacientes(
paciente_id serial,
datos_personales objetos.persona,
paciente_info objetos.paciente,
PRIMARY KEY (paciente_id)
);

CREATE TABLE objetos.citasMedicas(
cita_id serial,
fecha_hora date,
paciente_id Integer,
medico_id Integer,
PRIMARY KEY (cita_id),
FOREIGN KEY (paciente_id) REFERENCES objetos.pacientes(paciente_id),
FOREIGN KEY (medico_id) REFERENCES objetos.medicos(medico_id)
);

CREATE TABLE objetos.examenesMedicos(
examen_id serial,
paciente_id Integer,
examen_info objetos.examenMedico,
PRIMARY KEY (examen_id),
FOREIGN KEY (paciente_id) REFERENCES objetos.pacientes(paciente_id)
);

INSERT INTO objetos.medicos (datos_personales, medico_info)
VALUES
(ROW('Juan Pérez', 45), ROW('MED001', 'Cardiología')),
(ROW('Ana Gómez', 38), ROW('MED002', 'Pediatría'));

INSERT INTO objetos.pacientes (datos_personales, paciente_info)
VALUES
(ROW('Carlos Ruiz', 30), ROW('HIST001', 'O+')),
(ROW('Lucía Fernández', 25), ROW('HIST002', 'A-'));

INSERT INTO objetos.citasMedicas (fecha_hora, paciente_id, medico_id)
VALUES
('2025-11-26 10:00', 1, 1),
('2025-11-26 11:30', 2, 2);

INSERT INTO objetos.examenesMedicos (examen_info, paciente_id)
VALUES
(ROW('Hemograma', '2025-11-20', 'Normal'), 1),
(ROW('Radiografía', '2025-11-21', 'Fractura leve'), 2);
