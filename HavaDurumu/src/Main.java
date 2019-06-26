import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        myFrame myFrame = new myFrame();
        myFrame.setVisible(true);

        /*
        Weather weather = new Weather();

        JSONArray cities = weather.getCities();
        JSONArray district = weather.getDistrict("istanbul");
        JSONArray statics = weather.getStatics("17060");
        for (int i = 0; i < district.length(); i++) {
            JSONObject jsonObject = district.getJSONObject(i);
            //System.out.println(jsonObject.optString("ilce") + jsonObject.optString("sondurumIstNo"));
        }

        JSONObject jsonObject = statics.getJSONObject(0);
        System.out.println();*/
    }
}
