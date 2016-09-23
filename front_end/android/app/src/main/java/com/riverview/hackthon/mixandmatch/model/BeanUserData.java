package com.riverview.hackthon.mixandmatch.model;

/**
 * Created by Rumesha on 23/09/2016.
 */

public class BeanUserData {

    private int id;
    private String name;
    private String email;
    private String cantact_number;
    private String city;
    private String country;


    public String getCantact_number() {
        return cantact_number;
    }

    public void setCantact_number(String cantact_number) {
        this.cantact_number = cantact_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
