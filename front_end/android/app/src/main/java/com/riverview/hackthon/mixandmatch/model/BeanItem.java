package com.riverview.hackthon.mixandmatch.model;

/**
 * Created by Rumesha on 23/09/2016.
 */

public class BeanItem {

    private int id;
    private int categoryId;
    private String image;
    private String color;
    private String brand;

    public String getBrand() {
        return brand;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getColor() {
        return color;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
