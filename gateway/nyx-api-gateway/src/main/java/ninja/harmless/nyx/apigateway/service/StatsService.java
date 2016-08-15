package ninja.harmless.nyx.apigateway.service;

import ninja.harmless.nyx.apigateway.catalogue.MovieStatistic;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
public interface StatsService {
    MovieStatistic getMovieStatistic(String id);
}
