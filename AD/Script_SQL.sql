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
foreign key (id_paciente) references pacientes(id_paciente)
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
insert into pacientes(nombre, email, fecha_nacimiento) values
('Ricardo', 'ricardo@gmail.com', '2002-03-03'),('Juan', 'juan@gmail.com', '1963-10-15'),
('Edgardo', 'edgardo@gmail.com', '2002-04-04'),('Laura', 'laura@gmail.com', '1990-07-22'),
('Patricia', 'patricia@gmail.com', '1988-12-01');

/*Tratamientos*/
insert into tratamientos(id_tratamiento, nombre_tratamiento, descripcion) values
(1, 'Cirugía Mayor', 'Intervención compleja con anestesia general'),(2, 'Quimioterapia', 'Tratamiento del cáncer'),
(3, 'Láser Dermatológico', 'Tratamiento de piel con láser'),(4, 'Rehabilitación Neurológica', 'Terapia para recuperación motora'),
(5, 'Fisioterapia Deportiva', 'Tratamiento de lesiones musculares');

/*Citas*/
insert into citas(id_paciente, fecha) values
(1, '2025-04-15'),(2, '2025-12-10'),(3, '2025-06-20'),(4, '2025-08-05'),(5, '2025-09-25');

/*Pacientes-Tratamientos*/
insert into pacientes_tratamientos(id_paciente, id_tratamiento, fecha_inicio) values
(1,1,'2025-05-06'),(2,2,'2025-10-06'),(3,3,'2025-01-15'),(4,4,'2025-09-20'),(5,5,'2025-07-12');