import java.sql.*;

public class BD7 {

    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/colegio";
    private String user = "postgres";
    private String password = "ricardoBD90-";

    public BD7() {
        openConnection();
    }

    public void openConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //METODOS PARA MOSTRAR
    public void mostrarAlumnos() {
        String psql = "SELECT id_alumno, (datos).nombre, (datos).apellido FROM cole.alumnos";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(psql)) {
            while (rs.next()) {
                int id = rs.getInt("id_alumno");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                System.out.println("| ID: " + id + " | NOMBRE: " + nombre + " | APELLIDO: " + apellido);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void mostrarProfesores() {
        String psql = "SELECT id_profesor, (datos).nombre, (datos).apellido FROM cole.profesores";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(psql)) {
            while (rs.next()) {
                int id = rs.getInt("id_profesor");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");

                System.out.println("| ID: " + id + " | NOMBRE: " + nombre + " | APELLIDO: " + apellido);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //METODOS OFICIALES
    public void filtrarAlumno(int id) {
        String psql = "SELECT (datos).nombre, (datos).apellido, (datos).edad, (datos).dni, turno, grupo FROM cole.alumnos WHERE id_alumno = ?";
        try (PreparedStatement ps = connection.prepareStatement(psql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                String dni = rs.getString("dni");
                String turno = rs.getString("turno");
                String grupo = rs.getString("grupo");

                System.out.println("| ID: " + id + " | NOMBRE: " + nombre + " | APELLIDO: " + apellido + " | EDAD: " + edad + " | DNI: " + dni + " | TURNO: " + turno + " | GRUPO: " + grupo);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void filtrarProfesor(int id) {
        String psql = "SELECT (datos).nombre, (datos).apellido, (datos).edad, (datos).dni, cod_profesor FROM cole.profesores WHERE id_profesor = ?";
        try (PreparedStatement ps = connection.prepareStatement(psql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                String dni = rs.getString("dni");
                String cod = rs.getString("cod_profesor");

                System.out.println("| ID: " + id + " | NOMBRE: " + nombre + " | APELLIDO: " + apellido + " | EDAD: " + edad + " | DNI: " + dni + " | CODIGO PROFESOR: " + cod);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void insertAlumno(String nombre, String apellido, int edad, String dni, String direccion, String turno, String grupo) {
        String psql = "INSERT INTO cole.alumnos(datos,turno,grupo) VALUES (ROW(?,?,?,?,?),?,?)";
        try (PreparedStatement ps = connection.prepareStatement(psql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setString(4, dni);
            ps.setString(5, direccion);
            ps.setString(6, turno);
            ps.setString(7, grupo);

            int contador = ps.executeUpdate();
            if (contador > 0) System.out.println("SE AGREGO ALUMNO CORRECTAMENTE");
            else System.out.println("FALLO AL AGREGAR ALUMNO");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void insertProfesor(String nombre, String apellido, int edad, String dni, String cod_profesor) {
        String psql = "INSERT INTO cole.profesores(datos,cod_profesor) VALUES (ROW(?,?,?,?),?)";
        try (PreparedStatement ps = connection.prepareStatement(psql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setString(4, dni);
            ps.setString(5, cod_profesor);
            int contador = ps.executeUpdate();
            if (contador > 0) System.out.println("SE AGREGO PROFESOR");
            else System.out.println("FALLO EL AGREGADO");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void mediaNota(int id) {
        String psql = "SELECT (a.datos).nombre,  ROUND(AVG(n.nota),2) AS promedio FROM cole.alumnos a JOIN cole.notas n ON a.id_alumno = n.id_alumno WHERE n.id_alumno = ?";
        try (PreparedStatement ps = connection.prepareStatement(psql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("NO SE ENCONTRO NOTAS CON ESE ALUMNOS");
                return;
            }
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double promedio = rs.getDouble("promedio");

                System.out.println("| ALUMNO: " + nombre + " | PROMEDIO: " + promedio);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void elimnarAlumno(int id){
        String psql = "DELETE FROM cole.alumnos WHERE id_alumno = ?";
        try(PreparedStatement ps = connection.prepareStatement(psql)){
            ps.setInt(1,id);
            int contador = ps.executeUpdate();
            if(contador>0) System.out.println("SE ELIMINO ALUMNO");
            else System.out.println("FALLO AL ELIMINAR ALUMNO");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
