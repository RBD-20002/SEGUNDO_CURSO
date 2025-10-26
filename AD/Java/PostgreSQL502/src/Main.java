public class Main {
    public static void main(String[] args) {

        BD2 bd2 = BD2.getInstance();
        int opcion;

        do{
            System.out.println("""
                    |opciones:                      |
                    |1. listar equipos y directores |
                    |2. pilotos y equipo actual     |
                    |3. resultado de carrera        |
                    |4. piloto mas viejo            |
                    |5. victorias por equipo        |
                    |6. salir                       |
                    """.toUpperCase());
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    bd2.listarEquiposDirectores();
                    break;
                }
                case 2:{
                    bd2.listarPilotosEquipos();
                    break;
                }
                case 3:{
                    bd2.mostrarCarreras();
                    int id = ED.leerInt("id de carrera");
                    bd2.buscarCarreraEspecifica(id);
                    break;
                }
                case 4:{
                    bd2.pilotoMasViejo();
                    break;
                }
                case 5:{
                    bd2.victoriasPorEquipo();
                    break;
                }
                case 6:{
                    System.out.println("adios.........".toUpperCase());
                    break;
                }
                default:{
                    System.out.println("opcion invalida".toUpperCase());
                    break;
                }
            }
        }while (opcion != 6);
    }
}
