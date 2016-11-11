package com.example.a46066294p.magiccardsapi;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private View view;
    private ImageView ivImageCard;
    private TextView tvCardColor;
    private TextView tvCardRarity;
    private TextView tvCardType;
    private TextView tvCardName;
    private TextView tvCardText;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ivImageCard = (ImageView) view.findViewById(R.id.ivImageCard);
        tvCardName = (TextView) view.findViewById(R.id.tvCardName);
        tvCardType = (TextView) view.findViewById(R.id.tvCardType);
        tvCardRarity = (TextView) view.findViewById(R.id.tvCardRarity);
        tvCardColor = (TextView) view.findViewById(R.id.tvCardColor);
        tvCardText = (TextView) view.findViewById(R.id.tvCardText);

        Intent i = getActivity().getIntent();

        if (i != null) {
            Cards card = (Cards) i.getSerializableExtra("card");//s'obte l'objecte serialitzat que es diu "card"

            if (card != null) {
                updateCard(card);
            }
        }

        return view;
    }

    private void updateCard(Cards card) {
        Log.d("CARD", card.toString());

        tvCardName.setText(card.getName());
        tvCardType.setText(card.getType());
        tvCardRarity.setText(card.getRarity());
        tvCardColor.setText((CharSequence) card.getColor().toString());
        tvCardText.setText(card.getText());

        Glide.with(getContext()).load(card.getImageUrl()).into(ivImageCard);
    }

}


/*
        android:layout_width="220dp"
        android:layout_height="286dp"
 */