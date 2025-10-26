import java.sql.*;

public class BD2 {

    private static BD2 instancia;
    private String url = "jdbc:postgresql://localhost:5432/formula1";
    private String user = "postgres";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD2(){
        openConnection();
    }

    public static BD2 getInstance(){
        if(instancia == null){
            instancia = new BD2();
        }
        return instancia;
    }

    public void openConnection(){
        try {
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void listarEquiposDirectores(){
        String sql = "SELECT nombre, director FROM equipos";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                String nombre = rs.getString("nombre");
                String director = rs.getString("director");

                System.out.println("| NOMBRE: "+nombre+" | DIRECTOR: "+director+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarPilotosEquipos(){
        String sql = "SELECT pilotos.nombre AS piloto, equipos.nombre AS \"equipo actual\" FROM equipos JOIN pilotos ON equipos.equipo_id = pilotos.equipo_id";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados".toUpperCase());
            while (rs.next()){
                String piloto = rs.getString("piloto");
                String equipo = rs.getString("equipo actual");

                System.out.println("| PILOTO: "+piloto+" | EQUIPO ACTUAL: "+equipo+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void buscarCarreraEspecifica(int id){
        String sql = "SELECT pilotos.nombre AS \"piloto\", carreras.nombre AS \"carrera\", carreras.fecha, resultados.posicion FROM resultados JOIN pilotos ON resultados.piloto_id = pilotos.piloto_id JOIN carreras ON resultados.carrera_id = carreras.carrera_id WHERE carreras.carrera_id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                String piloto = rs.getString("piloto");
                String carrera = rs.getString("carrera");
                Date fecha = rs.getDate("fecha");
                int posicion = rs.getInt("posicion");

                System.out.println("| PILOTO: "+piloto+" | CARRERA: "+carrera+" | FECHA: "+fecha+" | POSICION: "+posicion+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void pilotoMasViejo(){
        String sql = "SELECT piloto_id, nombre, nacionalidad, fecha_nacimiento FROM pilotos ORDER BY fecha_nacimiento ASC LIMIT 1";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                int id = rs.getInt("piloto_id");
                String nombre = rs.getString("nombre");
                String nacionalidad = rs.getString("nacionalidad");
                String date = String.valueOf(rs.getDate("fecha_nacimiento"));

                System.out.println("| ID: "+id+" | PILOTO: "+nombre+" | NACIONALIDAD: "+nacionalidad+" | FECHA NACIMIENTO: "+date+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void victoriasPorEquipo(){
        String sql = "SELECT equipos.nombre, COUNT(resultados.resultado_id) AS victorias FROM resultados INNER JOIN pilotos ON resultados.piloto_id = pilotos.piloto_id INNER JOIN equipos ON pilotos.equipo_id = equipos.equipo_id WHERE resultados.posicion = 1 GROUP BY equipos.nombre";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("resultados:".toUpperCase());
            while (rs.next()){
                String nombre = rs.getString("nombre");
                int victorias = rs.getInt("victorias");

                System.out.println("| EQUIPO: "+nombre+" | VICTORIAS: "+victorias+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*METODOS ADICIONALES PARA MOSTRAR DATOS*/

    public void mostrarCarreras(){
        String sql = "SELECT * FROM carreras";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            System.out.println("carreras:".toUpperCase());
            while (rs.next()){
                int id = rs.getInt("carrera_id");
                String nombre = rs.getString("nombre");
                String fecha = String.valueOf(rs.getDate("fecha"));

                System.out.println("| ID: "+id+" | CARRERA: "+nombre+" | FECHA: "+fecha+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
