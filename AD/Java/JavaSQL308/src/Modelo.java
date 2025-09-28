import java.sql.*;
import java.util.ArrayList;

public class Modelo {

    private String usuario = "root";
    private String password = "ricardoBD90-";
    private String url = "jdbc:mysql://localhost:3306/school";
    private Connection connection;

    public Modelo(){
        openConnection();
    }

    public void openConnection(){
        try{
            connection = DriverManager.getConnection(usuario,password,url);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void closedConnection(){
        try{
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean insertStudent(Student student){
        String sql = "INSERT INTO student VALUES (?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getSurname());
            ps.setInt(4, student.getAge());

            return (ps.executeUpdate() > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Student> selectAllStudent(){
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            while (rs.next()){
                Student student = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age")
                );
                students.add(student);
            }
            return students;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return students;
    }

    public boolean deleteStudent(String id){
        String sql = "DELETE FROM student WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,id);
            return (ps.executeUpdate() > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean modifyStudent(Student student){
        String sql = "UPDATE student SET id=?, name=?, surname=?, age=?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getSurname());
            ps.setInt(4, student.getAge());
            return (ps.executeUpdate() > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
