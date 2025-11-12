import java.sql.*;

public class BD6 {

    private Connection connection;
    private String urlBase = "jdbc:postgresql://localhost:5432/";
    private String user = "postgres";
    private String password = "ricardoBD90-";

    public BD6() {
        openConnectio(urlBase);
    }

    public void openConnectio(String url) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void crearBD(){
        try(Statement st = connection.createStatement()){
            st.executeUpdate("DROP DATABASE almacen WITH (FORCE)");
            System.out.println("SE ELIMINO BD POR SI ACASO");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        try(Statement st = connection.createStatement()){
            st.executeUpdate("CREATE DATABASE almacen");
            System.out.println("SE CREO LA BD ALMACEN");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        String urlAl = urlBase+"almacen";
        openConnectio(urlAl);
        try(Statement st = connection.createStatement()){
            st.executeUpdate("DROP SCHEMA IF EXISTS producto CASCADE");
            st.executeUpdate("CREATE SCHEMA producto");

            st.executeUpdate("CREATE TYPE producto.prod AS (cod_producto varchar(20), nombre varchar(30), precio NUMERIC(4,2), descripcion TEXT)");

            st.executeUpdate("CREATE TABLE producto.productos (id_producto SERIAL, info_prodcuto producto.prod, PRIMARY KEY (id_producto))");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertProducto(String codigo, String nombre, double precio, String descripcion){
        String psql = "INSERT INTO producto.productos(info_prodcuto) VALUES(ROW(?,?,?,?))";
        try(PreparedStatement ps = connection.prepareStatement(psql)){
            ps.setString(1,codigo);
            ps.setString(2,nombre);
            ps.setDouble(3,precio);
            ps.setString(4,descripcion);
            int contador = ps.executeUpdate();
            if(contador > 0) System.out.println("SE AGREGO PRODUCTO");
            else System.out.println("FALLO AL AGREGAR PRODUCTO");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}