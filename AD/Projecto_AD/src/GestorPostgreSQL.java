import java.sql.*;

public class GestorPostgreSQL {

    private Connection connection;
    private static GestorPostgreSQL instancia;

    private String urlPostSQL = "jdbc:postgresql://localhost:5432/hospital_postgresql";
    private String userPostSQL = "postgres";
    private String passwordPostSQL = "ricardoBD90-";

    public GestorPostgreSQL(){
        openConnection();
    }

    public static GestorPostgreSQL getInstance(){
        if(instancia == null){
            instancia = new GestorPostgreSQL();
        }
        return instancia;
    }

    public void openConnection(){
        try{
            connection = DriverManager.getConnection(urlPostSQL,userPostSQL,passwordPostSQL);
        }catch (SQLException ep){
            System.out.println(ep.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void crearEspecialidad(String nombreEspecialidad){
        String sql = "INSERT INTO hospital.especialidades(nombre_especialidad) VALUES (?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombreEspecialidad);
            int fila = ps.executeUpdate();
            if(fila > 0) System.out.println("se agrego especialidad correctamente".toUpperCase());
            else System.out.println("no se agrego ningun ");

            System.out.println("-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void crearMedico(String nombreMedico, String nif, int telefono, String email){
        String sql = "INSERT INTO hospital.medicos(nombre_medico, contacto) VALUES (?,ROW(?,?,?,?))";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombreMedico);
            ps.setString(2,nombreMedico);
            ps.setString(3,nif);
            ps.setInt(4,telefono);
            ps.setString(5,email);
            ps.executeUpdate();

            System.out.println("se agrego medico correctamente".toUpperCase());
            System.out.println("-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminarMedico(int id){
        String sql = "DELETE FROM hospital.medicos WHERE id_medico = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            int fila = ps.executeUpdate();
            if(fila > 0) System.out.println("se elimino correctamente".toUpperCase());
            else System.out.println("no se encontro ningun medico ");
            System.out.println("-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void obtenerCantidadTratamientosPorSala(){
        String sql = "SELECT salas.nombre_sala, COUNT(salas_tratamientos.id_tratamiento) as \"Tratamientos por sala\" FROM hospital.salas JOIN hospital.salas_tratamientos ON salas.id_sala = salas_tratamientos.id_sala GROUP BY salas.id_sala, salas.nombre_sala";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("-".repeat(40)+"\nresultado:".toUpperCase());
            while(rs.next()){
                String nombreSala = rs.getString("nombre_sala");
                int cantidadPorSala = rs.getInt("tratamientos por sala");

                System.out.println("| SALA: "+nombreSala+" | TRATAMIENTOS DISPONIBLES: "+cantidadPorSala+" |");
            }
            System.out.println("-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
