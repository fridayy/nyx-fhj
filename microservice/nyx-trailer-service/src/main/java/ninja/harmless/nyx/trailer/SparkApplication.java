package ninja.harmless.nyx.trailer;

import org.springframework.stereotype.Component;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/12/16.
 */
@Component
public class SparkApplication {
    public void handleRoutes() {
        port(9090);
        get("/test", (req, res) -> "Hello World!");
    }
}
