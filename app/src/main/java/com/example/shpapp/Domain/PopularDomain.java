package com.example.shpapp.Domain;

public class PopularDomain {
    private String title;
    private String picUrl;
    private int review;
    private double score;
    private int numberInChart;
    private double prices;
    private String description;

    public PopularDomain(String title, String picUrl, int review, double score, double prices, String description) {
        this.title = title;
        this.picUrl = picUrl;
        this.review = review;
        this.score = score;
        this.prices = prices;
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

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public int getNumberInChart() {
        return numberInChart;
    }

    public void setNumberInChart(int numberInChart) {
        this.numberInChart = numberInChart;
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
