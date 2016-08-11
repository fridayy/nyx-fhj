package ninja.harmless.nyx.stats.handler.statistics;

import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.mongo.MongoClient;
import io.vertx.rxjava.ext.web.RoutingContext;
import ninja.harmless.nyx.stats.data.movie.Movie;

/**
 * Stateful Vertx handler which creates or updates a statistics entity in the database depending on what he receives.
 * This Handler is a subscriber which just sits and listens for any JSON representing a movie object which he then
 * creates or updates a statistic for.
 *
 * The nyx-data-acquisiton handler is the producer which emmits the movie object that was requested.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/11/16.
 */
public class MovieStatisticsVisitsHandler implements Handler<RoutingContext> {

    MongoClient mongoClient;

    public MovieStatisticsVisitsHandler(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void handle(RoutingContext event) {
        Movie stats = Json.decodeValue(event.getBodyAsString(), Movie.class);
        JsonObject query = new JsonObject().put("_id", stats.getId());

        mongoClient.findOneObservable("statistics", query, null).subscribe(
                entries -> {
                    if (entries != null) {
                        mongoClient.saveObservable("statistics", entries.put("visits", ((int) entries.getValue("visits")) + 1));
                    } else {
                        mongoClient.saveObservable("statistics", new JsonObject().put("_id", stats.getId()).put("visits", 1));
                    }
                },
                error -> {
                    error.printStackTrace();
                },
                () -> {
                    event.response().setStatusCode(200);
                }
        );
    }
}
