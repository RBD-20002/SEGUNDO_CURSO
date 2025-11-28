import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {

    HashMap<Integer,Material> libros = new HashMap<>();
    ArrayList<Material> prestamos = new ArrayList<>();

    public void addMaterial(Material material){
        libros.put(material.getNumIdent(),material);
        System.out.println("MATERIAL AGREGADO");
    }

    public Material viewMaterial(int id){
        return libros.get(id);
    }

    public void prestarMaterial(int id){
        Material mt = libros.get(id);
        if(mt == null){
            System.out.println("NO SE ENCONTRO LIBRO");
        }else if(mt.diponible()){
            mt.prestar();
            prestamos.add(mt);
            libros.remove(mt);
        }else{
            System.out.println("LIBRO NO DISPONIBLE");
        }
    }

    public void devolverMaterial(){
        mostrarPrestamos();
        int opcion = EntradaDatos.leerInt();
        if(opcion>prestamos.size()){
            System.out.println("OPCION INVALIDA");
            return;
        }
        Material mt = prestamos.get(opcion);
        mt.devolver();
        libros.put(mt.getNumIdent(),mt);
        prestamos.remove(mt);
    }

    public void mostrarPrestamos(){
        for(int i=0; i<prestamos.size();i++){
            System.out.println((i+1)+" "+prestamos.get(i));
        }
    }
}
