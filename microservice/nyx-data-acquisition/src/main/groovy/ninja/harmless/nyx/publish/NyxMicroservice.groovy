package ninja.harmless.nyx.publish

import com.fasterxml.jackson.annotation.JsonProperty
import ninja.harmless.nyx.data.JsonDTO

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@JsonDTO
class NyxMicroservice {
    @JsonProperty("application")
    Application application
    String subscriptionEndpoint

    public String getName() {
        return application.name
    }


    public List<String> getAddresses() {
        List<String> ipAddresses = []
        application.instance.collect {
            ipAddresses << "http://" + it.ipAddr + ":" + it.getPort() + subscriptionEndpoint
        }

        return ipAddresses
    }
}
