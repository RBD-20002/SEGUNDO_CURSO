public class Libro extends Material{

    private String autor;

    public Libro(String titulo, int numIdent, boolean estado, String autor){
        super(titulo, numIdent,estado);
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
