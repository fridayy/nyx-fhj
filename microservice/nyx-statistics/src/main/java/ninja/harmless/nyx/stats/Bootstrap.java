package ninja.harmless.nyx.stats;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.PostConstruct;

/**
 * Bootstrap class for Vert.x used within a spring container for spring magic purposes.
 * The sore purpose of this is to easily combine the Vert.x based Service with Eureka, Config-server and
 * the API Gateway which are abstracted by Spring Boot.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/9/16.
 */
@SpringBootApplication
@EnableEurekaClient
public class Bootstrap {

    @Autowired
    VertxHttpServerApplication vertxHttpServerApplication;

    /**
     * Disable the embedded tomcat at startup since vert.x will be used as the http server.
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(Bootstrap.class).web(false).run(args);
    }

    /**
     * Deploys the Vert.x verticle after the spring context went up
     */
    @PostConstruct
    public void deploy() {
        Vertx.vertx().deployVerticle(vertxHttpServerApplication);
    }
}
