package by.htp.hvozdzeu.rest;

import by.htp.hvozdzeu.model.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

import static by.htp.hvozdzeu.rest.RESTManager.sendQuery;

/**
 * The class for getting response from server
 */
public class ResponseManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseManager.class);

    /**
     * Private constructor
     */
    private ResponseManager() {
    }

    /**
     * The method for get token
     *
     * @param url        String URL server
     * @param parameters Map<Object, Object> parameters for get token
     * @return Response entity with response from server
     */
    public static Response getResponse(String url, Map<Object, Object> parameters, String typeQuery) {

        Response response = null;

        try {
            response = sendQuery(url, parameters, typeQuery);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return response;

    }

}
