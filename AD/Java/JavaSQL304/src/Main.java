import SQL.EntradaDatos;

public class Main {
    public static void main(String[] args) {

        BD_Empleados3 bd3 = new BD_Empleados3();
        int opcion;

        do{
            System.out.println("""
                    |OPCIONES REGISTRAR:|
                    |1. AGREGAR         |
                    |2. SALIR           |
                    """);
            opcion = EntradaDatos.leerInt("opcion");
            switch (opcion){
                case 1 -> bd3.agregarEmpleados();
                case 2 -> System.out.println("ADIOS......");
                default -> System.out.println("OPCION INVALIDA");
            }
        }while (opcion != 2);
    }
}
