public class Main {
    public static void main(String[] args) {

        BD2 bd2 = new BD2();
        int opcion;

        do {
            System.out.println("""
                    |opciones:             |
                    |1. info base de datos |
                    |2. mostrar proyectos  |
                    |3. insertat proyecto  |
                    |4. borrar proyecto    |
                    |5. salir              |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    bd2.selectInfoBD();
                    break;
                }
                case 2:{
                    bd2.selectForProject();
                    break;
                }
                case 3:{
                    int num = ED.leerInt("numero de proyecto");
                    String nombre = ED.leerString("nombre de proyecto");
                    String lugar = ED.leerString("lugar de proyecto");
                    int dep = ED.leerInt("numero departamento encargado");

                    bd2.insertNewProject(num,nombre,lugar,dep);
                    break;
                }
                case 4:{
                    int num = ED.leerInt("numero de proyecto a eliminar: ");

                    bd2.deleteForNum(num);
                    break;
                }
                case 5:{
                    System.out.println("ADIOS.........");
                    break;
                }
                default:
                    System.out.println("OPCION INVALIDA");
            }
        }while (opcion != 5);
    }
}
