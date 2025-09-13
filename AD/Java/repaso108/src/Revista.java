public class Revista extends Material{

    private int numero;

    public Revista(String titulo, int numIdent, boolean estado, int numero) {
        super(titulo, numIdent,estado);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Revista{" +
                "numero=" + numero +
                '}';
    }
}
