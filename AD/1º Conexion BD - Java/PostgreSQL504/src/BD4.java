import java.sql.*;

public class BD4 {

    private String urlBase = "jdbc:postgresql://localhost:5432/";
    private String user = "postgres";
    private String password = "ricardoBD90-";
    private Connection connection;


    public BD4(){
        openConnection(urlBase);
    }


    public void openConnection(String url){
        try{
            connection = DriverManager.getConnection(urlBase,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void crearBD(){
        try{
            Statement st = connection.createStatement();
            st.executeUpdate("DROP DATABASE uni WITH (FORCE)");
            System.out.println("base datos eliminado por siacaso".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        try(Statement st = connection.createStatement()){
            st.executeUpdate("CREATE DATABASE uni");
            System.out.println("se creo la bd uni".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        String urlUni = urlBase+"uni";
        openConnection(urlUni);

        try(Statement st = connection.createStatement()){
            st.executeUpdate("DROP SCHEMA objetos CASCADE");
            st.executeUpdate("CREATE SCHEMA objetos");

            st.executeUpdate("CREATE TYPE objetos.persona AS (nombre varchar(50), edad integer)");
            st.executeUpdate("CREATE TYPE objetos.profesor AS (cedula varchar(9), departamento varchar(20))");
            st.executeUpdate("CREATE TYPE objetos.estudiante AS (matricula varchar(20), carrera varchar(50))");

            st.executeUpdate("CREATE TABLE objetos.estudiantes(estudiante_id serial, datos_personales objetos.persona, estudiante_info objetos.estudiante, PRIMARY KEY (estudiante_id))");
            st.executeUpdate("CREATE TABLE objetos.cursos(curso_id serial, nombre varchar(50), PRIMARY KEY (curso_id))");
            st.executeUpdate("CREATE TABLE objetos.inscripciones(inscripcion_id serial, estudiante_id integer, curso_id integer, PRIMARY KEY (inscripcion_id), FOREIGN KEY (estudiante_id) REFERENCES objetos.estudiantes(estudiante_id) ON DELETE CASCADE, FOREIGN KEY (curso_id) REFERENCES objetos.cursos(curso_id) ON DELETE CASCADE)");
            st.executeUpdate("CREATE TABLE objetos.profesores(profesor_id serial, datos_personales objetos.persona, profesor_info objetos.profesor, curso_id integer, PRIMARY KEY (profesor_id), FOREIGN KEY (curso_id) REFERENCES objetos.cursos(curso_id) ON DELETE SET NULL)");

            // INSERTS DE PRUEBA
            st.executeUpdate("INSERT INTO objetos.estudiantes (datos_personales, estudiante_info) VALUES (ROW('Juan Pérez', 20), ROW('2021001', 'ingeniería informática'))");
            st.executeUpdate("INSERT INTO objetos.estudiantes (datos_personales, estudiante_info) VALUES (ROW('María Gómez', 22), ROW('2021002', 'psicología'))");
            st.executeUpdate("INSERT INTO objetos.profesores (datos_personales, profesor_info) VALUES (ROW('Carlos Rodríguez', 40), ROW('PR001', 'matemáticas'))");
            st.executeUpdate("INSERT INTO objetos.profesores (datos_personales, profesor_info) VALUES (ROW('Ana Martínez', 35), ROW('PR002', 'historia'))");
            st.executeUpdate("INSERT INTO objetos.cursos (nombre) VALUES ('álgebra lineal')");
            st.executeUpdate("INSERT INTO objetos.cursos (nombre) VALUES ('historia del arte')");
            st.executeUpdate("INSERT INTO objetos.inscripciones (estudiante_id, curso_id) VALUES (1, 1)");
            st.executeUpdate("INSERT INTO objetos.inscripciones (estudiante_id, curso_id) VALUES (2, 2)");
            st.executeUpdate("INSERT INTO objetos.estudiantes (datos_personales, estudiante_info) VALUES (ROW('Laura Torres', 21), ROW('2021003', 'biología'))");
            st.executeUpdate("INSERT INTO objetos.estudiantes (datos_personales, estudiante_info) VALUES (ROW('Eduardo López', 23), ROW('2021004', 'ingeniería civil'))");
            st.executeUpdate("INSERT INTO objetos.cursos (nombre) VALUES ('programación en c')");
            st.executeUpdate("INSERT INTO objetos.cursos (nombre) VALUES ('historia antigua')");
            st.executeUpdate("INSERT INTO objetos.profesores (datos_personales, profesor_info, curso_id) VALUES (ROW('Isabel Fernández', 37), ROW('PR003', 'informática'), 3)");
            st.executeUpdate("INSERT INTO objetos.profesores (datos_personales, profesor_info, curso_id) VALUES (ROW('Luis Sánchez', 45), ROW('PR004', 'historia del arte'), 4)");
            st.executeUpdate("INSERT INTO objetos.inscripciones (estudiante_id, curso_id) VALUES (3, 3)");
            st.executeUpdate("INSERT INTO objetos.inscripciones (estudiante_id, curso_id) VALUES (4, 4)");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*METODOS PRINCIPALES*/
    /*FUNCIONA EN PGADMIN4*/
    public void filtrarEstudiente(int id){
        String sql = "SELECT estudiante_id, (datos_personales).nombre, (datos_personales).edad, (estudiante_info).matricula, (estudiante_info).carrera FROM objetos.estudiantes WHERE estudiante_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultado:".toUpperCase());
            while (rs.next()){
                int est_id = rs.getInt("estudiante_id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String matricula = rs.getString("matricula");
                String carrera = rs.getString("carrera");

                System.out.println("| ID: "+est_id+" | NOMBRE: "+nombre+" | EDAD: "+edad+" | MATRICULA: "+matricula+" | CARRERA: "+carrera+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void mostrarEstudiantes(){
        String sql = "SELECT estudiante_id, (datos_personales).nombre, (datos_personales).edad, (estudiante_info).matricula, (estudiante_info).carrera FROM objetos.estudiantes";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                int id = rs.getInt("estudiante_id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String matricula = rs.getString("matricula");
                String carrera = rs.getString("carrera");

                System.out.println("| ID: "+id+" | NOMBRE: "+nombre+" | EDAD: "+edad+" | MATRICULA: "+matricula+" | CARRERA: "+carrera+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void filtrarCurso(int id){
        String sql = "SELECT curso_id, nombre FROM objetos.cursos WHERE curso_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultado:".toUpperCase());
            while (rs.next()){
                int cur_id = rs.getInt("curso_id");
                String nombre = rs.getString("nombre");

                System.out.println("| ID CURSO: "+cur_id+" | NOMBRE: "+nombre+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void mostrarCursos(){
        String sql = "SELECT curso_id, nombre FROM objetos.cursos";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                int id = rs.getInt("curso_id");
                String nombre = rs.getString("nombre");

                System.out.println("| ID: "+id+" | NOMBRE: "+nombre+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void filtrarProfesor(int id){
        String sql = "SELECT profesor_id, (datos_personales).nombre, (datos_personales).edad, (profesor_info).cedula, (profesor_info).departamento FROM objetos.profesores WHERE profesor_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultado:".toUpperCase());
            while (rs.next()){
                int prof_id = rs.getInt("profesor_id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String cedula = rs.getString("cedula");
                String departamento = rs.getString("departamento");

                System.out.println("| ID: "+id+" | NOMBRE: "+nombre+" | EDAD: "+edad+" | CEDULA: "+cedula+" | DEPARTAMENTO: "+departamento);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void mostrarProfesores(){
        String sql = "SELECT profesor_id, (datos_personales).nombre, (datos_personales).edad, (profesor_info).cedula, (profesor_info).departamento FROM objetos.profesores";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                int id = rs.getInt("profesor_id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String cedula = rs.getString("cedula");
                String departamento = rs.getString("departamento");

                System.out.println("| ID: "+id+" | NOMBRE: "+nombre+" | EDAD: "+edad+" | CEDULA: "+cedula+" | DEPARTAMENTO: "+departamento+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void estudainteMatricula(){
        String sql = "SELECT (datos_personales).nombre, (estudiante_info).matricula FROM objetos.estudiantes";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                String nombre = rs.getString("nombre");
                String matricula = rs.getString("matricula");

                System.out.println("| NOMBRE: "+nombre+" | MATRICULA: "+matricula+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void cursosConEstudiantes(){
        String sql = "SELECT cursos.nombre, COUNT(inscripciones.estudiante_id) AS \"cantidad de estudiantes\" FROM objetos.cursos JOIN objetos.inscripciones ON cursos.curso_id = inscripciones.curso_id GROUP BY cursos.curso_id, cursos.nombre";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad de estudiantes");

                System.out.println("| NOMBRE: "+nombre+" | CANTIDAD: "+cantidad+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void estudiantesPorCarrerra(){
        String sql = "SELECT (estudiante_info).carrera, COUNT(estudiante_id) AS \"cantidad\" FROM objetos.estudiantes GROUP BY (estudiante_info).carrera";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados: ".toUpperCase());
            while (rs.next()){
                String carrera = rs.getString("carrera");
                int contador = rs.getInt("cantidad");

                System.out.println("| CARRERA: "+carrera+" | CANTIDAD DE ALUMNOS: "+contador+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*ESTUDIANTES*/
    /*FUNCIONA EN PGADMIN4*/
    public void insertEstudiante(String nombre, int edad, String matricula, String carrera){
        String sql = "INSERT INTO objetos.estudiantes(datos_personales, estudiante_info) VALUES (ROW(?,?),ROW(?,?))";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nombre);
            ps.setInt(2,edad);
            ps.setString(3,matricula);
            ps.setString(4,carrera);

            ps.executeUpdate();
            System.out.println("se agrego correctamente al estudiante".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void deleteEstudiante(int id){
        String sql = "DELETE FROM objetos.estudiantes WHERE estudiante_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("estudiante eliminado correctamente".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    /*FUNCIONA EN PGADMIN4*/
    public void updateEstudiante(int id, String nombre, int edad, String matricula, String carrera){
        String sql = "UPDATE objetos.estudiantes SET datos_personales =ROW(?,?), estudiante_info = ROW(?,?) WHERE estudiante_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setInt(2,edad);
            ps.setString(3,matricula);
            ps.setString(4,carrera);
            ps.setInt(5,id);

            ps.executeUpdate();
            System.out.println("datos de estudiante modificados".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*PROFESORES*/
    /*FUNCIONA EN PGADMIN4*/
    public void insertProfesor(String nombre, int edad, String cedula, String departamento){
        String sql = "INSERT INTO objetos.profesores(datos_personales,profesor_info) VALUES(ROW(?,?),ROW(?,?))";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setInt(2,edad);
            ps.setString(3,cedula);
            ps.setString(4,departamento);

            ps.executeUpdate();
            System.out.println("se agrego correctamente al profesor".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void deleteProfesor(int id){
        String sql = "DELETE FROM objetos.profesores WHERE profesor_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);

            ps.executeUpdate();
            System.out.println("profesor eliminado correctamente".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void updateProfesor(int id, String nombre, int edad, String cedula, String departamento){
        String sql = "UPDATE objetos.profesores SET datos_personales = ROW(?,?), profesor_info = ROW(?,?) WHERE profesor_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setInt(2,edad);
            ps.setString(3,cedula);
            ps.setString(4,departamento);
            ps.setInt(5,id);

            ps.executeUpdate();
            System.out.println("datos de profesor modificados".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*CURSOS*/
    /*FUNCIONA EN PGADMIN4*/
    public void insertCurso(String nombre){
        String sql = "INSERT INTO objetos.cursos(nombre) VALUES(?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);

            ps.executeUpdate();
            System.out.println("curso agregado correctamente".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    /*FUNCIONA EN PGADMIN4*/
    public void deleteCurso(int id){
        String sql = "DELETE FROM objetos.cursos WHERE curso_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,id);

            ps.executeUpdate();
            System.out.println("curso eliminado correctamente".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void updateCurso(int id, String nombre){
        String sql = "UPDATE objetos.cursos SET nombre = ? WHERE curso_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,nombre);
            ps.setInt(2,id);

            ps.executeUpdate();
            System.out.println("datos de curso modificados".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*INSCRIPCION ALUMNO*/
    /*FUNCIONA EN PGADMIN4*/
    public void inscribirAlumno(int estudiante_id, int curso_id){
        String sql = "INSERT INTO objetos.Inscripciones (estudiante_id, curso_id) VALUES (?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,estudiante_id);
            ps.setInt(2,curso_id);

            ps.executeUpdate();
            System.out.println("inscripcion estudiante correctamente".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void desinscribirAlumno(int estudiante_id, int curso_id){
        String sql = "DELETE FROM objetos.inscripciones WHERE estudiante_id = ? AND curso_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,estudiante_id);
            ps.setInt(2,curso_id);

            ps.executeUpdate();
            System.out.println("desinscripcion estudiante correctamente".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*INSCRIPCION PROFESOR*/
    /*FUNCIONA EN PGADMIN4*/
    public void inscribirProfesor(int profesor_id, int curso_id){
        String sql = "UPDATE objetos.profesores set curso_id = ? WHERE profesor_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,curso_id);
            ps.setInt(2,profesor_id);

            ps.executeUpdate();
            System.out.println("inscripcion profesor correctamente".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*FUNCIONA EN PGADMIN4*/
    public void desinscribirProfesor(int profesor_id, int curso_id){
        String sql = " UPDATE objetos.profesores SET curso_id = ? WHERE profesor_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,profesor_id);
            ps.setInt(2,curso_id);

            ps.executeUpdate();
            System.out.println("desinscripcion profesor correctamente".toUpperCase());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*METODOS PARA VER*/
    /*FUNCIONA EN PGADMIN4*/
    public void mostrarInscripciones(){
        String sql = "SELECT inscripcion_id, estudiante_id, curso_id FROM objetos.inscripciones";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while(rs.next()){
                int ins_id = rs.getInt("inscripcion_id");
                int est_id = rs.getInt("estudiante_id");
                int cur_id = rs.getInt("curso_id");

                System.out.println("| ID INSCRIPCION: "+ins_id+" | ID ESTUDIANTE: "+est_id+" | ID CURSO: "+cur_id+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
