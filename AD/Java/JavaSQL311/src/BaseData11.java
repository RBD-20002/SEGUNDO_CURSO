import java.sql.*;

public class BaseData11 {

    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/tienda";
    private String user = "root";
    private String password = "ricardoBD90-";

    public BaseData11(){
        startConnection();
    }

    public void startConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean ExistsClient(String dni){
        String sql = "SELECT * FROM cliente WHERE dni = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,dni);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean ExistsCountBank(String dni){
        String sql = "SELECT COUNT(*) AS total FROM cuenta_bancaria WHERE id_cliente = (SELECT id from cliente WHERE dni = ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,dni);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt("total")>0;
            }
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean CreateAccountBank(int id_cliente, float dinero){
        String sql = "INSERT INTO cuenta_bancaria(id_cliente,dinero) VALUES (?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id_cliente);
            ps.setFloat(2, dinero);

            return (ps.executeUpdate() > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean DeleteAccountBank(int id_cliente){
        String sql2 = "DELETE FROM cuenta_bancaria WHERE id_cliente = ?";
        try(PreparedStatement ps2 = connection.prepareStatement(sql2)){
            ps2.setInt(1, id_cliente);
            return (ps2.executeUpdate() > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void DepositMoney(int numero, float dinero){
        String sql = "UPDATE cuenta_bancaria SET dinero = dinero + ? WHERE numero = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setFloat(1, dinero);
            ps.setInt(2,numero);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void WithMoney(int numero, float dinero){
        String sql = "UPDATE cuenta_bancaria SET dinero = dinero - ? WHERE numero = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setFloat(1, dinero);
            ps.setInt(2, numero);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean MakeTransfer(int num1, int num2, float dinero){
        try{
            connection.setAutoCommit(false);
            boolean existe = ExistsCountBank();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void SelectAccountBankForDni(String dni){
        String sql = "SELECT cb.numero, cb.id_cliente, cb.dinero FROM cuenta_bancaria cb JOIN cliente c ON cb.id_cliente = c.id WHERE c.dni = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int numero = rs.getInt("numero");
                int id_cliente = rs.getInt("id_cliente");
                float dinero = rs.getFloat("dinero");

                System.out.println("| NUMERO DE CUENTA: "+numero+" | ID CLIENTE: "+id_cliente+" | DINERO: "+dinero);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
