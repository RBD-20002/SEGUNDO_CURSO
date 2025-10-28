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
insert into pacientes(nombre, email, fecha_nacimiento) values
('ricardo', 'ricardo@gmail.com', '2002-03-03'),('juan', 'juan@gmail.com', '1963-10-15'),
('edgardo', 'edgardo@gmail.com', '2002-04-04'),('laura', 'laura@gmail.com', '1990-07-22'),
('patricia', 'patricia@gmail.com', '1988-12-01');

/*Tratamientos*/
insert into tratamientos(id_tratamiento, nombre_tratamiento, descripcion) values
(1, 'cirugia mayor', 'intervencion compleja con anestesia general'),
(2, 'quimioterapia', 'tratamiento del cancer'),
(3, 'laser dermatologico', 'tratamiento de piel con laser'),
(4, 'rehabilitación neurologica', 'terapia para recuperación motora'),
(5, 'fisioterapia deportiva', 'tratamiento de lesiones musculares');

/*Citas*/
insert into citas(id_paciente, fecha) values
(1, '2025-04-15'),(2, '2025-12-10'),(3, '2025-06-20'),(4, '2025-08-05'),(5, '2025-09-25');

/*Pacientes-Tratamientos*/
insert into pacientes_tratamientos(id_paciente, id_tratamiento, fecha_inicio) values
(1,1,'2025-05-06'),(2,2,'2025-10-06'),(3,3,'2025-01-15'),(4,4,'2025-09-20'),(5,5,'2025-07-12');