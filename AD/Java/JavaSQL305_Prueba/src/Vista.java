
public class Vista extends javax.swing.JFrame {

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration

    private Controlador controlador;

    public Vista() {
    }

    public void arranca(){
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setControlador(Controlador controlador){
        this.controlador = controlador;
        jButton1.addActionListener(controlador);
        jButton1.setActionCommand("SUMAR");
    }

    public String[] getCampos(){
        return new String[]{jTextField1.getText(), jTextField2.getText()};
    }

    public void escribirResultado(String resultado){
        this.jTextPane1.setText(resultado);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jButton1 = new javax.swing.JButton("Sumar");
        jTextField1 = new javax.swing.JTextField(10);
        jTextField2 = new javax.swing.JTextField(10);
        jTextPane1 = new javax.swing.JTextPane();

        jLabel1 = new javax.swing.JLabel("Número 1:");
        jLabel2 = new javax.swing.JLabel("Número 2:");

        jScrollPane1 = new javax.swing.JScrollPane(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        add(jLabel1);
        add(jTextField1);
        add(jLabel2);
        add(jTextField2);
        add(jButton1);
        add(jScrollPane1);

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        jButton1.setActionCommand("SUMAR");
    }


}