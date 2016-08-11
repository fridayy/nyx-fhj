package ninja.harmless.nyx.stats.handler.movie;

import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.ext.mongo.MongoClient;
import io.vertx.rxjava.ext.web.RoutingContext;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/11/16.
 */
public class MovieGetByTitleHandler implements Handler<RoutingContext> {

    MongoClient mongoClient;

    public MovieGetByTitleHandler(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void handle(RoutingContext event) {
        String title = event.request().getParam("title");
        JsonObject query = new JsonObject().put("title", title);
        mongoClient.findObservable("movies", query).subscribe(
                result -> {
                    event.response().end(Json.encodePrettily(result));
                }
        );
    }
}
