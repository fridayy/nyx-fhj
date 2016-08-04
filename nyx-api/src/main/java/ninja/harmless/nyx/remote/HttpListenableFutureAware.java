package ninja.harmless.nyx.remote;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Abstraction on Springs ListenableFutures which allows the client to easily collect
 * data from an http request.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
public class HttpListenableFutureAware<T> implements HttpFutureAware<T> {

    @Override
    public <S extends HttpEntity<T>> S collectFromListenableFuture(Future<S> future) {
        S entity = null;
        try {
            entity = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Future<T> makeHttpRequest(HttpRequestData requestData) {
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        HttpEntity<T> requestEntity = new HttpEntity<>(null, requestData.getHttpHeaders());

        return asyncRestTemplate.exchange(requestData.getUrl(), requestData.getHttpMethod(), requestEntity, requestData.getResponseType());
    }
}
