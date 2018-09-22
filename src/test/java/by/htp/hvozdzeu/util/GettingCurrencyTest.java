package by.htp.hvozdzeu.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class GettingCurrencyTest {

    private String currencyName;
    private String currencyValue;
    private String currencyScale;
    private static Map<String, String> currencyMap = new HashMap<>();

    @Test
    public void currencyOnline() throws IOException {

        List<String> urlApiList = new ArrayList<>();
        urlApiList.add("http://www.nbrb.by/API/ExRates/Rates/USD?ParamMode=2");
        urlApiList.add("http://www.nbrb.by/API/ExRates/Rates/EUR?ParamMode=2");
        urlApiList.add("http://www.nbrb.by/API/ExRates/Rates/RUB?ParamMode=2");
        urlApiList.add("http://www.nbrb.by/API/ExRates/Rates/UAH?ParamMode=2");

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
                currencyName = jsonObject.get("Cur_Abbreviation").getAsString();
                currencyValue = jsonObject.get("Cur_OfficialRate").getAsString();
                currencyScale = jsonObject.get("Cur_Scale").getAsString();
                currencyMap.put(currencyName, currencyValue);
            }
            in.close();
        }

        assertNotNull(currencyMap);
        assertNotNull(urlApiList);
        assertNotNull(currencyName);
        assertNotNull(currencyValue);
        assertNotNull(currencyScale);

    }
}