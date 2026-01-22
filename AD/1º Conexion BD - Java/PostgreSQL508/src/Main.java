import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        BD8 bd8 = new BD8();
        int opcion;

        do {
            System.out.println("""
                    opciones:
                    1.  crear esquema 'academiaTeis'
                    2.  crear tipo instituto
                    3.  insertar inscripcion
                    4.  actualizar email estudiante
                    5.  eliminar informacion de curso
                    6.  listar estudiantes
                    7.  promedio de estudiantes
                    8.  nombre-descripcion curso > 180
                    9.  nombre y descripcion de mayores de 28
                    10. estudiante con su cantidad de cursos
                    11. salir
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1: {
                    bd8.crearSchema();
                    break;
                }
                case 2: {
                    bd8.crearType();
                    break;
                }
                case 3: {
                    int curso = ED.leerInt("id curso");
                    int estudiante = ED.leerInt("id estudiante");
                    Date fecha = ED.leerDate("fecha inscripcion");

                    bd8.insertInscripcion(curso,estudiante,fecha);
                    break;
                }
                case 4: {
                    int estudiante = ED.leerInt("id estudiante");
                    String email = ED.leerString("nuevo email");

                    bd8.modificarEmail(email,estudiante);
                    break;
                }
                case 5: {
                    int curso_id = ED.leerInt("id del curso");

                    bd8.eliminarDescripcion(curso_id);
                    break;
                }
                case 6: {
                    bd8.mostrarEstudiantes();
                    break;
                }
                case 7: {
                    bd8.promedioEdad();
                    break;
                }
                case 8: {
                    bd8.consulta3();
                    break;
                }
                case 9: {
                    bd8.consulta4();
                    break;
                }
                case 10: {
                    bd8.consulta5();
                    break;
                }
                case 11: {
                    System.out.println("ADIOS.....................");
                    break;
                }
                default: {
                    System.err.println("opcion invalida");
                    break;
                }
            }
        }while (opcion != 11);
    }
}
