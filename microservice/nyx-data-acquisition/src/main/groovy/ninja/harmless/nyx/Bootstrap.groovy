package ninja.harmless.nyx

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Bootstrapping class for the Data Acquisition service.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
@SpringBootApplication
class Bootstrap {
    static void main(String[] args) {
        SpringApplication.run(Bootstrap, args)
    }
}
