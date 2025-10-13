public class Libros {

    private int codigo,stock;
    private String titulo,autor,editorial;
    private double precio;

    public Libros(int codigo, int stock, String titulo, String autor, String editorial, double precio) {
        this.codigo = codigo;
        this.stock = stock;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }
    public int getStock() {
        return stock;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getEditorial() {
        return editorial;
    }
    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Libro:" +
                "\n codigo:"+codigo+
                "\n stock:"+stock+
                "\n titulo: "+titulo+
                "\n autor: "+autor+
                "\n editorial: "+editorial+
                "\n precio: "+precio;
    }
}
