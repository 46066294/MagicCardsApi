package com.example.a46066294p.magiccardsapi;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Mat on 16/11/2016.
 */

public class DataManager {
    private static UriHelper URI_HELPER = UriHelper.with(ContentProviderOfMagicCardsAwsomeApi.AUTHORITY);
    private static Uri CARDS_URI = URI_HELPER.getUri(Cards.class);

    static void saveCards(ArrayList<Cards> cards, Context context) {
        cupboard().withContext(context).put(CARDS_URI, Cards.class, cards);
    }

    static void deleteCards(Context context) {
        cupboard().withContext(context).delete(CARDS_URI, "_id > ?", "0");
    }

    //cargar nueva peticion para filtrar en las cartas guardadas
    static CursorLoader getCursorLoader(Context context) {
        return new CursorLoader(context, CARDS_URI, null, null, null, null);
    }

}
