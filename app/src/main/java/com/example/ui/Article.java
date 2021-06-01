package com.example.ui;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("title")
    String title;
    @SerializedName("link")
    String link;
    @SerializedName("sharedate")
    String date;
    @SerializedName("shareUser")
    String userName;

}
