package ninja.harmless.nyx.stats.data;

import ninja.harmless.nyx.data.JsonDTO;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/9/16.
 */
@JsonDTO
public class MovieStatistic {

    public MovieStatistic() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVisits() {
        return visits;
    }

    public void setVisits(long visits) {
        this.visits = visits;
    }

    private String id;
    private long visits;
}
