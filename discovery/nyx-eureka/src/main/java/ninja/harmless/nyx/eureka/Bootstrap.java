package ninja.harmless.nyx.eureka;

import ninja.harmless.nyx.bootstrap.eureka.server.BootstrapEurekaServer;
import org.springframework.boot.SpringApplication;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/8/16.
 */
@BootstrapEurekaServer
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
