import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double longPis = 0, ancPis = 0, longPar = 0, ancPar = 0, parcela, piscina;
        boolean salir = false;

        while(!salir){
            try {
                System.out.println("LONGITUD PISCINA:");
                longPis = Double.parseDouble(sc.nextLine());
                System.out.println("ANCHO PISCINA:");
                ancPis = Double.parseDouble(sc.nextLine());
                System.out.println("LONGITUD PARCELA");
                longPar = Double.parseDouble(sc.nextLine());
                System.out.println("ANCHO PARCELA");
                ancPar = Double.parseDouble(sc.nextLine());

                piscina = (longPis * ancPis) / 2;
                parcela = (longPar * ancPar) / 2;
                System.out.println("El aforo de la piscina es: "+Math.max(piscina,parcela)+" personas");
                System.out.println("¿QUIERES SALIR?");
                String fin = sc.nextLine();
                if (fin.equalsIgnoreCase("si")){
                    salir = true;
                }
            }catch (NumberFormatException e){
                System.out.println("Ha ocurrido una excepción. Solo se permiten enteros");
            }
        }
    }
}
