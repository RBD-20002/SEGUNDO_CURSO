package org.example;

import org.example.Entidades.*;
import org.example.Repositorios.*;
import org.hibernate.Session;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Session session = HibernateUtil.get().openSession();

        RepoAutores repAutores = new RepoAutores(session);
        RepoLibros repLibros = new RepoLibros(session);

        boolean salir = false;

        while (!salir) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("     GESTIÓN LIBROS-AUTORES");
            System.out.println("=".repeat(50));
            System.out.println("1. Insertar nuevo autor");
            System.out.println("2. Insertar nuevo libro");
            System.out.println("3. Enlazar autor y libro");
            System.out.println("4. Insertar teléfono para autor");
            System.out.println("5. Borrar autor");
            System.out.println("6. Borrar libro");
            System.out.println("7. Buscar libro por título");
            System.out.println("8. Ver libros de un autor");
            System.out.println("9. Ver todos los libros");
            System.out.println("10. Ver todos los autores y sus libros");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("DNI Autor: ");
                    String dni = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nacionalidad: ");
                    String nacionalidad = sc.nextLine();
                    repAutores.insertAutor(new Autores(dni, nombre, nacionalidad));
                }

                case 2 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    repLibros.insertLibro(new Libros(titulo, precio));
                }

                case 3 -> {
                    System.out.print("DNI Autor: ");
                    String dniAutor = sc.nextLine();
                    System.out.print("ID Libro: ");
                    int idLibro = sc.nextInt();
                    sc.nextLine();
                    repAutores.enlazarAutorLibro(dniAutor, idLibro);
                }

                case 4 -> {
                    System.out.print("DNI Autor: ");
                    String dniA = sc.nextLine();
                    System.out.print("Número teléfono: ");
                    String numero = sc.nextLine();
                    repAutores.insertarTelefono(dniA, numero);
                }

                case 5 -> {
                    System.out.print("DNI Autor a borrar: ");
                    String dniBorrar = sc.nextLine();
                    repAutores.deleteAutor(dniBorrar);
                }

                case 6 -> {
                    System.out.print("ID Libro a borrar: ");
                    int idBorrar = sc.nextInt();
                    sc.nextLine();
                    repLibros.deleteLibro(idBorrar);
                }

                case 7 -> {
                    System.out.print("Título a buscar: ");
                    String tituloBuscar = sc.nextLine();
                    repLibros.filtrarTitulo(tituloBuscar);
                }

                case 8 -> {
                    System.out.print("DNI Autor: ");
                    String dniVer = sc.nextLine();
                    repAutores.verLibrosDeAutor(dniVer);
                }

                case 9 -> repLibros.listarLibros();

                case 10 -> repAutores.verAutorLibro();

                case 0 -> {
                    salir = true;
                    System.out.println("👋 Saliendo...");
                }

                default -> System.out.println("❌ Opción no válida");
            }
        }

        sc.close();
        session.close();
        System.out.println("✅ Programa finalizado");
    }
}