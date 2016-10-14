package com.example.a46066294p.magiccardsapi;

/**
 * Created by 46066294p on 14/10/16.
 */

public class Cards {
    private String cardName;
    private int idCard;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cardName='" + cardName + '\'' +
                ", idCard=" + idCard +
                '}';
    }
}//Cards class
