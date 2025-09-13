import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] numeros = {20, 20, 30, 40, 50, 50, 50};

        ArrayList<Integer> numeros2 = new ArrayList<Integer>();

        int inicial = numeros[0];
        for (int num : numeros){
            if(!numeros2.contains(num)){
                numeros2.add(num);
            }
        }
        System.out.println(numeros2.size());
    }
}
