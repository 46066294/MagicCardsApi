package com.example.a46066294p.magiccardsapi;

import android.graphics.Movie;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

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

    private final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

    public ArrayList<Cards> getCards()  {
        String url = getUrl();

        Log.d("URL: ", url);

        return doCall(url);
    }

    private ArrayList<Cards> doCall(String url) {
        try {

            //conexio a a la api
            String JsonResponse = HttpUtils.get(url);

            return processJson(JsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUrl(){
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
                .build();
        return builtUri.toString();
    }


    private ArrayList<Cards> processJson(String jsonResponse) {
        ArrayList<Cards> cards = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");

            for (int i = 0; i < jsonCards.length(); i++) {
                JSONObject jsonOneCard = jsonCards.getJSONObject(i);

                Cards card = new Cards();
                card.setCardName(jsonOneCard.getString("name"));

                cards.add(card);
            }
        }
        catch (JSONException e) {
                e.printStackTrace();
        }

        return cards;
    }

}// DataAccesObject class