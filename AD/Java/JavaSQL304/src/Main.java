public class Main {
    public static void main(String[] args) {

        int opcion;
        do{
            System.out.println("""
                    |OPCIONES REGISTRAR:|
                    |1. AGREGAR         |
                    |2. SALIR           |
                    """);
            opcion = EntradaDatos.leerInt("opcion");
            switch (opcion){
                case 1 -> BD_Empleados3.addEmployees();
                case 2 -> System.out.println("ADIOS......");
                default -> System.out.println("OPCION INVALIDA");
            }
        }while (opcion != 2);
    }
}
