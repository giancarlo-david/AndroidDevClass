package edu.txstate.gsd26.hw3;

import androidx.annotation.NonNull;

public class Car {
    private int id;
    private String brand;
    private String name;
    private double costPerDay;
    private String url;

    @NonNull
    @Override
    public String toString() {
        return id + ": " + brand + name;
    }

    public Car(int id, String brand, String name, double costPerDay, String url) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.costPerDay = costPerDay;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
