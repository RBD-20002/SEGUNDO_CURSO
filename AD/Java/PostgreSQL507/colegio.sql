create database colegio;

create schema cole;

create type cole.alumno_info as(
nombre varchar(50),
apellido varchar(50),
edad Integer,
dni varchar(9),
direccion varchar(50)
);

create type cole.profesor_info as(
nombre varchar(50),
apellido varchar(50),
edad Integer,
dni varchar(9)
);

create table cole.alumnos(
id_alumno serial,
datos cole.alumno_info,
turno varchar(6),
grupo varchar(1),
primary key (id_alumno)
);

create table cole.profesores(
id_profesor serial,
datos cole.profesor_info,
cod_profesor varchar(10),
primary key (id_profesor)
);

create table cole.salones(
id_salon serial,
id_alumno Integer,
id_profesor Integer,
primary key (id_salon),
foreign key (id_alumno) references cole.alumnos(id_alumno),
foreign key (id_profesor) references cole.profesores(id_profesor)
);

create table cole.cursos(
id_curso serial,
nombre varchar(50),
primary key (id_curso)
);

create table cole.notas(
id_nota serial,
id_alumno Integer,
nota numeric(4,2),
id_curso Integer,
primary key (id_nota,id_alumno),
foreign key (id_alumno) references cole.alumnos(id_alumno),
foreign key (id_curso) references cole.cursos(id_curso)
);

insert into cole.alumnos (datos, turno, grupo) values
(( 'luis', 'ramirez', 15, '12345678a', 'av. los olivos 123'), 'manana', 'a'),
(( 'carla', 'sanchez', 16, '23456789b', 'calle lima 456'), 'tarde', 'b'),
(( 'miguel', 'torres', 15, '34567890c', 'av. grau 789'), 'manana', 'a'),
(( 'lucia', 'fernandez', 17, '45678901d', 'jr. puno 321'), 'tarde', 'c'),
(( 'andres', 'lopez', 16, '56789012e', 'av. arequipa 654'), 'manana', 'b');

insert into cole.profesores (datos, cod_profesor) values
(( 'maria', 'gomez', 35, '11111111a'), 'prof001'),
(( 'jorge', 'ruiz', 42, '22222222b'), 'prof002'),
(( 'rosa', 'vargas', 39, '33333333c'), 'prof003'),
(( 'pedro', 'salas', 45, '44444444d'), 'prof004'),
(( 'elena', 'castro', 31, '55555555e'), 'prof005');

insert into cole.cursos (nombre) values
('matematicas'),('comunicacion'),('ciencias'),('historia'),('ingles');

insert into cole.salones (id_alumno, id_profesor) values
(1, 1),(2, 2),(3, 3),(4, 4),(5, 5);

insert into cole.notas (id_alumno, nota, id_curso) values
(1, 15.50, 1),(1, 14.75, 2),(1, 16.20, 3),(1, 13.90, 4),(1, 17.10, 5),

(2, 12.80, 1),(2, 13.40, 2),(2, 14.25, 3),(2, 15.00, 4),(2, 13.60, 5),

(3, 18.00, 1),(3, 17.50, 2),(3, 16.90, 3),(3, 19.00, 4),(3, 18.25, 5),

(4, 11.40, 1),(4, 12.50, 2),(4, 13.25, 3),(4, 12.90, 4),(4, 14.10, 5),

(5, 17.80, 1),(5, 18.20, 2),(5, 16.75, 3),(5, 17.90, 4),(5, 18.00, 5);