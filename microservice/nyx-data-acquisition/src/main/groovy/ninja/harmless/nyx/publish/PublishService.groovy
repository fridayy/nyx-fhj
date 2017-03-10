package ninja.harmless.nyx.publish

import ninja.harmless.nyx.movie.model.Movie

/**
 * This service sends (publishes) an acquired movie object (either from the database or the omdb) to
 * all subscribed nyx microservices.
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
interface PublishService {
    void publish(Movie movie, List<NyxMicroservice> services)
    void publish(Movie movie, NyxMicroservice service)
    void publish(Movie movie)
}