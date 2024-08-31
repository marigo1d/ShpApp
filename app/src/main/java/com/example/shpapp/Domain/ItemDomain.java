package com.example.shpapp.Domain;

import java.io.Serializable;

public class ItemDomain implements Serializable {
    private int id;
    private String tradename;
    private String picUrl;
    private double score;
    private double price;
    private String description;

    public ItemDomain(int id, String tradename, String picUrl, double score, double price, String description) {
        this.id = id;
        this.tradename = tradename;
        this.picUrl = picUrl;
        this.score = score;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
