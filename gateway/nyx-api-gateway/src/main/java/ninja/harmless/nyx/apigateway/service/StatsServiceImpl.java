package ninja.harmless.nyx.apigateway.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import ninja.harmless.nyx.apigateway.catalogue.MovieStatistic;
import ninja.harmless.nyx.remote.HttpListenableFutureAware;
import ninja.harmless.nyx.remote.HttpRequestData;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@Service
public class StatsServiceImpl extends HttpListenableFutureAware<MovieStatistic> implements StatsService {
    @Override
    @HystrixCommand(fallbackMethod = "stub")
    public MovieStatistic getMovieStatistic(String id) {
        HttpRequestData requestData = new HttpRequestData.HttpRequestDataBuilder("http://localhost:8081/stats/" + id)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(MovieStatistic.class)
                .build();
        ResponseEntity<MovieStatistic> entity = collectFromListenableFuture2(makeHttpRequest(requestData));
        return entity.getBody();
    }

    private MovieStatistic stub(String id) {
        MovieStatistic stub = new MovieStatistic();
        stub.setVisits(9999);
        return stub;
    }
}
