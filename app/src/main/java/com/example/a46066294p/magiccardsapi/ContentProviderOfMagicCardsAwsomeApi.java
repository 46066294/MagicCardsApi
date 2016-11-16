package com.example.a46066294p.magiccardsapi;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Mat on 16/11/2016.
 */

public class ContentProviderOfMagicCardsAwsomeApi extends CupboardContentProvider{

    // The content provider authority is used for building Uri's for the provider
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

    static {
        cupboard().register(Cards.class);
    }

    public ContentProviderOfMagicCardsAwsomeApi() {
        super(AUTHORITY, 1);
    }
}
