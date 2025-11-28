import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD3 {

    private String url = "jdbc:mysql://localhost:3306/empleados";
    private String user = "root";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD3(){
        startConnection();
    }

    public void startConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<String> selectForLetra(String letra){
        List<String> datos = new ArrayList<>();
        String sql = "SELECT * FROM empleado WHERE Nombre LIKE ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, letra+"%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String linea ="NSS: "+rs.getString("NSS")
                        +"\nNombre: "+rs.getString("Nombre")
                        +"\nPrimer Apellido: "+rs.getString("Apel1")
                        +"\nSegundo Apellido: "+rs.getString("Apel2")
                        +"\nSexo: "+rs.getString("Sexo")
                        +"\nDireccion: "+rs.getString("Dirección")
                        +"\nFecha Nacimiento: "+rs.getDate("Fechanac")
                        +"\nSalario: "+rs.getInt("Salario")
                        +"\nNumero Departamento: "+rs.getInt("Numdept")
                        +"\nNSS 2: "+rs.getString("NSSsup");

                datos.add(linea);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return datos;
    }

    public void deleteForNum(String num){
        String sql = "DELETE FROM empleado WHERE nss = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, num);
            ps.execute();

            System.out.println("SE ELIMINO CORRECTAMENTE");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
