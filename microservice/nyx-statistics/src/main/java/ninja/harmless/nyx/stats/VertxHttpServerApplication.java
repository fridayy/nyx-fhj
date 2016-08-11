package ninja.harmless.nyx.stats;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.mongo.MongoClient;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import ninja.harmless.nyx.stats.data.MongoConnection;
import ninja.harmless.nyx.stats.handler.movie.MovieGetByTitleHandler;
import ninja.harmless.nyx.stats.handler.statistics.MovieStatisticsGetAllHandler;
import ninja.harmless.nyx.stats.handler.statistics.MovieStatisticsGetByIdHandler;
import org.springframework.stereotype.Component;

import static ninja.harmless.nyx.stats.utils.CommonConstants.*;

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

        /**
         * Increments the visit counter for the movie send by the spring data acquistion service
         */
        router.post(STATS_ENDPOINT).consumes("application/json").handler(routingCtx -> {

        });
        MovieStatisticsGetAllHandler statsGetAllHandler = new MovieStatisticsGetAllHandler(mongo);
        MovieStatisticsGetByIdHandler statsGetByIdHandler = new MovieStatisticsGetByIdHandler(mongo);
        MovieGetByTitleHandler movieGetByTitleHandler = new MovieGetByTitleHandler(mongo);

        router.get(STATS_ENDPOINT).produces(APPLICATION_JSON).handler(statsGetAllHandler::handle);
        router.get(STATS_ID_ENDPOINT).produces(APPLICATION_JSON).handler(statsGetByIdHandler::handle);
        router.get(MOVIE_TITLE_ENDPOINT).produces(APPLICATION_JSON).handler(movieGetByTitleHandler::handle);


        server.requestHandler(router::accept).listen(8081);
    }
}
