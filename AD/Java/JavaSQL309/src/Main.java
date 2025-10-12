import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BD9 bd9 = new BD9();
        int opcion;

        do {
            System.out.println("""
                    |opciones:             |
                    |1. prestar libro      |
                    |2. devolver libro     |
                    |3. listar prestados   |
                    |4. listar disponibles |
                    |5. salir              |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    int idClient = ED.leerInt("id cliente");
                    String code = ED.leerString("codigo del libro");
                    bd9.addLoan(idClient, code);
                    break;
                }
                case 2:{
                    int idBook = ED.leerInt("id del libro");
                    bd9.addReturn(idBook);
                    break;
                }
                case 3:{
                    ArrayList<String> datos = bd9.borrowedBookList();

                    for (String d : datos){
                        System.out.println(d);
                    }
                    break;
                }
                case 4:{
                    ArrayList<String> datos = bd9.avaibleBookList();

                    for(String d : datos){
                        System.out.println(d);
                    }
                    break;
                }
                case 5:{
                    System.out.println("ADIOS...........");
                    break;
                }
                default:
                    System.out.println("opcion invalida".toUpperCase());
                    break;
            }
        }while(opcion != 5);
    }
}
