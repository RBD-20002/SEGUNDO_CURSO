public class Client {

    private String dni,nombre;

    public Client(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }
    public String getNombre() {
        return nombre;
    }

    public String toString(){
        return "CLIENTE: "
                +"\n DNI: "+dni
                +"\n NOMBRE: "+nombre;
    }
}
