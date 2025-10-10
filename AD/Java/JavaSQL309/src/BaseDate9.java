import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDate9 {

    private String user = "root";
    private String password = "ricardoBD90-";
    private String url = "jdbc:mysql://localhost:3306/library";
    private Connection connection;

    public void startConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public BaseDate9(){
        startConnection();
    }

    public boolean existsClient(int idClient){
        int contador = 0;
        String sql = "SELECT * FROM Client WHERE idClient = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, idClient);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                contador++;
            }
            return (contador > 0);
            /*
             * O en vez del while solo haz un |return rs.next()| el cual da true si hay al menos una coincidencia
             */
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean existsBook(int idBook) {
        String sql = "SELECT * FROM Book WHERE idBook = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, idBook);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // TRUE si hay al menos una coincidencia
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean esBorrowed(String code){
        String sql = "SELECT * FROM book WHERE idBook = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,code);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean addLoan(String code, int idClient){
        String sql = "SELECT * FROM Client JOIN Loan ON Client.idClient = Loan.idClient and Loan.code = ? AND Client.idClient = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,code);
            ps.setInt(2, idClient);

            ResultSet rs = ps.executeQuery();

            return rs.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addReturn(String code){
        String sql = "UPDATE Loan SET borrowed = NOT borrowed WHERE code = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, code);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<String> borrowedBookList(){
        List<String> datos = new ArrayList<>();
        String sql = "SELECT Book.idBook, Book.code, Book.title, Book.authors, Book.year, Loan.borrowed FROM Loan JOIN Book ON Book.idBook = Loan.idBook AND Loan.borrowed = TRUE";

        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while(rs.next()){
                String linea = " | code: "+rs.getString("code")+
                        " | title: "+rs.getString("title")+
                        " | authors: "+rs.getString("authors")+
                        " | year: "+ rs.getInt("year");
                datos.add(linea);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return datos;
    }

    public List<String> availableBooksList(){
        List<String> datos = new ArrayList<>();

        String sql = "SELECT Book.idBook, Book.code, Book.title, Book.authors, Book.year, Loan.borrowed FROM Loan JOIN Book ON Book.idBook = Loan.idBook AND Loan.borrowed = FALSE";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()){
                String linea = " | code: "+rs.getString("code")+
                        " | title: "+rs.getString("title")+
                        " | authors: "+rs.getString("authors")+
                        " | year: "+rs.getInt("year");
                datos.add(linea);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return datos;
    }
}
