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
                    Client cl = bd10.selectForDni(ED.leerString("dni"));
                    System.out.println(cl);
                    break;
                }
                case 2:{
                    String dni = ED.leerString("dni");
                    bd10.selectForPedido(dni);
                    break;
                }
                case 3:{
                    String dni = ED.leerString("dni");
                    bd10.insertNewPedido(dni);
                    break;
                }
                case 4:{
                    System.out.println("adios...................".toUpperCase());
                    break;
                }
                default:
                    System.out.println("opcion invalida".toUpperCase());
                    break;
            }
        }while (opcion != 4);
    }
}
