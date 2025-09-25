package SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDate2 {
    private Connection connection;

    public BaseDate2(){
        startConection();
    }

    private void startConection() {
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

    public List<String> SelectAllNameForChar(String letra){ // ← Añadir parámetro
        List<String> datos = new ArrayList<>();
        String sql = "SELECT * FROM empleado WHERE Nombre LIKE ?";

        try(PreparedStatement ps = connection.prepareStatement(sql)){ // ← Usar PreparedStatement
            ps.setString(1, letra + "%"); // ← Añadir el %

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    String linea = "| NSS: "+rs.getInt("NSS")+
                            " | NOMBRE: "+rs.getString("Nombre")+
                            " | APELLIDO 1: "+rs.getString("Apel1")+
                            " | APELLIDO 2: "+rs.getString("Apel2")+
                            " | SEXO: "+rs.getString("Sexo")+
                            " | DIRECCION: "+rs.getString("Dirección")+
                            " | NACIMIENTO: "+rs.getDate("Fechanac")+
                            " | SALARIO: "+rs.getInt("Salario")+
                            " | Nº DEPARTAMENTO: "+rs.getInt("Numdept")+
                            " | NSSsup: "+rs.getString("NSSsup");
                    datos.add(linea);
                }
            }
        }catch (SQLException e){
            System.out.println("Error en SelectAllNameForChar: " + e.getMessage());
        }
        return datos;
    }

    public void DeleteEmpForNum(int num){
        String sql = "DELETE FROM empleado WHERE NSS = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,num);
            ps.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
