package ninja.harmless.nyx.movie.service

import ninja.harmless.nyx.jsonmapper.JsonMapper
import ninja.harmless.nyx.movie.MoviePublishService
import ninja.harmless.nyx.movie.model.Movie
import ninja.harmless.nyx.remote.HttpListenableFutureAware
import ninja.harmless.nyx.remote.HttpRequestData
import ninja.harmless.nyx.subscribe.SubscriptionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/10/16.
 */
@Service
class MoviePublishServiceImpl extends HttpListenableFutureAware<Movie> implements MoviePublishService {

    SubscriptionService subscriptionService

    @Autowired
    MoviePublishServiceImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService
    }

    @Override
    void publish(Movie movie) {
        subscriptionService.provideSubscribedServices().each {
            publishMovie(movie, it.addresses)
        }
    }

    private void publishMovie(Movie movie, List<String> url) {
        url.each {
            HttpHeaders headers = new HttpHeaders()
            headers.setContentType(MediaType.APPLICATION_JSON)
            HttpRequestData requestData = new HttpRequestData.HttpRequestDataBuilder(it)
                    .withHttpMethod(HttpMethod.POST)
                    .withHttpHeaders(headers)
                    .withBody(JsonMapper.writeObject(movie)) //TODO: publish with headers, remove hardcoded url
                    .build()
            makeHttpRequest(requestData)
        }
    }
}
