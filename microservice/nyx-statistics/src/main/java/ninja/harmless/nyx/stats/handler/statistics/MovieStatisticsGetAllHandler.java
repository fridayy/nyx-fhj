package ninja.harmless.nyx.stats.handler.statistics;

import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.mongo.MongoClient;
import io.vertx.rxjava.ext.web.RoutingContext;

import static ninja.harmless.nyx.stats.utils.CommonConstants.STATS_COLLECTION;

/**
 * Stateful Vert.x Http Handler which queries all statistics from the database and
 * provides them as a REST endpoint.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/9/16.
 */
public class MovieStatisticsGetAllHandler implements Handler<RoutingContext> {

    private MongoClient mongoClient;

    public MovieStatisticsGetAllHandler(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void handle(RoutingContext event) {
        mongoClient.findObservable(STATS_COLLECTION, new JsonObject()).subscribe(
          result -> { event.response().end(Json.encodePrettily(result)); }
        );

    }
}
