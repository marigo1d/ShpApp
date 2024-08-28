package com.example.shpapp.Domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {
    private String title;
    private String picUrl;
    private int review;
    private double score;
    private int numberInCart;
    private double price;
    private String description;

    public PopularDomain(String title, String picUrl, int review, double score, double price, String description) {
        this.title = title;
        this.picUrl = picUrl;
        this.review = review;
        this.score = score;
        this.price = price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInChart) {
        this.numberInCart = numberInChart;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
