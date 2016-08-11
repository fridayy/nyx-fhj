package ninja.harmless.nyx.stats.utils;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/11/16.
 */
public final class CommonConstants {
    /**
     * Rest Endpoints
     */
    public static final String STATS_ENDPOINT = "/stats";
    public static final String STATS_ID_ENDPOINT = "/stats/:id";
    public static final String MOVIE_TITLE_ENDPOINT = "/movie/:title";
    public static final String MOVIE_ENDPOINT = "/movie";


    /**
     * Mongo Collections
     */
    public static final String STATS_COLLECTION = "statistics";

    /**
     * Content Types
     */
    public static final String APPLICATION_JSON = "application/json";
}
