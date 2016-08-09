package ninja.harmless.nyx.stats.data;


import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.mongo.MongoClient;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/9/16.
 */
public class MongoConnection {
    public MongoClient connect(Vertx vertx) {
        JsonObject config = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "test");
        MongoClient client = MongoClient.createShared(vertx, config);

        return client;
    }
}
