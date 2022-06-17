package com.example.loginregister;

import android.util.Log;

public class Cat {

    private int id;
    private String name;
    private double price;
    private double price_btc;
    private double price_eth;
    private String image;

    public Cat(int id, String name, double price, double price_btc, double price_eth, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.price_btc = price_btc;
        this.price_eth = price_eth;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getPrice_btc() {
        return price_btc;
    }

    public double getPrice_eth() {
        return price_eth;
    }

    public String getImage() {
        return image;
    }

}
