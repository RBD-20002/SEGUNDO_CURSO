import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Contacto> contactos = new ArrayList<>();

        int opcion;
        do{
            System.out.println("""
                    1. AGREGAR CONTACTOS
                    2. MOSTRAR CONTACTOS
                    3. ELIMINAR CONTACTO
                    4. SALIR 
                    INTRODUCE OPCION:
                    """);
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion){
                case 1:
                    String nombre = EntradaDatos.leerString("nombre");
                    String numero = EntradaDatos.leerString("numero");
                    contactos.add(new Contacto(nombre,numero));
                    System.out.println("CONTACTO AGREGADO");
                    break;
                case 2:
                    if(contactos.isEmpty()) System.out.println("CONTACTOS VACIOS");
                    else{
                        System.out.println("CONTACTOS: ");
                        for(Contacto contacto : contactos) {
                            System.out.println(contacto);
                        }
                    }
                    break;
                case 3:
                    if(contactos.isEmpty()) System.out.println("CONTACTOS VACIOS");
                    else {
                        String eliminar = EntradaDatos.leerString("contacto a eliminar");
                        for(Contacto contacto : contactos){
                            if(contacto.getNombre().equalsIgnoreCase(eliminar)) contactos.remove(contacto);
                            System.out.println("CONTACTO ELIMINADO");
                        }
                    }
                    break;
                case 4:
                    System.out.println("ADIOS......");
                    break;
                default:
                    System.out.println("OPCION INVALIDA");
            }
        }while (opcion != 4);
    }
}
