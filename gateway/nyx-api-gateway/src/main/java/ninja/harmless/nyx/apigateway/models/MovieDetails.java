package ninja.harmless.nyx.apigateway.models;

import ninja.harmless.nyx.apigateway.catalogue.Movie;
import ninja.harmless.nyx.apigateway.catalogue.MovieStatistic;
import ninja.harmless.nyx.apigateway.catalogue.MovieTrailer;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/8/16.
 */
public class MovieDetails {
    Movie movie;
    MovieStatistic statistic;
    MovieTrailer trailer;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieStatistic getStatistic() {
        return statistic;
    }

    public void setStatistic(MovieStatistic statistic) {
        this.statistic = statistic;
    }

    public MovieTrailer getTrailer() {
        return trailer;
    }

    public void setTrailer(MovieTrailer trailer) {
        this.trailer = trailer;
    }
}
