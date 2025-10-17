drop database if exists hospital_SQL;

create database if exists hospital_SQL;

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
descripcion text,
primary key (id_tratamiento)
);

create table citas(
id_cita int,
id_paciente int not null,
fecha date not null,
primary key (id_cita),
foreign key (id_paciente) references pacientes(id_paciente)
);

create table pacientes_tratamientos(
id_paciente int,
id_tratamiento int,
fecha_inicio date not null,
primary key (id_paciente, id_tratamiento),
foreign key (id_paciente) references pacientes(id_paciente),
foreign key (id_tratamiento) references tratamientos(id_tratamiento)
);
