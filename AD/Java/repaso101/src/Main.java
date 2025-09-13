public class Main {
    public static void main(String[] args) {

        String texto = "4;5;7;8;4";

        String[] separado = texto.split(";");
        int total = 0;

        for(String num : separado){
            int numero = Integer.parseInt(num);
            total += numero;
        }

        System.out.println(total);
    }
}
