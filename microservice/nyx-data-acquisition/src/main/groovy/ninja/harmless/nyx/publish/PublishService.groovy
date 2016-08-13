package ninja.harmless.nyx.publish

import ninja.harmless.nyx.movie.model.Movie

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
interface PublishService {

    void publish(Movie movie, List<NyxMicroservice> services)
}