package ninja.harmless.nyx.stats;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.mongo.MongoClient;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import ninja.harmless.nyx.stats.data.MongoConnection;
import ninja.harmless.nyx.stats.data.MovieStatistic;
import org.springframework.stereotype.Component;
import rx.Subscriber;

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

        router.post("/movie").handler(routingCtx -> {
                    MovieStatistic stats = Json.decodeValue(routingCtx.getBodyAsString(), MovieStatistic.class);

                    JsonObject query = new JsonObject().put("_id", stats.getId());

//            mongo.updateObservable("statistics", query, null).flatMap(
//                    obj -> mongo.saveObservable("statistics", new JsonObject().put("_id", stats.getId()).put("visits", ((long) obj.getValue("visits")) + 1))
//            ).subscribe(
//                    res -> { System.out.print("updated");
//                    }, error -> { System.out.print("error");
//                        error.printStackTrace();
//                    }, () -> { routingCtx.response().end(); }
//            );});

                mongo.findOneObservable("statistics", query, null).subscribe(
                        new Subscriber<JsonObject>() {
                            @Override
                            public void onCompleted() {
                                System.out.print("Completed");
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.print("No: ");
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(JsonObject entries) {
                                System.out.print("Found: " + entries.toString());
                            }
                        }
                );
//
//                    mongo.findOneObservable("statistics", query, null)
//                            .subscribe(result -> { mongo.saveObservable("statistics", result.put("visits", ((int) result.getValue("visits")) + 1)); },
//                                    error -> error.printStackTrace());

                routingCtx.response().end();
                });
        server.requestHandler(router::accept).listen(8081);
    }
}
