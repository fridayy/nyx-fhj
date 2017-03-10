package ninja.harmless.nyx.publish

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import ninja.harmless.nyx.data.JsonDTO

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@JsonDTO
@EqualsAndHashCode
class Application {
    @JsonProperty("name")
    String name
    @JsonProperty("instance")
    List<Instance> instance
}
