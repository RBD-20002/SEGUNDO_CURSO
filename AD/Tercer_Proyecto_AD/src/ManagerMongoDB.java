import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.basex.core.cmd.List;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;


public class ManagerMongoDB {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public ManagerMongoDB() throws Exception {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            mongoDatabase = mongoClient.getDatabase("Clientes");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void agregarCliente(int id, String nombre, String email, String direccion){
        try {
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");
            if (clientes.find(Filters.eq("email", email)).first() != null) {
                System.out.println("Se encontro un cliente con ese email: " + email);
                return;
            }

            Document nuevoCliente = new Document()
                    .append("_id",id)
                    .append("nombre",nombre)
                    .append("email",email)
                    .append("direccion",direccion)
                    .append("carrito", new ArrayList<>())
                    .append("pedidos", new ArrayList<>());

            clientes.insertOne(nuevoCliente);
            System.out.println("Cliente se agrego correctamente");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void filtrarEmail(String email){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");
            Document clienteBuscasdo = clientes.find(Filters.eq("email",email)).first();

            if(clienteBuscasdo == null){
                System.out.println("No se encontro al cliente, asi que no se pudo conseguir la ID");
                return;
            }
            ObjectId id = clienteBuscasdo.getObjectId("_id");
            System.out.println("Cliente encontrado: "+clienteBuscasdo.getString("nombre")+" | ID: "+id);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /*-----------------------MODIFICAR CAMPO-----------------------*/

    public void modificarCampo(int id, String campo, Object nuevoValor){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");

            Document clienteBuscado = clientes.find(Filters.eq("_id",id)).first();
            if(clienteBuscado == null){
                System.out.println("No se el cliente el cual se va a modificar los datos");
                return;
            }

            clientes.updateOne(Filters.eq("_id",id),new Document("$set", new Document(campo,nuevoValor)));
            System.out.println("Se modifico campo: "+campo+" a nuevo valor de: "+nuevoValor);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public String obtenerCampoModificable(int opcion){
        return switch (opcion){
            case 1 -> "nombre";
            case 2 -> "email";
            case 3 -> "direccion";
            default -> null;
        };
    }

    /*-------------------------------------------------------------------*/

    public void mostrarCarritoCliente(int id){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");

            Document clienteBuscado = clientes.find(Filters.eq("_id",id)).first();
            if(clienteBuscado == null){
                System.out.println("No se encontro cliente con id "+id);
                return;
            }

            ArrayList<Document> carrito = (ArrayList<Document>) clienteBuscado.get("carrito");
            if(carrito.isEmpty()){
                System.out.println("El carrito del cliente esta vacio");
                return;
            }

            double total = 0;
            System.out.println("Carrito del cliente: ");
            for(Document producto : carrito){
                int cantidad = producto.getInteger("cantidad");
                double precio = producto.getDouble("precio_unitario");
                System.out.println("Producto: "+producto.getString("nombre")+" | Cantidad: "+cantidad+" | Precio Unitario: "+precio);
                total += precio * cantidad;
            }

            System.out.println("Total del carrito es: "+total);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void mostrarPedidoCliente(){
    }

    /*------------------------------METODOS DE BaseX + MongoDB------------------------------*/

    public void agregarProductoCarrito(int clienteId, int productoId, int cantidad, ManagerBaseX managerBaseX){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");

            Document clienteBuscado = clientes.find(Filters.eq("_id",clienteId)).first();
            if(clienteBuscado == null){
                System.out.println("No se encontro cliente con id: "+clienteId);
                return;
            }

            String productosXml = managerBaseX.listarProductos();
            if (productosXml == null || productosXml.isEmpty()) {
                System.out.println("No hay productos en BaseX.");
                return;
            }

            String productoBloque = null;
            for (String producto : productosXml.split("</producto>")) {
                if (producto.contains("<id>" + productoId + "</id>")) {
                    productoBloque = producto + "</producto>";
                    break;
                }
            }

            if (productoBloque == null) {
                System.out.println("Producto con ID " + productoId + " no existe en BaseX.");
                return;
            }

            String nombreProducto = productoBloque.split("<nombre>")[1].split("</nombre>")[0];
            double precioUnitario = Double.parseDouble(productoBloque.split("<precio>")[1].split("</precio>")[0]);

            Document producto = new Document()
                    .append("producto_id", productoId)
                    .append("nombre", nombreProducto)
                    .append("cantidad", cantidad)
                    .append("precio_unitario", precioUnitario);

            clientes.updateOne(Filters.eq("_id", clienteId), new Document("$push", new Document("carrito", producto)));

            System.out.println("Producto '" + nombreProducto + "' agregado al carrito del cliente: " + clienteBuscado.getString("nombre"));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /*---------------------------------------------------------------------------------------------------------*/

    public void pagarCarrito(){
    }

    public void consulta1(){
    }

    public void consulta2(){
    }

    public void elimminarCliente(int id){

    }
}