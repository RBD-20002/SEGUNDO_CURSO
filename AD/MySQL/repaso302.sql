/*1*/
select art_color as color, dev_numero as numero, art_nome as nombre, art_peso as peso, art_provedor as proveedor from artigos join detalle_vendas on art_codigo = dev_numero and art_color = "negro";

/*2*/
select clt_nome as nombre, clt_apelidos as apellidos, ven_data as fecha from clientes join vendas on clt_id = ven_cliente;

/*3*/
select count(ven_id) as "total ventas", count(dev_venda) as "articulo vendidos", sum(dev_cantidade) as "unidades vendidas", avg(dev_prezo_unitario) as "media precio" from detalle_vendas join vendas on dev_numero = ven_id;

/*4*/
select art_codigo as numero, art_nome as nombre, art_peso as peso, (select peso_nome from pesos where art_peso BETWEEN peso_min and peso_max) AS peso_final from artigos;

/*5*/

/*A*/
select dev_venta as "Cantidad Vendida", dev_numero as "Identificador", dev_artigo as "Nombre Articulo", ven_data as "Fecha Venta" from vendas join detalle_vendas on ven_id = dev_numero;
/*B*/
select 
/*C*/

/*D*/

/*6*/


/*7*/


/*8*/


/*9*/
