create database libros;

\c libros

create type Autor as (
nombre_autor varchar(50),
fecha varchar(10)
);

crate table if not exists libros(
id serial,
titulo varchar(50),
autor Autor,
publicacion Integer,
primary key (id)
);