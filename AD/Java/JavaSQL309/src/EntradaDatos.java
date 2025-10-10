import java.util.Scanner;

public class EntradaDatos {

    static final Scanner SC = new Scanner(System.in);

    public static String leerString(String dato){
        while(true){
            System.out.println("INTRODUCE "+dato.toUpperCase()+":");
            String entrada = SC.nextLine();
            if(!entrada.isEmpty()) return entrada;
            else System.out.println(dato.toUpperCase()+" INVALIDO");
        }
    }

    public static int leerInt(String dato){
        while (true) {
            try {
                System.out.println("INTRODUCE "+dato.toUpperCase()+":");
                int entrada = Integer.parseInt(SC.nextLine());
                if(entrada > 0) return entrada;
                else System.out.println(dato.toUpperCase()+" INVALIDO");
            } catch (NumberFormatException e) {
                System.out.println("fallo el leerInt");
            }
        }
    }
}
