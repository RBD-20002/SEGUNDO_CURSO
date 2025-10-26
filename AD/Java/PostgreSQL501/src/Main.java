public class Main {
    public static void main(String[] args) {

        BD1 bd1 = new BD1();
        bd1.crearBD();
        int opcion;

        do {
            System.out.println("""
                    |opciones:     |
                    |1. agregar    |
                    |2. consultar  |
                    |3. actualizar |
                    |4. eliminar   |
                    """.toUpperCase());

            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    String titulo = ED.leerString("titulo");
                    String autor = ED.leerString("nombre del autor");
                    String fecha = ED.leerString("la fecha de nacimiento (dd-MM-yyyy)");
                    int publicacion = ED.leerInt("año de publicacion");

                    bd1.insertLibro(titulo,autor,fecha,publicacion);
                    break;
                }
                case 2:{

                }
                case 3:{

                }
                case 4:{

                }
                case 5:{
                    System.out.println("adios...........".toUpperCase());
                    break;
                }
                default:{
                    System.out.println("opcion invalida".toUpperCase());
                    break;
                }
            }
        }while (opcion != 4);
    }
}
