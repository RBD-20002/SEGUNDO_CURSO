public class Main {
    public static void main(String[] args) {
        BD_Emplados2 bd2 = new BD_Emplados2();
        int opcion;

        do{
            System.out.println("""
                    |bd-empleados           |
                    |1. mostrar por letra   |
                    |2. eliminar por numero |
                    |3. salir               |
                    """.toUpperCase());
            opcion = EntradaDatos.leerInt("opcion");
            switch (opcion){
                case 1 -> bd2.filtrarPorLetra();
                case 2 -> bd2.eliminarPorNumero();
                case 3 -> System.out.println("ADIOS.....");
                default -> System.out.println("OPCION INVALIDA");
            }
        }while (opcion != 3);
    }
}
