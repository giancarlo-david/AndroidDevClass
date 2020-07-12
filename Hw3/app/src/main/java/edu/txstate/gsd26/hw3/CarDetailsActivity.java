package edu.txstate.gsd26.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CarDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        TextView carID = findViewById(R.id.txtID);
        TextView carBrand = findViewById(R.id.txtBrand);
        TextView carName = findViewById(R.id.txtName);
        TextView carCostPerDay = findViewById(R.id.txtCostPerDay);
        final EditText numberOfDays = findViewById(R.id.txtNumberOfDays);


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(CarDetailsActivity.this);

        final Car selectedCar = new Car(pref.getInt("KeyID", 0),
                pref.getString("KeyBrand", null),
                pref.getString("KeyName", null),
                pref.getFloat("KeyCost", 0),
                pref.getString("KeyURL", null));

        DecimalFormat f = new DecimalFormat("###,###.##");

        carID.setText("ID: " + String.valueOf(selectedCar.getId()));
        carBrand.setText("Brand: " + selectedCar.getBrand());
        carName.setText("Name: " + selectedCar.getName());
        carCostPerDay.setText("$" + f.format(selectedCar.getCostPerDay()));

        Button calculate = findViewById(R.id.btnCalculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int intNumDays = Integer.parseInt(numberOfDays.getText().toString());
                    double totalCost = selectedCar.getCostPerDay() * intNumDays;
                    Toast.makeText(CarDetailsActivity.this, "Total Cost: $" + totalCost, Toast.LENGTH_LONG).show();
                }
                catch(Exception ex){
                    Toast.makeText(CarDetailsActivity.this, "Please enter a whole number for the number of days", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button getInfo = findViewById(R.id.btnInfo);

        getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCar.getUrl().equals(null))
                {

                }

                else
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(selectedCar.getUrl())));
                }
            }
        });
    }
}