import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador implements ActionListener {

    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
    }

    public void actualizarTabla(){
        ArrayList<Student> students = modelo.selectAllStudent();
        vista.actualizarDatos(students);
    }

    @Override
    public void actionPerformed(ActionEvent action){
        String[] datos = vista.getDatos();

    }
}
