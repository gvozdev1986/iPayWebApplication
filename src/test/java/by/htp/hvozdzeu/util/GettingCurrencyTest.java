package by.htp.hvozdzeu.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
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

    private StringBuilder currencyName;
    private StringBuilder currencyValue;
    private StringBuilder currencyScale;
    private static Map<String, String> currencyMap = new HashMap<>();

    @Before
    public void setUp(){
        currencyName = new StringBuilder();
        currencyValue = new StringBuilder();
        currencyScale = new StringBuilder();
    }

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
                currencyName.append(jsonObject.get("Cur_Abbreviation").getAsString());
                currencyName.append(jsonObject.get("Cur_OfficialRate").getAsString());
                currencyName.append(jsonObject.get("Cur_Scale").getAsString());
                currencyMap.put(currencyName.toString(), currencyValue.toString());
            }
            in.close();
        }

        System.out.println(currencyName);
        System.out.println(currencyValue);
        System.out.println(currencyScale);

        assertNotNull(currencyMap);
        assertNotNull(urlApiList);
        assertNotNull(currencyName);
        assertNotNull(currencyValue);
        assertNotNull(currencyScale);

    }
}