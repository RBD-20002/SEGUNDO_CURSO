package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDate3 {
    private Connection connection;

    private void startConnection() throws SQLException {
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/empleados",
                    "root",
                    "ricardoBD90-"
            );
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    public boolean AddEmployeesForTransaction(){
        boolean completado = false;
        String sql = "as";
        
    }
}
