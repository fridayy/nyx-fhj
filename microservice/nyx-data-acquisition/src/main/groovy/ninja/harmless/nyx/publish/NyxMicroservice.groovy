package ninja.harmless.nyx.publish

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import ninja.harmless.nyx.data.JsonDTO
/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@JsonDTO
@EqualsAndHashCode
class NyxMicroservice {
    @JsonProperty("application")
    Application application
    String subscriptionEndpoint

    public String getName() {
        return application.name
    }


    List<String> getAddresses() {
        List<String> ipAddresses = []
        application.instance.collect {
            ipAddresses << "http://" + it.ipAddr + ":" + it.getPort() + subscriptionEndpoint
        }

        return ipAddresses
    }
}
