import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MgStudent {

    private String url = "jdbc:mysql://localhost:3306/school";
    private String user = "root";
    private String password = "ricardoBD90-";
    private Connection connection;

    public MgStudent(){
        openConnection();
    }

    public void openConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean addStudent(Student student){
        String sql = "INSERT INTO student(id,name,surname,age) VALUES (?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getSurname());
            ps.setInt(4, student.getAge());

            int contador = ps.executeUpdate();

            return (contador > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Student selectStudent(String id){
        String sql = "SELECT * FROM student WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String nId = rs.getString("id");
                String nName = rs.getString("name");
                String nSurName = rs.getString("surname");
                int nAge = rs.getInt("age");

                return new Student(nId,nName,nSurName,nAge);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean deleteStudent(String id){
        String sql = "DELETE FROM student WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, id);

            int contador = ps.executeUpdate();
            return (contador > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateStudent(Student student){
        String sql = "UPDATE student SET name = ?, surname = ?, age = ? WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getId());

            int contador = ps.executeUpdate();
            return (contador > 0);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Student> selectAllStudent(){
        ArrayList<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM student";
        try(Statement st = connection.createStatement()){

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Student linea = new Student(rs.getString("id"),rs.getString("name"),rs.getString("surname"),rs.getInt("age"));
                students.add(linea);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return students;
    }
}
