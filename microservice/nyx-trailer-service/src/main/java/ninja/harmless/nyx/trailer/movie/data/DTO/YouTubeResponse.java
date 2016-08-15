package ninja.harmless.nyx.trailer.movie.data.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import ninja.harmless.nyx.data.JsonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@JsonDTO
public class YouTubeResponse {
    @JsonProperty("items")
    List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
