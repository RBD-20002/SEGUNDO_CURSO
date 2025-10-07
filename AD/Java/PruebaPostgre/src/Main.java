import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection conn;

        try{
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Prueba",
                    "postgres",
                    "ricardoBD90-"
            );
            System.out.println("FUNCIONO");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
