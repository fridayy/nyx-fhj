package ninja.harmless.nyx.apigateway.models;

import ninja.harmless.nyx.apigateway.catalogue.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/8/16.
 */
public class MovieDetails {

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    private List<Movie> movies = new ArrayList<Movie>();
}
