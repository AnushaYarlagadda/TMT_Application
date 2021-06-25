package com.anusha.tmtapplication.mvvm.data.model;

import com.google.gson.annotations.SerializedName;

public class Card2 {
    public String value;
    public Attributes attributes;

    @SerializedName("title")
    public TextValue title;

    @SerializedName("description")
    public TextValue description;
    public Image image;
}
