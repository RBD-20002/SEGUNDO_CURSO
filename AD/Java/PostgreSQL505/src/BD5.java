import java.sql.*;

public class BD5 {

    private String urlBase = "jdbc:postgresql://localhost:5432/";
    private String user = "postgres";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD5(){
        openConnection(urlBase);
    }

    public void openConnection(String url){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void crearBD(){
        try(Statement st = connection.createStatement()){
            st.executeUpdate("DROP DATABASE hospital WITH (FORCE)");
            System.out.println("se elimino la BD por siacaso");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        try(Statement st = connection.createStatement()){
            st.executeUpdate("CREATE DATABASE hospital");
            System.out.println("se creo la bd hospital".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        String urlHos = urlBase+"hospital";
        openConnection(urlHos);

        try(Statement st = connection.createStatement()){
            st.executeUpdate("DROP SCHEMA IF EXISTS objetos CASCADE");
            st.executeUpdate("CREATE SCHEMA objetos");

            st.executeUpdate("CREATE TYPE objetos.persona AS (nombre varchar(50), edad integer)");
            st.executeUpdate("CREATE TYPE objetos.paciente AS (numero_historia varchar(40), grupo_sanguineo varchar(10))");
            st.executeUpdate("CREATE TYPE objetos.examenMedico AS (nombre_examen varchar(50), fecha_realizacion date, resultados varchar(100))");
            st.executeUpdate("CREATE TYPE objetos.medico AS (codigo_medico varchar(20), especialidad varchar(50))");

            st.executeUpdate("CREATE TABLE objetos.medicos(medico_id serial, datos_personales objetos.persona, medico_info objetos.medico, PRIMARY KEY (medico_id))");
            st.executeUpdate("CREATE TABLE objetos.pacientes(paciente_id serial, datos_personales objetos.persona, paciente_info objetos.paciente, PRIMARY KEY (paciente_id))");
            st.executeUpdate("CREATE TABLE objetos.citasMedicas(cita_id serial, fecha_hora date, paciente_id integer, medico_id integer, PRIMARY KEY (cita_id), FOREIGN KEY (paciente_id) REFERENCES objetos.pacientes(paciente_id), FOREIGN KEY (medico_id) REFERENCES objetos.medicos(medico_id))");
            st.executeUpdate("CREATE TABLE objetos.examenesMedicos(examen_id serial, examen_info objetos.examenMedico, paciente_id integer, PRIMARY KEY (examen_id), FOREIGN KEY (paciente_id) REFERENCES objetos.pacientes (paciente_id))");

            st.executeUpdate("INSERT INTO objetos.medicos (datos_personales, medico_info) VALUES (ROW('Juan Pérez', 45), ROW('MED001', 'Cardiología'))");
            st.executeUpdate("INSERT INTO objetos.medicos (datos_personales, medico_info) VALUES (ROW('Ana Gómez', 38), ROW('MED002', 'Pediatría'))");
            st.executeUpdate("INSERT INTO objetos.pacientes (datos_personales, paciente_info) VALUES (ROW('Carlos Ruiz', 30), ROW('HIST001', 'O+'))");
            st.executeUpdate("INSERT INTO objetos.pacientes (datos_personales, paciente_info) VALUES (ROW('Lucía Fernández', 25), ROW('HIST002', 'A-'))");
            st.executeUpdate("INSERT INTO objetos.citasMedicas (fecha_hora, paciente_id, medico_id) VALUES ('2025-11-26 10:00', 1, 1)");
            st.executeUpdate("INSERT INTO objetos.citasMedicas (fecha_hora, paciente_id, medico_id) VALUES ('2025-11-26 11:30', 2, 2)");
            st.executeUpdate("INSERT INTO objetos.examenesMedicos (examen_info, paciente_id) VALUES (ROW('Hemograma', '2025-11-20', 'Normal'), 1)");
            st.executeUpdate("INSERT INTO objetos.examenesMedicos (examen_info, paciente_id) VALUES (ROW('Radiografía', '2025-11-21', 'Fractura leve'), 2)");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*METODOS MEDICOS*/
    /*FUNCIONA EN PGADMIN4*/
    public void insertMedico(String nombre, int edad, String codigo_medico, String especialidad){
        String sql = "INSERT INTO objetos.medicos(datos_personales,medico_info) VALUES (ROW(?,?),ROW(?,?))";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setInt(2,edad);
            ps.setString(3,codigo_medico);
            ps.setString(4,especialidad);

            ps.executeUpdate();
            System.out.println("medico agregado correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void deleteMedico(int id){
        String sql = "DELETE FROM objetos.medicos WHERE medico_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);

            ps.executeUpdate();
            System.out.println("medico eliminado correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void updateMedico(int id, String nombre, int edad, String codigo_medico, String especialidad){
        String sql = "UPDATE objetos.medicos SET datos_personales = ROW(?,?), medico_info = ROW(?,?) WHERE medico_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setInt(2,edad);
            ps.setString(3,codigo_medico);
            ps.setString(4,especialidad);
            ps.setInt(5,id);

            ps.executeUpdate();
            System.out.println("medico modificado correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*METODOS PACIENTES*/
    /*FUNCIONA EN PGADMIN4*/
    public void insertPaciente(String nombre, int edad, String numero_historia, String grupo_sanguineo){
        String sql = "INSERT INTO objetos.pacientes(datos_personales,paciente_info) VALUES (ROW(?,?),ROW(?,?))";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setInt(2,edad);
            ps.setString(3,numero_historia);
            ps.setString(4,grupo_sanguineo);

            ps.executeUpdate();
            System.out.println("paciente agregado correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void deletePaciente(int id){
        String sql = "DELETE FROM objetos.pacientes WHERE paciente_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);

            ps.executeUpdate();
            System.out.println("paciente eliminado correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void updatePaciente(int id, String nombre, int edad, String numero_historia, String grupo_sanguineo){
        String sql = "UPDATE objetos.pacientes SET datos_personales = ROW(?,?), paciente_info = ROW(?,?) WHERE paciente_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setInt(2,edad);
            ps.setString(3,numero_historia);
            ps.setString(4,grupo_sanguineo);
            ps.setInt(5,id);

            ps.executeUpdate();
            System.out.println("paciente modificado correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*METODOS EXAMENES MEDICOS*/
    /*FUNCIONA EN PGADMIN4*/
    public void insertExamenesMedicos(int id_paciente, String nombre_examen, Date fecha_realizacion, String resultados){
        String sql = "INSERT INTO objetos.examenesMedicos(examen_info,paciente_id) VALUES (ROW(?,?,?),?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre_examen);
            ps.setDate(2,fecha_realizacion);
            ps.setString(3,resultados);
            ps.setInt(4,id_paciente);

            ps.executeUpdate();
            System.out.println("medico agregado correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*METODOS EXAMENES MEDICOS*/
    /*FUNCIONA EN PGADMIN4*/
    public void deleteExamenMedico(int id){
        String sql = "DELETE FROM objetos.examenesMedicos WHERE examen_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*METODOS EXAMENES MEDICOS*/
    /*FUNCIONA EN PGADMIN4*/
    public void updateExamenMedico(String nombre_examen, Date fecha_realizacion, String resultados, int id_paciente){
        String sql = "UPDATE objetos.examenesMedicos SET examen_info = ROW(?,?,?) WHERE examen_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre_examen);
            ps.setDate(2,fecha_realizacion);
            ps.setString(3,resultados);
            ps.setInt(4,id_paciente);

            ps.executeUpdate();
            System.out.println("examen medico modificado correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*METODOS CITAS MEDICAS*/
    /*FUNCIONA EN PGADMIN4*/
    public void insertCitaMedica(Date fecha_hora, int paciente_id, int medico_id){
        String sql = "INSERT INTO objetos.citasMedicas(fecha_hora,paciente_id,medico_id) VALUES (?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDate(1,fecha_hora);
            ps.setInt(2,paciente_id);
            ps.setInt(3,medico_id);

            ps.executeUpdate();
            System.out.println("cita medica agregada correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void deleteCitaMedica(int id){
        String sql = "DELETE FROM objetos.citasMedicas WHERE cita_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);

            ps.executeUpdate();
            System.out.println("cita medica eliminada correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void updateCitaMedica(int id, Date fecha_hora, int paciente_id, int medico_id){
        String sql = "UPDATE objetos.citasMedicas SET fecha_hora = ?, paciente_id = ?, medico_id = ? WHERE cita_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDate(1,fecha_hora);
            ps.setInt(2,paciente_id);
            ps.setInt(3,medico_id);
            ps.setInt(4,id);

            ps.executeUpdate();
            System.out.println("cita medica modificada correctamente".toUpperCase());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*METODOS PRINCIPALES*/
    /*FUNCIONA EN PGADMIN4*/
    public void filtrarPaciente(int id){
        String sql = "SELECT paciente_id,(datos_personales).nombre,(datos_personales).edad,(paciente_info).numero_historia,(paciente_info).grupo_sanguineo FROM objetos.pacientes WHERE paciente_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                int id2 = rs.getInt("paciente_id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String historia = rs.getString("numero_historia");
                String sanguineo = rs.getString("grupo_sanguineo");

                System.out.println("|ID: "+id2+" | NOMBRE: "+nombre+" | EDAD: "+edad+" | NUMERO HISTORIA: "+historia+" | GRUPO SANGUINEO: "+sanguineo+" |");
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void mostrarPacientes(){
        String sql = "SELECT paciente_id, (datos_personales).nombre, (datos_personales).edad, (paciente_info).numero_historia, (paciente_info).grupo_sanguineo FROM objetos.pacientes";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                int id2 = rs.getInt("paciente_id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String historia = rs.getString("numero_historia");
                String sanguineo = rs.getString("grupo_sanguineo");

                System.out.println("|ID: "+id2+" | NOMBRE: "+nombre+" | EDAD: "+edad+" | NUMERO HISTORIA: "+historia+" | GRUPO SANGUINEO: "+sanguineo+" |");
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void filtrarMedico(int id){
        String sql = "SELECT medico_id, (datos_personales).nombre, (datos_personales).edad, (medico_info).codigo_medico, (medico_info).especialidad FROM objetos.medicos WHERE medico_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                int id2 = rs.getInt("medico_id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String codigo = rs.getString("codigo_medico");
                String especialidad = rs.getString("especialidad");

                System.out.println("| ID: "+id2+" | NOMBRE: "+nombre+" | EDAD: "+edad+" | CODIGO MEDICO: "+codigo+" | ESPECIALIDAD: "+especialidad+" |");
            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void mostrarMedicos(){
        String sql = "SELECT medico_id, (datos_personales).nombre, (datos_personales).edad, (medico_info).codigo_medico, (medico_info).especialidad FROM objetos.medicos";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                int id2 = rs.getInt("medico_id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String codigo = rs.getString("codigo_medico");
                String especialidad = rs.getString("especialidad");

                System.out.println("| ID: "+id2+" | NOMBRE: "+nombre+" | EDAD: "+edad+" | CODIGO MEDICO: "+codigo+" | ESPECIALIDAD: "+especialidad+" |");
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void citasDePaciente(int id){
        String sql = "SELECT (pacientes.datos_personales).nombre AS \"nombre paciente\", citasMedicas.fecha_hora, (medicos.datos_personales).nombre AS \"nombre medico\" FROM objetos.pacientes JOIN objetos.citasMedicas ON pacientes.paciente_id = citasMedicas.paciente_id JOIN objetos.medicos ON citasMedicas.medico_id = medicos.medico_id WHERE pacientes.paciente_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                String nombre = rs.getString("nombre paciente");
                Date fecha = rs.getDate("fecha_hora");
                String nombreMed = rs.getString("nombre medico");

                System.out.println("| PACIENTE: "+nombre+" | FECHA: "+fecha+" | MEDICO: "+nombreMed);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void citasDeMedicos(int id){
        String sql = "SELECT (medicos.datos_personales).nombre AS \"nombre medico\", citasMedicas.fecha_hora, (pacientes.datos_personales).nombre AS \"nombre paciente\" FROM objetos.citasMedicas JOIN objetos.medicos ON medicos.medico_id = citasMedicas.medico_id JOIN objetos.pacientes ON citasMedicas.paciente_id = pacientes.paciente_id WHERE medicos.medico_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                String nombreMed = rs.getString("nombre medico");
                Date fecha = rs.getDate("fecha_hora");
                String nombrePac = rs.getString("nombre paciente");

                System.out.println("| MEDICO:"+nombreMed+" | FECHA: "+fecha+" | PACIENTE: "+nombrePac+" |");
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void examenesMedicosPaciente(int id){
        String sql = "SELECT (pacientes.datos_personales).nombre AS \"nombre paciente\", (examen_info).nombre_examen, (examen_info).fecha_realizacion, (examen_info).resultados FROM objetos.examenesMedicos JOIN objetos.pacientes ON examenesMedicos.paciente_id = pacientes.paciente_id WHERE examenesMedicos.paciente_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                String nombre = rs.getString("nombre paciente");
                String nombreEx = rs.getString("mombre_examen");
                Date fecha = rs.getDate("fecha_realizacion");
                String resultado = rs.getString("resultado");

                System.out.println("| PACIENTE: "+nombre+" | EXAMEN: "+nombreEx+" | FECHA: "+fecha+" | RESULTADO: "+resultado+" |");
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void filtrarPorSangre(String grupo_sanguineo){
        String sql = "SELECT (datos_personales).nombre AS \"nombre paciente\", (paciente_info).grupo_sanguineo as \"tipo de sangre\" FROM objetos.pacientes WHERE (paciente_info).grupo_sanguineo = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, grupo_sanguineo);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                String nombre1 = rs.getString("nombre paciente");
                String sangre = rs.getString("tipo de sangre");

                System.out.println("| PACIENTE: "+nombre1+" | GRUPO SANGUINEO: "+sangre+" |");
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
