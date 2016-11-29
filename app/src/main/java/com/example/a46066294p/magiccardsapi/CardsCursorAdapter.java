package com.example.a46066294p.magiccardsapi;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.a46066294p.magiccardsapi.databinding.LvCardsRowBinding;

/**
 * Created by Mat on 17/11/2016.
 */

public class CardsCursorAdapter extends CupboardCursorAdapter<Cards>{

    public CardsCursorAdapter(Context context, Class<Cards> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Cards model, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LvCardsRowBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.lv_cards_row, parent, false);

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Cards model) {
        LvCardsRowBinding binding = DataBindingUtil.getBinding(view);
        binding.tvCardName.setText(model.getName());
        binding.color.setText(model.getColor());
        binding.rarity.setText(model.getRarity());
        binding.type.setText(model.getType());

        Glide.with(context).load(model.getImageUrl()).into(binding.imgUrl);
    }

}
