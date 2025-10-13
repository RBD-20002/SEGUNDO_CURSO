public class Cliente {

    private int id,edad;
    private String dni,nombre;

    public Cliente(int id, String dni, String nombre, int edad){
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }
    public String getDni() {
        return dni;
    }
    public String getNombre() {
        return nombre;
    }
    public int getEdad() {
        return edad;
    }

    public String toString(){
        return "| ID: "+id+" | DNI: "+dni+" | NOMBRE: "+nombre+" | EDAD: "+edad+" |";
    }
}
