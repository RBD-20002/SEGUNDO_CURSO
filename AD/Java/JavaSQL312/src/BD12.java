import java.sql.*;

public class BD12 {

    private String url = "jdbc:mysql://localhost:3306/libros";
    private String user = "root";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD12(){
        openConnection();
    }

    public void openConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarLibrosStock(){
        String sql = "CALL pa_libros_limite_stock()";
        try(CallableStatement cs = connection.prepareCall(sql)){
            ResultSet rs = cs.executeQuery();

            System.out.println("resultado:".toUpperCase());
            while (rs.next()){
                int codigo = rs.getInt("codigo");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");

                System.out.println("| CODIGO: "+codigo+" | TITULO: "+titulo+" | AUTOR: "+autor+" | EDITORIAL: "+editorial+" | PRECIO: "+precio+" | STOCK: "+stock+" |");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void filtrarLibro(int codigo){
        String sql = "CALL get_libro_by_id(?)";
        try(CallableStatement cs = connection.prepareCall(sql)){
            cs.setInt(1, codigo);
            ResultSet rs = cs.executeQuery();

            if(rs.next()){
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String editorial = rs.getString("editorial");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");

                System.out.println("| CODIGO: "+codigo+" | TITULO: "+titulo+" | AUTOR: "+autor+" | EDITORIAL: "+editorial+" | PRECIO: "+precio+" | STOCK: "+stock+" |");
            }else{
                System.out.println("no se encontro libro con esa id: ".toUpperCase()+codigo);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void contarLibrosPrecio(double precio){
        String sql = "CALL contar_libros_precio(?,?)";
        try(CallableStatement cs = connection.prepareCall(sql)){
            cs.setDouble(1, precio);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.executeUpdate();

            System.out.println(cs.getInt(2));
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
