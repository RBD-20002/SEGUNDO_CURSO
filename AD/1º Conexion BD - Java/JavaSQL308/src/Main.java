public class Main {
    public static void main(String[] args) {
        Modelo modelo = new Modelo();

        Vista vista = new Vista();

        Controlador controlador = new Controlador(modelo,vista);

        vista.arrancar();
        vista.setControlador(controlador);

        controlador.actualizarTabla();
    }
}
