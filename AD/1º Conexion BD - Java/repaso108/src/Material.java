public class Material {

    private String titulo;
    private int numIdent;
    private boolean estado;

    public Material(String titulo, int numIdent){
        this.estado = true;
        this.titulo = titulo;
        this.numIdent = numIdent;
    }

    public String getTitulo(){return titulo;}

    public int getNumIdent(){return numIdent;}

    public boolean diponible(){
        return estado;
    }

    public void prestar(){
        if(estado){
            estado = false;
            System.out.println(titulo+" FUE PRESTADO");
        }else{
            System.out.println("EL MATERIAL NO ESTA DISPONIBLE");
        }
    }

    public void devolver(){
        if(!estado){
            estado = true;
            System.out.println(titulo+" FUE REGRESADO");
        }else{
            System.out.println("EL MATERIAL ESTA DISPONIBLE");
        }
    }

    @Override
    public String toString() {
        return "Material{" +
                "titulo='" + titulo + '\'' +
                ", numIdent=" + numIdent +
                ", estado=" + estado +
                '}';
    }
}
