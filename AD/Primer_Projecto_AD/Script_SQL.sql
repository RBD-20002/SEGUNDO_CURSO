/*SCRIPT CREACION BASE DE DATOS----------------------------------------------*/

drop database if exists hospital_SQL;

create database hospital_SQL;

use hospital_SQL;

create table pacientes(
id_paciente int auto_increment,
nombre varchar(100) not null,
email varchar(100) null,
fecha_nacimiento date not null,
primary key(id_paciente)
);

create table tratamientos(
id_tratamiento int auto_increment,
nombre_tratamiento varchar(100) not null,
descripcion text null,
primary key (id_tratamiento)
);

create table citas(
id_cita int auto_increment,
id_paciente int not null,
fecha date not null,
primary key (id_cita),
foreign key (id_paciente) references pacientes(id_paciente) on delete cascade on update cascade
);

create table pacientes_tratamientos(
id_paciente int,
id_tratamiento int,
fecha_inicio date not null,
primary key (id_paciente, id_tratamiento),
foreign key (id_paciente) references pacientes(id_paciente)
on delete cascade on update cascade,
foreign key (id_tratamiento) references tratamientos(id_tratamiento)
on delete cascade on update cascade
);

/*DATOS DE PRUEBA------------------------------------------------------------*/

/*Pacientes*/
insert into pacientes (nombre, email, fecha_nacimiento) values
('juan perez','jperez@correo.com','1980-01-10'),
('ana lopez','alopez@correo.com','1985-02-15'),
('luis martinez','lmartinez@correo.com','1990-03-20'),
('carmen torres','ctorres@correo.com','1978-04-05'),
('jose fernandez','jfernandez@correo.com','1982-05-12'),
('marta suarez','msuarez@correo.com','1988-06-25'),
('ricardo ortiz','rortiz@correo.com','1975-07-30'),
('alejandro dominguez','adominguez@correo.com','1992-08-18'),
('lucia navarro','lnavarro@correo.com','1983-09-22'),
('raul morales','rmorales@correo.com','1995-10-14'),
('eva serrano','eserrano@correo.com','1987-11-11'),
('adrian blanco','ablanco@correo.com','1981-12-01');


/*Tratamientos*/
insert into tratamientos (nombre_tratamiento, descripcion) values
('tratamiento cardiaco','tratamiento para problemas del corazon'),
('tratamiento neurologico','tratamiento para enfermedades del cerebro'),
('tratamiento pediatrico','tratamiento para ninos y adolescentes'),
('tratamiento traumatologico','tratamiento de lesiones musculoesqueleticas'),
('tratamiento dermatologico','tratamiento de enfermedades de la piel'),
('tratamiento oncologico','tratamiento contra el cancer'),
('tratamiento urologico','tratamiento de aparato urinario'),
('tratamiento ginecologico','tratamiento del sistema reproductor femenino'),
('tratamiento oftalmologico','tratamiento de enfermedades de los ojos'),
('tratamiento otorrino','tratamiento de oidos nariz y garganta'),
('tratamiento reumatologico','tratamiento de articulaciones y huesos'),
('tratamiento endocrino','tratamiento de glandulas hormonales');

/*Citas*/
insert into citas (id_paciente, fecha) values
(1,'2025-11-01'),(2,'2025-11-02'),(3,'2025-11-03'),(4,'2025-11-04'),(5,'2025-11-05'),
(6,'2025-11-06'),(7,'2025-11-07'),(8,'2025-11-08'),(9,'2025-11-09'),(10,'2025-11-10'),
(11,'2025-11-11'),(12,'2025-11-12');

/*Pacientes-Tratamientos*/
insert into pacientes_tratamientos (id_paciente, id_tratamiento, fecha_inicio) values
(1,1,'2025-01-10'),(2,2,'2025-01-11'),(3,3,'2025-01-12'),(4,4,'2025-01-13'),(5,5,'2025-01-14'),
(6,6,'2025-01-15'),(7,7,'2025-01-16'),(8,8,'2025-01-17'),(9,9,'2025-01-18'),(10,10,'2025-01-19'),
(11,11,'2025-01-20'),(12,12,'2025-01-21');