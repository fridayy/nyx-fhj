package ninja.harmless.nyx.movie.service

import groovy.transform.TypeChecked
import ninja.harmless.nyx.movie.MovieRemoteAcquisitionService
import ninja.harmless.nyx.movie.model.Movie
import ninja.harmless.nyx.movie.repository.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert

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
        Assert.notNull(title, "Title cannot be null")
        CompletableFuture<Movie> providedMovie = repository.findByTitleIgnoreCase(title)
        if(Objects.isNull(providedMovie?.get())) {
            return remoteAcquisition.acquireByTitle(title)
        }
        return providedMovie.get()
    }

    @Override
    Movie provideById(String id) {
        CompletableFuture<Movie> providedMovie = repository.findById(id)
        return providedMovie.get()
    }

    @Override
    Iterable<Movie> provideAll() {
        return repository.findAll()
    }
}
