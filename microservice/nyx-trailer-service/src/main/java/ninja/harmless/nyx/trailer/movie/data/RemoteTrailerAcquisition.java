package ninja.harmless.nyx.trailer.movie.data;

import ninja.harmless.nyx.trailer.movie.data.DTO.Movie;
import ninja.harmless.nyx.trailer.movie.data.DTO.Trailer;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
public interface RemoteTrailerAcquisition {
    Trailer acquireTrailerRemotly(Movie movie);
}
