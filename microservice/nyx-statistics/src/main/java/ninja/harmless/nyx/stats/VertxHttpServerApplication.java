package ninja.harmless.nyx.stats;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.mongo.MongoClient;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import ninja.harmless.nyx.stats.data.MongoConnection;
import ninja.harmless.nyx.stats.data.movie.Movie;
import org.springframework.stereotype.Component;

/**
 * Main class for the Vert.x Service running inside a Spring Boot container.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/9/16.
 */
@Component
public class VertxHttpServerApplication extends AbstractVerticle {


    @Override
    public void start() throws Exception {


        MongoConnection conn = new MongoConnection();
        MongoClient mongo = conn.connect(vertx);

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/test").handler(routingCtx -> {
            routingCtx.response().putHeader("content-type", "text/html").end("hasdasd");
        });

        router.post("/stats").handler(routingCtx -> {
            Movie stats = Json.decodeValue(routingCtx.getBodyAsString(), Movie.class);

            JsonObject query = new JsonObject().put("_id", stats.getId());

            mongo.findOneObservable("statistics", query, null).subscribe(
                    entries -> {
                        if (entries != null) {
                            mongo.saveObservable("statistics", entries.put("visits", ((int) entries.getValue("visits")) + 1));
                        } else {
                            mongo.saveObservable("statistics", new JsonObject().put("_id", stats.getId()).put("visits", 1));
                        }
                    },
                    error -> {
                        error.printStackTrace();
                    },
                    () -> {
                        routingCtx.response().setStatusCode(200);
                    }
            );
        });


//        router.get("/movie").handler(MovieStatisticsHandler::handler);
        router.get("/movie/:title").handler(routingCtx -> {
            String title = routingCtx.request().getParam("title");
            JsonObject query = new JsonObject().put("title", title);
            JsonArray results = new JsonArray();
            mongo.findObservable("movies", query).subscribe(
                    entries -> {
                        results.add(entries);
                    },
                    error -> {
                        error.printStackTrace();
                    },
                    () -> {
                        routingCtx.response().putHeader("Content-Type", "application/json").write(results.encodePrettily()).end();
                    }
            );
        });
        server.requestHandler(router::accept).listen(8081);
    }
}
