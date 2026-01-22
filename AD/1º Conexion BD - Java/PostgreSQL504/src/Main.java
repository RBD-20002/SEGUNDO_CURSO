public class Main {
    public static void main(String[] args) {

        BD4 bd4 = new BD4();
        bd4.crearBD();
        int opcion;

        do {
            System.out.println("""
                    |opciones:                             |
                    |1. insertar estudiante                |
                    |2. eliminar estudiante                |
                    |3. modificar estudiante               |
                    |4. insertar profesor                  |
                    |5. eliminar profesor                  |
                    |6. modificar profesor                 |
                    |7. insertar curso                     |
                    |8. eliminar curso                     |
                    |9. modificar curso                    |
                    |10. inscribir estudiante a curso      |
                    |11. desinscribir estudiante de curso  |
                    |12. inscribir profesor a curso        |
                    |13. desinscribir profesor de curso    |
                    |14. mostrar informacion de estudiante |
                    |15. mostrar todos los estudiantes     |
                    |16. mostrar informacion de curso      |
                    |17. mostrar todos los cursos          |
                    |18. mostrar informacion de profesor   |
                    |19. mostrar todos los profesores      |
                    |20. listar estudiantes y matricula    |
                    |21. mostrar cursos con estudiantes    |
                    |22. mostrar estudiantes por carrera   |
                    |23. salir                             |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    String nombre = ED.leerString("nombre");
                    int edad = ED.leerInt("edad");
                    String matricula = ED.leerString("matricula");
                    String carrera = ED.leerString("carrera");

                    bd4.insertEstudiante(nombre,edad,matricula,carrera);
                    break;
                }
                case 2:{
                    int id = ED.leerInt("id estudiante a eliminar");

                    bd4.deleteEstudiante(id);
                    break;
                }
                case 3:{
                    bd4.mostrarEstudiantes();
                    int id = ED.leerInt("id de alumno a modificar");
                    String nombre = ED.leerString("nombre");
                    int edad = ED.leerInt("edad");
                    String matricula = ED.leerString("matricula");
                    String carrera = ED.leerString("carrera");

                    bd4.updateEstudiante(id,nombre,edad,matricula,carrera);
                    break;
                }
                case 4:{
                    String nombre = ED.leerString("nombre del profesor");
                    int edad = ED.leerInt("edad");
                    String cedula = ED.leerString("cedula");
                    String departamento = ED.leerString("departamento");

                    bd4.insertProfesor(nombre,edad,cedula,departamento);
                    break;
                }
                case 5:{
                    int id = ED.leerInt("id profesor a eliminar");

                    bd4.deleteProfesor(id);
                    break;
                }
                case 6:{
                    bd4.mostrarProfesores();
                    int id = ED.leerInt("id de profesor a modificar");
                    String nombre = ED.leerString("nombre del profesor");
                    int edad = ED.leerInt("edad");
                    String cedula = ED.leerString("cedula");
                    String departamento = ED.leerString("departamento");

                    bd4.updateProfesor(id,nombre,edad,cedula,departamento);
                    break;
                }
                case 7:{
                    String nombre = ED.leerString("nombre del curso");

                    bd4.insertCurso(nombre);
                    break;
                }
                case 8:{
                    int id = ED.leerInt("id curso a eliminar");

                    bd4.deleteCurso(id);
                    break;
                }
                case 9:{
                    bd4.mostrarCursos();
                    int id = ED.leerInt("id curso a modificar");
                    String nombre = ED.leerString("nombre");

                    bd4.updateCurso(id,nombre);
                    break;
                }
                case 10:{
                    int est_id = ED.leerInt("id del estudiante");
                    int cur_id = ED.leerInt("id del curso");

                    bd4.inscribirAlumno(est_id,cur_id);
                    break;
                }
                case 11:{
                    int est_id = ED.leerInt("id del estudiante");
                    int cur_id = ED.leerInt("id del curso");

                    bd4.desinscribirAlumno(est_id,cur_id);
                    break;
                }
                case 12:{
                    int prof_id = ED.leerInt("id del profesor");
                    int cur_id = ED.leerInt("id del curso");

                    bd4.inscribirProfesor(prof_id,cur_id);
                    break;
                }
                case 13:{
                    int prof_id = ED.leerInt("id del profesor");
                    int cur_id = ED.leerInt("id del curso");

                    bd4.desinscribirProfesor(prof_id,cur_id);
                    break;
                }
                case 14:{
                    int id = ED.leerInt("id estudiante a buscar");

                    bd4.filtrarEstudiente(id);
                    break;
                }
                case 15:{
                    bd4.mostrarEstudiantes();
                    break;
                }
                case 16:{
                    int id = ED.leerInt("id curso a buscar");
                    bd4.filtrarCurso(id);
                    break;
                }
                case 17:{
                    bd4.mostrarCursos();
                    break;
                }
                case 18:{
                    int id = ED.leerInt("id profesor a buscar");
                    bd4.filtrarProfesor(id);
                    break;
                }
                case 19:{
                    bd4.mostrarProfesores();
                    break;
                }
                case 20:{
                    bd4.estudainteMatricula();
                    break;
                }
                case 21:{
                    bd4.cursosConEstudiantes();
                    break;
                }
                case 22:{
                    bd4.estudiantesPorCarrerra();
                    break;
                }
                case 23:{
                    System.out.println("adios........".toUpperCase());
                    break;
                }
                default:{
                    System.out.println("opcion invalida".toUpperCase());
                    break;
                }
            }
        }while (opcion != 23);
    }
}
