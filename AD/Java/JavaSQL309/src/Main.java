import java.util.List;

public class Main {
    public static void main(String[] args) {

        BaseDate9 bd9 = new BaseDate9();
        int opcion;

        do{
            System.out.println("""
                    |OPCIONES:             |
                    |1. Prestar libro      |
                    |2. Devolver libro     |
                    |3. Listar prestados   |
                    |4. Listar disponibles |
                    |5. Salir              |
                    """);
            opcion = EntradaDatos.leerInt("opcion");
            switch (opcion){
                case 1:{
                    int idCliente = EntradaDatos.leerInt("id cliente");
                    if(!bd9.existsClient(idCliente)){
                        System.out.println("cliente no encontrado".toUpperCase());
                        break;
                    }
                    int idBook = EntradaDatos.leerInt("id book");
                    if(!bd9.existsBook(idBook)){
                        System.out.println("libro no encontrado".toUpperCase());
                        break;
                    }
                    String code = EntradaDatos.leerString("code");

                    bd9.addLoan(code, idCliente);
                    break;
                }
                case 2:{
                    int codeBook = EntradaDatos.leerInt("id libro");
                    if(!bd9.existsBook(codeBook)){
                        System.out.println("libro no encontrado".toUpperCase());
                        break;
                    }
                    String codeAfter = String.valueOf(codeBook);

                    bd9.addReturn(codeAfter);
                    break;
                }
                case 3:{
                    List<String> informacion = bd9.borrowedBookList();

                    for(String inf : informacion){
                        System.out.println(inf+"\n");
                    }
                    break;
                }
                case 4:{
                    List<String> informacion = bd9.availableBooksList();

                    for(String inf : informacion){
                        System.out.println(inf+"\n");
                    }
                    break;
                }
                case 5:{
                    System.out.println("ADIOS......");
                    break;
                }
                default:{
                    System.out.println("OPCION INVALIDA");
                }
            }
        }while (opcion != 5);
    }
}