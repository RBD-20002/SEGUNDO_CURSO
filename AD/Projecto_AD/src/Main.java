import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        GestorPostgreSQL gestorPostgre = GestorPostgreSQL.getInstance();
        GestorSQL gestorSQL = GestorSQL.getInstance();
        CoordPostSQL_MySQL coordPostSQL_mySQL = CoordPostSQL_MySQL.getInstance(gestorSQL,gestorPostgre);
        int opcion;

        do{
            System.out.println("""
                    ╔═════════════════════════════════════════════════════════════════════════════════════════╗
                    ║                             opciones de base datos hospital                             ║
                    ╠═════════════════════════════════════════════════════════════════════════════════════════╣
                    ║ 1.  Crear una nueva especialidad (PostgreSQL)                                           ║
                    ║ 2.  Crear un nuevo médico (PostgreSQL)                                                  ║
                    ║ 3.  Eliminar un médico por ID (PostgreSQL)                                              ║
                    ║ 4.  Crear un nuevo paciente (MySQL)                                                     ║
                    ║ 5.  Eliminar un paciente (MySQL)                                                        ║
                    ║ 6.  Crear nuevo tratamiento (MySQL + PostgreSQL)                                        ║
                    ║ 7.  Eliminar un tratamiento por su nombre (MySQL + PostgreSQL)                          ║
                    ║ 8.  Listar tratamientos  (MySQL)                                                        ║
                    ║ 9.  Obtener el total de citas por paciente (MySQL)                                      ║
                    ║ 10. Obtener cantidad de tratamientos por sala (PostgreSQL)                              ║
                    ║ 11. Listar tratamientos con especalidades y médicos (MySQL + PostgreSQL)                ║
                    ║ 12. Obtener pacientes que han recibido tratamiento de especialidad (MySQL + PostgreSQL) ║
                    ╠═════════════════════════════════════════════════════════════════════════════════════════╣
                    ║ 13.  salir                                                                              ║
                    ╚═════════════════════════════════════════════════════════════════════════════════════════╝
                    """.toUpperCase());
            opcion = EntradaDatos.leerInt("opcion a realizar");
            switch (opcion){
                case 1:{
                    String nuevaEspecialidad = EntradaDatos.leerString("nueva especialidad");

                    gestorPostgre.crearEspecialidad(nuevaEspecialidad);
                    break;
                }
                case 2:{
                    String nombreMedico = EntradaDatos.leerString("nombre de nuevo medico");
                    String nif = EntradaDatos.leerString("nif de nuevo medico");
                    int telefono = EntradaDatos.leerInt("telefono de nuevo medico");
                    String email = EntradaDatos.leerString("email de contacto");

                    gestorPostgre.crearMedico(nombreMedico, nif, telefono, email);
                    break;
                }
                case 3:{
                    gestorPostgre.mostrarMedicos();
                    int id = EntradaDatos.leerInt("id del medico a eliminar");

                    gestorPostgre.eliminarMedico(id);
                    break;
                }
                case 4:{
                    String nombre = EntradaDatos.leerString("nombre del pacient");
                    String email = EntradaDatos.leerString("email de contacto");
                    LocalDate fechaNacimiento = EntradaDatos.leerDate("fecha de nacimiento");

                    gestorSQL.crearPaciente(nombre, email, fechaNacimiento);
                    break;
                }
                case 5:{
                    gestorSQL.mostrarPacientes();
                    int id = EntradaDatos.leerInt("id del paciente a eliminar");

                    gestorSQL.eliminarPaciente(id);
                    break;
                }
                case 6:{
                    String nombreTrat = EntradaDatos.leerString("nombre de nuevo tratamiento");
                    String descripcion = EntradaDatos.leerString("descripcion del tratamiento");
                    String nombreEspec = EntradaDatos.leerString("nombre de la especialidad");
                    String nifMedico = EntradaDatos.leerString("nif del medico");

                    coordPostSQL_mySQL.crearTratamiento(nombreTrat,descripcion,nombreEspec,nifMedico);
                    break;
                }
                case 7:{
                    gestorSQL.mostrarTratamientos();
                    String nombreTrat = EntradaDatos.leerString("nombre de tratamiento a eliminar");

                    coordPostSQL_mySQL.eliminarTratamientoPorNombre(nombreTrat);
                    break;
                }
                /*DEL 8 AL 13 DE PTMR*/
                case 8:{
                    int cantidad = EntradaDatos.leerInt("cantidad de pacientes por la que filtrar");

                    gestorSQL.listarTratamientosConPocosPacientes(cantidad);
                    break;
                }
                case 9:{
                    gestorSQL.obtenerTotalCitasPorPaciente();
                    break;
                }
                case 10:{
                    gestorPostgre.obtenerCantidadTratamientosPorSala();
                    break;
                }
                case 11:{
                    coordPostSQL_mySQL.listarTratamientosConEspecialidadYMedico();
                    break;
                }
                case 12:{
                    int idEspecialidad = EntradaDatos.leerInt("id de la especialidad");

                    coordPostSQL_mySQL.obtenerPacientesPorEspecialidad(idEspecialidad);
                    break;
                }
                case 13:{
                    System.out.println("adios.........".toUpperCase());
                    break;
                }
                default:
                    System.out.println("opcion invalida".toUpperCase());
                    break;
            }
        }while (opcion != 13);
    }
}
