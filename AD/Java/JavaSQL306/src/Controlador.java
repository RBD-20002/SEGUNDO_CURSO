import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("BUSCAR")){
            String resultado = modelo.selectEmpleadoPorId(vista.getNumero());
            vista.datosTable(resultado);
        }
    }
}
