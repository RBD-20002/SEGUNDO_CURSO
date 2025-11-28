import java.util.List;

public class Main {
    public static void main(String[] args) {

        MgStudent MgS = new MgStudent();
        int opcion;

        do {
            System.out.println("""
                    |opciones:           |
                    |1. matricular       |
                    |2. dar de baja      |
                    |3. actualizar datos |
                    |4. ver estudiante   |
                    |5. ver estudiantes  |
                    |6. salir            |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    String id = ED.leerString("dni");
                    String name = ED.leerString("nombre");
                    String surname = ED.leerString("apellido");
                    int age = ED.leerInt("edad");
                    Student st = new Student(id,name,surname,age);

                    MgS.addStudent(st);
                    break;
                }
                case 2:{
                    MgS.deleteStudent(ED.leerString("dni"));
                    break;
                }
                case 3:{
                    String id = ED.leerString("dni");
                    String name = ED.leerString("cambio de nombre");
                    String surname = ED.leerString("cambio de apellido");
                    int age = ED.leerInt("cambio de edad");
                    Student st = new Student(id,name,surname,age);

                    MgS.updateStudent(st);
                    break;

                }
                case 4:{
                    Student st = MgS.selectStudent(ED.leerString("dni"));
                    System.out.println(st);
                    break;
                }
                case 5:{
                    List<Student> students  = MgS.selectAllStudent();

                    for (Student st : students){
                        System.out.println(st);
                    }
                    break;
                }
                case 6:{
                    System.out.println("adios".toUpperCase());
                    break;
                }
                default:
                    System.out.println("opcion invalida".toUpperCase());
                    break;
            }
        }while (opcion != 6);
    }
}
