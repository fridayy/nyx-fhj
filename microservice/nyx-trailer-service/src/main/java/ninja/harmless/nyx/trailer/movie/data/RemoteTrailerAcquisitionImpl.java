package ninja.harmless.nyx.trailer.movie.data;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import ninja.harmless.nyx.jsonmapper.JsonMapper;
import ninja.harmless.nyx.trailer.movie.data.DTO.Movie;
import ninja.harmless.nyx.trailer.movie.data.DTO.Trailer;
import ninja.harmless.nyx.trailer.movie.data.DTO.YouTubeResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static ninja.harmless.nyx.trailer.utils.CommonConstants.YOU_TUBE_URL;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
public class RemoteTrailerAcquisitionImpl implements RemoteTrailerAcquisition {
    @Override
    public Trailer acquireTrailerRemotly(Movie movie) {
        String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q=" + movie.getTitle() +"+Trailer&regionCode=AT&key=AIzaSyAnCh7vWudYYA272wK8ptp3PSoO4HkSu5s";

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        Future<Response> res = asyncHttpClient.prepareGet(url).execute();
        Response response = null;
        try {
            response = res.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        YouTubeResponse youTubeResponse = null;
        try {
            youTubeResponse = JsonMapper.mapObject(response.getResponseBody(), YouTubeResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Trailer trailer = new Trailer();

        trailer.setUrl(YOU_TUBE_URL + youTubeResponse.getItems().get(0).getId().getVideoId());

        return trailer;
    }
}
