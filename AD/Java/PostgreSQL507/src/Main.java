public class Main {
    public static void main(String[] args) {
        BD7 bd7 = new BD7();
        int opcion;

        do {
            System.out.println("""
                    1. mostrar alumno
                    2. mostrar profesores
                    3. filtrar alumno
                    4. filtrar profesor
                    5. agregar alumno
                    6. agregar profesor
                    7. media nota alumno
                    8. elimnar alumno
                    9. salir
                    """);
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    bd7.mostrarAlumnos();
                    break;
                }
                case 2:{
                    bd7.mostrarProfesores();
                    break;
                }
                case 3:{
                    bd7.mostrarAlumnos();
                    int id = ED.leerInt("id");

                    bd7.filtrarAlumno(id);
                    break;
                }
                case 4:{
                    bd7.mostrarProfesores();
                    int id = ED.leerInt("id");

                    bd7.filtrarProfesor(id);
                    break;
                }
                case 5:{
                    String nombre = ED.leerString("nombre");
                    String apellido = ED.leerString("apellido");
                    int edad = ED.leerInt("edad");
                    String dni = ED.leerString("dni");
                    String direccion = ED.leerString("direccion");
                    String turno = ED.leerString("turno");
                    String grupo = ED.leerString("grupo");

                    bd7.insertAlumno(nombre,apellido,edad,dni,direccion,turno,grupo);
                    break;
                }
                case 6:{
                    String nombre = ED.leerString("nombre");
                    String apellido = ED.leerString("apellido");
                    int edad = ED.leerInt("edad");
                    String dni = ED.leerString("dni");
                    String cod = ED.leerString("codigo profesor");

                    bd7.insertProfesor(nombre,apellido,edad,dni,cod);
                    break;
                }
                case 7:{
                    bd7.mostrarAlumnos();
                    int id = ED.leerInt("id alumno");
                    bd7.mediaNota(id);
                    break;
                }
                case 8:{
                    bd7.mostrarAlumnos();
                    int id = ED.leerInt("id alumno a elimnar");

                    bd7.elimnarAlumno(id);
                    break;
                }
                case 9:{
                    System.out.println("adios");
                    break;
                }
                default:{
                    System.out.println("invalido");
                    break;
                }
            }
        }while (opcion != 9);
    }
}
