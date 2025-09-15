public class Libro extends Material{

    private String autor;

    public Libro(int numIdent, String titulo, String autor){
        super(titulo, numIdent);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autor='" + autor + '\'' +
                '}';
    }
}
