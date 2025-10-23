/*SCRIPT CREACION BASE DE DATOS----------------------------------------------*/

create database hospital_postgresql;

\c hospital_postgresql;

create schema hospital;

create type hospital.contacto_medico as (
nombre_contacto varchar(40),
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
foreign key (id_medico) references hospital.medicos(id_medico) on delete cascade on update cascade,
foreign key (id_especialidad) references hospital.especialidades(id_especialidad) on delete cascade on update cascade
);

create table if not exists hospital.salas_tratamientos(
id_sala Integer,
id_tratamiento Integer,
primary key (id_sala,id_tratamiento),
foreign key (id_sala) references hospital.salas(id_sala),
foreign key (id_tratamiento) references hospital.tratamientos(id_tratamiento)
);

/*DATOS DE PRUEBA------------------------------------------------------------*/

/*Especialidades*/
insert into hospital.especialidades(nombre_especialidad) values 
('Cardiología'),('Nutrición'),('Dermatología'),('Neurología'),('Fisioterapia');

/*Médicos*/
insert into hospital.medicos(nombre_medico, contacto) values
('André', row('André López','11111111A',604219453,'andre@gmail.com')),
('Beatriz', row('Beatriz Soto','22222222B',600123456,'beatriz@gmail.com')),
('Carlos', row('Carlos Méndez','33333333C',601987654,'carlos@gmail.com')),
('Diana', row('Diana Ruiz','44444444D',602555666,'diana@gmail.com')),
('Elena', row('Elena Vidal','55555555E',603444777,'elena@gmail.com'));

/*Salas*/
insert into hospital.salas(nombre_sala, ubicacion) values
('Pediatría','Cunqueiro'),('Medicina General','Cunqueiro'),('Urgencias','Cunqueiro'),
('Rehabilitación','Cunqueiro'),('Fisioterapia','Cunqueiro');

/*Tratamientos*/
insert into hospital.tratamientos(id_tratamiento, id_medico, id_especialidad) values
(1, 1, 1),(2, 2, 2),(3, 3, 3),(4, 4, 4),(5, 5, 5);

/*Salas-Tratamientos*/
insert into hospital.salas_tratamientos(id_sala, id_tratamiento) values
(1,1),(2,2),(3,3),(4,4),(5,5);