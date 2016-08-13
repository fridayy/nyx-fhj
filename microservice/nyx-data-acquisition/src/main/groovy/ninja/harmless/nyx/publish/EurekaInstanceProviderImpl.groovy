package ninja.harmless.nyx.publish

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@Component
class EurekaInstanceProviderImpl implements EurekaInstanceProvider {

    @Value("\${eureka.metadata.url}")
    String url

    @Override
    EurekaInstance provide() {
        return new EurekaInstance(metadataUrl: url)
    }
}
