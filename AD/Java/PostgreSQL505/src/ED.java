import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public static Date leerDate(String datoIntroducido){
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            while (true){
                try{
                    System.out.println("INTRODUCE "+datoIntroducido.toUpperCase()+":");
                    String dato = SC.nextLine();
                    if(!dato.isEmpty()){
                        LocalDate localDate = LocalDate.parse(dato,formato);
                        return Date.valueOf(localDate); //Para este metodo seria poner una condicional con un patron para validar la fecha
                    }else System.out.println(datoIntroducido.toUpperCase()+" INVALIDO | INGRESA FORMATO VALIDO DE DD-MM-YYYY");
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }
}
