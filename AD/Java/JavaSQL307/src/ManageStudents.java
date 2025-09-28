import java.sql.*;
import java.util.ArrayList;

public class ManageStudents {
    private Connection connection;
    private String usuario = "root";
    private String password = "ricardoBD90-";

    public ManageStudents(){
        openConnection();
    }

    public void openConnection(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/",usuario,password);
            Statement st = connection.createStatement();
            st.executeUpdate("DROP DATABASE IF EXISTS school");
            st.executeUpdate("CREATE DATABASE school");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school",usuario,password);
            Statement st2 = connection.createStatement();

            st2.executeUpdate("""
            CREATE TABLE student (
                id CHAR(9) PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                surname VARCHAR(100) NOT NULL,
                age INT NOT NULL
            )
            """);
            st2.executeUpdate("INSERT INTO student VALUES ('11111111A','Draco','Malfoy',25)");
            st2.executeUpdate("INSERT INTO student VALUES ('22222222B','Hermione','Granger',23)");
            st2.executeUpdate("INSERT INTO student VALUES ('33333333C','Harry','Potter',20)");
            st2.executeUpdate("INSERT INTO student VALUES ('44444444D','Ron','Weasley',22)");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection(){
        try{
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean insertStudent(Student student){
        String sql = "INSERT INTO student VALUES (?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
           ps.setString(1,student.getId());
           ps.setString(2,student.getName());
           ps.setString(3,student.getSurname());
           ps.setInt(4,student.getAge());
           return (ps.executeUpdate() > 0);

        }catch (SQLException e){
            System.out.println(e.getMessage());
            closeConnection();
            return false;
        }
    }

    public Student selectStudent(String id){
        String sql = "SELECT * FROM student WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            if(rs.next()){
                return new Student(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getInt("age"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
            closeConnection();
        }
        return  null;
    }

    public boolean deleteStudent(String id){
        String sql = "DELETE FROM student WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,id);
            return (ps.executeUpdate() > 0);

        }catch (SQLException e){
            System.out.println(e.getMessage());
            closeConnection();
            return false;
        }
    }

    public ArrayList<Student> selectAllStudent(){
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            while (rs.next()){
                Student studentLine = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getInt("age")
                );
                students.add(studentLine);
            }
            return students;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            closeConnection();
        }
        return students;
    }

    public boolean modifyStudent(Student student){
        String sql = "UPDATE student SET id = ?, name = ?, surname = ?, age = ?";
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
