package ninja.harmless.nyx.testcategories.annotation;

import org.junit.experimental.categories.Category;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to label a given test as a unit test for semantic reasons
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/4/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Category(ninja.harmless.nyx.testcategories.UnitTest.class)
public @interface UnitTest {
}
