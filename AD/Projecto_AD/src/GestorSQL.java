import java.sql.*;
import java.time.LocalDate;

public class GestorSQL {

    private static GestorSQL instancia;
    private Connection connection;

    private String urlSQL = "jdbc:mysql://localhost:3306/hospital_sql";
    private String userSQL = "root";
    private String passwordSQL = "ricardoBD90-";


    public GestorSQL(){
        openConnection();
    }

    public static GestorSQL getInstance(){
        if(instancia == null) {
            instancia = new GestorSQL();
        }
        return instancia;
    }

    public void openConnection(){
        try {
            connection = DriverManager.getConnection(urlSQL,userSQL,passwordSQL);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void crearPaciente(String nombre, String email, LocalDate fechaNacimiento){
        String sql = "INSERT INTO pacientes(nombre,email,fecha_nacimiento) VALUES (?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setDate(3, Date.valueOf(fechaNacimiento));

            int fila = ps.executeUpdate();
            if(fila > 0) System.out.println("╔"+"═".repeat(17)+"╗\n PACIENTE AGREGADO\n╚"+"═".repeat(17)+"╝");
            else System.out.println("╔"+"═".repeat(21)+"╗\n NO SE AGREGO PACIENTE\n╚"+"═".repeat(21)+"╝");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminarPaciente(int id){
        String sql = "DELETE FROM pacientes WHERE id_paciente = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            if(fila > 0) System.out.println("╔"+"═".repeat(18)+"╗\n PACIENTE ELIMINADO\n╚"+"═".repeat(18)+"╝");
            else System.out.println("╔"+"═".repeat(26)+"╗\n NO HAY PACIENTE CON ESA ID\n╚"+"═".repeat(26)+"╝");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarTratamientosConPocosPacientes(int cantidad){
        String sql = "SELECT tratamientos.nombre_tratamiento, COUNT(id_paciente) AS 'cantidad de pacientes' FROM pacientes_tratamientos JOIN tratamientos ON tratamientos.id_tratamiento = pacientes_tratamientos.id_tratamiento GROUP BY tratamientos.nombre_tratamiento HAVING COUNT(pacientes_tratamientos.id_paciente) < ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, cantidad);
            ResultSet rs = ps.executeQuery();

            System.out.println("╔"+"═".repeat(69)+"╗\n RESULTADO:\n╚"+"═".repeat(69)+"╝");
            while (rs.next()){
                String nombreTratamiento = rs.getString("nombre_tratamiento");
                int contador = rs.getInt("cantidad de pacientes");

                System.out.println("╔"+"═".repeat(69)+"╗\n  TRATAMIENTO: "+nombreTratamiento+" ║ CANTIDAD DE PACIENTES: "+contador+"\n╚"+"═".repeat(69)+"╝");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void obtenerTotalCitasPorPaciente(){
        String sql = "SELECT pacientes.nombre, count(citas.id_paciente) AS \"cantidad de citas\" FROM pacientes JOIN citas ON pacientes.id_paciente = citas.id_paciente GROUP BY pacientes.nombre, pacientes.nombre";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println( "╔"+"═".repeat(53)+"╗\n║                      RESULTADO                      ║\n"+"╚"+"═".repeat(53)+"╝");
            while (rs.next()){
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad de citas");

                System.out.println( "╔"+"═".repeat(53)+"╗\n   NOMBRE: "+nombre+" ║ CANTIDAD DE CITAS: "+cantidad+"\n╚"+"═".repeat(53)+"╝");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarTratamientos(){
        String sql = "SELECT id_tratamiento, nombre_tratamiento FROM tratamientos";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println( "╔"+"═".repeat(50)+"╗\n║                     RESULTADO                    ║\n"+"╚"+"═".repeat(50)+"╝");
            System.out.println("╔"+"═".repeat(50)+"╗");
            while (rs.next()){
                int id = rs.getInt("id_tratamiento");
                String tramiento = rs.getString("nombre_tratamiento");
                System.out.println(" ID: "+id+" ║ TRATAMIENTO: "+tramiento);
            }
            System.out.println("╚"+"═".repeat(50)+"╝");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void mostrarPacientes(){
        String sql = "SELECT id_paciente, nombre FROM pacientes";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println( "╔"+"═".repeat(50)+"╗\n║                     RESULTADO                    ║\n"+"╚"+"═".repeat(50)+"╝");
            System.out.println("╔"+"═".repeat(50)+"╗");
            while (rs.next()){
                int id = rs.getInt("id_paciente");
                String nombre = rs.getString("nombre");

                System.out.println(" ID: "+id+" ║ NOMBRE: "+nombre);
            }
            System.out.println("╚"+"═".repeat(50)+"╝");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
