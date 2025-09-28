public class Vista extends javax.swing.JFrame{
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;

    private Controlador controlador;

    public void arrancar(){
        initComponent();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void setControlador(Controlador controlador){
        this.controlador = controlador;
        jButton1.addActionListener(controlador);
        jButton1.setActionCommand("BUSCAR");
    }

    public Vista(){
    }

    public String getNumero(){
        return jTextField1.getText();
    }
    public void datosTable(String resultado){
        jTextArea1.setText(resultado);
    }

    @SuppressWarnings("unchecked")
    private void initComponent(){
        jButton1 = new javax.swing.JButton("BUSCAR");
        jTextField1 = new javax.swing.JTextField(10);
        jTextArea1 = new javax.swing.JTextArea(10, 20);
        jScrollPane2 = new javax.swing.JScrollPane(jTextArea1);
        jLabel1 = new javax.swing.JLabel("NSS:");

        setLayout(new java.awt.FlowLayout());
        add(jLabel1);
        add(jTextField1);
        add(jButton1);
        add(jScrollPane2);
    }
}
