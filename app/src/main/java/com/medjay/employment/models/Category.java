package com.medjay.employment.models;

public class Category {

    int Logo;
    String name;

    public Category(int logo, String name) {
        Logo = logo;
        this.name = name;
    }

    public int getLogo() {
        return Logo;
    }

    public String getName() {
        return name;
    }
}
