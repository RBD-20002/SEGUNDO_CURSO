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

            ps.executeUpdate();
            System.out.println("\nse agrego paciente correctamente".toUpperCase()+"\n"+"-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminarPaciente(int id){
        String sql = "DELETE FROM pacientes WHERE id_paciente = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            int resultado = ps.executeUpdate();
            if(resultado == 0){
                System.out.println("no se encontro paciente con id: ".toUpperCase()+id);
            }
            System.out.println("-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarTratamientosConPocosPacientes(int cantidad){
        String sql = "SELECT tratamientos.nombre_tratamiento, COUNT(id_paciente) AS 'cantidad de pacientes' FROM pacientes_tratamientos JOIN tratamientos ON tratamientos.id_tratamiento = pacientes_tratamientos.id_tratamiento GROUP BY tratamientos.nombre_tratamiento HAVING COUNT(pacientes_tratamientos.id_paciente) < ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, cantidad);
            ResultSet rs = ps.executeQuery();

            System.out.println("-".repeat(40)+"\nrespuesta:".toUpperCase());
            while (rs.next()){
                String nombreTratamiento = rs.getString("nombre_tratamiento");
                int contador = rs.getInt("cantidad de pacientes");

                System.out.println("| TRATAMIENTO: "+nombreTratamiento+" | CANTIDAD DE PACIENTES: "+contador+" |");
            }
            System.out.println("-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void obtenerTotalCitasPorPaciente(){
        String sql = "SELECT pacientes.nombre, count(citas.id_paciente) AS \"cantidad de citas\" FROM pacientes JOIN citas ON pacientes.id_paciente = citas.id_paciente GROUP BY pacientes.nombre, pacientes.nombre";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("-".repeat(40)+"\nresultado:".toUpperCase());
            while (rs.next()){
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad de citas");

                System.out.println("| NOMBRE: "+nombre+" | CANTIDAD: "+cantidad+" |");
            }
            System.out.println("-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
