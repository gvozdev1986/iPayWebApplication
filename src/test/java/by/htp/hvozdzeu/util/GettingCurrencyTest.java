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
import java.util.List;

public class GettingCurrencyTest {

    @Test
    public void currencyOnline() throws IOException {

        List<String> urlApiList = new ArrayList<>();
        urlApiList.add("http://www.nbrb.by/API/ExRates/Rates/USD?ParamMode=2");
        urlApiList.add("http://www.nbrb.by/API/ExRates/Rates/EUR?ParamMode=2");
        urlApiList.add("http://www.nbrb.by/API/ExRates/Rates/RUB?ParamMode=2");


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
                String currencyName = jsonObject.get("Cur_Abbreviation").getAsString();
                String currencyValue = jsonObject.get("Cur_OfficialRate").getAsString();
                String currencyScale = jsonObject.get("Cur_Scale").getAsString();
                System.out.println(currencyName + ": " + currencyValue + ", " + currencyScale);
            }
            in.close();
        }

    }
}