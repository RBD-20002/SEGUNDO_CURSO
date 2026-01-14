package org.example;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ED {
    private static final Scanner SC = new Scanner(System.in);

    public static int leerInt(String elemento) {
        while (true) {
            try {
                System.out.println("INTRODUCE " + elemento.toUpperCase() + ":");
                int dato = Integer.parseInt(SC.nextLine());
                if (dato > 0) return dato;
                else System.out.print(elemento.toUpperCase() + " INVALIDO");
            } catch (NumberFormatException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    public static Double leerDouble(String elemento) {
        while (true) {
            try {
                System.out.println("INTRODUCE " + elemento.toUpperCase() + ":");
                double dato = Integer.parseInt(SC.nextLine());
                if (dato > 0) return dato;
                else System.out.print(elemento.toUpperCase() + " INVALIDO");
            } catch (NumberFormatException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    public static LocalDate leerDate(String datoIntroducido) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            try {
                System.out.println("INTRODUCE " + datoIntroducido.toUpperCase() + ":");
                String dato = SC.nextLine();
                if (!dato.isEmpty()) {
                    LocalDate localDate = LocalDate.parse(dato, formato);
                    return localDate; //Para este metodo seria poner una condicional con un patron para validar la fecha
                } else
                    System.out.println(datoIntroducido.toUpperCase() + " INVALIDO | INGRESA FORMATO VALIDO DE DD-MM-YYYY");
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println(e.getMessage());
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
