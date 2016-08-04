package ninja.harmless.nyx.movie.dto

import ninja.harmless.nyx.data.JsonDTO
import org.springframework.data.annotation.Id

/**
 * Data Transfer Object for MongoDB.
 * Holds all the neccessary data which can be persisted in mongo by
 * using the Spring Mongo repository.
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
@JsonDTO
class Movie {
    @Id
    String id

    String title
    String plot
    String year
    String director
    String imdbRating
    String actors

}
