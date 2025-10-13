import java.sql.*;

public class BD10 {

    private String url = "jdbc:mysql://localhost:3306/tienda";
    private String user = "root";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD10(){
        openConnection();
    }

    public void openConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Client selectForDni(String dni){
        String sql = "SELECT * FROM cliente WHERE dni = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String dniR = rs.getString("dni");
                String nombreR = rs.getString("nombre");

                return new Client(dniR, nombreR);
            }else{
                System.out.println("no hay cliente con ese dni".toUpperCase());
                return null;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void selectForPedido(String dni){
        String sql = "SELECT producto.nombre, producto_pedido.cantidad, (producto.precio * producto_pedido.cantidad) AS total FROM producto JOIN producto_pedido ON producto_pedido.idProducto = producto.id JOIN pedido ON pedido.id = producto_pedido.idPedido WHERE pedido.dniCliente = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                double total = rs.getDouble("total");

                System.out.println("PRODUCTOS: "
                        +"\nNOMBRE: "+nombre
                        +"\nCANTIDAD: "+cantidad
                        +"\nTOTAL: "+total);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertNewPedido(String dni){
        String sql2 = "INSERT INTO producto_pedido(idPedido,idProducto,cantidad) VALUES (?,?,?)";
        String sql = "INSERT INTO pedido(dniCliente,fecha) VALUES (?, NOW())";
        try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dni);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int idPedido = rs.getInt(1);

            while(true){
                int idProd = ED.leerInt("id producto (0 para salir)");
                if(idProd == 0) break;
                int cantidad = ED.leerInt("cantidad");

                try(PreparedStatement ps2 = connection.prepareStatement(sql2)){
                    ps2.setInt(1, idPedido);
                    ps2.setInt(2, idProd);
                    ps2.setInt(3, cantidad);

                    ps2.executeUpdate();
                }
            }
            System.out.println("PEDIDO REGISTRADO");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
