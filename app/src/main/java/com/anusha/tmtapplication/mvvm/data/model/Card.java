package com.anusha.tmtapplication.mvvm.data.model;

import com.google.gson.annotations.SerializedName;

public class Card {

    public String card_type;

    @SerializedName("card")
    public Card2 card2;
}
