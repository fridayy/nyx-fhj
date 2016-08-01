package ninja.harmless.nyx.bootstrap.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import javax.lang.model.element.Element;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Spring configuration server bootstrap annotation.
 * Used to bootstrap the spring boot config server and to minimize annotation usage.
 *
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/1/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EnableConfigServer
@SpringBootApplication
public @interface BootstrapConfigServer {
}
