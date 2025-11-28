public class Main{
    public static void main(String[] args) {
        BD6 bd6 = new BD6();
        bd6.crearBD();

        int opcion;
        do {
            System.out.println("""
                    |opciones:           |
                    |1. agregar producto |
                    |2. funcion producto |
                    |3. salir            |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1: {
                    String codigo = ED.leerString("codigo producto");
                    String nombre = ED.leerString("nombre");
                    double precio = ED.leerDouble("precio");
                    String descripcion = ED.leerString("descripcion");

                    bd6.insertProducto(codigo,nombre,precio,descripcion);
                    break;
                }
                case 3:{
                    System.out.println("ADIOS.......");
                    break;
                }
                default: {
                    System.out.println("OPCION INVALIDO");
                    break;
                }
            }
        }while (opcion != 3);
    }
}
