import java.sql.*;

public class BaseDate9 {

    private String user = "root";
    private String password = "ricardoBD90-";
    private String url = "jdbc:mysql://localhost:3306/library";
    private Connection connection;

    public void startConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public BaseDate9(){
        startConnection();
    }

    public boolean existsClient(int idClient){
        int contador = 0;
        String sql = "SELECT * FROM client WHERE idClient = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, idClient);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                contador++;
            }
            return (contador > 0);
            /*
             * O en vez del while solo haz un |return rs.next()| el cual da true si hay al menos una coincidencia
             */
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean existsBook(int idBook) throws SQLException {
        String sql = "SELECT * FROM book WHERE idBook = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, idBook);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean esBorrowed(String code){
        String sql = "";
        try{

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
