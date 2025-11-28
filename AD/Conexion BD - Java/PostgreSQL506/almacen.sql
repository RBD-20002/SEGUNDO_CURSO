CREATE DATABASE almacen;

CREATE SCHEMA productos;

CREATE TYPE productos.Producto AS (
codigo_producto varchar(10),
nombre varchar(50),
precio DOUBLE,
descripcion text
);

CREATE TABLE Productos(
producto_id serial,
producto_info productos.Producto
PRIMARY KEY (producto_id)
);