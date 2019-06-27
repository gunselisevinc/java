package com.company;

import org.json.JSONArray;
import org.jsoup.Jsoup;

import java.io.IOException;

public class  borsa{
    public JSONArray getParaBirimi(){
        String url = "https://api.canlidoviz.com/web/items?marketId=1&type=0";

        try {
            String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
            JSONArray objects = new JSONArray(json);

            return objects;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
