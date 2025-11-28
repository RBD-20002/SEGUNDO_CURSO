public class Main {
    public static void main(String[] args) {

        Biblioteca bl = new Biblioteca();

        Libro lb1 = new Libro(1,"DON QUIJOTE","CERVANTES");
        Libro lb2 = new Libro(2,"MIYASAKI","JAPONES");
        Revista rv1 = new Revista(3,"TOP JUEGOS",50);

        bl.addMaterial(lb1);
        bl.addMaterial(lb2);
        bl.addMaterial(rv1);

        bl.prestarMaterial(1);
        bl.prestarMaterial(2);
        bl.prestarMaterial(3);
        bl.mostrarPrestamos();
        bl.devolverMaterial();
    }
}
