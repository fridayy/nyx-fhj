package ninja.harmless.nyx.movie.repository

import ninja.harmless.nyx.movie.model.Movie
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository

import java.util.concurrent.CompletableFuture

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
@Document(collection = "movies")
interface MovieRepository extends MongoRepository<Movie, String> {

    CompletableFuture<Movie> findByTitleIgnoreCase(String title)
}
