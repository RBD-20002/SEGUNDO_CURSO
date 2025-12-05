package org.example;

import org.example.Entidades.Autores;
import org.example.Entidades.Libros;
import org.example.Entidades.Telefonos;
import org.example.Extra.ED;
import org.example.Extra.Menus;
import org.example.Repositorios.RepAutores;
import org.example.Repositorios.RepLibros;
import org.example.Repositorios.RepTelefonos;
import org.hibernate.Session;

public class Gestion {

    RepAutores rpAutor = new RepAutores(null);
    RepLibros rpLibro = new RepLibros(null);
    RepTelefonos rpTelefono = new RepTelefonos(null);


    public void menuInsertar(){
        int opcion;
        do{
            Menus.menu1();
            opcion = ED.leerInt("OPCION");
            switch (opcion){
                case 1: {
                    String dni = ED.leerString("DNI");
                    String nombre = ED.leerString("NOMBRE AUTOR");
                    String nacionalidad = ED.leerString("NACIONALIDAD");

                    rpAutor.addAuthor(new Autores(dni,nombre,nacionalidad));
                    break;
                }
                case 2: {
                    String titulo = ED.leerString("TITULO");
                    double precio = ED.leerDouble("PRECIO");

                    rpLibro.addBook(new Libros(titulo, precio));
                    break;
                }
                case 3: {
                    String dni = ED.leerString("DNI");
                    String titulo = ED.leerString("TITULO");

                    Autores autor = rpAutor.selectForDNI(dni);
                    Libros libro = rpLibro.selectForTitle(titulo);

                    autor.addLibro(libro);
                    rpAutor.actualizar(autor);
                    rpLibro.actualizar(libro);
                    break;
                }
                case 4: {
                    String DNI = ED.leerString("DNI");
                    String telefono = ED.leerString("TELEFONO");

                    Autores autores = rpAutor.selectForDNI(DNI);
                    Telefonos tlf = new Telefonos(autores,telefono);

                    autores.setNum
                }
            }
        }while (opcion != 5);
    }
}
