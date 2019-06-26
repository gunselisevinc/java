import org.json.JSONArray;
import org.jsoup.Jsoup;

import java.io.IOException;

public class Weather {

    public JSONArray getCities(){
        String url = "https://servis.mgm.gov.tr/api/merkezler/iller";
        try {
            String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
            JSONArray objects = new JSONArray(json);
            return objects;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray getDistrict(String city){
        String url = "https://servis.mgm.gov.tr/api/merkezler/ililcesi?il="+city;
        try {
            String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
            JSONArray objects = new JSONArray(json);
            return objects;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray getStatics(String id,String zamanlama){
        String url = "https://servis.mgm.gov.tr/api/tahminler/"+zamanlama+"?istno="+id;
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
