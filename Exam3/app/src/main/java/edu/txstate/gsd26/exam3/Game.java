package edu.txstate.gsd26.exam3;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Game {
    private int id;
    private String title;
    private int sales;

    @NonNull
    @Override
    public String toString() {
        return "Title: " +  id + "\t # of Sales: " + sales;
    }

    public Game() {
    }

    public Game(JSONObject object) {
        try{
            this.id = object.getInt("Id");
            this.title = object.getString("Title");
            this.sales = object.getInt("Sales");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Game(int id, String title, int sales) {
        this.id = id;
        this.title = title;
        this.sales = sales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}
