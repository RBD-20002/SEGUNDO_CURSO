public class Cliente {

    private String id;
    private String nombres;
    private String apellidos;
    private int edad;
    private String nacionalidad;

    public Cliente(String id, String nombres, String apellidos, int edad, String nacionalidad) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String toXML(){
        return "<cliente id='"+id+"'>\n"+
                    "<nombre>"+nombres+"</nombre>\n"+
                    "<apellidos>"+apellidos+"</apellidos>\n"+
                    "<edad>"+edad+"</edad>\n"+
                    "<nacionalidad>"+nacionalidad+"</nacionalidad>\n"+
                "</cliente>\n";
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
