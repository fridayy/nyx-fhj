package ninja.harmless.nyx.apigateway.service;

import ninja.harmless.nyx.apigateway.catalogue.Movie;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/8/16.
 */
public interface CatalogueService {
    Movie getMovie(String title);
}
