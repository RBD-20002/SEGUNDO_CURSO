package SQL;
import java.util.Scanner;

public class EntradaDatos {

    static Scanner SC = new Scanner(System.in);

    public static String leerString(String datoIntroducido){
        while(true){
            System.out.println("INTRODUCE "+datoIntroducido.toUpperCase()+":");
            String dato = SC.nextLine();
            if(!dato.isEmpty()) return dato;
            else System.out.println(datoIntroducido.toUpperCase()+" INVALIDO");
        }
    }

    public static int leerInt(String datoIntroducido){
        while (true){
            try{
                System.out.println("INTRODUCE "+datoIntroducido.toUpperCase()+":");
                int dato = Integer.parseInt(SC.nextLine());
                if(dato>0) return dato;
                else System.out.println(datoIntroducido.toUpperCase()+" INVALIDO");
            }catch (NumberFormatException e){
                throw new NumberFormatException(e.getMessage());
            }
        }
    }

    public static java.sql.Date leerDate(String datoIntroducido){
        while (true){
            try{
                System.out.println("INTRODUCE "+datoIntroducido.toUpperCase()+":");
                String dato = SC.nextLine();
                if(!dato.isEmpty()) return java.sql.Date.valueOf(dato); //Para este metodo seria poner una condicional con un patron para validar la fecha
                else System.out.println(datoIntroducido.toUpperCase()+" INVALIDO");
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
