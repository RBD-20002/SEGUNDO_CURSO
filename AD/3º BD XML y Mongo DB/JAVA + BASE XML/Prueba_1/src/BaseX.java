import org.basex.api.client.ClientSession;
import org.basex.core.cmd.XQuery;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BaseX {

    private ClientSession session;

    public BaseX() {
        try {
            session = new ClientSession("localhost", 1984, "admin", "admin");

            session.execute("OPEN clientes");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void agregarCliente(Cliente cliente) {
        try {
            String nodoXML = cliente.toXML();
            InputStream is = new ByteArrayInputStream(nodoXML.getBytes());

            String path = "clientes/cliente_"+cliente.getId()+".xml";

            session.add(path,is);

            System.out.println("CLIENTE AÑADIDO: "+path);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void listarClientes(){
        try{
            String xQuery = "xquery collection('clientes')";

            System.out.println(session.execute(xQuery));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void eliminarCliente(int id){
        try{
            String path = "clientes/cliente_"+id+".xml";

            session.execute("xquery db:delete('clientes','"+path+"')");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void filtrarCliente(String id){
        try{
            String xQuery = "for $cliente in collection('clientes')/clientes/cliente " +
                    "where $cliente/@id = '" + id + "' " +
                    "return <Cliente>" +
                    "<nombre>{$cliente/nombre}</nombre>" +
                    "</Cliente>";
            String resultado = session.execute("xquery "+xQuery);
            System.out.println(resultado);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void modificarEdad(String id, int edad){
        try{
            String xQuery = "replace value of node "+
                    "collection('clientes')/clientes/cliente[@id='" +id+"']/edad "+
                    "with "+edad;
            String resultado = session.execute("xquery "+xQuery);
            System.out.println(resultado);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void eliminarSegunNacionalidad(String nacionalidad){
        try{
            String xQuery = "delete nodes collection('clientes')/clientes/cliente[@nacionalidad='"+ nacionalidad +"']";

            String resultado = session.execute("xquery "+xQuery);
            System.out.println(resultado);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
