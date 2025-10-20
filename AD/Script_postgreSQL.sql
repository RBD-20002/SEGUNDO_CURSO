/*SCRIPT CREACION BASE DE DATOS----------------------------------------------*/

create database hospital_postgreSQL;

create schema hospital;

create type hospital.contacto_medico as (
nif varchar(9),
num_movil int,
email varchar(20)
);

create table if not exists hospital.especialidades(
id_especialidad serial,
nombre_especialidad varchar(100),
primary key (id_especialidad)
);

create table if not exists hospital.medicos(
id_medico serial,
nombre_medico varchar(100) not null,
contacto hospital.contacto_medico not null,
primary key (id_medico)
);

create table if not exists hospital.salas(
id_sala serial,
nombre_sala varchar(100) not null,
ubicacion varchar(100) not null,
primary key (id_sala)
);

create table if not exists hospital.tratamientos(
id_tratamiento Integer,
id_medico Integer not null,
id_especialidad Integer not null,
primary key (id_tratamiento),
foreign key (id_medico) references hospital.medicos(id_medico),
foreign key (id_especialidad) references hospital.especialidades(id_especialidad)
);

create table if not exists hospital.salas_tratamientos(
id_sala Integer,
id_tratamiento Integer,
primary key (id_sala,id_tratamiento),
foreign key (id_sala) references hospital.salas(id_sala),
foreign key (id_tratamiento) references hospital.tratamientos(id_tratamiento)
);

/*DATOS DE PRUEBA------------------------------------------------------------*/

insert into hospital.especialidades(nombre_especialidad) values 
('Cardiologia'),('Nutricion'),('Dermatologia'),('Neurologia');

insert into hospital.medicos(nombre_medico, contacto) values ('Andre', row('11111111A','604219453','andre@gmail.com')),('Beatriz', row('22222222B','600123456','beatriz@gmail.com')),('Carlos', row('33333333C','601987654','carlos@gmail.com')),('Diana', row('44444444D','602555666','diana@gmail.com'));

insert into hospital.salas(nombre_sala, ubicacion) values
('Pediatria','Cunqueiro'),('Medicina General','Cunqueiro'),('Urgencias','Cunqueiro'),('Rehabilitacion','Cunqueiro');

insert into hospital.tratamientos(id_tratamiento, id_medico, id_especialidad) values (1, 1, 1),(2, 2, 2),(3, 3, 3),(4, 4, 4);

insert into hospital.salas_tratamientos(id_sala, id_tratamiento) values
(1,1),(2,2),(3,3),(4,4);

/*CONSULTAS UTILIZADAS EN CODIGO JAVA----------------------------------------*/

/*METODO: crear especialidad*/
INSERT INTO hospital.especialidades(nombre_especialidad) VALUES (?)

/*METODO: crear medico*/
INSERT INTO hospital.medicos(nombre_medico, contacto) VALUES (?,ROW(?,?,?))

/*METODO: eliminar medico*/
DELETE FROM medicos WHERE id_medico = ?

/*METODO: obtener nombre sala con su cantidad de tratamientos*/
SELECT salas.nombre_sala, COUNT(salas_tratamientos.id_tratamiento) as "Tratamientos por sala" FROM salas JOIN salas_tratamientos ON salas.id_sala = salas_tratamientos.id_sala GROUP BY salas.id_sala, salas.nombre_sala