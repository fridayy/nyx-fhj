package ninja.harmless.nyx.trailer;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import ninja.harmless.nyx.jsonmapper.JsonMapper;
import ninja.harmless.nyx.trailer.movie.data.DTO.Movie;
import ninja.harmless.nyx.trailer.movie.data.DTO.Trailer;
import ninja.harmless.nyx.trailer.movie.data.RemoteTrailerAcquisition;
import ninja.harmless.nyx.trailer.movie.data.RemoteTrailerAcquisitionImpl;
import ninja.harmless.nyx.trailer.movie.data.Subscribe;
import ninja.harmless.nyx.trailer.movie.data.SubscriptionRequest;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static ninja.harmless.nyx.trailer.utils.CommonConstants.TRAILER_ENDPOINT;
import static spark.Spark.*;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/12/16.
 */
@Component
public class SparkApplication {

    RemoteTrailerAcquisition rat = new RemoteTrailerAcquisitionImpl();
    Trailer trailer;

    public void handleRoutes() {
        try {
            Thread.sleep(5000);
            subscribe();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        port(9090);
        get(TRAILER_ENDPOINT, (req, res) -> {
            res.type("application/json");
            String response = "";
            if(trailer != null) {
                 response = JsonMapper.writeObject(trailer);
                 res.status(200);
            } else {
                res.status(404);
            }
            return response;
        });

        post(TRAILER_ENDPOINT, (req, res) -> {
            Movie movie = JsonMapper.mapObject(req.body(), Movie.class);
            trailer = rat.acquireTrailerRemotly(movie);
            return "";
        });
    }

    private void subscribe() {
        Subscribe s = () -> {
            SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
            subscriptionRequest.setServiceName("NYX-TRAILER-SERVICE");
            subscriptionRequest.setSubscriptionEndpoint(TRAILER_ENDPOINT);
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            Future<Response> r = asyncHttpClient.preparePost("http://localhost:8000/subscribe")
                    .setHeader("Content-Type", "application/json")
                    .setBody(JsonMapper.writeObject(subscriptionRequest))
                    .execute();
            try {
                Response response = r.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        };
        s.subscribeToAcquisitionService();
    }
}
