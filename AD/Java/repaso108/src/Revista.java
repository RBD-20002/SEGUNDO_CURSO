public class Revista extends Material{

    private int numero;

    public Revista(int numIdent, String titulo, int numero) {
        super(titulo, numIdent);
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
