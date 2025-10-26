import java.sql.*;

public class BD3 {

    private String url = "jdbc:postgresql://localhost:5432/pokemons";
    private String user = "postgres";
    private String password = "ricardoBD90-";
    private Connection connection;
    private static BD3 instancia;

    public BD3(){
        openConnection();
    }

    public static BD3 getInstancia() {
        if(instancia == null){
            instancia = new BD3();
        }
        return instancia;
    }

    public void openConnection(){
        try {
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void insertPokemon(String nombre, String tipo, int nivel){
        String sql = "INSERTO INTO objetos.pokemons(ROW(nombre,tipo,nivel)) VALUES (ROW(?,?,?))";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setString(2,tipo);
            ps.setInt(3,nivel);

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void modificarPokemon(int id, String nombre, String tipo, int nivel){
        String sql = "UPDATE objetos.pokemons SET pokemon.nombre = ?, pokemon.tipo = ?, pokemon.nivel = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setString(2,tipo);
            ps.setInt(3,nivel);
            ps.setInt(4,id);

            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    /*METODOS ADICIONALES*/

    public void eliminarPokemon(int id){
        String sql = "DELETE FROM objetos.pokemons WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            int contador = ps.executeUpdate();
            if(contador > 0) System.out.println("se elimino correctamente el pokemon".toUpperCase());
            else System.out.println("no se encontro pokemon con id: ".toUpperCase()+id);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarPokemons(){
        String sql = "SELECT id, (pokemon).nombre, (pokemon).tipo, (pokemon).nivel FROM objetos.Pokemons";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                int nivel = rs.getInt("nivel");

                System.out.println("| ID: "+id+" | NOMBRE: "+nombre+" | TIPO: "+tipo+" | NIVEL: "+nivel+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
