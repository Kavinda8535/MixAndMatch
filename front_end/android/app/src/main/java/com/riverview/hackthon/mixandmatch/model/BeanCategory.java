package com.riverview.hackthon.mixandmatch.model;

/**
 * Created by Rumesha on 23/09/2016.
 */

public class BeanCategory {

    private int id;
    private String name;
    private int singlePiece;
    private int order;

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public int getSinglePiece() {
        return singlePiece;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setSinglePiece(int singlePiece) {
        this.singlePiece = singlePiece;
    }
}
