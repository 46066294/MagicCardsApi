package com.example.a46066294p.magiccardsapi;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.bumptech.glide.Glide;
import com.example.a46066294p.magiccardsapi.databinding.LvCardsRowBinding;


import java.util.List;

/**
 * Created by Mat on 03/11/2016.
 */

public class CardsAdapter extends ArrayAdapter<Cards>{

    public CardsAdapter(Context context, int resource, List<Cards> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtenim l'objecte en la possició corresponent
        Cards card = getItem(position);
        LvCardsRowBinding binding = null;

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            //convertView = inflater.inflate(R.layout.lv_cards_row, parent, false);
            binding = DataBindingUtil.inflate(inflater, R.layout.lv_cards_row, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }


        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        binding.tvCardName.setText(card.getName());
        binding.color.setText(card.getColor().toString());
        binding.type.setText(card.getType());
        binding.rarity.setText(card.getRarity());
        //binding.text.setText(card.getText());

        //Utilitzem Glide per les imatges
        Glide.with(getContext()).load(card.getImageUrl()).into(binding.imgUrl);
        Log.w("CARD ", card.toString());

        return binding.getRoot();

    }
}
