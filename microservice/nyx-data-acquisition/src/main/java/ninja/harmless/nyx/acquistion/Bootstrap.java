package ninja.harmless.nyx.acquistion;

import ninja.harmless.nyx.bootstrap.restservice.BootstrapRestService;
import org.springframework.boot.SpringApplication;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/2/16.
 */
@BootstrapRestService
public class Bootstrap {
    public static void main(String[] args) {
        new SpringApplication(Bootstrap.class).run(args);
    }
}
