package ninja.harmless.nyx.apigateway;

import ninja.harmless.nyx.bootstrap.apigateway.BootstrapApiGateway;
import org.springframework.boot.SpringApplication;

/**
 * This is the main class for the API Gateway which is the facade for all client communication with
 * the nyx microservice stack.
 *
 * The main parts are the zuul proxy and the hystrix components.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/8/16.
 */
@BootstrapApiGateway
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
