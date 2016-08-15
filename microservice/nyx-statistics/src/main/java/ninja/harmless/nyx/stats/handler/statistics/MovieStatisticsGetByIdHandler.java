package ninja.harmless.nyx.stats.handler.statistics;

import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.mongo.MongoClient;
import io.vertx.rxjava.ext.web.RoutingContext;

import static ninja.harmless.nyx.stats.utils.CommonConstants.STATS_COLLECTION;

/**
 * Stateful Vertx Handler that handles the GET Route: /stats/{id}
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/11/16.
 */
public class MovieStatisticsGetByIdHandler implements Handler<RoutingContext> {

    MongoClient mongoClient;

    public MovieStatisticsGetByIdHandler(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void handle(RoutingContext event) {
        String id = event.request().getParam("id");
        JsonObject query = new JsonObject().put("_id", id);

        mongoClient.findOneObservable(STATS_COLLECTION, query, null).subscribe(
               result -> { event.response().end(Json.encode(result));}
        );
    }
}
