package ninja.harmless.nyx.stats.data.stats;

import ninja.harmless.nyx.data.JsonDTO;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/9/16.
 */
@JsonDTO
public class MovieStatistic {

    public MovieStatistic() {

    }

    private String _id;
    private long visits;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public long getVisits() {
        return visits;
    }

    public void setVisits(long visits) {
        this.visits = visits;
    }
}
