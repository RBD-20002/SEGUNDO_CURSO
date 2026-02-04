public class Main {
    public static void main(String[] args) {

        BaseX baseX = new BaseX();

        for (int i = 0; i < 3; i++) {
            String id = ED.leerString("id");
            String nombres = ED.leerString("nombres");
            String apellidos = ED.leerString("apellidos");
            int edad = ED.leerInt("edad");
            String nacionalidad = ED.leerString("nacionalidad");

            Cliente nuevo = new Cliente(id, nombres, apellidos, edad, nacionalidad);
            baseX.agregarCliente(nuevo);
        }

        for (int i = 0; i < 3; i++) {
            baseX.listarClientes();
            int id = ED.leerInt("id a eliminar");
            baseX.eliminarCliente(id);
        }
    }
}
