import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class BaseDateDAO implements MetodosSQL{
    Connection connection;

    public void startConection() throws SQLException{
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/empleados",
                    "root",
                    "ricardoBD90-"
            );
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void selectInfoDataBase(){
    }

    @Override
    public void selectComandTable(){
        List<> datos 
        String sql = "SELECT * FROM ?";
    }

    @Override
    public void insertProjectNew(){}

    @Override
    public void deleteProyect(int numProy){
    }
}
