package ninja.harmless.nyx.remote;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Objects;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
public final class HttpRequestData {
    private String      url;
    private HttpMethod  httpMethod;
    private HttpHeaders httpHeaders;
    private Class       responseType;
    private Object      body;

    private HttpRequestData(HttpRequestDataBuilder builder) {
        this.url = builder.url;
        this.httpMethod = builder.httpMethod;
        this.httpHeaders = builder.httpHeaders;
        this.responseType = builder.responseType;
        this.body = builder.body;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public Class getResponseType() {
        return responseType;
    }

    public Object getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpRequestData that = (HttpRequestData) o;
        return Objects.equals(url, that.url) &&
                httpMethod == that.httpMethod &&
                Objects.equals(httpHeaders, that.httpHeaders) &&
                Objects.equals(responseType, that.responseType) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, httpMethod, httpHeaders, responseType, body);
    }


    public static class HttpRequestDataBuilder {
        private String      url;
        private HttpMethod  httpMethod;
        private HttpHeaders httpHeaders;
        private Class       responseType;
        private Object      body;

        public HttpRequestDataBuilder(String url) {
            Objects.requireNonNull(url);
            this.url            = url;
            this.httpMethod     = HttpMethod.GET;
            this.httpHeaders    = null;
            this.responseType   = String.class;
            this.body           = null;
        }

        public HttpRequestDataBuilder withHttpMethod(HttpMethod method) {
            this.httpMethod = method;
            return this;
        }

        public HttpRequestDataBuilder withHttpHeaders(HttpHeaders headers) {
            this.httpHeaders = headers;
            return this;
        }

        public HttpRequestDataBuilder withResponseType(Class clazz) {
            this.responseType = clazz;
            return this;
        }

        public HttpRequestDataBuilder withBody(Object body) {
            this.body = body;
            return this;
        }

        public HttpRequestData build() {
            return new HttpRequestData(this);
        }
    }
}
