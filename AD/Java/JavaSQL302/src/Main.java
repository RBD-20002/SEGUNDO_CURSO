import java.sql.SQLException;

public class Main{
    public static void main(String[] args) throws SQLException {

        BD_GestorEmpleados dbg = new BD_GestorEmpleados();

        int opcion;
        do {
            System.out.println("""
                    |BD_EMPLEADOS               |
                    |1. informacion de bd       |
                    |2. mostrar todos projectos |
                    |3. agregar projecto        |
                    |4. borrar porjecto         |
                    |5. salir                   |
                    """.toUpperCase());
            opcion = EntradaDatos.leerInt("opcion");
            switch (opcion){
                case 1 -> dbg.InfoDataBase();
                case 2 -> dbg.MostrarProjectos();
                case 3 -> dbg.AgregarProjectoNuevo();
                case 4 -> dbg.EliminarProyecto();
                case 5 -> System.out.println("ADIOS....");
                default -> System.out.println("INVALIDO");
            }
        }while (opcion != 5);
    }
}