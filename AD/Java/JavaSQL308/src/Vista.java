import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Vista extends javax.swing.JFrame {

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField surnameTextField;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField idTextField;
    private javax.swing.JTextField ageTextField;

    private Controlador controlador;

    public void arrancar(){
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setControlador(Controlador controlador){
        this.controlador = controlador;

        jButton1.addActionListener(controlador);
        jButton1.setActionCommand("GUARDAR");

        jButton2.addActionListener(controlador);
        jButton2.setActionCommand("ACTUALIZAR");

        jButton3.addActionListener(controlador);
        jButton3.setActionCommand("BORRAR");
    }

    public String[] getDatos(){
        return new String[] {idTextField.getText(), nameTextField.getText(), surnameTextField.getText(), ageTextField.getText()};
    }

    public void actualizarDatos(ArrayList<Student> students){
        String[] columnHeader = {"ID","Name","Surname","Age"};
        DefaultTableModel model = new DefaultTableModel(columnHeader, 0);

        for(Student student : students){
            model.addRow(new Object[] {student.getId(), student.getName(), student.getSurname(), student.getAge()});
        }

        jTable1.setModel(model);
    }

    @SuppressWarnings("unchecked")
    public void initComponents(){
        jLabel5 = new javax.swing.JLabel("STUDENT MANAGEMENT SYSTEM");
        jLabel1 = new javax.swing.JLabel("ID");
        jLabel2 = new javax.swing.JLabel("NAME");
        jLabel3 = new javax.swing.JLabel("SUN NAME");
        jLabel4 = new javax.swing.JLabel("AGE");

        idTextField = new javax.swing.JTextField(10);
        nameTextField = new javax.swing.JTextField(10);
        surnameTextField = new javax.swing.JTextField(10);
        ageTextField = new javax.swing.JTextField(10);

        jButton1 = new javax.swing.JButton("GUARDAR");
        jButton2 = new javax.swing.JButton("ACTUALIZAR");
        jButton3 = new javax.swing.JButton("BORRAR");


        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        add(jLabel1);
        add(idTextField);

        add(jLabel2);
        add(nameTextField);

        add(jLabel3);
        add(surnameTextField);

        add(jLabel4);
        add(ageTextField);

        add(jButton1);
        add(jButton2);
        add(jButton3);

        jScrollPane1.setViewportView(jTable1);
        add(jScrollPane1);
    }

}
