public class Material {

    private String titulo;
    private int numIdent;
    private boolean estado;

    public Material(String titulo, int numIdent, boolean estado){
        this.estado = estado;
        this.titulo = titulo;
        this.numIdent = numIdent;
    }

    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setTitulo(String titulo){this.titulo = titulo;}
    public String getTitulo(){return titulo;}

    public void setNumIdent(int numIdent){this.numIdent = numIdent;}
    public int getNumIdent(){return numIdent;}

    @Override
    public String toString() {
        return "Material{" +
                "titulo='" + titulo + '\'' +
                ", numIdent=" + numIdent +
                ", estado=" + estado +
                '}';
    }
}
