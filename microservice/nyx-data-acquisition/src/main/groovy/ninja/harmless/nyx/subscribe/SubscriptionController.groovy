package ninja.harmless.nyx.subscribe

import ninja.harmless.nyx.publish.NyxMicroservice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/13/16.
 */
@RestController
class SubscriptionController {

    SubscriptionService subscriptionService

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> handleSubscriptionRequest(@RequestBody SubscriptionRequest request) {
        subscriptionService.handleSubscriptionRequest(request)
        return new ResponseEntity<HttpStatus>(HttpStatus.OK)
    }

    @GetMapping(path = "/subscribers")
    Set<NyxMicroservice> getSubscribers() {
        return subscriptionService.provideSubscribedServices()
    }
}
