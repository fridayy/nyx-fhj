package ninja.harmless.nyx.jsonmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

/**
 * Simple abstraction on Jackson JSON databindings.
 * Provides convenience methods.
 * @author benjamin.krenn@edu.fh-joanneum.at - 8/5/16.
 */
public class JsonMapper {

    /**
     * Maps a valid JSON String to a given object.
     * @param json String
     * @param type POJO
     * @param <T> type
     * @return a mapped pojo
     */
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

    /**
     * Produces a JSON String out of an object.
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String writeObject(T object) {
        ObjectWriter objectWriter = new ObjectMapper().writer();
        String result = "";
        try {
            result = objectWriter.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
