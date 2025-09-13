import java.util.Scanner;

public class EntradaDatos {

    static Scanner sc = new Scanner(System.in);

    public static String leerString(String elemento){
        while(true){
            try{
                System.out.println(elemento.toUpperCase()+":");
                String dato = sc.nextLine();
                if(!dato.isEmpty()) return dato;
                else System.out.println(elemento.toUpperCase()+" INVALIDO ");
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
