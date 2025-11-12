import java.sql.*;

public class BD1 {

    private String urlBase = "jdbc:postgresql://localhost:5432/";
    private String user = "postgres";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD1(){
        openConnection(urlBase);
    }

    private void openConnection(String url){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void crearBD(){
        try(Statement st = connection.createStatement()){
            st.executeUpdate("DROP DATABASE libros");
            st.executeUpdate("CREATE DATABASE libros");
            System.out.println("se creo bd libros".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        String urlLibros = urlBase+"libros";
        openConnection(urlLibros);
        try(Connection connLibros = DriverManager.getConnection(urlLibros, user, password); Statement st = connLibros.createStatement()) {
                st.executeUpdate("CREATE TYPE Autor AS (nombre_autor varchar(50), fecha varchar(10))");
                st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS libros(
                    id serial,
                    titulo varchar(50),
                    autor Autor,
                    publicacion Integer,
                    PRIMARY KEY (id)
                )
                """);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertLibro(String titulo,String nombre_autor,String fecha,int publicacion){
        String sql = "INSERT INTO libros(titulo,autor,publicacion) VALUES (?,ROW(?,?),?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,titulo);
            ps.setString(2,nombre_autor);
            ps.setString(3,fecha);
            ps.setInt(4,publicacion);

            ps.executeUpdate();
            System.out.println("se inserto el nuevo libro correctamente".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminarLibro(int id){
        String sql = "DELETE FROM libros WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            int contador = ps.executeUpdate();
            if(contador > 0) System.out.println("se elimino correctamente".toUpperCase());
            else System.out.println("no se encontro libro con esa id".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void select(){
        String sql = "SELECT * FROM libros";
        try(Statement st = connection.createStatement()){
            st.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateLibros(int id,String titulo,String nombre_autor,String fecha,int publicacion){
        String sql = "UPDATE libros SET titulo = ?, Autor.nombre_autor = ?, Autor.fecha = ?, publicacion = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){

        }catch (SQLException e){
            System.out.println();
        }
    }
}
