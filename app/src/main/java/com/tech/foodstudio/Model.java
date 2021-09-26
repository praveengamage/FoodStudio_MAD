package com.tech.foodstudio;

public class Model {

    String id;
    String date;
    String description;
    String promoCode;

    public Model() {
    }

    public Model(String id, String date, String description, String promoCode) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.promoCode = promoCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}
