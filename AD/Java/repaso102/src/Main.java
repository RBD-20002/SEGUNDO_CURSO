import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int edad = -1;

        while(edad < 18) {
            try {
                System.out.println("INTRODUCE TU EDAD: ");
                edad = Integer.parseInt(sc.nextLine());
                if (edad < 18) System.out.println("TIENES QUE SER MAYOR DE EDAD");
                else if (edad < 65) System.out.println("TE FALTA " + (65 - edad) + " AÑOS PARA JUBILARTE");
                else if (edad > 65) System.out.println("LLEVAS " + (65 - edad) + " AÑOS JUBILADOS");
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
