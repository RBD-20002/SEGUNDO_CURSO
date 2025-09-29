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
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand(); // Declaras el string del comando
        String[] datos = vista.getDatos();    // Obtienes los datos del formulario

        switch (action) {
            case "GUARDAR":
                Student student = modelo.getStudent(datos[0]);
                Student newStudent = new Student(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));

                if (student == null || student.getId() == null) {
                    // No existe el alumno, insertarlo
                    modelo.insertStudent(newStudent);
                } else {
                    // Existe el alumno, modificarlo
                    modelo.modifyStudent(newStudent);
                }
                break;

            case "ACTUALIZAR":
                Student updatedStudent = new Student(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
                modelo.modifyStudent(updatedStudent);
                break;

            case "BORRAR":
                Student studentToDelete = modelo.getStudent(datos[0]);
                if (studentToDelete != null && studentToDelete.getId() != null) {
                    modelo.deleteStudent(datos[0]);
                }
                break;
        }

        // Actualizar la tabla después de cualquier acción
        ArrayList<Student> listaEstudiantes = modelo.selectAllStudent();
        vista.actualizarDatos(listaEstudiantes);
    }

}
