import SQL.BaseDateDAO;

import java.sql.SQLException;
import java.util.List;

public class BD_GestorEmpleados {

    private BaseDateDAO baseDateDAO;

    public BD_GestorEmpleados(){
        baseDateDAO = new BaseDateDAO();
    }

    public void InfoDataBase() {
        try {
            baseDateDAO.selectInfoDataBase();
        }catch (NullPointerException e){
            System.out.println("FALLO AL BDG AL MOSTAR PROPIEDADES"+e);
        }

    }

    public void MostrarProjectos(){
        System.out.println("DATOS DE LA TABLA PROJECTOS: ");
        List<String> datos = baseDateDAO.selectProject();
        for(String d : datos){
            System.out.println(d);
        }
    }

    public void AgregarProjectoNuevo() throws SQLException {
        int numero = EntradaDatos.leerInt("ID");
        String nombre = EntradaDatos.leerString("nombre del proyecto");
        String lugar = EntradaDatos.leerString("lugar del projecto");
        int departamento = EntradaDatos.leerInt("departamento encargado");
        try {
            baseDateDAO.insertProjectNew(numero, nombre, lugar, departamento);
        }catch (NullPointerException e){
            System.out.println("FALLO AL BDG AGREGAR PROJECTO"+e);
        }
    }

    public void EliminarProyecto() throws SQLException {
        int numero = EntradaDatos.leerInt("id");
        try{
            baseDateDAO.deleteProyect(numero);
        }catch (NullPointerException e){
            System.out.println("FALLO AL BDG ELIMINAR PROJECTO"+e);
        }
    }
}
