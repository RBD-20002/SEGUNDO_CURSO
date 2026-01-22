import java.util.List;

public class Main {
    public static void main(String[] args) {

        BD3 bd3 = new BD3();
        int opcion;

        do{
            System.out.println("""
                    |opciones:             |
                    |1. consultar empleado |
                    |2. eliminar empleado  |
                    |3. salir              |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    String letra = ED.leerString("la letra del empleado a filtrar");

                    List<String> datos = bd3.selectForLetra(letra);
                    for(String d : datos){
                        System.out.println(d);
                    }
                    break;
                }
                case 2:{
                    String num = ED.leerString("nss del empleado a eliminar");

                    bd3.deleteForNum(num);
                    break;
                }
                case 3:{
                    System.out.println("ADIOS............");
                    break;
                }
                default:
                    System.out.println("OPCION INVALIDA");
            }
        }while (opcion != 3);
    }
}
