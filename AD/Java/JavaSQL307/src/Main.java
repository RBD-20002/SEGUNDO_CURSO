import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ManageStudents ms = new ManageStudents();
        int opcion;

        do {
            System.out.println("""
                    |OPTION STUDENTS        |
                    |1. ADD STUDENT         |
                    |2. DELETE STUDENT      |
                    |3. MODIFY DATA STUDENT |
                    |4. VIEW DATA STUDENT   |
                    |5. VIEW ALL STUDENTS   |
                    |6. EXIT                |
                    """);
            opcion = DataEntry.ReadInt("option");
            switch (opcion) {
                case 1:{
                    String id = DataEntry.ReadString("dni");
                    String name = DataEntry.ReadString("name");
                    String lastName = DataEntry.ReadString("last name");
                    int age = DataEntry.ReadInt("age");
                    ms.insertStudent(new Student(id,name,lastName,age));
                    break;
                }
                case 2:{
                    String id =DataEntry.ReadString("id");
                    if(ms.deleteStudent(id)){
                        System.out.println("STUDENT ELIMINATED");
                    }else{
                        System.out.println("STUDENT NOT FOUND");
                    }
                    break;
                }
                case 3:{
                    String id = DataEntry.ReadString("id");
                    String name = DataEntry.ReadString("name");
                    String lastName = DataEntry.ReadString("last name");
                    int age = DataEntry.ReadInt("age");
                    Student st = new Student(id,name,lastName,age);
                    if(ms.modifyStudent(st)){
                        System.out.println("STUDENT MODIFY");
                    }else System.out.println("STUDENT NO MODIFY");
                    break;
                }
                case 4:{
                    String id = DataEntry.ReadString("id");
                    Student st = ms.selectStudent(id);
                    System.out.println(st);
                    break;
                }
                case 5:{
                    ArrayList<Student> estudiante = ms.selectAllStudent();
                    for (Student st : estudiante){
                        System.out.println(st.toString());
                    }
                    break;
                }
                case 6:{
                    System.out.println("BYE.............");
                    break;
                }
                default:{
                    System.out.println("OPTION INVALID");
                }
            }
        }while (opcion != 6);
    }
}
