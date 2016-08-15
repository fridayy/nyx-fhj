package ninja.harmless.nyx.trailer.movie.data.DTO;

import ninja.harmless.nyx.data.JsonDTO;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/12/16.
 */
@JsonDTO
public class Movie {

    private String id;
    private String title;
    private String plot;
    private String year;
    private String director;
    private String imdbRating;
    private String actors;
    private long visits;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public long getVisits() {
        return visits;
    }

    public void setVisits(long visits) {
        this.visits = visits;
    }
}
