package com.example.a46066294p.magiccardsapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

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

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_cards_row, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView tvCardTitle = (TextView) convertView.findViewById(R.id.tvCardName);
        TextView tvColor = (TextView) convertView.findViewById(R.id.color);
        TextView tvType = (TextView) convertView.findViewById(R.id.type);
        TextView tvRarity = (TextView) convertView.findViewById(R.id.rarity);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.text);
        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.imgUrl);

        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        tvCardTitle.setText(card.getName());
        tvColor.setText(card.getColor().toString());
        tvType.setText(card.getType());
        tvRarity.setText(card.getRarity());
        tvDescription.setText(card.getText());

        //Utilitzem Glide per les imatges
        Glide.with(getContext()).load(card.getImageUrl()).into(ivPosterImage);
        Log.w("CARD ", card.toString());

        // Retornem la View replena per a mostrarla
        return convertView;

    }
}
