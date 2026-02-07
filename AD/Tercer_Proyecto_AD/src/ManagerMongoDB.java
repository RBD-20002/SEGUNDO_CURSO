import com.mongodb.client.*;
import org.bson.Document;

public class ManagerMongoDB {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public ManagerMongoDB() throws Exception{
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        mongoDatabase = mongoClient.getDatabase("Clientes");
    }

    public void agregarCliente(Cliente cliente){
        MongoCollection<Document> clientes = mongoDatabase.getCollection("clientes");
    }
}
