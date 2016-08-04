package ninja.harmless.nyx.remote;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
public interface HttpFutureAware<T> {
    <S extends HttpEntity<T>> S collectFromListenableFuture(Future<S> future);
    Future<T> makeHttpRequest(HttpRequestData requestData);
}
