package ninja.harmless.nyx.apigateway.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ninja.harmless.nyx.apigateway.catalogue.Movie;
import ninja.harmless.nyx.remote.HttpListenableFutureAware;
import ninja.harmless.nyx.remote.HttpRequestData;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/8/16.
 */
@Service
public class CatalogueServiceImpl extends HttpListenableFutureAware<Movie> implements CatalogueService {
    @Override
    @SuppressWarnings("unchecked")
    @HystrixCommand(fallbackMethod = "stub")
    public Movie getMovie(String title) {
        HttpRequestData requestData = new HttpRequestData.HttpRequestDataBuilder("http://localhost:8000/catalogue/movies" + title)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(Movie.class)
                .build();
        ResponseEntity<Movie> entity = collectFromListenableFuture2(makeHttpRequest(requestData));
        return entity.getBody();
    }

    private Movie stub(String title) {
        Movie stub = new Movie();
        stub.setTitle("Sorry we are busy...");
        return stub;
    }
}
