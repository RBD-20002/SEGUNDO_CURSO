import java.util.Scanner;

public class EntradaDatos {

    static Scanner sc = new Scanner(System.in);

    public static int leerInt(){
        while(true){
            try{
            System.out.println("INTRODUCE OPCION:");
            int dato = Integer.parseInt(sc.nextLine());
            if(dato>0) return dato;
            else System.out.println("DATO INVALIDO");
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
