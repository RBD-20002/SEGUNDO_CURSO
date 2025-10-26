import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        BD5 bd5 = new BD5();
        bd5.crearBD();
        int opcion;

        do {
            System.out.println("""
                    |opciones:|
                    |1. insertar medicos                     |
                    |2. eliminar medicos                     |
                    |3. modificar medicos                    |
                    |4. insertar pacientes                   |
                    |5. eliminar pacientes                   |
                    |6. modificar pacientes                  |
                    |7. insertar examenes medicos            |
                    |8. eliminar examenes medicos            |
                    |9. modificar examenes medicos           |
                    |10. insertar citas medicas              |
                    |11. eliminar citas medicas              |
                    |12. modificar citas medicas             |
                    |13. filtrar paciente                    |
                    |14. mostrar pacientes                   |
                    |15. filtrar medicos                     |
                    |16. mostrar medicos                     |
                    |17. citas medicas de un paciente        |
                    |18. citas medicas de un medico          |
                    |19. mostrar examenes de paciente        |
                    |20. filtrar por grupo sanguineo         |
                    |21. filtrar medico que atendio paciente |
                    |22. salir                               |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    String nombre = ED.leerString("nombre medico");
                    int edad = ED.leerInt("edad");
                    String codigo = ED.leerString("codigo medico");
                    String especialidad = ED.leerString("especialidad");

                   bd5.insertMedico(nombre,edad,codigo,especialidad);
                    break;
                }
                case 2:{
                    bd5.mostrarMedicos();
                    int id = ED.leerInt("id del medico a eliminar");

                    bd5.deleteMedico(id);
                    break;
                }
                case 3:{
                    bd5.mostrarMedicos();
                    int id = ED.leerInt("id del medico a modificar");
                    String nombre = ED.leerString("nombre medico");
                    int edad = ED.leerInt("edad");
                    String codigo = ED.leerString("codigo medico");
                    String especialidad = ED.leerString("especialidad");

                    bd5.updateMedico(id,nombre,edad,codigo,especialidad);
                    break;
                }
                case 4:{
                    String nombre = ED.leerString("nombre del paciente");
                    int edad = ED.leerInt("edad");
                    String historia = ED.leerString("numero de historia del paciente");
                    String sangre = ED.leerString("grupo sanguineo");

                    bd5.insertPaciente(nombre,edad,historia,sangre);
                    break;
                }
                case 5:{
                    bd5.mostrarPacientes();
                    int id = ED.leerInt("id del paciente a eliminar");

                    bd5.deletePaciente(id);
                    break;
                }
                case 6:{
                    bd5.mostrarPacientes();
                    int id = ED.leerInt("id del paciente a modificar");
                    String nombre = ED.leerString("nombre paciente");
                    int edad = ED.leerInt("edad");
                    String historia = ED.leerString("numero de historia");
                    String sangre = ED.leerString("grupo sanguineo");

                    bd5.updatePaciente(id,nombre,edad,historia,sangre);
                    break;
                }
                case 7:{
                    int id = ED.leerInt("id del paciente");
                    String examen = ED.leerString("nombre examen");
                    Date fecha = ED.leerDate("fecha");
                    String resultado = ED.leerString("resultado");

                    bd5.insertExamenesMedicos(id,examen,fecha,resultado);
                    break;
                }
                case 8:{
                    int id = ED.leerInt("id examen medico a eliminar");

                    bd5.deleteExamenMedico(id);
                    break;
                }
                case 9:{
                    String examen = ED.leerString("nombre examen medico");
                    Date fecha = ED.leerDate("fecha");
                    String resultado = ED.leerString("resultado de examen medico");
                    int idPaciente = ED.leerInt("id del paciente");

                    bd5.updateExamenMedico(examen,fecha,resultado,idPaciente);
                    break;
                }
                case 10:{
                    Date fecha = ED.leerDate("fecha de cita");
                    int paciente = ED.leerInt("id del paciente");
                    int medico = ED.leerInt("id del medico");

                    bd5.insertCitaMedica(fecha,paciente,medico);
                    break;
                }
                case 11:{
                    int id = ED.leerInt("id cita a eliminar");

                    bd5.deleteCitaMedica(id);
                    break;
                }
                case 12:{
                    int id = ED.leerInt("id cita medica a modificar");
                    Date fecha = ED.leerDate("fecha");
                    int paciente = ED.leerInt("id del paciente");
                    int medico = ED.leerInt("id del medico");

                    bd5.updateCitaMedica(id,fecha,paciente,medico);
                    break;
                }
                case 13:{
                    int id = ED.leerInt("id paceinte a buscar");
                    bd5.filtrarPaciente(id);
                    break;
                }
                case 14:{
                    bd5.mostrarPacientes();
                    break;
                }
                case 15:{
                    int id = ED.leerInt("id medico");
                    bd5.filtrarMedico(id);
                    break;
                }
                case 16:{
                    bd5.mostrarMedicos();
                    break;
                }
                case 17:{
                    int id = ED.leerInt("id del paciente");
                    bd5.citasDePaciente(id);
                    break;
                }
                case 18:{
                    int id = ED.leerInt("id del medico");
                    bd5.citasDeMedicos(id);
                    break;
                }
                case 19:{
                    int id = ED.leerInt("id del paciente");
                    bd5.examenesMedicosPaciente(id);
                    break;
                }
                case 20:{
                    String sangre = ED.leerString("grupo sanguineo a filtrar");
                    bd5.filtrarPorSangre(sangre);
                    break;
                }
                case 21:{
                    System.out.println("falta hacer");
                    break;
                }
                case 22:{
                    System.out.println("adios........".toUpperCase());
                    break;
                }
                default:{
                    System.out.println("opcion invalida".toUpperCase());
                    break;
                }
            }
        }while (opcion != 22);
    }
}
