package edu.txstate.gsd26.hw4;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Car {

    private int id;
    private String name;
    private String brand;
    private double costPerDay;
    private String url;

    public Car(int id, String name, String brand, double costPerDay, String url) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.costPerDay = costPerDay;
        this.url = url;
    }

    public Car(JSONObject object){
        try{
            this.id = object.getInt("ID");
            this.brand = object.getString("Brand");
            this.name = object.getString("Name");
            this.costPerDay = object.getDouble("Cost");
            this.url = object.getString("URL");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }



    @NonNull
    @Override
    public String toString() {
        return id + ": " + brand + " " + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
