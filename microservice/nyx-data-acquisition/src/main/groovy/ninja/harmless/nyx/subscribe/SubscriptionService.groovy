package ninja.harmless.nyx.subscribe

import ninja.harmless.nyx.publish.NyxMicroservice

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
interface SubscriptionService {
    void handleSubscriptionRequest(SubscriptionRequest request)

    Set<NyxMicroservice> getSubscribedServices()
}