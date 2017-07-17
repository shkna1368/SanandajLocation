package com.shabab.googleplace;

import com.google.gson.*;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sh-Java on 6/27/2017.
 */
public class CallWS {
   public static String[] placeType = new String[]{"accounting",
            "accounting",
            "airport",
            "amusement_park",
            "aquarium",
            "art_gallery",
            "atm",
            "bakery",
            "bank",
            "bar",
            "beauty_salon",
            "bicycle_store",
            "book_store",
            "bowling_alley",
            "bus_station",
            "cafe",
            "campground",
            "car_dealer",
            "car_rental",
            "car_repair",
            "car_wash",
            "casino",
            "cemetery",
            "church",
            "city_hall",
            "clothing_store",
            "convenience_store",
            "courthouse",
            "dentist",
            "department_store",
            "doctor",
            "electrician",
            "electronics_store",
            "embassy",


            "fire_station",
            "florist",

            "funeral_home",
            "furniture_store",
            "gas_station",

            "gym",
            "hair_care",
            "hardware_store",
            "hindu_temple",
            "home_goods_store",
            "hospital",
            "insurance_agency",
            "jewelry_store",
            "laundry",
            "lawyer",
            "library",
            "liquor_store",
            "local_government_office",
            "locksmith",
            "lodging",
            "meal_delivery",
            "meal_takeaway",
            "mosque",
            "movie_rental",
            "movie_theater",
            "moving_company",
            "museum",
            "night_club",
            "painter",
            "park",
            "parking",
            "pet_store",
            "pharmacy",
            "physiotherapist",

            "plumber",
            "police",
            "post_office",
            "real_estate_agency",
            "restaurant",
            "roofing_contractor",
            "rv_park",
            "school",
            "shoe_store",
            "shopping_mall",
            "spa",
            "stadium",
            "storage",
            "store",
            "subway_station",
            "synagogue",
            "taxi_stand",
            "train_station",
            "transit_station",
            "travel_agency",
            "university",
            "veterinary_care",
            "zoo"
    };


    public void call(String type) {

        try {

            URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=35.311693,46.996137&radius=10000&types=" + type + "&key=AIzaSyAeeKBYM03NcA0N8Cb45lKgYy5-GB12MEM");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      /*      conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");*/

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

           /* Gson gson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = (JsonArray) jsonParser.parse(output);*/


            JsonParser parser = new JsonParser();
            JsonElement elem = parser.parse(output);

            JsonArray elemArr = elem.getAsJsonArray();
            System.out.println("size==" + elemArr.size());

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    public List<Place> getJSON(String type, int timeout) {
        HttpURLConnection c = null;
        StringBuilder sb = null;
        try {
            URL u = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=35.311693,46.996137&radius=10000&types=" + type + "&key=AIzaSyAeeKBYM03NcA0N8Cb45lKgYy5-GB12MEM");

            //  URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    //   return sb.toString();
            }

        } catch (MalformedURLException ex) {
            System.out.println("MalformedURLException=" + ex.toString());

        } catch (IOException ex) {
            System.out.println("IOException=" + ex.toString());

        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    System.out.println("Exception=" + ex.toString());

                }
            }
        }

        String data = sb.toString();
        System.out.println(data);
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(data).getAsJsonObject();
    JsonArray j=    o.getAsJsonArray("results");
      return   parsJson(j);

      /*  JsonObject jsonObject= (JsonObject) j.get(0);
        JsonObject jsonObjectLocation= jsonObject.getAsJsonObject("geometry").getAsJsonObject("location");
       String lat =jsonObjectLocation.get("lat").toString();
       String lon =jsonObjectLocation.get("lng").getAsString();
       String name =jsonObject.get("name").toString();
       String types =jsonObject.get("types").toString();
       String vicinity =jsonObject.get("vicinity").toString();

        System.out.println(lat+"-"+lon+"-"+name+"-"+types+"-"+"-"+vicinity);*/












    }

    private List<Place> parsJson(JsonArray jsonArray) {
      //  JsonObject jsonObject = (JsonObject) jsonArray.get(0);
        List<Place> placeList = new ArrayList<>();
      int size=  jsonArray.size();
        if (size> 0) {

for (int i=0;i<size;i++) {
    JsonObject jsonObject = (JsonObject) jsonArray.get(i);
    JsonObject jsonObjectLocation = jsonObject.getAsJsonObject("geometry").getAsJsonObject("location");
    double lat = jsonObjectLocation.get("lat").getAsDouble();
    double lon = jsonObjectLocation.get("lng").getAsDouble();
    String name = jsonObject.get("name").toString();
    String types = jsonObject.get("types").toString();
    String vicinity = jsonObject.get("vicinity").toString();


    Place p=new Place();
    p.setLat(lat);
    p.setLon(lon);
    p.setPlaceName(name);
    p.setPlaceAddress(vicinity);
    p.setType(types);

    placeList.add(i,p);
    System.out.println(lat + "-" + lon + "-" + name + "-"+'\n' + types + "-" + "-"
            +'\n'+ vicinity);
}
        }


        System.out.println("size list="+placeList.size());
        return placeList;
    }
}