import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BD9 {

    private String url = "jdbc:mysql://localhost:3306/library";
    private String user = "root";
    private String password = "ricardoBD90-";
    private Connection connection;

    public BD9(){
        openConnection();
    }

    public void openConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean existsClient(int idClient){
        String sql = "SELECT * FROM client WHERE idClient = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, idClient);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean existsBook(int idBook){
        String sql = "SELECT * FROM book WHERE idBook = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, idBook);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isBorrowed(String code){
        String sql = "SELECT loan.borrowed FROM loan JOIN book ON book.idBook = loan.idBook WHERE book.code = ? AND loan.borrowed = true ";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, code);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addLoan(int idClient, String code){

        String sql2 = "INSERT INTO loan(idBook, idClient,date,borrowed) VALUES (?,?,?,?)";
        String sql = "SELECT idBook FROM book WHERE code = ?";
        try(PreparedStatement ps1 = connection.prepareStatement(sql)){
            ps1.setString(1, code);
            ResultSet rs = ps1.executeQuery();
            int idBook = 0;

            if(rs.next()) {
                idBook = rs.getInt("idBook");
            }

            if(existsClient(idClient)){
                if(existsBook(idBook)){
                    try(PreparedStatement ps2 = connection.prepareStatement(sql2)){
                        ps2.setInt(1,idBook);
                        ps2.setInt(2, idClient);
                        ps2.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                        ps2.setBoolean(4, true);

                        ps2.executeUpdate();
                        System.out.println("SE AGREGO A LOAN CORRECTAMENTE");
                    }catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addReturn(int idBook){
        String sql = "UPDATE loan SET borrowed = false WHERE idBook = ? AND borrowed = true";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, idBook);
            ps.executeUpdate();
            System.out.println("SE DEVOLVIO CORRECTAMENTE");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> borrowedBookList(){
        ArrayList<String> datos = new ArrayList<>();
        String sql = "SELECT book.idBook, book.code, book.title, loan.borrowed FROM loan JOIN book ON loan.idBook = book.idBook WHERE loan.borrowed = true";
        try(PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                int idBook = rs.getInt("idBook");
                String code = rs.getString("code");
                String title = rs.getString("title");
                boolean borrowed = rs.getBoolean("borrowed");

                String linea = "|ID BOOK: "+idBook+" | CODE: "+code+" | TITLE: "+title+" | BORROWED: "+borrowed+"|";
                datos.add(linea);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return datos;
    }

    public ArrayList<String> avaibleBookList(){
        ArrayList<String> datos = new ArrayList<>();
        String sql = "SELECT book.idBook, book.code, book.title, loan.borrowed FROM loan JOIN book ON loan.idBook = book.idBook WHERE loan.borrowed = false";
        try(PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                int idBook = rs.getInt("idBook");
                String code = rs.getString("code");
                String title = rs.getString("title");
                boolean borrowed = rs.getBoolean("borrowed");

                String linea = "|ID BOOK: "+idBook+" | CODE: "+code+" | TITLE: "+title+" | BORROWED: "+borrowed+"|";
                datos.add(linea);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return datos;
    }
}
