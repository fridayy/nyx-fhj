package ninja.harmless.nyx.jsonmapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/5/16.
 */
public class JsonMapper {

    public static <T> T mapObject(String json, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T returnObj = null;
        try {
            returnObj = mapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnObj;
    }
}
