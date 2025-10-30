import java.sql.*;
import java.util.ArrayList;

public class CoordPostSQL_MySQL {

    private static CoordPostSQL_MySQL instancia;
    private GestorSQL gestorSQL;
    private GestorPostgreSQL gestorPostgreSQL;

    public CoordPostSQL_MySQL(GestorSQL sql, GestorPostgreSQL postgreSQL){
        this.gestorSQL = sql;
        this.gestorPostgreSQL = postgreSQL;
    }

    public static CoordPostSQL_MySQL getInstance(GestorSQL sql, GestorPostgreSQL postgreSQL){
        if(instancia == null){
            instancia = new CoordPostSQL_MySQL(sql,postgreSQL);
        }
        return instancia;
    }

    public void crearTratamiento(String nombre, String descripcion, String nombreEspecialidad, String nifMedico){
        try{
            gestorPostgreSQL.getConnection().setAutoCommit(false);
            gestorSQL.getConnection().setAutoCommit(false);

           int id_especialidad = 0;
           String selectIdEsp = "SELECT id_especialidad FROM hospital.especialidades WHERE nombre_especialidad = ?";
           try(PreparedStatement ps1 = gestorPostgreSQL.getConnection().prepareStatement(selectIdEsp)){
               ps1.setString(1,nombreEspecialidad);
               ResultSet rs = ps1.executeQuery();
               if(rs.next()) {
                   id_especialidad = rs.getInt("id_especialidad");
                   System.out.println("ESPECIALIDAD ENCONTRADA - ID: " + id_especialidad);
               } else {
                   System.out.println("ESPECIALIDAD NO ENCONTRADA: " + nombreEspecialidad);
               }
           }

           int id_medico = 0;
           String selectIdMed = "SELECT id_medico FROM hospital.medicos WHERE (contacto).nif = ?";
           try(PreparedStatement ps2 = gestorPostgreSQL.getConnection().prepareStatement(selectIdMed)){
               ps2.setString(1,nifMedico);
               ResultSet rs = ps2.executeQuery();
               if(rs.next()) {
                   id_medico = rs.getInt("id_medico");
                   System.out.println("MÉDICO ENCONTRADO - ID: " + id_medico);
               } else {
                   System.out.println("MÉDICO NO ENCONTRADO CON NIF: " + nifMedico);
               }
           }

            if(id_especialidad == 0 || id_medico == 0){
                System.out.println("╔"+"═".repeat(43)+"╗");
                System.out.println("║    ESPECIALIDAD O MÉDICO NO ENCONTRADO    ║");
                System.out.println("║     NO SE CREÓ NINGÚN TRATAMIENTO         ║");
                System.out.println("╚"+"═".repeat(43)+"╝");

                gestorSQL.getConnection().rollback();
                gestorPostgreSQL.getConnection().rollback();
                return;
            }

            int id_nuevoTrat = 0;
            String sql = "INSERT INTO tratamientos(nombre_tratamiento,descripcion) VALUES (?,?)";
            try(PreparedStatement ps = gestorSQL.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,nombre);
                ps.setString(2,descripcion);

                ps.executeUpdate();
                System.out.println("╔"+"═".repeat(27)+"╗\n TRATAMIENTO AGREGADO EN SQL\n╚"+"═".repeat(27)+"╝");

                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) id_nuevoTrat = rs.getInt(1);
            }

            String sqlPostgre = "INSERT INTO hospital.tratamientos(id_tratamiento,id_medico,id_especialidad) VALUES (?,?,?)";
            try(PreparedStatement ps = gestorPostgreSQL.getConnection().prepareStatement(sqlPostgre)) {
                ps.setInt(1,id_nuevoTrat);
                ps.setInt(2,id_medico);
                ps.setInt(3,id_especialidad);

                ps.executeUpdate();
                System.out.println("╔"+"═".repeat(28)+"╗\n TRATAMIENTO AGREGADO EN PSQL\n╚"+"═".repeat(28)+"╝");
            }

            gestorPostgreSQL.getConnection().commit();
            gestorSQL.getConnection().commit();

            System.out.println("╔"+"═".repeat(17)+"╗\n INSERCION EXITOSA\n╚"+"═".repeat(17)+"╝");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            try{
                gestorPostgreSQL.getConnection().rollback();
                gestorSQL.getConnection().rollback();
                System.out.println("╔"+"═".repeat(22)+"╗\n SE REALIZO UN ROLLBACK\n╚"+"═".repeat(22)+"╝");
            }catch(SQLException rollbackEx){
                System.out.println("ERROR AL HACER ROLLBACK: " + rollbackEx.getMessage());
            }
        }finally{
            try{
                gestorPostgreSQL.getConnection().setAutoCommit(true);
                gestorSQL.getConnection().setAutoCommit(true);
            }catch(SQLException autoCommitEx){
                System.out.println("ERROR AL RESTAURAR AUTOCOMMIT: " + autoCommitEx.getMessage());
            }
        }
    }

    public void eliminarTratamientoPorNombre(String nombre){
        try{
            gestorPostgreSQL.getConnection().setAutoCommit(false);
            gestorSQL.getConnection().setAutoCommit(false);

            int id_tratamiento =0;
            String sIdTrat = "SELECT id_tratamiento FROM tratamientos WHERE nombre_tratamiento = ?";
            try(PreparedStatement ps = gestorSQL.getConnection().prepareStatement(sIdTrat)){
                ps.setString(1,nombre);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) id_tratamiento = rs.getInt("id_tratamiento");
            }

            if(id_tratamiento == 0){
                System.out.println("╔"+"═".repeat(25)+"╗\nTRATAMIENTO NO ENCONTRADO\n╚"+"═".repeat(25)+"╝");
                gestorPostgreSQL.getConnection().rollback();
                gestorSQL.getConnection().rollback();
                return;
            }

            String sql = "DELETE FROM tratamientos WHERE nombre_tratamiento = ?";
            try(PreparedStatement ps = gestorSQL.getConnection().prepareStatement(sql)){
                ps.setString(1,nombre);
                int filas = ps.executeUpdate();

                if(filas > 0) System.out.println("╔"+"═".repeat(28)+"╗\n TRATAMIENTO ELIMINADO EN SQL\n╚"+"═".repeat(28)+"╝");
                else {
                    System.out.println("╔"+"═".repeat(40)+"╗\n║  TRATAMIENTO NO ENCONTRADO EN MYSQL  ║\n╚"+"═".repeat(40)+"╝");
                    gestorPostgreSQL.getConnection().rollback();;
                    gestorSQL.getConnection().rollback();
                    return;
                }
            }

            String sqlPostgre = "DELETE FROM hospital.tratamientos WHERE id_tratamiento = ?";
            try(PreparedStatement ps = gestorPostgreSQL.getConnection().prepareStatement(sqlPostgre)) {
                ps.setInt(1, id_tratamiento);
                int filasAfectadasPG = ps.executeUpdate();
                if (filasAfectadasPG > 0) System.out.println("╔" + "═".repeat(29) + "╗\n TRATAMIENTO ELIMINADO EN PSQL\n╚" + "═".repeat(29) + "╝");
                else{
                    System.out.println("╔" + "═".repeat(45) + "╗\n║  TRATAMIENTO NO ENCONTRADO EN POSTGRESQL  ║\n╚" + "═".repeat(45) + "╝");
                    gestorPostgreSQL.getConnection().rollback();;
                    gestorSQL.getConnection().rollback();
                    return;
                }

            }

            gestorPostgreSQL.getConnection().commit();
            gestorSQL.getConnection().commit();
            System.out.println("╔"+"═".repeat(19)+"╗\n ELIMINACION EXITOSA\n╚"+"═".repeat(19)+"╝");

        }catch (SQLException e){
            System.err.println(e.getMessage());
            try{
                gestorPostgreSQL.getConnection().rollback();
                gestorSQL.getConnection().rollback();
                System.err.println("╔"+"═".repeat(22)+"╗\n SE REALIZO UN ROLLBACK\n╚"+"═".repeat(22)+"╝");
            }catch(SQLException rollbackEx){
                System.err.println(rollbackEx.getMessage());
            }
        }finally{
            try{
                gestorPostgreSQL.getConnection().setAutoCommit(true);
                gestorSQL.getConnection().setAutoCommit(true);
            }catch(SQLException autoCommit){
                System.err.println(autoCommit.getMessage());
            }
        }
    }

    public void listarTratamientosConEspecialidadYMedico(){
        try{
            ArrayList<String> datosSQL = new ArrayList<>();
            String sql = "SELECT id_tratamiento, nombre_tratamiento, descripcion FROM tratamientos ORDER BY id_tratamiento";
            try(Statement st = gestorSQL.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)){
                while (rs.next()){
                    int id = rs.getInt("id_tratamiento");
                    String nombre = rs.getString("nombre_tratamiento");
                    String descripcion = rs.getString("descripcion");

                    datosSQL.add(" ID: "+id+" \n NOMBRE: "+nombre+" \n DESCRIPCION: "+descripcion);
                }
            }

            ArrayList<String> datosPostgreSQL = new ArrayList<>();
            String sqlPostgre = "SELECT t.id_tratamiento, e.nombre_especialidad, m.nombre_medico, (m.contacto).nif AS nif_medico FROM hospital.tratamientos t JOIN hospital.especialidades e ON t.id_especialidad = e.id_especialidad JOIN hospital.medicos m ON t.id_medico = m.id_medico ORDER BY t.id_tratamiento";
            try(Statement st = gestorPostgreSQL.getConnection().createStatement(); ResultSet rs = st.executeQuery(sqlPostgre)){
                while(rs.next()){
                    int idTratamiento = rs.getInt("id_tratamiento");
                    String especialidad = rs.getString("nombre_especialidad");
                    String medico = rs.getString("nombre_medico");
                    String nifMedico = rs.getString("nif_medico");

                    datosPostgreSQL.add(" ESPECIALIDAD: "+especialidad+" \n MEDICO: "+medico+" \n NIF: "+nifMedico);
                }
            }
            System.out.println("╔"+"═".repeat(82)+"╗\n LISTADO DE ESPECIALIDADES CON MEDICOS:\n╚"+"═".repeat(82)+"╝");
           for(int i = 0; i < datosSQL.size(); i++){
               System.out.println("╔"+"═".repeat(82)+"╗");
               System.out.println(datosSQL.get(i));
               System.out.println(datosPostgreSQL.get(i));
               System.out.println("╚"+"═".repeat(82)+"╝");
           }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void obtenerPacientesPorEspecialidad(int idEspecialidad){
        try {
            ArrayList<Integer> idTratamientos = new ArrayList<>();
            String sqlPostgre = "SELECT id_tratamiento FROM hospital.tratamientos WHERE id_especialidad = ?";
            try(PreparedStatement ps = gestorPostgreSQL.getConnection().prepareStatement(sqlPostgre)){
                ps.setInt(1,idEspecialidad);

                ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    idTratamientos.add(rs.getInt("id_tratamiento"));
                }
            }

            String sql = "SELECT pacientes.id_paciente,pacientes.nombre, tratamientos.nombre_tratamiento FROM pacientes_tratamientos JOIN pacientes ON pacientes.id_paciente = pacientes_tratamientos.id_paciente JOIN tratamientos ON pacientes_tratamientos.id_tratamiento = tratamientos.id_tratamiento WHERE pacientes_tratamientos.id_tratamiento = ?";
            try(PreparedStatement ps = gestorSQL.getConnection().prepareStatement(sql)){
                for(Integer id_tratamiento : idTratamientos) {
                    ps.setInt(1, id_tratamiento);

                    System.out.println("╔"+"═".repeat(80)+"╗\n║"+" ".repeat(36)+"RESULTADO"+" ".repeat(35)+"║\n╚"+"═".repeat(80)+"╝");
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id_paciente = rs.getInt("id_paciente");
                        String nombre_paciente = rs.getString("nombre");
                        String nombre_tratamiento = rs.getString("nombre_tratamiento");

                        System.out.println("╔"+"═".repeat(80)+"╗\n  ID PACIENTE: "+id_paciente+" ║ PACIENTE: "+nombre_paciente+" ║ TRATAMIENTO: "+nombre_tratamiento+"\n╚"+"═".repeat(80)+"╝");
                    }
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
