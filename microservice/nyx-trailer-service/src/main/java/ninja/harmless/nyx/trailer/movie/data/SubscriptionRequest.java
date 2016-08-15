package ninja.harmless.nyx.trailer.movie.data;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
public class SubscriptionRequest {
    private String serviceName;
    private String subscriptionEndpoint;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSubscriptionEndpoint() {
        return subscriptionEndpoint;
    }

    public void setSubscriptionEndpoint(String subscriptionEndpoint) {
        this.subscriptionEndpoint = subscriptionEndpoint;
    }
}
