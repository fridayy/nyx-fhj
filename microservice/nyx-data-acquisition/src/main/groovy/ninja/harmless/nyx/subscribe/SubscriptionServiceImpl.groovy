package ninja.harmless.nyx.subscribe

import ninja.harmless.nyx.jsonmapper.JsonMapper
import ninja.harmless.nyx.publish.EurekaInstance
import ninja.harmless.nyx.publish.EurekaInstanceProvider
import ninja.harmless.nyx.publish.NyxMicroservice
import ninja.harmless.nyx.remote.HttpListenableFutureAware
import ninja.harmless.nyx.remote.HttpRequestData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@Service
class SubscriptionServiceImpl extends HttpListenableFutureAware<String> implements SubscriptionService {

    EurekaInstanceProvider eurekaInstanceProvider
    List<NyxMicroservice> registeredServices

    @Autowired
    SubscriptionServiceImpl(EurekaInstanceProvider eurekaInstanceProvider) {
        this.eurekaInstanceProvider = eurekaInstanceProvider
        this.registeredServices = new ArrayList<>()
    }

    @Override
    void handleSubscriptionRequest(SubscriptionRequest request) {
        EurekaInstance eurekaInstance = eurekaInstanceProvider.provide()
        HttpRequestData requestData = new HttpRequestData.HttpRequestDataBuilder(eurekaInstance.metadataUrl + request.serviceName)
            .withHttpMethod(HttpMethod.GET)
            .withResponseType(String.class)
            .build()

        ResponseEntity<String> entity = collectFromListenableFuture(makeHttpRequest(requestData)) as ResponseEntity<String>
        NyxMicroservice nyxMicroservice = JsonMapper.mapObject(entity.getBody(), NyxMicroservice.class)
        nyxMicroservice.subscriptionEndpoint = request.subscriptionEndpoint
        registeredServices.add(nyxMicroservice)
    }

    @Override
    List<NyxMicroservice> getSubscribedServices() {
        return registeredServices
    }
}
