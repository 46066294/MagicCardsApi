package com.example.a46066294p.magiccardsapi;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent i = getActivity().getIntent();

        if (i != null) {
            Cards card = (Cards) i.getSerializableExtra("card");//s'obte l'objecte serialitzat que es diu "card"

            if (card != null) {
                updateUi(card);
            }
        }

        return view;
    }

    private void updateUi(Cards card) {
        Log.d("CARD", card.toString());
    }

}
