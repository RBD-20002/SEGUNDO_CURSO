/*SCRIPT CREACION BASE DE DATOS----------------------------------------------*/

create database hospital_postgresql;

\c hospital_postgresql;

create schema hospital;

create type hospital.contacto_medico as (
nombre_contacto varchar(40),
nif varchar(9),
num_movil int,
email varchar(100)
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
foreign key (id_sala) references hospital.salas(id_sala) on delete cascade on update cascade,
foreign key (id_tratamiento) references hospital.tratamientos(id_tratamiento) on delete cascade on update cascade
);

/*DATOS DE PRUEBA------------------------------------------------------------*/
/*Especialidades*/
insert into hospital.especialidades (nombre_especialidad) values
('cardiologia'),('neurologia'),('pediatria'),('traumatologia'),
('dermatologia'),('oncologia'),('urologia'),('ginecologia'),
('oftalmologia'),('otorrinolaringologia'),('reumatologia'),('endocrinologia');

/*Médicos*/
insert into hospital.medicos (nombre_medico, contacto) values
('juan perez', row('juan perez','12345678a',600111111,'jperez@correo.com')),
('ana lopez', row('ana lopez','12345678b',600111112,'alopez@correo.com')),
('luis martinez', row('luis martinez','12345678c',600111113,'lmartinez@correo.com')),
('carmen torres', row('carmen torres','12345678d',600111114,'ctorres@correo.com')),
('jose fernandez', row('jose fernandez','12345678e',600111115,'jfernandez@correo.com')),
('marta suarez', row('marta suarez','12345678f',600111116,'msuarez@correo.com')),
('ricardo ortiz', row('ricardo ortiz','12345678g',600111117,'rortiz@correo.com')),
('alejandro dominguez', row('alejandro dominguez','12345678h',600111118,'adominguez@correo.com')),
('lucia navarro', row('lucia navarro','12345678i',600111119,'lnavarro@correo.com')),
('raul morales', row('raul morales','12345678j',600111120,'rmorales@correo.com')),
('eva serrano', row('eva serrano','12345678k',600111121,'eserrano@correo.com')),
('adrian blanco', row('adrian blanco','12345678l',600111122,'ablanco@correo.com'));

/*Salas*/
insert into hospital.salas (nombre_sala, ubicacion) values
('sala 1', 'planta baja'),
('sala 2', 'planta baja'),
('sala 3', 'planta 1'),
('sala 4', 'planta 1'),
('sala 5', 'planta 2'),
('sala 6', 'planta 2'),
('sala 7', 'planta 3'),
('sala 8', 'planta 3'),
('sala 9', 'planta 4'),
('sala 10', 'planta 4'),
('sala 11', 'planta 5'),
('sala 12', 'planta 5');

/*Tratamientos*/
/*Tratamientos*/
insert into hospital.tratamientos (id_tratamiento, id_medico, id_especialidad) values
(1,1,1),(2,2,2),(3,3,3),(4,4,4),(5,5,5),(6,6,6),(7,7,7),(8,8,8),(9,9,9),(10,10,10),
(11,11,11),(12,12,12);


/*Salas-Tratamientos*/
insert into hospital.salas_tratamientos (id_sala, id_tratamiento) values
(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),
(11,11),(12,12);