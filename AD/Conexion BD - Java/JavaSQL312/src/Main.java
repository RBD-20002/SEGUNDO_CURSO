public class Main {
    public static void main(String[] args) {

        BD12 bd12 = new BD12();
        int opcion;

        do{
            System.out.println("""
                    |opciones:          |
                    |1. lista stock     |
                    |2. filtrar libro   |
                    |3. procedimiento X |
                    |4. salir           |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    bd12.mostrarLibrosStock();
                    break;
                }
                case 2:{
                    int codigo = ED.leerInt("codigo de libro a buscar");
                    bd12.filtrarLibro(codigo);
                    break;
                }
                case 3:{
                    double precio = ED.leerDouble("precio");
                    bd12.contarLibrosPrecio(precio);
                    break;
                }
                case 4:{
                    System.out.println("adios.......".toUpperCase());
                    break;
                }
                default:
                    System.out.println("opcion invalida".toUpperCase());
                    break;
            }
        }while (opcion != 4);
    }
}
