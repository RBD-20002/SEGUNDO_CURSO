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

insert into pacientes(nombre,email,fecha_nacimiento) values
('Ricardo', 'ricardo@gmail.com', '2002-03-03'),('Juan', 'juan@gmail.com', '1963-10-15'),('Edgardo', 'edgardo@gmail.com', '2002-04-04'),('Laura', 'laura@gmail.com', '1990-07-22');

insert into tratamientos(id_tratamiento, nombre_tratamiento, descripcion) values
(1, 'Cirugia Mayor', 'Intervencion compleja con anestesia general'),(2, 'Quimioterapia', 'Tratamiento del cancer'),(3, 'Laser Dermatologico', 'Tratamiento de piel con laser'),(4, 'Rehabilitacion Neurologica', 'Ejercicios y terapia para recuperar funciones');

insert into citas(id_paciente, fecha) values (1, '2025-04-15'),(2, '2025-12-10'),(3, '2025-06-20'),(4, '2025-08-05');

insert into pacientes_tratamientos(id_paciente, id_tratamiento, fecha_inicio) values
(1,1,'2026-05-06'),(2,2,'2025-10-06'),(3,3,'2026-01-15'),(4,4,'2025-09-20');

/*CONSULTAS UTILIZADAS EN CODIGO JAVA----------------------------------------*/

/*METODO: crear paciente*/
INSERT INTO pacientes(nombre,email,fecha_nacimiento) VALUES (?,?,?)

/*METODO: eliminar paciente*/
DELETE FROM pacientes WHERE id_paciente = ?

/*METODO: listar tratamientos con pacientes menor que el dato pasado*/
SELECT tratamientos.nombre_tratamiento, COUNT(id_paciente) AS 'cantidad de pacientes' FROM pacientes_tratamientos JOIN tratamientos ON tratamientos.id_tratamiento = pacientes_tratamientos.id_tratamiento GROUP BY tratamientos.nombre_tratamiento HAVING COUNT(pacientes_tratamientos.id_paciente) < ?

/*METODO: obtener pacientes con el total de sus citas*/
SELECT pacientes.nombre, count(citas.id_paciente) AS 'cantidad de citas' FROM pacientes JOIN citas ON pacientes.id_paciente = citas.id_paciente GROUP BY pacientes.nombre, pacientes.nombre