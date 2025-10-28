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

            if(fila > 0) System.out.println("╔"+"═".repeat(19)+"╗\n ESPECIALIDAD CREADA\n╚"+"═".repeat(19)+"╝");
            else System.out.println("╔"+"═".repeat(17)+"╗\n NO SE AGREGO NADA\n╚"+"═".repeat(17)+"╝");

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

            System.out.println("╔"+"═".repeat(13)+"╗\n MEDICO CREADO\n╚"+"═".repeat(13)+"╝");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminarMedico(int id){
        String sql = "DELETE FROM hospital.medicos WHERE id_medico = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            int fila = ps.executeUpdate();
            if(fila > 0) {
                System.out.println("╔"+"═".repeat(16)+"╗\n"+"║MEDICO ELIMINADO║\n╚"+"═".repeat(16)+"╝");
            }
            else System.out.println("╔"+"═".repeat(16)+"╗\n"+"║NO EXISTE ESA ID║\n╚"+"═".repeat(16)+"╝");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void obtenerCantidadTratamientosPorSala(){
        String sql = "SELECT salas.nombre_sala, COUNT(salas_tratamientos.id_tratamiento) as \"Tratamientos por sala\" FROM hospital.salas JOIN hospital.salas_tratamientos ON salas.id_sala = salas_tratamientos.id_sala GROUP BY salas.id_sala, salas.nombre_sala";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println( "╔"+"═".repeat(53)+"╗\n║                      RESULTADO                      ║\n"+"╚"+"═".repeat(53)+"╝");
            while(rs.next()){
                String nombreSala = rs.getString("nombre_sala");
                int cantidadPorSala = rs.getInt("tratamientos por sala");

                System.out.println( "╔"+"═".repeat(53)+"╗\n  SALA: "+nombreSala+" ║ TRATAMIENTO DISPONIBLE: "+cantidadPorSala+"\n╚"+"═".repeat(53)+"╝");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMedicos(){
        String pqsl = "SELECT id_medico, nombre_medico FROM hospital.medicos";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(pqsl)){
            System.out.println( "╔"+"═".repeat(25)+"╗\n RESULTADO:\n"+"╚"+"═".repeat(25)+"╝");
            System.out.println("╔"+"═".repeat(25)+"╗");
            while (rs.next()){
                int id = rs.getInt("id_medico");
                String nombre = rs.getString("nombre_medico");

                System.out.println(" ID: "+id+" ║ DOCTOR/AR: "+nombre);
            }
            System.out.println("╚"+"═".repeat(25)+"╝");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
