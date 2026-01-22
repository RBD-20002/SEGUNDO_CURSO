public class Contacto {

    private String nombre,numero;

    public Contacto(String nombre, String numero){
        this.nombre = nombre;
        this.numero = numero;
    }

    public void setNombre(String nombre){this.nombre = nombre;}
    public String getNombre(){return nombre;}

    public void setNumero(String numero){this.numero = numero;}
    public String getNumero(){return numero;}

    @Override
    public String toString() {
        return "NOMBRE: "+nombre+" | NUMERO: "+numero;
    }
}
