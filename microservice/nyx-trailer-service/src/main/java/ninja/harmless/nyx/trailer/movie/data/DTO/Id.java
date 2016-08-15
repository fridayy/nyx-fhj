package ninja.harmless.nyx.trailer.movie.data.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
public class Id {
    @JsonProperty("videoId")
    String videoId;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
