package ninja.harmless.nyx.movie

import ninja.harmless.nyx.movie.model.Movie

/**
 * This service sends an acquired movie object (either from the database or the omdb) to the
 * vert.x statistics microservice which then creates a simple set of statistics for the given movie.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/10/16.
 */
interface MovieStatisticsRemoteService {

    /**
     * Sends the movie DTO to a given address
     * @param movie
     */
    void send(Movie movie)
}