public class Main {
    public static void main(String[] args) {

        BD11 bd11 = new BD11();
        int opcion;

        do {
            System.out.println("""
                    |opciones:              |
                    |1. crear cuenta        |
                    |2. borrar cuenta       |
                    |3. ingresar dinero     |
                    |4. retirar dinero      |
                    |5. hacer transferencia |
                    |6. filtrar cuentas     |
                    |7. salir               |
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
                case 5:{

                    break;
                }
                case 6:{

                    break;
                }
                case 7:{
                    System.out.println("adios...........".toUpperCase());
                    break;
                }
                default:
                    System.out.println("opcion invalida".toUpperCase());
                    break;
            }
        }while (opcion != 7);
    }
}
