package com.example.a46066294p.magiccardsapi;

import android.net.Uri;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 46066294p on 14/10/16.
 */

public class DataAccesObject {
    /*
    LLamada general a la API
    https://api.magicthegathering.io/v1/cards
    */

    public DataAccesObject() {
    }

    private static final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

    static ArrayList<Cards> getCards()  {
        String url = getUrl();

        Log.d("URL: ", url);

        return doCall(url);
    }

    static ArrayList<Cards> getCards(Set<String> colors)  {
        String url = getUrl(colors);

        Log.d("URL: ", url);

        return doCall(url);
    }

    static ArrayList<Cards> getCards(Set<String> colors, String rarity)  {
        String url = getUrl(colors, rarity);

        Log.d("URL: ", url);

        return doCall(url);
    }


    private static ArrayList<Cards> doCall(String url) {
        try {

            //conexio a a la api
            String JsonResponse = HttpUtils.get(url);

            return processJson(JsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUrl(Set<String> colors, String rarity) {

        Log.d("getURL-colorSet" , colors.toString());
        Log.d("getURL-rarity" , rarity);

        List<String> colorList = new ArrayList<String>(colors);
        int size = colorList.size();

        Uri builtUri = Uri.parse(BASE_URL);

        if(colorList.get(0).equals("no_color")){
            builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendQueryParameter("rarity", rarity)
                    .build();
            return builtUri.toString();
        }


        switch (size) {
            case 1:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .build();
                break;

            case 2:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .build();
                break;

            case 3:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        .build();
                break;

            case 4:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        .appendQueryParameter("colors", colorList.get(3))
                        .build();
                break;

            case 5:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        .appendQueryParameter("colors", colorList.get(3))
                        .appendQueryParameter("colors", colorList.get(4))
                        .build();
                break;
        }

        return builtUri.toString();
    }

    public static String getUrl(Set<String> color){

        Log.d("getURL-colorSet" , color.toString());

        List<String> colorList = new ArrayList<String>(color);
        int size = colorList.size();

        Uri builtUri = Uri.parse(BASE_URL);

        if(colorList.get(0).equals("no_color")){
            builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .build();
            return builtUri.toString();
        }


        switch (size) {
            case 1:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("colors", colorList.get(0))
                        .build();
                        break;

            case 2:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .build();
                        break;

            case 3:
                 builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        .build();
                        break;

            case 4:
                 builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        .appendQueryParameter("colors", colorList.get(3))
                        .build();
                        break;

            case 5:
                 builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        .appendQueryParameter("colors", colorList.get(3))
                        .appendQueryParameter("colors", colorList.get(4))
                        .build();
                        break;
        }

        return builtUri.toString();
    }

    public static String getUrl(){
        Uri builtUri = Uri.parse(BASE_URL)
                /*
                .buildUpon()
                .appendPath("lists")
                .appendPath("movies")
                .appendPath(endpoint)
                .appendQueryParameter("country", pais)
                .appendQueryParameter("limit", LIMIT.toString())
                .appendQueryParameter("apikey", API_KEY)
                .build();
                */
                .buildUpon()
                .appendQueryParameter("pageSize", "90")
                .build();
        return builtUri.toString();
    }


    private static ArrayList<Cards> processJson(String jsonResponse) {
        ArrayList<Cards> cards = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");

            for (int i = 0; i < jsonCards.length(); i++) {
                JSONObject jsonOneCard = jsonCards.getJSONObject(i);

                Cards card = new Cards();
                card.setName(jsonOneCard.getString("name"));
                card.setType(jsonOneCard.getString("type"));
                card.setRarity(jsonOneCard.getString("rarity"));

                if(jsonOneCard.toString().contains("\"colors\":")){
                    JSONArray jsonArrayColors = jsonOneCard.getJSONArray("colors");
                    for(int j = 0; j < jsonArrayColors.length(); j++){
                        String color = jsonArrayColors.getString(j);
                        card.addColor(color);
                    }
                } else
                    card.addColor("no_color");

                if(jsonOneCard.toString().contains("\"text\"")){
                    card.setText(jsonOneCard.getString("text"));
                } else
                    card.setText("no_text");

                card.setImageUrl(jsonOneCard.getString("imageUrl"));

                cards.add(card);
            }
        }
        catch (JSONException e) {
                e.printStackTrace();
        }

        return cards;
    }

}// DataAccesObject class