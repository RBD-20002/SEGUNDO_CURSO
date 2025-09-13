/*1*/
select * from tendas;

/*2*/
select prv_nome from provedores;

/*3*/
select * from paises join clientes on cdg_pais = pai_id group by cdg_pais having count(cdg_id) > 0;

/*4*/
select art_nome as nombre, art_pv as "precio de venta", art_pv+(art_pv*0.10) as "incremento de 10%" from artigos;

/*5*/
select clt_nome, clt_apelidos from clientes where clt_poblacion = "Madrid";

/*6*/
select art_codigo, art_nome, art_peso from artigos where art_peso >= 500;

/*7*/
select * from artigos where art_pv >= (art_pc * 2);

/*8*/
select clt_apelidos, clt_nome, clt_poblacion, clt_desconto from clientes where clt_poblacion in ("Asturias","Valencia") and (clt_desconto >=2 or clt_desconto is null);

/*9*/
select * from artigos where art_color = "negro" and art_peso > 5000;

/*10*/
select * from artigos where art_color != "negro" and art_peso <= 5000;

/*11*/
select * from artigos where art_peso > 100 and art_color in ("negro","cyan");

/*12*/
select * from artigos where art_pc between 12 and 18;

/*13*/
select * from artigos where art_color in ("negro","cyan");

/*14*/
select * from clientes where clt_apelidos like "RO%";

/*15*/
select * from clientes where clt_nome like "B___A";

/*16*/
select * from artigos where art_color is null;

/*17*/
select * from artigos order by art_peso desc;

/*18*/
select art_codigo as codigo, art_nome as nombre, art_pc as "precio compra", art_pv as "precio venta", (art_pv - art_pc) as "margen ganancias" from artigos where art_pc > 3000 order by "margen ganancias";

/*19*/
select art_nome as nombre, art_provedor as proveedor, art_stock as stock, art_peso as peso from artigos where art_peso <= 1000 order by art_provedor asc, art_stock desc;

/*20*/
select clt_nome as nombre, clt_apelidos as apellidos from clientes where clt_apelidos like "F%Z";

/*21*/
select * from clientes where (clt_apelidos like "A%" or clt_apelidos like "F%");

/*22*/
select * from clientes where clt_nome not like "A%";

/*23*/
select * from clientes where clt_nome like "_____";