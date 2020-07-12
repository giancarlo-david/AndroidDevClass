package edu.txstate.gsd26.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends ListActivity {

    List<Car> carList = new ArrayList<Car>();

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Car selectedCar = carList.get(position);

        DecimalFormat f = new DecimalFormat("###,###.##");
        Toast.makeText(CarListActivity.this, "Cost per day is: $" +
                f.format(selectedCar.getCostPerDay()), Toast.LENGTH_SHORT).show();

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(CarListActivity.this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("KeyID", selectedCar.getId());
        editor.putString("KeyBrand", selectedCar.getBrand());
        editor.putString("KeyName", selectedCar.getName());
        editor.putFloat("KeyCost", (float) selectedCar.getCostPerDay());
        editor.putString("KeyURL", selectedCar.getUrl());
        editor.putInt("KeyImage", selectedCar.getImage());

        editor.commit();

        startActivity(new Intent(CarListActivity.this, CarDetailsActivity.class));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Car car1 = new Car(101, "Mazda", "6",40.00, "https://www.mazdausa.com/vehicles/mazda6", R.drawable.mazda6);
        Car car2 = new Car(102, "Honda","Accord",35.00, "https://automobiles.honda.com/accord-sedan", R.drawable.hondaaccord);
        Car car3 = new Car(103, "Toyota", "Camry",35.00, "https://www.toyota.com/camry/", R.drawable.toyotacamry);
        Car car4 = new Car(104, "Ford", "Fusion",40.00, "https://www.ford.com/cars/fusion/", R.drawable.fordfusion);
        Car car5 = new Car(105, "Audi", "A4",50.00,"https://www.audiusa.com/models/audi-a4", R.drawable.audia4);

        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
        carList.add(car4);
        carList.add(car5);

        setListAdapter(new CarAdapter(this, R.layout.car_row, carList));
    }
}