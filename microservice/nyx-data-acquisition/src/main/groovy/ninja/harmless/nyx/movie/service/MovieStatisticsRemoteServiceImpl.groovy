package ninja.harmless.nyx.movie.service

import ninja.harmless.nyx.jsonmapper.JsonMapper
import ninja.harmless.nyx.movie.MovieStatisticsRemoteService
import ninja.harmless.nyx.movie.model.Movie
import ninja.harmless.nyx.remote.HttpListenableFutureAware
import ninja.harmless.nyx.remote.HttpRequestData
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/10/16.
 */
@Service
class MovieStatisticsRemoteServiceImpl extends HttpListenableFutureAware<Movie> implements MovieStatisticsRemoteService {
    @Override
    void send(Movie movie) {
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        HttpRequestData requestData = new HttpRequestData.HttpRequestDataBuilder("http://localhost:8081/stats")
                .withHttpMethod(HttpMethod.POST)
                .withHttpHeaders(headers)
                .withBody(JsonMapper.writeObject(movie)) //TODO: send with headers, remove hardcoded url
                .build()
        makeHttpRequest(requestData)
    }
}
