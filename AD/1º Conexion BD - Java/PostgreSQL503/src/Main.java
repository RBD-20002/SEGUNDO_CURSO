public class Main {
    public static void main(String[] args) {

        BD3 bd3 = BD3.getInstancia();
        int opcion;

        do {
            System.out.println("""
                    |opciones:            |
                    |1. agregar pokemon   |
                    |2. modificar pokemon |
                    |3. eliminar pokemon  |
                    |4. salir             |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    String nombre = ED.leerString("nombre del pokemon");
                    String tipo = ED.leerString("tipo del pokemon");
                    int nivel = ED.leerInt("nivel del pokemon");

                    bd3.insertPokemon(nombre,tipo,nivel);
                    break;
                }
                case 2:{
                    bd3.mostrarPokemons();
                    int id = ED.leerInt("id del pokemon a modificar");
                    String nombre = ED.leerString("Nombre nuevo");
                    String tipo = ED.leerString("tipo nuevo");
                    int nivel = ED.leerInt("nivel nuevo");

                    bd3.modificarPokemon(id,nombre,tipo,nivel);
                    break;
                }
                case 3:{
                    bd3.mostrarPokemons();
                    int id = ED.leerInt("id del pokemon a eliminar");
                    bd3.eliminarPokemon(id);
                    break;
                }
                case 4:{
                    System.out.println("adios..........".toUpperCase());
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
