package com.reber.cryptotracker.Models;
import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("name")
     public String name;
    @SerializedName("symbol")
    public String symbol;
    @SerializedName("price_usd")
    public String price;
}
