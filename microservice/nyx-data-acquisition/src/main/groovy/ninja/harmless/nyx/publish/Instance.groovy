package ninja.harmless.nyx.publish

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import ninja.harmless.nyx.data.JsonDTO

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@JsonDTO
@EqualsAndHashCode
class Instance {
    @JsonProperty("app")
    String app
    @JsonProperty("ipAddr")
    String ipAddr
    @JsonProperty("status")
    String status
    @JsonProperty("instanceId")
    String instanceId

    String getPort() {
        return instanceId.split(":")[2]
    }
}
