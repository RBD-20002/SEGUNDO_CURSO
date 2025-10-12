import java.sql.*;

public class BD4 {

    private String url = "jdbc:mysql://localhost:3306/empleados";
    private String user = "root";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD4(){
        startConnection();
    }

    public void startConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void transForEmp(int numVeces, String nss, String nombre, String apel1, String apel2, String sexo, String direccion, java.util.Date fecha, int salario, int numDep, String nssUp){
        String sql = "INSERT INTO empleado(NSS,Nombre,Apel1,Apel2,Sexo,Dirección,Fechanac,Salario,Numdept,NSSsup) VALUES (?,?,?,?,?,?,?,?,?,?)";
        int contador = 0;
        try{
            connection.setAutoCommit(false);
            for(int i=0; i<numVeces; i++){
                try(PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setString(1, nss);
                    ps.setString(2, nombre);
                    ps.setString(3, apel1);
                    ps.setString(4, apel2);
                    ps.setString(5, sexo);
                    ps.setString(6, direccion);
                    ps.setDate(7, (Date) fecha);
                    ps.setInt(8, salario);
                    ps.setInt(9, numDep);
                    ps.setString(10, nssUp);
                    ps.executeUpdate();

                    contador++;
                    System.out.println("insercion ".toUpperCase()+(contador+1)+" exitosa".toUpperCase());
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
            connection.commit();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
