package ninja.harmless.nyx.publish

import ninja.harmless.nyx.movie.model.Movie
import ninja.harmless.nyx.publish.exception.PublishRequestDeniedException
import ninja.harmless.nyx.remote.HttpListenableFutureAware
import ninja.harmless.nyx.remote.HttpRequestData
import ninja.harmless.nyx.subscribe.SubscriptionService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

import static org.springframework.http.HttpMethod.POST
import static org.springframework.http.MediaType.APPLICATION_JSON
/**
 * @author bnjm@harmless.ninja - 1/9/17.
 */
@Component
class PublishServiceImpl extends HttpListenableFutureAware<Movie> implements PublishService {

    private SubscriptionService subscriptionService

    PublishServiceImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService
    }

    @Override
    void publish(Movie movie, List<NyxMicroservice> services) {
        services.each { service ->
            executeHttpRequest(service.addresses.first(), movie)
        }
    }

    @Override
    void publish(Movie movie, NyxMicroservice service) {
        executeHttpRequest(service.addresses.first(), movie)
    }

    @Override
    void publish(Movie movie) {
        Set<NyxMicroservice> microservices = subscriptionService.provideSubscribedServices()
        microservices.each { service ->
            executeHttpRequest(service.addresses.first(), movie)
        }
    }

    private void executeHttpRequest(String url, Movie movie) {
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(APPLICATION_JSON)

        HttpRequestData.HttpRequestDataBuilder builder = new HttpRequestData.HttpRequestDataBuilder(url)
        HttpRequestData requestData = builder.withHttpHeaders(headers)
                .withBody(movie).withHttpMethod(POST)
                .withResponseType(String.class)
                .build()

        ResponseEntity<Movie> response = collectFromListenableFuture(makeHttpRequest(requestData))

        if(response.statusCode != HttpStatus.OK) {
            throw new PublishRequestDeniedException("Service (${url}) denied the published request")
        }
    }
}
