import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD11 {

    private String url = "jdbc:mysql://localhost:3306/tienda";
    private String user = "root";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD11(){
        openConnection();
    }

    public void openConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
