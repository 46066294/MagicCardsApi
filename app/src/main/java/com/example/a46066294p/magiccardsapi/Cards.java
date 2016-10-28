package com.example.a46066294p.magiccardsapi;

/**
 * Created by 46066294p on 14/10/16.
 */

public class Cards {

    private String name;
    String rarity;
    String text;
    String type;
    String imageUrl;

    public Cards(){}

    public Cards(String name, String rarity, String text, String type, String imageUrl) {
        this.name = name;
        this.rarity = rarity;
        this.text = text;
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}