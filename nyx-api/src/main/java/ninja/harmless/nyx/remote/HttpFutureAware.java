package ninja.harmless.nyx.remote;

import org.springframework.http.HttpEntity;

import java.util.concurrent.Future;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
public interface HttpFutureAware<T> {

    /**
     * Collects and HttpEntity from a given future
     * @param future
     * @param <S>
     * @return
     */
    <S extends HttpEntity<T>> S collectFromListenableFuture(Future<S> future);

    /**
     * Sends an Http request with header data, url etc. taken from the HttpRequestData object which
     * is assembled via its builder.
     * @param requestData
     * @return
     */
    Future<T> makeHttpRequest(HttpRequestData requestData);
}
