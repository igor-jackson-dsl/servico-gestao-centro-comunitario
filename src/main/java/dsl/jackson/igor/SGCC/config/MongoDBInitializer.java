package dsl.jackson.igor.SGCC.config;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class MongoDBInitializer implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) {
        MongoDatabase database = mongoTemplate.getDb();
        String collectionName = "recursos"; //coleção base de recursos

        if (database.listCollectionNames().into(new ArrayList<>()).contains(collectionName)) {
            // Coleção já existe, não faz nada
            return;
        }

        // Coleção não existe, cria a coleção e insere os documentos
        MongoCollection<org.bson.Document> collection = database.getCollection(collectionName);

        // Crie e insire os documentos dos recursos padroes na base
        org.bson.Document doc1 = new org.bson.Document("codRecurso", 10).append("nome", "Médico").append("pontos",4);
        org.bson.Document doc2 = new org.bson.Document("codRecurso", 20).append("nome", "Voluntário").append("pontos",3);
        org.bson.Document doc3 = new org.bson.Document("codRecurso", 30).append("nome", "Kit de suprimentos médicos").append("pontos",7);
        org.bson.Document doc4 = new org.bson.Document("codRecurso", 40).append("nome", "Veículo de transporte").append("pontos",5);
        org.bson.Document doc5 = new org.bson.Document("codRecurso", 50).append("nome", "Cesta básica").append("pontos",2);


        collection.insertMany(Arrays.asList(doc1,doc2,doc3,doc4,doc5));
    }
}
