package SQL;

import java.sql.*;
import java.util.Scanner;

public class BaseDate3 {
    private Connection connection;
    static Scanner SC = new Scanner(System.in);

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

    public void insertEmpleado(){
        String sql = "INSERT INTO empleado VALUES (?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,EntradaDatos.leerInt("id"));
            ps.setString(2,EntradaDatos.leerString("nombre"));
            ps.setString(3,EntradaDatos.leerString("primer apellido"));
            ps.setString(4,EntradaDatos.leerString("segundo apellido"));
            ps.setString(5,EntradaDatos.leerString("sexo"));
            ps.setString(6,EntradaDatos.leerString("direccion"));
            ps.setDate(7,EntradaDatos.leerDate("fecha nacimiento"));
            ps.setInt(8,EntradaDatos.leerInt("salario"));
            ps.setInt(9,EntradaDatos.leerInt("numero de departamento"));
            ps.setString(10,EntradaDatos.leerString("NSSsup"));
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void AddEmployeesForTransaction() throws SQLException{
        int num = EntradaDatos.leerInt("numero de empleados a agregar");
        try{
            connection.setAutoCommit(false);
            for(int i=0; i<num; i++){
                System.out.println("Empleado ("+(i+1)+")");
                insertEmpleado();
            }
            connection.commit();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            try{
                connection.rollback();
            }catch (SQLException s){
                System.out.println(s.getMessage());
            }
        }
    }
}
