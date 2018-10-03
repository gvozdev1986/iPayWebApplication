package by.htp.hvozdzeu.rest;

import by.htp.hvozdzeu.model.response.Response;
import by.htp.hvozdzeu.rest.exception.ErrorGetResponseException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * The class for queries to RESTServer
 */
class RESTQueryManager {

    private static final String CONTEXT_TYPE = "Content-Type";
    private static final String CONTEXT_TYPE_VALUE = "application/json";
    private static final Integer RESPONSE_CODE_OK = 200;
    private static final String RESPONSE_STATUS = "status";
    private static final String RESPONSE_MESSAGE = "message";

    /**
     * Private constructor
     */
    private RESTQueryManager() {
    }

    /**
     * The method for send query and get response
     *
     * @param url        String url to REST server
     * @param parameters Map<String, String> parameters for send parameters to Rest server
     * @return Map<String   ,       String> responseMap response from Rest server
     * @throws IOException Exception
     */
    static Response sendQuery(String url, Map<Object, Object> parameters, String typeQuery) throws IOException {

        Boolean status = false;
        String message = null;

        URL urlQuery = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlQuery.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(typeQuery);
        conn.addRequestProperty(CONTEXT_TYPE, CONTEXT_TYPE_VALUE);

        Gson gson = new Gson();
        String input = gson.toJson(parameters);

        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (conn.getResponseCode() != RESPONSE_CODE_OK) {
            throw new ErrorGetResponseException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        String output;
        while ((output = br.readLine()) != null) {
            JsonElement jsonParser = new JsonParser().parse(output);
            JsonObject jsonObject = jsonParser.getAsJsonObject();
            status = Boolean.valueOf(jsonObject.get(RESPONSE_STATUS).getAsString());
            message = jsonObject.get(RESPONSE_MESSAGE).getAsString();
        }

        Response response = Response.getBuilder()
                .status(status)
                .message(message)
                .build();

        conn.disconnect();
        return response;
    }

}
