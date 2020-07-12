package edu.txstate.sl20.mycityguide;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Attraction {

    private int id;
    private String name;
    private double cost;
    private String url;

    public Attraction(int id, String name, double cost, String url) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.url = url;
    }

    public Attraction(JSONObject object) {
        try {
            this.id = object.getInt("Id");  //case sensitive
            this.name = object.getString("Name");
            this.cost = object.getDouble("Cost");
            this.url = object.getString("Url");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Attraction() {
    }

    @NonNull
    @Override
    public String toString() {
        //return super.toString();
        return id + ": " + name;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
