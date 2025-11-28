import java.sql.*;

public class Modelo {

    private Connection connection;

    public Modelo(){
        startConnection();
    }

    private void startConnection(){
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/empleados",
                    "root",
                    "ricardoBD90-"
            );
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String selectEmpleadoPorId(String numEmpleado){
        String resultado = "";
        String sql = "SELECT * FROM empleado WHERE NSS = "+numEmpleado;
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                resultado = "NSS: " + rs.getInt("NSS") +
                        "\nNombre: " + rs.getString("Nombre") +
                        "\nApellido 1: " + rs.getString("Apel1") +
                        "\nApellido 2 : " + rs.getString("Apel2") +
                        "\nSexo: " + rs.getString("Sexo") +
                        "\nDirección: " + rs.getString("Dirección") +
                        "\nFecha nacimiento: " + rs.getString("Fechanac") +
                        "\nSalario: " + rs.getString("Salario") +
                        "\nNúm. departamento: " + rs.getString("Numdept") +
                        "\nNSSsup: " + rs.getString("NSSsup");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
}
