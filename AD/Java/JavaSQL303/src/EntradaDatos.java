import java.util.Scanner;

public class EntradaDatos {

    static Scanner SC = new Scanner(System.in);

    public static int leerInt(String elemento){
        while(true){
            System.out.println("INTRODUCE "+elemento.toUpperCase()+":");
            int dato = Integer.parseInt(SC.nextLine());
            if(dato > 0) return dato;
            else System.out.println(elemento.toUpperCase()+" INVALIDO");
        }
    }

    public static String leerString(String elemento){
        while(true){
            System.out.println("INTRODUCE "+elemento.toUpperCase()+":");
            String dato = SC.nextLine();
            if(!dato.isEmpty()){
                if(dato.length() < 2) return dato;
                else System.out.println(elemento.toUpperCase()+" INVALIDO");
            }else System.out.println(elemento.toUpperCase()+" NO PUEDE ESTAR VACIO");
        }
    }
}
