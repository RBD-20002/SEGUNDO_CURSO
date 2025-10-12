public class Main {
    public static void main(String[] args) {

        BD10 bd10 = new BD10();
        int opcion;

        do{
            System.out.println("""
                    |opciones:            |
                    |1. consultar cliente |
                    |2. consultar pedido  |
                    |3. realizar pedido   |
                    |4. salir             |
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

                    break;
                }
                default:
                    System.out.println("opcion invalida".toUpperCase());
                    break;
            }
        }while (opcion != 4);
    }
}
