package ninja.harmless.nyx.trailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.annotation.PostConstruct;

/**
 * Bootstrapping class for Spark Framework.
 *
 * This class integrates the Spark Framework within the Spring Container by wiring the spark component into
 * the application context and invoking the corresponding method after the spring context is up.
 *
 * The Spark Application class is the only class in the spring container since no further interaction with spring
 * is needed.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/11/16.
 */
@SpringBootApplication
public class Bootstrap {
    @Autowired
    SparkApplication sparkApplication;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Bootstrap.class).web(false).run(args);
    }

    @PostConstruct
    public void deploy() {
        sparkApplication.handleRoutes();
    }
}
