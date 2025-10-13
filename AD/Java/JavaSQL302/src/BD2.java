import java.sql.*;

public class BD2 {

    private String url = "jdbc:mysql://localhost:3306/empleados";
    private String user = "root";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD2(){
        startConnection();
    }

    public void startConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void selectInfoBD(){
        try{
            DatabaseMetaData dm = connection.getMetaData();
            String gestor = dm.getDatabaseProductName();
            String user = dm.getUserName();
            String url = dm.getURL();

            System.out.println("GESTOR: "+gestor+
                    "\nUSER: "+user+
                    "\nURL: "+url);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /*Statement */
    public void selectForProject(){
        String sql = "SELECT * FROM proyecto";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            while (rs.next()){
                int numProy = rs.getInt("Numproy");
                String nombreProy = rs.getString("Nombreproy");
                String lugarProy = rs.getString("Lugarproy");
                int depart = rs.getInt("departamento_Numdep");

                System.out.println("NUMERO PROYECTO: "+numProy+" | NOMBRE PROYECTO: "+nombreProy+" | LUGAR PROYECTO: "+lugarProy+" | DEPARTAMENTO ENCARGADO: "+depart);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertNewProject(int num, String nombre, String lugar, int departamento){
        String sql = "INSERT INTO proyecto (Numproy, Nombreproy, Lugarproy, departamento_Numdep) VALUES (?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, num);
            ps.setString(2, nombre);
            ps.setString(3, lugar);
            ps.setInt(4, departamento);
            ps.executeUpdate();

            System.out.println("SE AGREGO CORRECTAMENTE");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteForNum(int num){
        String sql = "DELETE FROM proyecto WHERE NumProy = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, num);
            ps.executeUpdate();

            System.out.println("SE ELIMINO CORRECTAMENTE");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
