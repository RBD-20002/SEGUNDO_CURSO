public class Main {
    public static void main(String[] args) {

        BD12 bd12 = new BD12();
        int opcion;

        do{
            System.out.println("""
                    |opciones:          |
                    |1. lista stock     |
                    |2. filtrar libro   |
                    |3. procedimiento X |
                    |4. salir|
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{

                    break;
                }
                case 2:{

                    break;
                }
                case 3:{

                    break;
                }
                case 4:{
                    System.out.println("adios.......".toUpperCase());
                    break;
                }
                default:
                    System.out.println("opcion invalida".toUpperCase());
                    break;
            }
        }while (opcion != 4);
    }
}
