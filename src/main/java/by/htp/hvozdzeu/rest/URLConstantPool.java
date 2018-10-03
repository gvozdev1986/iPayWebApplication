package by.htp.hvozdzeu.rest;

public class URLConstantPool {

    public static final String QUERY_TYPE_GET = "GET";
    public static final String QUERY_TYPE_POST = "POST";
    public static final String QUERY_TYPE_PUT = "PUT";

    public static final String URL_GET_TOKEN = "http://localhost:8090/RESTServer/rest/auth/";
    public static final String URL_CHECK_CREDIT_CARD = "http://localhost:8090/RESTServer/rest/balance/check/card/";
    public static final String URL_WRITE_OFF_BALANCE = "http://localhost:8090/RESTServer/rest/balance/write/";
    public static final String URL_CHECK_SERVER_STATUS = "http://localhost:8090/RESTServer/rest/auth/status/";

    private URLConstantPool() {
    }

}
