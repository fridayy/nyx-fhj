package ninja.harmless.nyx.movie.service

import groovy.transform.TypeChecked
import ninja.harmless.nyx.movie.MovieRemoteAcquisitionService
import ninja.harmless.nyx.movie.dto.Movie
import ninja.harmless.nyx.movie.repository.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.concurrent.CompletableFuture

/**
 * MovieProviderService implementation class
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
@Service
@TypeChecked
class MovieProviderServiceImpl implements ninja.harmless.nyx.movie.MovieProviderService {

    MovieRepository repository
    MovieRemoteAcquisitionService remoteAcquisition

    @Autowired
    public MovieProviderServiceImpl(MovieRepository repository, MovieRemoteAcquisitionService remoteAcquisiton) {
        this.repository = repository
        this.remoteAcquisition = remoteAcquisiton
    }

    /**
     * @inheritDoc
     */
    @Override
    Movie provideByTitle(String title) {
        CompletableFuture<Movie> providedMovie = repository.findByTitleIgnoreCase(title)
        if(Objects.isNull(providedMovie?.get())) {
            return remoteAcquisition.acquireByTitle(title)
        }
        return providedMovie.get()
    }
}