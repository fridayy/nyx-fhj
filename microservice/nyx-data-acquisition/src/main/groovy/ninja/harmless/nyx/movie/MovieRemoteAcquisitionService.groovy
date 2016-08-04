package ninja.harmless.nyx.movie

import ninja.harmless.nyx.movie.dto.Movie
/**
 * Service which is used to acquire movie data from the open movie database as a fallback method
 * if a movie is not available in the database.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
interface MovieRemoteAcquisitionService {

    /**
     * Acquires a Movie DTO from the data provided by the open movie database by looking for the title
     * @param title
     * @return
     */
    Movie acquireByTitle(title)
}