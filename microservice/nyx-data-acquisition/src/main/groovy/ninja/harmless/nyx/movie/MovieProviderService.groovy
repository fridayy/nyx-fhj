package ninja.harmless.nyx.movie

import ninja.harmless.nyx.movie.model.Movie

/**
 * Provides Movies acquired either directly from the MongoDB or from
 * the open movie database (http://www.omdbapi.com/)
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
interface MovieProviderService {

    /**
     * Provides a movie DTO by either acquisition from the database or by assembling it with data from the
     * open movie database. (http://www.omdbapi.com/)
     * @param title
     * @return
     */
    Movie provideByTitle(String title)

    Movie provideById(String id)

    /**
     * Provides all movies currently stored within the database
     */
    Iterable<Movie> provideAll()
}