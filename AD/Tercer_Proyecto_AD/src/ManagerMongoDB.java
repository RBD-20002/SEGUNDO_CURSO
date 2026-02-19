import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ManagerMongoDB {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public ManagerMongoDB() throws Exception {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            mongoDatabase = mongoClient.getDatabase("Clientes");
            MongoCollection<Document> collection = mongoDatabase.getCollection("clientes");

            collection.deleteMany(new Document());

            String rutaProyecto = System.getProperty("user.dir");
            String rutaJson = rutaProyecto + "/clientes.json";
            String json = new String(Files.readAllBytes(Paths.get(rutaJson)));

            List<Document> documentos = Document.parse("{data:" + json + "}").getList("data", Document.class);
            collection.insertMany(documentos);
            System.out.println("BD Clientes recargada desde clientes.json");
        } catch (Exception e) {
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

    public Integer filtrarEmail(String email){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");
            Document clienteBuscasdo = clientes.find(Filters.eq("email",email)).first();

            if(clienteBuscasdo == null){
                System.out.println("No se encontro al cliente, asi que no se pudo conseguir la ID");
                return null;
            }

            Integer id = clienteBuscasdo.getInteger("_id");
            System.out.println("Cliente encontrado: "+clienteBuscasdo.getString("nombre")+" | ID: "+id);
            return id;

        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    /*----------------------------------MODIFICAR CAMPO---------------------------------*/

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

    /*--------------------------------------------------------------------------------------*/

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

            System.out.println("Carrito del cliente: ");
            System.out.println(carrito);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void mostrarPedidoCliente(int id){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");

            Document cliente = clientes.find(Filters.eq("_id",id)).first();
            if(cliente == null){
                System.out.println("No se encontro cliente con id "+id);
                return;
            }

            System.out.println("Pedidos del cliente "+cliente.getString("nombre"));

            List<Document> pedidos = (List<Document>) cliente.get("pedidos");
            if (pedidos == null || pedidos.isEmpty()) {
                System.out.println("El cliente no tiene pedidos.");
                return;
            }
            System.out.println(pedidos);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void pagarCarrito(int clienteId) {
        try {
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");

            Document cliente = clientes.find(Filters.eq("_id", clienteId)).first();
            if (cliente == null) {
                System.out.println("No se encontro cliente con id: " + clienteId);
                return;
            }

            ArrayList<Document> carrito = (ArrayList<Document>) cliente.get("carrito");
            if (carrito == null || carrito.isEmpty()) {
                System.out.println("El carrito esta vacio.");
                return;
            }

            double total = 0;
            for (Document producto : carrito) {
                int cantidad = producto.getInteger("cantidad");
                double precio = producto.getDouble("precio_unitario");
                total += cantidad * precio;
            }

            ArrayList<Document> pedidos = (ArrayList<Document>) cliente.get("pedidos");
            int nuevoPedidoId = (pedidos == null) ? 1 : pedidos.size() + 1;

            Document nuevoPedido = new Document()
                    .append("pedido_id", nuevoPedidoId)
                    .append("productos", carrito)
                    .append("total", total)
                    .append("fecha_pedido", new java.util.Date());

            clientes.updateOne(
                    Filters.eq("_id", clienteId),
                    new Document("$push", new Document("pedidos", nuevoPedido))
            );

            clientes.updateOne(
                    Filters.eq("_id", clienteId),
                    new Document("$set", new Document("carrito", new ArrayList<>()))
            );

            System.out.println("Pedido realizado correctamente. Total: " + total);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void consulta1(){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");
            FindIterable<Document> listaClientes = clientes.find();

            List<Document> resultados = new ArrayList<>();

            for(Document cliente : listaClientes){
                String nombre = cliente.getString("nombre");

                List<Document> carrito = (List<Document>) cliente.get("carrito");
                double totalCarrito = 0;

                if(carrito != null){
                    for(Document producto : carrito){
                        Double precio = producto.getDouble("precio_unitario");
                        if(precio != null){
                            totalCarrito +=precio;
                        }
                    }
                }

                Document resultadoFinal = new Document()
                        .append("nombre",nombre)
                        .append("totalCarrito",totalCarrito);
                resultados.add(resultadoFinal);
            }

            resultados.sort(Comparator.comparingDouble(cliente -> cliente.getDouble("totalCarrito")));

            for(Document resultado : resultados){
                System.out.println("Cliente: "+resultado.getString("nombre")+" | Total Carrito: "+resultado.getDouble("totalCarrito"));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta2(){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");
            FindIterable<Document> listaClientes = clientes.find();

            for(Document cliente : listaClientes){
                String nombre = cliente.getString("nombre");

                List<Document> pedidos = (List<Document>) cliente.get("pedidos");
                double total = 0;

                if(pedidos != null){
                    for(Document pedido : pedidos){
                        Double totalPedido = pedido.getDouble("total");
                        if(totalPedido != null){
                            total += totalPedido;
                        }
                    }
                }
                System.out.println("Cliente: " + nombre + " | Total gastado: " + total);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void elimminarCliente(int id){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");

            DeleteResult borrarCliente = clientes.deleteOne(Filters.eq("_id",id));
            if(borrarCliente.getDeletedCount() == 0){
                System.out.println("No se encontro el cliente el cual eliminar");
            }else System.out.println("Se elimino el cliente con id "+id);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    /*-----------------------------------------------------------------------------------*/

    public void listarCliente(){
        try{
            MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");

            FindIterable<Document> resultados = clientes.find().projection(Projections.fields(Projections.include("_id","nombre")));

            for(Document cliente : resultados){
                System.out.println("ID: "+cliente.getInteger("_id")+" | Nombre: "+cliente.getString("nombre"));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}