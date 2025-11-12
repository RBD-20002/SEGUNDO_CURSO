import java.sql.*;

public class BD8 {

    private String url = "jdbc:postgresql://localhost:5432/academia";
    private String user = "postgres";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD8(){
        openConnection();
    }

    public void openConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void crearSchema(){
        String psql = "CREATE SCHEMA academiaTeis";
        try(Statement st = connection.createStatement()){
            st.executeUpdate(psql);
            System.out.println("SE CREO EL SCHEMA 'academiaTeis'");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void crearType(){
        String psql = "CREATE TYPE academiaTeis.instituto AS(nombre VARCHAR(40), numero_profesores INTEGER, presupuesto NUMERIC(10,2))";
        try(Statement st = connection.createStatement()){
            st.executeUpdate(psql);
            System.out.println("SE CREO EL TYPE instituto");
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void insertInscripcion(int curso_id, int estudiante_id, Date fecha_inscripcion){
        String sql = "INSERT INTO Inscripciones(curso_id,estudiante_id,fecha_inscripcion) VALUES (?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,curso_id);
            ps.setInt(2,estudiante_id);
            ps.setDate(3,fecha_inscripcion);

            int contador = ps.executeUpdate();
            if(contador > 0) System.out.println("Se agrego inscripcion");
            else System.out.println("Fallo al insertar inscripcion");

        }catch(SQLException e ){
            System.err.println(e.getMessage());
        }
    }

    public void modificarEmail(String email, int estudiante_id){
        String psql = "UPDATE Estudiantes SET info_estudiante.email = ? WHERE estudiante_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(psql)){
            ps.setString(1,email);
            ps.setInt(2,estudiante_id);

            int contador = ps.executeUpdate();
            if(contador > 0) System.out.println("Se modifico el email del estudiante");
            else System.out.println("Fallo al modificar email");

        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void eliminarDescripcion(int curso_id){
        String psql = "UPDATE Cursos SET descripcion = NULL WHERE curso_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(psql)){
            ps.setInt(1,curso_id);

            int contador = ps.executeUpdate();
            if(contador > 0) System.out.println("Se elimino descripcion del curso");
            else System.out.println("No se elimino nada");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarEstudiantes(){
        String psql = "SELECT estudiante_id, (info_estudiante).nombre, (info_estudiante).edad FROM Estudiantes";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(psql)){

            while(rs.next()){
                int id = rs.getInt("estudiante_id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");

                System.out.println("ID: "+id+" | NOMBRE: "+nombre+" | EDAD: "+edad);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void promedioEdad(){
        String psql = "SELECT ROUND(AVG((info_estudiante).edad),2) AS promedio FROM Estudiantes";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(psql)){
            if(rs.next()) {
                double promedio = rs.getDouble("promedio");
                System.out.println("PROMEDIO EDAD: " + promedio);
            }
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta3(){
        String psql = "SELECT c.curso_id, c.nombre_curso, c.precio, p.nombre FROM Cursos c JOIN Profesores p ON c.profesor_id = p.profesor_id WHERE c.precio > 180";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(psql)){
            while (rs.next()){
                int id = rs.getInt("curso_id");
                String curso = rs.getString("nombre_curso");
                double precio = rs.getDouble("precio");
                String profesor = rs.getString("nombre");

                System.out.println("ID: "+id+" | CURSO: "+curso+" | PRECIO: "+precio+" | PROFESOR: "+profesor);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta4(){
        String psql = "SELECT c.nombre_curso, c.descripcion FROM Cursos c JOIN Inscripciones i ON c.curso_id = i.curso_id JOIN Estudiantes e ON e.estudiante_id = i.estudiante_id WHERE (e.info_estudiante).edad > 28";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(psql)){
            while (rs.next()){
                String curso = rs.getString("nombre_curso");
                String descripcion = rs.getString("descripcion");

                System.out.println("CURSO: "+curso+" | DESCRIPCION: "+descripcion);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta5(){
        String psql = "SELECT (e.info_estudiante).nombre, COUNT(i.estudiante_id) AS cantidad FROM Cursos c JOIN Inscripciones i ON c.curso_id = i.curso_id JOIN Estudiantes e ON e.estudiante_id = i.estudiante_id GROUP BY i.estudiante_id, (e.info_estudiante).nombre";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(psql)){
            while (rs.next()){
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");

                System.out.println("NOMBRE: "+nombre+" | CANTIDAD CURSO: "+cantidad);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}