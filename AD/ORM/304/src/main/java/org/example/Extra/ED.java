package org.example.Extra;

import java.util.Scanner;

public class ED {

    final static Scanner SC = new Scanner(System.in);

    public static String leerString(String elemento) {
        while (true) {
            System.out.println("INTRODUCE " + elemento + ":");
            String dato = SC.nextLine();
            if (!dato.isEmpty()) return dato;
            else System.out.println(elemento + " INVALIDO");
        }
    }

    public static double leerDouble(String elemento) {
        while (true) {
            try {
                System.out.println("INTRODUCE " + elemento + ":");
                double dato = Double.parseDouble(SC.nextLine());
                if (dato > 0) return dato;
                else System.out.println(elemento + " INVALIDO");
            }catch (NumberFormatException e){
                System.err.println(e.getMessage());
            }
        }
    }

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
}
