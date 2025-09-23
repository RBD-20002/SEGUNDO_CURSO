package SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDateDAO implements MetodosSQL {
    Connection connection;
    public BaseDateDAO() {
        // EL CONSTRUCTOR SE ENCARGA DE LA CONEXIÓN O MUERE
        conectarOBLIGATORIO();
    }

    private void conectarOBLIGATORIO() {
        int intentos = 0;
        while (intentos < 3) {
            try {
                System.out.println("🔄 Intentando conectar a la BD...");

                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/empleados",
                        "root",
                        "ricardoBD90-"
                );

                // VERIFICACIÓN EXTREMA
                if (connection != null && !connection.isClosed()) {
                    System.out.println("✅ CONEXIÓN EXITOSA A LA BD");
                    return; // ¡ÉXITO! Salimos del método
                }

            } catch (SQLException e) {
                intentos++;
                System.out.println("❌ FALLO EN INTENTO " + intentos + ": " + e.getMessage());

                if (intentos >= 3) {
                    System.out.println("💀 NO SE PUDO CONECTAR DESPUÉS DE 3 INTENTOS");
                    System.out.println("🔧 VERIFICA:");
                    System.out.println("   1. MySQL está ejecutándose");
                    System.out.println("   2. La BD 'empleados' existe");
                    System.out.println("   3. Usuario/contraseña correctos");
                    System.exit(1); // CIERRA EL PROGRAMA
                }

                // Espera 2 segundos antes de reintentar
                try { Thread.sleep(2000); } catch (InterruptedException ie) {}
            }
        }
    }

    @Override
    public void selectInfoDataBase(){
        try{
            DatabaseMetaData dbmd = connection.getMetaData();
            String gestor = dbmd.getDatabaseProductName();
            String conector = dbmd.getDatabaseProductName();
            String url = dbmd.getURL();
            String usuario = dbmd.getUserName();

            System.out.println("GESTOR: "+gestor+
                    "\nCONECTOR: "+conector+
                    "\nURL: "+url+
                    "\nUSUARIO: "+usuario);
        }catch (SQLException e){
            System.out.println("fallo en el select "+e);
        }
    }

    @Override
    public List<String> selectProject() {
        List<String> datos = new ArrayList();
        String sql = "SELECT * FROM proyecto";

        try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(sql)){
            while (rs.next()){
                String linea = "NUMERO DE PROYECTO: "+rs.getInt("Numproy")
                        +" NOMBRE DE PROYECTO: "+rs.getString("Nombreproy")
                        +" LUGAR DEL PROYECTO: "+rs.getString("Lugarproy")
                        +" NUMERO DE DEPARTAMENTO: "+rs.getInt("departamento_Numdep");
                datos.add(linea);
            }
        }catch (SQLException e){
            System.out.println("FALLO MOSTRAR PROJECT"+e);
        }
        return datos;
    }

    @Override
    public void insertProjectNew(int num, String nombreProyecto, String lugarProyecto, int departamento){
        String sql = "INSERT INTO proyecto (Numproy, Nombreproy, LugarProy, departamento_Numdep) VALUES (?,?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,num);
            ps.setString(2,nombreProyecto);
            ps.setString(3,lugarProyecto);
            ps.setInt(4, departamento);
            ps.execute();
        }catch (SQLException e){
            System.out.println("FALLO INSERT"+e);
        }
    }

    @Override
    public void deleteProyect(int numProy){
        String sql = "DELETE FROM proyecto WHERE Numproy = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,numProy);
            ps.execute();
        }catch (SQLException e){
            System.out.println("FALLO BORRAR"+e);
        }
    }
}
