package com.example.a46066294p.magiccardsapi;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a46066294p.magiccardsapi.databinding.FragmentDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    /*
    private View view;
    private ImageView ivImageCard;
    private TextView tvCardColor;
    private TextView tvCardRarity;
    private TextView tvCardType;
    private TextView tvCardName;
    private TextView tvCardText;
     */

    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail, container, false);

        View view = binding.getRoot();

        Intent i = getActivity().getIntent();
        if (i != null) {
            Cards card = (Cards) i.getSerializableExtra("card");//s'obte l'objecte serialitzat que es diu "card"

            if (card != null) {
                updateCard(card);
            }
        }
        //View view = inflater.inflate(R.layout.fragment_detail, container, false);

        /*
        ivImageCard = (ImageView) view.findViewById(R.id.ivImageCard);
        tvCardName = (TextView) view.findViewById(R.id.tvCardName);
        tvCardType = (TextView) view.findViewById(R.id.tvCardType);
        tvCardRarity = (TextView) view.findViewById(R.id.tvCardRarity);
        tvCardColor = (TextView) view.findViewById(R.id.tvCardColor);
        tvCardText = (TextView) view.findViewById(R.id.tvCardText);
         */


        return view;
    }

    private void updateCard(Cards card) {
        Log.d("CARD", card.toString());

        binding.tvCardName.setText(card.getName());
        binding.tvCardType.setText(card.getType());
        binding.tvCardRarity.setText(card.getRarity());
        binding.tvCardColor.setText((CharSequence) card.getColor().toString());
        binding.tvCardText.setText(card.getText());

        Glide.with(getContext()).load(card.getImageUrl()).into(binding.ivImageCard);
    }

}

