package by.htp.hvozdzeu.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GettingCurrency {

    private GettingCurrency() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(GettingCurrency.class);

    private static final String URL_API_USD_CURRENCY = "http://www.nbrb.by/API/ExRates/Rates/USD?ParamMode=2";
    private static final String URL_API_EUR_CURRENCY = "http://www.nbrb.by/API/ExRates/Rates/EUR?ParamMode=2";
    private static final String URL_API_RUB_CURRENCY = "http://www.nbrb.by/API/ExRates/Rates/RUB?ParamMode=2";
    private static final String URL_API_UAH_CURRENCY = "http://www.nbrb.by/API/ExRates/Rates/UAH?ParamMode=2";
    private static final String NODE_JSON_OBJECT_ABBREVIATION = "Cur_Abbreviation";
    private static final String NODE_JSON_OBJECT_OFFICIAL_RATE = "Cur_OfficialRate";

    private static Map<String, String> currencyMap = new HashMap<>();

    public static Map<String, String> currencyOnline() throws IOException {

        List<String> urlApiList = new ArrayList<>();
        urlApiList.add(URL_API_USD_CURRENCY);
        urlApiList.add(URL_API_EUR_CURRENCY);
        urlApiList.add(URL_API_RUB_CURRENCY);
        urlApiList.add(URL_API_UAH_CURRENCY);

        for (String anUrlApiList : urlApiList) {
            URL api = new URL(anUrlApiList);
            URLConnection urlConnection = api.openConnection();
            BufferedReader in;
            in = new BufferedReader(
                    new InputStreamReader(
                            urlConnection.getInputStream()
                    )
            );
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JsonElement jsonParser = new JsonParser().parse(inputLine);
                JsonObject jsonObject = jsonParser.getAsJsonObject();
                String currencyName = jsonObject.get(NODE_JSON_OBJECT_ABBREVIATION).getAsString();
                String currencyValue = jsonObject.get(NODE_JSON_OBJECT_OFFICIAL_RATE).getAsString();
                LOGGER.debug("Getting currency {} with currency value {}", currencyName, currencyValue);
                currencyMap.put(currencyName, currencyValue);
            }
            in.close();
        }

        return currencyMap;
    }

}
