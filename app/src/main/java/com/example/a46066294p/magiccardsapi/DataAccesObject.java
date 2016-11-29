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

    private static final int PAGES = 10;
    private static final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

    public DataAccesObject() {}

    static ArrayList<Cards> getCards()  {
        String url = getUrl();

        Log.d("URL: ", url);

        return doCall(url);
    }

    static ArrayList<Cards> getCards(Set<String> colors, String rarity)  {
        //String url = getUrl(colors, rarity);
        ArrayList<Cards> result = new ArrayList<>();

        //Log.d("URL: ", url);
        //return doCall(url);
        return doCall(colors, rarity);
    }


    private static ArrayList<Cards> doCall(Set<String> colors, String url) {
        ArrayList<Cards> cards = new ArrayList<>();

        for (int i = 0; i < PAGES; i++) {
            try {
                String urlStr = getUrl(colors, url);
                String JsonResponse = HttpUtils.get(urlStr);
                ArrayList<Cards> list = processJson(JsonResponse);
                cards.addAll(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cards;
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
                    //.appendQueryParameter("page", String.valueOf(page))
                    .build();
            return builtUri.toString();
        }


        switch (size) {
            case 1:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        //.appendQueryParameter("page", String.valueOf(page))
                        .build();
                break;

            case 2:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        //.appendQueryParameter("page", String.valueOf(page))
                        .build();
                break;

            case 3:
                builtUri = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("rarity", rarity)
                        .appendQueryParameter("colors", colorList.get(0))
                        .appendQueryParameter("colors", colorList.get(1))
                        .appendQueryParameter("colors", colorList.get(2))
                        //.appendQueryParameter("page", String.valueOf(page))
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
                        //.appendQueryParameter("page", String.valueOf(page))
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
                        //.appendQueryParameter("page", String.valueOf(page))
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

                if(jsonOneCard.toString().contains("\"colors\":")){//hasColor
                    JSONArray jsonArrayColors = jsonOneCard.getJSONArray("colors");
                    if(jsonArrayColors.length() > 1)
                        card.setColor("Various colors");
                    else
                        card.setColor((String) jsonArrayColors.get(0));
                } else
                    card.setColor("no_color");

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