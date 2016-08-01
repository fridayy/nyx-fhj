package ninja.harmless.nyx.bootstrap.restservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/2/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootApplication
public @interface BootstrapRestService {
}
