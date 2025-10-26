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
                System.out.println("especialidad o medico no encontrado".toUpperCase());
                return;
            }

            int id_nuevoTrat = 0;
            String sql = "INSERT INTO tratamientos(nombre_tratamiento,descripcion) VALUES (?,?)";
            try(PreparedStatement ps = gestorSQL.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1,nombre);
                ps.setString(2,descripcion);

                ps.executeUpdate();
                System.out.println("se agrego tratamiento a base datos sql".toUpperCase());

                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) id_nuevoTrat = rs.getInt(1);
            }

            String sqlPostgre = "INSERT INTO hospital.tratamientos(id_tratamiento,id_medico,id_especialidad) VALUES (?,?,?)";
            try(PreparedStatement ps = gestorPostgreSQL.getConnection().prepareStatement(sqlPostgre)) {
                ps.setInt(1,id_nuevoTrat);
                ps.setInt(2,id_medico);
                ps.setInt(3,id_especialidad);

                ps.executeUpdate();
                System.out.println("se agrego tratamiento a base datos postgresql".toUpperCase());
            }

            gestorPostgreSQL.getConnection().commit();
            gestorSQL.getConnection().commit();

            System.out.println("TRANSACCION COMPLETADA EXITOSAMENTE");
            System.out.println("-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
            try{
                gestorPostgreSQL.getConnection().rollback();
                gestorSQL.getConnection().rollback();
                System.out.println("SE REALIZO ROLLBACK DE LA TRANSACCION");
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
                System.out.println("TRATAMIENTO NO ENCONTRADO");
                gestorPostgreSQL.getConnection().rollback();
                gestorSQL.getConnection().rollback();
                return;
            }

            String sql = "DELETE FROM tratamientos WHERE nombre_tratamiento = ?";
            try(PreparedStatement ps = gestorSQL.getConnection().prepareStatement(sql)){
                ps.setString(1,nombre);

                ps.executeUpdate();
                System.out.println("tratamiento eliminado en base datos sql".toUpperCase());
            }

            String sqlPostgre = "DELETE FROM hospital.tratamientos WHERE id_tratamiento = ?";
            try(PreparedStatement ps = gestorPostgreSQL.getConnection().prepareStatement(sqlPostgre)){
                ps.setInt(1,id_tratamiento);

                ps.executeUpdate();
                System.out.println("tratamiento eliminado en base datos postgresql".toUpperCase());
            }

            gestorPostgreSQL.getConnection().commit();
            gestorSQL.getConnection().commit();

            System.out.println("ELIMINACION COMPLETADA EXITOSAMENTE");
            System.out.println("-".repeat(40));
        }catch (SQLException e){
            System.out.println(e.getMessage());
            try{
                gestorPostgreSQL.getConnection().rollback();
                gestorSQL.getConnection().rollback();
                System.out.println("SE REALIZO ROLLBACK DE LA TRANSACCION");
            }catch(SQLException rollbackEx){
                System.out.println("ERROR AL HACER ROLLBACK: " + rollbackEx.getMessage());
            }
        }finally{
            try{
                // Restaurar auto-commit al estado original
                gestorPostgreSQL.getConnection().setAutoCommit(true);
                gestorSQL.getConnection().setAutoCommit(true);
            }catch(SQLException autoCommitEx){
                System.out.println("ERROR AL RESTAURAR AUTOCOMMIT: " + autoCommitEx.getMessage());
            }
        }
    }

    public void listarTratamientosConEspecialidadYMedico(){
        try{
            ArrayList<String> datosSQL = new ArrayList<>();
            String sql = "SELECT nombre_tratamiento, descripcion FROM tratamientos";
            try(Statement st = gestorSQL.getConnection().createStatement(); ResultSet rs = st.executeQuery(sql)){
                while (rs.next()){
                    String nombre = rs.getString("nombre_tratamiento");
                    String descripcion = rs.getString("descripcion");

                    datosSQL.add("|NOMBRE: "+nombre+" | DESCRIPCION: "+descripcion+" |");
                }
            }

            ArrayList<String> datosPostgreSQL = new ArrayList<>();
            String sqlPostgre = "SELECT id_tratamiento,id_medico,id_especialidad FROM hospital.tratamientos";
            try(Statement st = gestorPostgreSQL.getConnection().createStatement(); ResultSet rs = st.executeQuery(sqlPostgre)){
                while(rs.next()){
                    int tratamiento = rs.getInt("id_tratamiento");
                    int medico = rs.getInt("id_medico");
                    int especialidad = rs.getInt("id_especialidad");

                    datosPostgreSQL.add("|CODIGO TRATAMIENTO: "+tratamiento+" | CODIGO MEDICO: "+medico+" | CODIGO ESPECIALIDAD: "+especialidad+" |");
                }
            }
            System.out.println("-".repeat(40)+"\nresultado:".toUpperCase());
            for(String dato : datosSQL){
                System.out.println(dato);
            }
            for(String dato : datosPostgreSQL){
                System.out.println(dato);
            }
            System.out.println("-".repeat(40));
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

                    System.out.println("-".repeat(40)+"\nresultado:".toUpperCase());
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int id_paciente = rs.getInt("id_paciente");
                        String nombre_paciente = rs.getString("nombre");
                        String nombre_tratamiento = rs.getString("nombre_tratamiento");

                        System.out.println("|ID PACIENTE: " + id_paciente + " | NOMBRE: " + nombre_paciente + " | TRATAMIENTO: " + nombre_tratamiento + " |");
                    }
                    System.out.println("-".repeat(40));
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
