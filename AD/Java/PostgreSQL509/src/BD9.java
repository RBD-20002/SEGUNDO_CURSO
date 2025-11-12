import java.sql.*;

public class BD9 {

    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/";
    private String user = "postgres";
    private String password = "ricardoBD90-";

    public BD9(){
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
            System.out.println("SE CREO EL SCHEMA");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void crearType(){
        String psql = "CREATE TYPE academiaTeis.instituto AS (nombre VARCHAR(40), numero_profesores INTEGER, presupuesto NUMERIC(10,2))";
        try(Statement st = connection.createStatement()){
            st.executeUpdate(psql);
            System.out.println("SE CREO EL TYPE instituto");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void crearInsercion(int curso_id, int estudiante_id, Date fecha){
        String psql = "INSERT INTO Inscripciones(curso_id, estudiante_id, fecha_inscripcion) VALUES (?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(psql)){
            ps.setInt(1,curso_id);
            ps.setInt(2,estudiante_id);
            ps.setDate(3,fecha);

            if(ps.executeUpdate() > 0) System.out.println("INSCRIPCION HECHA CORRECTAMENTE");
        }catch (SQLException e){
            System.out.println("FALLO AL HACER INSCRIPCION");
            System.err.println(e.getMessage());
        }
    }
}
