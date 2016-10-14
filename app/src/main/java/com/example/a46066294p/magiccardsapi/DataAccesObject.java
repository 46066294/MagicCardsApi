package com.example.a46066294p.magiccardsapi;

import android.graphics.Movie;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public String getCards(String pais) {
            ArrayList<Cards> getCards(String pais) {
            Uri builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendPath("v1")
                    .appendPath("cards")
                    //.appendQueryParameter("country", pais)
                    .build();
            String url = builtUri.toString();

            return url;
        }
    }

    private ArrayList<Movie> processJson(String jsonResponse) {

            ArrayList<Movie> movies = new ArrayList<>();
            try {
                JSONObject data = new JSONObject(jsonResponse);
                JSONArray jsonCards = data.getJSONArray("cards");

                for (int i = 0; i < jsonCards.length(); i++) {
                    JSONObject jsonOneCard = jsonCards.getJSONObject(i);

                    Cards cards = new Cards();
                    cards.setTitle(jsonMovie.getString("title"));
                    cards.setYear(jsonMovie.getInt("year"));
                    cards.setSynopsis(jsonMovie.getString("synopsis"));
                    cards.setPosterUrl(jsonMovie.getJSONObject("posters").getString("thumbnail"));
                    cards.setCritics_score(jsonMovie.getJSONObject("ratings").getInt("critics_score"));

                    movies.add(movie);
                }
            }
            catch (JSONException e) {
                    e.printStackTrace();
            }

                    return movies;
    }

}// DataAccesObject class