package ninja.harmless.nyx.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Helper annotation which ensures that unknown properties will be ignored and not serialized.
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/5/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@JsonIgnoreProperties(ignoreUnknown = true)
public @interface JsonDTO {
}
