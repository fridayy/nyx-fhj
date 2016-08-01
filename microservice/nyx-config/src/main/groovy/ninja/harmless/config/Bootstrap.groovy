package ninja.harmless.config

import ninja.harmless.nyx.bootstrap.config.BootstrapConfigServer
import org.springframework.boot.SpringApplication

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/1/16.
 */
@BootstrapConfigServer
class Bootstrap {
    static void main(String[] args) {
       SpringApplication.run(Bootstrap, args)
    }
}
