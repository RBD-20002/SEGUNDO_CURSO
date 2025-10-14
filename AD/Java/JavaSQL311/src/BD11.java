import java.sql.*;

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

    public void insertAccountBank(float dinero, String dni){
        String sql1 = "SELECT id FROM cliente WHERE = dni = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql1)){
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            int id = rs.getInt("id");

            String sql2 = "INSERT INTO cuenta_bancaria(dinero, id_cliente) VALUES (?,?)";
            try(PreparedStatement ps2 = connection.prepareStatement(sql2)){
                ps.setFloat(1, dinero);
                ps.setInt(2,id);
                ps.executeUpdate();

                System.out.println("SE AGREGO LA CUENTA CORRECTAMENTE");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("FALLO AL AGREGAR LA CUENTA");
        }
    }

    public void deleteForDni(String dni){
        String sql1 = "SELECT cuenta_bancaria.numero, cuenta_bancaria.dinero FROM cuenta_bancaria JOIN cliente ON cliente.id = cuenta_bancaria.id_cliente WHERE cliente.dni = ?";
        String sql2 = "DELETE FROM cuenta_bancaria WHERE nummero = ?";

        try(PreparedStatement ps = connection.prepareStatement(sql1)){
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            System.out.println("CUENTAS REGISTRADAS: ");
            while (rs.next()){
                int idNum = rs.getInt("numero");
                float dinero = rs.getFloat("dinero");
                System.out.println("|NUMERO DE CUENTA: "+idNum+" | DINERO: "+dinero);
            }
            try(PreparedStatement ps2 = connection.prepareStatement(sql2)){
                int numDelete = ED.leerInt("numero de cuenta a eliminar");
                ps2.setInt(1, numDelete);
                ps2.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
