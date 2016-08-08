package ninja.harmless.nyx.movie.service

import ninja.harmless.nyx.jsonmapper.JsonMapper
import ninja.harmless.nyx.movie.MovieRemoteAcquisitionService
import ninja.harmless.nyx.movie.model.Movie
import ninja.harmless.nyx.movie.repository.MovieRepository
import ninja.harmless.nyx.remote.HttpListenableFutureAware
import ninja.harmless.nyx.remote.HttpRequestData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
/**
 * Acquires movie data from the open movie database api
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
@Service
class MovieRemoteAcquisitionServiceImpl extends HttpListenableFutureAware<String> implements MovieRemoteAcquisitionService{

    MovieRepository repository
    String url = "http://www.omdbapi.com/?t="

    @Autowired
    public MovieRemoteAcquisitionServiceImpl(MovieRepository repository) {
        this.repository = repository
    }

    @Override
    Movie acquireByTitle(Object title) {
        HttpRequestData requestData = new HttpRequestData.HttpRequestDataBuilder(url + title)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(String.class)
                .build();
        ResponseEntity<String> entity = collectFromListenableFuture(makeHttpRequest(requestData))
        Movie movie = JsonMapper.mapObject(entity.getBody(), Movie);
        repository.save(movie)
        return movie
    }
}
