public class Main {
    public static void main(String[] args) {

        int opcion;
        BD4 bd4 = new BD4();

        do{
            System.out.println("""
                    |opciones             |
                    |1. agregar empleados |
                    |2. salir             |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    int numVeces = ED.leerInt("numero de empleados");
                    String nss = ED.leerString("nss");
                    String nombre = ED.leerString("nombre");
                    String apel1 = ED.leerString("primer apellido");
                    String apel2 = ED.leerString("segundo apellido");
                    String sexo = ED.leerString("sexo");
                    String direccion = ED.leerString("direccion");
                    java.util.Date fecha = ED.leerDate("fecha nacimiento");
                    int salario = ED.leerInt("salario");
                    int numDep = ED.leerInt("numero de departamento");
                    String nssUp = ED.leerString("nss supervisor");

                    bd4.transForEmp(numVeces,nss,nombre,apel1,apel2,sexo,direccion,fecha,salario,numDep,nssUp);
                    break;
                }
                case 2:{
                    System.out.println("ADIOS..............");
                    break;
                }
                default:
                    System.out.println("OPCION INVALIDA");
                    break;
            }
        }while (opcion != 2);
    }
}
