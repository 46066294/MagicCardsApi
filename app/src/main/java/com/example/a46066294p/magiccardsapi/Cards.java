package com.example.a46066294p.magiccardsapi;

import java.io.Serializable;

/**
 * Created by 46066294p on 14/10/16.
 */

public class Cards implements Serializable{

    private String name;
    private String color;
    private String rarity;
    private String text;
    private String type;
    private String imageUrl;

    public Cards(){}

    public Cards(String name, String color, String rarity, String text, String type, String imageUrl) {
        this.name = name;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
                ", color='" + color + '\'' +
                ", rarity='" + rarity + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}' + "\n";
    }

}