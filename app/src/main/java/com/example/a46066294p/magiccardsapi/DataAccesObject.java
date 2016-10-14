package com.example.a46066294p.magiccardsapi;

import android.graphics.Movie;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 46066294p on 14/10/16.
 */

public class DataAccesObject {
    /*
    LLamada general a la API
    https://api.magicthegathering.io/v1/cards
    */

    private final String BASE_URL = "https://api.magicthegathering.io/";

    public ArrayList<Cards> getCards()  {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("v1")
                .appendPath("cards")
                //.appendQueryParameter("country", pais)
                .build();
        String url = builtUri.toString();

        return doCall(url);

    }


    private ArrayList<Cards> doCall(String url){
        try {
            String JsonResponse = HttpUtils.get(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private ArrayList<Cards> processJson(String jsonResponse) {

        ArrayList<Cards> cards = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");

            for (int i = 0; i < jsonCards.length(); i++) {
                JSONObject jsonOneCard = jsonCards.getJSONObject(i);

                Cards card = new Cards();
                card.setCardName("cardName");

                cards.add(card);
            }
        }
        catch (JSONException e) {
                e.printStackTrace();
        }

        return cards;
    }

}// DataAccesObject class