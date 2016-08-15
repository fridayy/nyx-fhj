package ninja.harmless.nyx.trailer.movie.data.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import ninja.harmless.nyx.data.JsonDTO;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@JsonDTO
public class Item {
    @JsonProperty("id")
    Id id;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
