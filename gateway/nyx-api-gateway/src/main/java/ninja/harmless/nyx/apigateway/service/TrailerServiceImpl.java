package ninja.harmless.nyx.apigateway.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ninja.harmless.nyx.apigateway.catalogue.MovieTrailer;
import ninja.harmless.nyx.remote.HttpListenableFutureAware;
import ninja.harmless.nyx.remote.HttpRequestData;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@Service
public class TrailerServiceImpl extends HttpListenableFutureAware<MovieTrailer> implements TrailerService {
    @Override
    @HystrixCommand(fallbackMethod = "stub")
    public MovieTrailer getTrailer() {
        HttpRequestData requestData = new HttpRequestData.HttpRequestDataBuilder("http://localhost:9090/trailer")
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(MovieTrailer.class)
                .build();
        ResponseEntity<MovieTrailer> entity = collectFromListenableFuture2(makeHttpRequest(requestData));
        return entity.getBody();
    }

    private MovieTrailer stub() {
        MovieTrailer stub = new MovieTrailer();
        stub.setUrl("Sorry...");
        return stub;
    }
}
