import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double total = 0;
        boolean salir = false;
        while(!salir){
            System.out.println("PRODUCTO:");
            String producto = sc.nextLine();

            System.out.println("PRECIO:");
            double precio = Double.parseDouble(sc.nextLine());
            total += precio;

            System.out.println("¿QUIERES SALIR?");
            String respuesta = sc.nextLine();
            if(respuesta.equalsIgnoreCase("si")){
                salir = true;
                System.out.println(total);
            }
        }
    }
}
