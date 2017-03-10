package ninja.harmless.nyx.movie

import ninja.harmless.nyx.movie.model.Movie

/**
 * This service sends (publishes) an acquired movie object (either from the database or the omdb) to
 * all subscribed nyx microservices.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/10/16.
 */
@Deprecated
interface MoviePublishService {

    /**
     * Sends the movie DTO to a given address
     * @param movie
     */
    void publish(Movie movie)
}