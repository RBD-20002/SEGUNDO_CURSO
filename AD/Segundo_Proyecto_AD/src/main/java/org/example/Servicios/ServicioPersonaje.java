package org.example.Servicios;

import org.example.Entidades.Personaje;
import org.example.Entidades.Traje;
import org.example.Extra.EntradaDatos;
import org.example.Repositorios.RepoPersonaje;
import org.example.Repositorios.RepoTraje;

public class ServicioPersonaje {

    private final RepoPersonaje repoPersonaje;
    private final RepoTraje repoTraje;

    public ServicioPersonaje(RepoPersonaje repoPersonaje, RepoTraje repoTraje){
        this.repoPersonaje = repoPersonaje;
        this.repoTraje = repoTraje;
    }

    public void crearPersonaje() {
        repoPersonaje.listarPersonajes();
        int id = EntradaDatos.leerInt("id del personaje");
        String nombre = EntradaDatos.leerString("nombre del personaje");
        String alias = EntradaDatos.leerString("alias del personaje");

        String respuesta = EntradaDatos.leerString("QUIRES ASOCIARLE SU TRAJE ¿SI O NO?");
        validarRespuesta(respuesta, repoTraje, id, nombre, alias, repoPersonaje);
    }

    public void eliminarPersonaje() {
        repoPersonaje.listarPersonajes();
        int id = EntradaDatos.leerInt("id del personaje a eliminar");

        repoPersonaje.eliminarPersonaje(id);
    }

    public void modificarPersonaje() {
        repoPersonaje.listarPersonajes();
        int idPersonaje = EntradaDatos.leerInt("id del personaje el cual se va a modificar");
        String alias = EntradaDatos.leerString("nuevo alias");

        repoTraje.listarTrajes();
        int idTraje = EntradaDatos.leerInt("id del traje");

        repoPersonaje.modificarPersonaje(idPersonaje,alias,idTraje);
    }

    public void mostrarDatosPersonaje() {
        repoPersonaje.listarPersonajes();
        int id = EntradaDatos.leerInt("id del personaje");

        repoPersonaje.infoTotalPersonaje(id);
    }

    private static void validarRespuesta(String respuesta, RepoTraje repoTraje, int id, String nombre, String alias, RepoPersonaje repoPersonaje) {
        if(respuesta.equalsIgnoreCase("si")){
            repoTraje.listarTrajes();
            int idTraje = EntradaDatos.leerInt("ID DEL TRAJE");
            Traje trajeValido = repoTraje.selectTraje(idTraje);
            if(trajeValido == null){
                System.out.println("TRAJE NO EXISTE, CREANDO SIN TRAJE");
                Personaje nuevoSinTraje = new Personaje(id,nombre,alias,null);
                repoPersonaje.crearPersonaje(nuevoSinTraje);
                return;
            }
            if(trajeValido.getPersonaje() != null){
                System.out.println("EL TRAJE SELECCIONADO ESTA SIENDO USADO POR "+trajeValido.getPersonaje().getNombre()+" CREANDO SIN TRAJE POR ESO");
                Personaje nuevoSinTraje2 = new Personaje(id,nombre,alias,null);
                repoPersonaje.crearPersonaje(nuevoSinTraje2);
                return;
            }

            Personaje nuevoConTraje = new Personaje(id, nombre, alias,trajeValido);
            repoPersonaje.crearPersonaje(nuevoConTraje);
        }else if(respuesta.equalsIgnoreCase("no")){
            Personaje nuevoSinTraje = new Personaje(id, nombre, alias,null);
            repoPersonaje.crearPersonaje(nuevoSinTraje);
        }else{
            System.out.println("RESPUESTA INVALIDA, SOLO SI O NO");
        }
    }
}
