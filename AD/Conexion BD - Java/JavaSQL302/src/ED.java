import java.util.Scanner;

public class ED {

    static final Scanner SC = new Scanner(System.in);

    public static int leerInt(String elemento){
        while(true){
            try{
                System.out.println("INTRODUCE "+elemento.toUpperCase()+":");
                int dato = Integer.parseInt(SC.nextLine());
                if(dato>0) return dato;
                else System.out.print(elemento.toUpperCase()+" INVALIDO");
            }catch(NumberFormatException e){
                System.out.print(e.getMessage());
            }
        }
    }

    public static String leerString(String elemento){
        while(true){
            System.out.println("INTRODUCE "+elemento.toUpperCase());
            String dato = SC.nextLine();
            if(!dato.isEmpty()) return dato;
            else System.out.print(elemento.toUpperCase()+" INVALIDO");
        }
    }


}
